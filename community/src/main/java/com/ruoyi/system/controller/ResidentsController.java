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
import com.ruoyi.system.domain.Residents;
import com.ruoyi.system.service.IResidentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 居民信息Controller
 * 
 * @author rcz
 * @date 2024-05-29
 */
@RestController
@RequestMapping("/person/residents")
public class ResidentsController extends BaseController
{
    @Autowired
    private IResidentsService residentsService;

    /**
     * 查询居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:residents:list')")
    @GetMapping("/list")
    public TableDataInfo list(Residents residents)
    {
        startPage();
        List<Residents> list = residentsService.selectResidentsList(residents);
        return getDataTable(list);
    }

    /**
     * 导出居民信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:residents:export')")
    @Log(title = "居民信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Residents residents)
    {
        List<Residents> list = residentsService.selectResidentsList(residents);
        ExcelUtil<Residents> util = new ExcelUtil<Residents>(Residents.class);
        util.exportExcel(response, list, "居民信息数据");
    }

    /**
     * 获取居民信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:residents:query')")
    @GetMapping(value = "/{residentID}")
    public AjaxResult getInfo(@PathVariable("residentID") Long residentID)
    {
        return success(residentsService.selectResidentsByResidentID(residentID));
    }

    /**
     * 新增居民信息
     */
    @PreAuthorize("@ss.hasPermi('person:residents:add')")
    @Log(title = "居民信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Residents residents)
    {
        return toAjax(residentsService.insertResidents(residents));
    }

    /**
     * 修改居民信息
     */
    @PreAuthorize("@ss.hasPermi('person:residents:edit')")
    @Log(title = "居民信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Residents residents)
    {
        return toAjax(residentsService.updateResidents(residents));
    }

    /**
     * 删除居民信息
     */
    @PreAuthorize("@ss.hasPermi('person:residents:remove')")
    @Log(title = "居民信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{residentIDs}")
    public AjaxResult remove(@PathVariable Long[] residentIDs)
    {
        return toAjax(residentsService.deleteResidentsByResidentIDs(residentIDs));
    }
}
