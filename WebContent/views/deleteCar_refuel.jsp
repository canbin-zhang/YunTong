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
        <h4 class="modal-title">删除车辆加油记录</h4>
    </div>
    <div class="modal-body">
    <div class="alert alert-warning" role="alert">警告！！！您正在删除车辆加油记录</div>
	<section class="content">	
		<div class="tab-content">	
		
				<form id="form-deleteCar_refuel" action="${pageContext.request.contextPath}/Car_refuel/deleteCar_refuel" method="post">
					 
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
								<input type="hidden" name="id" id="id" value="${car_refuel.id}" />
								
							
								 <div class="col-md-4 title">车牌号</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
                                        <input type="text" class="form-control required" id="cid" name="cid" value="${car_refuel.cid}" readonly />
                                    </div>
									</div>
							 	
									<div class="col-md-4 title">车管员ID</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
                                        <input type="text" class="form-control required" id="operator_id" name="operator_id" value="${car_refuel.operator_id}" readonly />
                                    </div>
									</div>
									<div class="col-md-4 title">加油日期</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-calendar-o fa-fw"></i></span>
                                        <input type="text" class="form-control required" id="start_date datepicker" name="start_date" 
                                        value="<fmt:formatDate value='${car_refuel.refuel_date}' type='date' pattern='yyyy-MM-dd'/>" readonly/>
                                    </div>
                                    </div>
                                    <div class="col-md-4 title">加油金额</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-rmb fa-fw"></i></span>
                                        <input type="text" class="form-control" id="refuel_money" name="refuel_money" 
                                        value="<fmt:formatNumber value="${car_refuel.refuel_money}" pattern="#.00" type="number"/>" readonly/>
                                    </div>
									</div>
									
								
						 </div>		
       				</div>
        		</form>
		 </div>
	</section>
	</div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="btn-deleteCar_refuel" class="btn btn-danger" data-dismiss="modal"  >删除</button>
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
        
    $('#btn-deleteCar_refuel').click(function () {
      	$('#form-deleteCar_refuel').submit();
	});
   
   
	
    </script>
</body>
</html>
