package com.Exception;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MyExecption implements HandlerExceptionResolver{

   
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,Object arg2,
			Exception ex) {
		ModelAndView mv=new ModelAndView();
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		if(ex instanceof IncorrectCredentialsException) {
			session.setAttribute("message", "密码错误，请重新输入！！！");
			mv.setViewName("redirect:/");
		}if(ex instanceof UnknownAccountException) {
			session.setAttribute("message", "无该用户信息或该用户已离职，请重新输入！！！");
			mv.setViewName("redirect:/");
		}
		else if(ex instanceof UnauthorizedException){// ⻆⾊不⾜
			//跳转权限不⾜的⻚⾯
			mv.setViewName("redirect:/views/unauthorized.jsp");
        }else if(ex instanceof UnauthenticatedException){//没有登录 没有合法身份
            //跳转登录⻚⾯，重新登录
        	session.setAttribute("message", "您尚未登录或者登录时间已过，请先登录！！！");
            mv.setViewName("redirect:/");
        }else if(ex instanceof SQLException) {
        	mv.setViewName("redirect:/views/400.jsp");
        }else if(ex instanceof UncategorizedSQLException) {
        	mv.setViewName("redirect:/views/400.jsp");
        }else if(ex instanceof DataIntegrityViolationException) {
        	mv.setViewName("redirect:/views/400.jsp");
        }
        	
		return mv;
	}
	

}
