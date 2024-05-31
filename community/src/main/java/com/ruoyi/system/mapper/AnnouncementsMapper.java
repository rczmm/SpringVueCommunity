package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Announcements;

/**
 * 公告管理Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface AnnouncementsMapper 
{
    /**
     * 查询公告管理
     * 
     * @param announcementID 公告管理主键
     * @return 公告管理
     */
    public Announcements selectAnnouncementsByAnnouncementID(Long announcementID);

    /**
     * 查询公告管理
     *
     * @param authorId 作者id
     * @return 公告管理
     */
    public List<Announcements> selectAnnouncementsByAuthorId(Long authorId);

    /**
     * 查询公告管理列表
     * 
     * @param announcements 公告管理
     * @return 公告管理集合
     */
    public List<Announcements> selectAnnouncementsList(Announcements announcements);

    /**
     * 新增公告管理
     * 
     * @param announcements 公告管理
     * @return 结果
     */
    public int insertAnnouncements(Announcements announcements);

    /**
     * 修改公告管理
     * 
     * @param announcements 公告管理
     * @return 结果
     */
    public int updateAnnouncements(Announcements announcements);

    /**
     * 删除公告管理
     * 
     * @param announcementID 公告管理主键
     * @return 结果
     */
    public int deleteAnnouncementsByAnnouncementID(Long announcementID);

    /**
     * 批量删除公告管理
     * 
     * @param announcementIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnnouncementsByAnnouncementIDs(Long[] announcementIDs);
}
