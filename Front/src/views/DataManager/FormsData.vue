<!--
 * @Author: your name
 * @Date: 2020-07-29 15:07:56
 * @LastEditTime: 2020-09-07 18:14:03
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\DataManager\FormsData.vue
-->
<template>
    <div>
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
      <!-- <el-form-item label="鼠标" prop="date">
        <el-date-picker type="date" v-model="form.date" value-format="yyyy-MM-dd"></el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="技术栈" prop="type">
        <el-radio-group v-model="form.type">
          <el-radio label="Vue" name="type"></el-radio>
          <el-radio label="React" name="type"></el-radio>
          <el-radio label="Node" name="type"></el-radio>
          <el-radio label="小程序" name="type"></el-radio>
          <el-radio label="angular" name="type"></el-radio>
        </el-radio-group>
      </el-form-item> -->
       <el-form-item>
        <el-button @click="submitForm('ruleForm')" type="primary">创建员工资产</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
       </el-form>
    </div>
</template>

<script lang="ts">
import { Component, Vue ,Provide} from 'vue-property-decorator';

@Component
export default class FormsData extends Vue{
    @Provide() form:Object ={
        name: "",
        type: "",
        computer: "",
        mouse: "",
        other: "",
    };
    @Provide() rules: any = {
    name: [{ required: true, message: "请输入员工姓名", trigger: "blur" }],
    computer: [{ required: true, message: "请输入电脑品牌", trigger: "change" }],
    mouse: [{ required: true, message: "请输入鼠标品牌", trigger: "blur" }],
    type: [
      {
        required: true,
        message: "请选择电脑类型",
        trigger: "change"
      }
    ]
  };

  submitForm(formName: string) {
    (this as any).$refs[formName].validate((valid: boolean) => {
      if (valid) {
        (this as any).$axios
          .post(`api/emp/save`, this.form)
          .then((res: any) => {
              console.log(res.data);
            this.$message({
              message: res.data.msg,
              type: "success"
            });
            this.resetForm(formName);
          });
      }
    });
  }

  resetForm(formName: string) {
    (this as any).$refs[formName].resetFields();
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