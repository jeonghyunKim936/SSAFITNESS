<template>
<div class="board">
    <br><br>
    <h1>자유 게시판</h1>
    <br>
    <div style="width:80%; margin:0 auto; font-size: 20px; margin-bottom:10px;">
        <svg @click="showSearchModal" style="float:left; cursor: pointer;" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
        </svg>
        <span @click="clickWriteButton()" style="float:right; color:#44A69C; cursor: pointer;">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M11.293 1.293a1 1 0 0 1 1.414 0l2 2a1 1 0 0 1 0 1.414l-9 9a1 1 0 0 1-.39.242l-3 1a1 1 0 0 1-1.266-1.265l1-3a1 1 0 0 1 .242-.391l9-9zM12 2l2 2-9 9-3 1 1-3 9-9z" />
                <path fill-rule="evenodd" d="M12.146 6.354l-2.5-2.5.708-.708 2.5 2.5-.707.708zM3 10v.5a.5.5 0 0 0 .5.5H4v.5a.5.5 0 0 0 .5.5H5v.5a.5.5 0 0 0 .5.5H6v-1.5a.5.5 0 0 0-.5-.5H5v-.5a.5.5 0 0 0-.5-.5H3z" />
            </svg>
            쓰기
        </span>

        <!-- 검색 모달 -->
        <b-modal centered class="search-class" ref="search-modal" hide-footer title="검색">
            <div class="d-block text-center">
                <b-form-select style="margin-bottom:10px;" v-model="selected" :options="options" class="mt-3"></b-form-select>
                <b-form-input v-model="keyword" placeholder=""></b-form-input>
            </div>
            <br>
            <div style="float:right;">
                <b-button variant="light" style="color:#44A69C;" @click="searchQnAList(selected,keyword)">검색</b-button>
                &nbsp;&nbsp;
                <b-button variant="light" @click="hideSearchModal()">취소</b-button>
            </div>
        </b-modal>
    </div>
    <notice-list :list-array="pageArray" ref="noticeList" />
    <br><br>
</div>
</template>

<script>
import axios from 'axios'
import NoticeList from './NoticeList'
import constants from '../../lib/constants.js'
import moment from "moment"
const resourceHost = constants.baseUrl

export default {
    name: 'simple-pagination',
    components: {
        NoticeList
    },
    data() {
        return {
            pageArray: [],
            selected: 'title',
            keyword: null,
            options: [{
                    value: 'title',
                    text: '제목'
                },
                {
                    value: 'name',
                    text: '아이디'
                },
                {
                    value: 'content',
                    text: '내용'
                },
            ],
        }
    },
    created() {
        axios.get(`${resourceHost}qna/list/`)
            .then(response => {
                this.pageArray = response.data;

                this.pageArray.forEach(element => {
                    var commentDate = moment(element.qnaDate)
                    var timeDiff = moment.duration(moment().diff(commentDate))
                    if (timeDiff.asYears() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asYears()) + "년 전"
                    } else if (timeDiff.asMonths() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asMonths()) + "개월 전"
                    } else if (timeDiff.asWeeks() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asWeeks()) + "주 전"
                    } else if (timeDiff.asHours() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asHours()) + "시간 전"
                    } else if (timeDiff.asMinutes() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asMinutes()) + "분 전"
                    } else if (timeDiff.asMilliseconds() >= 1) {
                        element.qnaDate = Math.floor(timeDiff.asMilliseconds() / 1000) + "초 전"
                    }
                })
            })
            .catch(err => {
                //console.log(err);
            });
    },
    methods: {
        showSearchModal() {
            this.$refs['search-modal'].show()
        },
        hideSearchModal() {
            this.$refs['search-modal'].hide()
        },
        searchQnAList(type, keyword) {
            if (keyword == null) {
                alert("내용을 입력해주세요")
                return
            }
            axios.get(`${resourceHost}qna/search/` + type + "/" + keyword)
                .then(response => {
                    this.pageArray = response.data;
                    this.$refs.noticeList.firstPage()
                    //console.log("search success");
                })
                .catch(err => {
                    //console.log(err);
                })
                .finally(
                    this.hideSearchModal()
                )
        },
        clickWriteButton() {
            if (this.$store.state.email == "") {
                alert("로그인이 필요합니다.")
                return
            }
            this.$router.push("/notice/input");
        },
    },
}
</script>

<style scoped>
.board {
    margin: 0 auto;
    width: 80%;
    height: 50vh;
    background-color: white;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12),
        0 1px 2px rgba(0, 0, 0, 0.24);
    border-radius: 4px;
}

h1 {
    font-weight: bold;
    color: #44A69C;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h5 {
    font-weight: bold;
    color: #454545;
    width: 5px;
    margin-left: 10%;
}

h5:hover {
    color: #000000;
    cursor: pointer;
}

.modal-title {
    width: 100%;
    margin: 0;
}
</style>
