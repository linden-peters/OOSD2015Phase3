/*
 * GetCustomerXML.java - Customer XML Servlet
 * Author: Linden Peters
 * Written: 2015/10/05
 */

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Servlet implementation class GetCustomerXML
 */
public class GetCustomerXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String customerId;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerXML() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doStuff(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doStuff(request, response);
	}

	private void doStuff(HttpServletRequest request, HttpServletResponse response)
	{
	    //if the employee ID was selected, populate the Employee list
		customerId = request.getParameter("id");
	    if (customerId != null)
	    {
			response.setContentType("text/xml");
	    	PrintWriter out;
			try {
				 out = response.getWriter();
			     //get the database objects
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				//Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orant11g","ictoosd","ictoosd");
				Class.forName("com.mysql.jdbc.Driver");
				//Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
				Connection con1 = TravelExpertsDB.getConnection();
				Statement stmt1 = con1.createStatement();
				ResultSet rs = stmt1.executeQuery("SELECT * FROM customers where CustomerId = " + customerId);
			    
				//print the start of the display table and open the first row
				out.println("<customers>");
				
			    ResultSetMetaData rsmd = rs.getMetaData();
			    while (rs.next())
			    {
			    	out.println("<customer>");
			    	for (int i=1; i<=rsmd.getColumnCount(); i++)
			        {
			           out.println("<" + rsmd.getColumnName(i) + ">" + rs.getString(i) + "</" + rsmd.getColumnName(i) + ">");
			        }
			    	out.println("</customer>");
			    }
			    out.println("</customers>");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	}
}
