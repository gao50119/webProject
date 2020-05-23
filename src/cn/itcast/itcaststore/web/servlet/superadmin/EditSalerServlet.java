package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class EditSalerServlet
 */
@WebServlet(name="editSaler",urlPatterns="/editSaler")
public class EditSalerServlet extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String gtype = request.getParameter("gtype");
		String email = request.getParameter("email");
		//System.out.println("id:"+id+"name:"+name+"pw:"+pw+"gtype:"+gtype+"email:"+email);
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(pw);
		user.setgType(gtype);
		user.setEmail(email);
		
		UserService service = new UserService();
		try {
			service.editSaler(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//操作日志
    	String userid = request.getParameter("user");
    	Logger logger = Logger.getLogger("adminlog");
    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
	    //System.out.println(date.format(new Date()));
	    //获取电脑上的ip
	  	String ip=InetAddress.getLocalHost().getHostAddress();
	  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
	  	logger.info("管理员["+userid+"] IP地址["+ip+"],编辑了销售员["+id+"]");
		
		
		response.sendRedirect(request.getContextPath() + "/findSaler");
	}

}
