package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {

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
    private String projectTypeId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
