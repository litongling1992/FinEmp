<template>
  <div class="login">
    <LoginHeader>
      <el-form
        slot="container"
        ref="loginForm"
        :rules="rules"
        :model="loginForm"
        label-position="left"
        label-width="0px"
      >
        <div class="title">
          <h3>用户注册</h3>
        </div>
        <el-form-item prop="username">
          <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="realname">
          <el-input type="text" v-model="loginForm.realname" auto-complete="off" placeholder="真实姓名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            auto-complete="off"
            placeholder="密码"
          ></el-input>
        </el-form-item>
         <el-form-item >					
             <el-input type="text" auto-complete="off" placeholder="验证码"></el-input>
             <img id="num" :src="srcUrl" />
             <a href="javascript:;" onclick="document.getElementById('num').src = 'image?'+(new Date()).getTime()">换一张</a>
        </el-form-item>

        <el-form-item label="性别" prop="type">
          <el-radio-group v-model="loginForm.sex">
            <el-radio label="男" name="type"></el-radio>
            <el-radio label="女" name="type"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button
            :loading="isLoding"
            style="width: 100%"
            type="primary"
            class="form-confirm"
            @click.native.prevent="handleSubmit"
          >注册</el-button>
        </el-form-item>
        <!-- 七天自动登录和忘记密码 -->
        <!-- <el-form-item>
          <el-checkbox
            style="float:left;"
            v-model="loginForm.autoLogin"
            :checked="loginForm.autoLogin"
          >七天自动登录</el-checkbox>
          <el-button type="text" class="forget" @click="$router.push('/password')">忘记密码?</el-button>
        </el-form-item>-->
      </el-form>
    </LoginHeader>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Provide } from "vue-property-decorator";
import { Getter, Action, State, Mutation } from "vuex-class";
import LoginHeader from "@/views/Login/LoginHeader.vue";

@Component({
  components: {
    LoginHeader,
  },
})
export default class Reginster extends Vue {
  //存储user
  @Action("setUser") setUser: any;

  @Provide() srcUrl:string="";

  @Provide() isLoding: boolean = false;

  @Provide() loginForm: {
    username: String;
    realname: string;
    password: String;
    sex: string;
    code:string;
  } = {
    username: "",
    realname: "",
    password: "",
    sex: "男",
    code: ""
  };

  @Provide() rules = {
    userName: [{ required: true, message: "请输入账号", trigger: "blur" }],
    password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    realname: [{ required: true, message: "请输入真实名", trigger: "blur" }],
     code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
    sex: [
      {
        required: true,
        message: "请选择性别",
        trigger: "change",
      },
    ],
  };

  /**登录提交事件
   * @description:
   * @param {type}
   * @return:
   */
  handleSubmit(): void {
    (this.$refs["loginForm"] as any).validate((validate: boolean) => {
      //如果校验通过
      if (validate) {
        console.log("校验通过");
        this.isLoding = true;
        //console.log("this.loginForm"+" username="+this.loginForm.username+";pwd="+this.loginForm.pwd);

        (this as any).$axios
          .post("/api/users/login", this.loginForm)
          .then((res: any) => {
            this.isLoding = false;
            console.log(res.data);
            // 存储token
            localStorage.setItem("tsToken", res.data.token);
            // 存储到vuex中
            this.setUser(res.data.token);

            // 登录成功 跳转 /
            this.$router.push("/");
          })
          .catch((err: string) => {
            console.log("失败" + err);
            this.isLoding = false;
          });
      } else {
        this.isLoding = false;
        console.log("校验失败");
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