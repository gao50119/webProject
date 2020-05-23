package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(name="deleteProduct",urlPatterns="/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
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
		String id = request.getParameter("gNo");
		String gtype = request.getParameter("gType");
		
		ProductService service = new ProductService();
		service.deleteProduct(id);
		
		//操作日志
    	String userid = request.getParameter("user");
    	Logger logger = Logger.getLogger("adminlog");
    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
	    //System.out.println(date.format(new Date()));
	    //获取电脑上的ip
	  	String ip=InetAddress.getLocalHost().getHostAddress();
	  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
	  	logger.info("销售员["+userid+"] IP地址["+ip+"] 下架商品["+id+"]");
		
	  	
		request.setAttribute("gType",gtype);
		request.getRequestDispatcher("/listProduct").forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/listProduct");
		return;
	}

}
