package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Review_topic extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Review_topic frame = new Review_topic();
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
	public Review_topic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectTopic = new JLabel("Select topic");
		lblSelectTopic.setBounds(180, 41, 92, 55);
		contentPane.add(lblSelectTopic);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Select--"}));
		comboBox.setBounds(126, 107, 182, 20);
		try {
			Db.dbConnection();
			Db.stmt=Db.con.createStatement();
			Db.res=Db.stmt.executeQuery("Select distinct topic from bookings where user='"+login.username+"'");
			Db.res.beforeFirst();
			while(Db.res.next())
				comboBox.addItem(Db.res.getString("topic"));
		}catch(Exception e)
		{
			System.out.println(e);
		}
		contentPane.add(comboBox);
		
		JButton btnGetInfo = new JButton("Get Info");
		btnGetInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				new Review(comboBox.getSelectedItem().toString()).setVisible(true);
			}
		});
		btnGetInfo.setBounds(320, 216, 89, 23);
		contentPane.add(btnGetInfo);
	}

}
