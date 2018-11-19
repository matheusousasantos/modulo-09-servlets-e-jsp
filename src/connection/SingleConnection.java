package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
 * @author mathe
 *
 */
public class SingleConnection {
																		//   Se a conexão cair ele vai conectar automaticamente
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	 
	static {
		conectar();	/*Vamos criar uma chamada estática para nosso método conectar. A partir do momento que invocar-mos essa classe,
			  		de qualquer forma ela vai execultar o conectar() garantindo nossa conexão com o BD*/
	}
	
	public SingleConnection() {
		conectar();
	}
	
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); //Não quero que a minha transação commita automaticamento
				System.out.println("Conectou com sucesso!");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados");
		}
	}
	
	public static Connection getConnection() { //Esse método que mais trazer a coneção.
		return connection;
	}

}
