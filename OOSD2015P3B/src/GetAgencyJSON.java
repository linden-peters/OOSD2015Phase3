/*
 * GetAgencyJSON.java - Agency JSON Servlet
 * Author: Linden Peters
 * Written: 2015/10/06
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetAgencyJSON
 */
@WebServlet("/GetAgencyJSON")
public class GetAgencyJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String agencyid = request.getParameter("id");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM agencies WHERE AgencyId=" + agencyid);
			JSONObject obj = new JSONObject();
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next())
			{
				for (int i=1; i<rsmd.getColumnCount(); i++)
				{
					obj.put(rsmd.getColumnName(i), rs.getString(i));
				}
			}
			response.setContentType("application/json");
			out.print(obj.toJSONString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
