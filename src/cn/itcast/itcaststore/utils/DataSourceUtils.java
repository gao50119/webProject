package cn.itcast.itcaststore.utils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
private static DataSource dataSource = new ComboPooledDataSource();
/*private static DataSource dataSource = null;
static {
	ComboPooledDataSource cpds = new ComboPooledDataSource();
	try {
		cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/saler1.0?characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=UTC&amp;rewriteBatchedStatements=true");
		cpds.setUser("root");
		cpds.setPassword("1234");
		
		dataSource = cpds;
	}catch(Exception e) {
		e.printStackTrace();
	}
}*/
private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    public static DataSource getDataSource() {
    	return dataSource;
    }
    
    public static Connection getConnection() throws SQLException{
    	Connection con = tl.get();
    	if(con == null) {
    		con = dataSource.getConnection();
    		tl.set(con);
    	}
    	return con;
    }
    
    public static void startTransaction() throws SQLException{
    	Connection con = getConnection();
    	if(con != null)
    		con.setAutoCommit(false);
    }
    
    public static void releaseAndCloseConnection() throws SQLException{
    	Connection con = getConnection();
    	if(con != null) {
    		con.commit();
    		tl.remove();
    		con.close();
    	}
    }
    
    public static void rollback() throws SQLException{
    	Connection con = getConnection();
    	if(con != null) {
    		con.rollback();
    	}
    }
}
