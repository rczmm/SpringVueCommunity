import request from '@/utils/request'

// 查询报修管理列表
export function listMaintenancerequests(query) {
  return request({
    url: '/Maintenance/maintenancerequests/list',
    method: 'get',
    params: query
  })
}

// 查询报修管理详细
export function getMaintenancerequests(requestID) {
  return request({
    url: '/Maintenance/maintenancerequests/' + requestID,
    method: 'get'
  })
}

// 新增报修管理
export function addMaintenancerequests(data) {
  return request({
    url: '/Maintenance/maintenancerequests',
    method: 'post',
    data: data
  })
}

// 修改报修管理
export function updateMaintenancerequests(data) {
  return request({
    url: '/Maintenance/maintenancerequests',
    method: 'put',
    data: data
  })
}

// 删除报修管理
export function delMaintenancerequests(requestID) {
  return request({
    url: '/Maintenance/maintenancerequests/' + requestID,
    method: 'delete'
  })
}
