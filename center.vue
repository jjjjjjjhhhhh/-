<template>
	<div>
		<el-row style="margin-top: 0%;">
		  <h1>Personal Center</h1>
		</el-row>
		<el-row>
			<el-form :inline="true">
			  <el-form-item>
			    <el-button type="primary"  @click="dialogVisible = true" >Change Password</el-button>
			  </el-form-item>
			</el-form>
			<el-dialog
			  title=""
			  :visible.sync="dialogVisible"
			  width="600px"
			  :before-close="handleClose">
			  
						<el-form :model="editForm" :rules="editFormRules" ref="editForm" label-width="100px" class="demo-editForm">
						  		  
						  <el-form-item label="Original password" prop="password1" label-width="100px">
						     <el-input v-model="editForm.username" autocomplete="off"></el-input>
						   </el-form-item>
						   
						   <el-form-item label="New Password" prop="password2"label-width="100px" >
						      <el-input v-model="editForm.email"></el-input>
						    </el-form-item>
							
							<el-form-item label="Confirm Password" prop="password3"label-width="100px">
							   <el-input v-model="editForm.phone"></el-input>
							 </el-form-item>
							 
						
						  <el-form-item>
						    <el-button type="primary" @click="opentp()">confirm</el-button>
						  </el-form-item>
						</el-form>
						
			</el-dialog>
		</el-row>
		<el-row>
		  <el-col :span="8" style="margin-right: 100px;">
			  <el-form ref="form" :model="username" label-width="80px">
			    <el-form-item label="username">
			      <el-input v-model="form.username"></el-input>
			    </el-form-item>
			  </el-form>
		  </el-col>
		  <el-col :span="8">
		  	<el-form ref="form" :model="email" label-width="80px">
		  		<el-form-item label="email">
		  		<el-input v-model="form.email"></el-input>
		  		</el-form-item>
		  	</el-form>
		  </el-col>
		</el-row>
		<el-row>
		 <el-col :span="8" style="margin-right: 100px;">
		 			  <el-form ref="form" :model="city" label-width="80px">
		 			    <el-form-item label="city">
		 			      <el-input v-model="form.city"></el-input>
		 			    </el-form-item>
		 			  </el-form>
		 </el-col>
		 <el-col :span="8">
		 	<el-form ref="form" :model="phone" label-width="80px">
		 		<el-form-item label="phone">
		 		<el-input v-model="form.phone"></el-input>
		 		</el-form-item>
		 	</el-form>
		 </el-col>
		</el-row>
		<el-row>
		 <el-form ref="form" :model="status" label-width="80px">
		   <el-form-item label="status">
		     <el-input v-model="form.status"></el-input>
		   </el-form-item>
		 </el-form>
		</el-row>
		
	</div>
</template>

<script>
	 export default {
	    data() {
	      return {
	        form: {
	         username: '11',
			 phone: '11',
			 email: '11',
			 status: '11',
			 city: '11',
	        },
			dialogVisible: false,
			editForm: {
				
			},
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
	      }
	    },
	    methods: {
	      onSubmit() {
	        console.log('submit!');
	      }
	    }
	  }
</script>

<style>
	.el-row {
	    margin-top: 10px;
	  }
	  .el-col {
	    border-radius: 4px;
	  }
	  
	  .grid-content {
	    border-radius: 4px;
	    min-height: 36px;
	  }
	 
</style>