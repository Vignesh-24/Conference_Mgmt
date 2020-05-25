package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Evaluate extends JFrame {

	private JPanel contentPane;
   String user,date;
   int id;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Evaluate(int id,String date,String user) {
		this.id=id;
		this.date=date;
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(32, 54, 104, 14);
		contentPane.add(lblDescription);
		
		JTextArea des = new JTextArea();
		des.setBounds(162, 49, 219, 90);
		try {
			Db.dbConnection();
			Db.stmt=Db.con.createStatement();
			Db.res=Db.stmt.executeQuery("Select des from submissions where user='"+user+"' and date='"+date+"' and id='"+id+"'");
			Db.res.beforeFirst();
			Db.res.next();
			des.setText(Db.res.getString("des"));
		    des.setEditable(false);
		}catch(Exception ex)
		{
			
		}
		contentPane.add(des);
		
		JButton btnOpenFile = new JButton("Open file");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j=new JFileChooser();
				j.showOpenDialog(null);
				
			}
		});
		btnOpenFile.setBounds(29, 204, 89, 23);
		contentPane.add(btnOpenFile);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Db.dbConnection();
					Db.stmt=Db.con.createStatement();
					Db.stmt.executeUpdate("Update submissions set status='Accepted' where user='"+user+"' and date='"+date+"' and id='"+id+"'");
					setVisible(false);
					new Review_topic().setVisible(true);
				}catch(Exception ex)
				{
					
				}
			}
		});
		btnAccept.setBackground(Color.GREEN);
		btnAccept.setBounds(292, 204, 89, 23);
		contentPane.add(btnAccept);
		
		JButton btnReject = new JButton("Reject");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Db.dbConnection();
					Db.stmt=Db.con.createStatement();
					Db.stmt.executeUpdate("Update submissions set status='Rejected' where user='"+user+"' and date='"+date+"' and id='"+id+"'");
					setVisible(false);
					new Review_topic().setVisible(true);
				}catch(Exception ex)
				{
					
				}
			}
			
		});
		btnReject.setBackground(Color.RED);
		btnReject.setBounds(167, 204, 89, 23);
		contentPane.add(btnReject);
	}
}
