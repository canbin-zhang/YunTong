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
        <h4 class="modal-title">添加收入</h4>
    </div>
    <div class="modal-body">
	<section class="content">	
		<div class="tab-content">	
				<form id="form-addIncome" action="${pageContext.request.contextPath}/Income/addIncome" method="post">
					 
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
								<input type="hidden" name="operator_id" id="operator_id" value="${sessionScope.employee.eid}" />
								<div class="col-md-12 data">
									<span id="incomeInfo" style="font-size: 25px;"></span>
								</div>
							
								 <div class="col-md-4 title">员工ID</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"></i></span>
                                        <input type="text" class="form-control" id="eid" name="eid" placeholder="员工身份证" onblur="verifyEmployee()" required/>
                                    </div>
									</div>
									<div class="col-md-4 title">收入日期</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-calendar-o fa-fw"></i></span>
                                        <input type="text" class="form-control income_date" id="datepicker" name="income_date" placeholder="收入日期 " required/>
                                    </div>
									</div>
									<div class="col-md-4 title">收入金额</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="fa fa-rmb fa-fw"></i></span>
                                        <input type="text" class="form-control" id="income_money" name="income_money" placeholder="收入金额" required/>
                                    </div>
									</div>
									<div class="col-md-12 data">
										<textarea class="form-control"  id="income_detail" name="income_detail" placeholder="收入明细(100字以内)" maxlength="100" required></textarea>
									</div>
				               	    
								
						 </div>		
       				</div>
        		</form>
		 </div>
	</section>
	</div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="btn-addIncome" class="btn btn-primary" disabled="disabled">提交</button>
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
        
    $('#btn-addIncome').click(function () {
    	if($("#eid").val()==''||$(".income_date").val()==''||$("#income_money").val()==''||$("#income_detail").val()==''){
    		$("#disburseInfo").html("<font color='red'><strong>请检查填入数据</strong></font>");
    	}else if($("#eid").val()!=''&&$(".income_date").val()!=''&&$("#income_money").val()!=''&&$("#income_detail").val()!=''){
    		$('#form-addIncome').submit();
      	}   	
      	
	});
    
  //Date picker
	$('#datepicker').datepicker({
		autoclose: true,
		format: 'yyyy-mm-dd'
	});
    //Ajax请求验证用户信息
   function verifyEmployee(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Income/verifyEmployee",
	        data:{'eid':$("#eid").val()},
	        success:function (data) {
	        	if(data.toString()=="OK"){
	        		$('#btn-addIncome').removeAttr("disabled","disabled");
	        		$('#eid').attr("readonly","readonly");
	        	}
	            	 $("#incomeInfo").html(data);
	       }
	   });
	}
  
   
	
    </script>
</body>
</html>
