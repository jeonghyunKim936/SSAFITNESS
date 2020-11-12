<template>
<div class="navigation" style="z-index:1; background-color: #424242;">
<!-- <div class="navigation" style="z-index:1; background-color: #232323"> -->
    <div class="navigation-container" id="header">
        <!-- <span class ="image-container"> -->
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                <img class="navigation-logo" src="../../assets/img/brand_logo.png">
            </router-link>
        <!-- </span> -->
        <nav role='navigation' class="nav-list d-none d-sm-block">
            <ul class="">
                <li class="pt-link">
                    <a href="/pt/list">PT</a>
                    <!-- <ul >
                        <li><router-link to="/pt/write">글 쓰기</router-link></li>
                    </ul> -->
                </li>

                <li>
                    <a href="/guide/list">헬스가이드</a>
                    <!-- <ul>
                        <li><a href="/guide/chest">가슴</a></li>
                        <li><a href="/guide/back">등</a></li>
                        <li><a href="/guide/shoulder">어깨</a></li>
                        <li><a href="/guide/lowerbody">하체</a></li>
                        <li><a href="/guide/etc">팔</a></li>
                    </ul> -->
                </li>

                <li>
                    <a href="/notice">커뮤니티</a>
                </li>

                <li>
                    <a href="/calendar">다이어리</a>
                </li>

                <li>
                    <a href="/diet">식단</a>
                    <!-- <ul>
                        <li><a href="/diet">계산기</a></li>
                        <li><a href="/diet/macro">식단 구성</a></li>
                    </ul> -->
                </li>
            </ul>
        </nav>
        <nav class="mobile-nav d-block d-sm-none">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <v-icon style="color: #fff;">mdi-menu</v-icon>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="z-index: 3;">
                    <a class="dropdown-item" href="/pt/list/">PT</a>
                    <a class="dropdown-item" href="/guide/list/">가이드</a>
                    <a class="dropdown-item" href="/notice/">커뮤니티</a>
                    <a class="dropdown-item" href="/calender/">다이어리</a>
                    <a class="dropdown-item" href="/diet/">식단</a>
                </div>
            </div>
        </nav>
        <div class="navigation-loginBtn">
            <!-- <button id="login-btn" v-if="$store.state.email === ''" @click="$bvModal.show('login-modal')">로그인</button> -->
            <a class="login-join" href="/user/loginjoin/" v-if="$store.state.email === ''">로그인</a>
            <a class="login-join" href="/user/loginjoin/" v-if="$store.state.email === ''">회원가입</a>
            <!-- 로그아웃 처리 -->
            <div class="navigation-logined" v-else>
                <div class="navigation-user">
                    <b-avatar src="https://placekitten.com/300/300"></b-avatar>
                    어서오세요!
                    <router-link class="user-info-link" v-bind:to="{name:constants.URL_TYPE.USER.INFO}">
                        {{$store.state.nickname}}
                    </router-link>
                    님!
                </div>
                <button class="logoutBtn" style="margin-left:30px;" v-on:click="onClickLogout()">로그아웃</button>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import '../../assets/css/header.scss'
import constants from '../../lib/constants.js'
// import Login from '../../page/user/Login.vue'

export default {
    name: 'Header',
    components: {
        // Login,
    },
    props: ['isHeader'],
    computed: {},
    watch: {},
    created() {
        // 세션 스토리지에 토큰 key 자체가 있는가 ..?
        if("jwt-auth-token" in sessionStorage){
            // 만약 세션스트로지에 token이 있다면 ? 토큰이 유효한지 서버에 보내 본다 
            if (sessionStorage.getItem('jwt-auth-token') !== ""){
            this.$store.dispatch('GETINFO',{nickname:sessionStorage.getItem("user_nickname")});
        }
        }else{
            sessionStorage.setItem('jwt-auth-token', ""); 
            sessionStorage.setItem('user_nickname', ""); 
        }
        

    },
    methods: {
        onClickLogout() {
            //console.log("test")
            this.$store.dispatch('LOGOUT')
                .then(
                    //console.log(sessionStorage.getItem('jwt-auth-token'))
                )
        },
        test() {
            //console.log(this.$store.state.guide);
        },
        onClickGuidePart(part) {
            // 부위별 링크 처리
            // store에 있는 video state를 변경
            this.$store.dispatch('PART', part)
                .then(() => {
                        //console.log("header success")
                        // this.$router.push('/guide')
                    }
                )
                .catch(
                    //console.log("header error")
                )
        }
    },
    data: function () {
        return {
            constants,
            keyword: "",
        }
    },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');

* {
    font-family: 'Jua', sans-serif;
}

/* @media screen and (max-width: 426px) {
    a {
        min-width: 0px;
        margin: 0px;
        padding: 5px;
        width: 60px;
    }
    nav > ul > li {
        width: 60px;
    }
    .navigation-container {
        padding: 5px;
        margin: 0px;
        width: 100%;
        max-width: 100%;
    }
    nav {
        width: 100%;
    }
} */

nav {
    margin-left: 15px;
    width: 80%;
}

nav ul{
    list-style: none;
    /* //position: relative;
    //float: left;
    //margin-right: 100px;
    //display: inline-table; */
}
nav ul li{
    float: left;
    -webkit-transition: all .2s ease-in-out;
    -moz-transition: all .2s ease-in-out;
    transition: all .2s ease-in-out;
}

nav ul li:hover {
    background: rgba(0,0,0,.15);
}
/* nav ul li:hover > ul, li{display: block;} */
nav ul li{
    float: left;
    -webkit-transition: all .2s ease-in-out;
    -moz-transition: all .2s ease-in-out;
    transition: all .2s ease-in-out;
}
nav ul li a{
    min-width: 100px;
    display: block; 
    padding: 20px 20px 20px;
    color: white; 
    font-size: .9em;
    letter-spacing: 1px;
    text-decoration: none;
    text-transform: uppercase;
}
nav ul li a:hover {
    color: #FFBD07;
}
/* nav ul ul{
    display: none;
    background: #fff;
    position: absolute; 
    top: 100%;
    box-shadow: -3px 3px 10px -2px rgba(0,0,0,.1);
    border: 1px solid rgba(0,0,0,.1);
}
nav ul ul li{float: none; position: relative;}
nav ul ul li a {
    padding: 15px 30px; 
    border-bottom: 1px solid rgba(0,0,0,.05);
    color: black;
} */
nav ul .pt-link {
  width: 102.74px;
}
/* nav ul ul ul {
    position: absolute; 
    left: 100%; 
    top:0;
}	 */
.navigation-user{
    color:#ffffff
}
/* img{
    position: relative;
    width: 60%;
    height: 100%;
} */
.dropdown-toggle {
    background-color: transparent;
    border: none;
}
</style>
