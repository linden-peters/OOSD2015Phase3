import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dwija
 * Date: Sept 20 2015
 * Purpose: This class talks with the database about anything to do with Suppliers
 * 
 */
public class SupplierDB {
      //Function that will fetch all products from the database into a List object
    public static List<Supplier> getAllSupplier()
    {
        //List that will store all the products
        List<Supplier> suppliers = new ArrayList<>();
        
        try ( //self-closing try
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM suppliers"); 
        ) {
            while (result.next())
            {
                //Make new product from each entry in the database
                Supplier supplier = new Supplier();
                //Set the props
                supplier.setSupplierId(result.getInt("SupplierId"));
                supplier.setSupName(result.getString("SupName"));
                
                //Add the product to the products list
                suppliers.add(supplier);
            }
        } 
        catch (SQLException e)
        {
            System.out.println("There was an error while trying to fetch all suppliers. Message: " + e.getMessage());
        }
        
        return suppliers;
    }
    
    //Function to get the product by its ID
    public static Supplier getSupplierById(int id)
    {
        //Initialize a product object as null. It will stay null unless we find a row in the database matching the id
        Supplier supplier = null;
        try ( //self-closing try
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM suppliers WHERE SupplierId=" + id); 
        ) {
            if(result.next())
            {
                //Make a new product 
                supplier = new Supplier();
                //set props
                supplier.setSupplierId(id);
                supplier.setSupName(result.getString("SupName"));
            }           
        } 
        catch (SQLException e)
        {
            System.out.println("There was an error while trying to fetch suppliers by id. Message: " + e.getMessage());
        }
        return supplier;
    }
    
    //Function that will be used to updating products
    public static int updateSuppliers(Supplier supplier)
    {
        //the update resulr.
        int result = -1;
        try (
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
           
        ){
            result = statement.executeUpdate("UPDATE suppliers SET SupName='" + supplier.getSupName()+ "' WHERE SupplierId=" + supplier.getSupplierId());
        } catch (Exception ex) {
            System.out.println("Exception in updating suppliers. Message:" + ex.getMessage());
        }
        return result;
    }
}
