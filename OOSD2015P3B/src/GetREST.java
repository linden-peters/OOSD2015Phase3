//package example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

@Path("/getrest")
public class GetREST {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String serveText()
	{
		return "it worked!";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String serveXML()
	{
		return "<?xml version='1.0'?><success>IT WORKED!</success>";
	}
	@GET
	@Path("/{ a }")
	@Produces(MediaType.APPLICATION_JSON)
	public String serveJSON(@PathParam("a") int custid)
	{
		JSONObject obj = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE CustomerId=" + custid);
			obj = new JSONObject();
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next())
			{
				for (int i=1; i<=rsmd.getColumnCount(); i++)
				{
					obj.put(rsmd.getColumnName(i), rs.getString(i));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj.toJSONString();
	}
}
