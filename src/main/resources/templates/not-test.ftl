<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <#assign path="${springMacroRequestContext.getContextPath()}">
</head>

<body>

<div id="main" style="height:400px;width: 500px"></div>

<#--<script src="https://cdn.jsdelivr.net/npm/echarts@5.4.0/dist/echarts.js" type="text/javascript"></script>-->
<script src="${path}/echarts/echarts.js" type="text/javascript"></script>
<script>
    //指定图标的配置和数据
    var option = {
        title:{
            text:'折线图'
        },
        tooltip:{},
        legend:{
            data:['访客来源']
        },
        xAxis:{
            data:["智联招聘","51job","拉钩","Boss直聘"]
        },
        yAxis:{
        },
        series:[{
            name:'访问量',
            type:'line',
            areaStyle: {
                normal: {}
            },
            data:[600,310,200,800]
        }]
    };
    //初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    //使用制定的配置项和数据显示图表
    myChart.setOption(option);

    var baseImage = myChart.getDataURL("png");
    console.log(baseImage)
</script>



</body>
</html>