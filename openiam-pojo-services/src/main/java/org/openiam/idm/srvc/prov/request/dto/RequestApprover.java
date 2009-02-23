package org.openiam.idm.srvc.prov.request.dto;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * ReqApprover generated by hbm2java
 */
public class RequestApprover implements java.io.Serializable {

	private String reqApproverId;
	private String userId;
	private String approverLevel;
	private String mngSysGroupId;
	private String managedSysId;
	private Set<ApprovalHistory> approvalHistories = new HashSet<ApprovalHistory>(
			0);

	public RequestApprover() {
	}

	public RequestApprover(String reqApproverId, String mngSysGroupId,
			String managedSysId) {
		this.reqApproverId = reqApproverId;
		this.mngSysGroupId = mngSysGroupId;
		this.managedSysId = managedSysId;
	}

	public RequestApprover(String reqApproverId, String userId,
			String approverLevel, String mngSysGroupId, String managedSysId,
			Set<ApprovalHistory> approvalHistories) {
		this.reqApproverId = reqApproverId;
		this.userId = userId;
		this.approverLevel = approverLevel;
		this.mngSysGroupId = mngSysGroupId;
		this.managedSysId = managedSysId;
		this.approvalHistories = approvalHistories;
	}

	public String getReqApproverId() {
		return this.reqApproverId;
	}

	public void setReqApproverId(String reqApproverId) {
		this.reqApproverId = reqApproverId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApproverLevel() {
		return this.approverLevel;
	}

	public void setApproverLevel(String approverLevel) {
		this.approverLevel = approverLevel;
	}

	public String getMngSysGroupId() {
		return this.mngSysGroupId;
	}

	public void setMngSysGroupId(String mngSysGroupId) {
		this.mngSysGroupId = mngSysGroupId;
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public Set<ApprovalHistory> getApprovalHistories() {
		return this.approvalHistories;
	}

	public void setApprovalHistories(Set<ApprovalHistory> approvalHistories) {
		this.approvalHistories = approvalHistories;
	}

}