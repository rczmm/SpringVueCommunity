package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 维修记录对象 maintenancerecords
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Maintenancerecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordID;

    /** 报修ID */
    @Excel(name = "报修ID")
    private Long requestID;

    /** 维修人姓名 */
    @Excel(name = "维修人姓名")
    private String repairerName;

    /** 维修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairTime;

    /** 维修结果 */
    @Excel(name = "维修结果")
    private String repairResult;

    public void setRecordID(Long recordID) 
    {
        this.recordID = recordID;
    }

    public Long getRecordID() 
    {
        return recordID;
    }
    public void setRequestID(Long requestID) 
    {
        this.requestID = requestID;
    }

    public Long getRequestID() 
    {
        return requestID;
    }
    public void setRepairerName(String repairerName) 
    {
        this.repairerName = repairerName;
    }

    public String getRepairerName() 
    {
        return repairerName;
    }
    public void setRepairTime(Date repairTime) 
    {
        this.repairTime = repairTime;
    }

    public Date getRepairTime() 
    {
        return repairTime;
    }
    public void setRepairResult(String repairResult) 
    {
        this.repairResult = repairResult;
    }

    public String getRepairResult() 
    {
        return repairResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordID", getRecordID())
            .append("requestID", getRequestID())
            .append("repairerName", getRepairerName())
            .append("repairTime", getRepairTime())
            .append("repairResult", getRepairResult())
            .toString();
    }
}
