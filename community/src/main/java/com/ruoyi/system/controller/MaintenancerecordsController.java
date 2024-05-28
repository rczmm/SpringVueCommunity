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
import com.ruoyi.system.domain.Maintenancerecords;
import com.ruoyi.system.service.IMaintenancerecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维修记录Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Maintenance/maintenancerecords")
public class MaintenancerecordsController extends BaseController
{
    @Autowired
    private IMaintenancerecordsService maintenancerecordsService;

    /**
     * 查询维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(Maintenancerecords maintenancerecords)
    {
        startPage();
        List<Maintenancerecords> list = maintenancerecordsService.selectMaintenancerecordsList(maintenancerecords);
        return getDataTable(list);
    }

    /**
     * 导出维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:export')")
    @Log(title = "维修记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Maintenancerecords maintenancerecords)
    {
        List<Maintenancerecords> list = maintenancerecordsService.selectMaintenancerecordsList(maintenancerecords);
        ExcelUtil<Maintenancerecords> util = new ExcelUtil<Maintenancerecords>(Maintenancerecords.class);
        util.exportExcel(response, list, "维修记录数据");
    }

    /**
     * 获取维修记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:query')")
    @GetMapping(value = "/{recordID}")
    public AjaxResult getInfo(@PathVariable("recordID") Long recordID)
    {
        return success(maintenancerecordsService.selectMaintenancerecordsByRecordID(recordID));
    }

    /**
     * 新增维修记录
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:add')")
    @Log(title = "维修记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Maintenancerecords maintenancerecords)
    {
        return toAjax(maintenancerecordsService.insertMaintenancerecords(maintenancerecords));
    }

    /**
     * 修改维修记录
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:edit')")
    @Log(title = "维修记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Maintenancerecords maintenancerecords)
    {
        return toAjax(maintenancerecordsService.updateMaintenancerecords(maintenancerecords));
    }

    /**
     * 删除维修记录
     */
    @PreAuthorize("@ss.hasPermi('Maintenance:maintenancerecords:remove')")
    @Log(title = "维修记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIDs}")
    public AjaxResult remove(@PathVariable Long[] recordIDs)
    {
        return toAjax(maintenancerecordsService.deleteMaintenancerecordsByRecordIDs(recordIDs));
    }
}
