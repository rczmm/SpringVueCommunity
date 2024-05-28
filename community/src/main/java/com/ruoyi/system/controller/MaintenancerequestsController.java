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
import com.ruoyi.system.domain.Maintenancerequests;
import com.ruoyi.system.service.IMaintenancerequestsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报修管理Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Maintenance/maintenancerequests")
public class MaintenancerequestsController extends BaseController
{
    @Autowired
    private IMaintenancerequestsService maintenancerequestsService;

    /**
     * 查询报修管理列表
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:list')")
    @GetMapping("/list")
    public TableDataInfo list(Maintenancerequests maintenancerequests)
    {
        startPage();
        List<Maintenancerequests> list = maintenancerequestsService.selectMaintenancerequestsList(maintenancerequests);
        return getDataTable(list);
    }

    /**
     * 导出报修管理列表
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:export')")
    @Log(title = "报修管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Maintenancerequests maintenancerequests)
    {
        List<Maintenancerequests> list = maintenancerequestsService.selectMaintenancerequestsList(maintenancerequests);
        ExcelUtil<Maintenancerequests> util = new ExcelUtil<Maintenancerequests>(Maintenancerequests.class);
        util.exportExcel(response, list, "报修管理数据");
    }

    /**
     * 获取报修管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:query')")
    @GetMapping(value = "/{requestID}")
    public AjaxResult getInfo(@PathVariable("requestID") Long requestID)
    {
        return success(maintenancerequestsService.selectMaintenancerequestsByRequestID(requestID));
    }

    /**
     * 新增报修管理
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:add')")
    @Log(title = "报修管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Maintenancerequests maintenancerequests)
    {
        return toAjax(maintenancerequestsService.insertMaintenancerequests(maintenancerequests));
    }

    /**
     * 修改报修管理
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:edit')")
    @Log(title = "报修管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Maintenancerequests maintenancerequests)
    {
        return toAjax(maintenancerequestsService.updateMaintenancerequests(maintenancerequests));
    }

    /**
     * 删除报修管理
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerequests:remove')")
    @Log(title = "报修管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{requestIDs}")
    public AjaxResult remove(@PathVariable Long[] requestIDs)
    {
        return toAjax(maintenancerequestsService.deleteMaintenancerequestsByRequestIDs(requestIDs));
    }
}
