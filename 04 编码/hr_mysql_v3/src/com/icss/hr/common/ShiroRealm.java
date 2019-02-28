package com.icss.hr.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.perm.service.PermService;

/**
 * 自定义realm
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private PermService permService;

	/**
	 * 获得授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		System.out.println("进行授权...doGetAuthorizationInfo");

		// 获得当前用户名
		String userName = (String) getAvailablePrincipal(principals);

		// 通过用户名去获得用户的所有资源，并把资源存入info中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	
		// 设置权限
		Set<String> s = new HashSet<String>();
		List<HashMap<String,Object>> list = permService.queryPerm(userName);
		for (Map map : list) {
			System.out.println(map.get("perm_name"));
			s.add((String)map.get("perm_name"));
		}
		info.setStringPermissions(s);

		// 设置角色
		Set<String> r = new HashSet<String>();
		list = permService.queryRole(userName);
		for (Map map : list) {
			System.out.println(map.get("role_name"));
			r.add((String)map.get("role_name"));
		}
		info.setRoles(r);
		
		// 返回此用户的授权信息
		return info;
	}

	/**
	 * 获得认证信息
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("进行登陆验证...doGetAuthenticationInfo");

		// token中储存着输入的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		
		//调用登录业务，返回登录结果
		int status = empService.checkLogin(username, password);
		
		if (status == 1) {
			//账号不存在
			throw new UnknownAccountException();
		} else if (status == 2) {
			//密码错误
			throw new IncorrectCredentialsException();
		} else {			
			// 登录成功则返回info
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
					password.toCharArray(), getName());
			
			return info;
		}		
	}

	/**
	 * 删除当前用户的缓存
	 * 新版本的shiro可以在用户重新登录的时候自动调用授权，更新缓存
	 * 
	 * @param userId
	 */
	public void removeUserCache(String userId) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userId, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

}