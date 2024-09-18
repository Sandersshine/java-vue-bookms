<template>
    <div>
        <el-table id="table-container" :data="paginatedBooks" :cell-style="{ textAlign: 'center' }"
            :header-cell-style="{ textAlign: 'center' }" stripe border>
            <el-table-column label="序号" type="index" :index="transIndex" width="50"></el-table-column>
            <el-table-column prop="name" label="书名">
                <template slot-scope="scope">
                    <span v-if="!scope.row.editing">{{ scope.row.name }}</span>
                    <el-input v-else v-model="scope.row.name"></el-input>
                </template>
            </el-table-column>
            <el-table-column prop="author" label="作者">
                <template slot-scope="scope">
                    <span v-if="!scope.row.editing">{{ scope.row.author }}</span>
                    <el-input v-else v-model="scope.row.author"></el-input>
                </template>
            </el-table-column>

            <el-table-column prop="press" label="出版社">
                <template slot-scope="scope">
                    <span v-if="!scope.row.editing">{{ scope.row.press }}</span>
                    <el-input v-else v-model="scope.row.press"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button v-if="!scope.row.editing" @click="editBook(scope.row)">编辑</el-button>
                    <el-button v-else @click="saveBook(scope.row)">保存</el-button>

                    <el-button v-if="!scope.row.editing" @click="deleteBook(scope.$index, scope.row.id)"
                        type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 15]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="books.length">
        </el-pagination>
    </div>
</template>   
  
<script>
import axios from 'axios';
export default {
    data() {
        return {
            books: [], // 假设这是你从后端获取的所有图书数据  
            currentPage: 1,
            pageSize: 5,
            paginatedBooks: []
        };
    },
    methods: {
        // 获取分页后的图书数据  
        getPaginatedBooks() {
            var [start, end] = this.updateStartEnd();
            this.paginatedBooks = this.books.slice(start, end);
        },
        // 处理条数变化事件  
        handleSizeChange(newSize) {
            this.pageSize = newSize;
            this.getPaginatedBooks();
        },
        handleCurrentChange(newPage) {
            this.currentPage = newPage;
            this.getPaginatedBooks();
        },
        addBook() {
            // 跳转到添加图书页面  
            this.$router.push('/add'); // 假设添加图书的路由是'/add-book'  
        },
        editBook(row) {
            // console.log(row);
            // 跳转到编辑图书页面，并传入图书数据  
            this.$set(row, 'editing', true)
        },
        saveBook(row) {
            // console.log(row);
            // 这里可以添加保存数据的逻辑，例如发送请求到后端更新数据  
            this.$set(row, 'editing', false); // 保存后切换回非编辑状态  

            axios.put("/update", {
                id: row.id,
                name: row.name,
                author: row.author,
                press: row.press
            })
                .then(response => {
                    // 请求成功处理逻辑  
                    if (response.data) {
                        this.$message.success('更新成功');
                    }
                    else {
                        this.$message.error('更新失败');
                    }
                });
        },
        deleteBook(index, id) {
            // 计算要删除的图书在books数组里的下标
            index = this.transIndex(index) - 1;
            // 删除图书数据，并更新表格  
            axios.delete("/delete", {
                params: {	// 请求参数拼接在url上
                    id: id
                }
            })
                .then(response => {
                    // 请求成功处理逻辑  
                    if (response.data) {
                        this.books.splice(index, 1);
                        // console.log(this.books);
                        this.getPaginatedBooks();

                        // 当前页删完了 就转到前一页
                        var restlen = this.paginatedBooks.length;
                        // console.log("还剩下 " + restlen);
                        if (restlen == 0) {
                            this.currentPage = this.currentPage - 1;
                            this.getPaginatedBooks();
                        }

                        this.$message.success('删除成功');
                    }
                    else {
                        this.$message.error('删除失败');
                    }
                });
        },
        transIndex(index) {
            return (this.currentPage - 1) * this.pageSize + index + 1;
        },
        updateStartEnd() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = this.currentPage * this.pageSize;
            return [start, end];
        }
    },
    mounted() {
        this.$store.dispatch("fetchBooks").then(() => {
            this.books = this.$store.getters.getBooks;
            // console.log(this.books); // 这将在数据被异步请求更新后执行  
            this.getPaginatedBooks();
        });
        // axios.post("/all").then(res => {
        //     // console.log("mounted：");
        //     // console.log(res)
        //     this.books = res.data;

        //     this.getPaginatedBooks();
        //     // console.log(this.books.slice(start, end));

        //     var arr = this.books.filter(book => {
        //         return (
        //             book.name.search("红") != -1
        //         );
        //     });

        //     console.log("arr：");
        //     console.log(arr);
        // })
    },
};  
</script>

<style>
#table-container {
    margin: 0 auto;
    width: 60%;
}

.el-form-item {
    margin: 0 auto;
}
</style>