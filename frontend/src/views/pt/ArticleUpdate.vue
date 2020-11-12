<template>
  <div id="ptUpdating">
    <h2>게시글 수정</h2>
    <div class="title-group">
      <label for="input-title">제목</label>
      <input type="text" id="input-title" autofocus placeholder="제목을 입력하세요." v-model="videoData.title" required>
    </div>
    <div class="content-group">
      <label for="input-content">내용</label> <br>
      <textarea id="input-content" rows="8" cols="80" placeholder="내용을 입력하세요." v-model="videoData.content" required></textarea>
    </div>
    <span style="font-weight:lighter"><v-icon class="warning-icon">mdi-alert-octagon</v-icon> 동영상 파일만 업로드 가능합니다</span>
    <div class="video-group">
      <input type="file" accept=".mp4" id="input-video" ref="file" @change="upload">
    </div>
    <h2 class="target-type">운동 부위: {{ videoData.type }}</h2>
    <div class="switch-field">
      <input type="radio" id="radio-one" name="switch-one" value="가슴" v-model="videoData.type" checked required/>
      <label for="radio-one">가슴</label>
      <input type="radio" id="radio-two" name="switch-one" value="등" v-model="videoData.type" />
      <label for="radio-two">등</label>
      <input type="radio" id="radio-three" name="switch-one" value="어깨" v-model="videoData.type"/>
      <label for="radio-three">어깨</label>
      <input type="radio" id="radio-four" name="switch-one" value="하체" v-model="videoData.type"/>
      <label for="radio-four">하체</label>
      <input type="radio" id="radio-five" name="switch-one" value="팔" v-model="videoData.type"/>
      <label for="radio-five">팔</label>
    </div>
    <button class="ptUpdateBtn" @click="onWriting(videoData)">수정</button>
    <button class="ptUpdateCancel" @click="onCancel">취소</button>
  </div>
</template>

<script>
import '../../assets/css/pt/ptwriting.css'
import axios from 'axios'
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl;

export default {
  name: 'ArticleUpdate',
  data() {
    return {
      pt_id: this.$route.params.pt_id,
      nickname: "",
      videoData: {
        pt_id: "",
        title: "",
        content: "",
        videoFile: "",
        type: "",
        nickname: "",
      },
    }
  },
  methods: {
    upload() {
      var formData = new FormData();
      formData.append('file', this.$refs.file.files[0]);
      this.videoData.videoFile = formData
    },
    onCancel() {
      this.$router.push(`/pt/${this.pt_id}`)
    },
    onWriting(videoData) { 
      videoData.videoFile.append('title', videoData.title)
      videoData.videoFile.append('content', videoData.content)
      videoData.videoFile.append('type', videoData.type)
      videoData.videoFile.append('nickname', videoData.nickname)
      videoData.videoFile.append('pt_id', videoData.pt_id)
      axios.put(`${resourceHost}pt/updateFile/`, videoData.videoFile ,{
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(() => {
          this.$router.push(`/pt/${this.pt_id}`)
        })
        .catch(() => {
          //console.log("업로드에 실패하였습니다.")
        })
    },
    fetchUpdate() {
      axios.get(`${resourceHost}pt/${this.pt_id}/`)
          .then(res => {
            //console.log(res.data)
            this.videoData.pt_id = this.pt_id
            this.videoData.title = res.data.pt_title
            this.videoData.content = res.data.pt_content
            this.videoData.type = res.data.pt_type
            this.nickname = res.data.nickname
            //console.log(this.nickname)
            //console.log(this.$store.state.nickname)
            if (this.nickname !== this.$store.state.nickname) {
              alert('권한이 없습니다.')
              this.$router.push(`/pt/${this.pt_id}`)
            }
          })
          .catch(
            //err => console.log(err)
            )
      
      
    },
  },
  created() {
    this.fetchUpdate()
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

* {
  font-family: 'Black Han Sans', sans-serif;
}

input, button {
  font-family: 'Do Hyeon', sans-serif;
}

.ptUpdateBtn, .ptUpdateCancel {
  padding: 5px 10px 5px;
  border: none;
  border-radius: 8px;
  color: #fff;
  background-color: gray;
  margin-right: 5px;
}
.ptUpdateBtn:focus, .ptUpdateCancel:focus {
  outline-style: none;
}
</style>