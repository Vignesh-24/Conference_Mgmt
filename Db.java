package confrence_mgmt;
import java.sql.*;
public class Db {
	protected static Connection con;
	protected static ResultSet res;
	protected static Statement stmt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MainCclass main=new MainCclass();
		new login().setVisible(true);
	}
	protected static void dbConnection() throws SQLException
	{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/conference","root","");
	}
}

	

