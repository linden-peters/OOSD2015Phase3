/*
 * GetAgent.java - Agent Servlet
 * Author: Linden Peters
 * Written: 2015/10/05
 */

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAgent
 */
public class GetAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String agentId;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAgent() {
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
		agentId = request.getParameter("id");
	    if (agentId != null)
	    {
			PrintWriter out;
			try {
				 out = response.getWriter();
			     //get the database objects
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				//Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orant11g","ictoosd","ictoosd");
				Class.forName("com.mysql.jdbc.Driver");
				//Connection con1 = DriverManager.getConnection("jdbc:mysql://192.168.25.139:3306/travelexperts","root","");
				Connection con1 = TravelExpertsDB.getConnection();
				Statement stmt1 = con1.createStatement();
				ResultSet rs = stmt1.executeQuery("SELECT * FROM agents WHERE AgentId = '" + agentId + "'");
			    
				//print the start of the display table and open the first row
				out.println("<table>");
				
			    ResultSetMetaData rsmd = rs.getMetaData();
			    while (rs.next())
			    {
			       for (int i=1; i<=rsmd.getColumnCount(); i++)
			       {
			          out.println("<tr><td>" + rsmd.getColumnName(i) + "</td><td>" + rs.getString(i) + "</td></tr>");
			       }
			    }
			    out.println("</table>");
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
