import request from '@/utils/request'

export default {

  // 1、课程分类列表
  getAllSubject() {
    return request({
      url: `/serviceedu/subject/getAllSubject`,
      method: 'get'
    })
  }

}
