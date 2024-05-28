import request from '@/utils/request'

// 查询活动报名列表
export function listRegistrations(query) {
  return request({
    url: '/event/registrations/list',
    method: 'get',
    params: query
  })
}

// 查询活动报名详细
export function getRegistrations(registrationID) {
  return request({
    url: '/event/registrations/' + registrationID,
    method: 'get'
  })
}

// 新增活动报名
export function addRegistrations(data) {
  return request({
    url: '/event/registrations',
    method: 'post',
    data: data
  })
}

// 修改活动报名
export function updateRegistrations(data) {
  return request({
    url: '/event/registrations',
    method: 'put',
    data: data
  })
}

// 删除活动报名
export function delRegistrations(registrationID) {
  return request({
    url: '/event/registrations/' + registrationID,
    method: 'delete'
  })
}
