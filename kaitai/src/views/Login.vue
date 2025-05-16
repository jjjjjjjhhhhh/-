<template>
	
	<div>
		<el-row type="flex" class="row-bg" justify="center">
		  <el-col :xl="6" :lg="5">
			  <h2>Family healthy life information management system</h2>
			  <!-- <el-image :src="require('@/assets/zhang.jpg')" style="height: 180px;width: 180px;" ></el-image>
			  <p>任何疑问请扫码联系本人</p> -->
			</el-col>
		  <el-col :span="4">
			  <el-divider direction="vertical"></el-divider>
			</el-col>
		  <el-col :xl="6" :lg="7">
			  <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
			    <el-form-item label="UserName" prop="username" style="width: 380px;">
			      <el-input v-model="loginForm.username"></el-input>
			    </el-form-item>
				<el-form-item label="PassWord" prop="password" show-password style="width: 380px;" type="password">
				  <el-input v-model="loginForm.password"show-password></el-input>
				</el-form-item>
				<!-- <el-input placeholder="请输入密码" v-model="loginForm.password" show-password></el-input> -->
				<el-form-item label="Code" prop="code" style="width: 380px;">
				  <el-input v-model="loginForm.code" style="width: 172px;float: left; "></el-input>
				  <el-image :src="captchIma" class="captchIma" @click="getCaptcha()"></el-image>
				</el-form-item>
				
				 <el-form-item style="margin-top: 15px;">
				    <el-button type="primary" @click="submitForm('loginForm')">login</el-button>
				  </el-form-item>
			 
			  </el-form>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	
	import qs from 'qs'
	
  export default {
    data() {
      return {
		radio: 3,
        loginForm: {
          username: '',
          password: '',
          code: '',
		  token: ''
        },
        rules: {
          username: [
            { required: true, message: 'enter one user name', trigger: 'blur' }
          ],
		  password: [
		    { required: true, message: 'Please enter the password', trigger: 'blur' }		    
		  ],
		  vc: [
		    { required: true, message: 'Please enter the verification code', trigger: 'blur' },
			{ min: 5, max: 5, message: 'The length is 5 characters', trigger: 'blur' }
		  ],
         
        },
		captchIma: null
      };
    },
    methods: {
		
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
         if(valid)
		 {
			this.$axios.post('/login?'+qs.stringify(this.loginForm)).then(res =>{
				
				console.log(res)
				
				const jwt  = res.headers['authorization']
				this.$store.commit('SET_TOKEN', jwt)
				this.$router.push("/index")
				
			})
		 }
		 else{
			 console.log('error submit!!');
			 return false;
		 }
		 
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
	  
	  getCaptcha()
	  {
		this.$axios.get('/captcha').then(res =>{
			
			console.log("/captcha")
			console.log(res)
			
		  	this.loginForm.token = res.data.data.token
			this.captchIma = res.data.data.captchIma
			this.loginForm.code=''
		})
		
	  }
    },
	created() {
		this.getCaptcha()
	}
  }
</script>

<style>
	
	.el-divider{
		height: 200px;
	}
	
		
	.el-row{
		background-color: #fafafa ;
		height: 100%;
		display: flex;
		align-items: center;
		text-align: center;
		margin-top: 110px;
	}
	
	.captchIma{
		float: left;
		margin-left: 8px;
		border-radius: 4px;
	}
	
	
</style>