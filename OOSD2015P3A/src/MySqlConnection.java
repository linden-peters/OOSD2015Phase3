import java.sql.*;

import javax.swing.*;

public class MySqlConnection {
	public static Connection getConnection()
	{
	   Connection conn = null;
	   try
	   {
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","root");
	      JOptionPane.showMessageDialog(null, "Connection Successful");
	      return conn;
	   }
	   catch(Exception e){
		  JOptionPane.showMessageDialog(null, e);
		  return null;
	  }	
	}

}	

