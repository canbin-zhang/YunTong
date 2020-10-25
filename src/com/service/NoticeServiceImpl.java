package com.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.NoticeDao;
import com.pojo.Employeelist;
import com.pojo.Notice;
import com.utils.MyUtils;


@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;

	@Transactional
	@Override
	public boolean addNotice(Notice notice,HttpServletRequest request) {
		if(notice.getDate()==null||"".equals(notice.getDetail())||notice.getNphotofile().getSize()==0) {
			return false;
		}
		String newFileName = "";
		String fileName = notice.getNphotofile().getOriginalFilename();
		
		if(noticeDao.addNotice(notice)>0) {
			//选择文件
			if(fileName.length() > 0){
				String realpath = request.getServletContext().getRealPath("photos/notice");
				//实现文件上传
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				
				newFileName = notice.getId() + fileType;
				notice.setNphoto(newFileName);
				File targetFile = new File(realpath, newFileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
				//上传
		        try {   
		        	notice.getNphotofile().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			}
			if(noticeDao.updateNoticePhoto(newFileName, notice.getId())>0) {
				return true;
			}
		}return false;
		
	}

	@Transactional
	@Override
	public boolean deleteNotice(int id,HttpServletRequest request) {
		Notice notice=noticeDao.queryNotice(id);
		String fileName=notice.getNphoto();
		String realpath=request.getServletContext().getRealPath("photos/notice");
		File targetFile = new File(realpath, fileName); 
		if(targetFile.exists()){  
            if(targetFile.delete()&&noticeDao.deleteNotice(id)==1) {
            	return true;
            }
        } return false;
		
	}

	@Transactional
	@Override
	public boolean updateNotice(Notice notice,HttpServletRequest request) {
		if(notice.getNphotofile()!=null) {
			String newFileName = "";
			String fileName = notice.getNphotofile().getOriginalFilename(); 
			
			//选择文件
			if(fileName.length() > 0){
				String realpath = request.getServletContext().getRealPath("photos/notice");
				//实现文件上传
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				//用身份证号作为照片文件名
				newFileName = notice.getId() + fileType;
				notice.setNphoto(newFileName);
				File targetFile = new File(realpath, newFileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
				//上传覆盖原来的图片
		        try {   
		        	notice.getNphotofile().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			}
		}
		if(noticeDao.updateNotice(notice)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Notice queryNotice(int id) {
		return noticeDao.queryNotice(id);
	}

	@Override
	public List<Notice> queryAllNotice() {
		// TODO Auto-generated method stub
		return noticeDao.queryAllNotice();
	}

	
}
