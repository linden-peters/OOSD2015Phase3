import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
  * @author Dwija
 * Date: Sept 19 2015
 * Purpose: This class talks with the database about anything to do with Products
 */
public class ProductDB 
{
    //Function that will fetch all products from the database into a List object
    public static List<Product> getAllProducts()
    {
        //List that will store all the products
        List<Product> products = new ArrayList<>();
        
        try ( //self-closing try
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM products"); 
        ) {
            while (result.next())
            {
                //Make new product from each entry in the database
                Product product = new Product();
                //Set the props
                product.setProductId(result.getInt("ProductId"));
                product.setProdName(result.getString("ProdName"));
                
                //Add the product to the products list
                products.add(product);
            }
        } 
        catch (SQLException e)
        {
            System.out.println("There was an error while trying to fetch all products. Message: " + e.getMessage());
        }
        
        return products;
    }
    
    //Function to get the product by its ID
    public static Product getProductById(int id)
    {
        //Initialize a product object as null. It will stay null unless we find a row in the database matching the id
        Product product = null;
        try ( //self-closing try
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM products WHERE Productid=" + id); 
        ) {
            if(result.next())
            {
                //Make a new product 
                product = new Product();
                //set props
                product.setProductId(id);
                product.setProdName(result.getString("ProdName"));
            }           
        } 
        catch (SQLException e)
        {
            System.out.println("There was an error while trying to fetch product by id. Message: " + e.getMessage());
        }
        return product;
    }
    
    //Function that will be used to updating products
    public static int updateProduct(Product prod)
    {
        //the update result.
        int result = -1;
        try (
            Connection connection = TravelExpertsDB.getConnection();
            Statement statement = connection.createStatement();
           
        ){
            result = statement.executeUpdate("UPDATE products SET ProdName='" + prod.getProdName() + "' WHERE ProductId=" + prod.getProductId());
        } catch (Exception ex) {
            System.out.println("Exception in updating products. Message:" + ex.getMessage());
        }
        return result;
    }
}
