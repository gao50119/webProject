package cn.itcast.itcaststore.web.servlet.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CatchLogServlet
 */
@WebServlet(name="catchLog",urlPatterns="/catchLog")
public class CatchLogServlet extends HttpServlet {
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
		String category = request.getParameter("gType");
		String logname = null;
		switch(category) {
		case "卡牌":
			logname = "log_card.txt";
			break;
		case "冒险":
			logname = "log_adven.txt";
			break;
		case "竞技":
			logname = "log_comp.txt";
			break;
		case "塔防":
			logname = "log_defense.txt";
			break;
		case "模拟":
			logname = "log_simula.txt";
			break;
		case "休闲":
			logname = "log_leisure.txt";
			break;
		case "恐怖":
			logname = "log_horror.txt";
			break;
		case "RPG":
			logname = "log_rpg.txt";
			break;
		case "策略":
			logname = "log_strategy.txt";
			break;
		case "动作":
			logname = "log_move.txt";
			break;
		case "射击":
			logname = "log_shot.txt";
			break;
		case "音乐":
			logname = "log_music.txt";
			break;
		case "体育":
			logname = "log_pe.txt";
			break;
		case "格斗":
			logname = "log_fight.txt";
			break;
		}
		
		//获取要下载的文件路径
		ServletContext sc = getServletContext();
		String serverPath = sc.getRealPath("/");
		String path = serverPath + "log/" + logname;
		//System.out.println(path);
		
		        
		//通知客户端以下载的方式打开
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(logname.getBytes("GBK"),"utf-8"));
		response.setCharacterEncoding("utf-8");
		       
		//构建输入输出流
		InputStream in = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = in.read(buf)) != -1){
			out.write(buf, 0, len);
		}
		
		
		in.close();
		out.close();
		
		//操作日志
    	String userid = request.getParameter("user");
    	Logger logger = Logger.getLogger("adminlog");
    	//SimpleDateFormat  date=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置时间格式
	    //System.out.println(date.format(new Date()));
	    //获取电脑上的ip
	  	String ip=InetAddress.getLocalHost().getHostAddress();
	  	//System.out.println("电脑ip："+ip+"电脑名称："+name);
	  	logger.info("销售员["+userid+"] IP地址["+ip+"] 下载日志["+path+"]");
	}

}
