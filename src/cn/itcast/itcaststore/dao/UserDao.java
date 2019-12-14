package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.itcast.itcaststore.domain.*;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class UserDao {
    public Boolean addUser(User user) throws SQLException{
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    	
    	int count = runner.execute("{call insertNewUser(?,?,?,?)}",user.getId(),user.getName(),
    			user.getPassword(),user.getEmail());
    	
    	if(count > 0)
    		return true;
    	return false;
    }
    
    public User findUserByUsernameAndPassword(String id, String password) throws SQLException {
		String sql="select * from user where id=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),id,password);
	}
    
}
