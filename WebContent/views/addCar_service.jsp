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
        <h4 class="modal-title">添加车辆检修记录</h4>
    </div>
    <div class="modal-body">
	<section class="content">	
		<div class="tab-content">	
				<form id="form-addCar_service" action="${pageContext.request.contextPath}/Car_service/addCar_service" method="post">
					 
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
								<input type="hidden" name="operator_id" id="operator_id" value="${sessionScope.employee.eid}" />
								
								<div class="col-md-12 data">
									<span id="car_serviceInfo" style="font-size: 25px;"></span>
								</div>
							
								 <div class="col-md-4 title">车牌号</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
                                        <input type="text" class="form-control" id="cid" name="cid" placeholder="车牌号" onblur="verifyCar()" />
                                    </div>
									</div>
									
									<div class="col-md-4 title">开始日期</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-calendar-o fa-fw"></i></span>
                                        <input type="text" class="form-control start_date " id="datepicker" name="start_date" placeholder="开始日期 " onblur="verifyStart_date()" readonly/>
                                    </div>
									</div>
									
									<div class="col-md-4 title">结束日期</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-calendar-o fa-fw"></i></span>
                                        <input type="text" class="form-control end_date " id="datepicker2" name="end_date" placeholder="结束日期 " onblur="verifyEnd_date()" readonly/>
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
        <button type="button" id="btn-addCar_service" class="btn btn-primary" data-dismiss="modal" disabled="disabled">提交</button>
    </div>

    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
     <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    
    <script type="text/javascript">
        
    $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
            $(".modal-backdrop").remove();
        });
        
    $('#btn-addCar_service').click(function () {
      	$('#form-addCar_service').submit();
	});
  //Date picker
	$('#datepicker').datepicker({
		autoclose: true,
		format: 'yyyy-mm-dd'
	});
  
	$('#datepicker2').datepicker({
		autoclose: true,
		format: 'yyyy-mm-dd'
	});
	
    //Ajax请求验证用户信息
   function verifyCar(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Car_service/verifyCar",
	        data:{'cid':$("#cid").val()},
	        success:function (data) {
	        	if(data.toString()=="<font color='green'><strong>OK</strong></font>"){
	        		$("input[name='start_date']").removeAttr("readonly","readonly");
	        		$('#cid').attr("readonly","readonly");
	        	}
	            	 $("#car_serviceInfo").html(data);
	       }
	   });
	}
   function verifyStart_date(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Car_service/verifyStart_date",
	        data:{'start_date':$("input[name='start_date']").val()},
	        success:function (data) {
	        	if(data.toString()=="<font color='green'><strong>OK</strong></font>"){
	        		$("input[name='end_date']").removeAttr("readonly","readonly");
	        		$("input[name='start_date']").attr("readonly","readonly");
	        		$("input[name='start_date']").removeAttr("id");
	        	}
	            	 $("#car_serviceInfo").html(data);
	       }
	   });
	}
   function verifyEnd_date(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Car_service/verifyEnd_date",
	        data:{'end_date':$("input[name='end_date']").val(),'start_date':$("input[name='start_date']").val()},
	        success:function (data) {
	        	if(data.toString()=="<font color='green'><strong>OK</strong></font>"){
	        		$('#btn-addCar_service').removeAttr("disabled","disabled");
	        		$("input[name='end_date']").attr("readonly","readonly");
	        		$("input[name='end_date']").removeAttr("id");
	        	}
	            	 $("#car_serviceInfo").html(data);
	       }
	   });
	}
   
  
   
	
    </script>
</body>
</html>
