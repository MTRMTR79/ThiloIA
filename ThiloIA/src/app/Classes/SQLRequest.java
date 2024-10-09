package app.Classes;
import java.sql.*;


public class SQLRequest {
    public static ResultSet SQLQuery(String SQL){ //Method for retreving data from SQL database
        Statement sqlst;
        ResultSet result = null;

        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost: 3306/IdeasHub", "root", "A5M%r5vWMxSx");
            Class.forName("com.mysql.jdbc.Driver");
            sqlst = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = sqlst.executeQuery(SQL);
        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found, (JAR file)");
        }
        catch (SQLException ex){
            System.out.println("SQL Request page BROKEN " + ex.getMessage());
        }
        return result;
    }
    
    public static void SQLUpdate(String SQL){ //SQL Methed for adding / editing the database
        Statement sqlst;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost: 3306/IdeasHub", "root", "A5M%r5vWMxSx");
            sqlst = con.createStatement();
            sqlst.executeUpdate(SQL);


        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found, (JAR file)");
        }
        
        catch (SQLException ex){
            System.out.println("SQL BROKEN " + ex.getMessage());
        }
    }
}
