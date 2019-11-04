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
 * 文编每日统计结算表
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-15
 */
@TableName("editor_statistics_day")
public class EditorStatisticsDay extends Model<EditorStatisticsDay> {

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
     * 提交数
     */
    private Integer submit;
    /**
     * 审1退回数
     */
    @TableField("examine1_return")
    private Integer examine1Return;
    /**
     * 审2退回数
     */
    @TableField("examine2_return")
    private Integer examine2Return;
    /**
     * 审1修1
     */
    @TableField("examine1_return_edit1")
    private Integer examine1ReturnEdit1;
    /**
     * 审1修1
     */
    @TableField("examine1_return_edit2")
    private Integer examine1ReturnEdit2;
    /**
     * 审2修1
     */
    @TableField("examine2_return_edit1")
    private Integer examine2ReturnEdit1;
    /**
     * 审2修2
     */
    @TableField("examine2_return_edit2")
    private Integer examine2ReturnEdit2;
    /**
     * 作废数
     */
    private Integer abolish;
    /**
     * 通过数(审2通过的数量-作废数)
     */
    private Integer pass;
    /**
     * 结算金额
     */
    @TableField("settlement_amount")
    private Integer settlementAmount;
    /**
     * 统计日期
     */
    @TableField("statistics_time")
    private Date statisticsTime;


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

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getExamine1Return() {
        return examine1Return;
    }

    public void setExamine1Return(Integer examine1Return) {
        this.examine1Return = examine1Return;
    }

    public Integer getExamine2Return() {
        return examine2Return;
    }

    public void setExamine2Return(Integer examine2Return) {
        this.examine2Return = examine2Return;
    }

    public Integer getExamine1ReturnEdit1() {
        return examine1ReturnEdit1;
    }

    public void setExamine1ReturnEdit1(Integer examine1ReturnEdit1) {
        this.examine1ReturnEdit1 = examine1ReturnEdit1;
    }

    public Integer getExamine1ReturnEdit2() {
        return examine1ReturnEdit2;
    }

    public void setExamine1ReturnEdit2(Integer examine1ReturnEdit2) {
        this.examine1ReturnEdit2 = examine1ReturnEdit2;
    }

    public Integer getExamine2ReturnEdit1() {
        return examine2ReturnEdit1;
    }

    public void setExamine2ReturnEdit1(Integer examine2ReturnEdit1) {
        this.examine2ReturnEdit1 = examine2ReturnEdit1;
    }

    public Integer getExamine2ReturnEdit2() {
        return examine2ReturnEdit2;
    }

    public void setExamine2ReturnEdit2(Integer examine2ReturnEdit2) {
        this.examine2ReturnEdit2 = examine2ReturnEdit2;
    }

    public Integer getAbolish() {
        return abolish;
    }

    public void setAbolish(Integer abolish) {
        this.abolish = abolish;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Integer settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Date getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(Date statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EditorStatisticsDay{" +
        ", id=" + id +
        ", userId=" + userId +
        ", submit=" + submit +
        ", examine1Return=" + examine1Return +
        ", examine2Return=" + examine2Return +
        ", examine1ReturnEdit1=" + examine1ReturnEdit1 +
        ", examine1ReturnEdit2=" + examine1ReturnEdit2 +
        ", examine2ReturnEdit1=" + examine2ReturnEdit1 +
        ", examine2ReturnEdit2=" + examine2ReturnEdit2 +
        ", abolish=" + abolish +
        ", pass=" + pass +
        ", settlementAmount=" + settlementAmount +
        ", statisticsTime=" + statisticsTime +
        "}";
    }
}
