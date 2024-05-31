package com.ruoyi.system.task;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.Expenses;
import com.ruoyi.system.service.IExpensesService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Component("ExpensesTask")
public class ExpensesTask {

    private final IExpensesService expensesService;
    private final ISysUserService sysUserService;

    @Autowired
    public ExpensesTask(IExpensesService expensesService, ISysUserService sysUserService) {
        this.expensesService = expensesService;
        this.sysUserService = sysUserService;
    }

    public void runMonth() {
        List<SysUser> sysUsers = sysUserService.selectUserList(new SysUser());
        Random random = new Random();
        for (SysUser sysUser : sysUsers) {
            Expenses expense1 = new Expenses("水费", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            Expenses expense2 = new Expenses("电费", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            Expenses expense3 = new Expenses("燃气费", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            Expenses expense4 = new Expenses("物业费", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            Expenses expense5 = new Expenses("垃圾处理费", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            Expenses expense6 = new Expenses("其他费用", BigDecimal.valueOf(random.nextDouble() * (400 - 10) + 10).setScale(2, RoundingMode.HALF_UP), "未缴费", sysUser.getUserId());
            expensesService.insertExpenses(expense1);
            expensesService.insertExpenses(expense2);
            expensesService.insertExpenses(expense3);
            expensesService.insertExpenses(expense4);
            expensesService.insertExpenses(expense5);
            expensesService.insertExpenses(expense6);
        }
    }
}
