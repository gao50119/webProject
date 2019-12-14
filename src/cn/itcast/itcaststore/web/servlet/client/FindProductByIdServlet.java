package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class findProductById
 */
@WebServlet(name = "findProductById", urlPatterns = "/findProductById")
public class FindProductByIdServlet extends HttpServlet {
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
		// �õ���Ʒ��id
		String id = request.getParameter("gNo");
		// ��ȡtype����ֵ���˴���type����������ͨ�û��ͳ����û�
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			// ����service�㷽����ͨ��id������Ʒ
			Product p = service.findProductById(id);
			request.setAttribute("p", p);
			// ��ͨ�û�Ĭ�ϲ�����typeֵ������ת��info.jspҳ��
			if (type == null) {
				
				//��־
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				
				Logger logger = Logger.getLogger("userlog");
				try{						
					logger.info("[" + user.getId() + "]正在查询商品");
							
				}catch(Exception e){
					logger.info("错误信息"+e.toString());
				}
				
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}						
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
