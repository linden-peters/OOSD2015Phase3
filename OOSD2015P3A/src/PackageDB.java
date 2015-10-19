import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;

public class PackageDB 
{
	public static Package pkgObj;
	//private static DateFormat df;
	/*public static Vector vectorPkgObj;
	 

	public static Vector<Package> GetAllProducts() throws NumberFormatException, SQLException
	{
		try
		{
			vectorPkgObj = new Vector<Package>();
			pkgObj = new Package();
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Products");
			while(rs.next())
			{
				pkgObj.setProductId(Integer.parseInt(rs.getString("ProductId")));
				pkgObj.setProdName(rs.getString("ProdName"));
				vectorPkgObj.add(pkgObj);
			}
			rs.close();
			conn.close();
		}
		
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}	
		return vectorPkgObj;
		
	}*/
	
	
	// populate the combo box with the package IDs
	public static Vector<String> getPackagesById() {
		Vector<String> pkgids = new Vector<String>();
		try
		{
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PackageId from packages");
			while(rs.next())
			{
				pkgids.add(rs.getString(1));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return pkgids;
		//return null;
	}
	
	// this will return all the package information based on the pkgid
	public static Package GetPackage(String pkgId)
	{
		try
		{
			pkgObj = new Package();
			Connection conn = TravelExpertsDB.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from packages where PackageId=" + pkgId);
			if(rs.next())
			{
				//df = new SimpleDateFormat();
				pkgObj.setPackageId(Integer.parseInt(pkgId));
				pkgObj.setPkgName(rs.getString(2));
			//	pkgObj.setPkgStartDate(rs.getSelectedDate(3));
			
				pkgObj.setPkgStartDate(rs.getDate(3));
				pkgObj.setPkgEndDate(rs.getDate(4));
			//	pkgObj.setPkgStartDate(Date.valueOf(rs.getString(3)));
			//	pkgObj.setPkgEndDate(Date.valueOf(rs.getString(4)));
				pkgObj.setPkgDesc(rs.getString(5));
				pkgObj.setPkgBasePrice(Float.valueOf(rs.getString(6)));
				pkgObj.setPkgAgencyCommission(Float.valueOf(rs.getString(7)));
			}
			else
			{
				System.out.println("no rows returned");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return pkgObj;
		
	}
	
	
	// ADD THE PACKAGES
	
	public static boolean addPackage(Package pkgObj)
	{
		try
        {
            Connection conn = TravelExpertsDB.getConnection();
            Statement stmt = conn.createStatement();
            String addPkg = "INSERT INTO packages"
                       	+ "( PkgName"
                       	+ ", PkgStartDate"
                       	+ ", PkgEndDate"
                       	+ ", PkgDesc"
                       	+ ", PkgBasePrice"
                       	+ ", PkgAgencyCommission"
                       	+ ") VALUES"
                       	+ "( '" + pkgObj.getPkgName() + "'" 
                       	+ ", '" + dateToString(pkgObj.getPkgStartDate()) + "'"
                       	+ ", '" + dateToString(pkgObj.getPkgEndDate()) + "'"
                       	+ ", '" + pkgObj.getPkgDesc() + "'"
                       	+ ", " + pkgObj.getPkgBasePrice()
                       	+ ", " + pkgObj.getPkgAgencyCommission() 
                       	+ ")";
            int numRows = stmt.executeUpdate(addPkg);
            if(numRows>0)
            {
            	return true;
            }
            else
            {
            	return false;
            }
            
        }
		catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }	
		
	}

	private static String dateToString(Date pkgDate) {
		String dateStr;
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       	dateStr = sdf.format(pkgDate);
       	return dateStr;
	}
	
	//Method to update Package
    public static boolean updatePackage(Package modPkg)
    {
        try
        {
        	Connection conn = TravelExpertsDB.getConnection();
            Statement stmt = conn.createStatement();                          
            String updatePackage = "UPDATE packages SET"
                                + " PkgName = '" + modPkg.getPkgName() + "'"
                                + " ,PkgStartDate = '" + dateToString(modPkg.getPkgStartDate()) + "'"
                                + " ,PkgEndDate = '" + dateToString(modPkg.getPkgEndDate()) + "'"
                                + " ,PkgDesc = '" + modPkg.getPkgDesc() + "'"
                                + " ,PkgBasePrice = " + modPkg.getPkgBasePrice()
                                + " ,PkgAgencyCommission = " + modPkg.getPkgAgencyCommission()
                                + " WHERE PackageId = " + modPkg.getPackageId();
            System.out.println("update Package sql : " + updatePackage);
            int numRows = stmt.executeUpdate(updatePackage);
            conn.close();
            if (numRows == 0)
            {
                    System.out.println("Update package failed");
                    return false;
            }
            else
            {
                    System.out.println("updated " + numRows + " row(s) in Package");
                    return true;
            }
            
        }
        catch(SQLException ex)
        {
            System.out.println("Error occured while updating package: " + ex.getMessage());
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
	
	
	/*// Product and Suppliers
	public static int GetProdSuppId(int productId, int supplierId) throws SQLException
    {
        int prodSuppId =0;
        try
        {
            Connection conn = TravelExpertsDB.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select ProductSupplierId from Products_Suppliers where ProductId = " + productId + " and  SupplierId = "  + supplierId  + "";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
            {
                 prodSuppId = Integer.parseInt(rs.getString("ProductSupplierId"));
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No record found");
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        
        }
        return prodSuppId; 
    }*/
}
