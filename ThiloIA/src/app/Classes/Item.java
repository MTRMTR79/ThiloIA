package app.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
    int ItemID;
    String ItemName;
    String Status;
    String ItemType;
    String ItemGroup;
    String LoanUsername;
    public Item(int newItemID){
        ItemID = newItemID;
        String SQL = "SELECT * FROM Items WHERE ItemID = " + ItemID;
        ResultSet results = SQLRequest.SQLQuery(SQL);
        try {
            while (results.next()){
                ItemName = results.getString("ItemName");
                Status = results.getString("Status");
                ItemGroup = results.getString("Group");
                ItemType = results.getString("Type");
                LoanUsername = results.getString("LoanUsername");
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
        }
    }
    public int getItemID() {
        return ItemID;
    }public String getItemGroup() {
        return ItemGroup;
    }public String getItemName() {
        return ItemName;
    }public String getItemType() {
        return ItemType;
    }public String getLoanUsername() {
        return LoanUsername;
    }public String getStatus() {
        return Status;
    }
}
