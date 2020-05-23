package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.itcaststore.domain.RegisterForm;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class CreateSalerServlet
 */
@WebServlet(name="CreateSaler",urlPatterns="/CreateSaler")
public class CreateSalerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-type", "text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	//response.getWriter().write("Hello MyServlet");
    	String gtype = request.getParameter("gtype");
    	
    	
    	RegisterForm form = new RegisterForm();
    	form.setName(request.getParameter("name"));
    	form.setPassword(request.getParameter("password"));
    	form.setPassword2(request.getParameter("password2"));
    	form.setEmail(request.getParameter("email"));
    	form.setId(request.getParameter("id"));
    	
    	if(!form.validate()) {
    		request.setAttribute("form", form);
    		request.getRequestDispatcher(".jsp").forward(request, response);
    	}
    	
    	
		User user = new User();
		try {
    		BeanUtils.populate(user, request.getParameterMap());
    		user.setgType(gtype);
    	}catch(IllegalAccessException e) {
    		e.printStackTrace();
    	}catch(InvocationTargetException e) {
    		e.printStackTrace();
    	}
		
		UserService service = new UserService();
		try {
    		service.addSaler(user);
    	}catch(RegisterException e) {
    		e.printStackTrace();
    		response.getWriter().write(e.getMessage());
    		return;
    	}
		
	}

}
