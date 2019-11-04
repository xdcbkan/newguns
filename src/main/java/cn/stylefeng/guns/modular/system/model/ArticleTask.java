package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章任务表
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-21
 */
@TableName("article_task")
@Data
public class ArticleTask extends Model<ArticleTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 主标题
     */
    @TableField("main_title")
    private String mainTitle;
    /**
     * 副标题
     */
    private String subheading;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 时效性类别(1 非实时或知识类文章 2 精品类文章 3 实时类文章 4 其他类文章)
     */
    @TableField("timeliness_category")
    private Integer timelinessCategory;
    /**
     * 任务状态(1、待领去  2、已领取)
     */
    @TableField("task_status")
    private Integer taskStatus;
    /**
     * 领取人id
     */
    @TableField("recipients_id")
    private Integer recipientsId;
    /**
     * 领取人
     */
    private String recipients;
    /**
     * 领取时间
     */
    @TableField("pick_up_time")
    private Date pickUpTime;
    /**
     * 文章分类
     */
    private Integer type;
    /**
     * url地址
     */
    private String url;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
