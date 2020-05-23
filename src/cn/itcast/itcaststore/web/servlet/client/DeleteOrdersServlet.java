package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.service.OrderService;

/**
 * Servlet implementation class DeleteOrdersServlet
 */
@WebServlet(name="deleteOrders",urlPatterns="/deleteOrders")
public class DeleteOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		String type = request.getParameter("type");
		String oNo = request.getParameter("oNo");
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		
		OrderService service = new OrderService();
		if(type.equals("client")) {

			service.delOrderByIdWithClient(oNo);
			request.getRequestDispatcher("/findOrders?type=client&id=" + id).forward(request,response);
		}
		else if(type.equals("admin")) {
			service.delOrderById(oNo);
			
			//操作日志
	    	String userid = request.getParameter("user");
	    	Logger logger = Logger.getLogger("adminlog");
	    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
		    //System.out.println(date.format(new Date()));
		    //获取电脑上的ip
		  	String ip=InetAddress.getLocalHost().getHostAddress();
		  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
		  	logger.info("销售员["+userid+"] IP地址["+ip+"] 删除订单["+oNo+"]");
		  	
			request.getRequestDispatcher("/findOrders?type=admin&gType="+category).forward(request,response);
		}
	}

}
