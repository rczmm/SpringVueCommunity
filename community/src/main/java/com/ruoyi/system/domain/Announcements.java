package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 公告管理对象 announcements
 *
 * @author rczmm
 * @date 2024-04-15
 */
public class Announcements extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    private Long announcementID;

    /**
     * 公告标题
     */
    @Excel(name = "公告标题")
    private String title;

    /**
     * 公告内容
     */
    @Excel(name = "公告内容")
    private String content;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishedAt;

    /**
     * 作者
     */
    @Excel(name = "作者")
    private String author;

    /**
     * 作者编号
     */
    @Excel(name = "作者编号")
    private Long authorId;

    public void setAnnouncementID(Long announcementID) {
        this.announcementID = announcementID;
    }

    public Long getAnnouncementID() {
        return announcementID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("announcementID", getAnnouncementID())
                .append("title", getTitle())
                .append("content", getContent())
                .append("publishedAt", getPublishedAt())
                .append("author", getAuthor())
                .append("authorId", getAuthorId())
                .toString();
    }
}
