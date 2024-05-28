import request from '@/utils/request'

// 查询缴费记录列表
export function listPaymentrecords(query) {
  return request({
    url: '/Expense/paymentrecords/list',
    method: 'get',
    params: query
  })
}

// 查询缴费记录详细
export function getPaymentrecords(recordID) {
  return request({
    url: '/Expense/paymentrecords/' + recordID,
    method: 'get'
  })
}

// 新增缴费记录
export function addPaymentrecords(data) {
  return request({
    url: '/Expense/paymentrecords',
    method: 'post',
    data: data
  })
}

// 修改缴费记录
export function updatePaymentrecords(data) {
  return request({
    url: '/Expense/paymentrecords',
    method: 'put',
    data: data
  })
}

// 删除缴费记录
export function delPaymentrecords(recordID) {
  return request({
    url: '/Expense/paymentrecords/' + recordID,
    method: 'delete'
  })
}
