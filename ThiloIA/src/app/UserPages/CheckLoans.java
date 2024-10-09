package app.UserPages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import app.Classes.SQLRequest;
import app.Classes.User;
import app.Menus.UserMenu;

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

public class CheckLoans implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JTable resultsTable;
    private static JButton confirm, backButton;
    private static DefaultTableModel model;

    private static void fillTable(){
        while (model.getRowCount() > 0){
        model.removeRow(model.getRowCount() - 1);
        }
        String SQL = "SELECT Loans.*, Items.ItemName FROM Loans LEFT JOIN Items ON Loans.ItemID = Items.ItemID WHERE Username = \"" + User.username + "\"";
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
                    model.addRow(new Object[] {ItemID, ItemName, Username, DateBorrowed, DueDate, DateReturned, Status, "Click to Return" });
                }else{
                model.addRow(new Object[] {ItemID, ItemName, Username, DateBorrowed, DueDate, null, Status, "Click to Return" });
                }
            
            }

        } catch (SQLException ex){
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(frame, "Error, server down. Please try again later.");
        }
    }
    public static void main(String[] args) {

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
        backButton.addActionListener(new CheckLoans());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Loans from " + User.username);
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

        

        model = new DefaultTableModel(new Object[]{"Item ID","Item Name", "Username", "Date Borrowed" , "Due Date", "Date Returned", "Status", "Return Item"},0);
        fillTable();
        
        
        resultsTable = new JTable(model);
        resultsTable.setDefaultEditor(Object.class, null);;
        resultsTable.setFillsViewportHeight(true);
        resultsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent returnClick) {
                Point point = returnClick.getPoint();
                int row = resultsTable.rowAtPoint(point);
                if (returnClick.getClickCount() == 1 && resultsTable.getSelectedRow() != -1 && resultsTable.getSelectedColumn() == 7){
                    int result = JOptionPane.showConfirmDialog(null, "Have you returned this Item?", "Have you returned this Item?", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION){
                        LocalDate today = LocalDate.now();
                        String SQL = "UPDATE Loans SET Status = \"Returned\", DateReturned = \"" + today + "\" WHERE ItemID =" + Integer.parseInt(resultsTable.getModel().getValueAt(row, 0).toString());
                        System.out.println(SQL);
                        SQLRequest.SQLUpdate(SQL);
                        resultsTable.clearSelection();
                        fillTable();
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
            UserMenu.main(null);
        }
    }
}