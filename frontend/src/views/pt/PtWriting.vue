<template>
  <div id="ptWriting">
    <h2 class="pt-write-title">게시글 작성</h2>
    <div class="title-group">
      <label for="input-title">제목</label> <br>
      <input type="text" id="input-title" style="width:100%" autofocus placeholder="제목을 입력하세요." v-model="videoData.title" required>
    </div>
    <div class="content-group">
      <label for="input-content">내용</label>
      <textarea id="input-content" rows="8" cols="80" placeholder="내용을 입력하세요." v-model="videoData.content" required></textarea>
    </div>
    <span style="font-weight:lighter"><v-icon class="warning-icon">mdi-alert-octagon</v-icon> 동영상 파일만 업로드 가능합니다</span>
    <div class="video-group">
      <input type="file" accept=".mp4" id="input-video" ref="file"  @change="upload">
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
    <button class="ptWritingBtn" @click="onWriting(videoData)">등록하기</button>
  </div>
</template>

<script>
import '../../assets/css/pt/ptwriting.css'
import axios from 'axios'
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl;


export default {
  name: 'PtWriting',
  data() {
    return {
      videoData: {
        title: "",
        content: "",
        videoFile: "",
        type: "",
      },
    }
  },
  created(){
    if (sessionStorage.getItem("user_nickname") == "") {
            alert("로그인 해주세요")
            this.$router.push("/user/loginjoin/");
        }
  },
  methods: {
    upload() {
      var formData = new FormData();
      formData.append('file', this.$refs.file.files[0]);
      
      this.videoData.videoFile = formData
      //console.log('formData')
      // for (let value of formData.values()) {
      //   console.log(value)
      // }
    },
    onWriting(videoData) { 
      //console.log(this.$store.state.nickname)
      if (this.$store.state.nickname) {
        videoData.videoFile.append('title', videoData.title)
        videoData.videoFile.append('content', videoData.content)
        videoData.videoFile.append('type', videoData.type)
        videoData.videoFile.append('nickname', this.$store.state.nickname)
        //console.log('videoData.videoFile')
        // for (var value of videoData.videoFile.values()) {
        //   console.log(value)
        // }
        axios.post(`${resourceHost}pt/uploadFile`, videoData.videoFile ,{
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
        .then(res => {
          //console.log(res.data)
          //console.log("업로드에 성공하였습니다.")
          this.$router.push("/pt/list")
        })
        .catch(err => {
          //console.log(err)
          //console.log("업로드에 실패하였습니다.")
        })
      } else {
        alert('로그인이 필요합니다.')
        this.$router.push(constants.URL_TYPE.USER.MAIN)
      }
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

* {
  font-family: 'Black Han Sans', sans-serif;
}
input, button ,span, textarea{
  font-family: 'Do Hyeon', sans-serif;
}

</style>
