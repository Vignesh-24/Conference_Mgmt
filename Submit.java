package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Submit extends JFrame {

	private JPanel contentPane;
	private  int id;
	private String date;
	


	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Submit(int id,String date) {
		this.date=date;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdeaDescription = new JLabel("Idea Description");
		lblIdeaDescription.setBounds(27, 72, 97, 14);
		contentPane.add(lblIdeaDescription);
		
		JTextArea desc = new JTextArea();
		desc.setBounds(144, 35, 247, 88);
		contentPane.add(desc);
		
		JButton btnUploadFile = new JButton("Upload file");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j=new JFileChooser();
				j.showOpenDialog(null); 
			    btnUploadFile.hide();
			    JLabel j1=new JLabel("File Upload Sucessfull");
			    j1.setBounds(27, 215, 200, 23);
				contentPane.add(j1);
			}
			
		});
		btnUploadFile.setBounds(27, 215, 97, 23);
		contentPane.add(btnUploadFile);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setVisible(false);
					String user=login.username;
					if(submission(user,desc.getText(),id,date))
					new Payment("user").setVisible(true);
					else
						JOptionPane.showMessageDialog(null,"Error in Submission");
				}catch(Exception e)
				{
					System.out.print(e);
				}
				
			}
		});
		btnSubmit.setBounds(299, 215, 89, 23);
		contentPane.add(btnSubmit);
	}
	private boolean submission(String user,String des,int id,String date)
	{
		try {
		Db.dbConnection();
		Db.stmt=Db.con.createStatement();
	    Db.stmt.executeUpdate("Insert into submissions values('"+user+"','"+des+"','"+id+"','"+date+"','pending')");
		
		Db.stmt.executeUpdate("Update bookings set available=available-1 where id='"+id+"' and date='"+date+"'");
		return true;
		/* This Function will return true only if user is registered.. else foreign key constraints will fail and return false
		 * please copy paste this "Alter table submissions add foreign key(user) references user(userid);" in sql
		 */
		}catch(Exception e) {
		return false;}
	}
}
