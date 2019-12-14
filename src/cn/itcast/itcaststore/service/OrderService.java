package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;
import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderService {

	private OrderDao odao = new OrderDao();
	private ProductDao pdao = new ProductDao();
	
	// 1.添加订单
	public void addOrder(Order order) {
		try {
			// 1.开启事务
			DataSourceUtils.startTransaction();
			// 2.完成操作
			// 2.1向orders表中添加数据
			odao.addProduct(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); // 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				// 关闭，释放以及提交事务
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 查找所有订单
		public List<Order> findOrderByManyCondition(String id,String name, int type) {
			List<Order> orders = null;
			try {
				// 查找出订单信息
				orders = odao.findOrderByManyCondition(id,name,type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return orders;
		}

	//根据id删除订单 管理员删除订单
	public void delOrderById(String id) {			
		try {
			//DataSourceUtils.startTransaction();//开启事务
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	
	//普通用户删除订单
	public void delOrderByIdWithClient(String id) {
		try {
			//DataSourceUtils.startTransaction();//开启事务
			odao.delOrderByIdWithClient(id); //删除订单
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
