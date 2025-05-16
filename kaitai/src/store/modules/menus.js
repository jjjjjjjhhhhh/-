import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default ({
  state: {
	
	menuList: [],
	permList: [],
	
	// hashRoute: sessionStorage.getItem("hashRoute")
	hashRoute: false,
	
	editableTabsValue: 'Index',
	editableTabs: [{
	  title: 'Index',
	  name: 'Index',
	}]
	
  },
  mutations: {
	  
	setMenuList(state, menus) {
		state.menuList = menus
	},  
	
	setPermList(state, perms) {
		state.permList = perms
	},
	  
	changeRouteStatus(state,hashRoute) {
		state.hashRoute = hashRoute,
		sessionStorage.setItem("hashRoute", hashRoute)
	},
	  
	addTab(state, tab)
	{
	
		let index = state.editableTabs.findIndex(e=> e.name === tab.name)
		if(index === -1)
		{
			let newTabName = '';
			state.editableTabs.push({
				title: tab.title,
				name: tab.name,
			});
		}
		
		state.editableTabsValue = tab.name;
	},
	
	resetState: (state) => {
		state.menuList = []
		state.permList = []
		state.hashRoute = false
		state.editableTabsValue = 'Index'
		state.editableTabs = [{
	  title: 'Index',
	  name: 'Index',
	  }]
		
	}
	  
	  
	  
	
		
  },
  actions: {
  },

})
