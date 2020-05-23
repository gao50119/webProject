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
		OrderService service = new OrderService();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String category = request.getParameter("gType");

		if(type.equals("admin")) {
		    List<Order> orders = service.findOrderByManyCondition(null, null, 1, category);
		    request.setAttribute("orders", orders);
		    request.getRequestDispatcher("/saler/orders/list.jsp").forward(request,response);
		}
		else if(type.equals("client")) {
			List<Order> orders = service.findOrderByManyCondition(null, id, 2, null);
			request.setAttribute("orders", orders);
		    request.getRequestDispatcher("/client/list.jsp").forward(request,response);
		}
	}

}
