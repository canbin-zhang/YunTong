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
        <h4 class="modal-title">修改密码</h4>
    </div>
    <div class="modal-body">
    
	<section class="content">	
		<div class="tab-content">	
		
				<form id="form-resetPwd" action="${pageContext.request.contextPath}/Employee/resetPassword" method="post">
					 
					 <div class="tab-pane active" id="tab-common">
		                 <div class="row data-type">
								<input type="hidden" name="eid" id="eid" value="${sessionScope.employee.eid}" />
								<div class="col-md-12 data">
									<span id="employeeInfo" style="font-size: 25px;"></span>
								</div>
							
								 <div class="col-md-4 title">员工姓名</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input type="text" class="form-control required" id="ename" name="ename" value="${employee.ename}" readonly/>
                                    </div>
									</div>
							
									<div class="col-md-4 title">原密码：</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="text" class="form-control required" value="${employee.epassword }" readonly/>
                                    </div>
									</div>
									
									<div class="col-md-4 title">输入新密码：</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="password" class="form-control required" id="epassword" name="epassword"  onblur="verifypassword()"/>
                                    </div>
									</div>
									
									<div class="col-md-4 title">确认密码：</div>
                                    <div class="col-md-8 data">
                                    <div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="password" class="form-control required" id="password2" name="password2"  onblur="verifypassword2()" disabled="disabled"/>
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
        <button type="button" id="btn-resetPwd" class="btn btn-primary" data-dismiss="modal" disabled="disabled">修改</button>
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
        
   
    function verifypassword(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Employee/verifypassword",
	        data:{'epassword':$("#epassword").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	               $('#employeeInfo').html("<font style='vertical-align: middle' color='green'><strong>OK</strong></font>")
	               $('#password2').removeAttr("disabled");
	               $('#epassword').attr("readonly","readonly");
	           }else {
	        	   $('#employeeInfo').html("<font style='vertical-align: middle' color='red'><strong>"+data+"</strong></font>");
	           }
	         
	            
	       }
	   });
	}
    
	function verifypassword2(){
	    $.post({
	        url:"${pageContext.request.contextPath}/Employee/verifypassword2",
	        data:{'epassword':$("#epassword").val(),'password2':$("#password2").val()},
	        success:function (data) {
	            if (data.toString()=='OK'){
	            	$('#employeeInfo').html("<font style='vertical-align: middle' color='green'><strong>OK</strong></font>")
		            $('#btn-resetPwd').removeAttr("disabled","disabled");
	            	$("#password2").attr("readonly","readonly");
		        }else {
		        	$('#employeeInfo').html("<font style='vertical-align: middle' color='red'><strong>"+data+"</strong></font>");
		        }
	           
	       }
	   });
	}
	
	$('#btn-resetPwd').click(function () {
		if($("#epassword").val()==''||$("#password2").val()==''){
			$("#employeeInfo").html("<font color='red'><strong>请检查输入数据</strong></font>");
		}else if($("#epassword").val()!=''&&$("#password2").val()!=''){
      		$('#form-resetPwd').submit();
		}
	});
	
    </script>
</body>
</html>
