package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.pojo.Notice;



public interface NoticeService {

	public boolean addNotice(Notice notice,HttpServletRequest request);
	public boolean deleteNotice(int id,HttpServletRequest request);
	public boolean updateNotice(Notice notice,HttpServletRequest request);
	public Notice queryNotice(int id);
	public List<Notice> queryAllNotice();
	
}
