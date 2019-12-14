package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class FindProductByManyConditionServlet
 */
@WebServlet(name="findProductByManyCondition",urlPatterns="/findProductByManyCondition")
public class FindProductByManyConditionServlet extends HttpServlet {
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
		//1.��ȡ������
		request.setCharacterEncoding("UTF-8");
		
		
		String id = request.getParameter("id"); // ��Ʒid
		//String name = request.getParameter("name"); // ��Ʒ����
		String name=URLDecoder.decode(request.getParameter("name"),"utf-8");
		//String category = request.getParameter("category"); // ��Ʒ���
		String category=URLDecoder.decode(request.getParameter("category"),"utf-8");
		// 2.����ProductService����
		System.out.println(name + " " + category);
		
		ProductService service = new ProductService();
		// 3.����service������������ѯ�ķ���
		List<Product> ps = service.findProductByManyCondition(id, name,
				category);
		// 4.��������ѯ�Ľ���Ž�request����
		request.setAttribute("ps", ps);
		// 5.�����ض�����Ʒ������ҳlist.jspҳ��
		request.getRequestDispatcher("/admin/products/list.jsp").forward(
				request, response);
	}

}
