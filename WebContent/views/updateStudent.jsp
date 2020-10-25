<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>学员详情</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
<link rel="icon" href="${pageContext.request.contextPath}/img/car.ico"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<style>
/*解决模态框显示后页面偏移问题和背景颜色变黑问题*/
body.modal-open {
	overflow-y: auto !important;		
	padding-right: 0 !important;//解决了右边框每次加17px问题
}
.modal-backdrop {
  opacity: 0 !important;
  filter: alpha(opacity=0) !important;//解决背景颜色加深问题
}

</style>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title">编辑学员</h4>
	</div>
	<div class="modal-body">
		<div class="alert alert-warning" role="alert" id="info">提示！！！您正在修改学员信息</div>
		<p class="text-center">
			<img
				src="${pageContext.request.contextPath}/photos/${student.sphoto}"
				class="img-circle" height="120px" width="120px" />
		</p>
		
			<div class="tab-content">
				<form id="updateForm"
					action="${pageContext.request.contextPath}/Student/updateStudent"
					method="post" enctype="multipart/form-data">
					<div class="tab-pane active" id="tab-common">
						<div class="row data-type">

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-id-card-o fa-fw"></i></span> <input type="text"
										class="form-control" id="sid" name="sid"
										value="${student.sid}" disabled /> 
										<input type="hidden" class="form-control" name="sid" value="${student.sid}" readonly="readonly"/>
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-user-o fa-fw"></i></span> 
										<input type="text" id="sname" name="sname" class="form-control" value="${student.sname}">
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-mars fa-fw"></i></span> <select id="ssex" name="ssex"
										class="form-control">
										<c:choose>
											<c:when test="${student.ssex=='男'}">
												<option value="男" selected="selected">男</option>
												<option value="女">女</option>
											</c:when>
											<c:otherwise>
												<option value="男">男</option>
												<option value="女" selected="selected">女</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-phone fa-fw"></i></span> 
										<input type="text"	class="form-control" id="sphone" name="sphone" value="${student.sphone}" onblur="verifysphone()" />
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-cog fa-spin fa-fw"></i></span>
			            			<select name="subject" id="subject"  class="form-control input-sm"  >
		                                		<option value= "科目一">科目一</option>
		                                		<option value= "科目二">科目二</option>
		                                		<option value= "科目三">科目三</option>
		                                		<option value= "科目四">科目四</option>	
		                             </select>
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i  class="fa fa-photo fa-fw"></i></span> 
									<a href="javascript:;"	class="a-upload"> 
										<input type="file" name="sphotofile"	id="sphotofile" value="${student.sphotofile}">点此更换照片(128*128)
									</a>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
		
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		<button type="button" id="btn-updateStudent" class="btn btn-primary">提交</button>

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
		
		$("#myModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			$(".modal-backdrop").remove();
		});
		
		//提交用户更新表单
		$('#btn-updateStudent').click(function() {
			if($("sname").val()==''){
				$("#info").html("学员姓名不能为空!!!");
			}if($("sphone").val()==''){
				$("#info").html("联系方式不能为空!!!");
			}if($("#sname").val()!=''&&$("#sphone").val()!='')
				$('#updateForm').submit();
			});
		
		//前端验证数据
		function verifysphone(){
		    $.post({
		        url:"${pageContext.request.contextPath}/Ajax/verifysphone",
		        data:{'sphone':$("#sphone").val()},
		        success:function (data) {
		            if (data.toString()=='OK'){
		               $("#btn-updateStudent").removeAttr("disabled");
		               $("#info").html("提示！！！您正在修改学员信息"); 
		           }else {
		        	   $("#btn-updateStudent").attr("disabled","disabled");
		               $("#info").html(data.toString()); 
		           }           
		       }
		   });
		};
		
		
	</script>
</body>
</html>
