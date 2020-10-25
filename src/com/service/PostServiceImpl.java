package com.service;

import com.dao.PostDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;


    @Override
    public List<Post> queryAllPost() {
        return postDao.queryAllPost();
    }


	@Override
	public List<Post> queryAllPost(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return postDao.queryAllPost();
	}


	@Override
	public Post queryPostByPid(String pid) {
		// TODO Auto-generated method stub
		return postDao.queryPostByPid(pid);
	}


	@Override
	public int addPost(Post post) {
		// TODO Auto-generated method stub
		return postDao.addPost(post);
	}

	@Override
	public int updatePostSalary(String pid, String salary) {
		// TODO Auto-generated method stub
		return postDao.updatePostSalary(pid, salary);
	}
}
