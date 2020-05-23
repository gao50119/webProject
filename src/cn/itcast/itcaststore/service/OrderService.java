package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderService {

	private OrderDao odao = new OrderDao();
	private ProductDao pdao = new ProductDao();
	

	public void addOrder(Order order) {
		try {

			DataSourceUtils.startTransaction();

			odao.addProduct(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public List<Order> findOrderByManyCondition(String id,String name, int type, String category) {
		List<Order> orders = null;
		try {
			orders = odao.findOrderByManyCondition(id,name,type,category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
		
		
	public List<Order> findOrderById(String id)	{
		List<Order> orders = null;
		try {
			orders = odao.findOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	public int findAllOrderById(String id,String gNo)	{
		int count = 0;
		try {
			count = odao.findAllOrderById(id,gNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public List<Product> SaleTrend(Date starttime,Date endtime) throws SQLException {
		List<Product> ls = odao.SaleTrend(starttime, endtime);
		return ls;
	}
		
		
	public List<Order> superFindOrder(String orderid, String salerid, String category) {
		List<Order> orders = null;
		try {
			orders = odao.superFindOrder(orderid,salerid,category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
		

	public void delOrderById(String id) {			
		try {
			//DataSourceUtils.startTransaction();
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	
	public void delOrderByIdWithClient(String id) {
		try {
			//DataSourceUtils.startTransaction();//��������
			odao.delOrderByIdWithClient(id); //ɾ������
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
