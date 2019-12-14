package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		OrderService service = new OrderService();
		if(type.equals("client")) {
			//用户发起
			service.delOrderByIdWithClient(oNo);
			request.getRequestDispatcher("/findOrders?type=client&id=" + id).forward(request,response);
		}
		else if(type.equals("admin")) {
			//管理员发起
			service.delOrderById(oNo);
			request.getRequestDispatcher("/findOrders?type=admin").forward(request,response);
		}
	}

}
