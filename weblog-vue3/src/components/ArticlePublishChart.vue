<template>
    <div>
        <el-card shadow="never" class="border-1">
            <template #header>
                <div class="flex justify-between">
                    <span class="text-sm">文章发布热点图</span>
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

/*
* origin：将整个年度的活跃图都显示出来，这就导致因屏幕尺寸不足难以显示后面月数的热图信息
* repair：只显示已当前日期为基准，前四后二总共六个月的热图信息
* */
const currentDate = new Date();
const currentYear = currentDate.getFullYear();
const currentMonth = currentDate.getMonth() + 1;
const startYear = currentYear;
const startMonth = currentMonth - 4; // 获取最近三个月的数据
const endYear = currentYear;
const endMonth = currentMonth + 2;

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
            range: [startYear + '-' + startMonth, endYear + '-' + endMonth],
        },
        series: {
            type: 'heatmap',
            coordinateSystem: 'calendar',
            data: myData
        },
        gradientColor: [
            '#fff',
            '#40c463',
            '#30a14e',
            '#216e39',
        ]
    };

    option && myChart.setOption(option);
})
</script>
