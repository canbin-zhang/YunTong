<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 页面meta -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>学员管理</title>
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
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

	<!-- 页面头部 -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- 导航侧栏 -->
	<jsp:include page="nav.jsp"></jsp:include>
	<!-- 导航侧栏 /-->

	<!-- 内容区域 -->
	<div class="content-wrapper">

		<!-- 内容头部 -->
		<section class="content-header">
			<h1>
				学员管理
				<small>添加学员</small>
			</h1>
			
		</section>
		<!-- 内容头部 /-->
		<!-- 正文区域 -->
		<section class="content">	
				<div class="tab-content">	
				<form action="${pageContext.request.contextPath}/Student/addStudent" method="post" enctype="multipart/form-data">
					
                            <!--基础控件-->
                            <div class="tab-pane active" id="tab-common">
                                <div class="row data-type">

                                    <div class="col-md-2 title">身份证</div>
                                    <div class="col-md-4 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
                                        <input type="text" class="form-control" id="sid" name="sid" placeholder="学员身份证" required onblur="verifysid()"/>
                                    	<span class="glyphicon glyphicon-ok form-control-feedback" id="vsid" style="display:none"></span>
                                    </div>
									</div>
									
                                    <div class="col-md-2 title">姓名</div>
                                    <div class="col-md-4 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user-o fa-fw"></i></span>
                                        <input type="text" class="form-control" id="sname" name="sname" placeholder="学员姓名" required/>
                                    </div>
                                    </div>

                                    <div class="col-md-2 title">性别</div>
                                    <div class="col-md-4 data">
                                        <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-mars fa-fw"></i></span>
                                            <select id="ssex" name="ssex" class="form-control"  required >
                                            	
												<option value="男">男</option>
												<option value="女">女</option>
											</select>
                                    </div>
                                    </div>

                                    <div class="col-md-2 title">手机号</div>
                                    <div class="col-md-4 data">
                                    <div class="input-group">
											<span class="input-group-addon"><i class="fa fa-phone fa-fw"></i></span>
                                            <input type="text" class="form-control" id="sphone" name="sphone" placeholder="学员手机号" required onblur="verifysphone()"/>        
                                    		<span class="glyphicon glyphicon-ok form-control-feedback" id="vsphone" style="display:none"></span>
                                    </div>
                                    </div>

                                  

                                    <div class="col-md-2 title">驾照类型</div>
                                    <div class="col-md-4 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-star-o fa-fw"></i></span>		
										<select id="lid" name="lid"  class="form-control" required>
												
											<c:forEach items="${licenses}" var="license">
												<option value="${license.lid}">${license.lname}</option>
											</c:forEach>
										</select>
                                    </div>
                                    </div>

									<div class="col-md-2 title">招生日期</div>
                                    <div class="col-md-4 data">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-calendar-o fa-fw"></i></span> 
										<input type="text" class="form-control" id="datepicker" name="sm_date" placeholder="请选择" required />
									</div>
									</div>
									
									<div class="col-md-2 title">所属教练</div>
                                    <div class="col-md-4 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-badge fa-fw"></i></span>		
										<select id="eid" name="eid"  class="form-control" required>
												
											<c:forEach items="${employees}" var="employee">
												<option value="${employee.eid}">${employee.ename}</option>
											</c:forEach>
										</select>
                                    </div>
                                    </div>
                                    
                                    <div class="col-md-2 title">照片上传</div>
                                    <div class="col-md-4 data">
                                        <a href="javascript:;" class="a-upload">
            							<input type="file" name="sphotofile" id="sphotofile" required/>点击这里上传文件
        								</a>
                                    </div>
   									<input type="hidden" name="subject" value="科目一" />
                                    <div class="col-md-12 data text-center">
                                        <button type="submit" class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                    
									</div>
									</div>
								
                          </form>
					</div>
					<div class="alert alert-danger alert-dismissible" role="alert" id="verify" hidden="hidden" >
	  					<button type="button" class="close" id="close" ><span aria-hidden="true">&times;</span></button>
	  					<strong id="verifydata"></strong> 
					</div>
		</section>
		<c:if test="${failedMessage!=null}">
            	<div class="alert alert-danger alert-dismissible" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  					<strong>${failedMessage} </strong> 
				</div>
        </c:if>
        </div>
    <!--基础控件/-->

                      
	<!-- 正文区域 -->
	
	<!-- 底部导航 -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- 底部导航 /-->

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
<script>
	$(document).ready(function() {
		// 选择框
		$(".select2").select2();

		// WYSIHTML5编辑器
		$(".textarea").wysihtml5({
			locale: 'zh-CN'
		});
	});


	// 设置激活菜单
	function setSidebarActive(tagUri) {
		var liObj = $("#" + tagUri);
		if (liObj.length > 0) {
			liObj.parent().parent().addClass("active");
			liObj.addClass("active");
		}
	}


	$(document).ready(function() {
		// 激活导航位置
		setSidebarActive("admin-index");
	});

	//Date picker
	$('#datepicker').datepicker({
		autoclose: true,
		format: 'yyyy-mm-dd'
	});

	//前端验证数据
	function verifysid(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/verifystudent",
	        data:{'sid':$("#sid").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	            	$("#vsid").show();
	            	$("#verify").attr("hidden","hidden");
	           }else {
	                $("#verify").removeAttr("hidden");
	                $("#verifydata").html(data);
	                
	           }           
	       }
	   });
	}
	//前端验证数据
	function verifysphone(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Ajax/verifysphone",
	        data:{'sphone':$("#sphone").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	            	$("#vsphone").show();
	            	$("#verify").attr("hidden","hidden");
	           }else {
	                $("#verify").removeAttr("hidden");
	                $("#verifydata").html(data);
	                
	           }           
	       }
	   });
	}
	$("#close").click(function () {
		$("#verify").attr("hidden","hidden");
	})
</script>
</body>
</html>