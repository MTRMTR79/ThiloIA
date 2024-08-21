package app.Classes;
import java.sql.*;


public class SQLRequest {
    public static ResultSet SQLQuery(String SQL){ //Method for retreving data from SQL database
        Statement sqlst;
        ResultSet result = null;


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost: 3306/IdeasHub", "root", "A5M%r5vWMxSx");
            sqlst = con.createStatement();
            result = sqlst.executeQuery(SQL);
            
            con.close();


        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found, (JAR file)");
        }
        
        catch (SQLException ex){
            System.out.println("SQL BROKEN " + ex.getMessage());
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
            con.close();


        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found, (JAR file)");
        }
        
        catch (SQLException ex){
            System.out.println("SQL BROKEN " + ex.getMessage());
        }
    }
}
