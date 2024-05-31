package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用管理对象 expenses
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Expenses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 缴费单ID */
    private Long expenseID;

    /** 缴费类型 */
    @Excel(name = "缴费类型")
    private String expenseType;

    /** 账号 */
    @Excel(name = "账号")
    private BigDecimal amount;

    /** 状态 */
    @Excel(name = "状态")
    private String paymentStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    public Expenses(String expenseType, BigDecimal amount, String paymentStatus, Long userId) {
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.userId = userId;
    }

    public Expenses() {
    }
    public void setExpenseID(Long expenseID)
    {
        this.expenseID = expenseID;
    }

    public Long getExpenseID() 
    {
        return expenseID;
    }
    public void setExpenseType(String expenseType) 
    {
        this.expenseType = expenseType;
    }

    public String getExpenseType() 
    {
        return expenseType;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expenseID", getExpenseID())
            .append("expenseType", getExpenseType())
            .append("amount", getAmount())
            .append("paymentStatus", getPaymentStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt()).append("userId", getUserId())
            .toString();
    }

}
