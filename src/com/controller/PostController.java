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
import com.pojo.Employee;
import com.pojo.Post;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.PostService;
import com.service.PostServiceImpl;

@Controller
@RequestMapping("/Post")
@RequiresAuthentication
public class PostController {
	
	@Autowired
	private PostService postServiceImpl;
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/postList")
	public ModelAndView postList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Post> postlist=postServiceImpl.queryAllPost(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(postlist); //分页
		Post post=postlist.get(0);
		int s=post.getEmployeelist().size();
		System.out.println(s);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("postList");
		return mv;
	}
	
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/postEmployeeList")
	public ModelAndView postEmployeeList(@RequestParam("pid")String pid,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Employee> employeelist=employeeServiceImpl.queryEmployeeByPid(pid,pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(employeelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("post",postServiceImpl.queryPostByPid(pid));
		mv.setViewName("postEmployeeList");
		return mv;
	}
	
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/toAddPost")
	public String toAddPost() {
		return "addPost";
	}
	
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/addPost")
	public String addPost(Post post,RedirectAttributes attributes) {
		if(postServiceImpl.addPost(post)>0) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
			return "redirect:/Post/postList?pageNum=1&pageSize=7";
		}
		attributes.addFlashAttribute("failedMessage", "操作失败，请重试！");
		return "redirect:/Post/postList?pageNum=1&pageSize=7";
	}
	

}
