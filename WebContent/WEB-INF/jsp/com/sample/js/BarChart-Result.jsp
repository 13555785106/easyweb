<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="contextPath" value="/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${contextPath}theme/js/echarts.js"></script>
<title>BAR</title>
</head>
<body>

	<h2 style="text-align:center;">条形图</h2>
	<div id="container" style="height: 400px">
	</div>
	<script type="text/javascript">
		var data = ${reqResult};
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		app.title = '坐标轴刻度与标签对齐';

		option = {
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : data.xAxisData,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '日收入',
				type : 'bar',
				barWidth : '60%',
				data : data.seriesData
			} ]
		};
		
		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}
	</script>
</body>
</html>