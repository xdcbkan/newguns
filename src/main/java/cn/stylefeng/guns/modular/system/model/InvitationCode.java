package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户注册邀请码表
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-09
 */
@TableName("invitation_code")
public class InvitationCode extends Model<InvitationCode> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 邀请码
     */
    @TableField("invitation_code")
    private String invitationCode;
    /**
     * 注册次数
     */
    private Integer amount;
    /**
     * 创建人id
     */
    @TableField("create_user_id")
    private Integer createUserId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "InvitationCode{" +
        ", id=" + id +
        ", invitationCode=" + invitationCode +
        ", amount=" + amount +
        ", createUserId=" + createUserId +
        "}";
    }
}
