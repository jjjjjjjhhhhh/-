import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import User from '../views/sys/User.vue'
import Role from '../views/sys/Role.vue'
import Menu from '../views/sys/Menu.vue'
import axios from '../axios.js' 
import store from '../store'
import center from '../views/center/center.vue'
import jkjc from '../views/helthe/jkjc.vue'
import jklr from '../views/helthe/jklr.vue'
import jktx from '../views/helthe/jktx.vue'
import jkjh from '../views/plan/jkjh.vue'
import jkzx from '../views/plan/jkzx.vue'
import zxwz from '../views/ycwz/zxwz.vue'
import ysgl from '../views/ysgl/ysgl.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
	children: [
		{
		  path: '/index',
		  name: 'Index',
		  component: Index
		},
		{
		  path: '/userCenter',
		  name: 'UserCenter',
		  component:  () => import('../views/UserCenter.vue')
		},
		// {
		//   path: '/sys/user',
		//   name: 'SysUser',
		//   component: User
		// },
		// {
		//   path: '/sys/role',
		//   name: 'SysRole',
		//   component: Role
		// },
		// {
		//   path: '/sys/menu',
		//   name: 'SysMenu',
		//   component: Menu
		// }
	]
  },
  
  {
    path: '/login',
    name: 'Login',
	component: Login
    // component: () => import('../views/Login.vue')
  },
]

const router = new VueRouter({
	
	mode: 'history',
	linkActiveClass: 'active',
	
  routes
})


//一般用来做一些进入页面的限制。
//比如没有登录，就不能进入某些页面，只有登录了之后才有权限查看某些页面。。。说白了就是路由拦截。
router.beforeEach( (to , from , next ) => {
	
	
	let hashRoute = store.state.menus.hashRoute
	
	if(!hashRoute)
	{
		axios.get("/sys/menu/nav", {
			Headers: {
				Authorization: localStorage.getItem("token")
			}	
		}).then(res =>{
			//拿到menuList
			
			 console.log(res.data.data)
			
			store.commit("setMenuList", res.data.data.nav)
		
			//拿到用户的权限
			store.commit("setPermList", res.data.data.authoritys)
			
			console.log(store.state.menus.menuList)
			
			//动态绑定路由
			let newRoutes = router.options.routes
			res.data.data.nav.forEach(menu => {
			
			if(menu.children)
			{
				menu.children.forEach(e => {
					
					//转化成路由
					let route = menuToRoute(e)
					
					//把路由添加到管理中
					if(route)
					{
						newRoutes[0].children.push(route)
					}
					
				})
			}
				
			})
			
			console.log("newRoutes")
			console.log(newRoutes)
			
			router.addRoutes(newRoutes)
			
			hashRoute = true
			store.commit("changeRouteStatus", hashRoute)
			
		})
	}
	
	
	
	
	next()
	
})


//导航转成路由
const menuToRoute = (menu) => {
	
	if(!menu.component)
	{
		return null
	}
	
	let route = {
		name: menu.name,
		path: menu.path,
		meta: {
			icon: menu.icon,
			title: menu.title
		}
	}
	
	route.component = () => import('@/views/'+ menu.component +'.vue')
	
	return route
	
}



export default router