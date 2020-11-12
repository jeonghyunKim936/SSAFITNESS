<template>
  <div id="articleDetail">
    <div class="article-container">
      <div class="title-sub-grid">
        <span class="article_title">{{ article.pt_title }}</span>
        <ul class="button-list">
          <li><router-link tag="button" class="pt-update-button" :disabled="article.nickname !== $store.state.nickname" :to="{ name: 'ArticleUpdate', params: { pt_id: pt_id } }"><v-icon style="color: ligthgray">mdi-circle-edit-outline</v-icon></router-link></li>
          <li><button class="pt-delete-button" @click="onDelete" :disabled="article.nickname !== $store.state.nickname"><v-icon style="color: ligthgray">mdi-trash-can</v-icon></button></li>
        </ul>
      </div>
      <div class="video-sub-grid">
        <video controls>
          <source :src="article.pt_movie" type="video/mp4">
        </video>
      </div>
      <div class="pt-caption">
        <span>{{ article.nickname }} | {{ article.pt_date }}</span>
        <ul class="caption-list">
          <li><span class="badge">{{ article.pt_type }}</span></li>
          <li><v-icon>mdi-comment-processing</v-icon> {{ replys.length }}</li>
        </ul>
      </div>
      <div class="text-sub-grid">
        <div class="text-grid-item">
          <p>{{ article.pt_content }}</p>
        </div>
      </div>
      <hr>

      <div class="comments container">
        <div class="comment-wrap row">
          <div class="comment-block col-11">
            <textarea name="" id="" placeholder="댓글을 입력하세요" v-model="replyData.content" required></textarea>
          </div>
          <div class="comment-side col-1">
            <button @click="onComment">등록</button>
          </div>
        </div>
        <div class="comment-wrap row" v-if="$store.state.grade === 'trainer'">
          <div class="comment-side col-12">
            <input type="file" accept=".mp4" ref="file" @change="onCommentVideo">
          </div>
        </div>

        <!-- Best Replys -->
        <div class="comment-wrap row" style="position: relative;" v-for="(bestReply, idx) in bestReplys" :key="idx">
          <div class="comment-block-content col-12" style="background-color: #fcfae1;">
            <div class="comment-date">
              <span>{{ bestReply.nickname }}</span>
              <span>{{ bestReply.date }}</span> <br>
            </div>
            <p class="comment-text">{{ bestReply.content }}</p>
            <v-icon style="position: absolute; right: 50%; top: -25px; color: gold;" class="bestIcon">mdi-crown</v-icon>
            <span v-if="bestReply.grade === 'trainer'" class="badge grade-information" style="position: absolute; right: 5px; top: 5px;">트레이너</span>
            <button class="btnMovie" v-if="bestReply.reply_movie" data-toggle="modal" :data-target="`#exampleModal${bestReply.reply_id}`"><v-icon style="color: #ffffff;">mdi-youtube</v-icon></button>
            <div class="bottom-comment" style="margin-right:20px;">
              <div class="comment-like">
                <span style="position: absolute; left:5px; bottom:0px; font-size:0.875rem;">
                  <!-- <button class="like-button-best"> -->
                    <v-icon class="arm-icon">mdi-arm-flex</v-icon>
                  <!-- </button> -->
                  <!-- <button v-show="bestReply.checkedLike === 0" class="like-button-best" @click="onLikeBtn(bestReply)"><v-icon class="arm-icon">mdi-arm-flex-outline</v-icon></button> -->
                  {{ bestReply.like }}명이 좋아합니다
                </span>
              </div>

            </div>
          </div>
          <div class="modal fade" :id="`staticBackdrop${bestReply.reply_id}`" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel"><v-icon>mdi-circle-edit-outline</v-icon></h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <input type="text" id="updateReplyInput" value="" v-model="replyData.content">
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" @click="onCommentUpdate(bestReply)">수정 완료</button>
                </div>
              </div>
            </div>
          </div>
          
          <div class="modal fade" :id="`exampleModal${bestReply.reply_id}`" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content bg-transparent bg-gradient-none" style="border-style: none">
                <div class="modal-header" style="border-bottom: 0">
                  <button @click="videoPause(bestReply)" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <video :id="`video${bestReply.reply_id}`" :src="bestReply.reply_movie" controls></video>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- replys -->
        <div class="comment-wrap row" style="position: relative;"  v-for="reply in replys" :key="reply.reply_id">
          <div class="comment-block-content col-12" style="overflow:auto; white-space:normal;">
            <div class="comment-date">
              <span>{{ reply.nickname }}</span>
              <span>{{ reply.date }}</span> <br>
            </div>
            <p class="comment-text">{{ reply.content }}</p>
            <button class="btnMovie" v-if="reply.reply_movie" data-toggle="modal" :data-target="`#exampleModal${reply.reply_id}`"><v-icon style="color: #ffffff;">mdi-youtube</v-icon></button>
            <div class="bottom-comment">
              <div class="comment-like">
                <span style="position: absolute; left:5px; bottom:0px; font-size:0.875rem;">
                  <button v-if="reply.checkedLike === 1" class="like-button-normal" @click="offLikeBtn(reply)"><v-icon class="arm-icon">mdi-arm-flex</v-icon></button>
                  <button v-if="reply.checkedLike === 0" class="like-button-normal" @click="onLikeBtn(reply)"><v-icon class="arm-icon">mdi-arm-flex-outline</v-icon></button>
                  {{ reply.like }}
                </span>
              </div>
              <ul class="comment-actions" style="position: absolute; right:0px; top:0px;">
                <li v-if="reply.grade === 'trainer'"><span class="badge grade-information">트레이너</span></li>
                <li class="complain" v-if="$store.state.nickname === reply.nickname && $store.state.nickname !== ''" data-toggle="modal" :data-target="`#staticBackdrop${reply.reply_id}`"><v-icon>mdi-circle-edit-outline</v-icon></li>
                <li class="reply" v-if="$store.state.nickname === reply.nickname && $store.state.nickname !== ''" @click="onCommentDelete(reply)"><v-icon>mdi-trash-can</v-icon></li>
              </ul>
            </div>
          </div>
          <!-- comment edit -->
          <div class="modal fade" :id="`staticBackdrop${reply.reply_id}`" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel"><v-icon>mdi-circle-edit-outline</v-icon></h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <textarea id="updateReplyInput" value="" v-model="replyData.content"></textarea>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" @click="onCommentUpdate(reply)">수정 완료</button>
                </div>
              </div>
            </div>
          </div>
          <!-- comment video -->
          <div class="modal fade" :id="`exampleModal${reply.reply_id}`" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content bg-transparent bg-gradient-none" style="border-style: none">
                <div class="modal-header" style="border-bottom: 0">
                  <button @click="videoPause(reply)" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <video :id="`video${reply.reply_id}`" :src="reply.reply_movie" controls></video>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import '../../assets/css/pt/articledetail.scss'
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl;

export default {
    name: 'ArticleDatail',
    data() {
      return {
        pt_id: this.$route.params.pt_id,
        article: null,
        replys: [],
        bestReplys: [],
        replyData: {
          pt_id: 0,
          content: '',
          nickname: this.$store.state.nickname,
          like: 0,
          reply_id: 0,
          replyFile: "",
        },
        
      }
    },
    methods: {
      fetchArticle() {
        axios.get(`${resourceHost}pt/${this.pt_id}/`)
          .then(res => {
            this.article = res.data
          })
          .catch(err => console.log(err))
      },
      fetchReply() {
        // promise 안에서의 this는 우리가 원하는 this가 아니므로 추가 설정
        var _this = this;
        new Promise(function(){
        axios.get(`${resourceHost}pt/${_this.pt_id}/reply_list/`, {
          params: {
            nickname: sessionStorage.getItem('user_nickname'),
          }
        },)
          .then(res => {
            _this.bestReplys = res.data[0]
            _this.replys = res.data[1]
          })
          .catch(err => console.log(err))
        });
      },
      onDelete() {
        axios.delete(`${resourceHost}pt/deleteFile/${this.pt_id}/`, {
          heaers: {
            'Content-Type': 'application/json',
          }
        })
          .then(() => {
            alert('성공적으로 삭제하였습니다.')
            this.$router.push('/pt/list')
          })
          .catch(err => console.log(err))
      },
      onCommentVideo() {
        var formData = new FormData();
        formData.append('file', this.$refs.file.files[0]);
        this.replyData.replyFile = formData
      },
      videoPause(reply) {  
        var vid = document.getElementById(`video${reply.reply_id}`)
        vid.pause();
      },
      onComment() {
        // this.replyData.nickname = this.$store.state.nickname
        if (this.$store.state.nickname) { // 로그인 여부
          if (this.replyData.content) { // 댓글 내용 입력 여부
            this.replyData.pt_id = this.pt_id,
            this.replyData.nickname = this.$store.state.nickname
            if (!this.replyData.replyFile) { // 동영상 첨부 여부
              axios.post(`${resourceHost}pt/${this.pt_id}/reply_insert/`, this.replyData, {
                headers: {
                  'Content-Type': 'application/json',
                }
              })
                .then(res => {
                  this.bestReplys = res.data[0]
                  this.replys = res.data[1]
                  this.replyData.content = ''
                })
                .catch(err => console.log(err))
            } else {
              this.replyData.replyFile.append('content', this.replyData.content)
              this.replyData.replyFile.append('nickname', this.$store.state.nickname)
              this.replyData.replyFile.append('like', this.replyData.like)
              this.replyData.replyFile.append('pt_id', this.replyData.pt_id)
              axios.post(`${resourceHost}pt/${this.pt_id}/uploadFile/`, this.replyData.replyFile, {
                headers: {
                  'Content-Type': 'multipart/form-data',
                }
              })
                .then(res => {
                  this.bestReplys = res.data[0]
                  this.replys = res.data[1]
                  this.replyData.content = ''
                })
                .catch(err => console.log(err))
            }   
          } else {
            alert('댓글의 내용을 입력해주세요.')
          }
        } else {
          alert('로그인이 필요합니다.')
        }
      },
      onCommentUpdate(reply) {
        this.replyData.reply_id = reply.reply_id
        this.replyData.pt_id = this.pt_id
        axios.put(`${resourceHost}pt/${this.pt_id}/reply_update/`, this.replyData, {
          headers: {
            'Content-Type': 'application/json',
          }
        })
          .then(res => {
            this.bestReplys = ''
            // 삭제 후 받아온 리스트로 모든 값을 바꿔줌
            this.bestReplys = res.data[0]
            this.replys = res.data[1]
            this.replyData.content=''
            $(`#staticBackdrop${reply.reply_id}`).modal('hide')
          })
          .catch(err => console.log(err))
        
      },
      onCommentDelete(reply) {
        axios.delete(`${resourceHost}pt/${this.pt_id}/${reply.reply_id}/`, {
          headers: {
            'Content-Type': 'application/json',
          }
        })
          .then(res => {
            // 우선 베뎃을 초기화 해주고(아니면 에러나서 ..)
            this.bestReplys = ''
            // 삭제 후 받아온 리스트로 모든 값을 바꿔줌
            this.bestReplys = res.data[0]
            this.replys = res.data[1]
            this.replyData.content = ''
          })
          .catch(err => console.log(err))
      },
      // 좋아요 
      async onLikeBtn(reply) {
        // promise 안에서의 this는 우리가 원하는 this가 아니므로 추가 설정
        var _this = this;
        new Promise(function(){
          axios.get(`${resourceHost}pt/${_this.pt_id}/${reply.reply_id}/reply_like_insert/`, {
          params: { 
            nickname:_this.$store.state.nickname 
          },
        })
          .then(res => {
            // 현재 reply의 좋아요를 늘리고 체크락(0 -> 1)을 바꿔준다. frontend의 변수임
            reply.like = res.data
            reply.checkedLike = 1
          })
          .catch(err => console.log(err))
        })
        // this.fetchReply() 순차적으로 실행 
        await this.fetchReply()
      },
      // 좋아요 취소
      async offLikeBtn(reply) {
        // promise 안에서의 this는 우리가 원하는 this가 아니므로 추가 설정
        var _this = this;
        new Promise(function(){
          axios.delete(`${resourceHost}pt/${_this.pt_id}/${reply.reply_id}/reply_like_delete/`, {
          params: {
            nickname:_this.$store.state.nickname ,
          }
        })
          .then(res => {
            // 현재 reply의 좋아요를 늘리고 체크락(1 -> 0)을 바꿔준다. frontend의 변수임
            reply.like = res.data
            reply.checkedLike = 0
          })
          .catch(err => console.log(err))
        })
        // this.fetchReply() 순차적으로 실행 
        await this.fetchReply()
          
      },
      
    },
    mounted() {
      this.fetchArticle()
      this.fetchReply()
    },
}
</script>

<style scoped>
.pt-update-button {
  background-color: transparent;
}
.pt-delete-button {
  background-color: transparent;
}
.bestIcon {
  font-size: 2rem;
}
.like-button-best {
  background-color: transparent;
  border: none;
}
.like-button-normal {
  background-color: transparent;
  border: none;
}
.like-button-normal:focus {
  outline: 0;
}
.btnMovie {
  background-color: red;
  position: absolute;
  right: 0px;
  bottom: 0px;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  border: none;
}
.btnMovie:focus {
  outline: 0;
}
.grade-information {
  background-color: #aaf794;
  color: #fff;
}
.modal-dialog {
  max-width: 800px;
  margin-top: 150px !important;
}
#updateReplyInput {
  width: 100%;
}
*:not(.article_title) {
    font-family: 'NanumSquare', sans-serif !important;
    
}
.text-grid-item, .comment-text{
  font-weight: 600;
}

</style>
