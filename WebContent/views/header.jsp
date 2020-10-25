<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<body>
<header class="main-header">
            <a href="#" class="logo">
                <span class="logo-lg"><b> 运 通 信 息 系 统 </b></span>
            </a>
            
            <nav class="navbar navbar-static-top">
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            		<span class="sr-only">Toggle navigation</span>
        		</a>
                <span id="timeShow" class="time1" style="width:100%; height:50px; line-height:50px; text-align:center;padding-left: 800px;font-size: 15.5px;color: white;"></span>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                       
                       
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                        <img src="${pageContext.request.contextPath}/photos/${sessionScope.employee.ephoto}" class="user-image" alt="User Image">
		                        <span class="hidden-xs">${sessionScope.employee.ename}</span>
                    		</a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header">
                                    <img src="${pageContext.request.contextPath}/photos/${sessionScope.employee.ephoto}" class="img-circle" alt="User Image">

                                    <p>
                                        ${sessionScope.employee.ename} - ${sessionScope.pname}
                                        
                                    </p>
                                </li>
                               
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="${pageContext.request.contextPath}/Employee/toResetPwd?eid=${sessionScope.employee.eid}" class="btn btn-default btn-flat" data-toggle="modal" data-target="#myModal">修改密码</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="${pageContext.request.contextPath}/Employee/logout" class="btn btn-default btn-flat">注销</a>
                                    </div>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </nav>
        </header>
        <!-- 页面头部 /-->
    <script type="text/javascript">
		var t = null;
		t = setTimeout(time,1000);//开始执行
		function time()
		{
			clearTimeout(t);//清除定时器
			dt = new Date();
			var y=dt.getYear()+1900;
			var mm=dt.getMonth()+1;
			var d=dt.getDate();
			var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			var day=dt.getDay();
			var h=dt.getHours();
			var m=dt.getMinutes();
			var s=dt.getSeconds();
			if(h<10){h="0"+h;}
			if(m<10){m="0"+m;}
			if(s<10){s="0"+s;}
			document.getElementById("timeShow").innerHTML =  "当前时间为："+y+"年"+mm+"月"+d+"日   "+weekday[day]+"   "+h+":"+m+":"+s+"";
			t = setTimeout(time,1000); //设定定时器，循环执行    
		}
	</script>
</body>
     
</html>