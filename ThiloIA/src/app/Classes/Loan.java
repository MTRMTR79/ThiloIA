package app.Classes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Loan {
    private static int ItemID;
    private static String Username;
    private static LocalDate DateBorrowed; 
    private static LocalDate DueDate;
    private static LocalDate DateReturned; 
    private static String Status; 
    private static Item item;
    public Loan(int newItemID){
        ItemID = newItemID;
        String SQL = "SELECT * FROM Loans WHERE ItemID = " + ItemID;
        ResultSet results = SQLRequest.SQLQuery(SQL);
        try {
            while (results.next()){
                Username = results.getString("Username");
                DateBorrowed = results.getDate("DateBorrowed").toLocalDate();
                DueDate = results.getDate("DueDate").toLocalDate();
                try {
                    DateReturned = results.getDate("DateReturned").toLocalDate();
                
                } catch (Exception e) {
                    DateReturned = null;
                }
                Status = results.getString("Status");
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
        }
        item = new Item(ItemID);
    }
    public int getItemID() {
        return ItemID;
    }
    public String getUsername() {
        return Username;
    }public LocalDate getDateBorrowed() {
        return DateBorrowed;
    }public LocalDate getDueDate() {
        return DueDate;
    }public LocalDate getDateReturned() {
        return DateReturned;
    }public String getStatus() {
        return Status;
    }public Item getItem() {
        return item;
    }public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }public void setUsername(String username) {
        Username = username;
    }public void setDateReturned(LocalDate dateReturned) {
        DateReturned = dateReturned;
    }public void setStatus(String status) {
        Status = status;
    }

}
