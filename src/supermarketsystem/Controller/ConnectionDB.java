package supermarketsystem.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Klarić Dario
 */

public class ConnectionDB {
   
  private static Connection con = null;
  private static String url = "jdbc:mysql://localhost:3306/projekt_puj";
  
    private ConnectionDB() {
        
    }
  
  public static Connection openConnection()
  {
      if(con == null) {
        try {
            con = (Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return con;
  }
  
  public static void closeConnection() {
    
      if(con != null)
          con = null;
  } 
}    


// -- Provjera konekcije --

// public static void main(String[] args){
//     
//     try {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projekt_puj", "root", "");
//
//        if(con != null) {
//            System.out.println("Successfully connected!");
//        }
//     } catch (Exception e) {
//         
//         System.out.println(e);
//         System.out.println("Not connected!");
//     }
// }
