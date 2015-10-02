import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNav1 = new JButton("Agent Manager");
		btnNav1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agentpart1 mgmtWindow = new Agentpart1();
				mgmtWindow.frame.setVisible(true);
			}
		});
		panel.add(btnNav1);
		
		JButton btnNav2 = new JButton("Package Manager");
		btnNav2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PackageGUI mgmtWindow = new PackageGUI();
				mgmtWindow.setVisible(true);
			}
		});
		panel.add(btnNav2);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnExit);
		
	}

}
