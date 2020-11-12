<template>
<div class="guide-modal" style="z-index:2">
    <ul class="cards">
        <li class="cards__item">
            <div class="card">
            <div class="card__image">
                <img class="imagethumbnail" :src="require(`@/assets/guide/${video.guide_title}.png`)" :alt="video.guide_title" data-toggle="modal" :data-target="'#'+videoId" >
                <!-- <img :src="`https://img.youtube.com/vi/${video.guide_movie}/mqdefault.jpg`" alt="" data-toggle="modal" :data-target="'#'+videoId" > -->
            </div>
            <div class="card__content">
                <!-- <div class="card__title">{{ video.guide_title }}</div> -->
                <p class="card__text">{{ video.guide_script }}</p>
                <button class="btn btn--block card__btn" data-toggle="modal" :data-target="'#'+videoId">{{ video.guide_title }}</button>
            </div>
            </div>
        </li>
    </ul>
    <!-- Modal -->
    <div class="modal fade" :id="videoId" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="close-button-div">
                  <button @click="onClickCloseModal(videoId)" type="button" class="closeBtn">
                      <v-icon>mdi-close</v-icon>
                  </button>
                </div>
                <div class="modal-body">
                    <iframe ref="video" width="1010" height="568" :src="'https://www.youtube.com/embed/'+video.guide_movie+'?enablejsapi=1&version=3&playerapiid=ytplayer'" frameborder="0" allowfullscreen></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import '../../assets/css/guidemodal.scss'

export default {
    name: 'GuideModal',
    props: {
        video: Object,
    },
    data() {
        return {

        }
    },
    methods: {
        onClickCloseModal(videoId) {
          $(`#${videoId}`).modal('hide')
          // stop youtube
          this.$refs.video.contentWindow.postMessage('{"event":"command","func":"' + 'stopVideo' + '","args":""}', '*');
        }
    },
    computed: {
        videoId() {
            return `modal${this.video.guide_title}`

        }
    },
}
</script>

<style scoped>
* {
    font-family: 'NanumSquare', sans-serif !important;
    font-weight: 600;
}
.modal-content {
    background-color: transparent;
    border: 0px;
}
.modal-body {
  padding-left: 0px;
  padding-right: 0px;
  width: 1200px;
}
.img-thumbnail {
  padding: 0px;
}
.img-thumbnail:hover {
  opacity: 0.3;
}

.btn {
  background-color: transparent;
  border: 1px solid lightgray;
  /* //border-radius: 1rem; */
  color: darkgray;
  padding: 0.5rem;
  text-transform: lowercase;
}
.card__text {
    text-align: left;
}
.card__content {
    background-color: #fff;
}
.closeBtn {
    background-color: gray;
    color: #fff;
    width: 85%;
    margin-left: 80px;
}
.closeBtn:focus {
  outline-style: none;
}
</style>
