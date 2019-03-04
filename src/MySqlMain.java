import java.sql.*;

public class MySqlMain {
	public static void main(String[] args) {       
		MySqlQuery mySqlQuery = new MySqlQuery();
	    //根据需要访问的服务器地址及要访问的数据库名得到完整的url
	    String DB_URL = mySqlQuery.getDataBaseUrl("192.168.100.49", "websites");
	    // 数据库的用户名与密码
	    String USER = "root";
	    String PASS = "anyhow1995";
	    //创建connection对象
	    Connection conn = mySqlQuery.getConnection(DB_URL,USER,PASS);
	    //创建statement对象
	    Statement stmt = mySqlQuery.getStatement(conn);
	    String sql;
	    //sql = "SELECT id, name, url FROM websites where name = '武汉大学'";
	    sql = "SELECT * FROM websites";
	    //执行查询
	    mySqlQuery.goForQuery(sql,conn,stmt);
	    mySqlQuery.releaseSources(conn,stmt); 
    } 
    
}
