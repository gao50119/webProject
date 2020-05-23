package cn.itcast.itcaststore.web.servlet.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.AddProductException;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.utils.FileUploadUtils;
import cn.itcast.itcaststore.utils.IdUtils;

/**
 * Servlet implementation class addProductServlet
 */
@WebServlet(name="addProduct",urlPatterns="/addProduct")
public class AddProductServlet extends HttpServlet {
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

		Product p = new Product();
		Map<String, String> map = new HashMap<String, String>();
		map.put("gNo", IdUtils.getUUID());

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));
		dfif.setSizeThreshold(1024 * 1024 * 10);
		ServletFileUpload upload = new ServletFileUpload(dfif);

		upload.setHeaderEncoding("utf-8");
		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String value = item.getString("utf-8");
					//System.out.println(fieldName + " " + value);
					map.put(fieldName, value);
				} else {
					String fileName = item.getName();
					fileName = FileUploadUtils.subFileName(fileName);

					String randomName = FileUploadUtils
							.generateRandonFileName(fileName);

					String randomDir = FileUploadUtils
							.generateRandomDir(randomName);
					
					String imgurl_parent = "/productImg" + randomDir;

					File parentDir = new File(this.getServletContext()
							.getRealPath(imgurl_parent));
					//System.out.println("parentDir:"+parentDir);
					
					if (!parentDir.exists()) {
						parentDir.mkdirs();
						//System.out.println("excute mkdirs");
					}
					String imgurl = imgurl_parent + "/" + randomName;

					map.put("gImgurl", imgurl);

					IOUtils.copy(item.getInputStream(), new FileOutputStream(
							new File(parentDir, randomName)));
					
					/*InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(new File(parentDir, randomName));
					byte[] buffer = new byte[1024];
					
					int len;
					while ((len = in.read(buffer)) > 0) {
						System.out.println("output");
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
					item.delete();*/
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		try {
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ProductService service = new ProductService();
		try {
			service.addProduct(p);
			
			//操作日志
	    	String userid = request.getParameter("user");
	    	Logger logger = Logger.getLogger("adminlog");
	    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
		    //System.out.println(date.format(new Date()));
		    //获取电脑上的ip
		  	String ip=InetAddress.getLocalHost().getHostAddress();
		  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
		  	logger.info("销售员["+userid+"] IP地址["+ip+"] 添加商品["+p.getgNo()+"]");
			
			
			//request.setAttribute("gType", map.get("gType"));
			//System.out.println("addp servlet"+map.get("gType"));
			String gType = map.get("gType");
			request.getRequestDispatcher("/listProduct?gType="+gType).forward(request, response);
			//response.sendRedirect(request.getContextPath()+ "/listProduct?gType="+map.get("gType"));
			return;
		} catch (AddProductException e) {
			e.printStackTrace();
			response.getWriter().write("添加商品失败");
			return;
		}		
	}

}
