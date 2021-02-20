<!--
 * @Author: your name
 * @Date: 2020-07-29 15:10:53
 * @LastEditTime: 2020-09-07 19:24:59
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\UserManager\AccountData.vue
-->
<template>
  <div class="account-data">
    <div class="add-box">
      <el-button type="primary" @click="addAccount">新增账户</el-button>
    </div>
    <el-table :data="tableData" border style="width:100%">
      <el-table-column label="角色" width="180">
        <template slot-scope="scope">
            <el-select
            @change="selectChange(scope.row)"
            v-if="scope.row.edit"
            v-model="scope.row.role">
            <el-option
              v-for="option in options"
              :label="option.role"
              :value="option.role"
              :key="option.key"
            ></el-option>
          </el-select>
          <span v-else>{{scope.row.role}}</span>
        </template>
      </el-table-column>
      <el-table-column label="账户" width="120">
        <template slot-scope="scope">
         <el-input v-model="scope.row.username" v-if="scope.row.edit"></el-input>
          <span v-else>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" prop="des"></el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope" v-if="scope.row.username != 'admin'">
          <el-button @click="handleEdit(scope.$index,scope.row)" v-if="!scope.row.edit" size="mini">编辑</el-button>
          <el-button @click="handleSave(scope.$index,scope.row)" v-else size="mini" type="success">完成</el-button>
          <el-button @click="handleDelete(scope.$index,scope.row)" size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <AddAccount
      @update="getUserData"
      @closeDialog="closeDialog"
      :dialogVisible="dialogVisible"
      :options="options"
    ></AddAccount>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Provide } from "vue-property-decorator";
import AddAccount from "./AddAccount.vue";
@Component({
  components: {
    AddAccount,
  },
})
export default class AccountData extends Vue {
  @Provide() tableData: any = [];

  @Provide() dialogVisible: Boolean = false;

  // select数据
  @Provide() options: any = [
    {
      key: "admin",
      role: "管理员",
      des: "Super Administrator. Have access to view all pages.",
    },
    {
      key: "visitor",
      role: "游客",
      des: "Just a visitor. Can only see the home page and the document page",
    },
  ];

  created() {
    this.getUserData();
  }

  getUserData() {
    (this as any).$axios.get("http://localhost:8989/api/user/findAll")
      .then((res: any) => {
        console.log("获取数据" + res.data.msg);
        //遍历数据给每个属性增加一个属性edit为false
          res.data.users.forEach((item: any) => {
          item.edit = false;
        });

        this.tableData = res.data.users;
      })
      .catch((err: any) => {
         console.log("获取所有用户数据失败"+err);
      });
  }
  addAccount() {
    this.dialogVisible = true;
  }

  closeDialog() {
    this.dialogVisible = false;
  }

  handleEdit(index: number, row: any){
     // 编辑
    row.edit = true;
  }

   handleSave(index: number, row: any): void {
    // 完成
    row.edit = false;
    (this as any).$axios
      .post(`/api/users/editUser/${row.id}`, row)
      .then((res: any) => {
        this.$message({
          message: res.data.msg,
          type: "success"
        });
      });
  }

  selectChange(item: any) {
    this.options.map((option: any) => {
      if (option.role == item.role) {
        item.key = option.key;
        item.des = option.des;
      }
    });
  }

  handleDelete(index: number, row: any): void {
    // 删除
    (this as any).$axios
      .delete(`/api/users/deleteUser/${row._id}`)
      .then((res: any) => {
        this.$message({
          message: res.data.msg,
          type: "success"
        });

        this.tableData.splice(index, 1);
      });
  }
  
}
</script>


<style lang="scss" scoped>
.account-data {
  height: 100%;
  overflow: auto;
  .add-box {
    margin-bottom: 10px;
  }
}
</style>