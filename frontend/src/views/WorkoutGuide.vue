<template>
  <div v-if="$store.state.guide" id="workoutGuide" class="guide">
    <div class="post">
      <h2>운동 가이드 영상</h2>
      <ul class="part-list">
        <li v-bind:class="{isChest:isParts.isChest}"><a href="/guide/chest/">가슴</a></li>
        <li v-bind:class="{isBack:isParts.isBack}"><a href="/guide/back/">등</a></li>
        <li v-bind:class="{isShoulder:isParts.isShoulder}"><a href="/guide/shoulder/">어깨</a></li>
        <li v-bind:class="{isLowerbody:isParts.isLowerbody}"><a href="/guide/lowerbody/">하체</a></li>
        <li v-bind:class="{isEtc:isParts.isEtc}"><a href="/guide/etc/">팔</a></li>
      </ul>
      <div class="grid-image-container">
        <div class="grid-image-item" v-for="video in videos" :key="video.guide_movie">
          <GuideModal :video="video" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GuideModal from '../components/guide/GuideModal.vue'

export default {
  name: 'WorkoutGuidie',
  components: {
    GuideModal,
  },
  data() {
    return {
      videos: null,
      parts: {
        "list": "list",
        "chest": "가슴",
        "back": "등",
        "shoulder": "어깨",
        "lowerbody": "하체",
        "etc": "팔",
      },
      checkParts:{
        "list":"isList",
        "chest": "isChest",
        "back": "isBack",
        "shoulder": "isShoulder",
        "lowerbody": "isLowerbody",
        "etc": "isEtc"
      },
      isParts:{
        isList: false,
        isChest: false,
        isBack: false,
        isShoulder: false,
        isLowerbody: false,
        isEtc: false,
      }

    }
  },
  created() {
    var part = document.location.href.split("/")[4];
    var a = this.checkParts[part];
    this.isParts[a] = true;
    part = this.parts[part];
    // 부위별 링크 처리
    // store에 있는 video state를 변경
    this.$store.dispatch('PART', part)
      .then(() => {
        this.videos = this.$store.state.guide;
      })
      .catch(
        console.log("header error")
      )
  },
  methods: {
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
.grid-image-container {
  display: grid;
  grid-template-columns: auto auto auto;
  margin: 0px 20px 50px;
    
}
@media screen and (max-width: 800px) {
  .grid-image-container {
    grid-template-columns: auto auto;
    margin: 0px 20px 50px;
  }
}
@media screen and (max-width: 560px) {
  .grid-image-container {
    grid-template-columns: auto;
    margin: 0px 20px 50px;
  }
}
@media screen and (min-width: 1250px) {
  .grid-image-container {
    grid-template-columns: auto auto auto auto;
    margin: 0px 160px 50px;  
  }
}


*{
  font-family: 'Do Hyeon', sans-serif;
}

.part-list {
  list-style: none;
  position: fixed;
  right: 5%;
  top: 50%;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 20px 40px -14px rgba(0,0,0,0.25);
}
.part-list > li {
  border: 1px solid #eee;
  padding: 10px 30px 10px;
}
.part-list > li:hover {
  opacity: 0.5;
}
.part-list > li> a:focus {
  color: red;
}
.part-list > li > a {
  color: gray;
}
.isChest, .isBack,.isShoulder,.isLowerbody, .isEtc{
  background-color: #a5dc86;
}
</style>
