import request from '@/utils/request'

// 查询家庭信息列表
export function listFamilies(query) {
  return request({
    url: '/person/families/list',
    method: 'get',
    params: query
  })
}

// 查询家庭信息详细
export function getFamilies(familyID) {
  return request({
    url: '/person/families/' + familyID,
    method: 'get'
  })
}

// 新增家庭信息
export function addFamilies(data) {
  return request({
    url: '/person/families',
    method: 'post',
    data: data
  })
}

// 修改家庭信息
export function updateFamilies(data) {
  return request({
    url: '/person/families',
    method: 'put',
    data: data
  })
}

// 删除家庭信息
export function delFamilies(familyID) {
  return request({
    url: '/person/families/' + familyID,
    method: 'delete'
  })
}
