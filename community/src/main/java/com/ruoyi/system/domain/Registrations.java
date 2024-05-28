package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动报名对象 registrations
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public class Registrations extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报名ID */
    private Long registrationID;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long eventID;

    /** 报名人 */
    @Excel(name = "报名人")
    private String participantName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String participantContact;

    /** 报名人数 */
    @Excel(name = "报名人数")
    private Long participantCount;

    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registeredAt;

    public void setRegistrationID(Long registrationID) 
    {
        this.registrationID = registrationID;
    }

    public Long getRegistrationID() 
    {
        return registrationID;
    }
    public void setEventID(Long eventID) 
    {
        this.eventID = eventID;
    }

    public Long getEventID() 
    {
        return eventID;
    }
    public void setParticipantName(String participantName) 
    {
        this.participantName = participantName;
    }

    public String getParticipantName() 
    {
        return participantName;
    }
    public void setParticipantContact(String participantContact) 
    {
        this.participantContact = participantContact;
    }

    public String getParticipantContact() 
    {
        return participantContact;
    }
    public void setParticipantCount(Long participantCount) 
    {
        this.participantCount = participantCount;
    }

    public Long getParticipantCount() 
    {
        return participantCount;
    }
    public void setRegisteredAt(Date registeredAt) 
    {
        this.registeredAt = registeredAt;
    }

    public Date getRegisteredAt() 
    {
        return registeredAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("registrationID", getRegistrationID())
            .append("eventID", getEventID())
            .append("participantName", getParticipantName())
            .append("participantContact", getParticipantContact())
            .append("participantCount", getParticipantCount())
            .append("registeredAt", getRegisteredAt())
            .toString();
    }
}
