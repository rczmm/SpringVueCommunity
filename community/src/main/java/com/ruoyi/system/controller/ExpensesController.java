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
import com.ruoyi.system.domain.Expenses;
import com.ruoyi.system.service.IExpensesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用管理Controller
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/Expense/expenses")
public class ExpensesController extends BaseController
{
    @Autowired
    private IExpensesService expensesService;

    /**
     * 查询费用管理列表
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:list')")
    @GetMapping("/list")
    public TableDataInfo list(Expenses expenses)
    {
        startPage();
        List<Expenses> list = expensesService.selectExpensesList(expenses);
        return getDataTable(list);
    }

    /**
     * 导出费用管理列表
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:export')")
    @Log(title = "费用管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Expenses expenses)
    {
        List<Expenses> list = expensesService.selectExpensesList(expenses);
        ExcelUtil<Expenses> util = new ExcelUtil<Expenses>(Expenses.class);
        util.exportExcel(response, list, "费用管理数据");
    }

    /**
     * 获取费用管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:query')")
    @GetMapping(value = "/{expenseID}")
    public AjaxResult getInfo(@PathVariable("expenseID") Long expenseID)
    {
        return success(expensesService.selectExpensesByExpenseID(expenseID));
    }

    /**
     * 新增费用管理
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:add')")
    @Log(title = "费用管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Expenses expenses)
    {
        return toAjax(expensesService.insertExpenses(expenses));
    }

    /**
     * 修改费用管理
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:edit')")
    @Log(title = "费用管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Expenses expenses)
    {
        return toAjax(expensesService.updateExpenses(expenses));
    }

    /**
     * 删除费用管理
     */
    @PreAuthorize("@ss.hasPermi('Expense:expenses:remove')")
    @Log(title = "费用管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{expenseIDs}")
    public AjaxResult remove(@PathVariable Long[] expenseIDs)
    {
        return toAjax(expensesService.deleteExpensesByExpenseIDs(expenseIDs));
    }
}
