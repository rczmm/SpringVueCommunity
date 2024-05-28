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
import com.ruoyi.system.domain.Paymentrecords;
import com.ruoyi.system.service.IPaymentrecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 缴费记录Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Expense/paymentrecords")
public class PaymentrecordsController extends BaseController
{
    @Autowired
    private IPaymentrecordsService paymentrecordsService;

    /**
     * 查询缴费记录列表
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(Paymentrecords paymentrecords)
    {
        startPage();
        List<Paymentrecords> list = paymentrecordsService.selectPaymentrecordsList(paymentrecords);
        return getDataTable(list);
    }

    /**
     * 导出缴费记录列表
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:export')")
    @Log(title = "缴费记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Paymentrecords paymentrecords)
    {
        List<Paymentrecords> list = paymentrecordsService.selectPaymentrecordsList(paymentrecords);
        ExcelUtil<Paymentrecords> util = new ExcelUtil<Paymentrecords>(Paymentrecords.class);
        util.exportExcel(response, list, "缴费记录数据");
    }

    /**
     * 获取缴费记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:query')")
    @GetMapping(value = "/{recordID}")
    public AjaxResult getInfo(@PathVariable("recordID") Long recordID)
    {
        return success(paymentrecordsService.selectPaymentrecordsByRecordID(recordID));
    }

    /**
     * 新增缴费记录
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:add')")
    @Log(title = "缴费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Paymentrecords paymentrecords)
    {
        return toAjax(paymentrecordsService.insertPaymentrecords(paymentrecords));
    }

    /**
     * 修改缴费记录
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:edit')")
    @Log(title = "缴费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Paymentrecords paymentrecords)
    {
        return toAjax(paymentrecordsService.updatePaymentrecords(paymentrecords));
    }

    /**
     * 删除缴费记录
     */
    @PreAuthorize("@ss.hasPermi('Expense:paymentrecords:remove')")
    @Log(title = "缴费记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIDs}")
    public AjaxResult remove(@PathVariable Long[] recordIDs)
    {
        return toAjax(paymentrecordsService.deletePaymentrecordsByRecordIDs(recordIDs));
    }
}
