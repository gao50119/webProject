package cn.itcast.itcaststore.web.servlet.manager;

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
        //获取订单编号和收件人名称
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
       
		if(type.equals("admin")) {
			 //创建Service层对象
			OrderService service = new OrderService();
			//调用Service层OrderService类的findOrderByManyCondition()方法查询数据
			List<Order> orders = service.findOrderByManyCondition(id, name, 1);
	        //将查询结果添加到request作用域中
			request.setAttribute("orders", orders);
			
            //请求转发到list.jsp页面，并将request请求和response响应也转发到该页面中
		    request.getRequestDispatcher("/admin/orders/list.jsp").forward(request,
				    response);
		}else {
			//用户
			//创建Service层对象
			OrderService service = new OrderService();
			//调用Service层OrderService类的findOrderByManyCondition()方法查询数据
			List<Order> orders = service.findOrderByManyCondition(id, name, 2);
	        //将查询结果添加到request作用域中
			request.setAttribute("orders", orders);
			
			request.getRequestDispatcher("/client/list.jsp").forward(request,
				    response);
		}
	}

}
