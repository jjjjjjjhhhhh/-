import axios from "axios";
import Element from 'element-ui'
import router from "./router";
axios.defaults.baseURL = "http://localhost:8082"
const request = axios.create({
   timeout: 5000,
   headers: {
      'Content-Type': 'application/json; charset=utf-8'
   }
})
request.interceptors.request.use(config => {
   config.headers['Authorization'] = localStorage.getItem("token") // 请求头带上token
   return config
})
request.interceptors.response.use(response => {
      let res = response.data;
      console.log("response")
      console.log(res)
      if (res.code === 200) {
         return response
      } else {
         // Element.Message.error(res.msg? res.msg : '系统异常！', {duration: 3 * 1000})
         return Promise.reject(response.data.msg)
      }
   },
   error => {
      console.log(error)
      if(error.response.data) {
         error.message = error.response.data.msg
      }
      if(error.response.status === 400) {
         router.push("/Login")
      }
      Element.Message.error(error.message, {duration: 3 * 1000})
      return Promise.reject(error)
   }
)
export default request