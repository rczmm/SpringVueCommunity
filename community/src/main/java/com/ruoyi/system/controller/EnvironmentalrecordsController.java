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
import com.ruoyi.system.domain.Environmentalrecords;
import com.ruoyi.system.service.IEnvironmentalrecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 环境卫生Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Environmental/environmentalrecords")
public class EnvironmentalrecordsController extends BaseController
{
    @Autowired
    private IEnvironmentalrecordsService environmentalrecordsService;

    /**
     * 查询环境卫生列表
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(Environmentalrecords environmentalrecords)
    {
        startPage();
        List<Environmentalrecords> list = environmentalrecordsService.selectEnvironmentalrecordsList(environmentalrecords);
        return getDataTable(list);
    }

    /**
     * 导出环境卫生列表
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:export')")
    @Log(title = "环境卫生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Environmentalrecords environmentalrecords)
    {
        List<Environmentalrecords> list = environmentalrecordsService.selectEnvironmentalrecordsList(environmentalrecords);
        ExcelUtil<Environmentalrecords> util = new ExcelUtil<Environmentalrecords>(Environmentalrecords.class);
        util.exportExcel(response, list, "环境卫生数据");
    }

    /**
     * 获取环境卫生详细信息
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:query')")
    @GetMapping(value = "/{recordID}")
    public AjaxResult getInfo(@PathVariable("recordID") Long recordID)
    {
        return success(environmentalrecordsService.selectEnvironmentalrecordsByRecordID(recordID));
    }

    /**
     * 新增环境卫生
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:add')")
    @Log(title = "环境卫生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Environmentalrecords environmentalrecords)
    {
        return toAjax(environmentalrecordsService.insertEnvironmentalrecords(environmentalrecords));
    }

    /**
     * 修改环境卫生
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:edit')")
    @Log(title = "环境卫生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Environmentalrecords environmentalrecords)
    {
        return toAjax(environmentalrecordsService.updateEnvironmentalrecords(environmentalrecords));
    }

    /**
     * 删除环境卫生
     */
    @PreAuthorize("@ss.hasPermi('Environmental:environmentalrecords:remove')")
    @Log(title = "环境卫生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIDs}")
    public AjaxResult remove(@PathVariable Long[] recordIDs)
    {
        return toAjax(environmentalrecordsService.deleteEnvironmentalrecordsByRecordIDs(recordIDs));
    }
}
