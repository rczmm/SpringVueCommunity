import request from '@/utils/request'

// 查询关系信息列表
export function listRelationships(query) {
  return request({
    url: '/person/relationships/list',
    method: 'get',
    params: query
  })
}

// 查询关系信息详细
export function getRelationships(relationshipID) {
  return request({
    url: '/person/relationships/' + relationshipID,
    method: 'get'
  })
}

// 新增关系信息
export function addRelationships(data) {
  return request({
    url: '/person/relationships',
    method: 'post',
    data: data
  })
}

// 修改关系信息
export function updateRelationships(data) {
  return request({
    url: '/person/relationships',
    method: 'put',
    data: data
  })
}

// 删除关系信息
export function delRelationships(relationshipID) {
  return request({
    url: '/person/relationships/' + relationshipID,
    method: 'delete'
  })
}
