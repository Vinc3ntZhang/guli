import request from '@/utils/request'

export default {

  // 1、添加课程
  addCourseInfo(courseInfo) {
    return request({
      url: `/serviceedu/course/addCourseInfo`,
      method: 'post',
      data: courseInfo
    })
  },
  // 2、查询所有讲师
  getListTeacher() {
    return request({
      url: '/serviceedu/teacher/findAllTeacher',
      method: 'get'
    })
  }

}
