<template>
    <div>
        <el-form :model="bookForm" ref="bookFormRef" label-width="100px" :rules="rules">
            <el-form-item label="书名" prop="name">
                <el-input v-model="bookForm.name"></el-input>
            </el-form-item>
            <el-form-item label="作者" prop="author">
                <el-input v-model="bookForm.author"></el-input>
            </el-form-item>
            <el-form-item label="出版社" prop="press">
                <el-input v-model="bookForm.press"></el-input>
            </el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
            <el-button @click="cancelForm">取消</el-button>
        </el-form>
    </div>
</template>  
  
<script>
import axios from 'axios';
export default {
    data() {
        return {
            bookForm: {
                name: '',
                author: '',
                press: ''
            },
            rules: {
                name: [
                    { required: true, trigger: 'blur', message: '请输入书名' }
                ],
                author: [
                    { required: true, trigger: 'blur', message: '请输入作者' },
                    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                ],
                press: [
                    { required: true, trigger: 'blur', message: '请输入出版社' }
                ]
            }
        };
    },
    methods: {
        submitForm() {
            console.log(this.bookForm);
            // 提交表单，将图书数据添加到主页的数据列表中  
            this.$refs.bookFormRef.validate(valid => {
                if (valid) {
                    // 添加图书到数据列表  
                    axios.post('/insert', this.bookForm)
                        .then(response => {
                            // 如果请求成功  
                            if (response.data === 1) {
                                this.$message.success('添加成功');
                            } else {
                                this.$message.error('添加失败，请检查表单项');
                            }
                        })
                        .catch(error => {
                            // 如果请求失败  
                            console.error(error);
                            this.$message.error('添加失败，请检查网络或重试');
                        });
                    this.resetForm();
                } else {
                    this.$message.error('添加失败，请检查表单项');
                    return false;
                }
            });
        },
        resetForm() {
            this.$refs.bookFormRef.resetFields();
        },
        cancelForm() {
            // 返回到主页  
            this.$router.push('/');
        }
    }
};  
</script>

<style>
.el-form-item {
    width: 600px;
    margin-bottom: 22px;
    margin-left: auto;
    margin-right: auto;
    padding-right: 40px;
}
</style>