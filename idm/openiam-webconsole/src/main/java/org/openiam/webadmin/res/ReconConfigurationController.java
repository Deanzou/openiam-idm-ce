package org.openiam.webadmin.res;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.batch.dto.BatchTask;
import org.openiam.idm.srvc.batch.service.BatchDataService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.recon.dto.ReconciliationSituation;
import org.openiam.idm.srvc.recon.ws.AsynchReconciliationService;
import org.openiam.idm.srvc.recon.ws.ReconciliationWebService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for the new hire form.
 * 
 * @author suneet
 */
public class ReconConfigurationController extends CancellableFormController {

	protected String redirectView;
	protected NavigatorDataWebService navigationDataService;
	protected ReconciliationWebService reconcileService;
	protected BatchDataService batchDataService;
	protected AsynchReconciliationService asynchReconService;
	private ConnectorDataService connectorService;
	private ResourceDataService resourceDataService;

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	public void setPathToCSV(String pathToCSV) {
		this.pathToCSV = pathToCSV;
	}

	protected ManagedSystemDataService managedSysService;
	private String pathToCSV;

	private static final Log log = LogFactory
			.getLog(ReconConfigurationController.class);

	public ReconConfigurationController() {
		super();
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("MM/dd/yyyy"), true));
	}

	@Override
	protected ModelAndView onCancel(Object command) throws Exception {
		return new ModelAndView(new RedirectView(getCancelView(), true));
	}

	private String getFileName(ManagedSys mSys, String prefix, boolean isShow) {

		StringBuilder sb = new StringBuilder();
		sb.append(pathToCSV);
		sb.append(prefix);
		sb.append(mSys.getManagedSysId());
		sb.append(mSys.getResourceId());
		if (isShow)
			sb.append(".html");
		else
			sb.append(".csv");
		return sb.toString();
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String menuGrp = request.getParameter("menugrp");
		String resId = request.getParameter("objId");

		if (resId != null && resId.length() > 0) {
			request.setAttribute("objId", resId);
		}

		ReconConfigurationCommand cmd = new ReconConfigurationCommand();
		try {
			ManagedSys mSys = managedSysService.getManagedSysByResource(resId);
			ProvisionConnector pCon = connectorService.getConnector(mSys
					.getConnectorId());

			boolean isCSV = pCon != null
					&& pCon.getServiceUrl().contains("CSVConnectorService");

			cmd.setIsCSV(isCSV);
			if (isCSV) {
				cmd.setReconCSVName(this.getFileName(mSys, "recon_", false));
				cmd.setCsvDirectory(pathToCSV);
			}
			File file = new File(this.getFileName(mSys, "report_", false));
			cmd.setIsReportExist(file.exists());
		} catch (Exception e) {
			log.error(e.getMessage());
			cmd.setIsCSV(false);
		}
		ReconciliationConfig config = reconcileService.getConfigByResource(
				resId).getConfig();
		if (config == null) {

			cmd.getConfig().setResourceId(resId);

			cmd.getSituationList().add(
					new ReconciliationSituation(null, "Match Found"));
			cmd.getSituationList().add(
					new ReconciliationSituation(null, "Resource Delete"));
			cmd.getSituationList().add(
					new ReconciliationSituation(null, "IDM Delete"));
			cmd.getSituationList().add(
					new ReconciliationSituation(null, "IDM Not Found"));
			cmd.getSituationList().add(
					new ReconciliationSituation(null, "Login Not Found"));
			// cmd.getSituationList().add(new ReconciliationSituation(null,
			// "IDM Changed"));
			// cmd.getSituationList().add(new ReconciliationSituation(null,
			// "Resource Changed"));
		} else {
			// move set to a list
			cmd.setConfig(config);
			List<ReconciliationSituation> situationList = new ArrayList<ReconciliationSituation>();
			for (ReconciliationSituation s : config.getSituationSet()) {
				situationList.add(s);
			}
			cmd.setSituationList(situationList);

		}

		List<Menu> level3MenuList = navigationDataService.menuGroupByUser(
				menuGrp, userId, "en").getMenuList();

		Resource res = resourceDataService.getResource(resId);
		request.setAttribute("menuL3", ResourceMenuHelper.resourceTypeMenu(res
				.getResourceType().getResourceTypeId(), level3MenuList));

		return cmd;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.debug("onSubmit called");

		ReconConfigurationCommand configCommand = (ReconConfigurationCommand) command;

		ReconciliationConfig config = configCommand.getConfig();
		List<ReconciliationSituation> situationList = configCommand
				.getSituationList();
		ManagedSys mSys = managedSysService.getManagedSysByResource(config
				.getResourceId());
		String btn = request.getParameter("btn");
		String configId = config.getReconConfigId();
		if (btn != null && btn.equalsIgnoreCase("Export to CSV")) {
			return download(response, mSys, "");
		}

		if (btn != null && btn.equalsIgnoreCase("Export report")) {
			return download(response, mSys, "report_");
		}

		if (btn != null && btn.equalsIgnoreCase("Delete")) {

			reconcileService.removeConfig(configId);

			// remove the synch job that is linked to it.
			String name = "Reconcil:" + configId;
			// check if a batch object for this already exists
			BatchTask task = batchDataService.getTaskByName(name);
			if (task != null) {
				batchDataService.removeBatchTask(task.getTaskId());
			}

			ModelAndView mav = new ModelAndView("/deleteconfirm");
			mav.addObject("msg", "Configuration has been successfully deleted.");
			return mav;

		}

		if (config.getReconConfigId() == null
				|| config.getReconConfigId().length() == 0) {
			// new
			log.info("Creating new configuration..");

			config.setReconConfigId(null);
			// build the set of situation objects
			Set<ReconciliationSituation> situationSet = new HashSet<ReconciliationSituation>();
			for (ReconciliationSituation s : situationList) {
				s.setReconConfigId(null);
				s.setReconSituationId(null);
				situationSet.add(s);

			}
			config.setSituationSet(situationSet);
			configId = reconcileService.addConfig(config).getConfig()
					.getReconConfigId();

		} else {
			// existing record
			Set<ReconciliationSituation> situationSet = new HashSet<ReconciliationSituation>();
			for (ReconciliationSituation s : situationList) {
				situationSet.add(s);

			}
			config.setSituationSet(situationSet);
			reconcileService.updateConfig(config);

		}
		// update the batch configuration
		if (configId != null) {
			String name = "Reconcil:" + configId;
			// check if a batch object for this already exists
			BatchTask task = batchDataService.getTaskByName(name);
			if (task == null) {
				task = new BatchTask();
				task.setTaskName(name);
				task.setTaskId(null);
				task.setParam1(configId);
				task.setTaskUrl("batch/reconciliation.groovy");
			}
			if (config.getFrequency() == null
					|| config.getFrequency().length() == 0) {
				task.setFrequencyUnitOfMeasure(null);
				task.setEnabled(0);
			} else {
				task.setFrequencyUnitOfMeasure(config.getFrequency());
				task.setEnabled(1);
			}
			if (task.getTaskId() == null) {
				this.batchDataService.addBatchTask(task);
			} else {
				batchDataService.upateBatchTask(task);
			}
		}

		String view = redirectView
				+ "?mode=1&menuid=RECONCILCONFIG&menugrp=SECURITY_RES&objId="
				+ config.getResourceId();
		log.info("redirecting to=" + view);

		if (btn != null && btn.equalsIgnoreCase("Show report")) {
			FileInputStream is = new FileInputStream(new File(this.getFileName(
					mSys, "report_", true)));
			ServletOutputStream os = response.getOutputStream();
			byte[] buffer = new byte[65536];
			int data;
			while ((data = is.read(buffer)) != -1) {
				os.write(buffer, 0, data);
			}
			os.flush();
			return null;
		}

		if (btn != null && btn.equalsIgnoreCase("Reconcile Now")) {
			if (config != null) {
				asynchReconService.startReconciliation(config);
				return new ModelAndView(new RedirectView(view, true));
			}

		}

		return new ModelAndView(new RedirectView(view, true));

	}

	private ModelAndView download(HttpServletResponse response,
			ManagedSys mSys, String preffix) {
		FileInputStream stream = null;
		File file;
		int length = 0;
		try {
			file = new File(this.getFileName(mSys, preffix, true));
			if (!file.exists()) {
				log.error("Nothing to Export");
			} else {
				response.setContentType("text/plain");
				response.setHeader("Content-Disposition",
						"attachment;filename=ExportData.csv");
				//
				// Stream to the requester.
				//
				ServletOutputStream op = response.getOutputStream();
				byte[] bbuf = new byte[1024];
				DataInputStream in = new DataInputStream(new FileInputStream(
						file));
				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, length);
				}
				in.close();
				op.flush();
				op.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private byte[] readFile(File file) {
		int ch;
		StringBuffer strContent = new StringBuffer("");
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			while ((ch = fin.read()) != -1)
				strContent.append((char) ch);
			fin.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return strContent.toString().getBytes();
	}

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public ReconciliationWebService getReconcileService() {
		return reconcileService;
	}

	public void setReconcileService(ReconciliationWebService reconcileService) {
		this.reconcileService = reconcileService;
	}

	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}

	public BatchDataService getBatchDataService() {
		return batchDataService;
	}

	public void setBatchDataService(BatchDataService batchDataService) {
		this.batchDataService = batchDataService;
	}

	public void setAsynchReconService(
			AsynchReconciliationService asynchReconService) {
		this.asynchReconService = asynchReconService;
	}

	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}

	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}
}
