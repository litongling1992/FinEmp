/*
 * @Author: your name
 * @Date: 2020-07-29 10:28:08
 * @LastEditTime: 2020-09-03 19:09:59
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\store\actions.ts
 */
import { ActionTree } from 'vuex'
import jwt_decode from 'jwt-decode'
import { asyncRouterMap } from '../router'

const actions: ActionTree<any,any> ={
   async setUser({state,commit},user:any){
        console.log("Set-user"+user);
        const decoded:any = jwt_decode(user);
        commit('SET_USER',decoded);
        console.log("用户角色decoded;" + decoded.roles);
        //key里边包含了用户角色 如 admin  visitor editor
        const  key  =decoded.roles;

        let accessedRouters =asyncFilterRouresMap(asyncRouterMap,key);
        console.log("Action解析完成用户角色为："+key);
        commit('SET_ROUTERS', accessedRouters);
   }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap 所有路由表
 * @param roles 当前角色
 */
function asyncFilterRouresMap(asyncRouterMap: Array<any>, roles: string){
   //遍历当前的路由表
   const accessedRouters = asyncRouterMap.filter(route => {
      // console.log(route);
      if (hasPermission(roles, route)) {
        if (route.children && route.children.length) {
          route.children = asyncFilterRouresMap(route.children, roles);
        }
        return true;
      }
      return false;
    });
  
    return accessedRouters;
}

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



export default actions;