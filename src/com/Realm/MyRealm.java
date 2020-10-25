package com.Realm;


import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import com.pojo.Post;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.context.ContextLoader;

import com.dao.EmployeeDao;
import com.pojo.Employee;


public class MyRealm extends AuthorizingRealm{
	
	//在shiro配置文件注入该dao，调用验证身份和权限
	private EmployeeDao employeedao;
	

	public EmployeeDao getEmployeedao() {
		return employeedao;
	}

	public void setEmployeedao(EmployeeDao employeedao) {
		this.employeedao = employeedao;
	}

	/**
	 * 查看权限信息（查看用户角色时用）
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String eid=(String) principal.getPrimaryPrincipal();
		 //新建SimpleAuthorizationInfo对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        EmployeeDao employeedao=(EmployeeDao)ContextLoader.getCurrentWebApplicationContext().getBean("employeeDao");
        Set<String> roles=new HashSet();
        if("admin".equals(eid)) {
        	roles.add(eid);
        }else {
	        Post post=employeedao.queryPostByEid(eid);
	        roles.add(post.getPname());
        }
        info.setRoles(roles);
        return info;
	}

	/**
	 * 查看身份信息（登录时用）
	 * 何时触发：subject.login(token)
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//subject.login(token)时触发，从token拿到用户名
		String eid=(String) token.getPrincipal();
		String epassword = new String((char[])token.getCredentials());
		//判断admin账号
		if("admin".equals(eid)&&"admin".equals(epassword)) {
			char[] password = epassword.toCharArray();
	        return new SimpleAuthenticationInfo(eid,password,getName());
		}else {
			//从spring容器中拿到EmployeeDao
			EmployeeDao employeedao =(EmployeeDao)ContextLoader.getCurrentWebApplicationContext().getBean("employeeDao");
			Employee employee=employeedao.queryEmployeeByEid(eid);
			String status=employeedao.queryEmployeeStatus(eid);
			if(employee == null || "离职".equals(status)){
	            return null;
	        }
	        // 将 当前⽤户的认证信息存⼊ SimpleAuthenticationInfo 并返回
	        // 注意此⽅法的本职⼯作就是查询⽤户的信息，所以查到后不⽤⽐对密码是否正确，那是shiro后续流程的职责。
	        // 如果密码错误，shiro的后续流程中会抛出异常IncorrectCredentialsException
			//因为在SimpleAuthenticationInfo内部将password转为数组，因为将user的password也转为数组才能进行比较
			char[] password = employee.getEpassword().toCharArray();
	        return new SimpleAuthenticationInfo(employee.getEid(),password,getName());
		}
	}

	

	
	
	
	
	

}
