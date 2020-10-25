package com.service;

import com.pojo.Post;
import javafx.geometry.Pos;

import java.util.List;

import org.springframework.stereotype.Service;

public interface PostService {
    public List<Post> queryAllPost();
    public List<Post> queryAllPost(int pageNum,int pageSize);
    public Post queryPostByPid(String pid);
    public int addPost(Post post);
    public int updatePostSalary(String pid,String salary);
}
