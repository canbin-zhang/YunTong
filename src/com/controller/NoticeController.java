package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.Employee;
import com.pojo.Notice;
import com.service.NoticeService;
import com.service.NoticeServiceImpl;

@Controller
@RequiresAuthentication
@RequestMapping("/Notice")

public class NoticeController {
	
	@Autowired
	private NoticeService noticeServiceImpl;
	
	@RequiresRoles(value={"业务员","admin"},logical = Logical.OR)
	@RequestMapping(value = "/notices",method = RequestMethod.GET)
	public ModelAndView noticeList(ModelAndView mv) {
		List<Notice> noticeList=noticeServiceImpl.queryAllNotice();
		mv.addObject("noticeList",noticeList);
		mv.setViewName("noticeList");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping(value = "/notice",method = RequestMethod.GET)
	public String toAddNotice(ModelAndView mv) {
		return "addNotice";
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping(value = "/notice",method = RequestMethod.POST)
	public String addNotice(@ModelAttribute("notice") Notice notice,RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		if(noticeServiceImpl.addNotice(notice,request)) {
			attributes.addFlashAttribute("successMessage","操作成功！！！");
			return "redirect:/Notice/notices";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次！！！");
			return "redirect:/Notice/notices";
		}
	}
	
	
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toUpdateNotice")
	public ModelAndView toUpdateNotice(@RequestParam("id")int id,ModelAndView mv) {
		Notice notice=noticeServiceImpl.queryNotice(id);
		mv.addObject("notice", notice);
		mv.setViewName("updateNotice");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping(value="/updateNotice")
	public String updateNotice(@ModelAttribute("notice") Notice notice,RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		if(noticeServiceImpl.updateNotice(notice,request)) {
			attributes.addFlashAttribute("successMessage","操作成功！！！");
			return "redirect:/Notice/notices";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Notice/notices";
		}
	}
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toDeleteNotice")
	public ModelAndView toDeleteNotice(@RequestParam("id")int id,ModelAndView mv) {
		Notice notice=noticeServiceImpl.queryNotice(id);
		mv.addObject("notice", notice);
		mv.setViewName("deleteNotice");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping(value="/deleteNotice/{id}")
	public String deleteNotice(@RequestParam("id")int id,RedirectAttributes attributes,HttpServletRequest request) {
		if(noticeServiceImpl.deleteNotice(id,request)) {
			attributes.addFlashAttribute("successMessage","操作成功！！！");
			
			return "redirect:/Notice/notices";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Notice/notices";
		}
	}
	
	@ResponseBody
	@RequestMapping("/verifyDetail")
	public String verifyDetail(String detail) {
		if(detail.length()<2) {  //判断是否为空
			return "公告明细不能为空！";
		}
		return "OK";
	}

}
