import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Home from '../views/Home.vue'
import Layout from '../views/Layout/Index.vue'
import jwt_decode from 'jwt-decode';

Vue.use(VueRouter);


/**
* hidden: true                   如果hidden为true则在左侧菜单栏展示，否则不显示
* name:'router-name'             路由名称，必须填写
* meta : {
    title: 'title'               对应路由在左侧菜单栏的标题名称
    icon: 'icon-class'           对应路由在左侧菜单栏的图标样式，样式使用fontawesome图标库
  }
**/

//自定义的路由数组(其实和原始定义的一样道理,这样写主要是方便导出，便于其他路由可动态展示一些页面)
export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    hidden: true,
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'home',
        meta: { title: '首页', icon: 'el-icon-s-home' },
        component: () => import('@/views/Home.vue')
      }
    ]
  },
  {
    path: '/dataManage',
    name: 'dataManage',
    hidden: true,
    meta: { title: '数据管理', icon: 'el-icon-s-order' },
    component: Layout,
    redirect: '/tableData',
    children: [
      {
        path: '/tableData',
        name: 'tableData',
        meta: { title: '表格管理', icon: 'el-icon-s-order' },
        component: () => import('@/views/DataManager/TablesData.vue')
      },
      {
        path: '/chartsData',
        name: 'chartsData',
        meta: { title: '图表管理', icon: 'el-icon-s-order' },
        component: () => import('@/views/DataManager/CharsData.vue')
      },
      {
        path: '/formData',
        name: 'formData',
        meta: {
          title: '表单管理',
          icon: 'el-icon-s-order',
          roles: ['admin'] //增加权限
        },
        component: () => import('@/views/DataManager/FormsData.vue')
      }
    ]
  },
  {
    path: '/userManage',
    hidden: true,
    name: 'userManage',
    component: Layout,
    redirect: '/accountData',
    children: [
      {
        path: '/accountData',
        name: 'accountData',
        meta: { title: '账户管理', icon: 'el-icon-coordinate' , roles: ['admin'] },
        component: () => import('@/views/UserManager/AccountData.vue')
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/userInfo',
    hidden: false,
    children: [
      {
        path: '/userInfo',
        name: 'userInfo',
        meta: { title: '个人中心' },
        component: () => import('@/views/UserManager/UserInfo.vue')
      }
    ]
  },
  {
    path: '/404',
    name: '404',
    hidden: false,
    meta: { title: '404' },
    component: () => import('@/views/404.vue')
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/login',
    name: 'login',
    hidden: false,
    meta: { title: '系统登录' },
    component: () => import('@/views/Login/Login.vue')
  },
  {
    path: '/password',
    name: 'password',
    hidden: false,
    meta: { title: '找回密码' },
    component: () => import('@/views/Login/Password.vue')
  }
]

//const routes: Array<RouteConfig> = [
// {
//   path: '/',
//   name: 'Home',
//   component: Home
// },
//   {
//     path: '/',
//     name: 'Index',
//     component: Index
//   },
//   {
//     path: '/login',
//     name: 'login',
//     component: () => import('@/views/Login/Login.vue')
//   },
//   {
//     path: '/password',
//     name: 'password',
//     component: () => import('@/views/Login/Password.vue')
//   },
// ]

//解决重复点击左侧菜单栏，控制台会输出报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location:any) {
  return (originalPush.call(this, location) as any).catch((err:any) => err)
}

const router: any = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: asyncRouterMap
})

router.beforeEach((to: any, from: any, next: any) => {
  //判断是否已经登录
  const isLogin = localStorage.tsToken ? true : false;
  if (to.path == '/login' || to.path == '/password') {
    next();
  } else {
    if(isLogin){
      const decode:any = jwt_decode(localStorage.tsToken);
      const {key} = decode;
      if(hasPermission(decode.roles,to)){
        next();
      }else{
        next('/404');
      }

    }else{
      next('/login');
    }
    //isLogin ? next() : next('/login');
  }
})

/**
 * 判断是否有权限
 * @param roles 当前角色
 * @param route 当前路由对象
 * */
function hasPermission(roles: string, route: any) {
  if (route.meta && route.meta.roles) {
    // 如果meta.roles是否包含角色的key值,如果包含那么就是有权限,否则无权限
    return route.meta.roles.some((role: string) => role.indexOf(roles) >= 0);
  } else {
    // 默认不设置有权限
    return true;
  }
}

export default router
