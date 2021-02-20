/*
 * @Author: your name
 * @Date: 2020-07-29 10:29:15
 * @LastEditTime: 2020-09-03 18:47:46
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\store\mutations.ts
 */
import { MutationTree } from 'vuex'

const mutations: MutationTree<any> ={
    SET_USER(state:any,user:any):void{
       
        state.user = user;

        console.log("用户设置"+user)
    },
    SET_ROUTERS(state:any,routers:any):void{
        state.routers = routers;
    }
}

export default mutations;