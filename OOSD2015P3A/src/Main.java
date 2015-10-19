/*
 * Main.java - Application Launcher
 * Author: Linden Peters
 * Written: 2015/10/06
 */
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;

public class Main {

	private JFrame frmTravelExperts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmTravelExperts.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTravelExperts = new JFrame();
		frmTravelExperts.setTitle("Travel Experts");
		frmTravelExperts.setBounds(100, 100, 450, 300);
		frmTravelExperts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frmTravelExperts.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "No user-editable settings have been implemented yet.", "Settings", JOptionPane.WARNING_MESSAGE);
			}
		});
		mnEdit.add(mntmSettings);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Travel Experts Management Interface\n© 2015 Team 5. All rights reserved.", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		
		JPanel panelN = new JPanel();
		JPanel panelC = new JPanel(new GridLayout(2,1));
		JPanel panelS = new JPanel();
		frmTravelExperts.getContentPane().add(panelN, BorderLayout.NORTH);
		frmTravelExperts.getContentPane().add(panelC, BorderLayout.CENTER);
		frmTravelExperts.getContentPane().add(panelS, BorderLayout.SOUTH);
		
		JLabel lblTitle = new JLabel("Management Interface");
		lblTitle.setFont(new Font("Stencil", Font.PLAIN, 20));
		panelN.add(lblTitle);
		
		JButton btnNav1 = new JButton("Agent Manager");
		btnNav1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agentpart1 mgmtWindow = new Agentpart1();
				mgmtWindow.setVisible(true);
			}
		});
		panelC.add(btnNav1);
		
		JButton btnNav2 = new JButton("Package Manager");
		btnNav2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PackageList mgmtWindow = new PackageList();
				mgmtWindow.setVisible(true);
			}
		});
		panelC.add(btnNav2);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelS.add(btnExit);
	}
}
