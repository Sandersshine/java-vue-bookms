<template>
  <div id="app">

    <el-menu class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff"
      active-text-color="#ffd04b">
      <div class="site-name">海豹图书管理系统</div>
      <el-menu-item index="1"><router-link to="/">主页</router-link></el-menu-item>
      <el-menu-item index="2"><router-link to="/add">添加图书</router-link></el-menu-item>
      <el-menu-item index="3"><router-link to="/search">搜索图书</router-link></el-menu-item>
    </el-menu>
    <p></p>
    <!-- <nav>
      <router-link to="/">主页</router-link> |
      <router-link to="/add">添加图书</router-link> |
      <router-link to="/search">搜索图书</router-link>
    </nav> -->
    <router-view />
  </div>
</template>

<script>
import home from './components/home.vue'
export default {
  data() {
    return {

    }
  },
  methods: {

  },
  name: 'App',
  components: {
    home,
  },
  created() {
    console.log(this.activeIndex);
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
  font-size: large;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.site-name {
  color: antiquewhite;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size: 22px;
  position: fixed;
  left: 10px;
  padding: 10px;
  /* background-color: #834c4c; */
  /* 背景色，可以根据需要调整 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* 阴影效果，增强视觉效果 */
}

.el-menu {
  align-items: center;
  justify-content: center;
  display: flex;
}
</style>
