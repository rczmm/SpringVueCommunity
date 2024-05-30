package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.system.domain.Relationships;
import com.ruoyi.system.service.IRelationshipsService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 关系信息Controller
 * 
 * @author rcz
 * @date 2024-05-27
 */
@RestController
@RequestMapping("/person/relationships")
public class RelationshipsController extends BaseController
{
    @Autowired
    private IRelationshipsService relationshipsService;

    /**
     * 查询关系信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:list')")
    @GetMapping("/list")
    public AjaxResult list(Relationships relationships)
    {
        Map<Long,List<Relationships>> ships = relationshipsService.selectRelationshipsList(relationships);
        return success(ships);
    }

    /**
     * 导出关系信息列表
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:export')")
    @Log(title = "关系信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Relationships relationships)
    {
        Map<Long,List<Relationships>> list = relationshipsService.selectRelationshipsList(relationships);
        ExcelUtil<Relationships> util = new ExcelUtil<Relationships>(Relationships.class);
//        util.exportExcel(response, , "关系信息数据");
    }

    /**
     * 获取关系信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:query')")
    @GetMapping(value = "/{relationshipID}")
    public AjaxResult getInfo(@PathVariable("relationshipID") Long relationshipID)
    {
        return success(relationshipsService.selectRelationshipsByRelationshipID(relationshipID));
    }

    /**
     * 新增关系信息
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:add')")
    @Log(title = "关系信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Relationships relationships)
    {
        return toAjax(relationshipsService.insertRelationships(relationships));
    }

    /**
     * 修改关系信息
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:edit')")
    @Log(title = "关系信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Relationships relationships)
    {
        return toAjax(relationshipsService.updateRelationships(relationships));
    }

    /**
     * 删除关系信息
     */
    @PreAuthorize("@ss.hasPermi('person:relationships:remove')")
    @Log(title = "关系信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationshipIDs}")
    public AjaxResult remove(@PathVariable Long[] relationshipIDs)
    {
        return toAjax(relationshipsService.deleteRelationshipsByRelationshipIDs(relationshipIDs));
    }
}
