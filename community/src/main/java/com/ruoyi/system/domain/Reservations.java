package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动预约对象 reservations
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public class Reservations extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    private Long reservationID;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long eventID;

    /** 姓名 */
    @Excel(name = "姓名")
    private String reserverName;

    /** 练习方式 */
    @Excel(name = "练习方式")
    private String reserverContact;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

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

    public void setReservationID(Long reservationID) 
    {
        this.reservationID = reservationID;
    }

    public Long getReservationID() 
    {
        return reservationID;
    }
    public void setEventID(Long eventID) 
    {
        this.eventID = eventID;
    }

    public Long getEventID() 
    {
        return eventID;
    }
    public void setReserverName(String reserverName) 
    {
        this.reserverName = reserverName;
    }

    public String getReserverName() 
    {
        return reserverName;
    }
    public void setReserverContact(String reserverContact) 
    {
        this.reserverContact = reserverContact;
    }

    public String getReserverContact() 
    {
        return reserverContact;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
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
            .append("reservationID", getReservationID())
            .append("eventID", getEventID())
            .append("reserverName", getReserverName())
            .append("reserverContact", getReserverContact())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
