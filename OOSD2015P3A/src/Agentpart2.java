import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;




public class Agentpart2 extends JFrame {

	
	
	private static final String String = null;
	private int targetID = 0;
	private int secondtargetID = 0;
	private JPanel contentPane;
	public JTextField txtAgentFirstName;
	public JTextField txtAgentPhone;
	public JTextField txtAgentEmail;
	public JTextField txtAgentPosition;
	public JTextField txtAgentMiddleInitial;
	public JTextField txtAgentLastName;
	public JTextField txtAgencyId;
	
	public static JList lstCurrentCustomer;
	public static JList lstUnselectCustomer;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		
		Agentpart1 callClass = new Agentpart1();
		callClass.initialize();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agentpart2 frame = new Agentpart2();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	
	
	
	public void printAgentId(String part2){
		//System.out.println("printAgentId(): " + part2);
		//System.out.print("the id u selected is : " + part2);
		//int foo = Integer.parseInt(part2);
		Integer x = Integer.valueOf(part2);
		targetID = x;
		secondtargetID = x;
		System.out.print("the id u selected is : " + secondtargetID);
		
		filllist2();
		filllist3();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			//System.out.println("select * from agents where AgentId='" + x + "'");
			stmt = conn.prepareStatement("select * from agents where AgentId='" + x + "'");
			
			
			
			stmt.executeQuery();
			
			rs= stmt.getResultSet();
			
			
			//int i = 0;
			//DefaultListModel info = new DefaultListModel();
			
			while (rs.next()){
				//System.out.println("Getting Next Entry");
				//String FirstName = rs.getString("AgtFirstName");
				txtAgentFirstName.setText(rs.getString("AgtFirstName"));
				//System.out.println("FirstName = " + FirstName);
				txtAgentMiddleInitial.setText(rs.getString("AgtMiddleInitial"));
				txtAgentLastName.setText(rs.getString("AgtLastName"));
				txtAgentPhone.setText(rs.getString("AgtBusPhone"));
				txtAgentEmail.setText(rs.getString("AgtEmail"));
				txtAgentPosition.setText(rs.getString("AgtPosition"));
				txtAgencyId.setText(rs.getString("AgencyId"));
				
				
				
				
				// i = i + 1;
			 }
			//System.out.print("the id u selected is : " + rs);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
		
		
	
	}
	
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public Agentpart2() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAgentName = new JLabel("Agent First Name");
		lblAgentName.setBounds(12, 77, 105, 16);
		contentPane.add(lblAgentName);
		
		JLabel lblAgentBusPhone = new JLabel("Agent Bus Phone");
		lblAgentBusPhone.setBounds(12, 192, 105, 16);
		contentPane.add(lblAgentBusPhone);
		
		JLabel lblAgentEmail = new JLabel("Agent Email");
		lblAgentEmail.setBounds(12, 245, 81, 16);
		contentPane.add(lblAgentEmail);
		
		JLabel lblAgentPosition = new JLabel("Agent Position");
		lblAgentPosition.setBounds(12, 280, 81, 16);
		contentPane.add(lblAgentPosition);
		
		txtAgentFirstName = new JTextField();
		txtAgentFirstName.setColumns(10);
		txtAgentFirstName.setBounds(150, 71, 116, 22);
		contentPane.add(txtAgentFirstName);
		
		txtAgentPhone = new JTextField();
		txtAgentPhone.setColumns(10);
		txtAgentPhone.setBounds(150, 186, 116, 22);
		contentPane.add(txtAgentPhone);
		
		txtAgentEmail = new JTextField();
		txtAgentEmail.setColumns(10);
		txtAgentEmail.setBounds(150, 239, 116, 22);
		contentPane.add(txtAgentEmail);
		
		txtAgentPosition = new JTextField();
		txtAgentPosition.setColumns(10);
		txtAgentPosition.setBounds(150, 274, 116, 22);
		contentPane.add(txtAgentPosition);
		
		JCheckBox chckbxActive = new JCheckBox("Active");
		chckbxActive.setBounds(102, 394, 73, 25);
		contentPane.add(chckbxActive);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (targetID > 0)
				{
					System.out.println("TargetID = " + targetID + "\n\tEdit Form");
					try {
						
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();
						
						
						String query="Update agents set AgtFirstName='"+txtAgentFirstName.getText()+"' , "
								+ "AgtMiddleInitial='"+txtAgentMiddleInitial.getText()+"'  ,"
										+ "AgtLastName='"+txtAgentLastName.getText()+"' ,"
										+ "AgtBusPhone='"+txtAgentPhone.getText()+"' ," 
										+ "AgtEmail='"+txtAgentEmail.getText()+"' ,"
										+ "AgtPosition='"+txtAgentPosition.getText()+"' where AgentId='" + targetID + "' ";								
						
						//System.out.println("AgtFirstName = " + txtAgentFirstName );
						//System.out.println("SQL: " + query);
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();
						
						pst.close();
						
						
					}catch (Exception ex){
						ex.printStackTrace();
					}
				}
				else if (targetID == 0)
				{
					System.out.println("TargetID = 0\n\tAdd Form");
					try{

						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();					
						PreparedStatement pst=conn.prepareStatement("insert into agents (AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId) values (?, ?, ?, ?, ?, ?, ?)");
					
						pst.setString(1, txtAgentFirstName.getText() );
						pst.setString(2, txtAgentMiddleInitial.getText() );
						pst.setString(3, txtAgentLastName.getText() );
						pst.setString(4, txtAgentPhone.getText() );
						pst.setString(5, txtAgentEmail.getText() );
						pst.setString(6, txtAgentPosition.getText() );
						pst.setString(7, txtAgencyId.getText() );
						
						pst.execute();
						
					}catch(Exception e1){
						e1.printStackTrace();
						System.err.println("Error: " +e1.getMessage());
					}
				}
				else
				{
					System.out.println("TargetID is negative\n\tSomething has gone horribly wrong.");
				}

				
				
			}
		});
		
		
		
		
		btnSave.setBounds(31, 432, 97, 25);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		btnCancel.setBounds(167, 432, 97, 25);
		contentPane.add(btnCancel);
		
		lstCurrentCustomer = new JList();
		lstCurrentCustomer.setBounds(300, 37, 250, 169);
		contentPane.add(lstCurrentCustomer);
		lstCurrentCustomer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//add(new JScrollPane());
		
		
		lstUnselectCustomer = new JList();
		lstUnselectCustomer.setBounds(300, 288, 250, 169);
		contentPane.add(lstUnselectCustomer);
		lstUnselectCustomer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//lstUnselectCustomer.setVisibleRowCount(100);
		
		JButton btnMoveUp = new JButton("Move up");
		btnMoveUp.setBounds(300, 239, 97, 25);
		contentPane.add(btnMoveUp);
		btnMoveUp.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						lstCurrentCustomer.setListData(lstUnselectCustomer.getSelectedValues());
						DefaultListModel model = (DefaultListModel) lstUnselectCustomer.getModel();
						int selectedIndex =lstUnselectCustomer.getSelectedIndex();
						if (selectedIndex != -1) {
						    model.remove(selectedIndex);
						}
					}
				}
				);
		
		JButton btnPullDown = new JButton("Pull down");
		btnPullDown.setBounds(453, 239, 97, 25);
		contentPane.add(btnPullDown);
		btnPullDown.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						lstUnselectCustomer.setListData(lstCurrentCustomer.getSelectedValues());
						//lstCurrentCustomer.remove(lstCurrentCustomer.getSelectedValues());
						DefaultListModel model = (DefaultListModel) lstCurrentCustomer.getModel();
						int selectedIndex = lstCurrentCustomer.getSelectedIndex();
						if (selectedIndex != -1) {
						    model.remove(selectedIndex);
						}
					}
				}
				);
		
		
		JLabel lblAgentMiddleInitial = new JLabel("Agent Middle Initial");
		lblAgentMiddleInitial.setBounds(12, 106, 116, 16);
		contentPane.add(lblAgentMiddleInitial);
		
		txtAgentMiddleInitial = new JTextField();
		txtAgentMiddleInitial.setColumns(10);
		txtAgentMiddleInitial.setBounds(148, 106, 116, 22);
		contentPane.add(txtAgentMiddleInitial);
		
		JLabel lblAgentLastName = new JLabel("Agent Last Name");
		lblAgentLastName.setBounds(12, 143, 116, 16);
		contentPane.add(lblAgentLastName);
		
		txtAgentLastName = new JTextField();
		txtAgentLastName.setColumns(10);
		txtAgentLastName.setBounds(150, 141, 116, 22);
		contentPane.add(txtAgentLastName);
		
		JLabel lblAgencyId = new JLabel("Agency Id");
		lblAgencyId.setBounds(12, 324, 81, 16);
		contentPane.add(lblAgencyId);
		
		txtAgencyId = new JTextField();
		txtAgencyId.setColumns(10);
		txtAgencyId.setBounds(150, 321, 116, 22);
		contentPane.add(txtAgencyId);
		
		
		
		//filllist2();
		
	}
	
	private void filllist2(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("select * from customers where AgentId='" + secondtargetID + "' ");	
			//System.out.print("the stmt selected is : " + stmt);
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info2 = new DefaultListModel();
			
			while (rs.next()){
				 info2.addElement("Id :" + rs.getString("CustomerId") +"   "
			       + "FirstName :" + rs.getString("CustFirstName") +"   "
			       //+ "MiddleInitial :" + rs.getString("AgtMiddleInitial") +"   "
			       + "LastName :" + rs.getString("CustLastName") +"   "
			       //+ "BusPhone :" + rs.getString("AgtBusPhone") +"   "
			       //+ "Email :" + rs.getString("AgtEmail") +"   "
			       //+ "Position :" + rs.getString("AgtPosition") +"   "
			       //+ "AgencyId :" + rs.getString("AgencyId")
						 );
				
				 i = i + 1;
			 }
			lstCurrentCustomer.setModel(info2);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
	}
	
	private void filllist3(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("Select * from customers where AgentId is null ");	
			//System.out.print("the stmt selected is : " + stmt);
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info2 = new DefaultListModel();
			
			while (rs.next()){
				 info2.addElement("Id :" + rs.getString("CustomerId") +"   "
			       + "FirstName :" + rs.getString("CustFirstName") +"   "
			       //+ "MiddleInitial :" + rs.getString("AgtMiddleInitial") +"   "
			       + "LastName :" + rs.getString("CustLastName") +"   "
			       //+ "BusPhone :" + rs.getString("AgtBusPhone") +"   "
			       //+ "Email :" + rs.getString("AgtEmail") +"   "
			       //+ "Position :" + rs.getString("AgtPosition") +"   "
			       //+ "AgencyId :" + rs.getString("AgencyId")
						 );
				
				 i = i + 1;
			 }
			lstUnselectCustomer.setModel(info2);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
	}
}
