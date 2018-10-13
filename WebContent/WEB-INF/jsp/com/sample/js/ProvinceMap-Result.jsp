<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="contextPath" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>辽宁省地区分布图</title>
<!-- 引入 ECharts 文件 -->
    <script src="${contextPath}theme/js/echarts.js"></script>
    <script src="${contextPath}theme/js/jquery.js"></script>
	<script src="${contextPath}mapdata/js/liaoning.js"></script>
    <script type="text/javascript">
	    $(function (){
	    	//js方式
	    	initchartForjs();
	    });
    </script>
</head>
<body>
<h2>辽宁省地区分布图</h2>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width:600px;height:500px;border:1px solid #ccc;"></div>
</body>
<script type="text/javascript">
//引入相应的js文件，然后就可以加载相应的地图
function initchartForjs() {
	var data = ${reqResult};
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
   option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: '{b} {c}'
	    },
	    dataRange: {
	        min: 0,
	        max: 2000,
	        color:data.rangeColor,
	        text:['高','低'],
	        calculable : true
	    },
	    series : [
	        {
	            name: data.seriesName,
	            type: 'map',
	            mapType: data.seriesName,
	            selectedMode : 'single',
	            itemStyle:{
	                normal:{
	                	label:{show:true},
	                	areaColor:'#FF9966',
	                	borderWidth:1,
		                borderColor:'green'
	        		},
	                emphasis:{
	                	label:{show:true},
	                	areaColor: '#6699ff'
	        		}
	            },
	            data:data.seriesData
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.on('click', function (params) {
        console.log(params);
        console.log(params.data.name);
    });
}
</script>
</html>