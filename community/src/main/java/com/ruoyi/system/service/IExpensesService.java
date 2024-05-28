package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Expenses;

/**
 * 费用管理Service接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface IExpensesService 
{
    /**
     * 查询费用管理
     * 
     * @param expenseID 费用管理主键
     * @return 费用管理
     */
    public Expenses selectExpensesByExpenseID(Long expenseID);

    /**
     * 查询费用管理列表
     * 
     * @param expenses 费用管理
     * @return 费用管理集合
     */
    public List<Expenses> selectExpensesList(Expenses expenses);

    /**
     * 新增费用管理
     * 
     * @param expenses 费用管理
     * @return 结果
     */
    public int insertExpenses(Expenses expenses);

    /**
     * 修改费用管理
     * 
     * @param expenses 费用管理
     * @return 结果
     */
    public int updateExpenses(Expenses expenses);

    /**
     * 批量删除费用管理
     * 
     * @param expenseIDs 需要删除的费用管理主键集合
     * @return 结果
     */
    public int deleteExpensesByExpenseIDs(Long[] expenseIDs);

    /**
     * 删除费用管理信息
     * 
     * @param expenseID 费用管理主键
     * @return 结果
     */
    public int deleteExpensesByExpenseID(Long expenseID);
}
