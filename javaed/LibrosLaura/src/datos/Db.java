package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
	public static Connection con;
	
	public static void init(){
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libros","root","");
			}
			catch( ClassNotFoundException cnfe){
				System.out.println("---no se puede encontrar el driver ");
			}
			catch (SQLException sqle){
				System.out.println("no se puede encontrar la base de datos");
				
			}

		}
		
		public static Connection getConexion(){
			return con;

}
		
}