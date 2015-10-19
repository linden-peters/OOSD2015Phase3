/*
 * PackageGUI.java - Package Edit Form
 * Author: Dwija Dholakia, Linden Peters
 * Written: 2015/10/06
 */
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

public class PackageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfPkgName;
	private JTextField tfBasePrice;
	private JTextField tfAgencyCom;
	//private ComboBoxModel cbProdIdModel;
	//private ComboBoxModel cbSupIdModel;
	private JComboBox cbPkgId;
	private ComboBoxModel cbPackageIdModel;
	private JTextArea taDesc;
	private JButton btnExit;
	private JButton btnSave;
	private JDateChooser dcStartDate; 
	private JDateChooser dcEndDate;
	private int packageId1 = 0;
	private int editpackageId = 0;
	private int targetID = 0;
	
	private JList LstPackageCurrent;
	
	Package pkgObj;
	private JList LstPackageUnselect;
	private JButton btnMoveUp;
	private JButton btnPullDown;
	//private DateFormat df;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageGUI frame = new PackageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getPkgId(String pkgId)
	{
		Integer x = Integer.valueOf(pkgId);
		//packageId1 = x;
		//targetID = x;
		editpackageId = x;
		System.out.print("The ID you selected is : " + editpackageId);
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM packages WHERE PackageId='" + x + "'");		
			stmt.executeQuery();
			rs= stmt.getResultSet();
			while (rs.next())
			{
				tfPkgName.setText(rs.getString("pkgName"));
				dcStartDate.setDate(rs.getDate("pkgStartDate"));
				dcEndDate.setDate(rs.getDate("pkgEndDate")); //2014/08/06 15:59:48
				taDesc.setText(rs.getString("pkgDesc"));
				tfBasePrice.setText(rs.getString("pkgBasePrice"));
				tfAgencyCom.setText(rs.getString("pkgAgencyCommission"));
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		filllist4();
		filllist5();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public PackageGUI() {
		setTitle("Package Management - Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1012, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPackageId.setBounds(12, 60, 341, 19);
		contentPane.add(lblPackageId);
		
		JLabel lblPackageName = new JLabel("Package Name");
		lblPackageName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPackageName.setBounds(12, 89, 341, 19);
		contentPane.add(lblPackageName);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStartDate.setBounds(12, 118, 341, 16);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndDate.setBounds(12, 147, 341, 16);
		contentPane.add(lblEndDate);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(12, 176, 341, 16);
		contentPane.add(lblDescription);
		
		JLabel lblBasePrice = new JLabel("Base Price");
		lblBasePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBasePrice.setBounds(12, 237, 341, 16);
		contentPane.add(lblBasePrice);
		
		JLabel lblAgencyCommission = new JLabel("Agency Commission");
		lblAgencyCommission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAgencyCommission.setBounds(12, 266, 341, 19);
		contentPane.add(lblAgencyCommission);
		
		cbPackageIdModel = new DefaultComboBoxModel(PackageDB.getPackagesById());
		cbPkgId = new JComboBox();
		cbPkgId.setEnabled(false);
		cbPkgId.setModel(cbPackageIdModel);
		cbPkgId.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
				NumberFormat currency = NumberFormat.getCurrencyInstance();
				currency.setMinimumFractionDigits(2);
				String pkgId = (String) cbPkgId.getSelectedItem();
				pkgObj = PackageDB.GetPackage(pkgId);
				tfPkgName.setText(pkgObj.getPkgName());
				//tfPkgStart.setText(date.format(pkgObj.getPkgStartDate()));
				dcEndDate.getDate();
				taDesc.setText(pkgObj.getPkgDesc());
				tfBasePrice.setText(currency.format(pkgObj.getPkgBasePrice()));
				tfAgencyCom.setText(currency.format(pkgObj.getPkgAgencyCommission()));
			}
		});
		cbPkgId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbPkgId.setBounds(160, 57, 193, 22);
		contentPane.add(cbPkgId);
		
		tfPkgName = new JTextField();
		tfPkgName.setForeground(Color.BLACK);
		tfPkgName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPkgName.setBounds(160, 86, 193, 22);
		contentPane.add(tfPkgName);
		tfPkgName.setColumns(10);
		
		tfBasePrice = new JTextField();
		tfBasePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfBasePrice.setBounds(160, 234, 193, 22);
		contentPane.add(tfBasePrice);
		tfBasePrice.setColumns(10);
		
		tfAgencyCom = new JTextField();
		tfAgencyCom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAgencyCom.setBounds(160, 263, 193, 22);
		contentPane.add(tfAgencyCom);
		tfAgencyCom.setColumns(10);
		
		taDesc= new JTextArea();
		taDesc.setTabSize(4);
		taDesc.setLineWrap(true);
		taDesc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taDesc.setBounds(160, 171, 193, 57);
		contentPane.add(taDesc);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(256, 308, 97, 25);
		contentPane.add(btnExit);
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if (editpackageId > 0)
				{
					System.out.println("editpackageId = " + editpackageId + "\n\tEdit Form");
					PreparedStatement stmt = null;
					Connection conn = null;
					try {
						conn = TravelExpertsDB.getConnection();
						dcStartDate.setDateFormatString("MMMMM d, yyyy");
						dcEndDate.setDateFormatString("MMMMM d, yyyy");
						String sql="UPDATE packages SET PkgName='"+ tfPkgName.getText()+"' , "
								+ "PkgStartDate='" + dcStartDate.getDate() + "'  ,"
								+ "PkgEndDate='" + dcEndDate.getDate() + "' ,"
								+ "PkgDesc='" + taDesc.getText()+ "' ," 
								+ "PkgBasePrice='" + tfBasePrice.getText()+ "' ,"
								+ "PkgAgencyCommission='" + tfAgencyCom.getText()+"' "
								+ "WHERE PackageId='" + editpackageId + "' ";					
						System.out.println("SQL: " + sql);
						stmt = conn.prepareStatement(sql);
						stmt.executeUpdate();
						stmt.close();
					}
					catch(SQLException ex)
		            {
		                System.out.println("Error occured while updating package: " + ex.getMessage());
		            }
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else if (packageId1 == 0)
				{
					System.out.println("packageId1 = " + packageId1 + "\n\tAdd Form");
					pkgObj = new Package();
					pkgObj.setPkgName(tfPkgName.getText());
					pkgObj.setPkgStartDate(dcStartDate.getDate());
					pkgObj.setPkgEndDate(dcEndDate.getDate());
					pkgObj.setPkgDesc(taDesc.getText());
					pkgObj.setPkgBasePrice(Double.parseDouble(tfBasePrice.getText()));
					pkgObj.setPkgAgencyCommission(Double.parseDouble(tfAgencyCom.getText()));
					boolean addPackage = PackageDB.addPackage(pkgObj);
					if(addPackage)
					{
						JOptionPane.showMessageDialog(null, "Package Added Successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Package not added. Please try again");
					}
				}
				else
				{
					System.out.println("packageId1 is negative\n\tSomething has gone horribly wrong.");
				}
			}
		});
		btnSave.setBounds(147, 309, 97, 25);
		contentPane.add(btnSave);
		
		dcStartDate = new JDateChooser();
		dcStartDate.setDateFormatString("yyyy-MM-dd");
		dcStartDate.setBounds(160, 118, 193, 22);
		contentPane.add(dcStartDate);
		
		dcEndDate = new JDateChooser();
		dcEndDate.setDateFormatString("yyyy-MM-dd");
		dcEndDate.setBounds(160, 147, 193, 22);
		contentPane.add(dcEndDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(443, 41, 527, 163);
		contentPane.add(scrollPane);
		
		LstPackageCurrent = new JList();
		scrollPane.setViewportView(LstPackageCurrent);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(443, 255, 527, 175);
		contentPane.add(scrollPane_1);
		
		LstPackageUnselect = new JList();
		scrollPane_1.setViewportView(LstPackageUnselect);
		
		btnMoveUp = new JButton("Move up");
		btnMoveUp.setBounds(553, 214, 97, 25);
		contentPane.add(btnMoveUp);
		
		btnPullDown = new JButton("Pull down");
		btnPullDown.setBounds(771, 214, 97, 25);
		contentPane.add(btnPullDown);
	}
	
	private void filllist4() {
		//targetID = getPkgId(x);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			//Integer x = Integer.valueOf(pkgId);
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("SELECT p.ProductId, t.ProdTypeName, s.SupName "
					+ "FROM products AS p "
					+ "INNER JOIN producttypes AS t "
					+ "INNER JOIN suppliers AS s "
					+ "INNER JOIN packages_products AS pp "
					+ "ON t.ProductTypeId=p.ProductTypeId AND s.SupplierId=p.SupplierId AND pp.ProductId=p.ProductId "
					+ "WHERE pp.PackageId='" + editpackageId + "' ");
					/*"SELECT  pps.ProductSupplierId, Products.ProductId, Products.ProdName, " +
                            "Suppliers.SupplierId, Suppliers.SupName FROM Packages p INNER JOIN Packages_Products_Suppliers pps " +
                             "ON p.PackageId = pps.PackageId INNER JOIN Products_Suppliers ps " +
                             "ON pps.ProductSupplierId = ps.ProductSupplierId INNER JOIN Products ON " +
                             "ps.ProductId = Products.ProductId INNER JOIN Suppliers ON " +
                             "ps.SupplierId = Suppliers.SupplierId WHERE p.PackageId = " +  packageId1 + "order by Products.ProdName");	*/
					/*SELECT column_name(s)
					FROM table1
					INNER JOIN table2
					ON table1.column_name=table2.column_name;*/
			System.out.print("the editpackageId selected is : " + editpackageId);
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info4 = new DefaultListModel();
			while (rs.next()) {
				info4.addElement("Product Id :" + rs.getString("ProductId") + "   "
						+ "Product Name :" + rs.getString("ProdTypeName") + "   "
						+ "Supply Name :" + rs.getString("SupName"));				
				i = i + 1;
			}
			LstPackageCurrent.setModel(info4);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private void filllist5() {
		//targetID = getPkgId(x);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			//Integer x = Integer.valueOf(pkgId);
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("SELECT p.ProductId, t.ProdTypeName, s.SupName "
					+ "FROM products AS p "
					+ "INNER JOIN producttypes AS t "
					+ "INNER JOIN suppliers AS s "
					+ "ON t.ProductTypeId=p.ProductTypeId AND s.SupplierId=p.SupplierId "
					+ "ORDER BY p.ProductId");
					/*"SELECT  pps.ProductSupplierId, Products.ProductId, Products.ProdName, " +
                            "Suppliers.SupplierId, Suppliers.SupName FROM Packages p INNER JOIN Packages_Products_Suppliers pps " +
                             "ON p.PackageId = pps.PackageId INNER JOIN Products_Suppliers ps " +
                             "ON pps.ProductSupplierId = ps.ProductSupplierId INNER JOIN Products ON " +
                             "ps.ProductId = Products.ProductId INNER JOIN Suppliers ON " +
                             "ps.SupplierId = Suppliers.SupplierId WHERE p.PackageId = " +  packageId1 + "order by Products.ProdName");	*/
					/*SELECT column_name(s)
					FROM table1
					INNER JOIN table2
					ON table1.column_name=table2.column_name;*/
			System.out.print("the editpackageId selected is : " + editpackageId);
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info4 = new DefaultListModel();
			while (rs.next()) {
				info4.addElement("Product Id :" + rs.getString("ProductId") + "   "
						+ "Product Name :" + rs.getString("ProdTypeName") + "   "
						+ "Supply Name :" + rs.getString("SupName"));
				i = i + 1;
			}
			LstPackageUnselect.setModel(info4);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
	}
}
