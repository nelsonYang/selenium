/**
 * Title : SearchData.java Description :Search Data Bean
 * 
 * @author markli
 * @modify joeyzhang
 * @Version 1.1
 */

package com.freeborders.base.entity.basecase; 

public class SearchData
/**
 * this class may still be used by other modules.
 */
{
	private String type;
	private String oiDocketNum;
	private String num;
	private String seq;
	private String lastName;
	private String fullName;
	private String ssn;
	private String pa;
	private String state;
	private String caseType;
	private String caseStatus;
	private String disposition;
	private String disease;
	private String userState;
	private String searchCondition;
	private String searchValue;
	private boolean includeSoundAlike;
	private String displayMultipleRow = "1";// do not need display mutiple rows
	private String isSearchList = "0";
	private String checkNo;
	private String ce;

	public SearchData()
	{
	}

	/**
	 * @return Returns the num.
	 */
	public String getNum()
	{
		return num;
	}

	/**
	 * @param num
	 *        The num to set.
	 */
	public void setNum(String num)
	{
		this.num = num;
	}

	/**
	 * @return Returns the seq.
	 */
	public String getSeq()
	{
		return seq;
	}

	/**
	 * @param seq
	 *        The seq to set.
	 */
	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	/**
	 * @return Returns the userState.
	 */
	public String getUserState()
	{
		return userState;
	}

	/**
	 * @param userState
	 *        The userState to set.
	 */
	public void setUserState(String userState)
	{
		this.userState = userState;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *        The type to set.
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return Returns the caseStatus.
	 */
	public String getCaseStatus()
	{
		return caseStatus;
	}

	/**
	 * @param caseStatus
	 *        The caseStatus to set.
	 */
	public void setCaseStatus(String caseStatus)
	{
		this.caseStatus = caseStatus;
	}

	/**
	 * @return Returns the caseType.
	 */
	public String getCaseType()
	{
		return caseType;
	}

	/**
	 * @param caseType
	 *        The caseType to set.
	 */
	public void setCaseType(String caseType)
	{
		this.caseType = caseType;
	}

	/**
	 * @return Returns the disease.
	 */
	public String getDisease()
	{
		return disease;
	}

	/**
	 * @param disease
	 *        The disease to set.
	 */
	public void setDisease(String disease)
	{
		this.disease = disease;
	}

	/**
	 * @return Returns the disposition.
	 */
	public String getDisposition()
	{
		return disposition;
	}

	/**
	 * @param disposition
	 *        The disposition to set.
	 */
	public void setDisposition(String disposition)
	{
		this.disposition = disposition;
	}

	/**
	 * @return Returns the fullName.
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * @param fullName
	 *        The fullName to set.
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *        The lastName to set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return Returns the oiDocketNum.
	 */
	public String getOiDocketNum()
	{
		return oiDocketNum;
	}

	/**
	 * @param oiDocketNum
	 *        The oiDocketNum to set.
	 */
	public void setOiDocketNum(String oiDocketNum)
	{
		this.oiDocketNum = oiDocketNum;
	}

	/**
	 * @return Returns the pa.
	 */
	public String getPa()
	{
		return pa;
	}

	/**
	 * @param pa
	 *        The pa to set.
	 */
	public void setPa(String pa)
	{
		this.pa = pa;
	}

	/**
	 * @return Returns the ssn.
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param ssn
	 *        The ssn to set.
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state
	 *        The state to set.
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	
	public boolean isIncludeSoundAlike() {
		return includeSoundAlike;
	}

	public void setIncludeSoundAlike(boolean includeSoundAlike) {
		this.includeSoundAlike = includeSoundAlike;
	}

	/**
	 * @return Returns the searchCondition.
	 */
	public String getSearchCondition()
	{
		return searchCondition;
	}

	/**
	 * @param searchCondition
	 *        The searchCondition to set.
	 */
	public void setSearchCondition(String searchCondition)
	{
		this.searchCondition = searchCondition;
	}

	/**
	 * @return Returns the searchValue.
	 */
	public String getSearchValue()
	{
		return searchValue;
	}

	/**
	 * @param searchValue
	 *        The searchValue to set.
	 */
	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	/**
	 * @return Returns the isSearchList.
	 */
	public String getIsSearchList()
	{
		return isSearchList;
	}

	/**
	 * @param isSearchList
	 *        The isSearchList to set.
	 */
	public void setIsSearchList(String isSearchList)
	{
		this.isSearchList = isSearchList;
	}

	/**
	 * @return Returns the displayMultipleRow.
	 */
	public String getDisplayMultipleRow()
	{
		return displayMultipleRow;
	}

	/**
	 * @param displayMultipleRow
	 *        The displayMultipleRow to set.
	 */
	public void setDisplayMultipleRow(String displayMultipleRow)
	{
		this.displayMultipleRow = displayMultipleRow;
	}

	/**
	 * @return Returns the checkNo.
	 */
	public String getCheckNo()
	{
		return checkNo;
	}

	/**
	 * @param checkNo
	 *        The checkNo to set.
	 */
	public void setCheckNo(String checkNo)
	{
		this.checkNo = checkNo;
	}

	public String getCe()
	{
		return ce;
	}

	public void setCe(String ce)
	{
		this.ce = ce;
	}
	
}
