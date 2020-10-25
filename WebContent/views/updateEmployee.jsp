<%--
  Created by IntelliJ IDEA.
  User: GC
  Date: 2020/6/16
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>员工详情</title>
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
		<h4 class="modal-title">编辑员工</h4>
	</div>
	<div class="modal-body">
		<div class="alert alert-warning" role="alert" id="info">提示！！！您正在修改员工信息</div>
		<p class="text-center">
			<img
				src="${pageContext.request.contextPath}/photos/${employee.ephoto}"
				class="img-circle" height="120px" width="120px" />
		</p>
		
			<div class="tab-content">
				<form id="updateForm"
					action="${pageContext.request.contextPath}/Employee/updateEmployee"
					method="post" enctype="multipart/form-data">
					<div class="tab-pane active" id="tab-common">
						<div class="row data-type">

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-id-card-o fa-fw"></i></span> <input type="text"
										class="form-control" id="eid" name="eid"
										value="${employee.eid}" disabled /> 
										<input type="hidden" class="form-control" name="eid" value="${employee.eid}" />
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-user-o fa-fw"></i></span> 
										<input type="text" id="ename" name="ename" class="form-control" value="${employee.ename}">
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-mars fa-fw"></i></span> <select id="esex" name="esex"
										class="form-control">
										<c:choose>
											<c:when test="${employee.esex=='男'}">
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
									<span class="input-group-addon"><i
										class="fa fa-phone fa-fw"></i></span> <input type="text"
										class="form-control" id="ephone" name="ephone"
										value="${employee.ephone}" onblur="verifyephone()"/>
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope fa-fw"></i></span> <input type="email"
										class="form-control" id="email" name="email"
										value="${employee.email}" onblur="verifyemail()" />
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-star-o fa-fw"></i></span> <select id="pid" name="pid"
										class="form-control">
										<c:forEach items="${posts}" var="post">
											<c:choose>
												<c:when test="${employeePost.pname==post.pname}">
													<option value="${post.pid}" selected="selected">${post.pname}</option>
												</c:when>
												<c:otherwise>
													<option value="${post.pid}">${post.pname}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-hourglass-3 fa-fw"></i></span> <select id="status"
										name="status" class="form-control">
										<c:choose>
											<c:when test="${employee.status=='在职'}">
												<option value="在职" selected="selected">在职</option>
												<option value="请假">请假</option>

											</c:when>
											<c:when test="${employee.status=='请假'}">
												<option value="在职">在职</option>
												<option value="请假" selected="selected">请假</option>
											</c:when>
										</c:choose>
									</select>
								</div>
							</div>

							<div class="col-md-6 data">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-photo fa-fw"></i></span> <a href="javascript:;"
										class="a-upload"> <input type="file" name="ephotofile"
										id="ephotofile" value="${employee.ephotofile}">点此更换照片(128*128)
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
		<button type="button" id="btn-updateEmployee" class="btn btn-primary" >提交</button>

	</div>
	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	$("#myModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			$(".modal-backdrop").remove();
		});

		//提交用户更新表单
		$('#btn-updateEmployee').click(function() {
			if($("#ephone").val()==''||$("#email").val()==''||$("#ename").val()==''){
				$("#info").html("请检查填入数据");
			}else if($("#ephone").val()!=''&&$("#email").val()!=''&&$("#ename").val!=''){
				$('#updateForm').submit();
			}
		});
		
		
		function verifyephone(){
		    $.post({
		        url:"${pageContext.request.contextPath}/Ajax/verifyemployee",
		        data:{'ephone':$("#ephone").val()},
		        success:function (data) {
		            if (data.toString()=='OK'){
		            	$("#ephone").attr("readonly","readonly");
		            	$("#btn-updateEmployee").removeAttr("disabled");
		           }else {  
		        	   $("#btn-updateEmployee").attr("disabled","disabled");
		                $("#info").html(data);
		                
		           }           
		       }
		   });
		}
		function verifyemail(){
		    $.post({
		        url:"${pageContext.request.contextPath}/Ajax/verifyemail",
		        data:{'email':$("#email").val()},
		        success:function (data) {
		            if (data.toString()=='OK'){
		            	$("#email").attr("readonly","readonly");
		            	$("#btn-updateEmployee").removeAttr("disabled");
		           }else {
		        	   $("#btn-updateEmployee").attr("disabled","disabled");
		                $("#info").html(data);
		                
		           }           
		       }
		   });
		}
		
	</script>
</body>
</html>
