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
                    <small>学员列表</small>
                </h1>
            </section>
            <!-- 内容头部 /-->

            <!-- 正文区域 -->
            <section class="content">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">列表</h3>
                    </div>

                    <div class="box-body">

                        <!-- 数据表格 -->
                        <div class="table-box">

                            <!--工具栏-->
                            <div class="pull-left">
                                <div class="form-group form-inline">
                                    <div class="btn-group">
                                        <button type="button" id="btn-refresh" class="btn btn-default" title="刷新"><i class="fa fa-refresh"></i> 刷新</button>
                                    </div>
                                </div>
                            </div>
                            <!-- 学生搜索框 -->
                            <div class="box-tools pull-right">
                            	<button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#searchModal" ><i class="fa fa-search"> 搜 索 </i></button> 	
                            </div>
                            <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                               <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                            <h4 class="modal-title" id="myModalLabel">学生搜索</h4>
			                        </div>
			                        <div class="modal-body">
			                            <form id="form-search" action="${pageContext.request.contextPath}/Student/coachStudentSearch?pageNum=1&pageSize=8&eid=${sessionScope.employee.eid}" target="_blank" method="post">
	                                		
		                                	<div class="form-group form-inline">
								                <label class="radio-inline">
								                    <input type="radio" value="sid" name="property">学生ID
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="sname" name="property">姓名
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="ssex" name="property">性别
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="sphone" name="property">联系方式
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="lid" name="property">驾照ID
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="subject" name="property">目前科目
								                </label>
								            </div>
		                                	<div class="input-group">
			                                    <input type="text" name="keyword" class="form-control" placeholder="Search"/>
			                                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
			     							</div>
			                               
			                               
								 
								            
								       
		                                </form>
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			                            <button type="button" id="btn-searchStudent" class="btn btn-primary">提交</button>
			                        </div>
			                    </div>
			                </div>
                            </div>
                           
                            <!--工具栏/-->

                            <!--数据列表-->
                            <table id="dataList" class="table table-bordered table-striped table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center"> 序号</th>
                                    <th class="text-center"> 学生ID</th>
                                    <th class="text-center"> 学生姓名</th>
                                    <th class="text-center"> 学生性别</th>          
                                    <th class="text-center"> 联系方式</th>
                                    <th class="text-center"> 驾照ID</th>
                                    <th class="text-center"> 目前科目</th>
                                    <th class="text-center"> 操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="student" items="${pageInfo.list}" varStatus="status">
                                    <tr>
                                        <td class="text-center">${status.index+1+(pageInfo.pageNum-1)*pageInfo.pageSize}</td>
                                        <td class="text-center">${student.sid}</td>
                                        <td class="text-center">${student.sname}</td>
                                        <td class="text-center">${student.ssex}</td>
                                        <td class="text-center">${student.sphone}</td>
                                        <td class="text-center">${student.lid}</td>
                                        <td class="text-center">${student.subject}</td>
                                        <td class="text-center">
                                        			
		                                            <a type="button" class="btn btn-default  btn-xs" href="${pageContext.request.contextPath}/Student/studentDetail?sid=${student.sid}" data-toggle="modal" data-target="#myModal">详情</a>		                                            
	                                        		<a type="button" class="btn btn-info  btn-xs" href="${pageContext.request.contextPath}/Exam/toAddExam?sid=${student.sid}" >考试</a>		                  
	                                        		<a type="button" class="btn btn-info  btn-xs" href="${pageContext.request.contextPath}/Exercise/toAddExercise?sid=${student.sid}" >训练</a>		                  
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                              
                            </table>
                            <!--数据列表/-->

                        </div>
                        <!-- 数据表格 /-->


                    </div>
                    <!-- /.box-body -->

                    <!-- .box-footer-->
                    <div class="box-footer">
                       <div class="pull-left">
                            <div class="form-group form-inline">
                                <h5>总共<strong>${pageInfo.pages}</strong>页，共<strong>${pageInfo.getTotal()}</strong>条数据。 
                                	当前第<strong>${pageInfo.pageNum}</strong>页。
                                	跳转至第<input type="text" class="form-control" id="pageNum" value="${pageInfo.pageNum}" style="width: 60px;margin-left: 5px;margin-right: 5px;" >页
                                
                                </h5>
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <ul class="pagination">
                                <li>
                                    <a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=${pageInfo.navigateFirstPage}&pageSize=${pageInfo.pageSize}&eid=${sessionScope.employee.eid}" aria-label="Previous">首页</a>
                                </li>
                                <li><a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}&eid=${sessionScope.employee.eid}">上一页</a></li>
                                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                    <li><a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}&eid=${sessionScope.employee.eid}">${pageNum}</a></li>
                                </c:forEach>
                                <li><a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}&eid=${sessionScope.employee.eid}">下一页</a></li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=${pageInfo.navigateLastPage}&pageSize=${pageInfo.pageSize}&eid=${sessionScope.employee.eid}" aria-label="Next">尾页</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>

            </section>
            <!-- 正文区域 /-->
    
            <!-- 学生详情 -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <h5>正在查询....</h5>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
         
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 内容区域 /-->

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


        //选择其他页面页数刷新
        $('#btn-refresh').click(function(){
            var num=$('#pageNum').val();
            const  url = "${pageContext.request.contextPath}/Student/tocoachStudentList?eid=${sessionScope.employee.eid}&pageNum="+num+"&pageSize=8";
            window.location.href=url;
        });



        //解决modal只加载一次的问题
        $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
            $(".modal-backdrop").remove();  //解决多次modal后背景变黑的问题
        });

 
        
      
         //搜索提交
        $('#btn-searchStudent').click(function () {
			$('#form-search').submit();
		});
     
     

    </script>
</body>

</html>
<!---->