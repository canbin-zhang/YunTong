<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>添加驾照</title>
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
<style>
/*解决模态框显示后页面偏移问题和背景颜色变黑问题*/
body.modal-open {
	overflow-y: auto !important;
	padding-right: 0 !important;
	//
	解决了右边框每次加17px问题
}

.modal-backdrop {
	opacity: 0 !important;
	filter: alpha(opacity = 0) !important;
	//
	解决背景颜色加深问题
}
</style>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title">添加驾照类型</h4>
	</div>
	<div class="modal-body">
		<form id="form-addLicense"
			action="${pageContext.request.contextPath}/License/addLicense"
			method="post">
			<div class="row data-type">
				<div class="col-md-2 title">驾照ID</div>
				<div class="col-md-4 data">
					<div class="input-group">
						<span class="input-group-addon"><i
							class="fa fa-address-card-o fa-fw"></i></span> <input type="text"
							class="form-control" id="lid" name="lid" required="required" />
					</div>
				</div>

				<div class="col-md-2 title">驾照名字</div>
				<div class="col-md-4 data">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-star-o fa-fw"></i></span> 
						<input type="text"	class="form-control" id="lname" name="lname" required="required">
					</div>
				</div>

				<div class="col-md-2 title">学费</div>
				<div class="col-md-4 data">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-rmb fa-fw"></i></span>
						<input type="text" class="form-control" id="lmoney" name="lmoney"	required="required" >
					</div>
				</div>
				
				<div class="col-md-2 title"></div>
				<div class="col-md-4 data"></div>
			</div>
		</form>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="button" id="btn-addLicense" class="btn btn-primary"
			data-dismiss="modal">提交</button>

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
	
	<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
        $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
            $(".modal-backdrop").remove();
        });
        
        //提交用户更新表单
        $('#btn-addLicense').click(function () {
			$('#form-addLicense').submit();
		});
    </script>
</body>
</html>
