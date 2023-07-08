<template>
    <div>
        <el-card shadow="never" class="border-1">
            <template #header>
                <div class="flex justify-between">
                    <span class="text-sm"><!-- card title -->文章发布热点图</span>
                </div>
            </template>
            <!-- card body -->
            <div id="publishArticleChart" style="width: 100%; height: 300px;">

            </div>
        </el-card>

    </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeMount } from 'vue';
import * as echarts from 'echarts';
import { getDashboardPublishArticleStatisticsInfo } from '@/api/admin/dashboard'

const year = new Date().getFullYear().toString()
console.log('year:' + year)
const myData = []

var myChart = null

getDashboardPublishArticleStatisticsInfo().then((e) => {
    if (e.success) {
        let map = e.data
        for (let key in map) {
            myData.push([
                key,
                map[key]
            ]);
        }
    }

    var chartDom = document.getElementById('publishArticleChart');
    myChart = echarts.init(chartDom);

    var option;

    option = {
        visualMap: {
            show: false,
            min: 0,
            max: 10
        },
        calendar: {
            range: year
        },
        series: {
            type: 'heatmap',
            coordinateSystem: 'calendar',
            data: myData
        },
        gradientColor: [
            '#fff',
            // '#9be9a8',
            '#40c463',
            '#30a14e',
            '#216e39',
        ]
    };

    option && myChart.setOption(option);
})
</script>
