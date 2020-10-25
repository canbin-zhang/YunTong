<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">学员详情</h4>
    </div>
    <div class="modal-body">
	<section class="content">	
		<div class="tab-content">	
				<form>
					 <p class="text-center"><img src="${pageContext.request.contextPath}/photos/${student.sphoto}" class="img-circle" height="150px" width="150px" /></p>
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
					
					
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${student.sid}" readonly>
			         		</div>
			         		</div>
							
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-user-o fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${student.sname}" readonly>
							</div>
							</div>	
							
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-mars fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${student.ssex}" readonly>
							</div>
							</div>	
							
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-phone fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${student.sphone}" readonly>
							</div>
							</div>	
			    
			    			
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-id-badge fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="所属教练：${employee.ename}" readonly>
							</div>
							</div>	
							
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-star-o fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${license.lname}" readonly>
							</div>
							</div>	
			
			                <div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa fa-cog fa-spin fa-fw"></i></span>
			            		<input type="text" class="form-control"  placeholder="${student.subject}" readonly>
							</div>	
							</div>
							
							<div class="col-md-6 data">
							<div class="input-group">
			            		<span class="input-group-addon"><i class="fa  fa-calendar-o fa-fw"></i></span>
			            		<input type="text" class="form-control"  
			            		       placeholder="<fmt:formatDate value='${studentlist.sm_date}' type='date' pattern='yyyy-MM-dd'/>" readonly>
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
