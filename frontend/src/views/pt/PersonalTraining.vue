<template>
  <div id="personalTraining" class="pt">
    <div class="container">
      <router-link tag="button" class="write-button" to="/pt/write"><v-icon size="25">mdi-comment-text</v-icon></router-link>
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default" v-for="article in articles" :key="article.pt_id">
          <div class="panel-heading" role="tab" :id="`heading${article.pt_id}`">
            <h4 class="panel-title">
              <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" :href="`#collapse${article.pt_id}`" aria-expanded="false" :aria-controls="`collapse${article.pt_id}`">
                {{ article.pt_title }}
              </a>
            </h4>
            <div class="pt-detail-data">
              <span class="badge">{{ article.pt_type }}</span> <br>
              <span class="pt-date">{{ article.nickname }} | {{ article.pt_date }}</span>
              <router-link tag="button" class="detailBtn" :to="{ name: 'ArticleDetail', params: {pt_id: article.pt_id }}">상세 정보</router-link>
            </div>
          </div>
          <div :id="`collapse${article.pt_id}`" class="panel-collapse collapse in" role="tabpanel" :aria-labelledby="`heading${article.pt_id}`">
            <video :src="article.pt_movie" controls></video>
          </div>
        </div>
      </div>
      <div class="btn-cover">
          <button :disabled="pageNum === 1" @click="prevPage" class="page-btn">
            이전
          </button>
          <span class="page-count">{{ pageNum }} / {{ pageSize }} 페이지</span>
          <button :disabled="pageNum >= pageSize" @click="nextPage" class="page-btn">
            다음
          </button>
        </div>
    </div>
  </div>
</template>


<script>
import '../../assets/css/pt/personaltraining.scss'
import axios from 'axios'
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl;


export default {
  name: 'PersonalTraining',
  data() {
    return {
      articles: [],
      pageNum: 1,
      pageSize: 0,
    }
  },
  methods: {
    fetchArticle() {
      axios.get(`${resourceHost}pt/list`, { params:{currentPageNum: this.pageNum} })
        .then(res => {
          //console.log(res.data)
          this.pageSize = res.data.pageSize // 전체 페이지 수
          this.articles = res.data.articles
        })
        .catch(err => console.log(err))
    },
    nextPage() {
      this.pageNum += 1
      this.pageMove(this.pageNum)
    },
    prevPage() {
      this.pageNum -= 1
      this.pageMove(this.pageNum)
    },
    pageMove(pageNum) {
      axios.get(`${resourceHost}pt/list`, { params:{currentPageNum: this.pageNum} })
        .then(res => {
          //console.log(res.data)
          this.pageSize = res.data.pageSize
          this.articles = res.data.articles
        })
    }
  },
  created() {
    this.fetchArticle()
  },
  mounted() {
  },
}
</script>


<style scoped>
</style>
