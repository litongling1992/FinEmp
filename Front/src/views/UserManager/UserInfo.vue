<!--
 * @Author: your name
 * @Date: 2020-07-29 15:11:30
 * @LastEditTime: 2020-09-08 14:04:05
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\UserManager\UserInfo.vue
--> 
<template>
    <div class="user-info">
       <div class="img-box">
           <h2 class="title">About Me</h2>
           <img :src="require('@/assets/'+ getUser.roles+'.jpg')">
           <h4>{{ getUser.username}}</h4>
       </div>
       <div class="info-box">
            <h2 class="title">Account</h2>
            <!-- 用户信息的Form表单 -->
            <el-form v-model="userData" class="form-box">
                <el-form-item label="用户名">
                    <el-input v-model="getUser.username" readonly>
                    </el-input>
                </el-form-item>
                 <el-form-item label="密码">
                    <el-input type="password" v-model="userData.password">
                    </el-input>
                </el-form-item>
                 <el-form-item >
                   <el-button @click="onSubmit" type="primary" :loading="isLoding">修改密码</el-button>
                </el-form-item>
            </el-form>
       </div>
    </div>
</template>

<script lang="ts">
import { Component, Vue, Provide } from 'vue-property-decorator';
import { State, Getter, Mutation, Action } from "vuex-class";

@Component
export default class UserInfo extends Vue{
    @Provide() isLoading:boolean=false;

    @Provide() userData:any ={
        username:'',
        password:''
    }

    @Getter('user') getUser:any;

    onSubmit():void{
        this.userData.username = this.getUser.username;
         //发起网络请求后，按钮暂时处于打圈状态，防止再次点击，
         this.isLoading = true;
         (this as any).$axios.post("http://localhost:8989/api/user/update",this.userData)
         .then((res:any)=>{
                this.isLoading =false; 
                if(res.data.state){
                    this.$message({message:res.data.msg,type: "success"});
                    localStorage.removeItem("tsToken");
                    this.$router.replace("/login");
                }else{
                   this.$message.error(res.data.msg);
                }       
         })
         .catch((err:any)=>{
             this.isLoading =false;
        });
    }

}
</script>


<style lang="scss" scoped>
.user-info {
  height: calc(100% - 70px);
  display: flex;
  overflow: auto;
  color: #606266;
  .img-box,
  .info-box {
    height: 100%;
    padding: 20px;
    box-sizing: border-box;
    border: 1px solid #dcdfe6;
    background: #fff;
    .title {
      border-bottom: 1px solid #dcdfe6;
      padding: 10px;
      text-align: left;
      margin-bottom: 20px;
      font-size: 16px;
      font-weight: bold;
    }
  }
  .img-box {
    text-align: center;
    width: 30%;
    margin-right: 10px;
    img {
      width: 120px;
      height: 120px;
      border-radius: 50%;
    }
    h4 {
      margin-top: 20px;
      font-size: 16px;
    }
  }
  .info-box {
    flex: 1;
    .form-box {
      padding: 10px;
    }
  }
}
</style>