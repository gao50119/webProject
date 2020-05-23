package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet(name="download",urlPatterns="/download")
public class DownloadServlet extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String category = request.getParameter("category");
		
		ProductService service = new ProductService();
		//System.out.println("DownloadServlet:year"+ year + "month:"+month+"category:"+category);
		List<Object[]> ps = service.download(year,month,category);
		String fileName=year+"年"+month+"月"+category+"类商品销售榜单.csv";	

		response.setContentType(this.getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachement;filename="+new String(fileName.getBytes("GBK"),"iso8859-1"));		
		response.setCharacterEncoding("gbk");

		PrintWriter out = response.getWriter();
		out.println("商品编号,商品名称,销售数量");
		for (int i = 0; i < ps.size(); i++) {
			Object[] arr=ps.get(i);
			out.println(arr[2]+","+arr[0]+","+arr[1]);			
		}
		out.flush();
		out.close();
		
		//操作日志
    	String userid = request.getParameter("user");
    	Logger logger = Logger.getLogger("adminlog");
    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
	    //System.out.println(date.format(new Date()));
	    //获取电脑上的ip
	  	String ip=InetAddress.getLocalHost().getHostAddress();
	  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
	  	logger.info("管理员["+userid+"] IP地址["+ip+"],下载了["+fileName+"]");
	}

}
