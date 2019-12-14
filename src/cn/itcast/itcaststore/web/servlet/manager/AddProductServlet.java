package cn.itcast.itcaststore.web.servlet.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
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
		// ����javaBean,���ϴ����ݷ�װ.
		Product p = new Product();
		Map<String, String> map = new HashMap<String, String>();
		// ��װ��Ʒid
		map.put("gNo", IdUtils.getUUID());

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// ������ʱ�ļ��洢λ��
		dfif.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));
		// �����ϴ��ļ������С
		dfif.setSizeThreshold(1024 * 1024 * 10);
		// �����ϴ����
		ServletFileUpload upload = new ServletFileUpload(dfif);

		upload.setHeaderEncoding("utf-8");
		try {
			// ����request�õ����е�FileItem
			List<FileItem> items = upload.parseRequest(request);
			// ��������FileItem
			for (FileItem item : items) {
				// �жϵ�ǰ�Ƿ����ϴ����
				if (item.isFormField()) {
					// �����ϴ����
					String fieldName = item.getFieldName(); // ��ȡ�������
					String value = item.getString("utf-8"); // �����������
					map.put(fieldName, value);
				} else {
					// ���ϴ����
					// �õ��ϴ��ļ���ʵ����
					String fileName = item.getName();
					fileName = FileUploadUtils.subFileName(fileName);

					// �õ��������
					String randomName = FileUploadUtils
							.generateRandonFileName(fileName);

					// �õ����Ŀ¼
					String randomDir = FileUploadUtils
							.generateRandomDir(randomName);
					// ͼƬ�洢��Ŀ¼
					String imgurl_parent = "/productImg" + randomDir;

					File parentDir = new File(this.getServletContext()
							.getRealPath(imgurl_parent));
					// ��֤Ŀ¼�Ƿ���ڣ���������ڣ���������
					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}
					String imgurl = imgurl_parent + "/" + randomName;

					map.put("gImgurl", imgurl);

					/*IOUtils.copy(item.getInputStream(), new FileOutputStream(
							new File(parentDir, randomName)));*/
					
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(new File(parentDir, randomName));
					byte[] buffer = new byte[1024];
					
					int len;
					while ((len = in.read(buffer)) > 0)
						out.write(buffer, 0, len);
					
					in.close();
					out.close();
					item.delete();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		try {
			// �����ݷ�װ��javaBean��
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ProductService service = new ProductService();
		try {
			// ����service��������Ʒ����
			service.addProduct(p);
			response.sendRedirect(request.getContextPath()
					+ "/listProduct");
			return;
		} catch (AddProductException e) {
			e.printStackTrace();
			response.getWriter().write("添加商品失败");
			return;
		}		
	}

}
