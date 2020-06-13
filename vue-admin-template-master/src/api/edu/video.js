import request from '@/utils/request'

export default {

  // 1、添加小节
  addVideo(eduVideo) {
    return request({
      url: `/serviceedu/video/addVideo`,
      method: 'post',
      data: eduVideo
    })
  },
  // 2、修改小节
  updateVideo(eduVideo) {
    return request({
      url: `/serviceedu/video/updateVideo`,
      method: 'post',
      data: eduVideo
    })
  },
  // 3、删除小节
  deleteVideo(videoId) {
    return request({
      url: `/serviceedu/video/${videoId}`,
      method: 'delete'
    })
  },
  // 4、根据小节id查询
  getVideoInfo(videoId) {
    return request({
      url: `/serviceedu/video/getVideoInfo/${videoId}`,
      method: 'get'
    })
  }

}
