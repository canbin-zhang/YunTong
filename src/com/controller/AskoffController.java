package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.pojo.Askoff;
import com.pojo.Car_refuel;
import com.pojo.Employee;
import com.service.AskoffService;


@Controller
@RequestMapping("/Askoff")
@RequiresAuthentication

public class AskoffController {
	
	@Autowired
	private AskoffService askoffService;
	
	@RequestMapping("/askoffListByEid")
	public ModelAndView askoffListByEid(@RequestParam("eid")String eid,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Askoff> askofflist=askoffService.askoffListByEid(eid, pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(askofflist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("askoffListByEid");
		return mv;
	}

	@RequestMapping("/toAddAskoff")
	public String toAddAskoff() {
		return "addAskoff";
	}
	
	@ResponseBody
	@RequestMapping("/verifyStart_date")
	public String verifyStart_date(String start_date) {
		if(start_date.length()<4) {  //判断是否为空
			return "请先输入开始日期！";
		}
		return "OK";
	}
	
	@ResponseBody
	@RequestMapping("/verifyEnd_date")
	public String verifyEnd_date(String start_date,String end_date) throws ParseException {
		if(end_date.length()<4) {  //判断是否为空
			return "请先输入结束日期！";
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=df.parse(start_date);
			Date endDate=df.parse(end_date);
			if(startDate.compareTo(endDate)==1) {
				return "请输入正确的结束日期";
			}else {
				return "OK";
			}
		}
	}
	
	@ResponseBody
	@RequestMapping("/verifyReason")
	public String verifyReason(String reason) {
		if(reason.length()<2) {  //判断是否为空
			return "请假原因不能为空！";
		}
		return "OK";
	}
	
	@RequestMapping("/addAskoff")
	public String addAskoff(@ModelAttribute("askoff")Askoff askoff,RedirectAttributes attributes,ModelAndView mv) {
		if(askoffService.addAskoff(askoff)==0) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Askoff/toAddAskoff";
		}else {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Askoff/askoffListByEid?eid="+askoff.getEid()+"&pageNum=1&pageSize=8";
		}
		
	}
	
	@RequestMapping("/askoffDetail")
	public ModelAndView askoffDetail(@RequestParam("id")int id,ModelAndView mv) {
		Askoff askoff=askoffService.queryaskoffById(id);
		//判断是否有操作员审批请假申请
		if(askoff.getOperator_id()!=null) {
			String operator_name=askoffService.operatorNameByEid(askoff.getOperator_id());
			mv.addObject("operator_name", operator_name);
		}
		mv.addObject("askoff", askoff);
		mv.setViewName("askoffDetail");
		return mv;
	}
	
	@RequiresRoles(value={"人事部","admin"},logical= Logical.OR)
	@RequestMapping("/askoffList")
	public ModelAndView askoffList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Askoff> askofflist=askoffService.askoffList(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(askofflist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("askoffList");
		return mv;
	}
	
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/askoffPass")
	public String askoffPass(@RequestParam("id")String id,@RequestParam("operator_id")String operator_id,RedirectAttributes attributes,ModelAndView mv) {
		//将前端传过来的id强转为int类型
		int askoffid =Integer.parseInt(id);
		if(askoffService.askoffPass(askoffid, operator_id)==0) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Askoff/askoffList?pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Askoff/askoffList?pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/toAskoffStatus")
	public ModelAndView toAskoffStatus(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Askoff> askofflist=askoffService.toAskoffStatus(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(askofflist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("toAskoffStatus");
		return mv;
	}
	
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/toAtPostStatus")
	public ModelAndView toAtPostStatus(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Askoff> askofflist=askoffService.toAtPostStatus(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(askofflist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("toAtPostStatus");
		return mv;
	}
	
	@RequestMapping("/deleteAskoff")
	public String deleteAskoff(@RequestParam("id")int id,@RequestParam("eid")String eid,RedirectAttributes attributes) {
		if(askoffService.deleteAskoff(id)==1) {
			attributes.addFlashAttribute("successMessage","操作成功！！！");
			return "redirect:/Askoff/askoffListByEid?eid="+eid+"&pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新操作一次！！！");
			return "redirect:/Askoff/askoffListByEid?eid="+eid+"pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"人事部","admin"},logical= Logical.OR)
	@RequestMapping("/askoffSearch")
	public ModelAndView askoffSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Askoff> askofflist=askoffService.searchAskoff(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(askofflist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("askoffSearch");
		return mv;
	}
	
}
