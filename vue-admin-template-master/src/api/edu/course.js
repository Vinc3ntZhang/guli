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
  },
  // 5、课程确认信息显示
  getPublishCourseInfo(id) {
    return request({
      url: `/serviceedu/course/getPublishCourseInfo/${id}`,
      method: 'get'
    })
  },
  // 6、课程最终发布
  publishCourse(id) {
    return request({
      url: `/serviceedu/course/publishCourse/${id}`,
      method: 'post'
    })
  },
  // 课程列表
  getListCourse(current, limit, courseQuery) {
    return request({
      url: `/serviceedu/course/courseList/${current}/${limit}`,
      method: 'post',
      data: courseQuery
    })
  },
  // 删除课程
  removeCourse(courseId) {
    return request({
      url: `/serviceedu/course/${courseId}`,
      method: 'delete'
    })
  }

}
