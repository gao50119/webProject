package cn.itcast.itcaststore.web.servlet.superadmin;

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
 * Servlet implementation class SuperFindOrderServlet
 */
@WebServlet(name="superFindOrder",urlPatterns="/superFindOrder")
public class SuperFindOrderServlet extends HttpServlet {
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
		String orderid = request.getParameter("orderid");
		String salerid = request.getParameter("salerid");
		String category = request.getParameter("category");
		String op = request.getParameter("op");
		//System.out.println("superServlet:category"+ category + "orderid:"+orderid+"salerid:"+salerid);
		
		if(category!=null) {
		    if(category.equals("全部类别")) {
		    	category = null;
		    }
		}
       		
		OrderService service = new OrderService();			
		List<Order> orders = service.superFindOrder(orderid, salerid, category);
		
		
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
			  	logger.info("管理员["+userid+"] IP地址["+ip+"] 正在查询订单列表"
			  			+ " 查询条件为[orderid:"+orderid+",salerid"+salerid+",category:"+category+"]");
			}
		}
		
	        
		request.setAttribute("orders", orders);			
		request.getRequestDispatcher("/super/orderlist.jsp").forward(request,response);
	}

}
