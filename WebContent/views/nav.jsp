<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body>
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image" style="padding-left: 20px">
					<img src="${pageContext.request.contextPath}/photos/${sessionScope.employee.ephoto}?random="
						class="img-circle" alt="User Image"  >
				</div>
				<div class="pull-left info" style="padding-left: 80px">
					<span><i class="glyphicon glyphicon-user text-success"></i> <b>${sessionScope.pname} </b></span><br/><br/>
					
					&nbsp;&nbsp;&nbsp;
					<c:if test="${sessionScope.employee.status=='在职'}">
						<span class="label label-success">${sessionScope.employee.status}</span>
					</c:if>
					<c:if test="${sessionScope.employee.status=='请假'}">
						<span class="label label-warning">${sessionScope.employee.status}</span>
					</c:if>
				</div>
			</div>
			<!-- search form -->
			<!--<form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="搜索...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>-->
			<!-- /.search form -->


			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu">

				<li id="admin-index">
					<a href="${pageContext.request.contextPath}/Employee/index">
						<i class="glyphicon glyphicon-home"></i> <span>首页</span>
					</a>
				</li>
				<!-- 菜单 -->
				<!-- 所有员工都有的功能 -->
				<shiro:hasAnyRoles name="财务,业务员,教练,车管员,人事部">
				<li class="treeview"><a href="#"> <i class="fa fa-user"></i>
						<span>个人中心</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="form-general">
						<a href="${pageContext.request.contextPath}/Employee/employeeDetail?eid=${sessionScope.employee.eid}" data-toggle="modal" data-target="#myModal"> 
						<i class="fa fa-circle-o"></i> 个人资料
						</a></li>

						<li id="form-advanced"><a href="${pageContext.request.contextPath}/Employee/salaryListByEid?pageNum=1&pageSize=8&eid=${sessionScope.employee.eid}">
								<i class="fa fa-circle-o"></i> 工资管理
						</a></li>
						
						<li id="form-advanced"><a href="${pageContext.request.contextPath}/Askoff/askoffListByEid?eid=${sessionScope.employee.eid}&pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 请假管理
						</a></li>
					</ul>
				</li>
				</shiro:hasAnyRoles>
				<!-- 人事部管理 -->
				<shiro:hasAnyRoles name="人事部,admin">
				<li class="treeview"><a href="#"> <i
						class="fa fa-users"></i> <span>员工管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs">
							<a href="${pageContext.request.contextPath}/Employee/toEmployeeList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 员工列表
							</a></li>
							
						
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Employee/toaddEmployee">
								<i class="fa fa-circle-o"></i> 添加员工
						</a></li>
						
						
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Employeelist/employeelistList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 招聘记录
						</a></li>
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Dimission/dimissionList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 离职记录
						</a></li>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class=" fa fa-star-o "></i> <span>岗位管理</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Post/postList?pageNum=1&pageSize=7">
								<i class="fa fa-circle-o"></i> 岗位列表
						</a></li>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-user-times"></i> <span>请假管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Askoff/askoffList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 请假列表
						</a></li>
                        <!-- 
								1、将在职的员工调整为请假：
								（1）判断当天哪些员工请假（只遍历请假申请已经通过了的）
								（2）判断员工状态是否为请假
								2、将请假的员工调整为在职：
								（1）遍历请假的员工（只遍历请假申请已经通过的）
								（2）判断当天是否还是请假的日期
								 -->
						<shiro:hasRole name="人事部">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Askoff/toAskoffStatus?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 请假调整
								
						</a></li>
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Askoff/toAtPostStatus?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 在职调整
								
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				</shiro:hasAnyRoles>
				<!-- 教练功能 -->
				<shiro:hasRole name="教练">
				<li class="treeview">
				<a href="#"> 
					<i class="fa fa-users "></i>
					<span>学员管理</span> 
					<span class="pull-right-container"> 
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li id="admin-login">
							<a href="${pageContext.request.contextPath}/Student/tocoachStudentList?pageNum=1&pageSize=8&eid=${sessionScope.employee.eid}"> <i class="fa fa-circle-o"></i> 查看学员信息 </a>
						</li>
					</ul>
				</li>

				<li class="treeview"><a href="#"> <i
						class="fa fa-car"></i> <span>考试管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Exam/toExamList?pageNum=1&pageSize=8&eid=${sessionScope.employee.eid}">
								<i class="fa fa-circle-o"></i> 考试记录
						</a></li>
					</ul>
					</li>
				<li class="treeview"><a href="#"> <i class="fa fa-car"></i>
						<span>训练管理</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						

						<li id="elements-icons"><a href="${pageContext.request.contextPath}/Exercise/toExerciseList?pageNum=1&pageSize=8&eid=${sessionScope.employee.eid}">
								<i class="fa fa-circle-o"></i> 练车记录
						</a></li>
					</ul>
				</li>
				</shiro:hasRole>
				
				<!-- 财务人员功能 -->
				<shiro:hasAnyRoles name="财务,admin">
				<li class="treeview"><a href="#"> <i
						class="fa fa-rmb"></i> <span>工资管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Salary/salaryList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 发放记录
						</a></li>
						
						<shiro:hasRole name="财务">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Salary/postSalaryList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 工资调整
						</a></li>
						</shiro:hasRole>
						
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-bar-chart"></i> <span>支出管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Disburse/disburseList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 查看支出
						</a></li>
						<shiro:hasRole name="财务">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Disburse/toAddDisburse"  data-toggle="modal" data-target="#myModal">
								<i class="fa fa-circle-o"></i> 添加支出
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-bar-chart"></i> <span>收入管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Income/incomeList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 查看收入
						</a></li>
						<shiro:hasRole name="财务">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Income/toAddIncome"  data-toggle="modal" data-target="#myModal">
								<i class="fa fa-circle-o"></i> 添加收入
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				</shiro:hasAnyRoles>
				
				<!-- 业务人员管理 -->
				<shiro:hasAnyRoles name="业务员,admin">
				<li class="treeview"><a href="#"> <i
						class="fa fa-users"></i> <span>招生管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/License/licenseList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 驾照管理
						</a></li>

						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Student/toStudentList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 学员管理
						</a></li>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-tv"></i> <span>公告管理</span> <span
						class="pull-right-container"> <i
							class="fa   fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Notice/notices">
								<i class="fa fa-circle-o"></i> 公告列表
						</a></li>

					</ul>
				</li>
				</shiro:hasAnyRoles>
				
				<!-- 车管员管理 -->
				<shiro:hasAnyRoles name="车管员,admin">
				<li class="treeview"><a href="#"> <i
						class="fa fa-car"></i> <span>车辆管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left  pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Car/carList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 车辆列表
						</a></li>
						<shiro:hasRole name="车管员">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Car/toAddCar"  data-toggle="modal" data-target="#myModal">
								<i class="fa fa-circle-o"></i> 购入车辆
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-pencil-square-o"></i> <span>使用管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Car_use/car_useList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 使用记录
						</a></li>

						
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa  fa-wrench"></i> <span>检修管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Car_service/car_serviceList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 检修记录
						</a></li>
						<shiro:hasRole name="车管员">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Car_service/toAddCar_service" data-toggle="modal" data-target="#myModal">
								<i class="fa fa-circle-o"></i> 添加记录
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-hourglass-start"></i> <span>加油管理</span> <span
						class="pull-right-container"> <i
							class="fa  fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">

						<li id="charts-chartjs"><a href="${pageContext.request.contextPath}/Car_refuel/car_refuelList?pageNum=1&pageSize=8">
								<i class="fa fa-circle-o"></i> 加油记录
						</a></li>
						<shiro:hasRole name="车管员">
						<li id="charts-morris"><a href="${pageContext.request.contextPath}/Car_refuel/toAddCar_refuel" data-toggle="modal" data-target="#myModal">
								<i class="fa fa-circle-o"></i> 添加记录
						</a></li>
						</shiro:hasRole>
					</ul>
				</li>
				</shiro:hasAnyRoles>
				
				
				<!-- 菜单 /-->

			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</body>
</html>