package cn.itcast.itcaststore.service;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import cn.itcast.itcaststore.dao.SimilarDao;
import cn.itcast.itcaststore.domain.Similar;
import cn.itcast.itcaststore.domain.User;

public class SimilarService {
	SimilarDao dao = new SimilarDao();
	public void addSimilar(String user1,String user2,double score) throws SQLException{
    	try {
    		dao.addSimilar(user1, user2, score);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
	public void delSimilar() throws SQLException{
		try {
		    dao.delSimilar();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
    public Similar findSimilarById(String id) throws LoginException {
		//从数据库查找用户
		Similar si = null;
		try {
			si = dao.findSimilarById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("service:"+si);
		return si;	
	}
}
