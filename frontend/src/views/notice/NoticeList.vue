<template>
<div class="test">
    <br>
    <table>
        <tr>
            <th style="width:60%;">제목</th>
            <th>글쓴이</th>
            <th>댓글수</th>
            <th>날짜</th>
        </tr>
        <tr v-for="p in paginatedData" :key="p.qnaNo">
            <td style="text-align: left;"><a :href="link+p.qnaNo">{{ p.qnaTitle }}</a></td>
            <td>{{ p.qnaName }}</td>
            <td>{{replys[p.qnaNo]}}</td>
            <td>{{ p.qnaDate }}</td>
        </tr>
    </table>
    <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
            이전
        </button>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
            다음
        </button>
    </div>
</div>
</template>

<script>
import constants from '../../lib/constants.js'
import axios from 'axios'
const resourceHost = constants.baseUrl
const resourceLocal = window.location.href.substring(0, window.location.href.length - 6)
export default {
    name: 'paginated-list',
    data() {
        return {
            pageNum: 0,
            link: resourceLocal + "notice/detail?no=",
            replys: [],
        }
    },
    created: function () {
        axios.get(`${resourceHost}qna/list/`)
            .then(response => {
                response.data.forEach(element => {
                    axios.get(`${resourceHost}noticeReply/` + element.qnaNo)
                        .then(response => {
                            this.$set(this.$data.replys,element.qnaNo,response.data.length)
                        })
                        .catch(err => {
                            //console.log(err);
                        });
                });
            })
            .catch(err => {
                //console.log(err);
            });
    },
    props: {
        listArray: {
            type: Array,
            required: true
        },
        pageSize: {
            type: Number,
            required: false,
            default: 20
        }
    },
    methods: {
        firstPage() {
            this.pageNum = 0;
        },
        nextPage() {
            this.pageNum += 1;
        },
        prevPage() {
            this.pageNum -= 1;
        }
    },
    computed: {
        pageCount() {
            let listLeng = this.listArray.length,
                listSize = this.pageSize,
                page = Math.floor(listLeng / listSize);
            if (listLeng % listSize > 0) page += 1;

            /*
            아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
            이런식으로 if 문 없이 고칠 수도 있다!
            */
            return page;
        },
        paginatedData() {
            const start = this.pageNum * this.pageSize,
                end = start + this.pageSize;
            return this.listArray.slice(start, end);
        }
    }
}
</script>

<style scoped>
table {
    width: 80%;
    margin: 0 auto;
    border-collapse: collapse;
}

table th {
    font-size: 1.2rem;
}

table tr {
    padding: 0px;
    height: 0px;

    text-align: center;
    border-bottom: 1px solid #AEAEAE;
}

table tr:first-of-type {
    border-top: 2px solid #404040;
}

table tr td {
    height: 30px;

    font-size: 1.1rem;
}

.btn-cover {
    margin-top: 1.5rem;
    text-align: center;
}

.btn-cover .page-btn {
    width: 5rem;
    letter-spacing: 0.5px;
}

.btn-cover .page-count {
    padding: 0 1rem;
}

.test {
    background-color: white;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12);
    border-radius: 4px;
}
</style>
