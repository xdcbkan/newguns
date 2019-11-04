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
 * 审核1每月工作统计表

 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@TableName("examine1_statistics_month")
public class Examine1StatisticsMonth extends Model<Examine1StatisticsMonth> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 领取数
     */
    private Integer receive;
    /**
     * 通过数
     */
    private Integer pass;
    /**
     * 未通过数
     */
    private Integer unpass;
    /**
     * 审核中的数量
     */
    @TableField("examining_num")
    private Integer examiningNum;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 作废数
     */
    private Integer abolish;
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

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getUnpass() {
        return unpass;
    }

    public void setUnpass(Integer unpass) {
        this.unpass = unpass;
    }

    public Integer getExaminingNum() {
        return examiningNum;
    }

    public void setExaminingNum(Integer examiningNum) {
        this.examiningNum = examiningNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAbolish() {
        return abolish;
    }

    public void setAbolish(Integer abolish) {
        this.abolish = abolish;
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
        return "Examine1StatisticsMonth{" +
        ", id=" + id +
        ", receive=" + receive +
        ", pass=" + pass +
        ", unpass=" + unpass +
        ", examiningNum=" + examiningNum +
        ", userId=" + userId +
        ", abolish=" + abolish +
        ", statisticsTime=" + statisticsTime +
        "}";
    }
}
