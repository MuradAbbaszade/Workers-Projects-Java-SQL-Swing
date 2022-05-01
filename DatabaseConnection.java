import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
	private String username="root";
	private String password="";
	private String databaseName="admin";
	private String host="localhost";
	private int port =3306;
	private Connection connection=null;
	private Statement statement=null;
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public DatabaseConnection(){
		String url="jdbc:mysql://"+host+":"+port+"/"+databaseName;
		try {
			connection=DriverManager.getConnection(url,username,password);
			statement=connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Connection lost.");
		}
	}
}
