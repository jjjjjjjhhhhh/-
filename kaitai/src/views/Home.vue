<template>
	<el-container>
	  <el-aside width="250px">
			
			<SideMenu></SideMenu>
			
	  </el-aside>
	  <el-container>
	    <el-header>
			<strong>Family healthy life information management system</strong>
			<div class="header-avater">
				<el-avatar size="medium"  :src="userInfo.avatar"></el-avatar>
				<el-dropdown>
				  <span class="el-dropdown-link">
				    {{userInfo.username}}<i class="el-icon-arrow-down el-icon--right"></i>
				  </span>
				  <el-dropdown-menu slot="dropdown">
					  <router-link to="/userCenter">
						  <el-dropdown-item>Personal Center</el-dropdown-item>
					  </router-link>
				    
				    <el-dropdown-item @click.native="logout">quit</el-dropdown-item>
				  </el-dropdown-menu>
				</el-dropdown>
				
				<!-- <el-link href="https://www.douyin.com/user/MS4wLjABAAAAupHc2knw3eqQApL6H5biwgm9uyb8ywBnGgL2zKWoa74" target="_blank">抖音主页</el-link>
				<el-link href="https://space.bilibili.com/1537648083?spm_id_from=333.1007.0.0" target="_blank">B站</el-link> -->
				
			</div>
		</el-header>
			<el-main>
				<Tabs></Tabs>
				<div style="margin: 0 15px;">
					<router-view></router-view>	
				</div>
			</el-main>
	  </el-container>
	</el-container>
</template>

<script>
	import SideMenu from "./inc/sideMenu.vue"
	import Tabs from "./inc/Tabs.vue"
	
	export default {
		name: "Home",
		components: {
			SideMenu,Tabs
		},
		
		data () {
			return {
				userInfo: {
					id: "",
					username: "",
					avatar: ""
				}
			}
		},
		
		created() {
			
			this.getUserInfo()
			
		},
		methods: {
			getUserInfo() {
				this.$axios.get("/sys/userInfo").then(res =>{
					this.userInfo = res.data.data
				})
			},
			
			logout() {
				this.$axios.post("/logout").then(res=> {
					localStorage.clear()
					sessionStorage.clear()
					
					this.$store.commit("resetState")
					this.$router.push("/Login")
					
				})
			}
		}
	}
</script>

<style>
	
	.el-menu-vertical-demo{
		text-align: left;
		height: 100%;
	}
	
	.el-dropdown-link {
	    cursor: pointer;
	   /* color: #409EFF; */
	  }
	
	
	.header-avater{
		float: right;
		width: 210px;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	
	.el-container {
		padding: 0;
		margin: 0;
		height: 100vh;
	}
	
	.el-header {
	    background-color: #17b3a3;
	    color: #333;
	   text-align: center;
	    line-height: 60px;
	  }
	  
	  .el-aside {
	    background-color: #D3DCE6;
	    color: #333;
	    line-height: 200px;
	  }
	  
	  .el-main {
	    color: #333;
		padding: 0;
	  } 
	  
	  a {
		  text-decoration: none;
	  }
	
</style>