<%--
  Created by IntelliJ IDEA.
  User: GC
  Date: 2020/6/16
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>员工详情</title>
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
	padding-right: 0 !important;//解决了右边框每次加17px问题
}
.modal-backdrop {
  opacity: 0 !important;
  filter: alpha(opacity=0) !important;//解决背景颜色加深问题
}

</style>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">请假详情</h4>
    </div>
    <div class="modal-body">
	<section class="content">	
		<div class="tab-content">	
				<form>
					 
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
		                 
							<div class="col-md-4 title">开始日期:</div>
                            <div class="col-md-8 data">
                            <div class="input-group">
								<span class="input-group-addon"><i class="fa  fa-calendar-o fa-fw"></i></span>
                                <input type="text" class="form-control required" 
                                	   placeholder="<fmt:formatDate value='${askoff.start_date}' type='date' pattern='yyyy-MM-dd'/>" readonly>
                            </div>
							</div>
							
							<div class="col-md-4 title">结束日期:</div>
                            <div class="col-md-8 data">
                            <div class="input-group">
								<span class="input-group-addon"><i class="fa  fa-calendar-o fa-fw"></i></span>
                                <input type="text" class="form-control required" 
                                	   placeholder="<fmt:formatDate value='${askoff.end_date}' type='date' pattern='yyyy-MM-dd'/>" readonly>
                            </div>
							</div>
							
							<div class="col-md-4 title">操作员:</div>
                            <div class="col-md-8 data">
                            <div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input type="text" class="form-control required" 
                                	   placeholder="${operator_name}" readonly>
                            </div>
							</div>
							
							<div class="col-md-4 title">请假原因:</div>
                            <div class="col-md-8 data">
                            <div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <input type="text" class="form-control required" 
                                	   placeholder="${askoff.reason}" readonly>
                            </div>
							</div>
							
							
						  	</div>	
						 </div>		
       
        		</form>
		 </div>
	</section>
	</div>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary"" data-dismiss="modal">关闭</button>
    </div>

    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
            $(".modal-backdrop").remove();
        });
    </script>
</body>
</html>
