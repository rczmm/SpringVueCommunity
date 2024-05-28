package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公告管理对象 announcements
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public class Announcements extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Long announcementID;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String title;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String content;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishedAt;

    public void setAnnouncementID(Long announcementID) 
    {
        this.announcementID = announcementID;
    }

    public Long getAnnouncementID() 
    {
        return announcementID;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPublishedAt(Date publishedAt) 
    {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() 
    {
        return publishedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("announcementID", getAnnouncementID())
            .append("title", getTitle())
            .append("content", getContent())
            .append("publishedAt", getPublishedAt())
            .toString();
    }
}
