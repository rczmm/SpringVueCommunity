package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Events;
import com.ruoyi.system.service.IEventsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/event/events")
public class EventsController extends BaseController
{
    @Autowired
    private IEventsService eventsService;

    /**
     * 查询活动列表
     */
    @PreAuthorize("@ss.hasPermi('event:events:list')")
    @GetMapping("/list")
    public TableDataInfo list(Events events)
    {
        startPage();
        List<Events> list = eventsService.selectEventsList(events);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @PreAuthorize("@ss.hasPermi('event:events:export')")
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Events events)
    {
        List<Events> list = eventsService.selectEventsList(events);
        ExcelUtil<Events> util = new ExcelUtil<Events>(Events.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('event:events:query')")
    @GetMapping(value = "/{eventID}")
    public AjaxResult getInfo(@PathVariable("eventID") Long eventID)
    {
        return success(eventsService.selectEventsByEventID(eventID));
    }

    /**
     * 新增活动
     */
    @PreAuthorize("@ss.hasPermi('event:events:add')")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Events events)
    {
        return toAjax(eventsService.insertEvents(events));
    }

    /**
     * 修改活动
     */
    @PreAuthorize("@ss.hasPermi('event:events:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Events events)
    {
        return toAjax(eventsService.updateEvents(events));
    }

    /**
     * 删除活动
     */
    @PreAuthorize("@ss.hasPermi('event:events:remove')")
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eventIDs}")
    public AjaxResult remove(@PathVariable Long[] eventIDs)
    {
        return toAjax(eventsService.deleteEventsByEventIDs(eventIDs));
    }
}
