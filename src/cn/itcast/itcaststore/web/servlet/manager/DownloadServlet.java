package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		ProductService service = new ProductService();
		List<Object[]> ps = service.download(year,month);
		//ƴ���ļ���
		String fileName=year+"年"+month+"月销售榜单.csv";	
		//�ͻ����ܷ��岻ͬ���������
		response.setContentType(this.getServletContext().getMimeType(fileName));
		//�����ļ���
		response.setHeader("Content-Disposition", "attachement;filename="+new String(fileName.getBytes("GBK"),"iso8859-1"));		
		response.setCharacterEncoding("gbk");
		//���ļ���д������
		PrintWriter out = response.getWriter();
		out.println("商品名称,销售数量");
		for (int i = 0; i < ps.size(); i++) {
			Object[] arr=ps.get(i);
			out.println(arr[0]+","+arr[1]);			
		}
		out.flush();
		out.close();
	}

}
