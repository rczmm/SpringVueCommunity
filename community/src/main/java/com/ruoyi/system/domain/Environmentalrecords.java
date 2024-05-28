package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 环境卫生对象 environmentalrecords
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Environmentalrecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordID;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 环境卫生情况 */
    @Excel(name = "环境卫生情况")
    private String environmentalStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setRecordID(Long recordID) 
    {
        this.recordID = recordID;
    }

    public Long getRecordID() 
    {
        return recordID;
    }
    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }
    public void setEnvironmentalStatus(String environmentalStatus) 
    {
        this.environmentalStatus = environmentalStatus;
    }

    public String getEnvironmentalStatus() 
    {
        return environmentalStatus;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordID", getRecordID())
            .append("recordTime", getRecordTime())
            .append("environmentalStatus", getEnvironmentalStatus())
            .append("remarks", getRemarks())
            .toString();
    }
}
