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
import java.awt.event.ActionEvent;

public class Selecthall extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private JLabel lblSelectHallId;
  Object[][] data=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selecthall frame = new Selecthall();
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
	public Selecthall() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  String[] columns = new String[] {
				  "Hallid", "Room No", "Amount", "Capacity", "Ac", "venue"
			};

	   
	  Object[][] data = getEmployeeDetails();
	  table = new JTable(data,columns);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		JScrollPane pane=new JScrollPane(table);
		pane.setBounds(5, 0, 424, 253);
		contentPane.add(pane);
		
		comboBox = new JComboBox();
		comboBox.setBounds(122, 314, 77, 20);
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));

		try {
			Db.dbConnection();
			Db.stmt=Db.con.createStatement();
			Db.res=Db.stmt.executeQuery("Select id from hall");Db.res.beforeFirst();
			while(Db.res.next())
			comboBox.addItem(Db.res.getInt("id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		contentPane.add(comboBox);
	
		
		lblSelectHallId = new JLabel("Select Hall ID");
		lblSelectHallId.setBounds(21, 317, 91, 14);
		contentPane.add(lblSelectHallId);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new SeeHalldetails(comboBox.getSelectedItem().toString()).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnProceed.setBounds(300, 313, 89, 23);
		contentPane.add(btnProceed);
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
				Db.res=Db.stmt.executeQuery("Select * from hall");
				int row=0,j;
				  int r=getRowCount(Db.res);
				  int c=getColumnCount(Db.res);
				  data = new Object[r][c];
					Db.res.beforeFirst();
				while(Db.res.next())
				{
					
				j=0;
				
					data[row][j++]=Db.res.getInt("id");
					 //comboBox.addItem(j);
					data[row][j++]=Db.res.getInt("room");
					data[row][j++]=Db.res.getInt("amount");
					data[row][j++]=Db.res.getInt("capacity");
					data[row][j++]=Db.res.getString("ac");
					data[row][j++]=Db.res.getString("venue");
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
