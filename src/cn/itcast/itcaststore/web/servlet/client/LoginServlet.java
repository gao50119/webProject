package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;


@WebServlet(name="LoginServlet", urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				UserService service = new UserService();
				
				/*response.setContentType("text/html;charset=utf-8");
				ServletContext context = this.getServletContext();
				//PrintWriter out = response.getWriter();
				System.out.print("当前服务器的实际路径为："+context.getRealPath(""));*/
				try {
					User user = service.login(username, password);
					
					// 登录成功，将用户存到session中
					request.getSession().setAttribute("user", user);
					// 获取用户角色
					String role = user.getRole();
					
					
					//操作日志
			    	//String userid = request.getParameter("user");
			    	Logger logger = Logger.getLogger("adminlog");
				    //获取电脑上的ip
				  	String ip=InetAddress.getLocalHost().getHostAddress();
				  	logger.info(role+"["+user.getId()+"] IP地址["+ip+"] 登陆系统");
					
					
					//System.out.println(user.getgType());
					if ("销售".equals(role)) {
						response.sendRedirect(request.getContextPath() + "/saler/login/home.jsp");
						return;
					} else if("管理".equals(role)) {
						response.sendRedirect(request.getContextPath() + "/super/home.jsp");
						return;
					} else {
						response.sendRedirect(request.getContextPath() + "/client/menu_search.jsp");
						return;
					}
					
					
				} catch (LoginException e) {
                    //错误则将错误信息存储到request
					e.printStackTrace();
					request.setAttribute("register_message", e.getMessage());
					request.getRequestDispatcher("/client/login.jsp").forward(request, response);
					return;
				}
	}

}
