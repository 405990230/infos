<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息板块</title>

</head>
<body>
	<h3>天气信息
		<a href="javascript:searchWeather();" id="detail"><font style="font-size:12px;vertical-align:text-top;">选取地址</font></a>
	</h3>
	<h3>股票信息</h3>
       <input class="" id="searchValue" type="text"/>
       <button type="button" onclick="searchDZH();">搜索</button>
	<div><a href="${ctx}/dzh/getStock?type=sectors"><button type="button">板块涨停</button></a>
		 <a href="${ctx}/dzh/getStock?type=hsidx"><button type="button">沪深指数</button></a>
	</div>
	<h3>新闻
		<a href="javascript:getCategory();" id="newsList"><font style="font-size:12px;vertical-align:text-top;">查看主题</font></a>
	</h3>
	<h4 id='categoryCN'></h4>
	<h4 id='categoryEN'></h4>

	<div id="NewsLists">

	</div>
	<div id="NewsDetail">
		<%--<img src="${ctx}/static/image/news/carLogo.png" alt="新闻图片">--%>
	</div>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	function searchDZH(){
        if ($("#searchValue").val() == "" || $("#searchValue").val() == null) {
            alert.msg("请输入搜索条件!");
            return;
        }
        window.location.href = ctx+"/dzh/getStock?type=search&param=" + $("#searchValue").val();
	}
	
	function searchWeather(){
		window.location.href = ctx+"/weather/getMap";
	}

	function getCategory() {
        $.post(ctx + "/news/getCategory", function (data) {
            //data = JSON.parse(data);
            if (data.code == "0") {
                console.log(data.data[0]);
                console.log(data.data[0].categories[0]);
				for(var i=0;i<data.data.length;i++){
                    var html= "";
                    for(var k=0;k<data.data[i].categories.length;k++){
                        html += "<a href=javascript:getNewsList("+"'"+data.data[i].categories[k]+"'"+",'"+data.data[i].oid+"');>"
						+data.data[i].names[k]+"</a>&nbsp;&nbsp;&nbsp;"
                    }
                    if(i==0){
                        $("#categoryEN").html(html);
					}else{
                        $("#categoryCN").html(html);
					}

				}

            } else {
                console.log("测试失败");
            }

        });

    }

    var infoList;
	//get  NewsList
    function getNewsList(category,language){
        //$.post(ctx + "/news/getNewsList",{category:category,language:language},function (data) {
        $.get(ctx + "/news/getNewsList?category="+category+"&language="+language,function (data) {
            //data = JSON.parse(data);
            console.log("请求新闻");
            if(data.code == "0"){
                var html= "";
                var items = data.data[0].items;
                for(var i=0;i<items.length;i++){
                    html += "<div>标题"+(i+1)+"&nbsp;:&nbsp;"
						 //+"<a href='javascript:void(0)' onclick="+"'showNews("+JSON.stringify(items[i])+")'>"
                        +"<a href='javascript:void(0)' onclick="+"'showNews("+i+")'>"
						+items[i].title+"</a>"+"</div>";
                }
                $("#NewsLists").html(html);
                infoList = items;
			}else{
                alert("服务异常，请稍后重试");
			}

        });
	}

	//showNewsDetail
	function showNews(index){
        console.log("新闻序号: "+index);
        var item = infoList[index];
        console.log("新闻详情： "+item);
        var html= "<h3 id='newsDetails'><a href='javascript:void(0)'>新闻标题: "+item.title+"</a></h3>"
			+"<div>时间："+item.timestamp+"</div>"
            +"<div>主要内容：</br>"+item.paragraphs+"</div>"
		    +"<div>新闻图片：</br>";
        if(item.images==null||item.images=="") {
            html=html+"<img src='"+ctx+"/static/image/news/carLogo.png' alt='新闻图片'/>"
                +"</div>"
		}else
            html=html+"<img src='"+item.images[0].full.link+"'alt='新闻图片'/>"
			+"</div>"
		$("#NewsDetail").html(html);
	}

    //showNewsDetail
    function showNews2(title,timestamp,paragraphs,images){
        var html= "<h3 id='newsDetails'><a href='javascript:void(0)'>新闻标题: "+title+"</a></h3>"
            +"<div>时间："+timestamp+"</div>"
            +"<div>主要内容：</br>"+paragraphs+"</div>"
            +"<div>新闻图片：</br>";
        if(images==null||images=="") {
            html=html+"<img src='"+ctx+"/static/image/news/carLogo.png' alt='新闻图片'/>"
                +"</div>"
        }else
            html=html+"<img src='"+images+"'alt='新闻图片'/>"
                +"</div>"
        $("#NewsDetail").html(html);
    }
</script>
</html>