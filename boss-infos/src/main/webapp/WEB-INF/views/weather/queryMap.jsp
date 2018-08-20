<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百度地图</title>
</head>
<style type="text/css">
	 html{height:100%}
     body{height:100%;margin:0px;padding:0px}
     .size {
     	height:100%
     }
	.changeSize{
		width:500px;
		height:500px
	}
</style>
<body>
	<h3>天气信息
		<a href="${ctx}/api/hello" id="home" style="font-size:12px;vertical-align:top;">返回</a>
	</h3>
	<input type="text" id="cityName" style="width:300px;height:27px"/>
	<!-- <input type="button" onclick="setCity()" value="设置"  /> -->
	<button  onclick="setCity()" style="height:24px;font-size:14px">设置</button>
		<a href="javascript:void(0);"  onclick="changeSize();" id="changeSize">地图变大</a>
		<a href="javascript:searchWeather();" id="detail">查询天气</a>
	<div id="container" class = "changeSize"></div>
	
	<input type="text"  id="lng" />
	<input type="text"  id="lat" />
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&key=79d13dd2c1076b1ad827484228ee4dea&v=1.1&services=true&s=1" ></script>--%>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=WTZG7UB629GBLY2Yok5tThvD&s=1"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var cityName;
    $(function () {
     	$("#home").click(function () {
    	     $("#container").addClass("changeSize");
    	});      
    });
	function changeSize(){
		var msg = $("#changeSize").text();
 		if("地图缩小"==msg){
			$("#container").removeClass("size");
			$("#container").addClass("changeSize");
			$("#changeSize").text("地图变大");
		}else{
			$("#changeSize").text("地图缩小");
			$("#container").removeClass("changeSize");
			$("#container").attr("class", "size");
			}
	}
	
    var map = new BMap.Map("container");        //在container容器中创建一个地图,参数container为div的id属性;
    
    var point = new BMap.Point(120.2,30.25);    //创建点坐标
    map.centerAndZoom(point, 14);                //初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom();                //激活滚轮调整大小功能
    map.addControl(new BMap.NavigationControl());    //添加控件：缩放地图的控件，默认在左上角；
    map.addControl(new BMap.MapTypeControl());        //添加控件：地图类型控件，默认在右上方；
    map.addControl(new BMap.ScaleControl());        //添加控件：地图显示比例的控件，默认在左下方；
    map.addControl(new BMap.OverviewMapControl());  //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl   
    
  //定位  
/*     var geolocation = new BMap.Geolocation();  
       geolocation.getCurrentPosition(function(result){  
        if(this.getStatus() == window.BMAP_STATUS_SUCCESS){  
               var mk = new BMap.Marker(result.point);//创建一个覆盖物  
               map.addOverlay(mk);//增加一个标示到地图上  
               map.panTo(result.point);  
               addMarker(result.point);
               latitude  = result.point.lat;//获取到的纬度  
               longitude = result.point.lng;//获取到的经度 
               console.log('您的位置：' + result.point.lng + ',' + result.point.lat );
           }  else {
               console.log('failed:'+this.getStatus());
           } 
       },{enableHighAccuracy: true}); */
    
    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	var search = new BMap.LocalSearch("中国", {
	  onSearchComplete: function(result){
	    if (search.getStatus() == BMAP_STATUS_SUCCESS){
	      var res = result.getPoi(0);
	      var point = res.point;
	      map.centerAndZoom(point, 11);
	    }
	  },renderOptions: {  //结果呈现设置，
	    map: map,  
	    autoViewport: true,  
	    selectFirstResult: true 
	  } ,onInfoHtmlSet:function(poi,html){//标注气泡内容创建后的回调函数，有了这个，可以简单的改一下返回的html内容了。
	     //alert(html.innerHTML)
	  }//这一段可以不要，只不过是为学习更深层次应用而加入的。	  
	});
	 
    function searchByStationName() {
        map.clearOverlays();//清空原来的标注
        var keyword = document.getElementById("cityName").value;
        cityName = keyword;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            $("#lng").val(poi.point.lng);
            $("#lat").val(poi.point.lat);
            //alert("经度:"+poi.point.lng+"---"+"纬度:"+poi.point.lat);
            map.centerAndZoom(poi.point, 13);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            var content = document.getElementById("cityName").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
            // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        });
        localSearch.search(keyword);
    } 
	function setCity(){
	  //search.search(document.getElementById("cityName").value);
		searchByStationName();
	}
	
   function addMarker(point){  // 创建图标对象     
        var myIcon = new BMap.Icon("position.png", new BMap.Size(23, 50), {offset: new BMap.Size(10, 25)});  
        // 创建标注对象并添加到地图     
        var marker = new BMap.Marker(point, {icon: myIcon});      
        map.addOverlay(marker);  
    } 
	function searchWeather(){
		window.location.href = ctx+"/weather/getInfos?lon="+$("#lng").val()+"&lat="+$("#lat").val()+"&cityName="+encodeURI(encodeURI(cityName));
	}
</script>
</html>
