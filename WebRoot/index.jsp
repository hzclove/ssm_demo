<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("contextPath", request.getContextPath());
%>
<script>
	var path = "<%=path%>"
</script>
<jsp:include page="/assets/jquery/lib.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
<script>
	 $(document).ready(function () {
       		var url = path+'/mine/basis/user.do?method=insert',
                param={
                	userId:1101,
                	userName:"mine",
                	password:"mine",
                	memo:"test"
                };
            $.post(url,{param:JSON.stringify(param)}).then(function(res){
                console.log(res)
            });

    }); 
</script>