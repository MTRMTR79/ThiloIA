package app.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public static String username, email;
    public User(String newUsername){
        username = newUsername;
        String SQL = "SELECT Email FROM Users WHERE Username = \"" + newUsername + "\"";
        ResultSet results = SQLRequest.SQLQuery(SQL);
        try {
            String output;
            while (results.next()){
                output = results.getString(1);
                email = output;
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
        }
    }
    public static void storeUser(String newUsername){
        username = newUsername;
        
        
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    
}
