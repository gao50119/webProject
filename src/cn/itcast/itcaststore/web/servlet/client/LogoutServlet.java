package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name="logoutServlet",urlPatterns="/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String role = user.getRole();
		
		
		//操作日志
    	//String userid = request.getParameter("user");
    	Logger logger = Logger.getLogger("adminlog");
	    //获取电脑上的ip
	  	String ip=InetAddress.getLocalHost().getHostAddress();
	  	logger.info(role+"["+user.getId()+"] IP地址["+ip+"] 登出系统");
		
		session.invalidate();
		String flag = request.getParameter("flag");
		if (flag == null || flag.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/client/login.jsp");
	    }
	}

}
