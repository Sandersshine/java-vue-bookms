<template>
    <div>
        <el-form :model="bookForm" ref="bookForm" label-width="120px">
            <el-form-item label="书名">
                <el-input v-model="bookForm.title"></el-input>
            </el-form-item>
            <el-form-item label="作者">
                <el-input v-model="bookForm.author"></el-input>
            </el-form-item>
            <el-form-item label="出版社">
                <el-input v-model="bookForm.publisher"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="updateBook">更新</el-button>
                <el-button @click="cancelEdit">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>  
  
<script>
export default {
    props: {
        // 从父组件传递要编辑的图书数据  
        book: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            bookForm: {
                title: '',
                author: '',
                publisher: ''
            }
        };
    },
    created() {
        // 初始化表单数据为要编辑的图书数据  
        this.bookForm = {
            title: this.book.title,
            author: this.book.author,
            publisher: this.book.publisher
        };
    },
    methods: {
        updateBook() {
            // 提交表单，更新图书数据  
            this.$refs.bookForm.validate(valid => {
                if (valid) {
                    // 调用父组件的方法更新图书数据  
                    this.$emit('update', this.bookForm);
                    this.$message.success('更新成功');
                    this.cancelEdit();
                } else {
                    this.$message.error('更新失败，请检查表单项');
                    return false;
                }
            });
        },
        cancelEdit() {
            // 重置表单并返回到主页或图书列表页  
            this.bookForm = {
                title: '',
                author: '',
                publisher: ''
            };
            this.$emit('cancel');
        }
    }
};  
</script>