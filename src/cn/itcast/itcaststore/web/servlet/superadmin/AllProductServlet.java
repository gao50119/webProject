package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.ListProductException;
import cn.itcast.itcaststore.service.ProductService;


@WebServlet(name="allProduct",urlPatterns="/allProduct")
public class AllProductServlet extends HttpServlet {
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
		ProductService service = new ProductService();
		try {
			//String gtype=URLDecoder.decode(request.getParameter("gType"),"utf-8");
			List<Product> ps = service.listAll();
			
			
			//操作日志
	    	String userid = request.getParameter("user");
	    	Logger logger = Logger.getLogger("adminlog");
	    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
		    //System.out.println(date.format(new Date()));
		    //获取电脑上的ip
		  	String ip=InetAddress.getLocalHost().getHostAddress();
		  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
		  	logger.info("管理员["+userid+"] IP地址["+ip+"],查询了商品列表");
			
			
			request.setAttribute("ps", ps);
			request.getRequestDispatcher("/super/productlist.jsp").forward(
					request, response);
			return;
		} catch (ListProductException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}		
	}

}
