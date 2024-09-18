<template>
    <div>
        <h2>{{ book.title }}</h2>
        <p>作者: {{ book.author }}</p>
        <p>出版社: {{ book.publisher }}</p>
        <p>ISBN号: {{ book.isbn }}</p>
        <p>页数: {{ book.pages }}</p>
        <p>描述: {{ book.description }}</p>
        <el-button @click="goBack">返回</el-button>
    </div>
</template>  
  
<script>
export default {
    props: {
        book: {
            type: Object,
            required: true
        }
    },
    methods: {
        goBack() {
            // 返回到图书列表页或主页  
            this.$router.go(-1);
        }
    }
};  
</script>