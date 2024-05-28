import request from '@/utils/request'

// 查询维修记录列表
export function listMaintenancerecords(query) {
  return request({
    url: '/Maintenance/maintenancerecords/list',
    method: 'get',
    params: query
  })
}

// 查询维修记录详细
export function getMaintenancerecords(recordID) {
  return request({
    url: '/Maintenance/maintenancerecords/' + recordID,
    method: 'get'
  })
}

// 新增维修记录
export function addMaintenancerecords(data) {
  return request({
    url: '/Maintenance/maintenancerecords',
    method: 'post',
    data: data
  })
}

// 修改维修记录
export function updateMaintenancerecords(data) {
  return request({
    url: '/Maintenance/maintenancerecords',
    method: 'put',
    data: data
  })
}

// 删除维修记录
export function delMaintenancerecords(recordID) {
  return request({
    url: '/Maintenance/maintenancerecords/' + recordID,
    method: 'delete'
  })
}
