package register;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRegisterLoader implements RegisterLoader {
	public static final String URL = "jdbc:mysql://localhost/school";
	public static final String USER = "root";
	public static final String PASSWORD = "MAT.246.mar.";
	public static final String DROP = "DROP TABLE IF EXISTS list";
	public static final String CREATE = "CREATE TABLE list (name VARCHAR(32) NOT NULL, phoneNumber VARCHAR(10))";
	public static final String INSERT = "INSERT INTO list (name, phoneNumber) VALUES (?, ?)";
	public static final String SELECT = "SELECT * FROM list";

	public DatabaseRegisterLoader() {

	}

	@Override
	public void store(Register register) throws FileNotFoundException, IOException {

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement stmt = con.prepareStatement(DROP);
			stmt.executeUpdate();
			stmt.close();
			PreparedStatement stmt1 = con.prepareStatement(CREATE);
			stmt1.executeUpdate();
			stmt1.close();
			PreparedStatement stmt2 = con.prepareStatement(INSERT);
			for (int i = 0; i < register.getCount(); i++) {

				stmt2.setString(1, register.getPerson(i).getName());
				stmt2.setString(2, register.getPerson(i).getPhoneNumber());

				stmt2.executeUpdate();
			}
			stmt2.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Register load() throws FileNotFoundException, IOException, ClassNotFoundException {
		Register register;
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(SELECT);
	        register = new ArrayRegister(20);
	        while(rs.next()) {
	        	register.addPerson(new Person(rs.getString(1), rs.getString(2)));
	        }
	        return register;
		} catch (SQLException e) {
			Connection con;
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt1 = con.prepareStatement(CREATE);
				stmt1.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return new ArrayRegister(20);
	}

}
