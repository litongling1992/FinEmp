<template>
   <el-container class="layout-content">
    <!-- 左侧菜单 -->
    <el-aside width="200px">
      <slot class="side"  name="left"></slot>
    </el-aside>

    <!-- 右侧页面 -->
    <el-main>
      <!-- 面包屑 -->
      <div class="top">
        <i class="el-icon-s-unfold"></i>
        <!-- <el-breadcrumb class="breadcrumb" separator="/">
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        </el-breadcrumb> -->
        <el-breadcrumb class="breadcrumb" separator="/">
          <el-breadcrumb-item
            v-for="(item,index) in breadCrumbItems"
            :key="index"
            :to="{path:item.path}"
          >{{item.title}}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <!-- 页面内容 -->
      <div class="content">
        <slot name="content"></slot>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Provide, Watch } from "vue-property-decorator";

@Component
export default class Content extends Vue{

  /**面包屑的数组
   * @description: 
   * @param {type} 
   * @return: 
   */
  @Provide() breadCrumbItems: any; 


  /** 监听路由
   * @description: 
   * @param {type} 
   * @return: 
   */
  @Watch("$route") handleRouteChange(to: any) {
    this.initBreadCrumbItems(to);
  }

  /**钩子函数
   * @description: 
   * @param {type} 
   * @return: 
   */
  created() {
    this.initBreadCrumbItems(this.$route);
  }

  /** 初始化面包列数组
   * 将路由数组初始化
   * @description: 
   * @param {type} 
   * @return: 
   */
  initBreadCrumbItems(router: any) {
    // console.log(router);
    // 根路由title
    let breadCrumbItems: any = [{ path: "/", title: "后台管理系统" }];
    // 遍历父级到当前子路由的页面的title和path 存储到数组里
    for (const index in router.matched) {
      if (router.matched[index].meta && router.matched[index].meta.title) {
        breadCrumbItems.push({
          path: router.matched[index].path ? router.matched[index].path : "/",
          title: router.matched[index].meta.title
        });
      }
    }
    this.breadCrumbItems = breadCrumbItems;
  }
}
</script>


<style lang="scss" scoped>
.layout-content {
  width: 100%;
  height: 100%;
  .el-side{
    .side{background: #495060};
  }
  .el-main {
    padding: 0;
    .top {
      background: #fff;
      height: 54px;
      border-right: none;
      border-bottom: 1px solid #e6e6e6;
      display: flex;
      align-items: center;
      i {
        font-size: 20px;
        cursor: pointer;
        padding-left: 16px;
      }
      .breadcrumb {
        padding-left: 16px;
      }
    }
    .content {
      padding: 10px;
      height: calc(100% - 54px);
      box-sizing: border-box;
    }
  }
}
</style>