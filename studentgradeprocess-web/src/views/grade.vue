<template>
  <div class="grade-wrap">
    <H1>Student List</H1>
  <el-card shadow="never" class="aui-card--fill">
    <div class="left">
      <el-form :inline="true" :model="dataForm" label-width="160px" label-position="right">
        <el-form-item>
          <el-input v-model="dataForm.lastName" placeholder="Last Name" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input  v-model="dataForm.studentId" placeholder="Student ID" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="getDataList()">search</el-button>
        </el-form-item>
        <el-upload
          :show-file-list="false"
          :before-upload="beforeUpload"
          :on-success="onSuccess"
          :on-error="onError"
          :disabled="importDisabled"
          style="display: inline-flex;margin-right: 10px;"
          :action="importUrl">
          <el-button :disabled="importDisabled" type="success">import</el-button>
        </el-upload>
      </el-form>
    </div>
      <el-table v-loading="dataListLoading" :data="dataList" border style="width: 100%;" :header-cell-style="{background:'#f5f7fa',color:'#7f8ea7'}">
        <el-table-column fixed prop="lastName" label="Last Name" header-align="center" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column prop="firstName" label="First Name" header-align="center" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentId" label="Student ID" header-align="center" align="center"></el-table-column>
        <el-table-column label="Detail" fixed="right" header-align="center" align="center" width="150">
          <template slot-scope="scope">
            <el-button @click="seeHandle(scope.row.id)" type="text" size="small">Detail Grade</el-button>
          </template>
        </el-table-column>
      </el-table>
    <el-pagination
      :current-page="page"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="limit"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="pageSizeChangeHandle"
      @current-change="pageCurrentChangeHandle">
    </el-pagination>
    <!-- 弹窗-->
    <grade-info v-if="seeVisible" ref="gradeInfo" @refreshDataList="getDataList"></grade-info>
  </el-card>
  </div>
</template>

<script>
import GradeInfo from './grade-info'
export default {
  components: {GradeInfo},
  data () {
    return {
      dataForm: {
        lastName: '',
        studentId: ''
      },
      // 设置属性
      mixinViewModuleOptions: {
        createdIsNeed: true,
        getDataListURL: 'studentInfo/page',
        getDataListIsPage: true
      },
      dataList: [],
      order: '',
      orderField: '',
      page: 1,
      limit: 10,
      total: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      seeVisible: false,
      importDisabled: false,
      importUrl: `api/grade/import`
    }
  },
  created () {
    if (this.mixinViewModuleOptions.createdIsNeed) {
      this.query()
    }
  },
  methods: {
    // 获取数据列表
    query () {
      this.dataListLoading = true
      this.$ajax.get(
        this.mixinViewModuleOptions.getDataListURL,
        {
          params: {
            order: this.order,
            orderField: this.orderField,
            page: this.mixinViewModuleOptions.getDataListIsPage ? this.page : null,
            limit: this.mixinViewModuleOptions.getDataListIsPage ? this.limit : null,
            ...this.dataForm
          }
        }
      ).then(({ data: res }) => {
        this.dataListLoading = false
        if (res.code !== 0) {
          this.dataList = []
          this.total = 0
          return this.$message.error(res.msg)
        }
        this.dataList = this.mixinViewModuleOptions.getDataListIsPage ? res.data.list : res.data
        console.log(this.dataList)
        this.total = this.mixinViewModuleOptions.getDataListIsPage ? res.data.total : 0
      }).catch(() => {
        this.dataListLoading = false
      })
    },
    // 分页, 每页条数
    pageSizeChangeHandle (val) {
      this.page = 1
      this.limit = val
      this.query()
    },
    // 分页, 当前页
    pageCurrentChangeHandle (val) {
      this.page = val
      this.query()
    },
    getDataList: function () {
      this.page = 1
      this.query()
    },
    seeHandle (id) {
      this.$router.push({name: 'studentInfo', query: {id: id}})
    },
    // 导入文件失败后回调
    onError () {
      this.importDataText = '导入数据'
      this.importDataIcon = 'el-icon-upload2'
      this.importDisabled = false
      this.query()
      this.$message.error('import error！')
    },
    // 导入文件成功后回调
    onSuccess () {
      // 成功后文本修改为原来的导入数据
      this.importDataText = '导入数据'
      // 图标修改
      this.importDataIcon = 'el-icon-upload2'
      // 将上传组件改为允许使用
      this.importDisabled = false
      // 调用刷新数据的方法
      this.query()
      // message 弹出消息
      this.$message.success('import success！')
    },
    // 上传文件调用
    beforeUpload () {
      // 将文本修改为正在导入
      this.importDataText = '正在导入'
      // 修改其图标
      this.importDataIcon = 'el-icon-loading'
      // 将其上传组件暂时禁用
      this.importDisabled = true
    }
  }
}
</script>

<style scoped>
  .left{
    text-align: left
  }
  .el-table .row-notactive {
    background: #5d993c;
  }
</style>
