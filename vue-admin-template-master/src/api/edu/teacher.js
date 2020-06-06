import request from '@/utils/request'

export default {

  // 1、讲师列表
  // current当前页 limit每页记录数 teacherQuery条件对象
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      url: `/serviceedu/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      // teacherQuery条件对象，后端使用RequestBody获取数据
      // data表示将对象转换json进行传递到接口里面
      data: teacherQuery
    })
  },

  // 2、根据ID删除讲师
  deleteTeacherById(id) {
    return request({
      url: `/serviceedu/teacher/removeTeacherById/${id}`,
      method: 'delete'
    })
  },

  // 3、添加讲师
  addTeacher(eduTeacher) {
    return request({
      url: '/serviceedu/teacher/addTeacher',
      method: 'post',
      data: eduTeacher
    })
  },

  // 4、根据ID查询讲师
  queryTeacherById(id) {
    return request({
      url: `/serviceedu/teacher/getTeacherById/${id}`,
      method: 'get'
    })
  },

  // 5、修改讲师
  updateTeacherById(teacher) {
    return request({
      url: `/serviceedu/teacher/updateTeacher`,
      method: 'post',
      data: teacher
    })
  }

}
