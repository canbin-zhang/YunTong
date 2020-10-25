package com.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDao;
import com.dao.StudentDao;
import com.pojo.Employee;
import com.pojo.Student;


@RestController
@RequestMapping("/Ajax")
public class AjaxController {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
    private RedisTemplate redisTemplate;  //注入redis对验证码进行验证
	
	@Autowired
    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean
	
	
	@PostMapping("/verifyEmailByRegister")
	public String verifyEmailByRegister(String email) {
		Employee employee=employeeDao.queryEmployeeByEmail(email);
		if(employee==null) {
			return "该邮箱尚未注册";
		}
		else return "OK";
	}
	
	@PostMapping("/valicode")
	public String valicode(String email,String valicode) {
		if(redisTemplate.hasKey(email)==false) {
			return "验证码已过期，请检查邮箱后再次发送";
		}
		if(valicode.equals((String) redisTemplate.opsForValue().get(email))) {
			return "OK";
		}
		else return "验证码错误";
	}
	
	@PostMapping("/verifyemployee")
	public String verifyemployee(@Valid @ModelAttribute("employee") Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return result.getFieldErrors().get(0).getDefaultMessage();
		}
		else return "OK";
	}
	
	@PostMapping("/verifystudent")
	public String verifystudent(@Valid @ModelAttribute("student")Student student,BindingResult result) {
		if(result.hasErrors()) {
			return result.getFieldErrors().get(0).getDefaultMessage();
		}
		else return "OK";
	}
	
	@PostMapping("/verifysphone")
	public String verifysphone(@Valid @ModelAttribute("student")Student student,BindingResult result) {
		if(result.hasErrors()) {
			return result.getFieldErrors().get(0).getDefaultMessage();
		}else if(studentDao.queryStudentBySphone(student.getSphone())!=0){
			return "该手机号已存在，请换一个";
		}
		return "OK";
	}
	
	@PostMapping("/verifypassword2")
	public String verifypassword2(String password,String password2) {
		if(password.equals(password2)) {
			return "OK";
		}
		else return "两个密码不一致";
	}
	
	@PostMapping("/verifyemail")
	public String verifyemail(String email) {
		if(employeeDao.queryEmployeeByEmail(email)!=null) {
			return "该邮箱已被注册，请换一个";
		}
		return "OK";
	}
	
	
	@PostMapping("/sendMail")
    public String sendMail(String email){
    	if(employeeDao.queryEmployeeByEmail(email)==null) {
    		return "该邮箱未注册";
    	}
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo(email);//收件人邮箱
            mMessageHelper.setSubject("验证");
            String validatecode=new Random().nextInt(9999)+"";  //随机生成验证码
            mMessageHelper.setText(validatecode);
            javaMailSender.send(mMessage);//发送邮件
            redisTemplate.opsForValue().set(email, validatecode,200,TimeUnit.SECONDS);//将验证码存在redis中
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "验证码已发送";
    }
	
}
