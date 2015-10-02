/**
 * @author Dwija
 * Date: Sept 19 2015
 * Purpose: Supplier entity class for instantiating a new Supplier object
 */
public class Supplier 
{
    private int SupplierId;
    private String SupName;
    
    
    public int getSupplierId() 
    {
        return SupplierId;
    }
    
    public String getSupName() 
    {
        return SupName;
    }
    
    public void setSupplierId(int id)
    {
        this.SupplierId = id;
    }
    
    public void setSupName(String name) 
    {
        this.SupName = name;          
    }
}
