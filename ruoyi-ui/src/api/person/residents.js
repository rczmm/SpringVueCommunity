import request from '@/utils/request'

// 查询居民信息列表
export function listResidents(query) {
  return request({
    url: '/person/residents/list',
    method: 'get',
    params: query
  })
}

// 查询居民信息详细
export function getResidents(residentID) {
  return request({
    url: '/person/residents/' + residentID,
    method: 'get'
  })
}

// 新增居民信息
export function addResidents(data) {
  return request({
    url: '/person/residents',
    method: 'post',
    data: data
  })
}

// 修改居民信息
export function updateResidents(data) {
  return request({
    url: '/person/residents',
    method: 'put',
    data: data
  })
}

// 删除居民信息
export function delResidents(residentID) {
  return request({
    url: '/person/residents/' + residentID,
    method: 'delete'
  })
}
