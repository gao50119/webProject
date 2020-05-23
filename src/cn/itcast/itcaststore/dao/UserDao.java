package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.xpath.internal.operations.Bool;

import cn.itcast.itcaststore.domain.*;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class UserDao {
	//普通用户创建
    public Boolean addUser(User user) throws SQLException{
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    	
    	int count = runner.execute("{call insertNewUser(?,?,?,?)}",user.getId(),user.getName(),
    			user.getPassword(),user.getEmail());
    	
    	if(count > 0)
    		return true;
    	return false;
    }
    
    public Boolean addSaler(User user) throws SQLException{
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    	
    	int count = runner.execute("{call insertNewSaler(?,?,?,?,?)}",user.getId(),user.getName(),
    			user.getPassword(),user.getEmail(),user.getgType());
    	
    	if(count > 0)
    		return true;
    	return false;
    }
    
    
    public Boolean delSaler(String id) throws SQLException{
    	String sql = "delete from user where id=?";
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    	int count = runner.update(sql, id);
    	if(count > 0)
    		return true;
    	return false;
    }
    
    public User findUserByUsernameAndPassword(String id, String password) throws SQLException {
		String sql="select * from user where id=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),id,password);
	}
    
    public List<User> findSaler(String id, String category) throws SQLException{
    	List<Object> list = new ArrayList<Object>();
		String sql = "select * from user where role=\"销售\"";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and id=?";
			list.add(id);
		}

		if (category != null && category.trim().length() > 0) {
			sql += " and gType=?";
			list.add(category);
		}


		Object[] params = list.toArray();
		return runner.query(sql, new BeanListHandler<User>(User.class), params);
    }
    
    
    public List<User> findNormalUser() throws SQLException{
    	//List<Object> list = new ArrayList<Object>();
		String sql = "select * from user where role=\"普通用户\"";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class));
    }
    
    
    public User findUserById(String id) throws SQLException{
    	//List<Object> list = new ArrayList<Object>();
		String sql = "select * from user where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),id);
    }
    
    
    // 修改商品信息
 	public Boolean editSaler(User user) throws SQLException {
 		//1.创建集合并将商品信息添加到集合中
 		List<Object> obj = new ArrayList<Object>();
 		obj.add(user.getName());
 		obj.add(user.getPassword());
 		obj.add(user.getEmail());
 		obj.add(user.getgType());

 		//2.创建sql语句，并拼接sql
 		String sql  = "update user " +
 				      "set  name=?,password=?,email=?,gtype=? where id=?";

 		obj.add(user.getId());		
 		//System.out.println(sql);		
 		//System.out.println(obj);
 		//3.创建QueryRunner对象
 		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
 		//4.使用QueryRunner对象的update()方法更新数据
 		int count = runner.update(sql, obj.toArray());
 		
 		if(count > 0)
    		return true;
    	return false;
 	}
}
