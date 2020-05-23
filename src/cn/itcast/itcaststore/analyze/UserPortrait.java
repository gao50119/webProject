package cn.itcast.itcaststore.analyze;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.dao.ViewDao;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.domain.View;

public class UserPortrait {
    private static List<Order> orders = null;
    private static List<View> vs = null;
    private static List<User> us = null;
	
	private static void readData(String uid, String type) {
		ViewDao v_dao = new ViewDao();
		OrderDao o_dao = new OrderDao();
		UserDao u_dao = new UserDao();
		
		try {
			vs = v_dao.findViewByManyCondition(uid, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			orders = o_dao.findOrderByManyCondition(null, uid, 0, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			us = u_dao.findNormalUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static HashMap<String,Double> getScore(HashMap<String,Double> score) {
		double total = 0;
		//归一化
		for(String str:score.keySet()) {
			total += score.get(str);
		}
		
		for(String str:score.keySet()) {
			double point = score.get(str)/total;
			score.put(str, point);
		}
		
		return score;
	}
	
	
	public HashMap<String, HashMap<String,Double>> getPortrait() {
		readData(null, null);
		HashMap<String, HashMap<String,Double>> dict = new HashMap<String, HashMap<String,Double>>();
		//System.out.println("up orders:"+orders);
		
		for(int i=0;i<us.size();i++) {
			User user = us.get(i);
			HashMap<String,Double> score =  new HashMap<String,Double>();
			score.put("卡牌", 0.0);
			score.put("冒险", 0.0);
			score.put("竞技", 0.0);
			score.put("塔防", 0.0);
			score.put("模拟", 0.0);
			score.put("休闲", 0.0);
			score.put("恐怖", 0.0);
			score.put("RPG", 0.0);
			score.put("策略", 0.0);
			score.put("动作", 0.0);
			score.put("射击", 0.0);
			score.put("体育", 0.0);
			score.put("格斗", 0.0);
			score.put("音乐", 0.0);
			
			for(int j=0;j<orders.size();j++) {
				Order order = orders.get(j);
				if(order.getUser().getId().equals(user.getId())) {
					String key = order.getProduct().getgType();
					//System.out.println("up order product:"+order.getProduct());
					//System.out.println("up:"+key);
					//System.out.println("up score:"+score);
					double value = score.get(key)+1;
					score.put(key, value);
				}
			}
			
			score = getScore(score);
			dict.put(user.getId(), score);
		}
		
		//System.out.println(dict);
		return dict;
	}
	
	

}
