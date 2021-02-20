<!--
 * @Author: your name
 * @Date: 2020-08-03 18:18:45
 * @LastEditTime: 2020-09-07 18:45:48
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\DataManager\EditDialog.vue
--> 
<template>
<!--:close-on-click-modal="false"点击灰色地方不让关闭  -->
  <el-dialog title="员工资产编辑" :close-on-click-modal="false" :visible.sync="dialogVisible" :show-close="false">
    <!-- <el-form ref="ruleForm" :rules="rules" :model="form" label-width="100px" size="small" class="form-box">
      <el-form-item label="课程名称" prop="title">
        <el-input v-model="form.title" placeholder="请输入课程名称"></el-input>
      </el-form-item>
      <el-form-item label="课程等级" prop="level">
        <el-select v-model="form.level" placeholder="请选择课程等级">
          <el-option label="初级" value="初级"></el-option>
          <el-option label="中级" value="中级"></el-option>
          <el-option label="高级" value="高级"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="报名人数" prop="count">
        <el-input v-model="form.count" placeholder="请输入报名人数"></el-input>
      </el-form-item>
      <el-form-item label="上线日期" prop="date">
        <el-date-picker type="date" v-model="form.date" value-format="yyyy-MM-dd"></el-date-picker>
      </el-form-item>
      <el-form-item label="技术栈" prop="type">
        <el-radio-group v-model="form.type">
          <el-radio label="Vue" name="type"></el-radio>
          <el-radio label="React" name="type"></el-radio>
          <el-radio label="Node" name="type"></el-radio>
          <el-radio label="小程序" name="type"></el-radio>
          <el-radio label="angular" name="type"></el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form> -->
    <el-form class="form-box" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入员工姓名"></el-input>
      </el-form-item>
      <el-form-item label="电脑类型" prop="level">
        <el-select v-model="form.type" placeholder="电脑类型">
          <el-option label="笔记本" value="笔记本"></el-option>
          <el-option label="台式" value="台式"></el-option>        
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" prop="computer">
        <el-input v-model="form.computer" placeholder="电脑品牌"></el-input>
      </el-form-item>
      <el-form-item label="鼠标" prop="mouse">
        <el-input v-model="form.mouse" placeholder="鼠标"></el-input>
      </el-form-item>
      <el-form-item label="其它" prop="other">
        <el-input v-model="form.other" placeholder="其它"></el-input>
      </el-form-item>
       </el-form>
    <span slot="footer" class="dilog-footer">
        <el-button @click="$emit(`closeDialog`)" size="small">取消</el-button>
        <el-button @click="submitForm('ruleForm')" type="primary" size="small">确定</el-button>
    </span>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop, Provide } from "vue-property-decorator";

@Component
export default class EditDialog extends Vue {
  //加感叹号 强制解析
  @Prop(Boolean) dialogVisible!: boolean;

  @Prop(Object) form!: {
        name: String,
        type: String,
        computer:String,
        mouse: String,
        other: String,
        id: string;
  };

  // 校验规则。信息必填
  @Provide() rules: any = {
    name: [{ required: true, message: "请输入名字", trigger: "blur" }],
    computer: [{ required: true, message: "请输入品牌", trigger: "change" }],
    mouse: [{ required: true, message: "请输入鼠标", trigger: "blur" }],
    type: [
      {
        required: true,
        message: "请选择电脑类型",
        trigger: "change"
      }
    ]
  };

  submitForm(formName: any){

    (this.$refs[formName] as any).validate((validate:boolean) => {
      //如果校验通过
      if (validate){
        (this as any).$axios.post(`/api/emp/update/`,this.form)
        .then((res:any) => {
            // console.log(res.data);
            this.$emit("closeDialog");
            this.$message({
              message: res.data.msg,
              type: "success"
            });
        })
        .catch((err:any) => {
            this.$emit("closeDialog");
            this.$message({
               message: err.data.msg,
               type: 'warning'
            });
        })
      }

    });
  }
}
</script>


<style lang="scss" scoped>
.form-box {
  .el-input,
  .el-select {
    width: 200px !important;
  }
}
</style>