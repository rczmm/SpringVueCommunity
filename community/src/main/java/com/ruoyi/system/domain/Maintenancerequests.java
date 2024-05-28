package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报修管理对象 maintenancerequests
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Maintenancerequests extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报修ID */
    private Long requestID;

    /** 报修人姓名 */
    @Excel(name = "报修人姓名")
    private String requesterName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String requesterContact;

    /** 报修内容 */
    @Excel(name = "报修内容")
    private String requestContent;

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

    public void setRequestID(Long requestID) 
    {
        this.requestID = requestID;
    }

    public Long getRequestID() 
    {
        return requestID;
    }
    public void setRequesterName(String requesterName) 
    {
        this.requesterName = requesterName;
    }

    public String getRequesterName() 
    {
        return requesterName;
    }
    public void setRequesterContact(String requesterContact) 
    {
        this.requesterContact = requesterContact;
    }

    public String getRequesterContact() 
    {
        return requesterContact;
    }
    public void setRequestContent(String requestContent) 
    {
        this.requestContent = requestContent;
    }

    public String getRequestContent() 
    {
        return requestContent;
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
            .append("requestID", getRequestID())
            .append("requesterName", getRequesterName())
            .append("requesterContact", getRequesterContact())
            .append("requestContent", getRequestContent())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
