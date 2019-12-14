package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class showProductByPage
 */
@WebServlet(name = "showProductByPage", urlPatterns = "/showProductByPage")
public class ShowProductByPageServlet extends HttpServlet {
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
		// 1.���嵱ǰҳ�룬Ĭ��Ϊ1
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.����ÿҳ��ʾ����,Ĭ��Ϊ4
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		// 3.��ȡ���ҵķ���
		String category = "全部商品";
		String _category = request.getParameter("gType");
		if (_category != null) {
			category = _category;
		}
		// 4.����service����ɻ�ȡ��ǰҳ��ҳBean����.
		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount,
				category);
		
		
		//��־
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Logger logger = Logger.getLogger("userlog");
		try{						
			logger.info("[" + user.getId() + "]正在查询商品");
					
		}catch(Exception e){
			logger.info("错误信息:"+e.toString());
		}
		
		
		// �����ݴ洢��request��Χ����ת��product_list.jspҳ��չʾ
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}

}
