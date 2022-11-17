package com.fc.order.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order
 */
@TableName(value ="t_order")
@Data
public class Order implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 游戏id
     */
    @TableField(value = "game_id")
    private String gameId;

    /**
     * 游戏封面
     */
    @TableField(value = "game_picture")
    private String gamePicture;

    /**
     * 总价格
     */
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    /**
     * 状态 默认0（未支付）
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @TableLogic
    private Byte isDeleted;

    /**
     * 
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getGameId() == null ? other.getGameId() == null : this.getGameId().equals(other.getGameId()))
            && (this.getGamePicture() == null ? other.getGamePicture() == null : this.getGamePicture().equals(other.getGamePicture()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getGameId() == null) ? 0 : getGameId().hashCode());
        result = prime * result + ((getGamePicture() == null) ? 0 : getGamePicture().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", phone=").append(phone);
        sb.append(", gameId=").append(gameId);
        sb.append(", gamePicture=").append(gamePicture);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", status=").append(status);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}