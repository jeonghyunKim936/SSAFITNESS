<template>

  <html>
    
    <div class="home">

    <!-- 사진 -->
    <section id="intro">
      <div id="intro-background"></div>
      <div id="intro-tagline">
        <h1>Move to Live</h1>
      </div>
    </section>

    <main class="page-content">
    <!-- 소개글 -->
      <div class="page-container">
        <h1>About Ssafitness</h1>
        <hr />
        <h2>간단합니다. 흔들리면 그것은 지방입니다</h2>

        <p>현 시국, 코로나는 의료진이 막아주지만 불어나는 당신의 지방은 아무도 막아주지 않습니다. 건강한 몸에 건강한 정신이 깃드는게 아닌 건강한 몸이 있어야 정상적인 사고가 가능합니다</p>
        <p>SSAFITNESS는 SSAFY 개발진들이 헬린이의, 헬린이에 의해 , 헬린이들을 위해 제작한 FITNESS 커뮤니티 입니다. 자신의 운동영상을 올리고 믿을 수 있는 트레이너들의 피드백 영상을 집에서 보며 헬스와 더욱 가까워질 수 있습니다. 다이어리로 직접 자신의 운동량을 체크해보고 식단으로 효과를 더욱 극대화 할 수 있습니다</p>
        <p>만약 정말 아무것도 모르신다면 헬스가이드를 통해 유명 트레이너의 영상을 봐보세요. 집순이, 코로나, 자택근무와 같은 환경이 당신의 몸을 망가트리는 이유가 될 수 없습니다</p>
        <p>SSAFITNESS를 보고 SSAFITNESS를 따라해보세요. 말씀드릴 수 있습니다. 지금보다 더 괜찮아질거라고, 지금보다 더 빛날거라고</p>
      
      </div>
      <!-- Service -->
      <section id="services" class="sec-services">
        <div class="container">
          <h1>Services</h1>
          <hr/>
          <div class="row">
            <div class="col-sm-4">
              <div class="col" style="right:30px">
                <v-icon size="100" >mdi-kabaddi</v-icon>
              </div>
              <h2 class="h3"> PT 가이드</h2>
              <p>전문 트레이너가 당신을 도와줍니다</p>
            </div>

            <div class="col-sm-4">
              <div class="col" style="right:30px">
                <v-icon size="100" >mdi-calendar-clock</v-icon>
              </div>
              <h2 class="h3">다이어리</h2>
              <p>운동일지를 기록하고 확인하세요</p>
            </div>
            <div class="col-sm-4">
              <div class="col" style="right:30px">
                <v-icon size="100" >mdi-nutrition</v-icon>
              </div>
              <h2 class="h3">식단</h2>
              <p>적절한 칼로리의 식단을 제공합니다</p>
            </div>
          </div>
        </div>
      </section>
      <!-- photo -->
      <section id="folio" class="sec-folio">
        <div class="container">
          <h1>Trainer</h1>
          <hr />
          <div class="row">
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer1_R.jpg" alt="By Håkon Sataøen" />
              <v-icon size="20">mdi-account-circle</v-icon> Boo Kwon Nam <br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> 3대700 <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 1357 2468           
            </div>
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer2_R.jpg" alt="By Samuel Zeller" />
              <v-icon size="20">mdi-account-circle-outline</v-icon> Lim Gun ho <br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> 인성문제있어 <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 1004 1004     
            </div>
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer3_R.jpg" alt="By Scott Webb" />
              <v-icon size="20">mdi-account-circle</v-icon> Kim Jeong hyeon <br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> 4번은개인주의 <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 7777 7777   
            </div>
          </div>

          <div class="row">
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer4_R.jpg" alt="By Daniel Lozano Valdés" />
              <v-icon size="20">mdi-account-circle-outline</v-icon> Park Se jin <br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> 쇠질장인 <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 1234 4321   
            </div>
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer5_R.jpg" alt="By Scott Webb" />
              <v-icon size="20">mdi-account-circle</v-icon> Baek Sang hyeok<br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> 너무멀어역삼 <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 5252 5252   
            </div>
            <div class="col-md-4">
              <img class="center-block" src="../assets/trainer/trainer6_R.jpg" alt="By Daniel Lozano Valdés" />
              <v-icon size="20">mdi-account-circle-outline</v-icon> Jo Je Hyeong <br>
              <v-icon size="20">mdi-badge-account-horizontal </v-icon> ebro <br>
              <v-icon size="20">mdi-cellphone-iphone</v-icon> 010 9999 9999   
            </div>
          </div>
        </div>
      </section>
	  </main>
  </div>
  </html>
  
</template>

<script>
// @ is an alias to /src

import HelloWorld from '@/components/HelloWorld.vue'


//import $ from 'jquery';


export default {
  name: 'Home',
  components: {
    HelloWorld
  },

  created(){
    window.addEventListener('scroll', this.handleScroll);

    $(window).scroll(function() {
    var scroll = $(window).scrollTop();
    $("#intro-background").css("transform","translateY(" +  (scroll/2)  + "px)");    
    });
  }
  
}
</script>


<style scoped>


body {
    font-size: 100%;
    font-family: "Open Sans", sans-serif;
    color: #f35535;
    background-color: #2e313d;
}
html * {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}
body {
    line-height: 1;
}


#intro {
    position: absolute;
    /*position: fixed;*/
    top: 0;
    left: 0;
    width: 100%;
    height: 700px;
    margin-top: 0;
}

#intro #intro-background {
    height: 100%;
    width: 100%;
    /* background: url("http://www.hdwallpapers.in/walls/milky_way_sky-wide.jpg") no-repeat center center; */
    background: url("../assets/img/mainImage.png") no-repeat center center;
    /* background: url("../assets/img/benchpress_wallpaper.jpg") no-repeat center center; */
    background-size: cover;
    box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}

#intro #intro-tagline{
    position: absolute;
    width: 90%;
    max-width: 1170px;
    left: 50%;
    top: 45%;
    bottom: auto;
    right: auto;
    -webkit-transform: translateX(-50%) translateY(-50%);
    -moz-transform: translateX(-50%) translateY(-50%);
    -ms-transform: translateX(-50%) translateY(-50%);
    -o-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
    color: white;
    font-size: 20px;
    font-size: 1.5rem;
    font-weight: 300;
    text-align: center;
}

#intro h1 {
    font-family: "Open Sans", helvetica, arial, sans-serif;
    text-transform: uppercase;
    font-size: 1.8em;
    font-weight: 300;
    text-align: center;
    color: #ffffff;
    text-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
}

.page-content {
    position: relative;
    padding: 2em 0;
    line-height: 1.8;
    color: #65676f;
    font-weight: 100;
    /* background-color: #E6F6F4; */
    background-color: #ffffff;
    z-index: 2;
}

.page-content::before {
    content: '';
    position: absolute;
    bottom: 100%;
    left: 0;
    width: 100%;
    height: 50px;
    background: -webkit-linear-gradient( bottom , rgba(46, 49, 61, 0.5), rgba(46, 49, 61, 0));
    background: linear-gradient(to top, rgba(46, 49, 61, 0.5), rgba(46, 49, 61, 0));
}

@media only screen and (min-width: 1170px) {
.page-content {
    padding: 4em 0;
    margin-top: 570px;
 }
}

@media only screen and (min-width: 768px) { 
.cd-content {
    line-height: 1.8;
 }
}

.page-container {
    width: 90%;
    max-width: 768px;
    margin: 0 auto;
}

.page-container::after {
    content: '';
    display: table;
    clear: both;
}

@media only screen and (min-width: 1170px) {
.page-content p {
    font-size: 20px;
    font-size: 1.25rem;
 }
}

@media only screen and (min-width: 768px) {
.page-content h2 {
    font-size: 30px;
    font-size: 1.875rem;
 }
}

.page-content h2 {
    margin: 2em 0;
    /* font-size: 60px; 20 */
    /* font-size: 1.25rem; */
}

.page-content p {
    margin: 1em 0;
}
/* About */


/*Services*/

/* .sec-services {
  background: #f9f9f9;
} */

.sec-services i {
  display: block;
  margin: auto;
  width: 45px;
}

.sec-services .fa-paint-brush {
  width: 64px;
}

.sec-services .fa-code {
  width: 68px
}

.sec-services h2 {
  margin: 1em 0;
}

/* image layout*/
.sec-folio .row {
  /* margin-top: 2em; */
  margin-bottom: 1em;
  font-size: 16px;
  font-size: 1.00em;
}

.sec-folio img {
  max-width: 100%;
}


</style>