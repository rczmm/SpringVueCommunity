package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Announcements;
import com.ruoyi.system.service.IAnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 公告管理Controller
 *
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Announcement/announcements")
public class AnnouncementsController extends BaseController {

    private final IAnnouncementsService announcementsService;

    @Autowired
    public AnnouncementsController(IAnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    /**
     * 查询公告管理列表
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:list')")
    @GetMapping("/list")
    public TableDataInfo list(Announcements announcements) {
        startPage();
        List<Announcements> list = announcementsService.selectAnnouncementsList(announcements);
        return getDataTable(list);
    }

    /**
     * 获取公告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:select')")
    @GetMapping("/author/{authorId}")
    public TableDataInfo getInfoByAuthorId(@PathVariable("authorId") Long authorId) {
        startPage();
        System.out.println(authorId);
        List<Announcements> list = announcementsService.selectAnnouncementsByAuthorID(authorId);
        return getDataTable(list);
    }

    /**
     * 导出公告管理列表
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:export')")
    @Log(title = "公告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Announcements announcements) {
        List<Announcements> list = announcementsService.selectAnnouncementsList(announcements);
        ExcelUtil<Announcements> util = new ExcelUtil<Announcements>(Announcements.class);
        util.exportExcel(response, list, "公告管理数据");
    }

    /**
     * 获取公告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:query')")
    @GetMapping(value = "/{announcementID}")
    public AjaxResult getInfo(@PathVariable("announcementID") Long announcementID) {
        return success(announcementsService.selectAnnouncementsByAnnouncementID(announcementID));
    }

    /**
     * 新增公告管理
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:add')")
    @Log(title = "公告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Announcements announcements) {
        return toAjax(announcementsService.insertAnnouncements(announcements));
    }

    /**
     * 修改公告管理
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:edit')")
    @Log(title = "公告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Announcements announcements) {
        return toAjax(announcementsService.updateAnnouncements(announcements));
    }

    /**
     * 删除公告管理
     */
    @PreAuthorize("@ss.hasPermi('Announcement:announcements:remove')")
    @Log(title = "公告管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{announcementIDs}")
    public AjaxResult remove(@PathVariable Long[] announcementIDs) {
        return toAjax(announcementsService.deleteAnnouncementsByAnnouncementIDs(announcementIDs));
    }
}
