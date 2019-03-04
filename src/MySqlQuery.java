import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlQuery {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * ���ݷ�����ip��ַ��Ҫ���ʵ����ݿ�������ȡurl
     * @param serverIpAddress
     * @param databaseName
     * @return �ܹ���ȷ�������ݿ��url
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
     * ��ȡconnection����
     * @param url
     * @param userName
     * @param password
     * @return
     */
    protected  Connection getConnection(String url,String userName,String password) {
    	Connection conn = null;
    	try{
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);       
            System.out.println("���ݿ��������سɹ�");
         // ������
    		conn = DriverManager.getConnection(url,userName,password);
    		System.out.println("���ݿ����ӳɹ�");
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return conn;            
	}
    /**
     * ��ȡstatement����
     * @param conn
     * @return
     */
    protected Statement getStatement(Connection conn) {
    	Statement stmt = null;
    	try {
    		// ��ȡStatement�����
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
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");    
                // �������
                System.out.print("ID: " + id);
                System.out.print(", name: " + name);
                System.out.print(", URL: " + url);
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }
    }
    /**
     * �ͷ���Դ
     * @param conn
     * @param stmt
     */
    protected void releaseSources(Connection conn,Statement stmt) {         
    	// �ر���Դ
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        }// ʲô������
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
