package cn.itcast.itcaststore.web.servlet.superadmin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.service.OrderService;

/**
 * Servlet implementation class SaleTrendServlet
 */
@WebServlet(urlPatterns="/SaleTrendServlet",name="SaleTrendServlet")
public class SaleTrendServlet extends HttpServlet {
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
		// 创建SimpleDateFormat类型对象、 "yyyy-MM-dd HH:ss:mm.SSS"是正则式，分别表示年月日时分秒毫秒
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
		Date endDate = new Date();
		String endtime = df.format(endDate);
		
		//实现日期加减
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.MONTH,-1);
		Date startDate=calendar.getTime();
		String starttime = df.format(startDate);
		
		//System.out.println("end:"+endtime+",start:"+starttime);

		
		OrderService service = new OrderService();
		List<Product> list = null;
		try {
			list = service.SaleTrend(startDate, endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("saletrend:"+list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/super/trend.jsp").forward(
				request, response);
		
	}

}
