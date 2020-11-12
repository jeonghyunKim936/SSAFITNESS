<template>
  <div class="calorie-calculator container">
    <div class="form-row row">
      <div class="form-calculator col-5">
        <div class="data-group">
          <h2 style="padding-left: 90px;">칼로리 계산기</h2>
          <label for="heightData" style="margin-right: 5px;"><v-icon class="icon">mdi-human-male-height</v-icon></label>
          <input type="number" name="height" id="heightData" class="input-text" placeholder="height" v-model="inputData.height" min="1" required> <br>
          <label for="weightData" style="margin-right: 5px;"><v-icon class="icon">mdi-scale-bathroom</v-icon></label>
          <input type="number" name="weight" id="weightData" class="input-text" placeholder="weight" v-model="inputData.weight" min="1" required> <br>
          <label for="ageData" style="margin-right: 5px;"><v-icon class="icon">mdi-account</v-icon></label>
          <input id="ageData" class="input-text" type="number" min="1" placeholder="age" v-model="inputData.age" required> <br>
          <div class="radio-data-group">
            <label for="genderDataMale">남</label>
            <input type="radio" id="genderDataMale" name="gender" value="m" v-model="inputData.gender" required>
            <label for="genderDataFemale">여</label>
            <input type="radio" id="genderDataFemale" name="gender" value="w" v-model="inputData.gender"> <br>
            <label for="lowerActivity">활동량 적음</label>
            <input type="radio" id="lowerActivity" name="activity" value="1.2" v-model="inputData.activity" required>
            <label for="normalActivity">보통</label>
            <input type="radio" id="normalActivity" name="activity" value="1.4" v-model="inputData.activity">
            <label for="higherActivity">많음</label>
            <input type="radio" id="higherActivity" name="activity" value="1.55" v-model="inputData.activity"> <br>
            <label for="bulkup">벌크업</label>
            <input type="radio" name="purpose" value="bulkup" id="bulkup" v-model="inputData.purpose" required>
            <label for="diet">다이어트</label>
            <input type="radio" name="purpose" value="diet" id="diet" v-model="inputData.purpose">
          </div>
        </div>
        <button class="result-button" @click="onCalculate">결과 보기</button>
      </div>
      <div class="form-image col-7">
        <div class="form-image-group">
          <h2 class="fig-title">아직도 직접 계산하고 계신가요?</h2>
          <p class="fig-sub-title">몇 가지 정보를 입력하기만 하면 목적에 맞는 매크로를 계산해 드립니다</p>
          <img width="500" :src="require('@/assets/img/brand_logo.png')" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'CalorieCalculator',
  data () {
    return {
      inputData: {
        gender: null,
        weight: null,
        height: null,
        age: null,
        activity: null,
        purpose: null,
      },
      macroData: {
        tdee: 0,
        targetCalorie: 0,
        bmr: 0,
        carbon: 0,
        protein: 0,
        fat: 0,
      }
    }
  },

  methods: {
    onCalculate() {
      this.macroData.bmr = (10 * this.inputData.weight + 6.25 * this.inputData.height -5 * this.inputData.age);
      if (this.inputData.gender === 'm') {
        this.macroData.bmr += 5;
      } else {
        this.macroData.bmr -= 151;
      }
      this.macroData.tdee = this.macroData.bmr * this.inputData.activity;
      this.macroData.tdee = Math.round(this.macroData.tdee);
      if (this.inputData.purpose === 'bulkup') {
        this.macroData.targetCalorie = this.macroData.tdee + 300;
        this.macroData.protein = 1.8 * this.inputData.weight;
        this.macroData.fat = this.inputData.weight;
      } else {
        this.macroData.targetCalorie = this.macroData.tdee - 500;
        this.macroData.protein = 2.2 * this.inputData.weight;
        this.macroData.fat = this.macroData.targetCalorie * 0.25 / 9;
      }
      this.macroData.protein = Math.round(this.macroData.protein);
      this.macroData.fat = Math.round(this.macroData.fat);
      this.macroData.carbon = (this.macroData.targetCalorie - (this.macroData.protein * 4 + this.macroData.fat * 9)) / 4;
      this.macroData.carbon = Math.round(this.macroData.carbon);
      // this.$store.commit('setCalculatorResult', this.macroData)
      this.$router.push({ name: 'Macro', params: { macroData: this.macroData } })
    },
  },
  mounted() {
  },
}
</script>

<style scoped>
.calorie-calculator {
  background-color: #fff;
  padding: 0px;
  box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
    0 10px 10px rgba(0,0,0,0.22);
}
.form-calculator {
  margin-top: 50px;
  margin-left: auto;
}
.data-group {
  text-align: left;
  margin-left: 70px;
}
.data-group > .input-text {
  border: none;
  border-bottom: 1px solid #eee;
  margin-bottom: 10px;
  width: 80%;
}
.data-group > .input-text:hover {
  outline-style: none;
  border-bottom: 1px solid lightgreen;
}
.radio-data-group > input {
  margin-left: 10px;
  margin-right: 10px;
}
.result-button {
  background-color: gray;
  color: #fff;
  padding: 5px 10px 5px;
  border-radius: 10px;
  border: none;
}
.result-button:hover, :focus {
  color: #ffbd07;
  outline-style: none;
}
.form-image {
  background-color: rgb(66, 66, 66);
  color: #fff;
}
.form-image-group {
  margin: 10px;
}
.fig-title {
  color: #ffbd07;
}
</style>