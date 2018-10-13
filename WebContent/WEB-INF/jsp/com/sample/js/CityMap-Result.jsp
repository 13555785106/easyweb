<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="contextPath" value="/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>沈阳市城区分布图</title>
<!-- 引入 ECharts 文件 -->
    <script src="${contextPath}theme/js/echarts.js"></script>
    <script src="${contextPath}theme/js/jquery.js"></script>
    <script type="text/javascript">
	    $(function (){
	    	//json方式
	    	initchartForJson();
	    });
    </script>
</head>
<body>
<h2 style="text-align:center;">沈阳市城区分布图</h2>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="height:480px;border:1px solid #ccc;padding:10px;"></div>
</body>
<script type="text/javascript">
function initchartForJson() {
	var data = ${reqResult};
	var mapJsonPath = '${contextPath}mapdata/json/cities/210100.json';
	var areaName = data.areaName;
	var option = {
		tooltip : {
	        trigger: 'item'
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
        		name : areaName,
        		roam:true,
                scaleLimit:{min:1,max:100},
		        type : 'map',
		        map : areaName,
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
		        tooltip: {
	                trigger: 'item',
	                formatter: '{b} {c}'
	            },
			    data:data.seriesData
        	} 
        ]
    };
	
	$.get(mapJsonPath, function(mapJson) {
        echarts.registerMap(areaName, mapJson);
        var chart = echarts.init(document.getElementById('main'));
        chart.setOption(option);
    });
}
</script>
</html>