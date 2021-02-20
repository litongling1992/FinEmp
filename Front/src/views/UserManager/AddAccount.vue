<!--
 * @Author: your name
 * @Date: 2020-08-14 10:23:08
 * @LastEditTime: 2020-08-14 17:15:15
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\UserManager\AddAccount.vue
-->
<template>
       <el-dialog title="新增用户" :close-on-click-modal="false" :visible.sync="dialogVisible" :show-close="false" width="30%">
           <el-form :rules="rules" ref="ruleForm" :model="account" class="form-box" label-width="120px">
               <el-form-item label="请选择角色" prop="role">
                   <el-select @change="selectChange" v-model="account.role" placeholder="请选择角色">
                    <el-option v-for="option in options" :key="option.key" :label="option.role" :value="option.role">
                    </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item label="请输入账户名" prop="username">
                   <el-input v-model="account.username" placeholder="请输入用户名"></el-input>               
               </el-form-item>
           </el-form>
           <span slot="footer" class="dilog-footer">
                    <el-button @click="$emit(`closeDialog`)">取消</el-button>
                    <el-button @click="handleAdd" type="primary">确定</el-button>
           </span>
       </el-dialog>
</template>

<script lang="ts">
import { Component, Vue , Provide ,Prop,Emit} from 'vue-property-decorator';

@Component
export default class AddAccount extends Vue{

    //接收父组件传递过来的数据
    @Prop(Boolean) dialogVisible!:boolean;
    @Prop(Array) options!:any;

    @Provide() account:Object ={
        key:"",
        role:"",
        username:"",
        des:""
    }

      @Provide() rules: any = {
        username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        role: [{ required: true, message: "请选择用户角色", trigger: "change" }],
    };

    selectChange(selectName : string){
        this.options.map((option : any) => {
            if(option.role == selectName){
                (this as any).account.key = option.key;
                (this as any).account.des = option.des;
            }
        })
    }

    @Emit("closeDialog")
    handleAdd(){
         (this as any).$refs["ruleForm"].validate((valid: boolean) => {
      if (valid) {
        (this as any).$axios
          .post("/api/users/addUser", this.account)
          .then((res: any) => {
            this.$emit("update");
            this.$message({
              message: res.data.msg,
              type: "success"
            });
          });
      }
    });
    //(this.$refs[rulesForm] as any).validate((validate:boolean) => {
      //如果校验通过
    //  if (validate){
         
    //     (this as any).$axios.post(`/api/profiles/edit/${this.form._id}`)
    //     .then((res:any) => {
    //         // console.log(res.data);
    //         this.$emit("closeDialog");
    //         this.$message({
    //           message: res.data.msg,
    //           type: "success"
    //         });
    //     })
    //     .catch((err:any) => {
    //         this.$emit("closeDialog");
    //         this.$message({
    //            message: err.data.msg,
    //            type: 'warning'
    //         });
    //     })
    //   }

    // });
    }

}
</script>


<style lang="scss" scope>
    .form-box{
        .el-input,
        .el-select{
            width: 200px !important;
        }
    }
</style>