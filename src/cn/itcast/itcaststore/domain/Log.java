package cn.itcast.itcaststore.domain;

import java.util.logging.Logger;

public class Log {

	private Logger logger;
	private String gtype;
	//private String userId = null;
	//private String pname = null;
	//private double total;
	
	public Log(String gtype){
		this.gtype = gtype;
		
		switch(this.gtype) {
		case "卡牌":
			this.logger = Logger.getLogger("typeCard");
			//System.out.println("match success");
			break;
		case "冒险":
			this.logger = Logger.getLogger("typeAdven");
			break;
		case "竞技":
			this.logger = Logger.getLogger("typeComp");
			break;
		case "塔防":
			this.logger = Logger.getLogger("typeDefense");
			break;
		case "模拟":
			this.logger = Logger.getLogger("typeSimula");
			break;
		case "休闲":
			this.logger = Logger.getLogger("typeLeisure");
			break;
		case "恐怖":
			this.logger = Logger.getLogger("typeHorror");
			break;
		case "RPG":
			this.logger = Logger.getLogger("typeRpg");
			break;
		case "策略":
			this.logger = Logger.getLogger("typeStrategy");
			break;
		case "动作":
			this.logger = Logger.getLogger("typeMove");
			break;
		case "射击":
			this.logger = Logger.getLogger("typeShot");
			break;
		case "音乐":
			this.logger = Logger.getLogger("typeMusic");
			break;
		case "体育":
			this.logger = Logger.getLogger("typePe");
			break;
		case "格斗":
			this.logger = Logger.getLogger("typeFight");
			break;
		}
		//System.out.println("long.java type:"+this.logger);
	}
	
	
	public boolean buyLog(String userId,double money) {
		try{
			//System.out.println("long.java func:"+this.logger);
			this.logger.info("[" + userId + "]购物花费了[" + money + "]");
			return true;
		}catch(Exception e){
			this.logger.info("错误信息:"+e.toString());
			return false;
		}
	}
	
	public boolean browseLog(String userId,String pname) {
		try{						
			this.logger.info("[" + userId + "]正在查询商品:" + pname);
			return true;
		}catch(Exception e){
			this.logger.info("错误信息"+e.toString());
			return false;
		}
	}

}
