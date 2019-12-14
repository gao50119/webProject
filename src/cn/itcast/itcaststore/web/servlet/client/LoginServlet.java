package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				try {
					User user = service.login(username, password);
					
					// 登录成功，将用户存到session中
					request.getSession().setAttribute("user", user);
					// 获取用户角色
					String role = user.getRole();
					if ("超级用户".equals(role)) {
						response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
						return;
					} else {
						response.sendRedirect(request.getContextPath() + "/index.jsp");
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
