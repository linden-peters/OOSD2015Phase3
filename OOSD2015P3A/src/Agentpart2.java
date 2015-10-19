//Chen Cui
//Oct 08, 2015
//Harve Peters

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
import javax.swing.ListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.*;

public class Agentpart2 extends JFrame {
	
	//define everything first
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
	public static ListModel model1;
	public static ListModel model2;

	/**
	 * Launch the application.
	 */

	//get the id from agentpart1 which is the user picked
	public void printAgentId(String part2){
		Integer x = Integer.valueOf(part2);
		targetID = x;
		secondtargetID = x;	
		filllist2();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		//connect the server, display everything from agents table with the agentid which is transfort from agentpart1
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("select * from agents where AgentId='" + x + "'");		
			stmt.executeQuery();		
			rs= stmt.getResultSet();
			
			//display all the information about the agent which we selected from agent1
			while (rs.next()){
				txtAgentFirstName.setText(rs.getString("AgtFirstName"));
				txtAgentMiddleInitial.setText(rs.getString("AgtMiddleInitial"));
				txtAgentLastName.setText(rs.getString("AgtLastName"));
				txtAgentPhone.setText(rs.getString("AgtBusPhone"));
				txtAgentEmail.setText(rs.getString("AgtEmail"));
				txtAgentPosition.setText(rs.getString("AgtPosition"));
				txtAgencyId.setText(rs.getString("AgencyId"));
			 }
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}	
	}	
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	
	//agentpart2 main application
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
		
		//save button, which is the most inportant button in this part
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//give two lists' value to two models.
				model1 = lstCurrentCustomer.getModel();
				model2 = lstUnselectCustomer.getModel();
				
				//if the model1 is not empty then run the for loop
				if(model1.getSize() > 0)
				{
					//put the agentid to the string
					StringBuffer subclausedlist = new StringBuffer();
					for(int i=0; i < model1.getSize(); i++)
					{
						if (subclausedlist.length() != 0)
						{
							//give the or in the sql command as a argument if it is not the end of the string.
							subclausedlist.append(" OR ");
						}
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						Connection conn = TravelExpertsDB.getConnection();
						String agentdata = (String)model1.getElementAt(i);
						//give the id number after id:
						Pattern pattern = Pattern.compile("^Id :([0-9]+)");
						Matcher matcher = pattern.matcher(agentdata);
						if (matcher.find()) {
						   System.out.println(matcher.group(1));
						}
						String id = matcher.group(1);
						subclausedlist.append("CustomerId=" + id);
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();		
						//update the agentid to the id number which are selected
						String query="Update customers set AgentId=" + targetID + " where "+ subclausedlist;
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();
						
						pst.close();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}				
				  }
				
				//similar like the model1.
				if(model2.getSize() > 0)
				  {
					StringBuffer subclausedlist2 = new StringBuffer();
					for(int i=0; i < model2.getSize(); i++)
					{				
						if (subclausedlist2.length() != 0)
						{
							subclausedlist2.append(" OR ");
						}
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						Connection conn = TravelExpertsDB.getConnection();
						String agentdata = (String)model2.getElementAt(i);
						
						Pattern pattern = Pattern.compile("^Id :([0-9]+)");
						Matcher matcher = pattern.matcher(agentdata);
						if (matcher.find()) {
						   System.out.println(matcher.group(1));
						}
						String id = matcher.group(1);											
						subclausedlist2.append("CustomerId=" + id);
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();		
						//update the agentid become null which is agentid were selected .
						String query="Update customers set AgentId = null  where "+ subclausedlist2;
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();
						
						pst.close();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
				
				//if the agentid is selected
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
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();						
						pst.close();
												
					}catch (Exception ex){
						ex.printStackTrace();
					}
				}
				//if the agentid is not selected, which is from agentpart1 when the user click the add button
				else if (targetID == 0)
				{
					System.out.println("TargetID = 0\n\tAdd Form");
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();
						//insert the values into the sql database
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
		
		//define cancel button
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
		
		lstUnselectCustomer = new JList();
		lstUnselectCustomer.setBounds(300, 288, 250, 169);
		contentPane.add(lstUnselectCustomer);
		lstUnselectCustomer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//define move up button
		JButton btnMoveUp = new JButton("Move up");
		btnMoveUp.setBounds(300, 239, 97, 25);
		contentPane.add(btnMoveUp);
		btnMoveUp.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
						DefaultListModel model1 = (DefaultListModel) lstUnselectCustomer.getModel();
						DefaultListModel model2 = (DefaultListModel) lstCurrentCustomer.getModel();
						Object[] selectedValues = lstUnselectCustomer.getSelectedValues();
						//if the val is not empty, model2 add the selected value and model1 will remove selected value
						for(Object val : selectedValues)
						{
							if (val != null) {
								model2.addElement(val);
								model1.removeElement(val);
							}
						}
					}
				}
				);
		
		//define pull down button, samilar like move up button
		JButton btnPullDown = new JButton("Pull down");
		btnPullDown.setBounds(453, 239, 97, 25);
		contentPane.add(btnPullDown);
		btnPullDown.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						DefaultListModel model1 = (DefaultListModel) lstCurrentCustomer.getModel();
						DefaultListModel model2 = (DefaultListModel) lstUnselectCustomer.getModel();
						Object[] selectedValues = lstCurrentCustomer.getSelectedValues();
						for(Object val : selectedValues)
						{
							if (val != null) {
								model2.addElement(val);
								model1.removeElement(val);
							}
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
		lstCurrentCustomer.setModel(new DefaultListModel());
		filllist3();
	}
	
	//show the customers who belong to the agent which was selected from agentpart1 into the list
	private void filllist2(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("select * from customers where AgentId='" + secondtargetID + "' ");	
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info2 = new DefaultListModel();
			
			while (rs.next()){
				 info2.addElement("Id :" + rs.getString("CustomerId") +"   "
			       + "FirstName :" + rs.getString("CustFirstName") +"   "
			       + "LastName :" + rs.getString("CustLastName") +"   "
						 );
				
				 i = i + 1;
			 }
			lstCurrentCustomer.setModel(info2);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
	}
	
	//show the customers who is not belong to any agent in the list
	private void filllist3(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("Select * from customers where AgentId is null ");	
			stmt.executeQuery();
			rs= stmt.getResultSet();
			int i = 0;
			DefaultListModel info2 = new DefaultListModel();
			
			while (rs.next()){
				 info2.addElement("Id :" + rs.getString("CustomerId") +"   "
			       + "FirstName :" + rs.getString("CustFirstName") +"   "
			       + "LastName :" + rs.getString("CustLastName") +"   "
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
