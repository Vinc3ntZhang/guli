import request from '@/utils/request'

export default {

  // 1、根据课程id获取章节和小节数据列表
  getAllChapter(courseId) {
    return request({
      url: `/serviceedu/chapter/getChapterVideo/${courseId}`,
      method: 'get'
    })
  },
  // 2、添加章节
  addChapter(eduChapter) {
    return request({
      url: `/serviceedu/chapter/addChapter`,
      method: 'post',
      data: eduChapter
    })
  },
  // 3、根据id查询章节
  getChapter(chapterId) {
    return request({
      url: '/serviceedu/chapter/getChapterInfo/' + chapterId,
      method: 'get'
    })
  },
  // 4、修改章节
  updateChapter(eduChapter) {
    return request({
      url: `/serviceedu/chapter/updateChapter`,
      method: 'post',
      data: eduChapter
    })
  },
  // 5、删除章节
  deleteChapter(chapterId) {
    return request({
      url: `/serviceedu/chapter/` + chapterId,
      method: 'delete'
    })
  }

}
