package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ExpensesMapper;
import com.ruoyi.system.domain.Expenses;
import com.ruoyi.system.service.IExpensesService;

/**
 * 费用管理Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class ExpensesServiceImpl implements IExpensesService 
{
    @Autowired
    private ExpensesMapper expensesMapper;

    /**
     * 查询费用管理
     * 
     * @param expenseID 费用管理主键
     * @return 费用管理
     */
    @Override
    public Expenses selectExpensesByExpenseID(Long expenseID)
    {
        return expensesMapper.selectExpensesByExpenseID(expenseID);
    }

    /**
     * 查询费用管理列表
     * 
     * @param expenses 费用管理
     * @return 费用管理
     */
    @Override
    public List<Expenses> selectExpensesList(Expenses expenses)
    {
        return expensesMapper.selectExpensesList(expenses);
    }

    /**
     * 新增费用管理
     * 
     * @param expenses 费用管理
     * @return 结果
     */
    @Override
    public int insertExpenses(Expenses expenses)
    {
        return expensesMapper.insertExpenses(expenses);
    }

    /**
     * 修改费用管理
     * 
     * @param expenses 费用管理
     * @return 结果
     */
    @Override
    public int updateExpenses(Expenses expenses)
    {
        return expensesMapper.updateExpenses(expenses);
    }

    /**
     * 批量删除费用管理
     * 
     * @param expenseIDs 需要删除的费用管理主键
     * @return 结果
     */
    @Override
    public int deleteExpensesByExpenseIDs(Long[] expenseIDs)
    {
        return expensesMapper.deleteExpensesByExpenseIDs(expenseIDs);
    }

    /**
     * 删除费用管理信息
     * 
     * @param expenseID 费用管理主键
     * @return 结果
     */
    @Override
    public int deleteExpensesByExpenseID(Long expenseID)
    {
        return expensesMapper.deleteExpensesByExpenseID(expenseID);
    }
}
