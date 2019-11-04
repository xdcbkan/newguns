package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 交易流水表
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-14
 */
@TableName("trans_water")
public class TransWater extends Model<TransWater> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 状态(消费、充值、文编结算、文编提现、提现失败退款)
     */
    @TableField("trans_status")
    private Integer transStatus;
    /**
     * 操作人
     */
    @TableField("create_user")
    private Integer createUser;
    /**
     * 交易类型(现金、积分)
     */
    @TableField("trans_type")
    private Integer transType;
    /**
     * 积分余额
     */
    @TableField("points_balance")
    private Integer pointsBalance;
    /**
     * 文章id
     */
    @TableField("article_id")
    private Integer articleId;
    /**
     * 交易流水记录的用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 交易时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getPointsBalance() {
        return pointsBalance;
    }

    public void setPointsBalance(Integer pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TransWater{" +
                "id=" + id +
                ", amount=" + amount +
                ", transStatus=" + transStatus +
                ", createUser=" + createUser +
                ", transType=" + transType +
                ", pointsBalance=" + pointsBalance +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
