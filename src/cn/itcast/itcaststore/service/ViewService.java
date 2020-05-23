package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;
import cn.itcast.itcaststore.dao.ViewDao;
import cn.itcast.itcaststore.domain.View;

public class ViewService {
private ViewDao dao = new ViewDao();
	
	public void addView(View v) {
		try {
			dao.addView(v);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<View> findViewByManyCondition(String id, String category) {
		List<View> vs = null;
		try {
			vs = dao.findViewByManyCondition(id, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vs;
	}
}
