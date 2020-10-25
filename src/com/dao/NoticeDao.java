package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Notice;



@Repository
@Mapper
public interface NoticeDao {
	
	public int addNotice(Notice notice);
	public int deleteNotice(int id);
	public int updateNotice(Notice notice);
	public Notice queryNotice(int id);
	public List<Notice> queryAllNotice();
	public int updateNoticePhoto(@Param("nphoto")String nphoto,@Param("id")int id);
	
}
