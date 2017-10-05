package pizzaordersystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseDAO {
    protected static Connection conn;
	public void getConn()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","SCOTT","TIGER");				
			System.out.println("Driver Connected");
			//return conn;
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return conn;
	}
		
	
	public void closeConn()
	{
		try {
			if(conn!=null)
				{
				conn.close();
				System.out.println("Connection Closed");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
