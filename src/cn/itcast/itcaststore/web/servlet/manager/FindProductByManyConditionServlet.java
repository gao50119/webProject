package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
		request.setCharacterEncoding("UTF-8");
		
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		//String name=URLDecoder.decode(request.getParameter("name"),"utf-8");
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		//String category=URLDecoder.decode(request.getParameter("category"),"utf-8");
		
		if(category!=null) {
			if(category.equals("全部类别")) {
			    //=nul时查询出全部类别
			    category = null;
			}
		}
		
		ProductService service = new ProductService();
		List<Product> ps = service.findProductByManyCondition(id, name,category);

		request.setAttribute("ps", ps);
		
		if(type!=null) {
			//操作日志
	    	String userid = request.getParameter("user");
	    	//userid不为空表示是从productlist.jsp发出的查询
	    	if(userid!=null) {
	    		Logger logger = Logger.getLogger("adminlog");
		    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
			    //System.out.println(date.format(new Date()));
			    //获取电脑上的ip
			  	String ip=InetAddress.getLocalHost().getHostAddress();
			  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
			  	logger.info("管理员["+userid+"] IP地址["+ip+"] 正在查询商品列表"
			  			+ " 查询条件为[id:"+id+",name:"+name+",category:"+category+"]");
	    	}
			
			request.getRequestDispatcher("/super/productlist.jsp").forward(request, response);
		}
		else{
			String userid = request.getParameter("user");
	    	//userid不为空表示是从productlist.jsp发出的查询
	    	if(userid!=null) {
	    		Logger logger = Logger.getLogger("adminlog");
		    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
			    //System.out.println(date.format(new Date()));
			    //获取电脑上的ip
			  	String ip=InetAddress.getLocalHost().getHostAddress();
			  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
			  	logger.info("销售员["+userid+"] IP地址["+ip+"] 正在查询商品列表"
			  			+ " 查询条件为[id:"+id+",name:"+name+",category:"+category+"]");
	    	}
			
			request.getRequestDispatcher("/saler/products/list.jsp").forward(request, response);
		}
	}

}
