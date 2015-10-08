import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PackageList extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JButton btnAddPkg;
	private JButton btnEditPkg;
	private JButton btnDeletePkg;
	private JButton btnExitPkg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageList frame = new PackageList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	private JScrollPane scrollPane;
	 
	public PackageList() 
	{
		initialize();
		getPackageList();
	}
		
	private void getPackageList() 
	{
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try
		{

			//Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("select * from packages");
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info = new DefaultListModel();
			
			while (rs.next())
			{
				 info.addElement("ID :" + rs.getInt("packageId") +"   "
			      + "Package Name: " + rs.getString("pkgName") +"  "
			       + "Start Date: " + rs.getDate("pkgStartDate") + "  "
			       + "End Date: " + rs.getDate("pkgEndDate") + "  "
			       + "Description: " + rs.getString("pkgDesc") + "  "
			       + "Base Price: " + rs.getString("pkgBasePrice") + "  "
			       + "Agency Commission: " + rs.getString("pkgAgencyCommission")); 
				 System.out.println("/n");
				 i = i + 1;
				 
			}
			 list.setModel(info);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
		

	}

	
		public void initialize()
		{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 930, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 888, 292);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		//scrollPane.add(list);
		
		btnAddPkg = new JButton("Add");
		btnAddPkg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
				PackageGUI pkgObj = new PackageGUI();
				pkgObj.setVisible(true);
				
			}
		});
		btnAddPkg.setBounds(71, 331, 97, 25);
		contentPane.add(btnAddPkg);
		
		btnEditPkg = new JButton("Edit");
		btnEditPkg.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			/*PackageGUI pkgObj = new PackageGUI();
			pkgObj.setVisible(true);*/
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn = null;
			try
			{
				conn = TravelExpertsDB.getConnection();
				String packagedata = (String)list.getSelectedValue();
				String selectedpkgId = packagedata.substring(4,5);
				//System.out.println("selectedpkgId : " +  selectedpkgId);
				int packageid = 0; // Regex goes here
				String sql = "select * from packages where packageId= '" + selectedpkgId + "'";
				
				stmt = conn.prepareStatement(sql);
				
				PackageGUI pkgIdObj = new PackageGUI();
				pkgIdObj.getPkgId(selectedpkgId);
				pkgIdObj.setVisible(true);
				
				stmt.execute();
				
				stmt.close();
				
			}
			catch(Exception e1){
				e1.printStackTrace();
				System.err.println("Error: " +e1.getMessage());
			}
		}
		});
		btnEditPkg.setBounds(267, 331, 97, 25);
		contentPane.add(btnEditPkg);
		
		btnDeletePkg = new JButton("Delete");
		btnDeletePkg.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
				PreparedStatement stmt = null;
				Connection conn = null;
				try {
					conn = TravelExpertsDB.getConnection();
					
					String packageData = (String)list.getSelectedValue();
					String pkgId = packageData.substring(4,6);

					String sql = "DELETE from packages where PackageId= '" + pkgId + "'";
					stmt = conn.prepareStatement(sql);
			
                    stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Package Deleted");

					stmt.close();
					getPackageList();
					
				}
				catch(Exception e1){
					e1.printStackTrace();
					System.err.println("Error: " +e1.getMessage());
				}
			}
		});
		btnDeletePkg.setBounds(453, 331, 97, 25);
		contentPane.add(btnDeletePkg);
		
		btnExitPkg = new JButton("Exit");
		btnExitPkg.setBounds(636, 331, 97, 25);
		contentPane.add(btnExitPkg);
		//btnExit = new JButton("Exit");
		btnExitPkg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.exit(0);
				dispose();
			}
		});
	}
}
