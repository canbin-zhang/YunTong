<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>登录</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <link rel="icon" href="${pageContext.request.contextPath}/img/car.ico"  type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>

</head>
<body style="background: #d2d6de;">
	<div class="container">
      <form class="form-signin" action="${pageContext.request.contextPath}/Employee/login" method="post" style="width: 425px;">
        <h1 class="form-signin-heading" align="center">运通驾校信息管理系统</h1>
      
        
      	<div style="background-color: white;width: 425px;height: 250px;padding: 30px;">
      		<h5 align="center" style="margin-top: -13px;">登录系统</h5>
	      	<div class="form-group has-feedback">
	            <input type="text" class="form-control"  name="eid" placeholder="用户id(身份证)" required >
	            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	        </div>
	        
	        <div class="form-group has-feedback">
	            <input type="password" class="form-control" name="epassword" placeholder="密码" required>
	            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	        </div>
	      	
	       
	       
	        
	        <a class="a2" href="${pageContext.request.contextPath}/Employee/forgetPwd" >忘记密码？</a> <br>
	        
	        <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn-login">登录</button>
        </div>
      </form>
      <br>
    
      <!-- 登录提示 -->
      <c:if test="${message!=null}">
         	<div class="alert alert-danger alert-dismissible" role="alert" style="width: 425px;">
				<button type="button" id="btn-close-success" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong>${message}</strong> 
			</div>
     </c:if>
      <!-- 密码重置提示 -->
      <c:if test="${resetSuccess!=null}">
         	<div class="alert alert-success alert-dismissible" role="alert" style="width: 425px;">
				<button type="button" id="btn-close-success" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong>${resetSuccess}</strong> 
			</div>
     </c:if>
     
     <!-- 密码修改提示 -->
      <c:if test="${successMessage!=null}">
         	<div class="alert alert-success alert-dismissible" role="alert" style="width: 425px;">
				<button type="button" id="btn-close-success" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong>${successMessage}</strong> 
			</div>
     </c:if>
     
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		$('#btn-login').click(function clearSession(){
		    $.post({
		        url:"${pageContext.request.contextPath}/Employee/clearSession",
		        data:{ },
		        success:function (data) {
		                 }
		   });
		})
		$('#btn-close-success').click(function clearSession(){
		    $.post({
		        url:"${pageContext.request.contextPath}/Employee/clearSession",
		        data:{ },
		        success:function (data) {
		                 }
		   });
		})
	</script>
	
</body>
</html>