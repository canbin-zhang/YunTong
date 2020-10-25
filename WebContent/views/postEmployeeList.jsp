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
    <title>岗位管理</title>
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
                    	岗位管理
                    <small>员工列表</small>
                </h1>
            </section>
            <!-- 内容头部 /-->

            <!-- 正文区域 -->
            <section class="content">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">岗位名称为 “<strong style="color: purple">${post.pname}</strong>” 的员工共有：<strong style="color: blue">${pageInfo.size}</strong>名</h3>
                    </div>

                    <div class="box-body">

                        <!-- 数据表格 -->
                        <div class="table-box">

                            <!--工具栏-->
                            <div class="pull-left">
                                <div class="form-group form-inline">
                                    <div class="btn-group">
                                   		<shiro:hasRole name="人事部">
                                        <button type="button" id="btn-addemployee" class="btn btn-default" title="添加"><i class="glyphicon glyphicon-plus"></i> 添加 </button>
                                        </shiro:hasRole>
                                        <button type="button" id="btn-refresh" class="btn btn-default" title="刷新"><i class="fa fa-refresh"></i> 刷新</button>
                                    </div>
                                </div>
                            </div>
                            <!-- 员工搜索框 -->
                           <div class="box-tools pull-right">
                            	<button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#searchModal" ><i class="fa fa-search"> 搜 索 </i></button> 	
                            </div>
                            <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                            <h4 class="modal-title" id="myModalLabel">员工搜索</h4>
			                        </div>
			                        <div class="modal-body">
			                            <form id="form-search" action="${pageContext.request.contextPath}/Employee/employeeSearch?pageNum=1&pageSize=7" target="_blank" method="post">
	                                		
		                                	<div class="form-group form-inline">
								                <label class="radio-inline">
								                    <input type="radio" value="eid" name="property">ID
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="ename" name="property">姓名
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="esex" name="property">性别
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="email" name="property">邮箱
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="ephone" name="property">手机
								                </label>
								                <label class="radio-inline">
								                    <input type="radio"  value="status" name="property">状态
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
			                            <button type="button" id="btn-searchEmployee" class="btn btn-primary" data-dismiss="modal">提交</button>
			                        </div>
			                    </div>
			                </div>
            			</div>
                            <!--工具栏/-->

                            <!--数据列表-->
                            <table id="dataList" class="table table-bordered table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th class="text-center"> 序号</th>
                                    <th class="text-center"> 员工ID</th>
                                    <th class="text-center"> 员工姓名</th>
                                    <th class="text-center"> 员工性别</th>
                                    <th class="text-center"> 邮箱</th>
                                    <th class="text-center"> 联系方式</th>
                                    <th class="text-center"> 状态</th>
                                    <th class="text-center"> 操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="employee" items="${pageInfo.list}" varStatus="status">
                                    <tr>
                                        <td class="text-center">${status.index+1+(pageInfo.pageNum-1)*pageInfo.pageSize}</td>
                                        <td class="text-center">${employee.eid}</td>
                                        <td class="text-center">${employee.ename}</td>
                                        <td class="text-center">${employee.esex}</td>
                                        <td class="text-center">${employee.email}</td>
                                        <td class="text-center">${employee.ephone}</td>
                                        <c:if test="${employee.status=='在职'}">
                                        	<td class="text-center"><span class="label label-success">${employee.status}</span></td>
                                        </c:if>
                                        <c:if test="${employee.status=='请假'}">
                                        	<td class="text-center"><span class="label label-warning">${employee.status}</span></td>
                                        </c:if>
                                        <c:if test="${employee.status=='离职'}">
                                        	<td class="text-center"><span class="label label-danger">${employee.status}</span></td>
                                        </c:if>
                                        <td class="text-center">
                                       		<a type="button" class="btn btn-info  btn-xs" href="${pageContext.request.contextPath}/Employee/employeeDetail?eid=${employee.eid}" data-toggle="modal" data-target="#myModal">详情</a>
                                            <shiro:hasRole name="人事部">
                                            <a type="button" class="btn btn-primary  btn-xs" href="${pageContext.request.contextPath}/Employee/toUpdateEmployee?eid=${employee.eid}" data-toggle="modal" data-target="#myModal" >编辑</a>
                                            
                                            <a type="button" class="btn btn-danger  btn-xs" href="${pageContext.request.contextPath}/Employee/toDeleteEmployee?eid=${employee.eid}" data-toggle="modal" data-target="#myModal">删除</a>
                                        	</shiro:hasRole>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <!--
                            <tfoot>
                            <tr>
                            <th>Rendering engine</th>
                            <th>Browser</th>
                            <th>Platform(s)</th>
                            <th>Engine version</th>
                            <th>CSS grade</th>
                            </tr>
                            </tfoot>-->
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
                                    <a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=${pageInfo.navigateFirstPage}&pageSize=${pageInfo.pageSize}" aria-label="Previous">首页</a>
                                </li>
                                <li><a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}">上一页</a></li>
                                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                    <li><a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
                                </c:forEach>
                                <li><a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}">下一页</a></li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=${pageInfo.navigateLastPage}&pageSize=${pageInfo.pageSize}" aria-label="Next">尾页</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <!-- 添加员工后重定向回来的提示 -->
	            <c:if test="${successMessage!=null}">
	            	<div class="alert alert-success alert-dismissible" role="alert">
	  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  					<strong>操作成功!</strong> 
	  					新添加的用户id为：<strong>${successMessage.eid},</strong>
	  					密码为：<strong>${successMessage.epassword}</strong>
					</div>
	            </c:if>
	            
	             <!-- 删除员工后重定向回来的提示 -->
	            <c:if test="${deleteMessage!=null}">
	            	<div class="alert alert-success alert-dismissible" role="alert">
	  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  					<strong>${deleteMessage}</strong> 
					</div>
	            </c:if>
	            
	            <!-- 修改员工后重定向回来的提示 -->
	            <c:if test="${updateMessage!=null}">
	            	<div class="alert alert-success alert-dismissible" role="alert">
	  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  					<strong>${updateMessage}</strong> 
					</div>
	            </c:if>
            </section>
            <!-- 正文区域 /-->
    
            <!-- 员工详情 -->
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
            const  url = "${pageContext.request.contextPath}/Post/postEmployeeList?pid=${post.pid}&pageNum="+num+"&pageSize=8";
            window.location.href=url;
        });



        //解决modal只加载一次的问题
        $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
            $(".modal-backdrop").remove();  //解决多次modal后背景变黑的问题
        });

        //添加员工按钮
        $('#btn-addemployee').click(function () {
            window.location.href="${pageContext.request.contextPath}/Employee/toaddEmployee";
        });
        
        //搜索提交
        $('#btn-searchEmployee').click(function () {
			$('#form-search').submit();
		});
     

    </script>
</body>

</html>
<!---->