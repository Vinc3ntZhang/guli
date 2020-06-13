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
  },
  // 3、根据课程id查询课程基本信息
  getCourseInfo(id) {
    return request({
      url: `/serviceedu/course/getCourseInfo/${id}`,
      method: 'get'
    })
  },
  // 4、修改课程信息
  updateCourseInfo(courseInfo) {
    return request({
      url: `/serviceedu/course/updateCourseInfo`,
      method: 'post',
      data: courseInfo
    })
  }

}
