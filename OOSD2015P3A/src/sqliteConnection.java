import java.sql.*;

import javax.swing.*;


public class sqliteConnection {

public static Connection getConnection()
{
Connection conn = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
conn = TravelExpertsDB.getConnection();
JOptionPane.showMessageDialog(null, "Connection Successful");
return conn;
}
catch(Exception e){
	JOptionPane.showMessageDialog(null, e);
	return null;
}	
}

}	
	
	/*
	Connection conn = null;
	public static Connection  dbConnector()
	{
		try{
			//Class.forName("org.sqlite.JDBC");
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\700260\\Desktop\\SqliteDatabase\\EmployeeData.sqlite");

          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","root");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}*/
