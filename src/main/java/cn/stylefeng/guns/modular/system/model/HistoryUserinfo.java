package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-23
 */
@TableName("history_userinfo")
public class HistoryUserinfo extends Model<HistoryUserinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 积分余额
     */
    private Integer balance;
    /**
     * 名字
     */
    private String name;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 支付宝账号
     */
    private String alipay;
    /**
     * 剩余领取次数
     */
    private Integer residuereceivenum;
    /**
     * 每日领取上限
     */
    private Integer receivenum;
    /**
     * 文章单价
     */
    private Integer price;
    /**
     * 介绍人id
     */
    private Integer userid;
    /**
     * 用户编号
     */
    private Integer usernumber;
    /**
     * 角色id
     */
    private String roleid;
    /**
     * 文章分类id
     */
    private String articletypeid;
    /**
     * 文章总通过数
     */
    private Integer passnum;
    /**
     * 部门id
     */
    private Integer deptid;
    /**
     * 状态(1：启用  2：冻结  3：删除  4：待激活）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 保留字段
     */
    private Integer version;
    /**
     * 办公地点
     */
    @TableField("office_address")
    private String officeAddress;
    /**
     * 入职时间
     */
    @TableField("entry_time")
    private Date entryTime;
    /**
     * 微信账户
     */
    private String wechatid;
    /**
     * QQ账户
     */
    private String qqid;
    /**
     * 微博ID
     */
    private String sinaid;
    /**
     * 员工类型(合作/兼职)
     */
    @TableField("employee_type")
    private Integer employeeType;
    /**
     * 员工状态 1.合作中 2.暂停 3.废除/中止
     */
    @TableField("employee_status")
    private Integer employeeStatus;
    /**
     * 岗位职级
     */
    @TableField("post_rank")
    private BigDecimal postRank;
    /**
     * 民族
     */
    private String nation;
    /**
     * 身份证地址
     */
    @TableField("id_address")
    private String idAddress;
    /**
     * 证件有效期
     */
    @TableField("certificate_validity_period")
    private Date certificateValidityPeriod;
    /**
     * 首次参加工作时间
     */
    @TableField("first_time_worke")
    private Date firstTimeWorke;
    /**
     * 婚姻状况
     */
    @TableField("marital_status")
    private Integer maritalStatus;
    /**
     * 户籍类型(1.城镇 2.农村)
     */
    @TableField("registration_type")
    private Integer registrationType;
    /**
     * 现在住址
     */
    @TableField("current_address")
    private String currentAddress;
    /**
     * 政治面貌(1.团员 2.党员)
     */
    @TableField("political_outlook")
    private Integer politicalOutlook;
    /**
     * 个人社保账号
     */
    @TableField("social_security_account")
    private String socialSecurityAccount;
    /**
     * 个人公积金账号
     */
    @TableField("provident_fund_account")
    private String providentFundAccount;
    /**
     * 学历
     */
    private Integer education;
    /**
     * 毕业院校
     */
    private String university;
    /**
     * 毕业时间
     */
    @TableField("graduation_time")
    private Date graduationTime;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 合同公司
     */
    @TableField("contract_company")
    private String contractCompany;
    /**
     * 合同类型
     */
    @TableField("contract_type")
    private String contractType;
    /**
     * 首次合同起始日
     */
    @TableField("first_contract_origin")
    private Date firstContractOrigin;
    /**
     * 首次合同到期日
     */
    @TableField("first_contract_expire")
    private Date firstContractExpire;
    /**
     * 现合同起始日
     */
    @TableField("current_contract_origin")
    private Date currentContractOrigin;
    /**
     * 现合同到期日
     */
    @TableField("current_contract_expire")
    private Date currentContractExpire;
    /**
     * 合同期限
     */
    @TableField("contract_period")
    private Integer contractPeriod;
    /**
     * 续签次数
     */
    @TableField("renewal_times")
    private Integer renewalTimes;
    /**
     * 紧急联系人姓名
     */
    @TableField("emergency_contact_name")
    private String emergencyContactName;
    /**
     * 紧急联系人关系
     */
    @TableField("emergency_contact_relationship")
    private String emergencyContactRelationship;
    /**
     * 紧急联系人性别
     */
    @TableField("emergency_contact_sex")
    private Integer emergencyContactSex;
    /**
     * 联系人电话
     */
    @TableField("emergency_contact_phone")
    private String emergencyContactPhone;
    /**
     * 有无子女
     */
    @TableField("without_children")
    private Integer withoutChildren;
    /**
     * 子女姓名
     */
    @TableField("children_name")
    private String childrenName;
    /**
     * 子女性别
     */
    @TableField("children_sex")
    private Integer childrenSex;
    /**
     * 子女出生日期
     */
    @TableField("children_birthday")
    private Date childrenBirthday;
    /**
     * 身份证（人面像）
     */
    @TableField("id_card_positive")
    private String idCardPositive;
    /**
     * 身份证（国徽面）
     */
    @TableField("id_card_back")
    private String idCardBack;
    /**
     * 学历证书
     */
    @TableField("academic_certificate")
    private String academicCertificate;
    /**
     * 学位证书
     */
    private String diploma;
    /**
     * 前公司离职证明
     */
    @TableField("leaving_certificate")
    private String leavingCertificate;
    /**
     * 员工照片
     */
    private String photo;
    /**
     * 项目分类id
     */
    @TableField("project_type_id")
    private Integer projectTypeId;
    /**
     * 用户表id
     */
    @TableField("sys_user_id")
    private Integer sysUserId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
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

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HistoryUserinfo{" +
        ", id=" + id +
        ", avatar=" + avatar +
        ", account=" + account +
        ", password=" + password +
        ", salt=" + salt +
        ", balance=" + balance +
        ", name=" + name +
        ", birthday=" + birthday +
        ", sex=" + sex +
        ", email=" + email +
        ", phone=" + phone +
        ", idcard=" + idcard +
        ", alipay=" + alipay +
        ", residuereceivenum=" + residuereceivenum +
        ", receivenum=" + receivenum +
        ", price=" + price +
        ", userid=" + userid +
        ", usernumber=" + usernumber +
        ", roleid=" + roleid +
        ", articletypeid=" + articletypeid +
        ", passnum=" + passnum +
        ", deptid=" + deptid +
        ", status=" + status +
        ", createtime=" + createtime +
        ", version=" + version +
        ", officeAddress=" + officeAddress +
        ", entryTime=" + entryTime +
        ", wechatid=" + wechatid +
        ", qqid=" + qqid +
        ", sinaid=" + sinaid +
        ", employeeType=" + employeeType +
        ", employeeStatus=" + employeeStatus +
        ", postRank=" + postRank +
        ", nation=" + nation +
        ", idAddress=" + idAddress +
        ", certificateValidityPeriod=" + certificateValidityPeriod +
        ", firstTimeWorke=" + firstTimeWorke +
        ", maritalStatus=" + maritalStatus +
        ", registrationType=" + registrationType +
        ", currentAddress=" + currentAddress +
        ", politicalOutlook=" + politicalOutlook +
        ", socialSecurityAccount=" + socialSecurityAccount +
        ", providentFundAccount=" + providentFundAccount +
        ", education=" + education +
        ", university=" + university +
        ", graduationTime=" + graduationTime +
        ", major=" + major +
        ", contractCompany=" + contractCompany +
        ", contractType=" + contractType +
        ", firstContractOrigin=" + firstContractOrigin +
        ", firstContractExpire=" + firstContractExpire +
        ", currentContractOrigin=" + currentContractOrigin +
        ", currentContractExpire=" + currentContractExpire +
        ", contractPeriod=" + contractPeriod +
        ", renewalTimes=" + renewalTimes +
        ", emergencyContactName=" + emergencyContactName +
        ", emergencyContactRelationship=" + emergencyContactRelationship +
        ", emergencyContactSex=" + emergencyContactSex +
        ", emergencyContactPhone=" + emergencyContactPhone +
        ", withoutChildren=" + withoutChildren +
        ", childrenName=" + childrenName +
        ", childrenSex=" + childrenSex +
        ", childrenBirthday=" + childrenBirthday +
        ", idCardPositive=" + idCardPositive +
        ", idCardBack=" + idCardBack +
        ", academicCertificate=" + academicCertificate +
        ", diploma=" + diploma +
        ", leavingCertificate=" + leavingCertificate +
        ", photo=" + photo +
        ", projectTypeId=" + projectTypeId +
        ", sysUserId=" + sysUserId +
        "}";
    }
}
