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
 * 文章记录管理
 *
 * @author zpy
 * @since 2019-09-23
 */
@TableName("article_record")
@Data
public class ArticleRecord extends Model<ArticleRecord> {

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
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作内容
     */
    @TableField("operation_content")
    private String operationContent;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
