import java.sql.*;

public class MySqlMain {
	public static void main(String[] args) {       
		MySqlQuery mySqlQuery = new MySqlQuery();
	    //������Ҫ���ʵķ�������ַ��Ҫ���ʵ����ݿ����õ�������url
	    String DB_URL = mySqlQuery.getDataBaseUrl("192.168.100.49", "websites");
	    // ���ݿ���û���������
	    String USER = "root";
	    String PASS = "anyhow1995";
	    //����connection����
	    Connection conn = mySqlQuery.getConnection(DB_URL,USER,PASS);
	    //����statement����
	    Statement stmt = mySqlQuery.getStatement(conn);
	    String sql;
	    //sql = "SELECT id, name, url FROM websites where name = '�人��ѧ'";
	    sql = "SELECT * FROM websites";
	    //ִ�в�ѯ
	    mySqlQuery.goForQuery(sql,conn,stmt);
	    mySqlQuery.releaseSources(conn,stmt); 
    } 
    
}
