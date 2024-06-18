package topaggro;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectiondb {
	private String usuario = "root";
	private String senha = "2006";
	private String host = "localhost";
	private String porta = "3306";
	private String bd = "topaggro";

	public Connection obtemConexao (){
	        try{
	            Connection c = DriverManager.getConnection(
	            	"jdbc:mysql://" + host + ":" + porta + "/" + bd + 
	            	"?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true",
	            	usuario,
	            	senha
	           );
			   System.out.println("Conectou");
	            return c;
	        }
	        catch (Exception e){
	        	e.printStackTrace();
				System.out.println("Não conectou");
	            return null;
	        }
	    }
}