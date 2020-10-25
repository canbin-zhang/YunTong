package com.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.pojo.Student;
import com.pojo.License;
import com.service.StudentServiceImpl;
import com.service.LicenseService;
import com.service.LicenseServiceImpl;
import com.service.StudentService;

@Controller
@RequiresAuthentication
@RequestMapping("/License")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseServiceImpl;
	
	@Autowired
	private StudentService studentServiceImpl;
	
	
	@RequiresRoles(value={"业务员","admin"},logical = Logical.OR)
	@RequestMapping("/licenseList")
	public ModelAndView licenseList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<License> licenselist=licenseServiceImpl.queryAllLicense(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(licenselist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("licenseList");
		return mv;
	}
	
	@RequiresRoles(value={"业务员","admin"},logical = Logical.OR)
	@RequestMapping("/licenseStudentList")
	public ModelAndView licenseStudentList(@RequestParam("lid")String lid,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Student> studentlist=studentServiceImpl.queryStudentByLid(lid,pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(studentlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("license",licenseServiceImpl.queryLicenseByLid(lid));
		mv.setViewName("licenseStudentList");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toAddLicense")
	public String toAddLicense() {
		return "addLicense";
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/addLicense")
	public String addLicense(License license,RedirectAttributes attributes) {
		if(licenseServiceImpl.addLicense(license)>0) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
			return "redirect:/License/licenseList?pageNum=1&pageSize=8";
		}
		attributes.addFlashAttribute("failedMessage", "操作失败，请重试！");
		return "redirect:/License/licenseList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toUpdateLicense")
	public ModelAndView toUpdateLicense(@RequestParam("lid")String lid,ModelAndView mv) {
		License license=licenseServiceImpl.queryLicenseByLid(lid);
		mv.addObject("license", license);
		mv.setViewName("updateLicense");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/updateLicense")
	public String updateLicense(License license,RedirectAttributes attributes) {
		if(licenseServiceImpl.updateLicense(license)>0) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
			return "redirect:/License/licenseList?pageNum=1&pageSize=8";
		}
		attributes.addFlashAttribute("failedMessage", "操作失败，请重试！");
		return "redirect:/License/licenseList?pageNum=1&pageSize=8";
	}
	

}
