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
import com.ruoyi.system.domain.Registrations;
import com.ruoyi.system.service.IRegistrationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动报名Controller
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/event/registrations")
public class RegistrationsController extends BaseController
{
    @Autowired
    private IRegistrationsService registrationsService;

    /**
     * 查询活动报名列表
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:list')")
    @GetMapping("/list")
    public TableDataInfo list(Registrations registrations)
    {
        startPage();
        List<Registrations> list = registrationsService.selectRegistrationsList(registrations);
        return getDataTable(list);
    }

    /**
     * 导出活动报名列表
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:export')")
    @Log(title = "活动报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Registrations registrations)
    {
        List<Registrations> list = registrationsService.selectRegistrationsList(registrations);
        ExcelUtil<Registrations> util = new ExcelUtil<Registrations>(Registrations.class);
        util.exportExcel(response, list, "活动报名数据");
    }

    /**
     * 获取活动报名详细信息
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:query')")
    @GetMapping(value = "/{registrationID}")
    public AjaxResult getInfo(@PathVariable("registrationID") Long registrationID)
    {
        return success(registrationsService.selectRegistrationsByRegistrationID(registrationID));
    }

    /**
     * 新增活动报名
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:add')")
    @Log(title = "活动报名", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Registrations registrations)
    {
        return toAjax(registrationsService.insertRegistrations(registrations));
    }

    /**
     * 修改活动报名
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:edit')")
    @Log(title = "活动报名", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Registrations registrations)
    {
        return toAjax(registrationsService.updateRegistrations(registrations));
    }

    /**
     * 删除活动报名
     */
    @PreAuthorize("@ss.hasPermi('event:registrations:remove')")
    @Log(title = "活动报名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{registrationIDs}")
    public AjaxResult remove(@PathVariable Long[] registrationIDs)
    {
        return toAjax(registrationsService.deleteRegistrationsByRegistrationIDs(registrationIDs));
    }
}
