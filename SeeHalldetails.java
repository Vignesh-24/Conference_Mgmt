package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeeHalldetails extends JFrame {

	private JPanel contentPane;
	private JTextField topic;
    private ResultSet res1;
    private Statement stmt1;
    private JTextField time;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SeeHalldetails(String id) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Db.dbConnection();
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Select--"}));
		try {
			Db.stmt=Db.con.createStatement();
			String st="yes";

			Db.res=Db.stmt.executeQuery("Select * from hall where id='"+id+"'");
			
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/conference","root","lufi2412");
			stmt1=con1.createStatement();
			ResultSet res1=stmt1.executeQuery("Select date from bookings where id='"+id+"' and status='"+st+"'");
			
			Db.res.next();
			while(res1.next())
				comboBox.addItem(res1.getString("date"));
		
		JLabel lblRoomNo = new JLabel("Room no");
		lblRoomNo.setBounds(56, 35, 83, 14);
		contentPane.add(lblRoomNo);
		
		JLabel lblTopic = new JLabel("Topic");
		lblTopic.setBounds(56, 70, 83, 14);
		contentPane.add(lblTopic);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(229, 35, 64, 14);
		contentPane.add(lblAmount);
		
		JLabel lblVenue = new JLabel("Venue");
		lblVenue.setBounds(56, 142, 83, 14);
		contentPane.add(lblVenue);
		
		JLabel lblAvailableDates = new JLabel("Available Dates");
		lblAvailableDates.setBounds(56, 179, 136, 14);
		contentPane.add(lblAvailableDates);
		
		
		comboBox.setBounds(222, 176, 129, 20);
		
		contentPane.add(comboBox);
		
		JLabel room = new JLabel(String.valueOf(Db.res.getInt("room")));
		room.setBounds(145, 35, 45, 14);
		contentPane.add(room);
		
		JLabel topic_1 = new JLabel(String.valueOf(Db.res.getInt("amount")));
		topic_1.setBounds(306, 35, 45, 14);
		contentPane.add(topic_1);
		
		JLabel venue = new JLabel(Db.res.getString("addr")+" , "+Db.res.getString("venue"));
		venue.setBounds(222, 142, 181, 14);
		contentPane.add(venue);
		
		topic = new JTextField();
		topic.setBounds(215, 67, 136, 20);
		contentPane.add(topic);
		topic.setColumns(10);
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(56, 104, 46, 14);
		contentPane.add(lblTime);
		
		time = new JTextField();
		time.setBounds(215, 101, 136, 20);
		contentPane.add(time);
		time.setColumns(10);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Db.dbConnection();
					Db.stmt=Db.con.createStatement();
					String u=login.username;
					Db.stmt.executeUpdate("Update bookings set status='no',time='"+time.getText()+"',user='"+u+"',topic='"+topic.getText()+"' where id='"+id+"' and date='"+comboBox.getSelectedItem().toString()+"'");
					new Payment("org").setVisible(true);
					setVisible(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnBook.setBounds(263, 227, 89, 23);
		contentPane.add(btnBook);
		
		
		}catch(Exception e) {System.out.println(e);System.out.println("hi");}
		
	}
	
}
