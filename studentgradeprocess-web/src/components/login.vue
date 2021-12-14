<template>
  <div class="login-wrap">
    <div>
      <el-card shadow="never" class="aui-card--fill" style="width:550px;">
        <div>
          <H1>STUDENT GRADE PROCESS</H1>
          <el-form ref="form" label-width="80px">
            <el-form-item label="Username：">
              <el-input v-model="dataForm.username" placeholder="please enter username"></el-input>
            </el-form-item>
            <el-form-item label="Password：">
              <el-input v-model="dataForm.password" type="password" placeholder="please enter password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="sign()">Log in</el-button>
              <el-button @click="updatePassWordVisible()">Update password</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
    <el-dialog :visible.sync="seeVisible" :title="'Change Password'" :close-on-click-modal="false" :close-on-press-escape="false">
      <el-card>
        <el-form :model="dataForm" ref="dataForm" :label-width="'200px'">
          <el-form-item label="Username">
            <el-input v-model="updatePasswordMap.userName"></el-input>
          </el-form-item>
          <el-form-item label="Old password">
            <el-input v-model="updatePasswordMap.oldPassWord"></el-input>
          </el-form-item>
          <el-form-item label="New password">
            <el-input v-model="updatePasswordMap.newPassWord"></el-input>
          </el-form-item>
        </el-form>
      </el-card>
      <template slot="footer">
        <el-button @click="seeVisible = false">cancel</el-button>
        <el-button type="primary" @click="updatePassWord()">commit</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<pass-word-update ref="updatePassword"></pass-word-update>
<script>
export default {
  data () {
    return {
      dataForm: {
        username: '',
        password: ''
      },
      updatePasswordMap: {
        userName: '',
        oldPassWord: '',
        newPassWord: ''
      },
      seeVisible: false
    }
  },
  watch: {
    'seeVisible' (nval) {
      if (nval === false) {
        this.updatePasswordMap = this.$options.data().updatePasswordMap
      }
    }
  },
  methods: {
    // 登陆
    sign () {
      this.$ajax.post(`/login`, {userName: this.dataForm.username, passWord: this.dataForm.password}).then(({ data: res }) => {
        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        if (res.data == null) {
          this.$router.push('grade')
        } else {
          this.$router.push({name: 'studentInfo', query: {id: res.data.studentInfoId}})
        }
      }).catch(() => {})
    },
    updatePassWordVisible () {
      this.seeVisible = true
    },
    updatePassWord () {
      this.$ajax.put('/login', this.updatePasswordMap).then(({ data: res }) => {
        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        this.seeVisible = false
        this.$message({
          message: 'success',
          type: 'success',
          duration: 500
        })
        console.log(123)
      }).catch(() => {})
    }
  }
}
</script>

<style scoped>
  .aui-card--fill{
    position: relative;
    left: 50%;
    margin-top: 250px;
    transform: translate(-50%);
    background: #f6d89a;
  }
  .login-wrap {
    width: 100%;
    height: 1000px;
    margin-top: -60px;
    background: url("../assets/login.jpg") no-repeat;
    background-size: cover;
    overflow: hidden;
  }
</style>
