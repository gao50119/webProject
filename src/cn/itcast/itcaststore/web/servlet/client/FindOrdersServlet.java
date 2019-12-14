package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.service.OrderService;

/**
 * Servlet implementation class FindOrdersServlet
 */
@WebServlet(name="findOrders",urlPatterns="/findOrders")
public class FindOrdersServlet extends HttpServlet {
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
		// 创建Service层对象
		OrderService service = new OrderService();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		//管理员
		if(type.equals("admin")) {
		    // 调用Service层对象的findAllOrder()方法查询订单列表
		    List<Order> orders = service.findOrderByManyCondition(null, null, 1);
		    //将查询到的订单信息添加到request作用域
		    request.setAttribute("orders", orders);
		    // 将请求转发到list.jsp页面
		    request.getRequestDispatcher("/admin/orders/list.jsp").forward(request,response);
		}
		else if(type.equals("client")) {
			List<Order> orders = service.findOrderByManyCondition(null, id, 2);
			request.setAttribute("orders", orders);
		    // 将请求转发到list.jsp页面
		    request.getRequestDispatcher("/client/list.jsp").forward(request,response);
		}
	}

}
