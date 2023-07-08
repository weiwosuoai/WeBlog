<template>
    <div>
        <el-card shadow="never" class="border-1">
            <template #header>
                <div class="flex justify-between">
                    <span class="text-sm"><!-- card title -->PV 访问量统计</span>
                </div>
            </template>
            <!-- card body -->
            <div id="pvChart" style="width: 100%; height: 300px;">

            </div>
        </el-card>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'
import { getDashboardPVStatisticsInfo } from '@/api/admin/dashboard'

getDashboardPVStatisticsInfo().then((e) => {
    var chartDom = document.getElementById('pvChart');
    var myChart = echarts.init(chartDom);
    var option;

    
    if (e.success) {
        var date = e.data.pvDates
        var data = e.data.pvCounts

        option = {
            xAxis: {
                type: 'category',
                data: date
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: data,
                    type: 'line'
                }
            ]
        };

        option && myChart.setOption(option);
    }
})


</script>
