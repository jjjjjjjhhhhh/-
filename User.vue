<template>
	<div>
		<el-form :inline="true">
		  <el-form-item>
		    <el-button type="primary"  @click="dialogVisible = true" >Add</el-button>
		  </el-form-item>
		</el-form>
		
		<el-table
		    ref="multipleTable"
		    :data="tableData"
		    tooltip-effect="dark"
		    style="width: 100%"
			border
			stripe
		    @selection-change="handleSelectionChange">
		    <el-table-column
		      type="selection"
		      width="55">
		    </el-table-column>
			
			<el-table-column
			  prop="icon"
			  label="icon"
			  width="50">
			  <template>
				<i class="el-icon-user"></i>
			  </template>
			</el-table-column>
		    
		    <el-table-column
		      prop="username"
		      label="username"
		      width="120">
		    </el-table-column>
		    <!-- <el-table-column
		      prop="roleName"
		      label="角色名称"
			  type="text"
		      show-overflow-tooltip>
		    </el-table-column> -->
			<el-table-column
			  prop="email"
			  label="email"
			  show-overflow-tooltip>
			</el-table-column>
			<el-table-column
			  prop="phone"
			  label="phone"
			  show-overflow-tooltip>
			</el-table-column>	
			
			<el-table-column
			  prop="statu"
			  label="statu">
			  <template slot-scope="scope">
			  		<el-tag size="small" v-if="scope.row.statu === 1" type="success">正常</el-tag>
					<el-tag size="small" v-if="scope.row.statu === 0" type="danger">禁用</el-tag>
			  </template>
			</el-table-column>
			
			<el-table-column
			  prop="created"
			  label="created"
			  show-overflow-tooltip>
			</el-table-column>
			
			<el-table-column
			  prop="icon"
			  label="icon"
			  width="180px">
			  <template slot-scope="scope">
			  		<el-button type="text" @click="permHandle(scope.row.id)">Assign Permissions</el-button>
					<!-- <el-button type="text" @click="repassHandle(scope.row.id,scope.row.userName)">重置密码</el-button> -->
					<el-button type="text" @click="editHandle(scope.row)">edit</el-button>
					&nbsp;	
					<template>
					<el-popconfirm title="OK to delete this menu？" @confirm='delhandle(scope.row.id)'>
					  <el-button type="text" slot="reference">delete</el-button>
					</el-popconfirm>
					</template>
					
					
			  </template>
			  
			</el-table-column>
		
		  </el-table>
		  
		  <el-pagination
		        @size-change="handleSizeChange"
		        @current-change="handleCurrentChange"
		        :current-page="current"
		        :page-sizes="[10, 20, 50, 100]"
		        :page-size="size"
		        layout="total, sizes, prev, pager, next, jumper"
		        :total="total">
		      </el-pagination>
			  
		
		<el-dialog
		  title="提示"
		  :visible.sync="dialogVisible"
		  width="600px"
		  :before-close="handleClose">
		  
					<el-form :model="editForm" :rules="editFormRules" ref="editForm" label-width="100px" class="demo-editForm">
					  		  
					  <el-form-item label="userName" prop="userName" label-width="100px">
					     <el-input v-model="editForm.username" autocomplete="off"></el-input>
					   </el-form-item>
					   
					   <el-form-item label="email" prop="email"label-width="100px" >
					      <el-input v-model="editForm.email"></el-input>
					    </el-form-item>
						
						<el-form-item label="phoneNum" prop="phoneNum"label-width="100px">
						   <el-input v-model="editForm.phone"></el-input>
						 </el-form-item>
						 
					  <el-form-item label="statu" prop="statu" label-width="100px">
					    <el-radio-group v-model="editForm.statu">
					      <el-radio :label=0>Disable</el-radio>
					      <el-radio :label=1>normal</el-radio>
					    </el-radio-group>
					  </el-form-item>
					
					  <el-form-item>
					    <el-button type="primary" @click="opentp()">confirm</el-button>
					    <el-button @click="resetForm('editForm')">cancel</el-button>
					  </el-form-item>
					</el-form>
					
		</el-dialog>
		
		<el-dialog
		  title="prompt"
		  :visible.sync="dialogVisible2"
		  width="600px"
		  :before-close="handleClose2">
		  
					<el-form :model="editForm2"  ref="editForm2" label-width="100px" class="demo-editForm">
					  		  
					  <el-form-item label="userName" prop="userName" label-width="100px">
					     <el-input v-model="editForm2.username" autocomplete="off"></el-input>
					   </el-form-item>
					   
					   <el-form-item label="email" prop="email"label-width="100px" >
					      <el-input v-model="editForm2.email"></el-input>
					    </el-form-item>
						
						<el-form-item label="phoneNum" prop="phoneNum"label-width="100px">
						   <el-input v-model="editForm2.phone"></el-input>
						 </el-form-item>
						 
					  <el-form-item label="statu" prop="statu" label-width="100px">
					    <el-radio-group v-model="editForm2.statu">
					      <el-radio :label=0>Disable</el-radio>
					      <el-radio :label=1>normal</el-radio>
					    </el-radio-group>
					  </el-form-item>
					
					  <el-form-item>
					    <el-button type="primary" @click="upUser">confirm</el-button>
					    <el-button @click="dialogVisible2 = false">cancel</el-button>
					  </el-form-item>
					</el-form>
					
		</el-dialog>
			  
		<el-dialog
		  title="Assign Permissions"
		  :visible.sync="permDialogVisible"
		  width="600px"
		  >
			
			 <el-select v-model="value" placeholder="Please select">
			    <el-option
			      v-for="item in options"
			      :key="item.value"
			      :label="item.label"
			      :value="item.value">
			    </el-option>
			  </el-select>
				
			</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="permDialogVisible = false">Cancel</el-button>
			    <el-button type="primary" @click="upUserQx">confirm</el-button>
			  </span>
		  
		  </el-dialog>
		
	</div>
</template>

<script>

import { Table } from 'element-ui'
import { globle } from '../../globalFun.js'
	export default {
		name: "Role",
		data () {
			return {
				id: '',
				searchFrom: {},
				delBtStatu: true,
				total: 0,
				size: 10,
				current: 1,
				dialogVisible: false,
				dialogVisible2: false,
				 options: [],
				        value: '',
				editForm2: {
					
				},
				editForm: {
					
				},
				permForm: {
					
				},
				
				 tableData: [],
					editFormRules: {
						userName: [
							{required: true,message: '请输入名称',trigger: 'blur'}
						],
						email: [
							{required: true,message: '请输入唯一编码',trigger: 'blur'}
						],
						phoneNum: [
							{required: true,message: '请输入描述',trigger: 'blur'}
						],
						statu: [
							{required: true,message: '请输入状态',trigger: 'blur'}
						]
						
					},
					multipleSelection: [],
					permData: {
						
					},
					
					permDialogVisible: false,
					defaultProps: {
					          children: 'children',
					          label: 'name'
					        }
			}
		},
		created() {
			// this.getUserList()
			this.getUserInfo()
			
			// this.$axios.get('sys/user/role').then(res => {
			// 	this.permData = res.data.data
			// })
			
			
		},
		methods: {
			
			upUserQx(){
				this.$axios.get("/sys/user/upUserQx",
				{
					params: {
						id: this.id,
						value: this.value
					}
				}).then(res => {
					console.log(res)
				})
				
				this.permDialogVisible = false
				
				this.$message({
					message:'操作成功',
					type:'success'
				})
			},
			
			upUser(){
				this.$axios.get("/sys/user/upUser",
				{
					params: {
						username: this.editForm2.username,
						email: this.editForm2.email,
						phone: this.editForm2.phone,
						statu: this.editForm2.statu,
						id: this.id
					}
				}).then(res => {
					console.log(res)
				})
				this.$message({
					message:'修改成功',
					type:'success'
				})
				this.dialogVisible2 = false
			},
			
			opentp(){
				this.addUser()
				
			},
			
			
			
			addUser(){
				this.$axios.get("/sys/user/addUser",
				{
					params: {
						username: this.editForm.username,
						email: this.editForm.email,
						phone: this.editForm.phone,
						statu: this.editForm.statu
					}
				}).then(res => {
					console.log(res)
				})
				this.$message({
					message:'添加成功',
					type:'success'
				})
				this.dialogVisible = false
			},
			
			getUserInfo() {
				this.$axios.get("sys/user/getUserInfo").then(res => {
					this.tableData = res.data.data
				})
			},
			
			getUserList() {
				
				console.log(this.searchFrom.name)
				
				this.$axios.get('sys/user/list', {
					params: {
						name: this.searchFrom.name,
						current: this.current,
						size: this.size
					}
				}).then(res => {
					this.tableData = res.data.data.recodes
					this.current = res.data.data.current
					this.total = res.data.data.total
					console.log(res.data.data)
				})
				
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
				 if(valid)
				 {
					this.$axios.post('/sys/role/' + (this.editForm.id?'updata':'save'), this.editForm).
					then(res =>{
						
						 this.$message({
						          showClose: true,
						          message: '操作成功',
						          type: 'success',
								  onClose:() => {
								  	this.getRoleList()
								  }
						        });
						
					})
					
				 }
				 else{
					 console.log('error submit!!');
					 return false;
				 }
				 
				});
			},
			
		      toggleSelection(rows) {
		        if (rows) {
		          rows.forEach(row => {
		            this.$refs.multipleTable.toggleRowSelection(row);
		          });
		        } else {
		          this.$refs.multipleTable.clearSelection();
		        }
		      },
		      handleSelectionChange(val) {
		        this.multipleSelection = val;
				console.log(val)
				
				this.delBtStatu = this.multipleSelection == 0
				
		      },
			  handleSizeChange(val) {
			          console.log(`每页 ${val} 条`);
			        },
			  handleCurrentChange(val) {
			      console.log(`当前页: ${val}`);
			  },
			  
			  editHandle(id) {
			  	// this.$axios.get('sys/user/info/' + id).then(res => {
			  	// 	this.editForm = res.data.data
			  	// 	this.dialogVisible = true 	
			  	// })
				this.editForm2 = id
				this.dialogVisible2 = true
				this.id = id.id
				
			  },	
			  
			  handleClose() {
			  	this.resetForm('editForm')
			  },
			  handleClose2() {
			  	this.resetForm('editForm2')
			  },
			  resetForm(formName) {
			     this.$refs[formName].resetFields();
			     this.dialogVisible = false
			     this.editForm = {}
			  },
			  resetForm2(formName) {
			     this.$refs[formName].resetFields();
			     this.dialogVisible = false
			     this.editForm2 = {}
			  },
			  delhandle(id) {
				  
				  this.$axios.get("/sys/user/delUser",{
				  	params: {
				  		id: id
				  	}
				  }).then(res => {
				  	console.log(res)
				  })
				  this.$message({
				  	message:'删除成功',
				  	type:'success'
				  })
				  this.$router.go(0)
			  	
			  	
			  },
			  
			  permHandle(id) {
				  
				this.permDialogVisible =  true
				this.id = id
				
				
				
			  },
			  submitPermFormHandle(forName) {
				  
				var idsHandle  = this.$refs.permTree.getCheckedKeys()
				
				console.log(idsHandle)
				  
				this.$axios.post('sys/role/permForm'+ this.permForm.name, idsHandle).then(res => {
					
					this.$message({
					         showClose: true,
					         message: '操作成功',
					         type: 'success',
							onClose:() => {
							this.getRoleList()
						}
					});
					
				})
				  
			  },
			  
			  
			  repassHandle(id,username) {
				  this.$confirm('将重置用户【'+ username +'】的密码,是否继续?','提示', {
					  confirmButtonText: '确定',
					  cancelButtonText: '取消',
					  type: 'warning'
				  }).then(res => {
					
					  this.$axios.post('sys/user/repass').then(res => {
						  this.$message({
						           showClose: true,
						           message: '操作成功',
						           type: 'success',
						  		onClose:() => {
						  	}
						  });
					  })
				  })
			  }
			  
			  
			  
			  
		    }

			    
		
	}
</script>

<style>
	.el-pagination{
		float: right;
		margin-top: 22px;
	}
</style>