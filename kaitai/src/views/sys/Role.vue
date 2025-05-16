<template>
	<div>
		<el-form :inline="true">
		<el-input
		v-model="searchFrom.name"
		placeholder="名称"
		clearable
		
		style="width: 200px;margin-right: 10px"
		>
		</el-input>
		
		<el-form-item>
			<el-button @click="getRoleList(searchFrom.name)">搜索</el-button>
		</el-form-item>
		
		  <el-form-item>
		    <el-button type="primary"  @click="dialogVisible = true">新增</el-button>
		  </el-form-item>
		  
		  <el-form-item>
		  	<el-popconfirm title="确认批量删除吗？" @confirm='delhandle(null)'>
		  	  <el-button type="danger" slot="reference" :disabled="delBtStatu">批量删除</el-button>
		  	</el-popconfirm>
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
		      prop="name"
		      label="名称"
		      width="120">
		    </el-table-column>
		    <el-table-column
		      prop="code"
		      label="唯一编码"
		      show-overflow-tooltip>
		    </el-table-column>
			<el-table-column
			  prop="remark"
			  label="描述"
			  show-overflow-tooltip>
			</el-table-column>
			
			<el-table-column
			  prop="statu"
			  label="状态">
			  <template slot-scope="scope">
			  		<el-tag size="small" v-if="scope.row.statu === 1" type="success">正常</el-tag>
					<el-tag size="small" v-if="scope.row.statu === 0" type="danger">禁用</el-tag>
			  </template>
			</el-table-column>
			
			<el-table-column
			  prop="icon"
			  label="操作">
			  <template slot-scope="scope">
			  		<el-button type="text" @click="permHandle(scope.row.id)">分配权限</el-button>
					<el-button type="text" @click="editHandle(scope.row.id)">编辑</el-button>
					&nbsp;	
					<template>
					<el-popconfirm title="确定删除此菜单？" @confirm='delhandle(scope.row.id)'>
					  <el-button type="text" slot="reference">删除</el-button>
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
					  		  
					  <el-form-item label="角色名称" prop="name" label-width="100px">
					     <el-input v-model="editForm.name" autocomplete="off"></el-input>
					   </el-form-item>
					   
					   <el-form-item label="唯一编码" prop="code"label-width="100px" >
					      <el-input v-model="editForm.code"></el-input>
					    </el-form-item>
						
						<el-form-item label="描述" prop="remark"label-width="100px">
						   <el-input v-model="editForm.remark"></el-input>
						 </el-form-item>
						 
					  <el-form-item label="状态" prop="statu" label-width="100px">
					    <el-radio-group v-model="editForm.statu">
					      <el-radio :label=0>禁用</el-radio>
					      <el-radio :label=1>正常</el-radio>
					    </el-radio-group>
					  </el-form-item>
					
					  <el-form-item>
					    <el-button type="primary" @click="submitForm('editForm')">确认</el-button>
					    <el-button @click="resetForm('editForm')">取消</el-button>
					  </el-form-item>
					</el-form>
					
		</el-dialog>
			  
		<el-dialog
		  title="分配权限"
		  :visible.sync="permDialogVisible"
		  width="600px"
		  >
			
			<el-form :model="permForm">
				<el-tree
				  :data="permData"
				  show-checkbox
				  node-key="id"
				  ref="permTree"
				  :check-strictly=true
				  :default-expand-all = true
				  :props="defaultProps">
				</el-tree>
				
			</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="permDialogVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitPermFormHandle()">确 定</el-button>
			  </span>
		  
		  </el-dialog>
		
	</div>
</template>

<script>

import { Table } from 'element-ui'
	export default {
		name: "Role",
		data () {
			return {
				searchFrom: {},
				delBtStatu: true,
				total: 0,
				size: 10,
				current: 1,
				dialogVisible: false,
				editForm: {
					
				},
				permForm: {},
				
				 tableData: [],
					editFormRules: {
						name: [
							{required: true,message: '请输入名称',trigger: 'blur'}
						],
						code: [
							{required: true,message: '请输入唯一编码',trigger: 'blur'}
						],
						remark: [
							{required: false,message: '请输入描述',trigger: 'blur'}
						],
						statu: [
							{required: true,message: '请输入状态',trigger: 'blur'}
						]
						
					},
					multipleSelection: [],
					permData: {},
					
					permDialogVisible: false,
					defaultProps: {
					          children: 'children',
					          label: 'name'
					        }
			}
		},
		created() {
			this.getRoleList()
			
			this.$axios.get('sys/menu/list').then(res => {
				this.permData = res.data.data
			})
		},
		methods: {
			
			getRoleList() {
				
				console.log(this.searchFrom.name)
				
				this.$axios.get('sys/role/list', {
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
			  	this.$axios.get('sys/role/info/' + id).then(res => {
			  		this.editForm = res.data.data
			  		this.dialogVisible = true 	
			  	})
			  },	
			  
			  handleClose() {
			  	this.resetForm('editForm')
			  },
			  resetForm(formName) {
			     this.$refs[formName].resetFields();
			     this.dialogVisible = false
			     this.editForm = {}
			  },
			  delhandle(id) {
				  
				  var ids = []
				  if(ids)
				  {
					ids.push(id)  
				  }
				  else
				  {
					this.multipleSelection.forEach(rew => {
						ids.push(row.id)
					})
				  }

				
			  	this.$axios.post("/sys/role/del" + ids).then(res => {
			  		this.$message({
			  		         showClose: true,
			  		         message: '删除成功',
			  		         type: 'success',
			  				onClose:() => {
			  				this.getRoleList()
			  			}
			  		});
			  	})
			  	
			  },
			  
			  permHandle(id) {
				  
				this.permDialogVisible =  true
				
				this.$axios.get("sys/menu/info" + id).then(res => {
					
					    this.$refs.permTree.setCheckedKeys(res.data.data.menuIds);
						console.log(res.data.data)
					
						this.permForm = res.data.data
					
				})	
				
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
					
					this.permDialogVisible = false
					
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