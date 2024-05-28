import request from '@/utils/request'

// 查询处理记录列表
export function listHandlingrecords(query) {
  return request({
    url: '/Complaint/handlingrecords/list',
    method: 'get',
    params: query
  })
}

// 查询处理记录详细
export function getHandlingrecords(recordID) {
  return request({
    url: '/Complaint/handlingrecords/' + recordID,
    method: 'get'
  })
}

// 新增处理记录
export function addHandlingrecords(data) {
  return request({
    url: '/Complaint/handlingrecords',
    method: 'post',
    data: data
  })
}

// 修改处理记录
export function updateHandlingrecords(data) {
  return request({
    url: '/Complaint/handlingrecords',
    method: 'put',
    data: data
  })
}

// 删除处理记录
export function delHandlingrecords(recordID) {
  return request({
    url: '/Complaint/handlingrecords/' + recordID,
    method: 'delete'
  })
}
