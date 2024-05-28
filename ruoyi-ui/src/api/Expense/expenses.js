import request from '@/utils/request'

// 查询费用管理列表
export function listExpenses(query) {
  return request({
    url: '/Expense/expenses/list',
    method: 'get',
    params: query
  })
}

// 查询费用管理详细
export function getExpenses(expenseID) {
  return request({
    url: '/Expense/expenses/' + expenseID,
    method: 'get'
  })
}

// 新增费用管理
export function addExpenses(data) {
  return request({
    url: '/Expense/expenses',
    method: 'post',
    data: data
  })
}

// 修改费用管理
export function updateExpenses(data) {
  return request({
    url: '/Expense/expenses',
    method: 'put',
    data: data
  })
}

// 删除费用管理
export function delExpenses(expenseID) {
  return request({
    url: '/Expense/expenses/' + expenseID,
    method: 'delete'
  })
}
