package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Respons�vel por fazer a conex�o com o banco de dados
 * @author mathe
 *
 */
public class SingleConnection {
																		//   Se a conex�o cair ele vai conectar automaticamente
	private static String banco = "jdbc:postgresql://localhost:5432/postgres?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection conn = null;
	 
	static {
		conectar();	/*Vamos criar uma chamada est�tica para nosso m�todo conectar. A partir do momento que invocar-mos essa classe,
			  		de qualquer forma ela vai execultar o conectar() garantindo nossa conex�o com o BD*/
	}
	

	
	private static void conectar() {
		try {
			
			if(conn == null) {
				Class.forName("org.postegres.Driver");
				conn = DriverManager.getConnection("banco, user, password");
				conn.setAutoCommit(false); //N�o quero que a minha transa��o commita automaticamento
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados");
		}
	}
	
	public static Connection getConnection() { //Esse m�todo que mais trazer a cone��o.
		return conn;
	}

}
