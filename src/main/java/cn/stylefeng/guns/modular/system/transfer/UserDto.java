/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.transfer;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户传输bean
 *
 * @author stylefeng
 * @Date 2017/5/5 22:40
 */
public class UserDto {

    private Integer id;
    private String avatar;
    private String account;
    private String password;
    private String salt;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private String idcard;
    private String alipay;
    private Integer residuereceivenum;
    private Integer receivenum;
    private Integer price;
    private Integer userid;
    private Integer usernumber;
    private String roleid;
    private String articletypeid;
    private Integer passnum;
    private Integer deptid;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private Integer version;
    private String officeAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;
    private String wechatid;
    private String qqid;
    private String sinaid;
    private Integer employeeType;
    private Integer employeeStatus;
    private BigDecimal postRank;
    private String nation;
    private String idAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date certificateValidityPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstTimeWorke;
    private Integer maritalStatus;
    private Integer registrationType;
    private String currentAddress;
    private Integer politicalOutlook;
    private String socialSecurityAccount;
    private String providentFundAccount;
    private Integer education;
    private String university;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduationTime;
    private String major;
    private String contractCompany;
    private String contractType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstContractOrigin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstContractExpire;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date currentContractOrigin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date currentContractExpire;
    private Integer contractPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer renewalTimes;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private Integer emergencyContactSex;
    private String emergencyContactPhone;
    private Integer withoutChildren;
    private String childrenName;
    private Integer childrenSex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date childrenBirthday;
    private String idCardPositive;
    private String idCardBack;
    private String academicCertificate;
    private String diploma;
    private String leavingCertificate;
    private String photo;
    private String projectTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public Integer getResiduereceivenum() {
        return residuereceivenum;
    }

    public void setResiduereceivenum(Integer residuereceivenum) {
        this.residuereceivenum = residuereceivenum;
    }

    public Integer getReceivenum() {
        return receivenum;
    }

    public void setReceivenum(Integer receivenum) {
        this.receivenum = receivenum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(Integer usernumber) {
        this.usernumber = usernumber;
    }

    public String getArticletypeid() {
        return articletypeid;
    }

    public void setArticletypeid(String articletypeid) {
        this.articletypeid = articletypeid;
    }

    public Integer getPassnum() {
        return passnum;
    }

    public void setPassnum(Integer passnum) {
        this.passnum = passnum;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getSinaid() {
        return sinaid;
    }

    public void setSinaid(String sinaid) {
        this.sinaid = sinaid;
    }

    public Integer getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Integer employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public BigDecimal getPostRank() {
        return postRank;
    }

    public void setPostRank(BigDecimal postRank) {
        this.postRank = postRank;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public Date getCertificateValidityPeriod() {
        return certificateValidityPeriod;
    }

    public void setCertificateValidityPeriod(Date certificateValidityPeriod) {
        this.certificateValidityPeriod = certificateValidityPeriod;
    }

    public Date getFirstTimeWorke() {
        return firstTimeWorke;
    }

    public void setFirstTimeWorke(Date firstTimeWorke) {
        this.firstTimeWorke = firstTimeWorke;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Integer getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(Integer politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getSocialSecurityAccount() {
        return socialSecurityAccount;
    }

    public void setSocialSecurityAccount(String socialSecurityAccount) {
        this.socialSecurityAccount = socialSecurityAccount;
    }

    public String getProvidentFundAccount() {
        return providentFundAccount;
    }

    public void setProvidentFundAccount(String providentFundAccount) {
        this.providentFundAccount = providentFundAccount;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getContractCompany() {
        return contractCompany;
    }

    public void setContractCompany(String contractCompany) {
        this.contractCompany = contractCompany;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getFirstContractOrigin() {
        return firstContractOrigin;
    }

    public void setFirstContractOrigin(Date firstContractOrigin) {
        this.firstContractOrigin = firstContractOrigin;
    }

    public Date getFirstContractExpire() {
        return firstContractExpire;
    }

    public void setFirstContractExpire(Date firstContractExpire) {
        this.firstContractExpire = firstContractExpire;
    }

    public Date getCurrentContractOrigin() {
        return currentContractOrigin;
    }

    public void setCurrentContractOrigin(Date currentContractOrigin) {
        this.currentContractOrigin = currentContractOrigin;
    }

    public Date getCurrentContractExpire() {
        return currentContractExpire;
    }

    public void setCurrentContractExpire(Date currentContractExpire) {
        this.currentContractExpire = currentContractExpire;
    }

    public Integer getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public Integer getRenewalTimes() {
        return renewalTimes;
    }

    public void setRenewalTimes(Integer renewalTimes) {
        this.renewalTimes = renewalTimes;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public Integer getEmergencyContactSex() {
        return emergencyContactSex;
    }

    public void setEmergencyContactSex(Integer emergencyContactSex) {
        this.emergencyContactSex = emergencyContactSex;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Integer getWithoutChildren() {
        return withoutChildren;
    }

    public void setWithoutChildren(Integer withoutChildren) {
        this.withoutChildren = withoutChildren;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public Integer getChildrenSex() {
        return childrenSex;
    }

    public void setChildrenSex(Integer childrenSex) {
        this.childrenSex = childrenSex;
    }

    public Date getChildrenBirthday() {
        return childrenBirthday;
    }

    public void setChildrenBirthday(Date childrenBirthday) {
        this.childrenBirthday = childrenBirthday;
    }

    public String getIdCardPositive() {
        return idCardPositive;
    }

    public void setIdCardPositive(String idCardPositive) {
        this.idCardPositive = idCardPositive;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getAcademicCertificate() {
        return academicCertificate;
    }

    public void setAcademicCertificate(String academicCertificate) {
        this.academicCertificate = academicCertificate;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getLeavingCertificate() {
        return leavingCertificate;
    }

    public void setLeavingCertificate(String leavingCertificate) {
        this.leavingCertificate = leavingCertificate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }
}
