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
 * 领取文章记录表

 * </p>
 *
 * @author stylefeng
 * @since 2019-05-14
 */
@TableName("article_receive")
public class ArticleReceive extends Model<ArticleReceive> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章id
     */
    @TableField("article_id")
    private Integer articleId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 执行结果已领取（默认）、审核通过、打回、作废、配图完成、发布
     */
    @TableField("results_enforcement")
    private Integer resultsEnforcement;
    /**
     * 统计日期
     */
    @TableField("operate_time")
    private Date operateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getResultsEnforcement() {
        return resultsEnforcement;
    }

    public void setResultsEnforcement(Integer resultsEnforcement) {
        this.resultsEnforcement = resultsEnforcement;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticleReceive{" +
        ", id=" + id +
        ", articleId=" + articleId +
        ", userId=" + userId +
        ", resultsEnforcement=" + resultsEnforcement +
        ", operateTime=" + operateTime +
        "}";
    }
}
