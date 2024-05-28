import request from '@/utils/request'

// 查询公告管理列表
export function listAnnouncements(query) {
  return request({
    url: '/Announcement/announcements/list',
    method: 'get',
    params: query
  })
}

// 查询公告管理详细
export function getAnnouncements(announcementID) {
  return request({
    url: '/Announcement/announcements/' + announcementID,
    method: 'get'
  })
}

// 新增公告管理
export function addAnnouncements(data) {
  return request({
    url: '/Announcement/announcements',
    method: 'post',
    data: data
  })
}

// 修改公告管理
export function updateAnnouncements(data) {
  return request({
    url: '/Announcement/announcements',
    method: 'put',
    data: data
  })
}

// 删除公告管理
export function delAnnouncements(announcementID) {
  return request({
    url: '/Announcement/announcements/' + announcementID,
    method: 'delete'
  })
}
