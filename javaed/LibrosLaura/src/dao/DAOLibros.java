package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.Db;

import modelos.Libro;



public class DAOLibros {
	Connection con = Db.getConexion();

	
	public boolean alta(Libro a) {
		
		
		String sql = "insert into libros ( isbn,titulo, autor,paginas) values (?,?,?,?)";
		try {
			PreparedStatement l = con.prepareStatement(sql);
			l.setInt(1, a.getIsbn());
			l.setString(2, a.getTitulo());
			l.setString(3, a.getAutor());
			l.setInt(4, a.getPaginas());
			l.executeUpdate();
			l.close();
			return true;
			

		}

		catch (SQLException sqle) {
			sqle.fillInStackTrace();
			return false;
		}

		}
		
		public int borrar(Libro a){
			String sql="delete from libros where isbn = ?";
			try {
				PreparedStatement l = con.prepareStatement(sql);
				l.setInt(1, a.getIsbn());
				int x=l.executeUpdate();
				l.close();
				return x;
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				return -1;
			}
			}
			
			/**public Libro buscar(String titulo) {
				Libro a = null;
				String sql = "select isbn, autor, paginas from libros where titulo=?";
				try {
					PreparedStatement av = con.prepareStatement(sql);
					av.setString(1, titulo);
					ResultSet rs = av.executeQuery();
					if (rs.next()) {
						a = new Libro(rs.getInt("isbn"), titulo,rs.getString("autor"),rs.getInt("paginas"));

					}
					rs.close();
				}

				catch (SQLException sqle) {
					sqle.printStackTrace();

				}
				return a;

			}*/
		 
			public ArrayList<Libro> buscar(String titulo){
				ArrayList<Libro> libro = new ArrayList<Libro>();
				String sql = "select * from libros where titulo like ?";
				
				try{
					PreparedStatement ps =con.prepareStatement(sql);
					ps.setString(1, "%"+titulo+ "%");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
		    			Libro lb=new Libro(rs.getInt("isbn"),rs.getString("titulo"),rs.getString("autor"),rs.getInt("paginas"));
		    			libro.add(lb);
		    		}
					
		    	}
		    	catch(SQLException sqle){
		    		System.out.println("No se puede acceder a la base de datos");
		    	}
		    	
		    	return libro;
			}
			
		
		
		
		
			public ArrayList<Libro> listar() {
				ArrayList<Libro> libro = new ArrayList<Libro>();

				String sql = "select *from libros order by isbn";

				try {
					PreparedStatement l = con.prepareStatement(sql);
					ResultSet rs = l.executeQuery();
					while (rs.next()) {
					 Libro a = new Libro(rs.getInt("isbn"), rs.getString("titulo"),rs.getString("autor"), rs.getInt("paginas"));
						libro.add(a);
					}

				} catch (SQLException sqle) {
					sqle.printStackTrace();

				}
				return libro;
			}


			
			
		}
		
	