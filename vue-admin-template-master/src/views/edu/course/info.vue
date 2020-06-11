<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form label-width="120px">
        <el-form-item label="课程标题">
          <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写" />
        </el-form-item>

        <!-- 所属分类 TODO -->
        <!-- 所属分类：级联下拉列表 -->
        <!-- 一级分类 -->
        <el-form-item label="课程分类">
          <el-select
            v-model="courseInfo.subjectParentId"
            placeholder="一级分类"
            @change="subjectLevelOneChanged">
            <el-option
              v-for="subject in subjectOneList"
              :key="subject.id"
              :label="subject.title"
              :value="subject.id"/>
          </el-select>
          <!-- 二级分类 -->
          <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
            <el-option
              v-for="subject in subjectTwoList"
              :key="subject.id"
              :label="subject.title"
              :value="subject.id"/>
          </el-select>
        </el-form-item>

        <!-- 课程讲师 TODO -->
        <!-- 课程讲师 -->
        <el-form-item label="课程讲师">
          <el-select
            v-model="courseInfo.teacherId"
            placeholder="请选择">
            <el-option
              v-for="teacher in teacherList"
              :key="teacher.id"
              :label="teacher.name"
              :value="teacher.id"/>
          </el-select>
        </el-form-item>

        <el-form-item label="总课时">
          <el-input-number
            :min="0"
            v-model="courseInfo.lessonNum"
            controls-position="right"
            placeholder="请填写课程的总课时数"
          />
        </el-form-item>

        <!-- 课程简介 TODO -->
        <el-form-item label="课程简介">
          <el-input v-model="courseInfo.description" placeholder=" " />
        </el-form-item>

        <!-- 课程封面 TODO -->
        <!-- 课程封面-->
        <el-form-item label="课程封面">

          <el-upload
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :action="BASE_API+'/eduoss/fileoss'"
            class="avatar-uploader">
            <img :src="courseInfo.cover">
          </el-upload>

        </el-form-item>

        <el-form-item label="课程价格">
          <el-input-number
            :min="0"
            v-model="courseInfo.price"
            controls-position="right"
            placeholder="免费课程请设置为0元"
          />元
        </el-form-item>

        <el-form-item>
          <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
        </el-form-item>
      </el-form>
    </el-form>
  </div>
</template>

<script>
import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
export default {
  data() {
    return {
      courseInfo: {
        title: '',
        subjectId: '', // 二级分类id
        subjectParentId: '', // 一级分类id
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: 'https://eddie-study.oss-cn-shenzhen.aliyuncs.com/2020/06/07/39fbd93ffffc4b53bae2046ddcbced9ffile.png',
        price: 0
      },
      teacherList: [], // 封装所有讲师
      subjectOneList: [], // 封装所有一级分类
      subjectTwoList: [], // 封装所有二级分类
      saveBtnDisabled: false, // 保存按钮是否禁用
      BASE_API: process.env.BASE_API // 获取dev.env.js里面的地址
    }
  },
  init() {

  },

  created() {
    // 初始化所有讲师
    this.getListTeacher()
    // 初始化一级分类
    this.getOneSubject()
  },

  methods: {
    next() {
      course.addCourseInfo(this.courseInfo).then(resopnse => {
        this.$message({
          type: 'success',
          message: '添加课程信息成功！'
        })
        this.$router.push({ path: '/course/chapter/' + resopnse.data.courseId })
      })
    },
    // 查询所有讲师
    getListTeacher() {
      course.getListTeacher().then(response => {
        this.teacherList = response.data.items
      })
    },
    // 查询所有的一级分类
    getOneSubject() {
      subject.getAllSubject().then(respose => {
        this.subjectOneList = respose.data.list
      })
    },
    // 点击摸个一级分类，触发change，显示对应二级分类
    subjectLevelOneChanged(value) {
      // value就是一级分类的id值
      // 遍历所有的分类，包含一级和二级
      for (var i = 0; i < this.subjectOneList.length; i++) {
        // 每一个一级分类
        var oneSubject = this.subjectOneList[i]
        // 判断：所有一级分类id和点击一级分类id是否一样
        if (value === oneSubject.id) {
          // 从一级分类中去二级分类
          this.subjectTwoList = oneSubject.children
          // 把二级分类id值清空
          this.courseInfo.subjectId = ''
        }
      }
    },
    // 上传封面成功调用方法
    handleAvatarSuccess(res, file) {
      console.log(res)// 上传响应
      console.log(URL.createObjectURL(file.raw))// base64编码
      this.courseInfo.cover = res.data.url
    },
    // 上传之前要调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
</style>
