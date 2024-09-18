import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    books: [], // 初始图书数据
  },
  getters: {
    getBooks: state => state.books // 创建一个getter来获取所有图书
  },
  mutations: {
    setBooks(state, books) {  
      state.books = books
      // console.log(books);
    }
  },
  // 异步操作要放到action里
  actions: {
    // 在then里返回异步处理结果
    fetchBooks({ commit }) {  
      return axios.post("/all")
              .then(res => {  
                const books = res.data;  
                commit('setBooks', books);  
                
                return books; // 返回 Promise 解析后的数据
              })
              .catch(error => {  
                console.error('Error fetching books:', error);  
              });  
    } 
  },
  modules: {
  }
})
