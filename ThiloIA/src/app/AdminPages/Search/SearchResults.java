package app.AdminPages.Search;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import app.AdminPages.Search.Details.LoanDetails;
import app.AdminPages.Search.Details.UserDetails;
import app.Classes.Loan;
import app.Classes.SQLRequest;
import app.Classes.User;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SearchResults implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JTable resultsTable;
    private static JButton confirm, backButton;
    public static void main(String[] args, String query, String queryType) {

        // Declare and initalise login frame
        frame = new JFrame(); 
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IH Inventory Management");
        frame.setLocationRelativeTo(null);


        GridBagConstraints gbc = new GridBagConstraints();


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);

        JPanel buttonPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(buttonPanel, gbc);

        backButton = new JButton("←");
        backButton.setSize(20,20);
        backButton.addActionListener(new SearchResults());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Results for " + query);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        header.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(header, gbc);

        JPanel paddingPanel = new JPanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(paddingPanel, gbc);

        DefaultTableModel model;

        if(queryType.equals("Username")){
            model = new DefaultTableModel(new Object[]{"Username", "Email"},0);
            String SQL = "SELECT Username, Email FROM Users WHERE Username LIKE  \"%" + query + "%\";";
            String username;
            String email;
            ResultSet result = SQLRequest.SQLQuery(SQL);
            try {
                while(result.next()){
                    username = result.getString("Username");
                    email = result.getString("Email");
                    model.addRow(new Object[] {username, email});

                }
            } catch (SQLException ex){
                System.out.println("SQL BROKEN " + ex.getMessage());
            }

        }else if(queryType.equals("Item Name")){
            model = new DefaultTableModel(new Object[]{"Item ID", "Item Name", "Item Group", "Item Type", "Status" , "Loan Username"},0);
            String SQL = "SELECT * FROM Items WHERE ItemName LIKE  \"%" + query + "%\";";
            int ItemID;
            String ItemName;
            String Status;
            String ItemType;
            String ItemGroup;
            String LoanUsername;
            ResultSet result = SQLRequest.SQLQuery(SQL);
            try {
                while(result.next()){
                    ItemID = result.getInt("ItemID");
                    ItemName = result.getString("ItemName");
                    ItemGroup = result.getString("Group");
                    ItemType = result.getString("Type");
                    Status = result.getString("Status");
                    LoanUsername = result.getString("loanUsername");
                    model.addRow(new Object[] {ItemID, ItemName, ItemGroup, ItemType, Status, LoanUsername});

                }
            } catch (SQLException ex){
                System.out.println("SQL BROKEN " + ex.getMessage());
            }

        }else if(queryType.equals("Loans")){
            model = new DefaultTableModel(new Object[]{"Item ID","Item Name", "Username", "Date Borrowed" , "Due Date", "Date Returned", "Status"},0);
            String SQL = "SELECT Loans.*, Items.ItemName FROM Loans LEFT JOIN Items ON Loans.ItemID = Items.ItemID WHERE ItemName LIKE  \"%" + query + "%\" OR Username LIKE  \"%" + query + "%\"";
            String ItemID;
            String ItemName;
            String Username;
            LocalDate DateBorrowed;
            LocalDate DueDate;
            LocalDate DateReturned;
            String Status;
            ResultSet result = SQLRequest.SQLQuery(SQL);
            try {
                while(result.next()){
                    ItemID = result.getString("ItemID");
                    ItemName = result.getString("ItemName");
                    Username = result.getString("Username");
                    DateBorrowed = result.getDate("DateBorrowed").toLocalDate();
                    DueDate = result.getDate("DueDate").toLocalDate();
                    Status = result.getString("Status");
                    if (result.getDate("DateReturned") != null){
                        DateReturned = result.getDate("DateReturned").toLocalDate();
                        model.addRow(new Object[] {ItemID, ItemName, Username, DateBorrowed, DueDate, DateReturned, Status });
                    }else{
                    model.addRow(new Object[] {ItemID, ItemName, Username, DateBorrowed, DueDate, null, Status });
                    }

                }
            } catch (SQLException ex){
                System.out.println("SQL BROKEN " + ex.getMessage());
            }

        }else {
            model = new DefaultTableModel(new Object[]{},0);
        }

        
        
        resultsTable = new JTable(model);
        resultsTable.setDefaultEditor(Object.class, null);;
        resultsTable.setFillsViewportHeight(true);
        resultsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent doubleClick) {
                Point point = doubleClick.getPoint();
                int row = resultsTable.rowAtPoint(point);
                if (doubleClick.getClickCount() == 2 && resultsTable.getSelectedRow() != -1) {
                    if (queryType.equals("Username")){
                        String username = resultsTable.getModel().getValueAt(row, 0).toString();
                        User user = new User(username);
                        UserDetails.main(null, user, query, queryType);
                        frame.setVisible(false);
                        frame.dispose();

                    }
                    if (queryType.equals("Loans")){
                        int ItemID = Integer.parseInt(resultsTable.getModel().getValueAt(row, 0).toString());
                        Loan loan = new Loan(ItemID);
                        LoanDetails.main(args, loan, query, queryType);
                        frame.setVisible(false);
                        frame.dispose();

                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 3;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        

        

        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirm)){
            
        }else if(e.getSource().equals(backButton)){
            SearchQuery.main(null);
            frame.setVisible(false);
            frame.dispose();
        }
    }
}