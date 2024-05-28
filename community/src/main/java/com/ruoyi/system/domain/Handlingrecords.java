package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 处理记录对象 handlingrecords
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Handlingrecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录id */
    private Long recordID;

    /** 投诉ID */
    @Excel(name = "投诉ID")
    private Long complaintID;

    /** 处理人姓名 */
    @Excel(name = "处理人姓名")
    private String handlerName;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String handleResult;

    public void setRecordID(Long recordID) 
    {
        this.recordID = recordID;
    }

    public Long getRecordID() 
    {
        return recordID;
    }
    public void setComplaintID(Long complaintID) 
    {
        this.complaintID = complaintID;
    }

    public Long getComplaintID() 
    {
        return complaintID;
    }
    public void setHandlerName(String handlerName) 
    {
        this.handlerName = handlerName;
    }

    public String getHandlerName() 
    {
        return handlerName;
    }
    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }
    public void setHandleResult(String handleResult) 
    {
        this.handleResult = handleResult;
    }

    public String getHandleResult() 
    {
        return handleResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordID", getRecordID())
            .append("complaintID", getComplaintID())
            .append("handlerName", getHandlerName())
            .append("handleTime", getHandleTime())
            .append("handleResult", getHandleResult())
            .toString();
    }
}
