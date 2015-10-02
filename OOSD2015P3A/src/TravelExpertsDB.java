import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Author: Dwija Dholakia
 * Title: OOSD Team 5
 * Purpose: Connection to the TravelExperts Database*/

public class TravelExpertsDB 
{
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.25.139:3306/travelexperts","root","");
			//conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
