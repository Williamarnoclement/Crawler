import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

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

}
