<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>股票信息</title>
</head>
<style type="text/css">
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
		text-align:center !important;
	}
	table.gridtable th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #dedede;
		width: 100px;
	}
	table.gridtable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
		text-align:center !important;
	}

</style>
<body>
<h3>股票信息</h3>
	<div>
		<input class="" id="searchValue" type="text" value="${searchValue}" placeholder="请输入股票代码或股票首字母缩写"
			   style="width:200px;" pattern="[A-z][0-9]" maxlength="8" onKeyUp="value=value.replace(/[\W]/g,'')"/>
		<button type="button" onclick="searchDZH();">搜索</button>
		<a href="${ctx}/api/hello"><button type="button">返回</button></a>
	</div>
	<div><a href="${ctx}/dzh/getStock?type=sectors"><button type="button">板块涨停</button></a>
		<a href="${ctx}/dzh/getStock?type=hsidx"><button type="button">沪深指数</button></a>
	</div>
   	<div id="part2">
	        <div>
	        <c:choose>
	        	<c:when test="${ !empty sectorPojosList }">
					<h5>板块涨跌:<span style="font-size:12px;vertical-align:super;">${fn:length(sectorPojosList)}</span></h5>
					<table class="gridtable">
						<tr>
							<th>序号</th>
							<th>名称</th>
							<th>代码</th>
							<th>最新</th>
							<th>涨幅</th>
							<th>涨跌</th>
							<th>成交量</th>
							<th>今开</th>
						</tr>
						<c:forEach items="${sectorPojosList}" var="data" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${data.name}</td>
								<td><a href="getStock?type=SECTORSDETAIL&param=${data.cfgId}">${data.code}</a></td>
								<td>${data.latePrice}</td>
								<c:if test="${fn:contains(data.priceRise,'-')}">
									<td style="color:green;">${data.priceRise}</td></c:if>
								<c:if test="${fn:contains(data.priceRise,'+')}">
									<td style="color:red;">${data.priceRise}</td></c:if>
								<c:if test="${fn:contains(data.priceChange,'-')}">
									<td style="color:green;">${data.priceChange}</td></c:if>
								<c:if test="${fn:contains(data.priceChange,'+')}">
									<td style="color:red;">${data.priceChange}</td></c:if>
								<td>${data.lastPrice}</td>
								<td>${data.openPrice}</td>
							</tr>
						</c:forEach>
					</table>
	    		</c:when>
				<c:when test="${ !empty stocksPojoslist }">
					<h5>沪深指数:<span>${fn:length(stocksPojoslist)}</span></h5>
					<table class="gridtable">
						<tr>
							<th>序号</th>
							<th>名称</th>
							<th>代码</th>
							<th>最新</th>
							<th>涨幅</th>
							<th>涨跌</th>
							<th>成交量</th>
							<th>今开</th>
						</tr>
						<c:forEach items="${stocksPojoslist}" var="data" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${data.name}</td>
								<td><a href="#photo" id="${data.code}"
									   onclick="kChartSearch('${data.name}','${data.kChart.hour}','${data.kChart.day}','${data.kChart.week}','${data.kChart.month}','${data.code}');">
										${data.code}</a></td>
								<td>${data.latePrice}</td>
								<c:if test="${fn:contains(data.priceRise,'-')}">
									<td style="color:green;">${data.priceRise}</td></c:if>
								<c:if test="${fn:contains(data.priceRise,'+')}">
									<td style="color:red;">${data.priceRise}</td></c:if>
								<c:if test="${fn:contains(data.priceChange,'-')}">
									<td style="color:green;">${data.priceChange}</td></c:if>
								<c:if test="${fn:contains(data.priceChange,'+')}">
									<td style="color:red;">${data.priceChange}</td></c:if>
								<td>${data.lastPrice}</td>
								<td>${data.openPrice}</td>
								<%--<td><img src=${data.kChart.month} alt="月K"/></td>--%>
							</tr>
						</c:forEach>
					</table>
				</c:when>
	    		<c:otherwise>
					<h5>暂无数据</h5>
	    		</c:otherwise>
	    	</c:choose>
	        </div>
	    </div>
	<div id = "photodetail">
 		<%--<h3>股票走势图</h3>--%>
		<%--<table>--%>
			<%--<thead>--%>
				<%--<th>时分K</th>--%>
				<%--<th>日K</th>--%>
			<%--</thead>--%>
			<%--<tbody>--%>
				<%--<td><img src="${stocksPojoslist[0].kChart.hour}" alt="时分K" /></td>--%>
				<%--<td><img src="${stocksPojoslist[0].kChart.day}" alt="日K" /><br/></td>--%>
			<%--</tbody>--%>
		<%--</table>--%>
		<%--<table>--%>
			<%--<thead>--%>
			<%--<th>周K</th>--%>
			<%--<th>月K</th>--%>
			<%--</thead>--%>
			<%--<tbody>--%>
			<%--<td><img src="${stocksPojoslist[0].kChart.week}" alt="周K" /></td>--%>
			<%--<td><img src="${stocksPojoslist[0].kChart.month}" alt="月K" /><br/></td>--%>
			<%--</tbody>--%>
		<%--</table>--%>
	</div>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>--%>
<script type="text/javascript">
    var ctx = "${ctx}";
 	function kChartSearch(name,hour,day,week,moth,code){
/*         var msg = document.getElementById("photodetail");
        msg.innerHTML = ""; */
        $("#photodetail").html("");
		var html= "<h3 id='photo'><a href='#"+code+"'>股票走势图 : "+name+"</a></h3>"
					+"<table>"
					+"<thead>"
					+"<th>时分K</th>"
					+"<th>日K</th>"
					+"</thead><tbody><td>"
					+"<img src='"+hour+"'alt='时分K'/></td>"
					<%--+'<img src="${stocksPojoslist[0].kChart.hour}" alt="时分K" /></td>'--%>
					+'<td><img src="'+day+'" alt="日K" /><br/></td>'
					+"</tbody></table>"
					+"<table>"
					+"<thead>"
					+"<th>周K</th>"
					+"<th>月K</th>"
					+"</thead><tbody><td>"
					+'<img src="'+week+'" alt="周K" /></td>'
					+'<td><img src="'+moth+'" alt="月K" /><br/></td>'
					+"</tbody></table>"
		$("#photodetail").html(html);
        //msg.innerHTML = html;
	}

    function searchDZH(){
        if ($("#searchValue").val() == "" || $("#searchValue").val() == null) {
            alert.msg("请输入搜索条件!");
            return;
        }
        window.location.href = ctx+"/dzh/getStock?type=search&param=" + $("#searchValue").val();
    }
</script>
</html>