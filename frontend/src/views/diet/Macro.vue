<template>
  <div class="food-group container">
    <div id="basketGroup" class="basket-group row">
      <div class="col-3">
        <div class="trainer-chat">
          <span>어이, {{ nickname }}!!</span> <br>
          <span>딴 거 먹다가 걸려라</span>
        </div>
        <img :src="require('@/assets/img/trainer.png')" alt="">
      </div>
      <!-- list -->
      <table class="table table-borderless col-9">
        <!-- <caption>selecting food of user</caption> -->
        <thead class="thead-dark">
          <tr>
            <th scope="col-1">#</th>
            <th scope="col-3">이름</th>
            <th scope="col-1">칼로리</th>
            <th scope="col-1">탄수화물</th>
            <th scope="col-1">단백질</th>
            <th scope="col-1">지방</th>
            <th scope="col-1">나트륨</th>
            <th scope="col-2">수량</th>
            <th scope="col-1"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(data, index) in basketData" :key="data.food_id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ data.food_name }}</td>
            <td>{{ data.food_kcal }}kcal</td>
            <td>{{ data.food_carbon }}g</td>
            <td>{{ data.food_protein }}g</td>
            <td>{{ data.food_fat }}g</td>
            <td>{{ data.food_natrium }}mg</td>
            <td><button class="count-button" @click="minusFoodCnt(data)">-</button>
            <span>{{ data.food_cnt }}</span>
            <button class="count-button" @click="plusFoodCnt(data)">+</button></td>
            <td><button class="cart-remove-button" @click="removeBasket(data, index)"><v-icon>mdi-trash-can</v-icon></button></td>
          </tr>
          <tr style="color: lightcoral;">
            <th scope="row">목표</th>
            <td></td>
            <td>{{ macroData.targetCalorie }}kcal</td>
            <td>{{ macroData.carbon }}g</td>
            <td>{{ macroData.protein }}g</td>
            <td>{{ macroData.fat }}g</td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr style="color: cornflowerblue;">
            <th scope="row">총합</th>
            <td></td>
            <td>{{ totalMacro.calorie }}kcal</td>
            <td>{{ totalMacro.carbon }}g</td>
            <td>{{ totalMacro.protein }}g</td>
            <td>{{ totalMacro.fat }}g</td>
            <td>{{ totalMacro.natrium }}mg</td>
            <td></td>
            <td></td>
          </tr>
          <tr style="color: darkseagreen;">
            <th scope="row">남은 양</th>
            <td></td>
            <td>{{ macroData.targetCalorie - totalMacro.calorie }}kcal</td>
            <td>{{ macroData.carbon - totalMacro.carbon }}g</td>
            <td>{{ macroData.protein - totalMacro.protein }}g</td>
            <td>{{ macroData.fat - totalMacro.fat }}g</td>
          </tr>
        </tbody>
      </table>
    </div>
    <hr>
    <div id="scene" class="food-box row">
      <div id="left-zone" class="name-group col-4">
        <ul class="list" v-for="food in foods" :key="food.food_id">
          <li class="food-name" @click="onSelectMenu(food)">
            {{ food.food_name }}
          </li>
        </ul>
      </div>
      <div id="right-zone" class="col-8" v-if="nutrients">
        <button @click="intoBasket(nutrients)" class="cart-button"><v-icon style="color: #fff;">mdi-cart</v-icon></button>
        <h2 style="margin-top: 10px; margin-bottom: 50px;">{{ nutrients.food_name}}</h2>
        <div class="food-image col-12">
          <img style="max-height: 200px" :src="require(`@/assets/diet/${nutrients.food_name}.png`)" :alt="nutrients.food_name">
        </div>
        <!-- table -->
        <table class="table table-borderless nutrient-data">
          <caption>영양성분 표</caption>
          <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col" style="color: red;">칼로리</th>
              <th scope="col" style="color: orange;">탄수화물</th>
              <th scope="col" style="color: #ffbd07;">단백질</th>
              <th scope="col" style="color: lightgreen;">지방</th>
              <th scope="col" style="color: lightblue;">나트륨</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row" style="color: gray;">함량</th>
              <td>{{ nutrients.food_kcal }}kcal</td>
              <td>{{ nutrients.food_carbon }}g</td>
              <td>{{ nutrients.food_protein }}g</td>
              <td>{{ nutrients.food_fat }}g</td>
              <td>{{ nutrients.food_natrium }}mg</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>  
  </div>
</template>

<script>
import axios from 'axios'
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl

export default {
  name: 'Macro',
  data() {
    return {
      foods: [],
      nutrients: '',
      basketData: [],
      totalMacro: {
        calorie: 0,
        carbon: 0,
        protein: 0,
        fat: 0,
        natrium: 0,
      },
      macroData: this.$route.params.macroData,
      nickname: sessionStorage.getItem('user_nickname'),
    }
  },
  methods: {
    fetchMacro() {
      axios.get(`${resourceHost}diet/list/`)
        .then(res => {
          //console.log(res.data)
          this.foods = res.data
        })
    },
    onSelectMenu(food) {
      //console.log(food)
      this.nutrients = food
    },
    intoBasket(food) {
      var flag = false;
      for (var i=0; i < this.basketData.length; i++) {
        //console.log(this.basketData[i])
        if (this.basketData[i].food_name === food.food_name) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        this.totalMacro.calorie = this.totalMacro.calorie + food.food_kcal;
        this.totalMacro.carbon = this.totalMacro.carbon + food.food_carbon;
        this.totalMacro.protein = this.totalMacro.protein + food.food_protein;
        this.totalMacro.fat = this.totalMacro.fat + food.food_fat;
        this.totalMacro.natrium = this.totalMacro.natrium + food.food_natrium;
        this.basketData.push(food)
        //console.log('push')
      } else {
        alert('이미 담겨있습니다.')
      }
      
    },
    removeBasket(food, index) {      
      this.totalMacro.calorie = this.totalMacro.calorie - food.food_kcal * food.food_cnt;
      this.totalMacro.carbon = this.totalMacro.carbon - food.food_carbon * food.food_cnt;
      this.totalMacro.protein = this.totalMacro.protein - food.food_protein * food.food_cnt;
      this.totalMacro.fat = this.totalMacro.fat - food.food_fat * food.food_cnt;
      this.totalMacro.natrium = this.totalMacro.natrium - food.food_natrium * food.food_cnt;
      food.food_cnt = 1;
      this.basketData.splice(index, 1);
      
    },
    minusFoodCnt(food) {
      if (food.food_cnt > 1) {
        food.food_cnt -= 1;
        this.totalMacro.calorie = this.totalMacro.calorie - food.food_kcal;
        this.totalMacro.carbon = this.totalMacro.carbon - food.food_carbon;
        this.totalMacro.protein = this.totalMacro.protein - food.food_protein;
        this.totalMacro.fat = this.totalMacro.fat - food.food_fat;
      } else {
        alert('1개 이상 선택가능합니다.')
      }

    },
    plusFoodCnt(food) {
      food.food_cnt += 1;
      this.totalMacro.calorie = this.totalMacro.calorie + food.food_kcal;
      this.totalMacro.carbon = this.totalMacro.carbon + food.food_carbon;
      this.totalMacro.protein = this.totalMacro.protein + food.food_protein;
      this.totalMacro.fat = this.totalMacro.fat + food.food_fat;

    },
    
  },
  created() {
    this.fetchMacro();
  },
  computed: {},
}

</script>

<style scoped>
.trainer-chat {
  background-color: #fff;
  color: gray;
  padding: 20px 10px 20px;
  border-radius: 10px;
  font-size: 25px;
}
.trainer-chat > img {

}
.food-group {
  /* background-color: #fff; */
  /* max-height: 500px; */
  margin-top: 20px;
}
.name-group {
  overflow-y: auto;
  max-height: 500px;
  /* max-height: 400px; */
}
.food-name {
  list-style-type: none;
}
#scene {
  box-shadow: 0 1px 3px rgba(0,0,0,0.12),
              0 1px 2px rgba(0,0,0,0.24);
  background-color: #fff;
  border-radius: 10px;
}

#left-zone {
  font-size: 20px;
  color: darkgray;
}

.food-name:hover {
  color: red;
}

.nutrient-data {
  margin-top: 50px;
}

#right-zone {
  position: relative;
}

#right-zone > .cart-button {
  position: absolute;
  top: 0px;
  right: 0px;
  border: none;
  background-color: red;
  border-radius: 4px;
  padding: 10px;
}

#right-zone > .cart-button:focus {
  outline-style: none;
}

caption {
  caption-side: top;
}

.count-button {
  padding-left: 5px;
  padding-right: 5px;
  margin-left: 5px;
  margin-right: 5px;
  border: 1px solid gray;
  border-radius: 6px;
  background-color: transparent;
}

.cart-remove-button {
  background-color: transparent;
  border: none;
}
/* shopping list

.alert.alert-info {
  background-color: #42b983;
  color: white;
}

hr {
  margin: 3px 0;
}

.task {
  background-color: orange;  
  color: white;
  cursor: pointer;
  border-radius: 50px 50px;
}

.task:hover {
  background-color: rgba(255,165,0,.85);
  color: black;
}

.basket-group {
  margin: 5px 0px;    
}

.btn.btn-danger {
  background-color: rgba(0,0,0,.1);
  border-color: rgba(0,0,0,.3);
  color: #666;
}

.task .btn.btn-danger {
  background-color: rgba(255,0,0,.1);
  border-color: rgba(120,0,0,.3);
  color: rgba(120,0,0,.5);
}

.menu {
  padding: 10px;
} */


</style>