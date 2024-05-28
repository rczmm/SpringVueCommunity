import request from '@/utils/request'

// 查询人口信息列表
export function listResidents(query) {
  return request({
    url: '/person/residents/list',
    method: 'get',
    params: query
  })
}

// 查询人口信息详细
export function getResidents(residentID) {
  return request({
    url: '/person/residents/' + residentID,
    method: 'get'
  })
}

// 新增人口信息
export function addResidents(data) {
  return request({
    url: '/person/residents',
    method: 'post',
    data: data
  })
}

// 修改人口信息
export function updateResidents(data) {
  return request({
    url: '/person/residents',
    method: 'put',
    data: data
  })
}

// 删除人口信息
export function delResidents(residentID) {
  return request({
    url: '/person/residents/' + residentID,
    method: 'delete'
  })
}
