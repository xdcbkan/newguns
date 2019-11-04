package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 记录文章历史版本
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-16
 */
@TableName("historical_article")
@Data
public class HistoricalArticle extends Model<HistoricalArticle> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章编号
     */
    @TableField("article_num")
    private Long articleNum;
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
     * 新主标题
     */
    @TableField("new_main_title")
    private String newMainTitle;
    /**
     * 新副标题
     */
    @TableField("new_subheading")
    private String newSubheading;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 前缀0-6  提交 审1修1--- 审2修2
     */
    private Integer prefix;
    /**
     * 文章状态草稿  待审1 待审2  审1审核中  审2审核中  审1拒 审2拒 作废  待配图  配图中  文章完成   运营领取  已被购买
     */
    @TableField("article_status")
    private Integer articleStatus;
    /**
     * 配图数
     */
    @TableField("picture_num")
    private Integer pictureNum;
    /**
     * 文章分类id
     */
    @TableField("article_type_id")
    private Integer articleTypeId;
    /**
     * 审1领取人id
     */
    @TableField("examine1_id")
    private Integer examine1Id;
    /**
     * 审2领取人id
     */
    @TableField("examine2_id")
    private Integer examine2Id;
    /**
     * 配图人id
     */
    @TableField("layout_id")
    private Integer layoutId;
    /**
     * 运营领取人id
     */
    @TableField("operate_id")
    private Integer operateId;
    /**
     * 购买人id
     */
    @TableField("purchase_id")
    private Integer purchaseId;
    /**
     * 0非 1 是最新
     */
    @TableField("editor_new_flag")
    private Integer editorNewFlag;
    /**
     * 退回原因
     */
    @TableField("return_message")
    private String returnMessage;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Integer createUserId;
    /**
     * 修改人
     */
    @TableField("update_user_id")
    private Integer updateUserId;
    /**
     * 文章时效
     */
    @TableField("prescription")
    private Integer prescription;
    /**
     * 文章标签
     */
    @TableField("label")
    private String label;
    /**
     * 项目类型
     */
    @TableField("project_type_id")
    private Integer projectTypeId;
    /**
     * 插图类型
     */
    @TableField("illustration_type")
    private Integer illustrationType;

    /**
     * 新表id
     */
    @TableField("new_article_id")
    private Integer newArticleId;
    /**
     * 文章任务id
     */
    @TableField("article_task_id")
    private Integer articleTaskId;
    /**
     * url地址
     */
    private String url;
    /**
     * 发布类型
     * 1-分配、2-自拟
     */
    @TableField("release_type")
    private Integer releaseType;
    /**
     * 字数
     */
    @TableField("word_count")
    private Integer wordCount;

    /**
     * 是否下载
     * 1-未下载、2-已下载
     */
    @TableField("is_download")
    private Integer isDownload;

    /**
     * 下载地址
     */
    @TableField("dowanload_url")
    private String dowanloadUrl;
    /**
     * 审核下载地址
     */
    @TableField("dowanload_url_shen_he")
    private String dowanloadUrlShenHe;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
