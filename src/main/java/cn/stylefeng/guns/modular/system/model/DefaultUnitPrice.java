package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 默认单价表
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-13
 */
@TableName("default_unit_price")
public class DefaultUnitPrice extends Model<DefaultUnitPrice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户购买文章单价
     */
    @TableField("purchase_price")
    private Integer purchasePrice;
    /**
     * 文编文章结算单价
     */
    @TableField("settlement_price")
    private Integer settlementPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(Integer settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DefaultUnitPrice{" +
        ", id=" + id +
        ", purchasePrice=" + purchasePrice +
        ", settlementPrice=" + settlementPrice +
        "}";
    }
}
