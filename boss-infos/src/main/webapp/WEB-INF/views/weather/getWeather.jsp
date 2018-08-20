<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天气详情</title>
</head>
<style type="text/css">
 li , .li_style {
 	list-style-type:none;
 	align:center;
 	}
 .ul_style {
 	text-align:center;
 	margin-left: -50px;
 }

 .table_day15{
 	float:left; 
 	display:inline;
 	margin:10px;
 }
  .table_dayCrrent{
 	float:left; 
 	display:inline;
 	margin:2px;
 }
 .crrent{
 	font-size:20px
 }
 .fontClass{
	 font-size:12px;
	 vertical-align:super;
	 text-decoration:none
 }
</style>
<body>
   	<div id="part2">
	        <div>
	        <c:choose>
	        	<c:when test="${ !empty dataList }">
					<h2>天气信息:<a href="${ctx}/api/hello" class="fontClass">主页></a><a href="${ctx}/weather/getMap" class="fontClass">选址</a></h2>
		<div class="box_day15">
					<h3>位置：${cityName}
						<%--<button  onclick="test()" style="height:24px;font-size:14px">测试ehcache</button>--%>
					</h3>
				<div class="table_dayCrrent">
                    <h3>当前天气<a href="javascript:get();" id="detail"><font style="font-size:12px;vertical-align:super;">详情</font></a></h3>
                    <ul class="ul_style">
                        <li class="img">
                        	<img class="pngtqico" align="absmiddle" src="${ctx}/static/image/weather/small_day_${dataList[0].currentWeather.eweather}.png" style="border:0">	                        
                        </li>
                        <li class="crrent"><font color="#f00">${dataList[0].currentWeather.temperature}℃</font></li>
                        <li class="crrent">${dataList[0].currentWeather.weather}</li>
                        <li class="crrent" style="overflow:hidden">${dataList[0].currentWeather.windDirection}&nbsp; ${dataList[0].currentWeather.windPower}</li>
                   		<%-- <li class="crrent"><font>湿度：</font>${dataList[0].currentWeather.humidity}</li> --%>
                    </ul>
                </div>
				<c:forEach items="${dataList}" var="data" varStatus="status" begin="0">
					<div class="table_day15">
						<h3 style="font-size:14px"  align="center">
							<fmt:parseDate value="${data.timestamp}" type="date" var="bulidDate" pattern="yyyy-MM-dd"/>
	                    	<fmt:formatDate value="${bulidDate}" pattern="MM月dd日"/>
	                    	(${data.week})
						</h3>
	                    <ul class="ul_style">
	                        <li class="img"><!-- <img class="pngtqico" align="absmiddle" src="http://img.tianqi.com/static/images/tianqibig/b3.png" style="border:0;width:46px;height:46px"> -->
	                        	<img class="pngtqico" align="absmiddle" src="${ctx}/static/image/weather/small_day_${data.morning.eforecast}.png" style="border:0">
	                        </li>
	                        <li><font color="#f00">${data.morning.temperature.c}℃</font>~<font color="#4899be">${data.evening.temperature.c}℃</font></li>
	                        <li><font class="cDGray">白天：</font>${data.morning.forecast}</li>
	                        <li><font class="cDGray">夜间：</font>${data.evening.forecast}</li>
	                        <li style="height:18px;overflow:hidden">${data.morning.wind.windDirection}&nbsp; ${data.morning.wind.windPower}</li>
	                    </ul>
					</div>
				</c:forEach>
<!-- 				<div class="table_day15">
                    <h3>08月29日（今日）</h3>
                    <ul>
                        <li class="img"><img class="pngtqico" align="absmiddle" src="http://img.tianqi.com/static/images/tianqibig/b3.png" style="border:0;width:46px;height:46px"><img class="pngtqico" align="absmiddle" src="http://img.tianqi.com/static/images/tianqibig/b3.png" style="border:0;width:46px;height:46px"></li>
                        <li><font color="#f00">32℃</font>~<font color="#4899be">22℃</font></li>
                        <li><font class="cDGray">白天：</font>阵雨</li>
                        <li><font class="cDGray">夜间：</font>阵雨</li>
                        <li style="height:18px;overflow:hidden">北风 2级</li>
                    </ul>
                </div> -->

	        </div>
	    		</c:when>
	    		<c:otherwise>
					<h5>暂无数据</h5>
					<h3>位置：${cityName}  <button  onclick="test()" style="height:24px;font-size:14px">测试connected time out</button></h3>
	    		</c:otherwise>
	    	</c:choose>
	        </div>

	    </div>
    	<div style="clear:both;display: none;" id="indexDetail">
    		<h3>今日指数：</h3>
    		<ul>
        		<c:forEach items="${dataList[0].indices}" var="data" varStatus="status" begin="0">
	        		<li class="crrent" >${data.type}：<font color="${data.color}">${data.description}</font></li>
	        	</c:forEach>
	        	<li class="crrent"><font>湿度：</font>${dataList[0].currentWeather.humidity}</li>
	        	<li class="crrent" >建议：<font>${dataList[0].indexDescription}</font></li>
    		</ul>
        </div>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
	function get(){
		$("#indexDetail").toggle();
	}
	function testM(){
		$.post(ctx + "/weather/testEhcache", function (data) {
			data = JSON.parse(data);
			if (data.code == "0") {
				console.log("测试成功");
            } else {
            	console.log("测试失败");
            }
	    });
	}
	
	function testM2(){
		$.post(ctx + "/weather/getInfos?lon=114.311969&lat=30.598516&cityName=%25E6%25AD%25A6%25E6%25B1%2589", function (data) {
			console.log("连接超时");
	    });
	}
	
	function test(){
        var second=20; //间隔时间1秒钟
        setInterval(testM2,second);
	}
</script>
</html>