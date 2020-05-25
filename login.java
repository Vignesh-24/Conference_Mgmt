package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class login extends JFrame {

	protected static String username = null;
	private JPanel contentPane;
	private JTextField user;
	private JTextField pass;
	//public static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(112, 79, 93, 23);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(112, 34, 93, 14);
		contentPane.add(lblUsername);
		
		user = new JTextField();
		user.setBounds(228, 31, 121, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(228, 80, 121, 20);
		contentPane.add(pass);
		pass.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(112, 135, 93, 14);
		contentPane.add(lblType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Select--", "User", "Organiser"}));
		comboBox.setBounds(228, 132, 121, 20);
		contentPane.add(comboBox);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String type=comboBox.getSelectedItem().toString();
					username=user.getText();
					if(authorize(username,pass.getText(),type)) {
				setVisible(false);
				if(type.equals("Organiser"))
				new Organiser().setVisible(true);
				else
					new User().setVisible(true);}
					else
						JOptionPane.showMessageDialog(null, "Invalid userid/password");}catch(Exception e) {System.out.println(e);}
			}
		});
		btnlogin.setBounds(116, 196, 89, 23);
		contentPane.add(btnlogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Register().setVisible(true);
	
			}
		});
		btnRegister.setBounds(228, 196, 89, 23);
		contentPane.add(btnRegister);
	
	}
	public boolean authorize(String user,String passw,String type) throws SQLException
	{
		Db.dbConnection();
		Db.stmt=Db.con.createStatement();
		Db.res=Db.stmt.executeQuery("Select password from user where userid='"+user+"' and type='"+type+"'");
		Db.res.next();
		if(Db.res.getString("password").equals(passw))
		{
			return true;
		}
		return false;
	}
}
