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
			conn = DriverManager.getConnection("jdbc:mysql://192.168.25.25:3306/travelexperts","root","");
			//conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
