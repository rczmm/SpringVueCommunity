package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投诉管理对象 complaints
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Complaints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投诉ID */
    private Long complaintID;

    /** 投诉人名 */
    @Excel(name = "投诉人名")
    private String complainantName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String complainantContact;

    /** 投诉内容 */
    @Excel(name = "投诉内容")
    private String complaintContent;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setComplaintID(Long complaintID) 
    {
        this.complaintID = complaintID;
    }

    public Long getComplaintID() 
    {
        return complaintID;
    }
    public void setComplainantName(String complainantName) 
    {
        this.complainantName = complainantName;
    }

    public String getComplainantName() 
    {
        return complainantName;
    }
    public void setComplainantContact(String complainantContact) 
    {
        this.complainantContact = complainantContact;
    }

    public String getComplainantContact() 
    {
        return complainantContact;
    }
    public void setComplaintContent(String complaintContent) 
    {
        this.complaintContent = complaintContent;
    }

    public String getComplaintContent() 
    {
        return complaintContent;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("complaintID", getComplaintID())
            .append("complainantName", getComplainantName())
            .append("complainantContact", getComplainantContact())
            .append("complaintContent", getComplaintContent())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
