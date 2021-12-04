<template>
  <el-dialog :visible.sync="visible"   :close-on-click-modal="false" :close-on-press-escape="false" width="700px" top="9.5px">
    <el-card>
      <el-form :model="dataForm" ref="dataForm" :label-width="'200px'">
        <el-form-item label="Name" prop="type">
          <el-input v-model="dataForm.name"></el-input>
        </el-form-item>
<!--        -->
        <el-form-item label="StudentID" prop="type">
          <el-input v-model="dataForm.studentId"></el-input>
        </el-form-item>
<!--        -->
        <el-form-item label="Module And Level Of Study" prop="mid">
          <el-input v-model="dataForm.fileName"></el-input>
        </el-form-item>
        <el-form-item label="Overall Grade" prop="stuId">
          <el-input v-model="dataForm.overallGrade"></el-input>
        </el-form-item>
      </el-form>
      <el-table v-loading="dataListLoading" :data="dataList" border style="width: 100%;">
        <el-table-column prop="subjectName" label="Assessment Title" header-align="center" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column prop="credit" label="Credit" header-align="center" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column prop="gradeName" label="Final Grade" header-align="center" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-if="scope.row.gradeName === null" size="small" type="danger">-</el-tag>
            <el-tag v-else size="small" type="success">{{ scope.row.gradeName }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
<!--      <el-pagination-->
<!--        :current-page="page"-->
<!--        :page-sizes="[10, 20, 50, 100]"-->
<!--        :page-size="limit"-->
<!--        :total="total"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        @size-change="pageSizeChangeHandle"-->
<!--        @current-change="pageCurrentChangeHandle">-->
<!--      </el-pagination>-->
    </el-card>
    <template slot="footer">
<!--      <el-button @click="visible = false">close</el-button>-->
    </template>
  </el-dialog>
</template>

<script>
export default {
  name: 'grade-info',
  data () {
    return {
      visible: false,
      dataForm: {
        id: '',
        name: ''
      },
      // 设置属性
      mixinViewModuleOptions: {
        createdIsNeed: true,
        getDataListURL: 'grade/page',
        getDataListIsPage: true
      },
      dataList: [],
      order: '',
      orderField: '',
      page: 1,
      limit: 20,
      total: 0,
      dataListLoading: false,
      dataListSelections: []
    }
  },
  methods: {
    init () {
      this.visible = true
      this.$nextTick(() => {
        if (this.dataForm.id) {
          this.getInfo()
          this.query()
        }
      })
    },
    getInfo () {
      this.$ajax.get(`/studentInfo/${this.dataForm.id}`).then(({ data: res }) => {
        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
        this.dataForm.name = this.dataForm.lastName + ' ' + this.dataForm.firstName
      }).catch(() => {})
    },
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
    }
  }
}
</script>

<style scoped>

</style>
