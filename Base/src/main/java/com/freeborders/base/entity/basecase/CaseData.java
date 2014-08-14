package com.freeborders.base.entity.basecase;

import com.freeborders.base.entity.administration.UserData;
import com.freeborders.base.enumeration.CaseType;
import com.freeborders.base.enumeration.HandleCaseAs;

/**
 * @author tom.luo
 * 
 */
public class CaseData {
	private static final long serialVersionUID = 1;
	private SearchData searchData;
	private String caseId;
	private String caseOiDocketNum;
	private String caseOiDocketSequence;
	private String caseTypeId;
	private String plaintiffId;
	private String comCaseStatusId;
	private String caseCauseId;
	private String caseCauseName;
	private String comDispId;
	private String casePostVerdict;
	private String caseReceivedDate;
	private String caseDiseaseId;
	private String caseManifestationDate;
	private String comUpgrade;
	private String courtTypeName;
	private String courtfiledDate;
	private String courtservedDate;
	private String courtjurisdiction;
	private String courtDocketNumber;
	private String casegroupID;
	private String comSettleAmount;
	private String comSettleDate;
	private String caseDispDate;
	private String caseOiStateID;
	private String comPayStatusId;
	private String caseReleaseTypeId;
	private String caseShadowCase;
	private String comWasNotification;
	private String caseActivationDate;
	private String attorneyId;
	private String attorneyDocketNumber;
	private String authorizedForBilling;
	private String attorneyNationalId;
	private String agreementId;
	private String agreementName;
	private String attorneyVendorId;
	private String attorneyjurisdictionManager;
	private String comExpStatusId;
	private String caseExposureStart;
	private String caseExposureEnd;
	private String caseOccupationName;
	private String comDuplicate;
	private String caseCountedId;
	private String caseSecondExposure;
	private String caseOriginalOiDocket;
	// private String caseOriginalOiSequence;
	private String caseOriginalPlaintiffRelation;
	private String caseComments;
	private String caseCreatedDate;
	private String caseCreatedByUserId;
	private String caseModifyDate;
	private String caseModifyByUser;
	private String caseUpgradeFromCaseId;
	private String plainSsn;
	private String plainLastName;
	private String plaintFirstName;
	private String diseaseIsReadOnly;
	private String caseOccuCatorgryName;
	private String plainFullName;
	private String plainBirhDate;
	private String plainDeathDate;
	private String plainCity;
	private String plainStateName;
	private String plainCreateUserId;
	private String plainModifyUserId;
	// name based on id
	private String caseDiseaseName;
	private String caseStatusName;
	private String caseDispName;
	private String ageAtServeDate;
	private String subOccpDescription;
	private int isDataComplete;
	private String caseStateName;
	private String caseGroupName;
	private String attorneyName;
	private String createUserName;
	private String modifiedUserName;
	private String exposureStatusName;
	private String paymentStatusName;
	private CaseType caseTypeName;
	private String totalOiDocketNum;
	private HandleCaseAs handleCase;
	private String vendorName;
	private int secondExposure;
	private String secondExposureDocket;
	private String releaseTypeName;
	private String bocciManager;
	private String isHotCase;
	private String approveReason;
	private String isHousHold;
	private String sex;
	private String isJurisdicionManger;
	private String upgradeCaseType;
	private String masterCaseType;
	private String exposureCaseTypeId;
	private String plaintiffModifyDate;
	private String plaintiffCreateDate;
	private String caseOriginalExposureCaseId;
	private String caseSettlementRequest;
	private String StateFlag;
	private String approve_reject_status;
	private String approve_reject_date;
	private String approve_reject_userId;
	private String finalApproveDate;
	private String finalApporveUserId;
	private String diagnosisDate;
	private String demand;
	private String hasOriginal;
	private int ce = 0;
	private int fp = 0;
	private String oiStateName;
	private String OiDocktNumber;
	private String fullName;
	private String plaintiffAttorneyName;
	private String name;
	private int discoveryCount;
	private String medicareReport;
	private int foreignExposure;

	private UserData userData = new UserData();

	public int getDiscoveryCount() {
		return discoveryCount;
	}

	public void setDiscoveryCount(int discoveryCount) {
		this.discoveryCount = discoveryCount;
	}

	public String getOiStateName() {
		return oiStateName;
	}

	public void setOiStateName(String oiStateName) {
		this.oiStateName = oiStateName;
	}

	public String getOiDocktNumber() {
		return OiDocktNumber;
	}

	public void setOiDocktNumber(String oiDocktNumber) {
		OiDocktNumber = oiDocktNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPlaintiffAttorneyName() {
		return plaintiffAttorneyName;
	}

	public void setPlaintiffAttorneyName(String plaintiffAttorneyName) {
		this.plaintiffAttorneyName = plaintiffAttorneyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the hasOriginal.
	 */
	public String getHasOriginal() {
		return hasOriginal;
	}

	/**
	 * @param hasOriginal
	 *            The hasOriginal to set.
	 */
	public void setHasOriginal(String hasOriginal) {
		this.hasOriginal = hasOriginal;
	}

	/**
	 * @return Returns the demand.
	 */
	public String getDemand() {
		return demand;
	}

	/**
	 * @param demand
	 *            The demand to set.
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}

	/**
	 * @return Returns the diagnosisDate.
	 */
	public String getDiagnosisDate() {
		return diagnosisDate;
	}

	/**
	 * @param diagnosisDate
	 *            The diagnosisDate to set.
	 */
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	/**
	 * @return Returns the finalApporveUserId.
	 */
	public String getFinalApporveUserId() {
		return finalApporveUserId;
	}

	/**
	 * @param finalApporveUserId
	 *            The finalApporveUserId to set.
	 */
	public void setFinalApporveUserId(String finalApporveUserId) {
		this.finalApporveUserId = finalApporveUserId;
	}

	/**
	 * @return Returns the finalApproveDate.
	 */
	public String getFinalApproveDate() {
		return finalApproveDate;
	}

	/**
	 * @param finalApproveDate
	 *            The finalApproveDate to set.
	 */
	public void setFinalApproveDate(String finalApproveDate) {
		this.finalApproveDate = finalApproveDate;
	}

	/**
	 * @return Returns the approve_reject_date.
	 */
	public String getApprove_reject_date() {
		return approve_reject_date;
	}

	/**
	 * @param approve_reject_date
	 *            The approve_reject_date to set.
	 */
	public void setApprove_reject_date(String approve_reject_date) {
		this.approve_reject_date = approve_reject_date;
	}

	/**
	 * @return Returns the approve_reject_status.
	 */
	public String getApprove_reject_status() {
		return approve_reject_status;
	}

	/**
	 * @param approve_reject_status
	 *            The approve_reject_status to set.
	 */
	public void setApprove_reject_status(String approve_reject_status) {
		this.approve_reject_status = approve_reject_status;
	}

	/**
	 * @return Returns the approve_reject_userId.
	 */
	public String getApprove_reject_userId() {
		return approve_reject_userId;
	}

	/**
	 * @param approve_reject_userId
	 *            The approve_reject_userId to set.
	 */
	public void setApprove_reject_userId(String approve_reject_userId) {
		this.approve_reject_userId = approve_reject_userId;
	}

	/**
	 * @return Returns the caseSettlementRequest.
	 */
	public String getCaseSettlementRequest() {
		return caseSettlementRequest;
	}

	/**
	 * @param caseSettlementRequest
	 *            The caseSettlementRequest to set.
	 */
	public void setCaseSettlementRequest(String caseSettlementRequest) {
		this.caseSettlementRequest = caseSettlementRequest;
	}

	/**
	 * @return Returns the stateFlag.
	 */
	public String getStateFlag() {
		return StateFlag;
	}

	/**
	 * @param stateFlag
	 *            The stateFlag to set.
	 */
	public void setStateFlag(String stateFlag) {
		StateFlag = stateFlag;
	}

	/**
	 * @return Returns the caseOriginalExposureCaseId.
	 */
	public String getCaseOriginalExposureCaseId() {
		return caseOriginalExposureCaseId;
	}

	/**
	 * @param caseOriginalExposureCaseId
	 *            The caseOriginalExposureCaseId to set.
	 */
	public void setCaseOriginalExposureCaseId(String caseOriginalExposureCaseId) {
		this.caseOriginalExposureCaseId = caseOriginalExposureCaseId;
	}

	/**
	 * @return Returns the caseOriginalOiDocket.
	 */
	public String getCaseOriginalOiDocket() {
		return caseOriginalOiDocket;
	}

	/**
	 * @param caseOriginalOiDocket
	 *            The caseOriginalOiDocket to set.
	 */
	public void setCaseOriginalOiDocket(String caseOriginalOiDocket) {
		this.caseOriginalOiDocket = caseOriginalOiDocket;
		this.searchData.setOiDocketNum(caseOriginalExposureCaseId);
	}

	/**
	 * @return Returns the caseOriginalOiSequence.
	 */
	// public String getCaseOriginalOiSequence() {
	// return caseOriginalOiSequence;
	// }
	//
	// /**
	// * @param caseOriginalOiSequence
	// * The caseOriginalOiSequence to set.
	// */
	// public void setCaseOriginalOiSequence(String caseOriginalOiSequence) {
	// this.caseOriginalOiSequence = caseOriginalOiSequence;
	// }

	/**
	 * @return Returns the plaintiffCreateDate.
	 */
	public String getPlaintiffCreateDate() {
		return plaintiffCreateDate;
	}

	/**
	 * @param plaintiffCreateDate
	 *            The plaintiffCreateDate to set.
	 */
	public void setPlaintiffCreateDate(String plaintiffCreateDate) {
		this.plaintiffCreateDate = plaintiffCreateDate;
	}

	/**
	 * @return Returns the plaintiffModifyDate.
	 */
	public String getPlaintiffModifyDate() {
		return plaintiffModifyDate;
	}

	/**
	 * @param plaintiffModifyDate
	 *            The plaintiffModifyDate to set.
	 */
	public void setPlaintiffModifyDate(String plaintiffModifyDate) {
		this.plaintiffModifyDate = plaintiffModifyDate;
	}

	/**
	 * @return Returns the exposureCaseTypeId.
	 */
	public String getExposureCaseTypeId() {
		return exposureCaseTypeId;
	}

	/**
	 * @param exposureCaseTypeId
	 *            The exposureCaseTypeId to set.
	 */
	public void setExposureCaseTypeId(String exposureCaseTypeId) {
		this.exposureCaseTypeId = exposureCaseTypeId;
	}

	/**
	 * @return Returns the masterCaseType.
	 */
	public String getMasterCaseType() {
		return masterCaseType;
	}

	/**
	 * @param masterCaseType
	 *            The masterCaseType to set.
	 */
	public void setMasterCaseType(String masterCaseType) {
		this.masterCaseType = masterCaseType;
	}

	/**
	 * @return Returns the upgradeCaseType.
	 */
	public String getUpgradeCaseType() {
		return upgradeCaseType;
	}

	/**
	 * @param upgradeCaseType
	 *            The upgradeCaseType to set.
	 */
	public void setUpgradeCaseType(String upgradeCaseType) {
		this.upgradeCaseType = upgradeCaseType;
	}

	/**
	 * @return Returns the isJurisdicionManger.
	 */
	public String getIsJurisdicionManger() {
		return isJurisdicionManger;
	}

	/**
	 * @param isJurisdicionManger
	 *            The isJurisdicionManger to set.
	 */
	public void setIsJurisdicionManger(String isJurisdicionManger) {
		this.isJurisdicionManger = isJurisdicionManger;
	}

	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return Returns the isHousHold.
	 */
	public String getIsHousHold() {
		return isHousHold;
	}

	/**
	 * @param isHousHold
	 *            The isHousHold to set.
	 */
	public void setIsHousHold(String isHousHold) {
		this.isHousHold = isHousHold;
	}

	/**
	 * @return Returns the approveReason.
	 */
	public String getApproveReason() {
		return approveReason;
	}

	/**
	 * @param approveReason
	 *            The approveReason to set.
	 */
	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	/**
	 * @return Returns the isHotCase.
	 */
	public String getIsHotCase() {
		return isHotCase;
	}

	/**
	 * @param isHotCase
	 *            The isHotCase to set.
	 */
	public void setIsHotCase(String isHotCase) {
		this.isHotCase = isHotCase;
	}

	/**
	 * @return Returns the bocciManager.
	 */
	public String getBocciManager() {
		return bocciManager;
	}

	/**
	 * @param bocciManager
	 *            The bocciManager to set.
	 */
	public void setBocciManager(String bocciManager) {
		this.bocciManager = bocciManager;
	}

	/**
	 * @return Returns the releaseTypeName.
	 */
	public String getReleaseTypeName() {
		return releaseTypeName;
	}

	/**
	 * @param releaseTypeName
	 *            The releaseTypeName to set.
	 */
	public void setReleaseTypeName(String releaseTypeName) {
		this.releaseTypeName = releaseTypeName;
	}

	/**
	 * @return Returns the hasExposure.
	 */
	public String getHasExposure() {
		return hasExposure;
	}

	/**
	 * @param hasExposure
	 *            The hasExposure to set.
	 */
	public void setHasExposure(String hasExposure) {
		this.hasExposure = hasExposure;
	}

	private String hasExposure = "0";// no exposure information

	/**
	 * @return Returns the caseOccuCatorgryName.
	 */
	public String getCaseOccuCatorgryName() {
		return caseOccuCatorgryName;
	}

	/**
	 * @param caseOccuCatorgryName
	 *            The caseOccuCatorgryName to set.
	 */
	public void setCaseOccuCatorgryName(String caseOccuCatorgryName) {
		this.caseOccuCatorgryName = caseOccuCatorgryName;
	}

	/**
	 * @return Returns the diseaseIsReadOnly.
	 */
	public String getDiseaseIsReadOnly() {
		return diseaseIsReadOnly;
	}

	/**
	 * @param diseaseIsReadOnly
	 *            The diseaseIsReadOnly to set.
	 */
	public void setDiseaseIsReadOnly(String diseaseIsReadOnly) {
		this.diseaseIsReadOnly = diseaseIsReadOnly;
	}

	/**
	 * @return Returns the plaintFirstName.
	 */
	public String getPlaintFirstName() {
		return plaintFirstName;
	}

	/**
	 * @param plaintFirstName
	 *            The plaintFirstName to set.
	 */
	public void setPlaintFirstName(String plaintFirstName) {
		this.plaintFirstName = plaintFirstName;
	}

	/**
	 * @return Returns the secondExposureDocket.
	 */
	public String getSecondExposureDocket() {
		return secondExposureDocket;
	}

	/**
	 * @param secondExposureDocket
	 *            The secondExposureDocket to set.
	 */
	public void setSecondExposureDocket(String secondExposureDocket) {
		this.secondExposureDocket = secondExposureDocket;
	}

	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName
	 *            The vendorName to set.
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return Returns the totalOiDocketNum.
	 */
	public String getTotalOiDocketNum() {
		return totalOiDocketNum;
	}

	/**
	 * @param totalOiDocketNum
	 *            The totalOiDocketNum to set.
	 */
	public void setTotalOiDocketNum(String totalOiDocketNum) {
		this.totalOiDocketNum = totalOiDocketNum;
	}

	/**
	 * @return Returns the paymentStatusName.
	 */
	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	/**
	 * @param paymentStatusName
	 *            The paymentStatusName to set.
	 */
	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	public CaseType getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(CaseType caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	/**
	 * @return Returns the exposureStatusName.
	 */
	public String getExposureStatusName() {
		return exposureStatusName;
	}

	/**
	 * @param exposureStatusName
	 *            The exposureStatusName to set.
	 */
	public void setExposureStatusName(String exposureStatusName) {
		this.exposureStatusName = exposureStatusName;
	}

	/**
	 * @return Returns the attorneyName.
	 */
	public String getAttorneyName() {
		return attorneyName;
	}

	/**
	 * @param attorneyName
	 *            The attorneyName to set.
	 */
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}

	/**
	 * @return Returns the caseDiseaseName.
	 */
	public String getCaseDiseaseName() {
		return caseDiseaseName;
	}

	/**
	 * @param caseDiseaseName
	 *            The caseDiseaseName to set.
	 */
	public void setCaseDiseaseName(String caseDiseaseName) {
		this.caseDiseaseName = caseDiseaseName;
	}

	/**
	 * @return Returns the caseDispName.
	 */
	public String getCaseDispName() {
		return caseDispName;
	}

	/**
	 * @param caseDispName
	 *            The caseDispName to set.
	 */
	public void setCaseDispName(String caseDispName) {
		this.caseDispName = caseDispName;
	}

	/**
	 * @return Returns the caseGroupName.
	 */
	public String getCaseGroupName() {
		return caseGroupName;
	}

	/**
	 * @param caseGroupName
	 *            The caseGroupName to set.
	 */
	public void setCaseGroupName(String caseGroupName) {
		this.caseGroupName = caseGroupName;
	}

	/**
	 * @return Returns the caseStateName.
	 */
	public String getCaseStateName() {
		return caseStateName;
	}

	/**
	 * @param caseStateName
	 *            The caseStateName to set.
	 */
	public void setCaseStateName(String caseStateName) {
		this.caseStateName = caseStateName;
	}

	/**
	 * @return Returns the caseStatusName.
	 */
	public String getCaseStatusName() {
		return caseStatusName;
	}

	/**
	 * @param caseStatusName
	 *            The caseStatusName to set.
	 */
	public void setCaseStatusName(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}

	/**
	 * @return Returns the createUserName.
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName
	 *            The createUserName to set.
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return Returns the modifiedUserName.
	 */
	public String getModifiedUserName() {
		return modifiedUserName;
	}

	/**
	 * @param modifiedUserName
	 *            The modifiedUserName to set.
	 */
	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	public CaseData() {

	}

	/**
	 * @return Returns the plainCreateUserId.
	 */
	public String getPlainCreateUserId() {
		return plainCreateUserId;
	}

	/**
	 * @param plainCreateUserId
	 *            The plainCreateUserId to set.
	 */
	public void setPlainCreateUserId(String plainCreateUserId) {
		this.plainCreateUserId = plainCreateUserId;
	}

	/**
	 * @return Returns the plainModifyUserId.
	 */
	public String getPlainModifyUserId() {
		return plainModifyUserId;
	}

	/**
	 * @param plainModifyUserId
	 *            The plainModifyUserId to set.
	 */
	public void setPlainModifyUserId(String plainModifyUserId) {
		this.plainModifyUserId = plainModifyUserId;
	}

	/**
	 * @return Returns the plaintiffId.
	 */
	public String getPlaintiffId() {
		return plaintiffId;
	}

	/**
	 * @return Returns the agreementId.
	 */
	public String getAgreementId() {
		return agreementId;
	}

	/**
	 * @param agreementId
	 *            The agreementId to set.
	 */
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	/**
	 * @return Returns the attorneyDocketNumber.
	 */
	public String getAttorneyDocketNumber() {
		return attorneyDocketNumber;
	}

	/**
	 * @param attorneyDocketNumber
	 *            The attorneyDocketNumber to set.
	 */
	public void setAttorneyDocketNumber(String attorneyDocketNumber) {
		this.attorneyDocketNumber = attorneyDocketNumber;
	}

	/**
	 * @return Returns the attorneyId.
	 */
	public String getAttorneyId() {
		return attorneyId;
	}

	/**
	 * @param attorneyId
	 *            The attorneyId to set.
	 */
	public void setAttorneyId(String attorneyId) {
		this.attorneyId = attorneyId;
	}

	/**
	 * @return Returns the attorneyjurisdictionManager.
	 */
	public String getAttorneyjurisdictionManager() {
		return attorneyjurisdictionManager;
	}

	/**
	 * @param attorneyjurisdictionManager
	 *            The attorneyjurisdictionManager to set.
	 */
	public void setAttorneyjurisdictionManager(String attorneyjurisdictionManager) {
		this.attorneyjurisdictionManager = attorneyjurisdictionManager;
	}

	/**
	 * @return Returns the attorneyNationalId.
	 */
	public String getAttorneyNationalId() {
		return attorneyNationalId;
	}

	/**
	 * @param attorneyNationalId
	 *            The attorneyNationalId to set.
	 */
	public void setAttorneyNationalId(String attorneyNationalId) {
		this.attorneyNationalId = attorneyNationalId;
	}

	/**
	 * @return Returns the attorneyVendorId.
	 */
	public String getAttorneyVendorId() {
		return attorneyVendorId;
	}

	/**
	 * @param attorneyVendorId
	 *            The attorneyVendorId to set.
	 */
	public void setAttorneyVendorId(String attorneyVendorId) {
		this.attorneyVendorId = attorneyVendorId;
	}

	/**
	 * @return Returns the authorizedForBilling.
	 */
	public String getAuthorizedForBilling() {
		return authorizedForBilling;
	}

	/**
	 * @param authorizedForBilling
	 *            The authorizedForBilling to set.
	 */
	public void setAuthorizedForBilling(String authorizedForBilling) {
		this.authorizedForBilling = authorizedForBilling;
	}

	/**
	 * @return Returns the caseActivationDate.
	 */
	public String getCaseActivationDate() {
		return caseActivationDate;
	}

	/**
	 * @param caseActivationDate
	 *            The caseActivationDate to set.
	 */
	public void setCaseActivationDate(String caseActivationDate) {
		this.caseActivationDate = caseActivationDate;
	}

	/**
	 * @return Returns the caseCauseId.
	 */
	public String getCaseCauseId() {
		return caseCauseId;
	}

	/**
	 * @return Returns the caseComments.
	 */
	public String getCaseComments() {
		return caseComments;
	}

	/**
	 * @param caseComments
	 *            The caseComments to set.
	 */
	public void setCaseComments(String caseComments) {
		this.caseComments = caseComments;
	}

	/**
	 * @return Returns the caseCountedId.
	 */
	public String getCaseCountedId() {
		return caseCountedId;
	}

	/**
	 * @param caseCountedId
	 *            The caseCountedId to set.
	 */
	public void setCaseCountedId(String caseCountedId) {
		this.caseCountedId = caseCountedId;
	}

	/**
	 * @return Returns the caseCreatedByUserId.
	 */
	public String getCaseCreatedByUserId() {
		return caseCreatedByUserId;
	}

	/**
	 * @param caseCreatedByUserId
	 *            The caseCreatedByUserId to set.
	 */
	public void setCaseCreatedByUserId(String caseCreatedByUserId) {
		this.caseCreatedByUserId = caseCreatedByUserId;
	}

	/**
	 * @return Returns the caseCreatedDate.
	 */
	public String getCaseCreatedDate() {
		return caseCreatedDate;
	}

	/**
	 * @param caseCreatedDate
	 *            The caseCreatedDate to set.
	 */
	public void setCaseCreatedDate(String caseCreatedDate) {
		this.caseCreatedDate = caseCreatedDate;
	}

	/**
	 * @return Returns the caseDiseaseId.
	 */
	public String getCaseDiseaseId() {
		return caseDiseaseId;
	}

	/**
	 * @param caseDiseaseId
	 *            The caseDiseaseId to set.
	 */
	public void setCaseDiseaseId(String caseDiseaseId) {
		this.caseDiseaseId = caseDiseaseId;
	}

	/**
	 * @return Returns the caseDispDate.
	 */
	public String getCaseDispDate() {
		return caseDispDate;
	}

	/**
	 * @param caseDispDate
	 *            The caseDispDate to set.
	 */
	public void setCaseDispDate(String caseDispDate) {
		this.caseDispDate = caseDispDate;
	}

	/**
	 * @return Returns the caseExposureEnd.
	 */
	public String getCaseExposureEnd() {
		return caseExposureEnd;
	}

	/**
	 * @param caseExposureEnd
	 *            The caseExposureEnd to set.
	 */
	public void setCaseExposureEnd(String caseExposureEnd) {
		this.caseExposureEnd = caseExposureEnd;
	}

	/**
	 * @return Returns the caseExposureStart.
	 */
	public String getCaseExposureStart() {
		return caseExposureStart;
	}

	/**
	 * @param caseExposureStart
	 *            The caseExposureStart to set.
	 */
	public void setCaseExposureStart(String caseExposureStart) {
		this.caseExposureStart = caseExposureStart;
	}

	/**
	 * @return Returns the caseExpStatusId.
	 */
	public String getComExpStatusId() {
		return comExpStatusId;
	}

	/**
	 * @param caseExpStatusId
	 *            The caseExpStatusId to set.
	 */
	public void setComExpStatusId(String caseExpStatusId) {
		this.comExpStatusId = caseExpStatusId;
	}

	/**
	 * @return Returns the casegroupID.
	 */
	public String getCasegroupID() {
		return casegroupID;
	}

	/**
	 * @param casegroupID
	 *            The casegroupID to set.
	 */
	public void setCasegroupID(String casegroupID) {
		this.casegroupID = casegroupID;
	}

	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @return Returns the caseManifestationDate.
	 */
	public String getCaseManifestationDate() {
		return caseManifestationDate;
	}

	/**
	 * @param caseManifestationDate
	 *            The caseManifestationDate to set.
	 */
	public void setCaseManifestationDate(String caseManifestationDate) {
		this.caseManifestationDate = caseManifestationDate;
	}

	/**
	 * @return Returns the caseModifyByUser.
	 */
	public String getCaseModifyByUser() {
		return caseModifyByUser;
	}

	/**
	 * @param caseModifyByUser
	 *            The caseModifyByUser to set.
	 */
	public void setCaseModifyByUser(String caseModifyByUser) {
		this.caseModifyByUser = caseModifyByUser;
	}

	/**
	 * @return Returns the caseModifyDate.
	 */
	public String getCaseModifyDate() {
		return caseModifyDate;
	}

	/**
	 * @param caseModifyDate
	 *            The caseModifyDate to set.
	 */
	public void setCaseModifyDate(String caseModifyDate) {
		this.caseModifyDate = caseModifyDate;
	}

	public String getCaseOccupationName() {
		return caseOccupationName;
	}

	public void setCaseOccupationName(String caseOccupationName) {
		this.caseOccupationName = caseOccupationName;
	}

	/**
	 * @return Returns the caseOiDocketSequence.
	 */
	public String getCaseOiDocketSequence() {
		return caseOiDocketSequence;
	}

	/**
	 * @return Returns the caseOiStateID.
	 */
	public String getCaseOiStateID() {
		return caseOiStateID;
	}

	/**
	 * @param caseOiStateID
	 *            The caseOiStateID to set.
	 */
	public void setCaseOiStateID(String caseOiStateID) {
		this.caseOiStateID = caseOiStateID;
	}

	public String getCaseOriginalPlaintiffRelation() {
		return caseOriginalPlaintiffRelation;
	}

	public void setCaseOriginalPlaintiffRelation(String caseOriginalPlaintiffRelation) {
		this.caseOriginalPlaintiffRelation = caseOriginalPlaintiffRelation;
	}

	/**
	 * @return Returns the casePostVerdict.
	 */
	public String getCasePostVerdict() {
		return casePostVerdict;
	}

	/**
	 * @param casePostVerdict
	 *            The casePostVerdict to set.
	 */
	public void setCasePostVerdict(String casePostVerdict) {
		this.casePostVerdict = casePostVerdict;
	}

	/**
	 * @return Returns the caseReceivedDate.
	 */
	public String getCaseReceivedDate() {
		return caseReceivedDate;
	}

	/**
	 * @param caseReceivedDate
	 *            The caseReceivedDate to set.
	 */
	public void setCaseReceivedDate(String caseReceivedDate) {
		this.caseReceivedDate = caseReceivedDate;
	}

	/**
	 * @return Returns the caseReleaseTypeId.
	 */
	public String getCaseReleaseTypeId() {
		return caseReleaseTypeId;
	}

	/**
	 * @param caseReleaseTypeId
	 *            The caseReleaseTypeId to set.
	 */
	public void setCaseReleaseTypeId(String caseReleaseTypeId) {
		this.caseReleaseTypeId = caseReleaseTypeId;
	}

	/**
	 * @return Returns the caseSecondExposure.
	 */
	public String getCaseSecondExposure() {
		return caseSecondExposure;
	}

	/**
	 * @param caseSecondExposure
	 *            The caseSecondExposure to set.
	 */
	public void setCaseSecondExposure(String caseSecondExposure) {
		this.caseSecondExposure = caseSecondExposure;
	}

	/**
	 * @return Returns the caseShadowCase.
	 */
	public String getCaseShadowCase() {
		return caseShadowCase;
	}

	/**
	 * @param caseShadowCase
	 *            The caseShadowCase to set.
	 */
	public void setCaseShadowCase(String caseShadowCase) {
		this.caseShadowCase = caseShadowCase;
	}

	/**
	 * @return Returns the caseTypeId.
	 */
	public String getCaseTypeId() {
		return caseTypeId;
	}

	/**
	 * @param caseTypeId
	 *            The caseTypeId to set.
	 */
	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	/**
	 * @return Returns the caseUpgradeFromCaseId.
	 */
	public String getCaseUpgradeFromCaseId() {
		return caseUpgradeFromCaseId;
	}

	/**
	 * @param caseUpgradeFromCaseId
	 *            The caseUpgradeFromCaseId to set.
	 */
	public void setCaseUpgradeFromCaseId(String caseUpgradeFromCaseId) {
		this.caseUpgradeFromCaseId = caseUpgradeFromCaseId;
	}

	/**
	 * @return Returns the comCaseStatusId.
	 */
	public String getComCaseStatusId() {
		return comCaseStatusId;
	}

	/**
	 * @param comCaseStatusId
	 *            The comCaseStatusId to set.
	 */
	public void setComCaseStatusId(String comCaseStatusId) {
		this.comCaseStatusId = comCaseStatusId;
	}

	/**
	 * @return Returns the comDispId.
	 */
	public String getComDispId() {
		return comDispId;
	}

	/**
	 * @param comDispId
	 *            The comDispId to set.
	 */
	public void setComDispId(String comDispId) {
		this.comDispId = comDispId;
	}

	/**
	 * @return Returns the comDuplicate.
	 */
	public String getComDuplicate() {
		return comDuplicate;
	}

	/**
	 * @param comDuplicate
	 *            The comDuplicate to set.
	 */
	public void setComDuplicate(String comDuplicate) {
		this.comDuplicate = comDuplicate;
	}

	/**
	 * @return Returns the comPayDtatusId.
	 */
	public String getComPayStatusId() {
		return comPayStatusId;
	}

	/**
	 * @param comPayDtatusId
	 *            The comPayDtatusId to set.
	 */
	public void setComPayStatusId(String comPayDtatusId) {
		this.comPayStatusId = comPayDtatusId;
	}

	/**
	 * @return Returns the comSettleAmount.
	 */
	public String getComSettleAmount() {
		return comSettleAmount;
	}

	/**
	 * @param comSettleAmount
	 *            The comSettleAmount to set.
	 */
	public void setComSettleAmount(String comSettleAmount) {
		this.comSettleAmount = comSettleAmount;
	}

	/**
	 * @return Returns the comUpgrade.
	 */
	public String getComUpgrade() {
		return comUpgrade;
	}

	/**
	 * @param comUpgrade
	 *            The comUpgrade to set.
	 */
	public void setComUpgrade(String comUpgrade) {
		this.comUpgrade = comUpgrade;
	}

	/**
	 * @return Returns the comWasNotification.
	 */
	public String getComWasNotification() {
		return comWasNotification;
	}

	/**
	 * @param comWasNotification
	 *            The comWasNotification to set.
	 */
	public void setComWasNotification(String comWasNotification) {
		this.comWasNotification = comWasNotification;
	}

	/**
	 * @return Returns the courtDocketNumber.
	 */
	public String getCourtDocketNumber() {
		return courtDocketNumber;
	}

	/**
	 * @param courtDocketNumber
	 *            The courtDocketNumber to set.
	 */
	public void setCourtDocketNumber(String courtDocketNumber) {
		this.courtDocketNumber = courtDocketNumber;
	}

	public String getCourtTypeName() {
		return courtTypeName;
	}

	public void setCourtTypeName(String courtTypeName) {
		this.courtTypeName = courtTypeName;
	}

	/**
	 * @return Returns the plainBirhDate.
	 */
	public String getPlainBirhDate() {
		return plainBirhDate;
	}

	/**
	 * @param plainBirhDate
	 *            The plainBirhDate to set.
	 */
	public void setPlainBirhDate(String plainBirhDate) {
		this.plainBirhDate = plainBirhDate;
	}

	/**
	 * @return Returns the plainCity.
	 */
	public String getPlainCity() {
		return plainCity;
	}

	/**
	 * @param plainCity
	 *            The plainCity to set.
	 */
	public void setPlainCity(String plainCity) {
		this.plainCity = plainCity;
	}

	/**
	 * @return Returns the plainDeathDate.
	 */
	public String getPlainDeathDate() {
		return plainDeathDate;
	}

	/**
	 * @param plainDeathDate
	 *            The plainDeathDate to set.
	 */
	public void setPlainDeathDate(String plainDeathDate) {
		this.plainDeathDate = plainDeathDate;
	}

	/**
	 * @return Returns the plainFullName.
	 */
	public String getPlainFullName() {
		return plainFullName;
	}

	/**
	 * @param plainFullName
	 *            The plainFullName to set.
	 */
	public void setPlainFullName(String plainFullName) {
		this.plainFullName = plainFullName;
	}

	/**
	 * @return Returns the plainLastName.
	 */
	public String getPlainLastName() {
		return plainLastName;
	}

	/**
	 * @param plainLastName
	 *            The plainLastName to set.
	 */
	public void setPlainLastName(String plainLastName) {
		this.plainLastName = plainLastName;
	}

	/**
	 * @return Returns the plainSsn.
	 */
	public String getPlainSsn() {
		return plainSsn;
	}

	/**
	 * @param plainSsn
	 *            The plainSsn to set.
	 */
	public void setPlainSsn(String plainSsn) {
		this.plainSsn = plainSsn;
	}

	public String getPlainStateName() {
		return plainStateName;
	}

	public void setPlainStateName(String plainStateName) {
		this.plainStateName = plainStateName;
	}

	public void setPlaintiffId(String plaintiffId) {
		this.plaintiffId = plaintiffId;
	}

	/**
	 * @return Returns the comSettleDate.
	 */
	public String getComSettleDate() {
		return comSettleDate;
	}

	/**
	 * @param comSettleDate
	 *            The comSettleDate to set.
	 */
	public void setComSettleDate(String comSettleDate) {
		this.comSettleDate = comSettleDate;
	}

	/**
	 * @return Returns the ageAtServeDate.
	 */
	public String getAgeAtServeDate() {
		return ageAtServeDate;
	}

	/**
	 * @param ageAtServeDate
	 *            The ageAtServeDate to set.
	 */
	public void setAgeAtServeDate(String ageAtServeDate) {
		this.ageAtServeDate = ageAtServeDate;
	}

	/**
	 * @return Returns the caseOiDocketNum.
	 */
	public String getCaseOiDocketNum() {
		return caseOiDocketNum;
	}

	public String getCaseOIDocket() {
		return this.getCaseOiDocketNum()
				+ (this.getCaseOiDocketSequence() != null ? ("-" + this.getCaseOiDocketSequence()) : "");
	}

	/**
	 * @param caseOiDocketNum
	 *            The caseOiDocketNum to set.
	 */
	public void setCaseOiDocketNum(String caseOiDocketNum) {
		this.caseOiDocketNum = caseOiDocketNum;
	}

	/**
	 * @return Returns the courtfiledDate.
	 */
	public String getCourtfiledDate() {
		return courtfiledDate;
	}

	/**
	 * @param courtfiledDate
	 *            The courtfiledDate to set.
	 */
	public void setCourtfiledDate(String courtfiledDate) {
		this.courtfiledDate = courtfiledDate;
	}

	/**
	 * @return Returns the courtjurisdiction.
	 */
	public String getCourtjurisdiction() {
		return courtjurisdiction;
	}

	/**
	 * @param courtjurisdiction
	 *            The courtjurisdiction to set.
	 */
	public void setCourtjurisdiction(String courtjurisdiction) {
		this.courtjurisdiction = courtjurisdiction;
	}

	/**
	 * @return Returns the courtservedDate.
	 */
	public String getCourtservedDate() {
		return courtservedDate;
	}

	/**
	 * @param courtservedDate
	 *            The courtservedDate to set.
	 */
	public void setCourtservedDate(String courtservedDate) {
		this.courtservedDate = courtservedDate;
	}

	/**
	 * @param caseCauseId
	 *            The caseCauseId to set.
	 */
	public void setCaseCauseId(String caseCauseId) {
		this.caseCauseId = caseCauseId;
	}

	public String getCaseCauseName() {
		return caseCauseName;
	}

	public void setCaseCauseName(String caseCauseName) {
		this.caseCauseName = caseCauseName;
	}

	/**
	 * @param caseId
	 *            The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * @param caseOiDocketSequence
	 *            The caseOiDocketSequence to set.
	 */
	public void setCaseOiDocketSequence(String caseOiDocketSequence) {
		this.caseOiDocketSequence = caseOiDocketSequence;
	}

	/**
	 * @return Returns the subOccpDescription.
	 */
	public String getSubOccpDescription() {
		return subOccpDescription;
	}

	/**
	 * @param subOccpDescription
	 *            The subOccpDescription to set.
	 */
	public void setSubOccpDescription(String subOccpDescription) {
		this.subOccpDescription = subOccpDescription;
	}

	/**
	 * @return Returns the isDataComplete.
	 */
	public int getIsDataComplete() {
		return isDataComplete;
	}

	/**
	 * @param isDataComplete
	 *            The isDataComplete to set.
	 */
	public void setIsDataComplete(int isDataComplete) {
		this.isDataComplete = isDataComplete;
	}

	public HandleCaseAs getHandleCase() {
		return handleCase;
	}

	public void setHandleCase(HandleCaseAs handleCase) {
		this.handleCase = handleCase;
	}

	/**
	 * @return ce
	 */
	public int getCe() {
		return ce;
	}

	/**
	 * @param ce
	 *            set the ce
	 */
	public void setCe(int ce) {
		this.ce = ce;
	}

	public int getFp() {
		return fp;
	}

	public void setFp(int fp) {
		this.fp = fp;
	}

	public String getMedicareReport() {
		return medicareReport;
	}

	public void setMedicareReport(String medicareReport) {
		this.medicareReport = medicareReport;
	}

	public int getForeignExposure() {
		return foreignExposure;
	}

	public void setForeignExposure(int foreignExposure) {
		this.foreignExposure = foreignExposure;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public SearchData getSearchData() {
		return searchData;
	}

	public void setSearchData(SearchData searchData) {
		this.searchData = searchData;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public int getSecondExposure() {
		return secondExposure;
	}

	public void setSecondExposure(int secondExposure) {
		this.secondExposure = secondExposure;
	}

}
