package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文章发布地址表
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-17
 */
@TableName("article_publish_address")
public class ArticlePublishAddress extends Model<ArticlePublishAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id(运营人员id)
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 文章id
     */
    @TableField("article_id")
    private Integer articleId;
    /**
     * 发布地址1
     */
    private String address1;
    /**
     * 发布地址2
     */
    private String address2;
    /**
     * 发布地址3
     */
    private String address3;
    /**
     * 发布地址4
     */
    private String address4;
    /**
     * 发布地址5
     */
    private String address5;
    /**
     * 发布地址6
     */
    private String address6;
    /**
     * 发布地址7
     */
    private String address7;
    /**
     * 发布地址8
     */
    private String address8;
    /**
     * 发布地址9
     */
    private String address9;
    /**
     * 发布地址10
     */
    private String address10;
    /**
     * 发布地址11
     */
    private String address11;
    /**
     * 发布地址12
     */
    private String address12;
    /**
     * 发布地址13
     */
    private String address13;
    /**
     * 发布地址14
     */
    private String address14;
    /**
     * 发布地址15
     */
    private String address15;
    /**
     * 发布地址16
     */
    private String address16;
    /**
     * 发布地址17
     */
    private String address17;
    /**
     * 发布地址18
     */
    private String address18;
    /**
     * 发布地址19
     */
    private String address19;
    /**
     * 发布地址20
     */
    private String address20;


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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getAddress6() {
        return address6;
    }

    public void setAddress6(String address6) {
        this.address6 = address6;
    }

    public String getAddress7() {
        return address7;
    }

    public void setAddress7(String address7) {
        this.address7 = address7;
    }

    public String getAddress8() {
        return address8;
    }

    public void setAddress8(String address8) {
        this.address8 = address8;
    }

    public String getAddress9() {
        return address9;
    }

    public void setAddress9(String address9) {
        this.address9 = address9;
    }

    public String getAddress10() {
        return address10;
    }

    public void setAddress10(String address10) {
        this.address10 = address10;
    }

    public String getAddress11() {
        return address11;
    }

    public void setAddress11(String address11) {
        this.address11 = address11;
    }

    public String getAddress12() {
        return address12;
    }

    public void setAddress12(String address12) {
        this.address12 = address12;
    }

    public String getAddress13() {
        return address13;
    }

    public void setAddress13(String address13) {
        this.address13 = address13;
    }

    public String getAddress14() {
        return address14;
    }

    public void setAddress14(String address14) {
        this.address14 = address14;
    }

    public String getAddress15() {
        return address15;
    }

    public void setAddress15(String address15) {
        this.address15 = address15;
    }

    public String getAddress16() {
        return address16;
    }

    public void setAddress16(String address16) {
        this.address16 = address16;
    }

    public String getAddress17() {
        return address17;
    }

    public void setAddress17(String address17) {
        this.address17 = address17;
    }

    public String getAddress18() {
        return address18;
    }

    public void setAddress18(String address18) {
        this.address18 = address18;
    }

    public String getAddress19() {
        return address19;
    }

    public void setAddress19(String address19) {
        this.address19 = address19;
    }

    public String getAddress20() {
        return address20;
    }

    public void setAddress20(String address20) {
        this.address20 = address20;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticlePublishAddress{" +
        ", id=" + id +
        ", userId=" + userId +
        ", articleId=" + articleId +
        ", address1=" + address1 +
        ", address2=" + address2 +
        ", address3=" + address3 +
        ", address4=" + address4 +
        ", address5=" + address5 +
        ", address6=" + address6 +
        ", address7=" + address7 +
        ", address8=" + address8 +
        ", address9=" + address9 +
        ", address10=" + address10 +
        ", address11=" + address11 +
        ", address12=" + address12 +
        ", address13=" + address13 +
        ", address14=" + address14 +
        ", address15=" + address15 +
        ", address16=" + address16 +
        ", address17=" + address17 +
        ", address18=" + address18 +
        ", address19=" + address19 +
        ", address20=" + address20 +
        "}";
    }
}
