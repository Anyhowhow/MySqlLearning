import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlQuery {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * 根据服务器ip地址和要访问的数据库名来获取url
     * @param serverIpAddress
     * @param databaseName
     * @return 能够正确访问数据库的url
     */
    protected String getDataBaseUrl(String serverIpAddress,String databaseName) {
    	String portNumber = "3306";
    	StringBuffer DataBaseUrl = new StringBuffer();
    	DataBaseUrl.append("jdbc:mysql://");
    	DataBaseUrl.append(serverIpAddress+":"+portNumber+"/"+databaseName);
    	DataBaseUrl.append("?useSSL=false&serverTimezone=GMT");
    	return DataBaseUrl.toString();   	
    }
    /**
     * 获取connection对象
     * @param url
     * @param userName
     * @param password
     * @return
     */
    protected  Connection getConnection(String url,String userName,String password) {
    	Connection conn = null;
    	try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);       
            System.out.println("数据库驱动加载成功");
         // 打开链接
    		conn = DriverManager.getConnection(url,userName,password);
    		System.out.println("数据库连接成功");
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return conn;            
	}
    /**
     * 获取statement对象
     * @param conn
     * @return
     */
    protected Statement getStatement(Connection conn) {
    	Statement stmt = null;
    	try {
    		// 获取Statement类对象
        	stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return stmt;
    }
    /**
     * 
     * @param conn
     * @param stmt
     */
    protected void goForQuery(String sql,Connection conn,Statement stmt) {
    	try{                       
            ResultSet rs = stmt.executeQuery(sql);       
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", name: " + name);
                System.out.print(", URL: " + url);
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }
    /**
     * 释放资源
     * @param conn
     * @param stmt
     */
    protected void releaseSources(Connection conn,Statement stmt) {         
    	// 关闭资源
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        }// 什么都不做
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
