package cn.itcast.itcaststore.web.servlet.client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import cn.itcast.itcaststore.domain.*;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.utils.*;
import cn.itcast.itcaststore.service.UserService;

@WebServlet(name="RegisterServlet",urlPatterns="/RegisterServlet")
public class RegisterServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	doPost(request,response);
    }
    
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	response.setHeader("Content-type", "text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	//response.getWriter().write("Hello MyServlet");
    	
    	RegisterForm form = new RegisterForm();
    	form.setName(request.getParameter("name"));
    	form.setPassword(request.getParameter("password"));
    	form.setPassword2(request.getParameter("password2"));
    	form.setEmail(request.getParameter("email"));
    	form.setId(request.getParameter("id"));
    	
    	if(!form.validate()) {
    		request.setAttribute("form", form);
    		request.getRequestDispatcher("/client/register.jsp").forward(request, response);
    		return;
    	}
    	
    	
    	User user = new User();
    	try {
    		BeanUtils.populate(user, request.getParameterMap());
    	}catch(IllegalAccessException e) {
    		e.printStackTrace();
    	}catch(InvocationTargetException e) {
    		e.printStackTrace();
    	}
    	
    	
    	UserService service = new UserService();
    	try {
    		service.register(user);
    	}catch(RegisterException e) {
    		e.printStackTrace();
    		response.getWriter().write(e.getMessage());
    		return;
    	}

    	response.sendRedirect(request.getContextPath() + "/client/login.jsp");
    	
    	
    }
}
