package com.fc.order.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 支付日志表
 * @TableName t_pay_log
 */
@TableName(value ="t_pay_log")
@Data
public class PayLog implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 支付完成时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 支付金额（分）
     */
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    /**
     * 交易流水号
     */
    @TableField(value = "transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @TableField(value = "trade_state")
    private String tradeState;

    /**
     * 支付类型（1：微信 2：支付宝）
     */
    @TableField(value = "pay_type")
    private Byte payType;

    /**
     * 其他属性
     */
    @TableField(value = "attr")
    private String attr;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayLog other = (PayLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
            && (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getTradeState() == null ? other.getTradeState() == null : this.getTradeState().equals(other.getTradeState()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getAttr() == null ? other.getAttr() == null : this.getAttr().equals(other.getAttr()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getTradeState() == null) ? 0 : getTradeState().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getAttr() == null) ? 0 : getAttr().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", payTime=").append(payTime);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", tradeState=").append(tradeState);
        sb.append(", payType=").append(payType);
        sb.append(", attr=").append(attr);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}