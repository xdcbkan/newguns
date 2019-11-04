package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 提现申请表
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-13
 */
@TableName("withdraw_application")
public class WithdrawApplication extends Model<WithdrawApplication> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 支付宝账号
     */
    @TableField("ali_pay")
    private String aliPay;
    /**
     * 提现金额
     */
    private Integer amount;
    /**
     * 提现状态   1为提现中、2为提现完成、3为失败
     */
    @TableField("withdraw_status")
    private Integer withdrawStatus;
    /**
     * 提现反馈（失败原因）
     */
    @TableField("error_message")
    private String errorMessage;
    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAliPay() {
        return aliPay;
    }

    public void setAliPay(String aliPay) {
        this.aliPay = aliPay;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WithdrawApplication{" +
                "id=" + id +
                ", userId=" + userId +
                ", aliPay=" + aliPay +
                ", amount=" + amount +
                ", withdrawStatus=" + withdrawStatus +
                ", errorMessage='" + errorMessage + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
