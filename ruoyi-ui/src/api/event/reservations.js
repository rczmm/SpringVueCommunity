import request from '@/utils/request'

// 查询活动预约列表
export function listReservations(query) {
  return request({
    url: '/event/reservations/list',
    method: 'get',
    params: query
  })
}

// 查询活动预约详细
export function getReservations(reservationID) {
  return request({
    url: '/event/reservations/' + reservationID,
    method: 'get'
  })
}

// 新增活动预约
export function addReservations(data) {
  return request({
    url: '/event/reservations',
    method: 'post',
    data: data
  })
}

// 修改活动预约
export function updateReservations(data) {
  return request({
    url: '/event/reservations',
    method: 'put',
    data: data
  })
}

// 删除活动预约
export function delReservations(reservationID) {
  return request({
    url: '/event/reservations/' + reservationID,
    method: 'delete'
  })
}
