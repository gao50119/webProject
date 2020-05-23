package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
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

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
		double total = 0;
		for (Product p : cart.keySet()) {
			total += p.getgPrice();
		}

		if(user.getMoney() < total) {
			request.setAttribute("tips", "用户余额不足");
			request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
			return;
		}

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
			
			//日志
			//Log logger = new Log(p.getgType());
			//logger.buyLog(user.getId(),p.getgPrice());
			switch(p.getgType()) {
			case "卡牌":
				Logger logger1 = Logger.getLogger("typeCard");
				try{
					logger1.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger1.info("错误信息:"+e.toString());
				}
				break;
			case "冒险":
				Logger logger2 = Logger.getLogger("typeAdven");
				try{
					logger2.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger2.info("错误信息:"+e.toString());
				}
				break;
			case "竞技":
				Logger logger3 = Logger.getLogger("typeComp");
				try{
					logger3.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger3.info("错误信息:"+e.toString());
				}
				break;
			case "塔防":
				Logger logger4 = Logger.getLogger("typeDefense");
				try{
					logger4.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger4.info("错误信息:"+e.toString());
				}
				break;
			case "模拟":
				Logger logger5 = Logger.getLogger("typeSimula");
				try{
					logger5.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger5.info("错误信息:"+e.toString());
				}
				break;
			case "休闲":
				Logger logger6 = Logger.getLogger("typeLeisure");
				try{
					logger6.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger6.info("错误信息:"+e.toString());
				}
				break;
			case "恐怖":
				Logger logger7 = Logger.getLogger("typeHorror");
				try{
					logger7.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger7.info("错误信息:"+e.toString());
				}
				break;
			case "RPG":
				Logger logger8 = Logger.getLogger("typeRpg");
				try{
					logger8.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger8.info("错误信息:"+e.toString());
				}
				break;
			case "策略":
				Logger logger9 = Logger.getLogger("typeStrategy");
				try{
					logger9.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger9.info("错误信息:"+e.toString());
				}
				break;
			case "动作":
				Logger logger10 = Logger.getLogger("typeMove");
				try{
					logger10.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger10.info("错误信息:"+e.toString());
				}
				break;
			case "射击":
				Logger logger11 = Logger.getLogger("typeShot");
				try{
					logger11.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger11.info("错误信息:"+e.toString());
				}
				break;
			case "音乐":
				Logger logger12 = Logger.getLogger("typeMusic");
				try{
					logger12.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger12.info("错误信息:"+e.toString());
				}
				break;
			case "体育":
				Logger logger13 = Logger.getLogger("typePe");
				try{
					logger13.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger13.info("错误信息:"+e.toString());
				}
				break;
			case "格斗":
				Logger logger14 = Logger.getLogger("typeFight");
				try{
					logger14.info("用户[" + user.getId() + "]花费了[" + p.getgPrice() + "]元,购买了["+p.getgType()+"]类商品[" + p.getgNo() + "]");
				}catch(Exception e){
					logger14.info("错误信息:"+e.toString());
				}
			}
			
			int count = service.findAllOrderById(user.getId(), p.getgNo());
			System.out.println("pay count:"+count);
			//表示已购买了超过3次的同类商品
			if(count>3) {
			    //异常日志
			    Logger logger = Logger.getLogger("exception");
			    logger.info("用户["+user.getId()+"]已经购买了["+count+"]次商品["+p.getgNo()+"]");
			}
		}

		try {
		    String emailMsg = "您在商品总共消费：" + total + "元";
		    MailUtils.sendMail(user.getEmail(),emailMsg);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

		String username = user.getId();
		String password = user.getPassword();
		UserService service = new UserService();
		try {
		    User newuser = service.login(username, password);
		    request.getSession().setAttribute("user", newuser);
		} catch (LoginException e) {
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		cart.clear();
		request.getSession().setAttribute("cart", cart);		
		//request.getRequestDispatcher("/client/myAccount.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
	}

}
