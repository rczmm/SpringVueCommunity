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
import com.ruoyi.system.domain.Families;
import com.ruoyi.system.service.IFamiliesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 家庭信息Controller
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/person/families")
public class FamiliesController extends BaseController
{
    @Autowired
    private IFamiliesService familiesService;

    /**
     * 查询家庭信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:families:list')")
    @GetMapping("/list")
    public TableDataInfo list(Families families)
    {
        startPage();
        List<Families> list = familiesService.selectFamiliesList(families);
        return getDataTable(list);
    }

    /**
     * 导出家庭信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:families:export')")
    @Log(title = "家庭信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Families families)
    {
        List<Families> list = familiesService.selectFamiliesList(families);
        ExcelUtil<Families> util = new ExcelUtil<Families>(Families.class);
        util.exportExcel(response, list, "家庭信息数据");
    }

    /**
     * 获取家庭信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:families:query')")
    @GetMapping(value = "/{familyID}")
    public AjaxResult getInfo(@PathVariable("familyID") Long familyID)
    {
        return success(familiesService.selectFamiliesByFamilyID(familyID));
    }

    /**
     * 新增家庭信息
     */
    @PreAuthorize("@ss.hasPermi('person:families:add')")
    @Log(title = "家庭信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Families families)
    {
        return toAjax(familiesService.insertFamilies(families));
    }

    /**
     * 修改家庭信息
     */
    @PreAuthorize("@ss.hasPermi('person:families:edit')")
    @Log(title = "家庭信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Families families)
    {
        return toAjax(familiesService.updateFamilies(families));
    }

    /**
     * 删除家庭信息
     */
    @PreAuthorize("@ss.hasPermi('person:families:remove')")
    @Log(title = "家庭信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{familyIDs}")
    public AjaxResult remove(@PathVariable Long[] familyIDs)
    {
        return toAjax(familiesService.deleteFamiliesByFamilyIDs(familyIDs));
    }
}
