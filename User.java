package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;

public class User extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Object[][] data=null;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public User() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		  String[] columns = new String[] {
				  "Hallid","Date","Time","Topic","Available"
			};

	  
	  Object[][] data = getEmployeeDetails();
	  contentPane.setLayout(null);
	  table = new JTable(data,columns);
	  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		JScrollPane pane=new JScrollPane(table);
		pane.setBounds(5, 0, 424, 234);
		contentPane.add(pane);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				  int rowIndex = table.getSelectedRow();
				  String date=table.getValueAt(rowIndex, 1).toString();
				  int id=(int) table.getValueAt(rowIndex, 0);
				  new Submit(id,date).setVisible(true);
			     // int colIndex = table.getSelectedColumn();
				  
			}
		});
		btnNewButton.setBounds(295, 279, 112, 23);
		contentPane.add(btnNewButton);
		
		JButton btnViewSubmisions = new JButton("View Submisions");
		btnViewSubmisions.setBounds(43, 279, 134, 23);
		contentPane.add(btnViewSubmisions);
		
	
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));

		
	}
	private int getRowCount(ResultSet rs) {

		try {
			
			if(rs != null) {
				
				rs.last();
				
				return rs.getRow(); 
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return 0;
	}
	private Object[][] getEmployeeDetails()
	{
		  try {
				
				Db.dbConnection();
				Db.stmt=Db.con.createStatement();
				Db.res=Db.stmt.executeQuery("Select * from bookings where status='no'");
				int row=0,j;
				  int r=getRowCount(Db.res);
				 // int c=getColumnCount(Db.res);
				  data = new Object[r][5];
					Db.res.beforeFirst();
				while(Db.res.next())
				{
					
				j=0;
				
					data[row][j++]=Db.res.getInt("id");
					 //comboBox.addItem(j);
					data[row][j++]=Db.res.getString("date");
					data[row][j++]=Db.res.getString("time");
					data[row][j++]=Db.res.getString("topic");
					data[row][j++]=Db.res.getInt("available");
				
					row++;
					
				}
				
	}
		  catch(Exception e) {System.out.println(e);}
		  return data;
	}
	private int getColumnCount(ResultSet rs) {

		try {

			if(rs != null)
				return rs.getMetaData().getColumnCount();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}
}
		 
	
		
	
 

