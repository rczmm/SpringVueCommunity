package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 缴费记录对象 paymentrecords
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Paymentrecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordID;

    /** 费用ID */
    @Excel(name = "费用ID")
    private Long expenseID;

    /** 缴纳人 */
    @Excel(name = "缴纳人")
    private String payerName;

    /** 缴纳时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "缴纳时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 缴纳账号 */
    @Excel(name = "缴纳账号")
    private BigDecimal paymentAmount;

    public void setRecordID(Long recordID) 
    {
        this.recordID = recordID;
    }

    public Long getRecordID() 
    {
        return recordID;
    }
    public void setExpenseID(Long expenseID) 
    {
        this.expenseID = expenseID;
    }

    public Long getExpenseID() 
    {
        return expenseID;
    }
    public void setPayerName(String payerName) 
    {
        this.payerName = payerName;
    }

    public String getPayerName() 
    {
        return payerName;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setPaymentAmount(BigDecimal paymentAmount) 
    {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentAmount() 
    {
        return paymentAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordID", getRecordID())
            .append("expenseID", getExpenseID())
            .append("payerName", getPayerName())
            .append("paymentTime", getPaymentTime())
            .append("paymentAmount", getPaymentAmount())
            .toString();
    }
}
