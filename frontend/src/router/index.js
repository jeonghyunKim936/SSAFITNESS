import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import constants from '../lib/constants'

// 유저
import Info from '../page/user/Info.vue'
import LoginJoin from '../page/user/LoginJoin.vue'
//가이드
import WorkoutGuide from '../views/WorkoutGuide.vue'
//pt
import PersonalTraining from '../views/pt/PersonalTraining.vue'
import PtWriting from '../views/pt/PtWriting.vue'
import ArticleDetail from '../views/pt/ArticleDetail.vue'
import ArticleUpdate from '../views/pt/ArticleUpdate.vue'
//캘린더
import Calendar from '../views/calendar/Calendar.vue'
//식단
import CalorieCalculator from '../views/diet/CalorieCalculator.vue'
import Macro from '../views/diet/Macro.vue'
//게시판
import Notice from '../views/notice/Notice.vue'
import NoticeInput from '../views/notice/NoticeInput.vue'
import NoticeDetail from '../views/notice/NoticeDetail.vue'


Vue.use(VueRouter)

  const routes = [
  //Main
  {
    path: '/',
    name: constants.URL_TYPE.POST.MAIN,
    component: Home
  },
  //User
  {
    path: '/user/info',
    name: constants.URL_TYPE.USER.INFO,
    component: Info,
  },
  {
    path: '/user/loginjoin',
    name: 'LoginJoin',
    component: LoginJoin
  },
  // guide
  {
    path: '/guide/list',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  {
    path: '/guide/chest',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  {
    path: '/guide/back',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  {
    path: '/guide/shoulder',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  {
    path: '/guide/lowerbody',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  {    
    path: '/guide/etc',
    name: 'WorkoutGuide',
    component: WorkoutGuide
  },
  //pt
  {
    path: '/pt/list',
    name: 'PersonalTraining',
    component: PersonalTraining
  },
  {
    path: '/pt/write',
    name: 'PtWriting',
    component: PtWriting
  },
  {
    path: '/pt/:pt_id/update',
    name: 'ArticleUpdate',
    component: ArticleUpdate,
  },
  // calendar
  {
    path: '/calendar',
    name: 'MyCalendar',
    component: Calendar
  },
  {
    path: '/pt/:pt_id',
    name: 'ArticleDetail',
    component: ArticleDetail
  },
  // notice
  {
    path: '/notice',
    name: 'NoticeList',
    component: Notice
  },
  {
    path: '/notice/input',
    name: 'NoticeInput',
    component: NoticeInput
  },
  {
    path: '/notice/detail',
    name: 'NoticeDetail',
    component: NoticeDetail
  },
  // diet
  {
    path: '/diet',
    name: 'CalorieCalculator',
    component: CalorieCalculator
  },
  {
    path: '/diet/macro',
    name: 'Macro',
    component: Macro,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
