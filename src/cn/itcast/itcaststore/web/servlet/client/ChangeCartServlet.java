package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.itcaststore.domain.Product;

/**
 * Servlet implementation class ChangeCartServlet
 */
@WebServlet(name="/changeCart",urlPatterns="/changeCart")
public class ChangeCartServlet extends HttpServlet {
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
		// 1.得到商品id
		String id = request.getParameter("gNo");
		// 3.从session中获取购物车.
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Product p = new Product();
		p.setgNo(id);
		cart.remove(p);
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}

}
