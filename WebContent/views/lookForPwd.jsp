<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta charset="utf-8">
	<meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/car.ico"  type="image/x-icon" />
 
	<title>找回密码</title>
</head>
<body style="background: #d2d6de;">
<div class="container">
      <form id="resetForm" class="form-horizontal" id="form-Pwd" action="${pageContext.request.contextPath}/Employee/lookForPwd" method="post">
	        <h1 class="form-signin-heading" align="center">找 回 密 码</h1>
	      
	        <div class="form-group has-feedback">
		        <div class="col-md-4 col-md-offset-4">
		        	<div class="input-group">
		        		<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
		        		<input type="text" name="email" id="email" class="form-control" placeholder="请输入邮箱地址" required autofocus onblur="verifyEmailByRegister()"/>
		        	</div>
		        </div>
		        <span  style="font-size:22px" id="emailInfo"></span>
	        </div>
	        
	    
       
	        <div class="form-group">
		        <div class="col-md-3 col-md-offset-4">
		        	<div class="input-group">
		        	 	<span class="input-group-addon"><i class="fa fa-get-pocket"></i></span>
		        	 	<input type="text" name="valicode"  id="valicode" class="form-control" placeholder="请输入验证码" required onblur="verifyvalicode()" disabled="disabled"/>
		        	</div>
		        </div>
		        
		        <div class="col-md-1 ">
		        	<input type="button" id="btnvalicode"  class="btn btn-primary " style=" margin-left: -28px" disabled="disabled" value="获取验证码"/>
		        </div>
		        <span style="font-size:22px" id="valicodeInfo"></span>
	        </div>
	        
	        <div class="form-group">
		        <div class="col-md-4 col-md-offset-4">
		        	<div class="input-group">
			        	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			        	<input type="password" id="password" name="epassword" class="form-control" placeholder="新密码" disabled="disabled"  onblur="verifypassword()"/>
		        	</div>
		        </div>
		        <span style="font-size:22px" id="passwordInfo"></span>
	        </div>
	       
	        <div class="form-group">
		        <div class="col-md-4 col-md-offset-4">
		        	<div class="input-group">
			        	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			        	<input type="password" id="password2" name="password2" class="form-control" placeholder="确认密码" disabled="disabled"  onblur="verifypassword2()"/>
		        	</div>
		        </div>
		        <span  style="font-size:22px" id="password2Info"></span>
	        </div>
	        
	      
	       <div class="form-group">
		        <div class="col-md-4 col-md-offset-4">
		        	<button class="btn btn-lg btn-primary btn-block" type="submit" id="btn-submit" disabled="disabled">提交</button>
		        </div>
	        </div>
 	</form>
      
    </div>
 	<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<script type="text/javascript">
	
	var countdown = 100;
	
	function verifyEmailByRegister(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/verifyEmailByRegister",
	        data:{'email':$("#email").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	                $("#emailInfo").css("color","green");
	                $('#btnvalicode').removeAttr("disabled","disabled");
	           }else {
	                $("#emailInfo").css("color","red");
	           }
	            $("#emailInfo").html(data); 
		       }
	       });
	   }
	
	$("#btnvalicode").click(function(){
		$.post({
	        url:"${pageContext.request.contextPath}/Ajax/sendMail",
	        data:{'email':$("#email").val()},
	        success:function (data) {
	            $("#valicodeInfo").css("color","green");
	            $("#valicodeInfo").html(data);
	            $("#valicode").removeAttr("disabled");
	       }
	   });
		settime($("#btnvalicode"));
	})	
	
	function verifyvalicode(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/valicode",
	        data:{'email':$("#email").val(),'valicode':$("#valicode").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	                $("#valicodeInfo").css("color","green");
	                $("#password").removeAttr("disabled");
	                $("#email").attr("readonly",true);
	           }else {
	                $("#valicodeInfo").css("color","red");
	           }
	            $("#valicodeInfo").html(data); 
	       }
	   });
	}
	
	
	function verifypassword(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/verifyemployee",
	        data:{'epassword':$("#password").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	                $("#passwordInfo").css("color","green");
	                $("#password2").removeAttr("disabled");
	           }else {
	                $("#passwordInfo").css("color","red");
	           }
	            $("#passwordInfo").html(data); 
	           
	       }
	   });
	}
	
	function verifypassword2(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/verifypassword2",
	        data:{'password':$("#password").val(),'password2':$("#password2").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	                $("#password2Info").css("color","green");
	                $("#password").attr("readonly",true);
	                $("#password2").attr("readonly",true);
	                $("#btn-submit").removeAttr("disabled");
	           }else {
	                $("#password2Info").css("color","red");
	           }
	           $("#password2Info").html(data); 
	           
	       }
	   });
	}
	
	
	
	
	function settime(obj) {
        if (countdown == 0) {
            obj.attr('disabled', false);
			countdown = 100;
			return;
			} else {
	            obj.attr('disabled', true);
				obj.val("重新发送(" + countdown + ")");
				countdown--;
				$('#btnvalicode').css('background', '#265a88')
				$('#btnvalicode').css('color','#fff');
        	}
        setTimeout(function() {
            settime(obj)
       		 }, 1000)
            						
      }
	
	

</script>

</body>
</html>