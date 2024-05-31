package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Announcements;
import com.ruoyi.system.mapper.AnnouncementsMapper;
import com.ruoyi.system.service.IAnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告管理Service业务层处理
 *
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class AnnouncementsServiceImpl implements IAnnouncementsService {
    @Autowired
    private AnnouncementsMapper announcementsMapper;

    /**
     * 查询公告管理
     *
     * @param announcementID 公告管理主键
     * @return 公告管理
     */
    @Override
    public Announcements selectAnnouncementsByAnnouncementID(Long announcementID) {
        return announcementsMapper.selectAnnouncementsByAnnouncementID(announcementID);
    }

    /**
     * 查询公告管理
     *
     * @param authorId 作者id
     * @return 公告管理
     */
    @Override
    public List<Announcements> selectAnnouncementsByAuthorID(Long authorId) {
        return announcementsMapper.selectAnnouncementsByAuthorId(authorId);
    }


    /**
     * 查询公告管理列表
     *
     * @param announcements 公告管理
     * @return 公告管理
     */
    @Override
    public List<Announcements> selectAnnouncementsList(Announcements announcements) {
        return announcementsMapper.selectAnnouncementsList(announcements);
    }

    /**
     * 新增公告管理
     *
     * @param announcements 公告管理
     * @return 结果
     */
    @Override
    public int insertAnnouncements(Announcements announcements) {
        return announcementsMapper.insertAnnouncements(announcements);
    }

    /**
     * 修改公告管理
     *
     * @param announcements 公告管理
     * @return 结果
     */
    @Override
    public int updateAnnouncements(Announcements announcements) {
        return announcementsMapper.updateAnnouncements(announcements);
    }

    /**
     * 批量删除公告管理
     *
     * @param announcementIDs 需要删除的公告管理主键
     * @return 结果
     */
    @Override
    public int deleteAnnouncementsByAnnouncementIDs(Long[] announcementIDs) {
        return announcementsMapper.deleteAnnouncementsByAnnouncementIDs(announcementIDs);
    }

    /**
     * 删除公告管理信息
     *
     * @param announcementID 公告管理主键
     * @return 结果
     */
    @Override
    public int deleteAnnouncementsByAnnouncementID(Long announcementID) {
        return announcementsMapper.deleteAnnouncementsByAnnouncementID(announcementID);
    }
}
