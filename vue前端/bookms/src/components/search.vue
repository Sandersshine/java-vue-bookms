<template>
    <div>
        <el-input id="serachBox" style="width: 40%" v-model="searchKeyword" placeholder="请输入关键词检索图书"></el-input>
        <el-button @click="searchBooks">检索</el-button>
        <p></p>
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
            :total="searchResults.length">
        </el-pagination>
    </div>
</template>  
  
<script>
export default {
    data() {
        return {
            searchKeyword: '',
            allBooks: [], // 假设这是你的全部图书数据集  
            searchResults: [], // 用于存放检索结果  
            currentPage: 1,
            pageSize: 5,
        };
    },
    created() {
        // 假设在组件创建时从API或其他地方获取图书数据  
        this.allBooks = this.$store.getters.getBooks;
        this.getPaginatedBooks();
    },
    methods: {
        // async getAllBooks() {
        //     // 这里应该是从API或其他地方获取图书数据的逻辑  
        //     // 假设我们已经有了一个函数来获取这些数据  
        //     this.allBooks = await this.fetchBooksFromApi();
        // },
        searchBooks() {
            // 清除之前的搜索结果  
            this.searchResults = [];

            // 根据关键词进行检索  
            if (this.searchKeyword) {
                this.searchResults = this.allBooks.filter(book => {
                    return (
                        book.name.search(this.searchKeyword) != -1 ||
                        book.author.search(this.searchKeyword) != -1 ||
                        book.press.search(this.searchKeyword) != -1
                    );
                }
                );
                console.log(this.searchResults);
                this.getPaginatedBooks();
            }
        },
        // 获取分页后的图书数据  
        getPaginatedBooks() {
            var [start, end] = this.updateStartEnd();
            this.paginatedBooks = this.searchResults.slice(start, end);
        },
        transIndex(index) {
            return (this.currentPage - 1) * this.pageSize + index + 1;
        },
        updateStartEnd() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = this.currentPage * this.pageSize;
            return [start, end];
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
    }
};  
</script>

<style>
#table-container {
    margin: 0 auto;
    width: 60%;
}

#serachBox {
    width: 95%;
}
</style>