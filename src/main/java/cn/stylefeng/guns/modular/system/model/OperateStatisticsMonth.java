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
 * 运营每月工作统计表

 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@TableName("operate_statistics_month")
public class OperateStatisticsMonth extends Model<OperateStatisticsMonth> {

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
        return "OperateStatisticsMonth{" +
        ", id=" + id +
        ", receive=" + receive +
        ", userId=" + userId +
        ", abolish=" + abolish +
        ", statisticsTime=" + statisticsTime +
        "}";
    }
}
