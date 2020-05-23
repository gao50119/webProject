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

		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}

		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}

		String category = "全部商品";
		String _category = request.getParameter("gType");
		if (_category != null) {
			category = _category;
		}

		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount,
				category);
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		switch(category) {
		case "卡牌":
			Logger logger1 = Logger.getLogger("typeCard");
			try{
				logger1.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger1.info("错误信息:"+e.toString());
			}
			break;
		case "冒险":
			Logger logger2 = Logger.getLogger("typeAdven");
			try{
				logger2.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger2.info("错误信息:"+e.toString());
			}
			break;
		case "竞技":
			Logger logger3 = Logger.getLogger("typeComp");
			try{
				logger3.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger3.info("错误信息:"+e.toString());
			}
			break;
		case "塔防":
			Logger logger4 = Logger.getLogger("typeDefense");
			try{
				logger4.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger4.info("错误信息:"+e.toString());
			}
			break;
		case "模拟":
			Logger logger5 = Logger.getLogger("typeSimula");
			try{
				logger5.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger5.info("错误信息:"+e.toString());
			}
			break;
		case "休闲":
			Logger logger6 = Logger.getLogger("typeLeisure");
			try{
				logger6.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger6.info("错误信息:"+e.toString());
			}
			break;
		case "恐怖":
			Logger logger7 = Logger.getLogger("typeHorror");
			try{
				logger7.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger7.info("错误信息:"+e.toString());
			}
			break;
		case "RPG":
			Logger logger8 = Logger.getLogger("typeRpg");
			try{
				logger8.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger8.info("错误信息:"+e.toString());
			}
			break;
		case "策略":
			Logger logger9 = Logger.getLogger("typeStrategy");
			try{
				logger9.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger9.info("错误信息:"+e.toString());
			}
			break;
		case "动作":
			Logger logger10 = Logger.getLogger("typeMove");
			try{
				logger10.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger10.info("错误信息:"+e.toString());
			}
			break;
		case "射击":
			Logger logger11 = Logger.getLogger("typeShot");
			try{
				logger11.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger11.info("错误信息:"+e.toString());
			}
			break;
		case "音乐":
			Logger logger12 = Logger.getLogger("typeMusic");
			try{
				logger12.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger12.info("错误信息:"+e.toString());
			}
			break;
		case "体育":
			Logger logger13 = Logger.getLogger("typePe");
			try{
				logger13.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger13.info("错误信息:"+e.toString());
			}
			break;
		case "格斗":
			Logger logger14 = Logger.getLogger("typeFight");
			try{
				logger14.info("用户[" + user.getId() + "]正在浏览[" + category + "]类别商品");
			}catch(Exception e){
				logger14.info("错误信息:"+e.toString());
			}		
		}
		
		
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}

}
