import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Connect{

  private Connection conn = null;

  public static void Connect() {
    System.out.println("Test");

  }

  public void initialisation() {


    String driver = "com.mysql.cj.jdbc.Driver";
    String db = "widdle";
    String url = "jdbc:mysql://localhost/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    System.out.println("Launching connection");

    try{
      Class.forName(driver);
      this.conn = DriverManager.getConnection(url,user,pass);


      System.out.println("Connected!");
    } catch (SQLException e){
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLSate: " + e.getSQLState());
      System.out.println("Vendor Error: " + e.getErrorCode());
    } catch (ClassNotFoundException e){
      System.out.println("ClassNotFoundException Error..");
    }

  }



  public void newPage(String newURL, String newContent, String newTitle){

    try{


      Statement statement = this.conn.createStatement();


      //
      if ( newURL != null && newContent != null && newTitle != null ) {
        int insertingPage = statement.executeUpdate( "INSERT INTO page (url, contenu, titre) "
        + "VALUES ('" + newURL + "', '" + newContent + "', '" + newTitle + "');" );
      }
      //


      System.out.println("Connected!");
    } catch (SQLException e){

      System.out.println("nooooope!");
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLSate: " + e.getSQLState());
      System.out.println("Vendor Error: " + e.getErrorCode());
    }

  }




  public int getIniBLN(String ThisURL){

    try{
      String prestation = "SELECT bln FROM page WHERE url=?;";
      try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {

        statement.setString(1, ThisURL);

        /* Exécution d'une requête de lecture */
        ResultSet resultat = statement.executeQuery();


        /* Récupération des données du résultat de la requête de lecture */
        
        int iniBLN = resultat.getInt( 1 );
        System.out.println("BLN retourné: " + iniBLN);


        return iniBLN;
        /* Traiter ici les valeurs récupérées. */

      }
    } catch (SQLException e){
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLSate: " + e.getSQLState());
      System.out.println("Vendor Error: " + e.getErrorCode());
    }

    return 1;

  }




  public void setNEWBLN(String ThisURL, int newBLN){
    String sql = "UPDATE page SET bln=? WHERE url=?;";
    try{
      //
      if ( ThisURL != null ) {
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);) {

          stmt.setInt(1, newBLN);
          stmt.setString(2, ThisURL);

          stmt.executeUpdate();
          System.out.println("Database updated successfully ");
        }
      }
      //


      System.out.println("Connected!");
    } catch (SQLException e){
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLSate: " + e.getSQLState());
      System.out.println("Vendor Error: " + e.getErrorCode());
    }

  }

}
