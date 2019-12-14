package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class AddCart
 */
@WebServlet(name="addCart",urlPatterns="/addCart")
public class AddCart extends HttpServlet {
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
		// 1.�õ���Ʒid
		String id = request.getParameter("gNo");
		// 2.����service�㷽��������id������Ʒ
		ProductService service = new ProductService();
		try {
			Product p = service.findProductById(id);
			//3.����Ʒ��ӵ����ﳵ
			//3.1���session����
			HttpSession session = request.getSession();
			//3.2��session�л�ȡ���ﳵ����
			Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
			//3.3������ﳵΪnull,˵��û����Ʒ�洢�ڹ��ﳵ�У����������ﳵ
			if (cart == null) {
				cart = new HashMap<Product, Integer>();
			}
			//3.4���ﳵ�������Ʒ
			Integer count = cart.put(p, 1); //put()�������ض�Ӧkey�ľ�ֵvalue
			//3.5�����Ʒ������Ϊ�գ�����Ʒ����+1����������µ���Ʒ��Ϣ
			if (count != null) {
				//��ʾkey��Ϊnull�����ԻḲ��ԭ�е�valueֵ
				cart.put(p, 1);
				//�����û���Ʒ�Ѿ�����
				request.setAttribute("tips", "商品已加入购物车");
				String type = request.getParameter("type");	
				request.setAttribute("type", type);
				session.setAttribute("cart", cart);
				request.getRequestDispatcher("/findProductById").forward(request, response);
				return;
			}			
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
