/*
 * GetAgentJSON.java - Agent JSON Servlet
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
 * Servlet implementation class GetAgentJSON
 */
@WebServlet("/GetAgentJSON")
public class GetAgentJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		try {
			int agentid = Integer.parseInt(request.getParameter("id"));
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM agents" + (agentid > 0 ? " WHERE AgentId=" + agentid : ""));
			JSONObject objs = new JSONObject();
			ResultSetMetaData rsmd = rs.getMetaData();
			int i = 0;
			while (rs.next())
			{
				JSONObject obj = new JSONObject();
				for (int j=1; j<rsmd.getColumnCount(); j++)
				{
					obj.put(rsmd.getColumnName(j), rs.getString(j));
				}
				objs.put(i, obj);
				i++;
			}
			response.setContentType("application/json");
			out.print(objs.toJSONString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
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
