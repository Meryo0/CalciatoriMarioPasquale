package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn=null;
        try{
              Class.forName("org.postgresql.Driver");
              conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +dbname,user,pass);
              if (conn != null) {
                  System.out.println("Connessione Avvenuta");
              }
              else{
                  System.out.println("Connessione Fallita");
              }
        }catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
