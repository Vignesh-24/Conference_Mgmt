package confrence_mgmt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class Review extends JFrame{

	private JPanel contentPane;
	private JTable table;
   Object[][] data=null;
  String[] columns;
  private JLabel lab;
  private JButton btnBack;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Review(String topic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  columns = new String[] {
				 "id","date","applicant id"
			};
	data=getEmployeeDetails(topic);
	  table = new JTable(data,columns);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		JScrollPane pane=new JScrollPane(table);
		pane.setBounds(10, 41, 414, 253);
		contentPane.add(pane);
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));

		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					setVisible(false);
					int row=table.getSelectedRow();
					int id=(int) table.getValueAt(row, 0);
					String date=table.getValueAt(row, 1).toString();
					
					String user=table.getValueAt(row, 2).toString();
					new Evaluate(id,date,user).setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnProceed.setBounds(316, 336, 89, 23);
		contentPane.add(btnProceed);
		
		lab = new JLabel(topic);
		lab.setBounds(140, 11, 187, 14);
		contentPane.add(lab);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Review_topic().setVisible(true);
			}
			
		});
		btnBack.setBounds(21, 336, 89, 23);
		contentPane.add(btnBack);
		
	
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
	private Object[][] getEmployeeDetails(String topic)
	{
		  try {
				String user=login.username;
				Db.dbConnection();
				Db.stmt=Db.con.createStatement();
				Db.res=Db.stmt.executeQuery("Select * from submissions where status='pending' and id in (select id from bookings where user='"+user+"' and topic='"+topic+"') and date in (select date from bookings where user='"+user+"' and topic='"+topic+"')");
				int row=0,j;
				  int r=getRowCount(Db.res);
				  int c=getColumnCount(Db.res);
				  data = new Object[r][3];
					Db.res.beforeFirst();
				while(Db.res.next())
				{
					
				j=0;
				
					data[row][j++]=Db.res.getInt("id");
					 //comboBox.addItem(j);
					data[row][j++]=Db.res.getString("date");
					data[row][j++]=Db.res.getString("user");
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
