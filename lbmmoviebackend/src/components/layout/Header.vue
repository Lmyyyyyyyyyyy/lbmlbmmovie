<template>
  <div class="header">
    <div class="a">
      <div class="header-top">
        <el-dropdown>
          <el-button type="text">
            <div class="header-name">admin</div>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-button type="text" @click="handleLogout">
                <i style="font-size: 15px; padding-right: 3px" class="el-icon-switch-button"></i>退出登录
              </el-button>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

      <div class="header-icon" style="padding-top: 16px">
        <el-dropdown>
          <el-badge value="new" class="item">
            <i style="font-size: 18px" class="el-icon-bell"></i>
          </el-badge>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="item in list" :key="item">{{ item.content }}</el-dropdown-item>
            <el-dropdown-item v-if="list.length === 0">今日工作还没有上传</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="header-icon">
        <el-tooltip content="lbmmovie - 管理系统" placement="top">
          <i style="font-size: 20px" class="el-icon-warning-outline"></i>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Header",

  data() {
    return {
      list: []
    }
  },

  methods: {

    handleLogout() {
      localStorage.removeItem("token")
      localStorage.removeItem("role")
      localStorage.removeItem("wid")
      this.$router.push("/login")
    }

  },

}
</script>

<style scoped>

.header {

}

.header-icon {
  float: right;
  font-size: 20px;
  padding-top: 19px;
  padding-right: 30px;
}

.header-top {
  float: right;
  padding-left: 20px;
}

.header-name {
  float: right;
  padding-top: 12px;
  font-size: 15px;
  padding-right: 10px;
  padding-left: 9px;
}

.el-button {
  padding-top: 8px;
  color: #000000;
  letter-spacing: 2px;
}
</style>
