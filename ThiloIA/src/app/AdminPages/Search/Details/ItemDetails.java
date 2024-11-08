package app.AdminPages.Search.Details;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.App;
import app.AdminPages.Search.SearchResults;
import app.Classes.Item;
import app.Classes.SQLRequest;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ItemDetails implements ActionListener {
    private static JPanel  panel;
    private static JTextField ItemID, itemName, Username;
    private static JComboBox<String> Status, itemGroup, itemType;
    private static JButton backButton, editButton, deleteButton, confirmButton, cancelButton;
    private static String query, queryType;
    private static Item item;
    public static void main(String[] args, Item newItem, String nQuery, String nQueryType) {
        query = nQuery;
        queryType = nQueryType;
        item = newItem;

        GridBagConstraints gbc = new GridBagConstraints();


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(null);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        App.frame.add(panel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(buttonPanel, gbc);

        backButton = new JButton("←");
        backButton.setPreferredSize(App.BackButtonSize);
        backButton.addActionListener(new ItemDetails());
        backButton.setFont(App.BUTTON);
        backButton.setForeground(App.ButtonText);
        backButton.setBackground(App.ButtonColor);
        backButton.setBorder(App.buttonBorder);
        backButton.setOpaque(true);
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Item: " + item.getItemID() + ", " + item.getItemName());
        header.setFont(App.TITLE);
        header.setForeground(App.TitleColor);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        header.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(header, gbc);

        JPanel paddingPanel = new JPanel();
        paddingPanel.setOpaque(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(paddingPanel, gbc);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        subPanel.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(subPanel, gbc);

        // ItemID, itemName, ItemGroup, ItemType, Status, LoanUsername

        JLabel ItemIDLabel = new JLabel("Item ID:");
        ItemIDLabel.setFont(App.DEFAULT);
        ItemIDLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(ItemIDLabel, gbc);

        ItemID = new JTextField(String.valueOf(item.getItemID()));
        ItemID.setEditable(false);
        ItemID.setFont(App.DEFAULT);
        ItemID.setForeground(App.DefaultTextColor);
        ItemID.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 0;
        subPanel.add(ItemID,gbc);


        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(App.DEFAULT);
        itemNameLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemNameLabel, gbc);

        itemName = new JTextField(item.getItemName());
        itemName.setEditable(false);
        itemName.setFont(App.DEFAULT);
        itemName.setForeground(App.DefaultTextColor);
        itemName.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 1;
        subPanel.add(itemName,gbc);


        JLabel itemGroupLabel = new JLabel("Item Group:");
        itemGroupLabel.setFont(App.DEFAULT);
        itemGroupLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemGroupLabel, gbc);

        
        itemGroup = new JComboBox<String>();
        if (item.getItemGroup() == null){
            itemGroup.addItem("-");
        }else{
        itemGroup.addItem(item.getItemGroup());
        }
        itemGroup.setFont(App.DEFAULT);
        itemGroup.setForeground(App.DefaultTextColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        subPanel.add(itemGroup,gbc);


        JLabel itemTypeLabel = new JLabel("Item Type:");
        itemTypeLabel.setFont(App.DEFAULT);
        itemTypeLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemTypeLabel, gbc);

        
        itemType = new JComboBox<String>();
        if (item.getItemType() == null){
            itemType.addItem("-");
        }else{
        itemType.addItem(item.getItemType());
        }
        itemType.setFont(App.DEFAULT);
        itemType.setForeground(App.DefaultTextColor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        itemType.addActionListener(new ItemDetails());
        subPanel.add(itemType,gbc);


        JLabel StatusLabel = new JLabel("Status:");
        StatusLabel.setFont(App.DEFAULT);
        StatusLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(StatusLabel, gbc);

        String[] statusOptions = { item.getStatus() };
        Status = new JComboBox<String>(statusOptions);
        Status.setEditable(false);
        Status.setFont(App.DEFAULT);
        Status.setForeground(App.DefaultTextColor);
        gbc.gridx = 1;
        gbc.gridy = 4;
        subPanel.add(Status,gbc);

        JLabel UsernameLabel = new JLabel("Loan Username:");
        UsernameLabel.setFont(App.DEFAULT);
        UsernameLabel.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(UsernameLabel, gbc);

        Username = new JTextField(item.getLoanUsername());
        Username.setEditable(false);
        Username.setFont(App.DEFAULT);
        Username.setForeground(App.DefaultTextColor);
        Username.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 5;
        subPanel.add(Username,gbc);


        editButton = new JButton("Edit Item");
        editButton.addActionListener(new ItemDetails());
        editButton.setFont(App.BUTTON);
        editButton.setForeground(App.ButtonText);
        editButton.setBackground(App.ButtonColor);
        editButton.setBorder(App.buttonBorder);
        editButton.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 7;
        subPanel.add(editButton, gbc);

        confirmButton = new JButton("Confirm Changes");
        confirmButton.addActionListener(new ItemDetails());
        confirmButton.setVisible(false);
        confirmButton.setFont(App.BUTTON);
        confirmButton.setForeground(App.ButtonText);
        confirmButton.setBackground(App.ButtonColor);
        confirmButton.setBorder(App.buttonBorder);
        confirmButton.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 7;
        subPanel.add(confirmButton, gbc);

        deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(new ItemDetails());
        deleteButton.setFont(App.BUTTON);
        deleteButton.setForeground(App.ButtonText);
        deleteButton.setBackground(App.ButtonColor);
        deleteButton.setBorder(App.buttonBorder);
        deleteButton.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        subPanel.add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ItemDetails());
        cancelButton.setVisible(false);
        cancelButton.setFont(App.BUTTON);
        cancelButton.setForeground(App.ButtonText);
        cancelButton.setBackground(App.ButtonColor);
        cancelButton.setBorder(App.buttonBorder);
        cancelButton.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        subPanel.add(cancelButton, gbc);




        App.frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(backButton)){
        App.frame.getContentPane().removeAll();
        SearchResults.main(null, query, queryType);
    }else if (e.getSource().equals(editButton)){
        itemName.setEditable(true);
        Username.setEditable(true);
        Status.removeAllItems();
        String[] statusOptions = {"On Loan" , "idle", "Broken", "In Storage"};
        for (int i=0; i< statusOptions.length; i++){
            Status.addItem(statusOptions[i]);
        }
        itemType.removeAllItems();
        String SQL = "SELECT DISTINCT ItemType FROM ItemGroups";
        Queue<String> itemTypeQueue = new LinkedList<>();
        itemTypeQueue.add("-");
        ResultSet result = SQLRequest.SQLQuery(SQL);
        try {
            while (result.next()){
                itemTypeQueue.add(result.getString("ItemType"));
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }
        while (!(itemTypeQueue.peek() == null)) {
            itemType.addItem(itemTypeQueue.remove());
        }

        editButton.setVisible(false);
        deleteButton.setVisible(false);
        cancelButton.setVisible(true);
        confirmButton.setVisible(true);

    }else if (e.getSource().equals(cancelButton)){
        Username.setEditable(false);
        itemName.setEditable(false);
        itemName.setText(item.getItemName());
        Status.removeAllItems();
        Status.addItem(item.getStatus());
        itemGroup.removeAllItems();
        itemGroup.addItem(item.getItemGroup());
        itemType.removeAllItems();
        itemType.addItem(item.getItemType());
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        cancelButton.setVisible(false);
        confirmButton.setVisible(false);
        Username.setText(item.getLoanUsername());

    }else if (e.getSource().equals(confirmButton)){
        ResultSet result;
        String UsernameSQL = "SELECT Username FROM Users";
        String output;
        Boolean found = false;
        result = SQLRequest.SQLQuery(UsernameSQL);
        System.out.println(Username.getText());
        if (!(Username.getText().equals(""))){
            
            try {
                while(result.next()){
                    output = result.getString("Username");
                    if(Username.getText().equals(output)){
                        found = true;
                        break;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL BROKEN " + ex.getMessage());
                JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
            }
            if (!found){
                JOptionPane.showMessageDialog(App.frame, "Error: User not found");
            }else{
                String SQL;
                item.setLoanUsername(Username.getText());
                item.setStatus(Status.getSelectedItem().toString());
                item.setItemType(itemType.getSelectedItem().toString());
                item.setItemGroup(itemGroup.getSelectedItem().toString());
                item.setItemName(itemName.getText());
    
                if (item.getItemType().equals("-")){
                SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = \"" + item.getLoanUsername() + "\", `Type` = NULL, `Group` = NULL WHERE `ItemID` = " + item.getItemID();
                }else if (item.getItemGroup().equals("-")){
                    SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = \"" + item.getLoanUsername() + "\", `Type` = \"" + item.getItemType() + "\", `Group` = NULL WHERE `ItemID` = " + item.getItemID();
                }else{
                    SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = \"" + item.getLoanUsername() + "\", `Type` = \"" + item.getItemType() + "\", `Group` = \"" + item.getItemGroup() + "\" WHERE `ItemID` = " + item.getItemID();
                }



                try {
                    SQLRequest.SQLUpdate(SQL);
                } catch (Exception ex) {
                    System.out.println("SQL BROKEN " + ex.getMessage());
                }
                Username.setEditable(false);
                itemName.setEditable(false);
                Status.removeAllItems();
                Status.addItem(item.getStatus());
                itemType.removeAllItems();
                itemType.addItem(item.getItemType());
                itemGroup.removeAllItems();
                itemGroup.addItem(item.getItemGroup());
                editButton.setVisible(true);
                deleteButton.setVisible(true);
                cancelButton.setVisible(false);
                confirmButton.setVisible(false);
    
                
            }
        }else{
            String SQL;
            item.setLoanUsername(Username.getText());
            item.setStatus(Status.getSelectedItem().toString());
            item.setItemType(itemType.getSelectedItem().toString());
            item.setItemGroup(itemGroup.getSelectedItem().toString());
            item.setItemName(itemName.getText());

            if (item.getItemType().equals("-")){
                SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = NULL, `Type` = NULL, `Group` = NULL WHERE `ItemID` = " + item.getItemID();
            }else if (item.getItemGroup().equals("-")){
                SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = NULL, `Type` = \"" + item.getItemType() + "\", `Group` = NULL WHERE `ItemID` = " + item.getItemID();
            }else{
                SQL = "UPDATE Items SET `ItemName` = \"" + item.getItemName() + "\", `Status` = \"" + item.getStatus() + "\", `LoanUsername` = NULL, `Type` = \"" + item.getItemType() + "\", `Group` = \"" + item.getItemGroup() + "\" WHERE `ItemID` = " + item.getItemID();
            }

            try {
                SQLRequest.SQLUpdate(SQL);
            } catch (Exception ex) {
                System.out.println("SQL BROKEN " + ex.getMessage());
            }
            Username.setEditable(false);
            Status.removeAllItems();
            Status.addItem(item.getStatus());
            editButton.setVisible(true);
            deleteButton.setVisible(true);
            cancelButton.setVisible(false);
            confirmButton.setVisible(false);
        }
        
        
        
    }else if (e.getSource().equals(deleteButton)){
        Object[] options = {"Cancel", "Delete"};
        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to delete this item?", "Are you sure you want to delete this item?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.NO_OPTION){
            String SQL = "DELETE FROM Items WHERE ItemID = " + item.getItemID();
            SQLRequest.SQLUpdate(SQL);
        }
    }else if(e.getSource().equals(itemType)){
        if (!(itemType.getSelectedItem() == null || itemType.getSelectedItem().equals("-"))){
        itemGroup.removeAllItems();
        String SQL;
        
        SQL = "SELECT ItemGroup FROM ItemGroups WHERE ItemType = \"" + itemType.getSelectedItem().toString() + "\"";
        
        Queue<String> itemGroupQueue = new LinkedList<>();
        itemGroupQueue.add("-");
        ResultSet result = SQLRequest.SQLQuery(SQL);
        itemGroup.removeAllItems();
        try {
            while (result.next()){
                itemGroupQueue.add(result.getString("ItemGroup"));
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }

        int Length = itemGroupQueue.size();
        String[] itemGroups = new String[Length];

        for (int i=0; i<(Length);i++){
            itemGroups[i] = itemGroupQueue.remove();
            System.out.println(itemGroups[i]);
        }
        for (int i=0; i<(Length);i++){
        itemGroup.addItem(itemGroups[i]);
        }
    }else{
        itemGroup.removeAllItems();
        itemGroup.addItem("-");
    }
    }
}
}