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
import com.ruoyi.system.domain.Handlingrecords;
import com.ruoyi.system.service.IHandlingrecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 处理记录Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Complaint/handlingrecords")
public class HandlingrecordsController extends BaseController
{
    @Autowired
    private IHandlingrecordsService handlingrecordsService;

    /**
     * 查询处理记录列表
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(Handlingrecords handlingrecords)
    {
        startPage();
        List<Handlingrecords> list = handlingrecordsService.selectHandlingrecordsList(handlingrecords);
        return getDataTable(list);
    }

    /**
     * 导出处理记录列表
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:export')")
    @Log(title = "处理记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Handlingrecords handlingrecords)
    {
        List<Handlingrecords> list = handlingrecordsService.selectHandlingrecordsList(handlingrecords);
        ExcelUtil<Handlingrecords> util = new ExcelUtil<Handlingrecords>(Handlingrecords.class);
        util.exportExcel(response, list, "处理记录数据");
    }

    /**
     * 获取处理记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:query')")
    @GetMapping(value = "/{recordID}")
    public AjaxResult getInfo(@PathVariable("recordID") Long recordID)
    {
        return success(handlingrecordsService.selectHandlingrecordsByRecordID(recordID));
    }

    /**
     * 新增处理记录
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:add')")
    @Log(title = "处理记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Handlingrecords handlingrecords)
    {
        return toAjax(handlingrecordsService.insertHandlingrecords(handlingrecords));
    }

    /**
     * 修改处理记录
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:edit')")
    @Log(title = "处理记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Handlingrecords handlingrecords)
    {
        return toAjax(handlingrecordsService.updateHandlingrecords(handlingrecords));
    }

    /**
     * 删除处理记录
     */
    @PreAuthorize("@ss.hasPermi('Complaint:handlingrecords:remove')")
    @Log(title = "处理记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIDs}")
    public AjaxResult remove(@PathVariable Long[] recordIDs)
    {
        return toAjax(handlingrecordsService.deleteHandlingrecordsByRecordIDs(recordIDs));
    }
}
