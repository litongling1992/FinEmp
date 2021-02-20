<template>
  <div class="password">
    <LoginHeader>
      <el-form
        slot="container"
        ref="ruleForm"
        :rules="rules"
        :model="ruleForm"
        label-position="left"
        label-width="0px"
      >
        <div class="title">
          <h3>找回密码</h3>
        </div>
        <el-form-item prop="username">
          <el-input type="text" v-model="ruleForm.username" auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="ruleForm.email" auto-complete="off" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            :loading="loading"
            style="width: 100%"
            type="primary"
            class="form-confirm"
            @click.native.prevent="handleSubmit"
          >找回密码</el-button>
        </el-form-item>
      </el-form>
    </LoginHeader>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Provide } from "vue-property-decorator";
import LoginHeader from "@/views/Login/LoginHeader.vue";

@Component({
  components: {
    LoginHeader,
  },
})
export default class Password extends Vue {
  
  @Provide() loading: boolean = false;

  @Provide() rules = {
    username: [{ required: true, message: "请输入账号", trigger: "blur" }],
    email: [
      {
        required: true,
        message: "请输入邮箱",
        trigger: "blur",
      },
      {
        type: "email",
        message: "请输入正确的邮箱地址",
        trigger: "blur,change",
      },
    ],
  };

  @Provide() ruleForm: {
    userName: String;
    email: string;
  } = {
    userName: "",
    email: "",
  };

  /**提交Form表单
   * @description: 
   * @param {type} 
   * @return: 
   */
  handleSubmit(): void {
    (this.$refs["ruleForm"] as any).validate((valid: boolean) => {
      //如果表单验证通过
      if (valid) {
        this.loading = true;
        // 网络请求
        (this as any).$axios
          .post("/api/users/findPwd", this.ruleForm)
          .then((res: any) => {
            this.loading = false;
            // console.log(res.data);
            this.$message({
              message: res.data.msg,
              type: "success"
            });
            //返回登录页面
            this.$router.push("/login");
          })
          .catch(() => {
            this.loading = false;
          });
      }
    });
  }
}
</script>


<style lang="scss" scope>
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

i {
  font-size: 14px;
  margin-left: 8px;
}
.forget {
  float: right;
}
</style>