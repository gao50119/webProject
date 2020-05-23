package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.service.OrderService;

/**
 * Servlet implementation class FindOrderByManyConditionServlet
 */
@WebServlet(name="findOrderByManyCondition",urlPatterns="/findOrderByManyCondition")
public class FindOrderByManyConditionServlet extends HttpServlet {
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String category = request.getParameter("gType");
       
		if(type.equals("admin")) {		
			OrderService service = new OrderService();			
			List<Order> orders = service.findOrderByManyCondition(id, name, 1, category);
			
			//操作日志
	    	String userid = request.getParameter("user");
	    	Logger logger = Logger.getLogger("adminlog");
	    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
		    //System.out.println(date.format(new Date()));
		    //获取电脑上的ip
		  	String ip=InetAddress.getLocalHost().getHostAddress();
		  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
		  	logger.info("销售员["+userid+"] IP地址["+ip+"] 进行订单查询 查询条件为[id:"+id+",name:"+name+",category:"+category+"]");
	        
			request.setAttribute("orders", orders);			
		    request.getRequestDispatcher("/saler/orders/list.jsp").forward(request,
				    response);
		}else {
			OrderService service = new OrderService();
			List<Order> orders = service.findOrderByManyCondition(id, name, 2, null);
			
			
			
			request.setAttribute("orders", orders);		
			request.getRequestDispatcher("/client/list.jsp").forward(request,
				    response);
		}
	}

}
