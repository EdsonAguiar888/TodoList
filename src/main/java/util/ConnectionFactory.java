
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author HP
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    public static final String USER = "root";
    public static final String PASS = "";
    
    //Metodo para ABRIR a conexao
    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(Exception ex){
            throw new RuntimeException("Erro na conexão com o banco de dados.", ex);
        }
    }
    
    //Metodo para FECHAR a conexao
    public static void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
        }
    }
    
    // Mesmo metodo fechar a conexao fechando tambem o ***STATEMENT***
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try{
            if(connection != null){
                connection.close();
            }
            
            if(statement != null){
                statement.close();
            }
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
        }
    }   
    
    //Mesmo metodo fechar a conexao fechando tambem o ***RESULTSET***
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try{
            if(connection != null){
                connection.close();
            }
            
            if(statement != null){
                statement.close();
            }
            
            if(resultSet != null){
                resultSet.close();
            }
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
        }
    }   
    
}
