/*
* ------------------------------------------------------------------------------
* Title: diamelle.admin.security.IndexAction
* Author: Diamelle Technologies 
* Overview: Sets up static data required to run the Admin App
* ------------------------------------------------------------------------------
* Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
*
* This SOURCE CODE FILE, which has been provided by Diamelle Technologies as part
* of a Diamelle Software product for use ONLY by licensed users of the product,
* includes CONFIDENTIAL and PROPRIETARY information of Diamelle Technologies.
*
* This code or parts or derivatives of it cannot be used for any commercial
* products without written permission from Diamelle Technologies.
*
* USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
*
* IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
* RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
* CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
* DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
* ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
* DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
* ------------------------------------------------------------------------------
* CHANGE CONTROL:
* Last modified by : 
* on : 
* ------------------------------------------------------------------------------
*/
package org.openiam.selfsrvc;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
import org.apache.struts.util.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.apache.struts.action.DynaActionForm;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
import org.openiam.idm.srvc.email.MailService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.service.dto.Service;
//import org.openiam.selfsrvc.util.*;

import diamelle.common.status.*;
import diamelle.common.service.*;

/**
 * @version 	1.0
 * @author
 */
public class IncidentAction extends NavigationAction {
	LoginAccess loginAccess = new LoginAccess();
	ServiceAccess serviceAccess = null;
	
	SecurityDomainAccess secDomainAccess = null;
	AppConfiguration appConfiguration;


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		DynaActionForm incidentForm = (DynaActionForm)form;
		String from = (String)incidentForm.get("from");
		String subject = (String)incidentForm.get("subject");
		String msg = request.getParameter("msg");
		
		WebApplicationContext webContext =  getWebApplicationContext();
		
		MailService mailServc = (MailService)webContext.getBean("mailService");
		System.out.println("Sending email from new hire form....");
		mailServc.send(from, "Agent.SSO@ceoit.ocgov.com", subject,msg);
		
		return mapping.findForward("success");
		

	}
	

}