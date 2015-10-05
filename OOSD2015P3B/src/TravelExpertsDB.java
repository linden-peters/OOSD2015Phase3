/*
 * TravelExpertsDB.java - DB Connection Provider
 * Author: Linden Peters
 * Written: 2015/10/05
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TravelExpertsDB 
{
	public static Connection getConnection()
	{
		Connection dbConn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection("jdbc:mysql://192.168.25.139:3306/travelexperts","root","");
			//conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbConn;
	}
}
