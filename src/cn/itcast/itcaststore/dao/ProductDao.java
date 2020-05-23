package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

//import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class ProductDao {
	// 查找所有商品
	public List<Product> listAll() throws SQLException {
		String sql = "select * from product";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	
	public List<Product> listByType(String category) throws SQLException {
		String sql = "select * from product where gType=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class), category);
	}
	
	// 获取数据总条数
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from product";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (!"全部商品".equals(category)) {
			sql += " where gType=?";
			Long count = (Long) runner.query(sql, new ScalarHandler(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			return count.intValue();
		}
	}
	// 获取当前页数据
	public List<Product> findByPage(int currentPage, int currentCount,
			String category) throws SQLException {
		String sql = null;
		// 参数
		Object[] obj = null;
		// 如果category不为null,代表是按分类查找
		if (!"全部商品".equals(category)) {
			sql = "select * from product  where gType=? limit ?,?";
			obj = new Object[] { category, (currentPage - 1) * currentCount,
					currentCount, };
		} else {
			sql = "select * from product  limit ?,?";
			obj = new Object[] { (currentPage - 1) * currentCount,
					currentCount, };
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				obj);
	}	
	
	// 添加商品
	public void addProduct(Product p) throws SQLException {

		String sql = "insert into product(gNo,gName,gPrice,gType,gPostTime,gImgurl,gDescription,gScore,gState) "
				+ "values(?,?,?,?,CURDATE(),?,?,?,1)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getgNo(), p.getgName(), p.getgPrice(),
				p.getgType(), p.getgImgurl(), p.getgDescription(),p.getgScore());
	}
	
	// 根据id查找商品
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from product where gNo=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}
	
	// 销售榜单 按销售量排序
	public List<Object[]> salesList(String year, String month, String category)
			throws SQLException {
		String sql = "SELECT product.gName,Count(product.gNo) totalsalnum,product.gNo "
				+ "FROM `order`,product "
				+ "WHERE product.gNo=`order`.gNo and year(ordertime)=? and month(ordertime)=? and gType=? GROUP BY product.gNo ORDER BY totalsalnum DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month, category);
	}
	
	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category)
			throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from product where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and gNo=?";
			list.add(id);
		}

		if (name != null && name.trim().length() > 0) {
			sql += " and gName=?";
			list.add(name);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and gType=?";
			list.add(category);
		}


		Object[] params = list.toArray();
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				params);
	}
	
	// 修改商品信息
	public void editProduct(Product p) throws SQLException {
		//1.创建集合并将商品信息添加到集合中
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getgName());
		obj.add(p.getgPrice());
		//obj.add(p.getgPostTime());
		obj.add(p.getgType());
		obj.add(p.getgScore());
		obj.add(p.getgDescription());
		obj.add(p.getgState());
		//2.创建sql语句，并拼接sql
		String sql  = "update product " +
				      "set  gName=?,gPrice=?,gType=?,gScore=?,gDescription=?,gState=? ";
		//判断是否有图片
		if (p.getgImgurl() != null && p.getgImgurl().trim().length() > 0) {
			sql += " ,gImgurl=?";
			obj.add(p.getgImgurl());
		}
		sql += " where gNo=?";
		obj.add(p.getgNo());		
		//System.out.println(sql);		
		//System.out.println(obj);
		//3.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.使用QueryRunner对象的update()方法更新数据
		runner.update(sql, obj.toArray());
	}

	//前台，用于搜索框模糊查询相应的商品
	public List<Product> findBookByName(int currentPage, int currentCount,
			String searchfield) throws SQLException {
		//根据名字模糊查询
		String sql = "SELECT * FROM product WHERE gName LIKE '%"+searchfield+"%' LIMIT ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//用于分页查询的数据
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
		return runner.query(sql, 
				new BeanListHandler<Product>(Product.class),currentPage-1,currentCount);
	}
	
	//前台搜索框，根据名字模糊查询出的商品总数量
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		String sql = "SELECT COUNT(*) FROM product WHERE gName LIKE '%"+searchfield+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//查询出满足条件的总数量，为long类型
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	
	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) throws SQLException {
		String sql = "UPDATE product SET gState=2 WHERE gNo = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
