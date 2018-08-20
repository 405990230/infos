<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib  uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!doctype html>
<html>
<head>
	<meta content="" name="Keywords" />
    <meta content="" name="Description" />
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />   
    <meta content="telephone=no" name="format-detection" />
    <meta content="false" name="twcClient" id="twcClient" />
    <meta content="yes" name="apple-touch-fullscreen" />
</head>
<body>
         <div>
            <h4 class="modal-title">导入数据</h4>
        </div>
		<%--<form enctype="multipart/form-data" action="importTxt" method="post">--%>
		    <%--<input type="file" id="choosefile" name="importTxt"/>--%>
		    <%--<input type="file"  id="f" name="import" style="display:none;" />--%>
		    <%--<input type="submit" value="上传文件" id="submitBtn" />--%>
		<%--</form>--%>
         <form id="form1" method="post" action="importTxt" enctype="multipart/form-data">
             <tr>
                 <td width="25%" align="right">上传文件：</td>
                 <td><input id="file" type="file" name="file" style="width:300px;"></td>
             </tr>
             <tr align="center" valign="middle">
                 <td height="60" colspan="2"><input type="submit" ID="BtnOK" value="确认上传"></td>
             </tr>
         </form>
         <%-- 		<div>
            <p>"${result}"</p>
        </div> --%>

         <%--<form  id="uploadForm" name="uploadForm"--%>
                <%--enctype="multipart/form-data">--%>
             <%--&lt;%&ndash;<input name="messageContent" value="多个参数的情况下">&ndash;%&gt;--%>
             <%--<label>上传文件：</label> <input type="file" name="file">--%>
             <%--<button class="btn" type="button" id="doSave" onclick="upload()">提交</button>--%>
         <%--</form>--%>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.form.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    function upload() {
        $("#uploadForm").ajaxSubmit(
            {
                type : 'post',
                url : "Infomations/import/importTxt",

                //data:  //注意只要是写在表单里面的，都不需要加这个属性。在controller中可以根据@RequestParam String str获取到属性值。
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                success: function(data) {
                    //接受到的data还只是一个字符串，需要转成json对象
                    var obj = JSON.parse(data);
                    if(obj.flag==true){
                        alert("上传成功");
                    }else{
                        alert("error");
                    }
                },
                error: function (data)//服务器响应失败处理函数
                {
                    alert("出错");
                }
            });
    }
</script>
</html>

    

  
