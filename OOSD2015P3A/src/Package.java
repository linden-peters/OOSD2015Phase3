import java.util.Date;

public class Package 
{
	private int packageId;
	private String pkgName;
	private Date pkgStartDate;
	private Date pkgEndDate;
	private String pkgDesc;
	private double pkgBasePrice;
	private double pkgAgencyCommission;
	
	private int productSupplierId;
	private int productId;
	private int supplierId;
	
	private String prodName;
	private String supName;
	
	public Package(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc,
			double pkgBasePrice, double pkgAgencyCommission, int productSupplierId, int productId, int supplierId,
			String prodName, String supName) 
	{
		super();
		this.packageId = packageId;
		this.pkgName = pkgName;
		this.pkgStartDate = pkgStartDate;
		this.pkgEndDate = pkgEndDate;
		this.pkgDesc = pkgDesc;
		this.pkgBasePrice = pkgBasePrice;
		this.pkgAgencyCommission = pkgAgencyCommission;
		this.productSupplierId = productSupplierId;
		this.productId = productId;
		this.supplierId = supplierId;
		this.prodName = prodName;
		this.supName = supName;
	}
	

	public Package(int productId, String prodName) 
	{
		super();
		this.productId = productId;
		this.prodName = prodName;
	}
	
	


	public Package() 
	{
		super();
		this.packageId = -1;
		this.pkgName = "";
		this.pkgStartDate = null;
		this.pkgEndDate = null;
		this.pkgDesc = "";
		this.pkgBasePrice = -1;
		this.pkgAgencyCommission = -1;
		this.productSupplierId = -1;
		this.productId = -1;
		this.supplierId = -1;
		this.prodName = "";
		this.supName = "";
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Date getPkgStartDate() {
		return pkgStartDate;
	}

	public void setPkgStartDate(Date pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}

	public Date getPkgEndDate() {
		return pkgEndDate;
	}

	public void setPkgEndDate(Date pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgDesc() {
		return pkgDesc;
	}

	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}

	public double getPkgBasePrice() {
		return pkgBasePrice;
	}

	public void setPkgBasePrice(double pkgBasePrice) {
		this.pkgBasePrice = pkgBasePrice;
	}

	public double getPkgAgencyCommission() {
		return pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(double pkgAgencyCommission) {
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public int getProductSupplierId() {
		return productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}
	
	
	
	
	
	
}
