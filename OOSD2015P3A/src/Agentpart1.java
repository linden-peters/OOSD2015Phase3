import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Agentpart1 {

	public JFrame frame;
	public static Button btnAdd;
	public JButton btnExit;
	public JList jList1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agentpart1 window = new Agentpart1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	
	/**
	 * Create the application.
	 */
	public Agentpart1() {
		initialize();
		filllist();
	}
	
	private void filllist(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = TravelExpertsDB.getConnection();
			stmt = conn.prepareStatement("select * from agents");
			stmt.executeQuery();
			rs= stmt.getResultSet();
			
			
			
			int i = 0;
			DefaultListModel info = new DefaultListModel();
			
			
			
			while (rs.next()){
				 info.addElement("Id :" + rs.getString("AgentId") +"   "
			       + "FirstName :" + rs.getString("AgtFirstName") +"   "
			       + "MiddleInitial :" + rs.getString("AgtMiddleInitial") +"   "
			       + "LastName :" + rs.getString("AgtLastName") +"   "
			       + "BusPhone :" + rs.getString("AgtBusPhone") +"   "
			       + "Email :" + rs.getString("AgtEmail") +"   "
			       + "Position :" + rs.getString("AgtPosition") +"   "
			       + "AgencyId :" + rs.getString("AgencyId")
						 );
				
				 i = i + 1;
			 }
			
			 jList1.setModel(info);
			// System.out.print("the id u selected is : " + i);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Error: " +e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1083, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAgentsInformation = new JLabel("Agents' Information");
		lblAgentsInformation.setBounds(245, 14, 121, 16);
		frame.getContentPane().add(lblAgentsInformation);
		
		jList1 = new JList();
		jList1.setBounds(12, 57, 1041, 314);
		frame.getContentPane().add(jList1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					
				}
				frame.dispose();
	           Agentpart2 tc = new Agentpart2();
	           tc.setVisible(true);
			}
		});
		btnAdd.setBounds(12, 398, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					

						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = TravelExpertsDB.getConnection();
						String agentdata = (String)jList1.getSelectedValue();


						Pattern pattern = Pattern.compile("^Id :([0-9]+)");
						Matcher matcher = pattern.matcher(agentdata);
						if (matcher.find()) {
						    System.out.println(matcher.group(1));
						}
						String part1 = matcher.group(1);
						
					
						
						int agentid = 0; // Regex goes here
						//System.out.println("ParsedID: " + part);
						String b = "select * from agents where AgentId= '" + part1 + "'";
						
					    
						
						PreparedStatement pst=conn.prepareStatement(b);
						
						Agentpart2 sendAgentId = new Agentpart2();
						sendAgentId.printAgentId(part1);
						sendAgentId.setVisible(true);
						
						//jList1.getSelectedValue()
	                    pst.execute();
						
						//JOptionPane.showMessageDialog(null, "Data Deleted");
						
						pst.close();
						//filllist();
						
					}catch(Exception e1){
						e1.printStackTrace();
						System.err.println("Error: " +e1.getMessage());
					}
				   
				   
				   frame.dispose();
		           //Agentpart2 tc = new Agentpart2();
		           //tc.setVisible(true);
			}
		});
		btnEdit.setBounds(163, 398, 97, 25);
		frame.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
				//	Locale row = JList.getDefaultLocale();
				//	String AgentId_=(JList.getDefaultLocale().getVariant().toString());
					
				//	String query="select * from agents where AgentId='"+AgentId_+"' ";	
					
					/*String selection=(JList.WHEN_FOCUSED);
					String query="select * from agents where "+selection+"=? ";	*/

					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = TravelExpertsDB.getConnection();
					String agentdata = (String)jList1.getSelectedValue();
					//System.out.println("Deleting: " + agentdata);
					String part = agentdata.substring(4,6);
					
				
					
					/*int agentid = 0; // Regex goes here
					System.out.println("ParsedID: " + part);*/
					String b = "delete from agents where AgentId= '" + part + "'";
					PreparedStatement pst=conn.prepareStatement(b);
			
					
					//jList1.getSelectedValue()
                    pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					
					pst.close();
					filllist();
					
				}catch(Exception e1){
					e1.printStackTrace();
					System.err.println("Error: " +e1.getMessage());
				}
			}
		});
		btnDelete.setBounds(309, 398, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.exit(0);
				frame.dispose();
			}
		});
		btnExit.setBounds(449, 398, 97, 25);
		frame.getContentPane().add(btnExit);
	}
}
