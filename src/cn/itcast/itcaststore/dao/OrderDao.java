package cn.itcast.itcaststore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderDao {
	public void addProduct(Order order) throws SQLException {
		//String sql = "insert into `order`(oNo,oMoney,paystate,ordertime,id,gNo) values(?,?,1,CURDATE(),?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.execute("{call addOrderList(?,?,?,?)}", order.getoNo(),order.getoMoney(),
				order.getUser().getId(), order.getProduct().getgNo());
	}
	
	
	public List<Product> SaleTrend(Date starttime,Date endtime) throws SQLException {
		String sql = "select product.gNo,product.gName,COUNT(product.gNo) number from `order`,product " + 
				"where `order`.gNo=product.gNo and `order`.ordertime>=? and `order`.ordertime<=? " + 
				"GROUP BY product.gNo,product.gName " + 
				"order by number desc LIMIT 0,10";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());


		return runner.query(sql, new ResultSetHandler<List<Product>>() {
			public List<Product> handle(ResultSet rs) throws SQLException {
				List<Product> ps = new ArrayList<Product>();
               
				while (rs.next()) {
					Product p = new Product();
					p.setgNo(rs.getString("product.gNo"));
					p.setgName(rs.getString("product.gName"));
					p.setNum(rs.getInt("number"));
					ps.add(p);
					
				}
				return ps;
			}
		}, starttime, endtime);
	}

	public void delOrderById(String id) throws SQLException {
		//state=2
		String sql="update `order` set adminState=2 where oNo=?";		
		QueryRunner runner = new QueryRunner();		
		runner.update(DataSourceUtils.getConnection(),sql,id);	
	}
	
	public void delOrderByIdWithClient(String id) throws SQLException {
		//state=3
		String sql="update `order` set clientState=2 where oNo=?";		
		QueryRunner runner = new QueryRunner();		
		runner.update(DataSourceUtils.getConnection(),sql,id);	
	}
	
	public List<Order> findOrderByManyCondition(String id, String name, int type, String category)
			throws SQLException {

		List<Object> objs = new ArrayList<Object>();
		String sql = "select `order`.*,user.*,product.* from `order`,user,product where user.id=`order`.id and `order`.gNo=product.gNo ";

		if (id != null && id.trim().length() > 0) {
			sql += " and `order`.oNo=?";
			objs.add(id);
		}
		if (name != null && name.trim().length() > 0) {
			sql += " and `order`.id=?";
			objs.add(name);
		}
		if (type == 1) {
			sql += " and `order`.adminState=1";
		}
		else if(type == 2) {
			sql += " and `order`.clientState=1";
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and product.gType=?";
			objs.add(category);
		}

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
               
				while (rs.next()) {
					Order order = new Order();
					order.setoNo(rs.getString("order.oNo"));
					order.setoMoney(rs.getDouble("order.oMoney"));
					order.setOrdertime(rs.getDate("order.ordertime"));
					order.setClientState(rs.getInt("order.clientState"));
					order.setAdminState(rs.getInt("order.adminState"));
					orders.add(order);
					
					User user = new User();
					user.setId(rs.getString("user.id"));
					user.setEmail(rs.getString("user.email"));
					user.setPassword(rs.getString("user.password"));
					user.setRole(rs.getString("user.role"));
					user.setMoney(rs.getInt("user.money"));
					user.setName(rs.getString("user.name"));
					user.setgType(rs.getString("user.gtype"));
					order.setUser(user);
					
					Product product=new Product();
					product.setgDescription(rs.getString("gDescription"));
					product.setgImgurl(rs.getString("gImgurl"));
					product.setgName(rs.getString("gName"));
					product.setgNo(rs.getString("product.gNo"));
					product.setgPostTime(rs.getDate("gPostTime"));
					product.setgPrice(rs.getDouble("gPrice"));
					product.setgScore(rs.getDouble("gScore"));
					product.setgType(rs.getString("product.gType"));
					//System.out.println("odao type:"+rs.getString("product.gType"));
					product.setgState(rs.getInt("gState"));
					order.setProduct(product);
				}

				return orders;
			}
		}, objs.toArray());
	}
	
	
	public List<Order> findOrderById(String id)
			throws SQLException {

		List<Object> objs = new ArrayList<Object>();
		String sql = "select `order`.*,user.*,product.* from `order`,user,product "
				+ "where user.id=`order`.id and `order`.gNo=product.gNo and `order`.id=? "
				+ "order by ordertime desc LIMIT 0,3";

		objs.add(id);

		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
               
				while (rs.next()) {
					Order order = new Order();
					order.setoNo(rs.getString("order.oNo"));
					order.setoMoney(rs.getDouble("order.oMoney"));
					order.setOrdertime(rs.getDate("order.ordertime"));
					order.setClientState(rs.getInt("order.clientState"));
					order.setAdminState(rs.getInt("order.adminState"));
					orders.add(order);
					
					User user = new User();
					user.setId(rs.getString("user.id"));
					user.setEmail(rs.getString("user.email"));
					user.setPassword(rs.getString("user.password"));
					user.setRole(rs.getString("user.role"));
					user.setMoney(rs.getInt("user.money"));
					user.setName(rs.getString("user.name"));
					user.setgType(rs.getString("user.gtype"));
					order.setUser(user);
					
					Product product=new Product();
					product.setgDescription(rs.getString("gDescription"));
					product.setgImgurl(rs.getString("gImgurl"));
					product.setgName(rs.getString("gName"));
					product.setgNo(rs.getString("product.gNo"));
					product.setgPostTime(rs.getDate("gPostTime"));
					product.setgPrice(rs.getDouble("gPrice"));
					product.setgScore(rs.getDouble("gScore"));
					product.setgType(rs.getString("product.gType"));
					//System.out.println("odao type:"+rs.getString("product.gType"));
					product.setgState(rs.getInt("gState"));
					order.setProduct(product);
				}

				return orders;
			}
		}, objs.toArray());
	}
	
	
	public int findAllOrderById(String id,String gNo) throws SQLException {

		String sql = "select count(`order`.gNo) number from `order`,user,product "
				+ "where user.id=`order`.id and `order`.gNo=product.gNo and `order`.id=? and `order`.gNo=?";		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Long count = (Long)runner.query(sql, new ScalarHandler(), id, gNo);
		return count.intValue();
	}
	
	
	public List<Order> superFindOrder(String orderid, String salerid, String category)
			throws SQLException {

		List<Object> objs = new ArrayList<Object>();
		String sql = "select `order`.*,u1.*,product.*,u2.* "
				+ "from `order`,user as u1,user as u2,product "
				+ "where u1.id=`order`.id and `order`.gNo=product.gNo and product.gType=u2.gtype ";

		if (orderid != null && orderid.trim().length() > 0) {
			sql += " and `order`.oNo=?";
			objs.add(orderid);
		}
		if (salerid != null && salerid.trim().length() > 0) {
			sql += " and u2.id=?";
			objs.add(salerid);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and product.gType=?";
			objs.add(category);
		}
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
               
				while (rs.next()) {
					Order order = new Order();
					order.setoNo(rs.getString("order.oNo"));
					order.setoMoney(rs.getDouble("order.oMoney"));
					order.setOrdertime(rs.getDate("order.ordertime"));
					order.setClientState(rs.getInt("order.clientState"));
					order.setAdminState(rs.getInt("order.adminState"));
					orders.add(order);
					
					User user1 = new User();
					user1.setId(rs.getString("u1.id"));
					user1.setEmail(rs.getString("u1.email"));
					user1.setPassword(rs.getString("u1.password"));
					user1.setRole(rs.getString("u1.role"));
					user1.setMoney(rs.getInt("u1.money"));
					user1.setName(rs.getString("u1.name"));
					user1.setgType(rs.getString("u1.gtype"));
					order.setUser(user1);
					
					User user2 = new User();
					user2.setId(rs.getString("u2.id"));
					user2.setEmail(rs.getString("u2.email"));
					user2.setPassword(rs.getString("u2.password"));
					user2.setRole(rs.getString("u2.role"));
					user2.setMoney(rs.getInt("u2.money"));
					user2.setName(rs.getString("u2.name"));
					user2.setgType(rs.getString("u2.gtype"));
					order.setSaler(user2);
					
					Product product=new Product();
					product.setgDescription(rs.getString("gDescription"));
					product.setgImgurl(rs.getString("gImgurl"));
					product.setgName(rs.getString("gName"));
					product.setgNo(rs.getString("product.gNo"));
					product.setgPostTime(rs.getDate("gPostTime"));
					product.setgPrice(rs.getDouble("gPrice"));
					product.setgScore(rs.getDouble("gScore"));
					product.setgType(rs.getString("gType"));
					product.setgState(rs.getInt("gState"));
					order.setProduct(product);
				}

				return orders;
			}
		}, objs.toArray());
	}

}
