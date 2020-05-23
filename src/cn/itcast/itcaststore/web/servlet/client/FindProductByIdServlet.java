package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.net.InetAddress;

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
		String id = request.getParameter("gNo");
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			Product p = service.findProductById(id);
			request.setAttribute("p", p);
			if (type == null) {
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				
				
				//日志
				switch(p.getgType()) {
				case "卡牌":
					Logger logger1 = Logger.getLogger("typeCard");
					try{
						logger1.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger1.info("错误信息:"+e.toString());
					}
					break;
				case "冒险":
					Logger logger2 = Logger.getLogger("typeAdven");
					try{
						logger2.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger2.info("错误信息:"+e.toString());
					}
					break;
				case "竞技":
					Logger logger3 = Logger.getLogger("typeComp");
					try{
						logger3.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger3.info("错误信息:"+e.toString());
					}
					break;
				case "塔防":
					Logger logger4 = Logger.getLogger("typeDefense");
					try{
						logger4.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger4.info("错误信息:"+e.toString());
					}
					break;
				case "模拟":
					Logger logger5 = Logger.getLogger("typeSimula");
					try{
						logger5.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger5.info("错误信息:"+e.toString());
					}
					break;
				case "休闲":
					Logger logger6 = Logger.getLogger("typeLeisure");
					try{
						logger6.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger6.info("错误信息:"+e.toString());
					}
					break;
				case "恐怖":
					Logger logger7 = Logger.getLogger("typeHorror");
					try{
						logger7.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger7.info("错误信息:"+e.toString());
					}
					break;
				case "RPG":
					Logger logger8 = Logger.getLogger("typeRpg");
					try{
						logger8.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger8.info("错误信息:"+e.toString());
					}
					break;
				case "策略":
					Logger logger9 = Logger.getLogger("typeStrategy");
					try{
						logger9.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger9.info("错误信息:"+e.toString());
					}
					break;
				case "动作":
					Logger logger10 = Logger.getLogger("typeMove");
					try{
						logger10.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger10.info("错误信息:"+e.toString());
					}
					break;
				case "射击":
					Logger logger11 = Logger.getLogger("typeShot");
					try{
						logger11.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger11.info("错误信息:"+e.toString());
					}
					break;
				case "音乐":
					Logger logger12 = Logger.getLogger("typeMusic");
					try{
						logger12.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger12.info("错误信息:"+e.toString());
					}
					break;
				case "体育":
					Logger logger13 = Logger.getLogger("typePe");
					try{
						logger13.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger13.info("错误信息:"+e.toString());
					}
					break;
				case "格斗":
					Logger logger14 = Logger.getLogger("typeFight");
					try{
						logger14.info("用户[" + user.getId() + "]正在查询商品[" + p.getgName() + "] 商品编号为[" + p.getgNo() + "]");
					}catch(Exception e){
						logger14.info("错误信息:"+e.toString());
					}
				}
				
				
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}
			
			
			request.getRequestDispatcher("/saler/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
