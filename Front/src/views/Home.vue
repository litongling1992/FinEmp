<!--
 * @Author: your name
 * @Date: 2020-07-24 16:57:03
 * @LastEditTime: 2020-09-08 09:46:08
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\Home.vue
--> 
<template>
    <div class="home">
       <div class="home-body">
            <h4 class="title">欢迎来到**后台管理系统</h4>
            <el-button @click="learn">进入学习</el-button>
       </div>
    </div>
</template>

<script lang="ts">
import { Component, Vue,Provide } from 'vue-property-decorator';

@Component({
    
})
export default class Home extends Vue{

    @Provide() isReload:Boolean=false;
    created () {
      if(this.isReload == false){
         // location.reload();
          this.isReload =true;
      }
      
    }
   
    learn(): void {
    // 新窗口打开
    window.open(
      "https://ke.qq.com",
      "_blank"
    );
  }
}
</script>


<style lang="scss" scoped>
.home {
  width: 100%;
  height: 100%;
  background: url(../assets/bg.jpg) no-repeat;
  background-size: 100% 100%;
  .home-body {
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    color: #fff;
    text-align: center;
    padding: 100px;
    box-sizing: border-box;
    .title {
      font-size: 32px;
      font-weight: bold;
      line-height: 60px;
    }
    .des {
      font-size: 18px;
      margin: 40px 0;
    }
  }
}
</style>