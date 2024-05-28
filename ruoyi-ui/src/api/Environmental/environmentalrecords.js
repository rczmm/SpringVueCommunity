import request from '@/utils/request'

// 查询环境卫生列表
export function listEnvironmentalrecords(query) {
  return request({
    url: '/Environmental/environmentalrecords/list',
    method: 'get',
    params: query
  })
}

// 查询环境卫生详细
export function getEnvironmentalrecords(recordID) {
  return request({
    url: '/Environmental/environmentalrecords/' + recordID,
    method: 'get'
  })
}

// 新增环境卫生
export function addEnvironmentalrecords(data) {
  return request({
    url: '/Environmental/environmentalrecords',
    method: 'post',
    data: data
  })
}

// 修改环境卫生
export function updateEnvironmentalrecords(data) {
  return request({
    url: '/Environmental/environmentalrecords',
    method: 'put',
    data: data
  })
}

// 删除环境卫生
export function delEnvironmentalrecords(recordID) {
  return request({
    url: '/Environmental/environmentalrecords/' + recordID,
    method: 'delete'
  })
}
