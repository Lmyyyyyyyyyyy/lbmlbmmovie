<template>
  <div>
    <h3 style="letter-spacing: 1px;font-weight: 400;padding-bottom: 20px;color:#febd6e;">基本设置</h3>

    <div>
      <el-form style="width: 750px;margin-left: 50px;float: left" label-position="top" ref="form" :model="user" label-width="80px">
        <el-form-item style="padding: 0" label="昵称">
          <el-input v-model="user.nickname"></el-input>
        </el-form-item>
        <el-form-item style="padding: 0" label="密码">
          <el-input type="password" v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="user.gender">
            <el-radio label="男生"></el-radio>
            <el-radio label="女生"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生年月">
          <el-col>
            <el-date-picker
                type="date" placeholder="选择日期"
                v-model="user.birthday"
                value-format="yyyy-MM-dd"
                style="width: 100%;"/>
          </el-col>
        </el-form-item>
        <el-form-item style="padding: 0" label="邮箱">
          <el-input type="email" v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input :rows="5" type="textarea" v-model="user.info"></el-input>
        </el-form-item>
        <el-form-item style="padding-top: 20px">
          <el-button type="primary" @click="onSubmit" class="el-form-item-button">更新基本信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {findById, updateUser} from "@/api/user";
import config from "@/config";

export default {

  data() {
    return {
      header: {
        "Authorization": localStorage.getItem("token")
      },
      uploadAction: config.API_URL + '/upload',
      user: {
        nickname: '',
        password: '',
        email: '',
        birthday: '',
        gender: '',
        info: '',
      }
    }
  },

  mounted() {
    if (localStorage.getItem("uid") !== null) {
      findById(localStorage.getItem("uid")).then(res => {
        this.user = res.data;
      })
    }
  },

  methods: {

    onSubmit() {
      updateUser(this.user).then(res => {
        if (res.success) {
          this.user = res.data;
          this.$message({
            type: 'success',
            message: '用户基本信息更新成功!'
          });
        }
      })
    },

    handleUploadSuccess(res) {
      this.user.avatar = res;
      updateUser(this.user).then(res => {
        if (res.success) {
          this.user = res.data;
          this.$message({
            type: 'success',
            message: '头像上传成功!'
          });
        }
      })
    }

  },

}

</script>

<style scoped>
>>> .el-form--label-top .el-form-item__label {
  padding: 0;
}

.el-form-item {
  margin-bottom: 5px;
}


.el-form-item-button{
  background-color:#febd6e ;
}
</style>