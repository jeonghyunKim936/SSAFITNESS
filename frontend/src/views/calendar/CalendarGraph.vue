<template>
<div><canvas id="canvas" ref="canvas" width="300" height="200"></canvas></div>
</template>

<script>
import axios from "axios";
import constants from '../../lib/constants.js'
const resourceHost = constants.baseUrl

export default {
    props: ['diaryList','days','weights','min_weight'],
    mounted: function () {
        var ctx = document.getElementById('canvas').getContext('2d');
        var myLineChart = new Chart(document.getElementById("canvas"), {
            type: 'line',
            data: {
                labels: this.days,
                datasets: [{
                    label: '몸무게',
                    data: this.weights,
                    borderColor: "rgba(255, 201, 14, 1)",
                    backgroundColor: "rgba(255, 201, 14, 0.5)",
                    fill: true,
                    lineTension: 0
                }]
            },
            options: {
                responsive: true,
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '날짜'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        ticks: {
                            suggestedMin: this.min_weight,
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'KG'
                        }
                    }]
                }
            }
        });
    }
}
</script>

<style>

</style>
