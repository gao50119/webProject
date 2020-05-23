package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.Similar;
import cn.itcast.itcaststore.service.OrderService;
import cn.itcast.itcaststore.service.SimilarService;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet(urlPatterns="/recommendServlet",name="recommendServlet")
public class RecommendServlet extends HttpServlet {
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
		
		SimilarService service = new SimilarService();
		Similar similar = null;
		try {
			similar = service.findSimilarById(id);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("recommend:"+similar);
		if(similar!=null) {
			String sid = similar.getSimi();
			System.out.println("recommend sid:"+sid);
			OrderService service3 = new OrderService();
			
			//找到相似用户近期的三个订单 作为推荐
			List<Order> orders = service3.findOrderById(sid);
			System.out.println("recommend service3:"+orders);
			request.setAttribute("orders", orders);
		}
		
		request.getRequestDispatcher("/client/like.jsp").forward(request, response);
	}

}
