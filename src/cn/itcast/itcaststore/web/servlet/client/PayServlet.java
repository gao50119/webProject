package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.OrderService;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.IdUtils;
import cn.itcast.itcaststore.utils.MailUtils;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet(name="payServlet",urlPatterns="/payServlet")
public class PayServlet extends HttpServlet {
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
		// 1.�õ���ǰ�û�
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 2.�ӹ��ﳵ�л�ȡ��Ʒ��Ϣ
		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
		double total = 0;
		for (Product p : cart.keySet()) {
			total += p.getgPrice();
		}
		//����
		if(user.getMoney() < total) {
			request.setAttribute("tips", "用户余额不足");
			request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
			return;
		}
		// 3.�����ݷ�װ������������
		for (Product p : cart.keySet()) {
			Order order = new Order();
			order.setUser(user);
			order.setProduct(p);
			order.setoMoney(p.getgPrice());
			order.setoNo(IdUtils.getUUID());
			
			OrderService service = new OrderService();
			try {
			    service.addOrder(order);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//�����ʼ�
		try {
		    String emailMsg = "您在商品总共消费：" + total + "元";
		    MailUtils.sendMail(user.getEmail(),emailMsg);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//��־
		Logger logger = Logger.getLogger("userlog");
		try{						
			logger.info("[" + user.getId() + "]购物花费了[" + total + "]");
			
		}catch(Exception e){
			logger.info("错误信息:"+e.toString());
		}
		
		//��ȡ�µ��û���Ϣ
		String username = user.getId();
		String password = user.getPassword();
		UserService service = new UserService();
		try {
		    User newuser = service.login(username, password);
		    // ��¼�ɹ������û��浽session��
		    request.getSession().setAttribute("user", newuser);
		} catch (LoginException e) {
            //�����򽫴�����Ϣ�洢��request
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//���session�е�cart��Ϣ
		cart.clear();
		request.getSession().setAttribute("cart", cart);		
		//request.getRequestDispatcher("/client/myAccount.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
	}

}
