import request from '@/utils/request'

// 查询活动列表
export function listEvents(query) {
  return request({
    url: '/event/events/list',
    method: 'get',
    params: query
  })
}

// 查询活动详细
export function getEvents(eventID) {
  return request({
    url: '/event/events/' + eventID,
    method: 'get'
  })
}

// 新增活动
export function addEvents(data) {
  return request({
    url: '/event/events',
    method: 'post',
    data: data
  })
}

// 修改活动
export function updateEvents(data) {
  return request({
    url: '/event/events',
    method: 'put',
    data: data
  })
}

// 删除活动
export function delEvents(eventID) {
  return request({
    url: '/event/events/' + eventID,
    method: 'delete'
  })
}
