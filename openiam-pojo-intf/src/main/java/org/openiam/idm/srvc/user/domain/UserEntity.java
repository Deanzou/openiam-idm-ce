package org.openiam.idm.srvc.user.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.openiam.dozer.DozerDTOCorrespondence;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.continfo.domain.AddressEntity;
import org.openiam.idm.srvc.continfo.domain.EmailAddressEntity;
import org.openiam.idm.srvc.continfo.domain.PhoneEntity;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserAttributeMapAdapter;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.UserNoteSetAdapter;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

@Entity
@FilterDef(name = "parentTypeFilter", parameters = @ParamDef(name = "parentFilter", type = "string"))
@Table(name = "USERS")
@DozerDTOCorrespondence(User.class)
public class UserEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "USER_ID", length = 32, nullable = false)
	private String userId;

	@Column(name = "BIRTHDATE", length = 19)
	private Date birthdate;

	@Column(name = "COMPANY_ID", length = 32)
	private String companyId;

	@Column(name = "COMPANY_OWNER_ID", length = 32)
	private String companyOwnerId;

	@Column(name = "CREATE_DATE", length = 19)
	private Date createDate;

	@Column(name = "CREATED_BY", length = 32)
	private String createdBy;

	@Column(name = "DEPT_CD", length = 50)
	private String deptCd;

	@Column(name = "DEPT_NAME", length = 100)
	private String deptName;

	@Column(name = "EMPLOYEE_ID", length = 32)
	private String employeeId;

	@Column(name = "EMPLOYEE_TYPE", length = 20)
	private String employeeType;

	@Column(name = "FIRST_NAME", length = 50)
	private String firstName;

	@Column(name = "JOB_CODE", length = 50)
	private String jobCode;

	@Column(name = "LAST_NAME", length = 50)
	private String lastName;

	@Column(name = "LAST_UPDATE", length = 19)
	private Date lastUpdate;

	@Column(name = "LAST_UPDATED_BY", length = 32)
	private String lastUpdatedBy;

	@Column(name = "LOCATION_CD", length = 50)
	private String locationCd;

	@Column(name = "LOCATION_NAME", length = 100)
	private String locationName;

	@Column(name = "MANAGER_ID", length = 32)
	private String managerId;

	@Column(name = "TYPE_ID", length = 20)
	private String metadataTypeId;

	@Column(name = "CLASSIFICATION", length = 20)
	private String classification;

	@Column(name = "MIDDLE_INIT", length = 50)
	private String middleInit;

	@Column(name = "PREFIX", length = 4)
	private String prefix;

	@Column(name = "SEX", length = 1)
	private String sex;

	@Column(name = "STATUS", length = 40)
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;

	@Column(name = "SECONDARY_STATUS", length = 40)
	@Enumerated(EnumType.STRING)
	private UserStatusEnum secondaryStatus;

	@Column(name = "SUFFIX", length = 20)
	private String suffix;

	@Column(name = "TITLE", length = 30)
	private String title;

	@Column(name = "USER_TYPE_IND", length = 20)
	private String userTypeInd;

	@Column(name = "DIVISION", length = 50)
	private String division;

	@Column(name = "MAIL_CODE", length = 10)
	private String mailCode;

	@Column(name = "COST_CENTER", length = 20)
	private String costCenter;

	@Column(name = "START_DATE", length = 10)
	private Date startDate;

	@Column(name = "LAST_DATE", length = 10)
	private Date lastDate;

	@Column(name = "NICKNAME", length = 40)
	private String nickname;

	@Column(name = "MAIDEN_NAME", length = 40)
	private String maidenName;

	@Column(name = "PASSWORD_THEME", length = 20)
	private String passwordTheme;

	@Column(name = "COUNTRY", length = 30)
	private String country;

	@Column(name = "BLDG_NUM", length = 10)
	private String bldgNum;

	@Column(name = "STREET_DIRECTION", length = 20)
	private String streetDirection;

	@Column(name = "SUITE", length = 20)
	private String suite;

	@Column(name = "ADDRESS1", length = 512)
	private String address1;

	@Column(name = "ADDRESS2", length = 512)
	private String address2;

	@Column(name = "ADDRESS3", length = 45)
	private String address3;

	@Column(name = "ADDRESS4", length = 45)
	private String address4;

	@Column(name = "ADDRESS5", length = 45)
	private String address5;

	@Column(name = "ADDRESS6", length = 45)
	private String address6;

	@Column(name = "ADDRESS7", length = 45)
	private String address7;

	@Column(name = "CITY", length = 30)
	private String city;

	@Column(name = "STATE", length = 15)
	private String state;

	@Column(name = "POSTAL_CD", length = 10)
	private String postalCd;

	@Column(name = "EMAIL_ADDRESS", length = 320)
	private String email;

	@Column(name = "AREA_CD", length = 10)
	private String areaCd;

	@Column(name = "COUNTRY_CD", length = 10)
	private String countryCd;

	@Column(name = "PHONE_NBR", length = 50)
	private String phoneNbr;

	@Column(name = "PHONE_EXT", length = 20)
	private String phoneExt;

	@Column(name = "SHOW_IN_SEARCH", nullable = true)
	private Integer showInSearch = 0;

	@Column(name = "DEL_ADMIN", nullable = true)
	private Integer delAdmin = 0;

	@Column(name = "ALTERNATE_ID", length = 32)
	private String alternateContactId;

	@Column(name = "USER_OWNER_ID")
	private String userOwnerId;

	@Column(name = "DATE_PASSWORD_CHANGED", length = 10)
	private Date datePasswordChanged;

	@Column(name = "DATE_CHALLENGE_RESP_CHANGED", length = 10)
	private Date dateChallengeRespChanged;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<UserNoteEntity> userNotes = new HashSet<UserNoteEntity>(0);

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	@MapKey(name = "name")
	private Map<String, UserAttributeEntity> userAttributes = new HashMap<String, UserAttributeEntity>(
			0);

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	@Filter(name = "parentTypeFilter", condition = ":parentFilter = PARENT_TYPE")
	private Set<AddressEntity> addresses = new HashSet<AddressEntity>(0);

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	@Filter(name = "parentTypeFilter", condition = ":parentFilter = PARENT_TYPE")
	private Set<PhoneEntity> phones = new HashSet<PhoneEntity>(0);

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	@Filter(name = "parentTypeFilter", condition = ":parentFilter = PARENT_TYPE")
	private Set<EmailAddressEntity> emailAddresses = new HashSet<EmailAddressEntity>(
			0);

	public UserEntity() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyOwnerId() {
		return companyOwnerId;
	}

	public void setCompanyOwnerId(String companyOwnerId) {
		this.companyOwnerId = companyOwnerId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLocationCd() {
		return locationCd;
	}

	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getMiddleInit() {
		return middleInit;
	}

	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public UserStatusEnum getSecondaryStatus() {
		return secondaryStatus;
	}

	public void setSecondaryStatus(UserStatusEnum secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserTypeInd() {
		return userTypeInd;
	}

	public void setUserTypeInd(String userTypeInd) {
		this.userTypeInd = userTypeInd;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getPasswordTheme() {
		return passwordTheme;
	}

	public void setPasswordTheme(String passwordTheme) {
		this.passwordTheme = passwordTheme;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBldgNum() {
		return bldgNum;
	}

	public void setBldgNum(String bldgNum) {
		this.bldgNum = bldgNum;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getAddress6() {
		return address6;
	}

	public void setAddress6(String address6) {
		this.address6 = address6;
	}

	public String getAddress7() {
		return address7;
	}

	public void setAddress7(String address7) {
		this.address7 = address7;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCd() {
		return postalCd;
	}

	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getPhoneExt() {
		return phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public Integer getShowInSearch() {
		return showInSearch;
	}

	public void setShowInSearch(Integer showInSearch) {
		this.showInSearch = showInSearch;
	}

	public Integer getDelAdmin() {
		return delAdmin;
	}

	public void setDelAdmin(Integer delAdmin) {
		this.delAdmin = delAdmin;
	}

	public String getAlternateContactId() {
		return alternateContactId;
	}

	public void setAlternateContactId(String alternateContactId) {
		this.alternateContactId = alternateContactId;
	}

	public String getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(String userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

	public Date getDatePasswordChanged() {
		return datePasswordChanged;
	}

	public void setDatePasswordChanged(Date datePasswordChanged) {
		this.datePasswordChanged = datePasswordChanged;
	}

	public Date getDateChallengeRespChanged() {
		return dateChallengeRespChanged;
	}

	public void setDateChallengeRespChanged(Date dateChallengeRespChanged) {
		this.dateChallengeRespChanged = dateChallengeRespChanged;
	}

	public Set<UserNoteEntity> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Set<UserNoteEntity> userNotes) {
		this.userNotes = userNotes;
	}

	public Map<String, UserAttributeEntity> getUserAttributes() {
		return userAttributes;
	}

	public void setUserAttributes(
			Map<String, UserAttributeEntity> userAttributes) {
		this.userAttributes = userAttributes;
	}

	public Set<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public Set<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneEntity> phones) {
		this.phones = phones;
	}

	public Set<EmailAddressEntity> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<EmailAddressEntity> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserEntity that = (UserEntity) o;

		if (address1 != null ? !address1.equals(that.address1)
				: that.address1 != null)
			return false;
		if (alternateContactId != null ? !alternateContactId
				.equals(that.alternateContactId)
				: that.alternateContactId != null)
			return false;
		if (areaCd != null ? !areaCd.equals(that.areaCd) : that.areaCd != null)
			return false;
		if (city != null ? !city.equals(that.city) : that.city != null)
			return false;
		if (companyId != null ? !companyId.equals(that.companyId)
				: that.companyId != null)
			return false;
		if (country != null ? !country.equals(that.country)
				: that.country != null)
			return false;
		if (countryCd != null ? !countryCd.equals(that.countryCd)
				: that.countryCd != null)
			return false;
		if (deptCd != null ? !deptCd.equals(that.deptCd) : that.deptCd != null)
			return false;
		if (deptName != null ? !deptName.equals(that.deptName)
				: that.deptName != null)
			return false;
		if (division != null ? !division.equals(that.division)
				: that.division != null)
			return false;
		if (email != null ? !email.equals(that.email) : that.email != null)
			return false;
		if (employeeId != null ? !employeeId.equals(that.employeeId)
				: that.employeeId != null)
			return false;
		if (firstName != null ? !firstName.equals(that.firstName)
				: that.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(that.lastName)
				: that.lastName != null)
			return false;
		if (locationCd != null ? !locationCd.equals(that.locationCd)
				: that.locationCd != null)
			return false;
		if (locationName != null ? !locationName.equals(that.locationName)
				: that.locationName != null)
			return false;
		if (maidenName != null ? !maidenName.equals(that.maidenName)
				: that.maidenName != null)
			return false;
		if (mailCode != null ? !mailCode.equals(that.mailCode)
				: that.mailCode != null)
			return false;
		if (managerId != null ? !managerId.equals(that.managerId)
				: that.managerId != null)
			return false;
		if (middleInit != null ? !middleInit.equals(that.middleInit)
				: that.middleInit != null)
			return false;
		if (nickname != null ? !nickname.equals(that.nickname)
				: that.nickname != null)
			return false;
		if (phoneExt != null ? !phoneExt.equals(that.phoneExt)
				: that.phoneExt != null)
			return false;
		if (phoneNbr != null ? !phoneNbr.equals(that.phoneNbr)
				: that.phoneNbr != null)
			return false;
		if (postalCd != null ? !postalCd.equals(that.postalCd)
				: that.postalCd != null)
			return false;
		if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null)
			return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null)
			return false;
		if (state != null ? !state.equals(that.state) : that.state != null)
			return false;
		if (streetDirection != null ? !streetDirection
				.equals(that.streetDirection) : that.streetDirection != null)
			return false;
		if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null)
			return false;
		if (suite != null ? !suite.equals(that.suite) : that.suite != null)
			return false;
		if (title != null ? !title.equals(that.title) : that.title != null)
			return false;
		if (userOwnerId != null ? !userOwnerId.equals(that.userOwnerId)
				: that.userOwnerId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = companyId != null ? companyId.hashCode() : 0;
		result = 31 * result + (deptCd != null ? deptCd.hashCode() : 0);
		result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
		result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (locationCd != null ? locationCd.hashCode() : 0);
		result = 31 * result
				+ (locationName != null ? locationName.hashCode() : 0);
		result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
		result = 31 * result + (middleInit != null ? middleInit.hashCode() : 0);
		result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (division != null ? division.hashCode() : 0);
		result = 31 * result + (mailCode != null ? mailCode.hashCode() : 0);
		result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
		result = 31 * result + (maidenName != null ? maidenName.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result
				+ (streetDirection != null ? streetDirection.hashCode() : 0);
		result = 31 * result + (suite != null ? suite.hashCode() : 0);
		result = 31 * result + (address1 != null ? address1.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (postalCd != null ? postalCd.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (areaCd != null ? areaCd.hashCode() : 0);
		result = 31 * result + (countryCd != null ? countryCd.hashCode() : 0);
		result = 31 * result + (phoneNbr != null ? phoneNbr.hashCode() : 0);
		result = 31 * result + (phoneExt != null ? phoneExt.hashCode() : 0);
		result = 31
				* result
				+ (alternateContactId != null ? alternateContactId.hashCode()
						: 0);
		result = 31 * result
				+ (userOwnerId != null ? userOwnerId.hashCode() : 0);
		return result;
	}
}
