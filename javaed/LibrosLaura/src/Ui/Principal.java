package Ui;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DAOLibros;
import datos.Db;

import modelos.Libro;

public class Principal {
	private static Scanner teclado=new Scanner(System.in);

	public static void main(String[] args) {
		Db.init();
		boolean continuar=true;
		while(continuar){
			imprimirMenu();
			System.out.println("\nIntroduzca la opcion");
			String s=teclado.nextLine();
			s=s.trim(); 
			if(s.length()>0){
				char o=s.charAt(0);
				switch(o){
				case '1':
					altaLibro();
					break;
				
				case '2':
					borrarLibro();
					break;
				case '3':
					buscarLibro();
					break;
				case '4':
					listarLibro();
					break;
					
				case '5':
					System.out.println("Adios. Gracias por utilizar nuestra aplicación");
					continuar=false;
					break;
				default:
					System.out.println("Introduzca una opcion valida por favor.");
				}
			}
		}
		
	}
	private static void imprimirMenu(){
		System.out.println("MENU");
		System.out.println();
		System.out.println("1.- ALta Libro");
		System.out.println("2.- Borrar Libro");
		System.out.println("3.- Buscar libro");
		System.out.println("4.- Listar libro");
		System.out.println();
		System.out.println("5.- Salir");
		
	}
	private static void altaLibro(){
		
		System.out.print(" Isbn: ");
		int isbn=teclado.nextInt();
		teclado.nextLine();
		System.out.print(" Titulo: ");
		String titulo=teclado.nextLine();
		System.out.print("Autor: ");
		String autor=teclado.nextLine();
		System.out.print(" Paginas: ");
		int paginas=teclado.nextInt();
		teclado.nextLine();
		
		Libro a=new Libro(isbn, titulo, autor, paginas);
		DAOLibros dao=new DAOLibros();
		if(dao.alta(a)){
			System.out.println("El libro se ha introducido correctamente");
		}
		else{
			System.out.println("Error al introducir libro, puede que el isbn ya exista");
		}
	}
	
	public static void borrarLibro(){
		System.out.print(" Isbn: ");
		int isbn = teclado.nextInt();
		DAOLibros dao=new DAOLibros();
		Libro l=new Libro(isbn,null,null,-1);
		int x=dao.borrar(l);
		if(x==0){
			System.out.println("El libro no existe y no se puede borrar");
		}
		else if(x==-1){
			System.out.println("Se ha producido un error");
		}
		else{
			System.out.println("El libro se ha borrado correctamente");
		}
		
	}
	
	/**private static void buscarLibro(){
		System.out.print("titulo:");
		String titulo=teclado.nextLine();
		
		DAOLibros dao=new DAOLibros();
		Libro l=dao.buscar(titulo);
		if(l!=null){
			System.out.println(l);
		}
		else{
			System.out.println("La isbn introducida no consta en la base de datos.");
		}
	}*/
	
	private static void buscarLibro(){
		String titulo;
		
		System.out.println("Introduce un título o parte del mismo: ");
		titulo=teclado.nextLine();
		
		DAOLibros dao=new DAOLibros();
		ArrayList<Libro> a=dao.buscar(titulo);
		
		for(Libro l : a){
			System.out.println(l);
		}
		
	}
	private static void listarLibro(){
		System.out.println("LISTADO DE TODOS LOS LIBROS");
		System.out.println();
		DAOLibros dao=new DAOLibros();
		ArrayList<Libro> a=dao.listar();
		
		for(Libro l : a){
			System.out.println(l);
		}
	}
}
