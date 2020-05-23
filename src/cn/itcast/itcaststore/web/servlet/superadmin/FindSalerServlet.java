package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class FindSalerServlet
 */
@WebServlet(name="findSaler",urlPatterns="/findSaler")
public class FindSalerServlet extends HttpServlet {
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
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		String op = request.getParameter("op");
		//System.out.print("finaservlet id:"+id+",category:"+category);
		
		if(category!=null) {
			if(category.equals("全部类别")) {
				category = null;
			}
		}
		
		
		UserService service = new UserService();
		List<User> list = null;
		try {
			list = service.findSaler(id, category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(type!=null) {
			User user = list.get(0);
			request.setAttribute("u", user);
			request.getRequestDispatcher("/super/useredit.jsp").forward(request,response);
		}
		else {
			if(op!=null) {
				if(op.equals("find")) {
				    //操作日志
		    		String userid = request.getParameter("user");
		    		Logger logger = Logger.getLogger("adminlog");
		    		//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
			    	//System.out.println(date.format(new Date()));
			    	//获取电脑上的ip
			  		String ip=InetAddress.getLocalHost().getHostAddress();
			  		//System.out.println("电脑ip："+ip+"电脑名称："+name);
			  		logger.info("管理员["+userid+"] IP地址["+ip+"],进行销售员查询"
			  				+ " 查询条件为[id:"+id+",category:"+category+"]");
				}
			}
			
		    request.setAttribute("ul", list);	
		    request.getRequestDispatcher("/super/userlist.jsp").forward(request,response);
		}
	}

}
