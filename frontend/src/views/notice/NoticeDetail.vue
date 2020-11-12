<template>
<div class="board">
    <br><br>
    <h1 style="cursor: pointer;" @click="clickTitle()">자유 게시판</h1>
    <br>
    <div>
        <h4>{{title}}</h4>
        <table class="info">
            <td><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-app" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M11 2H5a3 3 0 0 0-3 3v6a3 3 0 0 0 3 3h6a3 3 0 0 0 3-3V5a3 3 0 0 0-3-3zM5 1a4 4 0 0 0-4 4v6a4 4 0 0 0 4 4h6a4 4 0 0 0 4-4V5a4 4 0 0 0-4-4H5z" />
                </svg>&nbsp;{{name}}
            </td>
            <td><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-clock" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm8-7A8 8 0 1 1 0 8a8 8 0 0 1 16 0z" />
                    <path fill-rule="evenodd" d="M7.5 3a.5.5 0 0 1 .5.5v5.21l3.248 1.856a.5.5 0 0 1-.496.868l-3.5-2A.5.5 0 0 1 7 9V3.5a.5.5 0 0 1 .5-.5z" />
                </svg>&nbsp;{{date}}
            </td>
            <td style="width:70%; text-align:right; padding-right:10px;"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-three-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
                </svg>
            </td>
        </table>
        <div class="url">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-share-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M12.024 3.797L4.499 7.56l-.448-.895 7.525-3.762.448.894zm-.448 9.3L4.051 9.335 4.5 8.44l7.525 3.763-.448.894z" />
                <path fill-rule="evenodd" d="M13.5 5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm0 11a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm-11-5.5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z" />
            </svg>
            &nbsp;
            <a v-bind:href="link">{{link}}</a>
        </div>
        <br>
        <div class="content">

        </div>
        <div class="content-footer">
            <a href="/notice">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-justify-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2 12.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z" />
                </svg>
            </a>
        </div>
        <hr style="margin-bottom:20px; margin-top:10px;">
        <div>
            <h3><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-right-dots-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h9.586a1 1 0 0 1 .707.293l2.853 2.853a.5.5 0 0 0 .854-.353V2zM5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                </svg>
                &nbsp;{{commentCount}}개의 댓글</h3>
            <br>
            <table class="commentInput">
                <tr style="margin: 0 auto;">
                    <td style="width:90%; margin: 0 auto; padding: 0;">
                        <b-form-textarea id="textarea" v-model="comment" placeholder="Enter something..." rows="3" max-rows="6"></b-form-textarea>
                    </td>
                    <td style="width:10%; margin: 0 auto; padding: 0;">
                        <b-button @click="addComment()" style="width:100%; height:85px;" variant="outline-success">등록</b-button>
                    </td>
                </tr>
            </table>
            <div class="comment">
                <div v-for="c in comments" :key="c.replyNo">
                    <p class="id"> <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-app" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M11 2H5a3 3 0 0 0-3 3v6a3 3 0 0 0 3 3h6a3 3 0 0 0 3-3V5a3 3 0 0 0-3-3zM5 1a4 4 0 0 0-4 4v6a4 4 0 0 0 4 4h6a4 4 0 0 0 4-4V5a4 4 0 0 0-4-4H5z" />
                        </svg>&nbsp;{{c.nickname}}<span style="float:right">{{c.replyDate}}</span></p>
                    <p class="text">{{c.replyContent}}</p>
                </div>

            </div>
            <br>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import constants from '../../lib/constants.js'
import moment from "moment"
const resourceHost = constants.baseUrl

export default {
    name: 'simple-pagination',
    components: {},
    data() {
        return {
            link: document.location.href,
            pageArray: [],
            title: null,
            name: null,
            date: null,
            comment: null,
            qnaNo: document.location.href.split("=")[1],
            comments: null,
            commentCount: 0,
        }
    },
    created() {
        axios.get(`${resourceHost}qna/list/`)
            .then(response => {
                this.pageArray = response.data;
                this.pageArray.forEach(element => {
                    if (element.qnaNo == this.getParam("no")) {
                        this.title = element.qnaTitle;
                        this.name = element.qnaName;
                        this.date = element.qnaDate;
                        document.querySelector('.content').innerHTML = element.qnaContent;
                    }
                });
            })
            .catch(err => {
                //console.log(err);
            });
        axios.get(`${resourceHost}noticeReply/` + this.$data.qnaNo)
            .then(response => {
                this.$data.comments = response.data
                this.$data.commentCount = this.$data.comments.length

                this.$data.comments.forEach(element => {
                    var commentDate = moment(element.replyDate)
                    var timeDiff = moment.duration(moment().diff(commentDate))
                    if (timeDiff.asYears() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asYears()) + "년 전"
                    } else if (timeDiff.asMonths() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asMonths()) + "개월 전"
                    } else if (timeDiff.asWeeks() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asWeeks()) + "주 전"
                    } else if (timeDiff.asHours() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asHours()) + "시간 전"
                    } else if (timeDiff.asMinutes() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asMinutes()) + "분 전"
                    } else if (timeDiff.asMilliseconds() >= 1) {
                        element.replyDate = Math.floor(timeDiff.asMilliseconds() / 1000) + "초 전"
                    }
                })
            })
            .catch(err => {
                //console.log(err);
            });
    },
    methods: {
        clickTitle: function () {
            this.$router.push("/notice")
        },
        addComment: function () {
            let user_nickname = sessionStorage.getItem("user_nickname")
            if (user_nickname == "") {
                alert("로그인 해주세요")
                return
            }
            if (this.$data.comment == null) {
                alert("내용을 입력해주세요")
                return
            }
            axios.post(`${resourceHost}noticeReply/add`, {
                    noticeNo: this.$data.qnaNo * 1,
                    nickname: user_nickname,
                    replyContent: this.$data.comment,
                })
                .then(response => {
                    //console.log(response)
                })
                .catch(err => {
                    //console.log(err);
                });
            location.reload()
        },
        getParam: function (sname) {
            var params = location.search.substr(location.search.indexOf("?") + 1);
            var sval = "";
            var temp;
            params = params.split("&");
            for (var i = 0; i < params.length; i++) {
                temp = params[i].split("=");
                if ([temp[0]] == sname) {
                    sval = temp[1];
                }
            }
            return sval;
        }
    }
}
</script>

<style scoped>
.id {
    border: 1px solid #ADD9D1;
    background-color: #ADD9D1;
    padding: 10px;
    border-radius: 5px;
}

.text {
    padding-left: 10px;
}

.comment {
    width: 80%;
    margin: 0 auto;
    text-align: left;
    line-height: 0.7em
}

.commentInput {
    width: 80%;
    margin: 0 auto;
    padding: 0;
    margin-bottom: 20px;
}

.info {
    padding-left: 0px;
    margin: 0 auto;
    width: 80%;
    border-top: 3px ridge #44A69C;
    background-color: #ADD9D1;
    height: 0px;
}

.board {
    margin: 0 auto;
    width: 80%;
    height: 100%;
    background-color: white;
}

.url {
    margin: 0 auto;
    width: 80%;
    text-align: left;
}

.content {
    text-align: left;
    margin: 0 auto;
    width: 80%;
    min-height: 100px;
}

.content-footer {
    margin: 0 auto;
    width: 80%;
    text-align: left;
}

hr {
    margin: 0 auto;
    width: 80%;
}

h1 {
    font-weight: bold;
    color: #44A69C;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h3 {
    font-weight: bold;
    color: #000000;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h4 {
    font-weight: bold;
    color: black;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h5:hover {
    color: #000000;
    cursor: pointer;
}
</style>
