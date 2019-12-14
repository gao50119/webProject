package cn.itcast.itcaststore.service;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.*;
import cn.itcast.itcaststore.utils.MailUtils;

public class UserService {
    private UserDao dao = new UserDao();
    
    public void register(User user) throws RegisterException{
    	try {
    		boolean result = dao.addUser(user);
    		System.out.print("add完成");
    		String emailMsg = "感谢您注册本网站";
    		MailUtils.sendMail(user.getEmail(),emailMsg);
    		
    		if(result == false)
    		{
    			System.out.print("执行成功");
    			throw new RegisterException("用户名已存在");
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RegisterException("注册失败");
    	}
    }
    
    //登录
    public User login(String username, String password) throws LoginException {
		try {
			//从数据库查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			if (user != null) {
				return user;
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
}
