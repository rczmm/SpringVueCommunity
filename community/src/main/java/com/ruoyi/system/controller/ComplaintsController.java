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
import com.ruoyi.system.domain.Complaints;
import com.ruoyi.system.service.IComplaintsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投诉管理Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Complaint/complaints")
public class ComplaintsController extends BaseController
{
    @Autowired
    private IComplaintsService complaintsService;

    /**
     * 查询投诉管理列表
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:list')")
    @GetMapping("/list")
    public TableDataInfo list(Complaints complaints)
    {
        startPage();
        List<Complaints> list = complaintsService.selectComplaintsList(complaints);
        return getDataTable(list);
    }

    /**
     * 导出投诉管理列表
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:export')")
    @Log(title = "投诉管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Complaints complaints)
    {
        List<Complaints> list = complaintsService.selectComplaintsList(complaints);
        ExcelUtil<Complaints> util = new ExcelUtil<Complaints>(Complaints.class);
        util.exportExcel(response, list, "投诉管理数据");
    }

    /**
     * 获取投诉管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:query')")
    @GetMapping(value = "/{complaintID}")
    public AjaxResult getInfo(@PathVariable("complaintID") Long complaintID)
    {
        return success(complaintsService.selectComplaintsByComplaintID(complaintID));
    }

    /**
     * 新增投诉管理
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:add')")
    @Log(title = "投诉管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Complaints complaints)
    {
        return toAjax(complaintsService.insertComplaints(complaints));
    }

    /**
     * 修改投诉管理
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:edit')")
    @Log(title = "投诉管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Complaints complaints)
    {
        return toAjax(complaintsService.updateComplaints(complaints));
    }

    /**
     * 删除投诉管理
     */
    @PreAuthorize("@ss.hasPermi('Complaint:complaints:remove')")
    @Log(title = "投诉管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{complaintIDs}")
    public AjaxResult remove(@PathVariable Long[] complaintIDs)
    {
        return toAjax(complaintsService.deleteComplaintsByComplaintIDs(complaintIDs));
    }
}
