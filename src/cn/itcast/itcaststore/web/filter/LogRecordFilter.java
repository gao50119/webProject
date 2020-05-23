package cn.itcast.itcaststore.web.filter;

import java.io.IOException;
import java.net.InetAddress;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.domain.View;
import cn.itcast.itcaststore.service.ViewService;
import cn.itcast.itcaststore.utils.EscapeCodingUtils;
import cn.itcast.itcaststore.utils.IdUtils;



/**
 * Servlet Filter implementation class LogRecordFilter
 */
@WebFilter(filterName="/LogRecordFilter",servletNames = {"toMenuSearchServlet", "showProductByPage","findProductById","addCart","showProductByPage"})
public class LogRecordFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogRecordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("过滤器销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// pass the request along the filter chain
		Cookie[] cookies = req.getCookies();//创建一个cookie集合并拿到cookie放入创建好的cookie集合里面
		String type = null;
		String time = null;
		//遍历cookie集合并判断是否有自己想要的指定cookie如果有则返回指定cookie的值，如果没有则返回空字符串
	    for (Cookie cookie:cookies){
	        String value = cookie.getValue();
	        if (cookie.getName().equals("type")){
	        	String value2 = EscapeCodingUtils.unescape(value);
	            type = value2;
	            //System.out.println("init:"+cookie.getValue()+",decode:"+value2);
	        }
	        if (cookie.getName().equals("time")) {
	            time = cookie.getValue();
	        }
	        //System.out.println("name:"+cookie.getName()+",value:"+value);
	    }
	    
	    
	    User user = (User) req.getSession().getAttribute("user");
	    
	    if(user!=null && type!=null && time!=null) {
	        if(user.getRole().equals("普通用户")) {
                //操作日志
    	        Logger logger = Logger.getLogger("adminlog");
	            //获取电脑上的ip
	  	        String ip=InetAddress.getLocalHost().getHostAddress();
	  	        logger.info("用户["+user.getId()+"] IP地址["+ip+"] 浏览["+type+"]类商品 停留时间["+time+"s]");
	  	        
	  	        
	  	        //写入数据库
	  		    String vid = IdUtils.getUUID();
	  		    View v = new View();
	  		    v.setId(user.getId());
	  		    v.setTime(Integer.parseInt(time));
	  		    v.setType(type);
	  		    v.setvNo(vid);
	  		    
	  		    ViewService service = new ViewService();
	  		    service.addView(v);
	        }
	    }

	    
	    Cookie cookie = new Cookie("type", null);
	    cookie.setMaxAge(0);//设置为答0 立即删除
	    Cookie cookie2 = new Cookie("time", null);
	    cookie2.setMaxAge(0);//设置为答0 立即删除

	    
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("过滤器初始化");
	}

}
