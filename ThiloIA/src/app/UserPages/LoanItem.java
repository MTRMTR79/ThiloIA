package app.UserPages;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import app.Classes.SQLRequest;
import app.Classes.User;
import app.Menus.UserMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanItem implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JLabel IDError, dateError;
    private static JTextField itemIDTextField;
    private static JButton confirm, backButton;
    private static JFormattedTextField datePicker;
    private static DateTimeFormatter formatter;
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
        backButton.addActionListener(new LoanItem());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Loan A tool");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        header.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(header, gbc);

        JPanel paddingPanel = new JPanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(paddingPanel, gbc);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(subPanel, gbc);

        JLabel itemID = new JLabel("Item ID:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemID, gbc);

        itemIDTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        subPanel.add(itemIDTextField, gbc);

        IDError = new JLabel();
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 1;
        gbc.gridy = 2;
        IDError.setVisible(false);
        subPanel.add(IDError, gbc);

        
        JLabel date = new JLabel("Due Date:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(date, gbc);

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        datePicker = new JFormattedTextField(formatter);
        datePicker.setColumns(9);
        datePicker.setValue(LocalDate.now().plusDays(14));
        datePicker.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        subPanel.add(datePicker, gbc);

        dateError = new JLabel();
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 1;
        gbc.gridy = 3;
        dateError.setVisible(false);
        subPanel.add(dateError, gbc);

        
        confirm = new JButton("Add");
        confirm.addActionListener(new LoanItem());
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        subPanel.add(confirm, gbc);

        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirm)){
            Boolean success = true;
            int itemID = 0;
            try {
                itemID = Integer.parseInt(itemIDTextField.getText());
            } catch (NumberFormatException numEx) {
                IDError.setText("Incorrect ID format, please enter the ID Number.");
                IDError.setVisible(true);
                success = false;
            }

            
            
            if(itemIDTextField.getText().isEmpty()){
                IDError.setText("Please add an item ID");
                IDError.setVisible(true);
                success = false;
            }
            try {

                String SQL = "SELECT ItemID FROM Items";
                ResultSet result = SQLRequest.SQLQuery(SQL);
                int output;
                boolean itemIDFound = false;
                while(result.next()){
                    output = result.getInt("ItemID");
                    if(itemID == output){
                        itemIDFound = true;
                    }
                }
                if (!itemIDFound){
                    IDError.setText("Item not found");
                    IDError.setVisible(true);
                    success = false;
                }

            } catch (SQLException ex) {
                System.out.println("SQL BROKEN " + ex.getMessage());
            }

            try {

                String SQL = "SELECT ItemID FROM Loans";
                ResultSet result = SQLRequest.SQLQuery(SQL);
                int output;
                boolean itemIDFound = false;
                while(result.next()){
                    output = result.getInt("ItemID");
                    if(itemID == output){
                        itemIDFound = true;
                    }
                }
                if (itemIDFound){
                    IDError.setText("Item not avaliable");
                    IDError.setVisible(true);
                    success = false;
                }

            } catch (SQLException ex) {
                System.out.println("SQL BROKEN " + ex.getMessage());
            }

            String username = User.username;
            
            LocalDate dueDate = LocalDate.parse(datePicker.getText(), formatter);

            LocalDate today = LocalDate.now();

            if((today.isAfter(dueDate))){
                dateError.setText("Date must be in the future");
                dateError.setVisible(true);
                success = false;
            }
            if(success){
                String SQL = "INSERT INTO Loans (`ItemID`, `Username`, `DateBorrowed`, `DueDate`, Status) VALUES (\"" + itemID + "\", \""+ username + "\", \"" + today + "\", \"" + dueDate + "\" , \"OnLoan\")";
                SQLRequest.SQLUpdate(SQL);
                itemIDTextField.setText("");
                JOptionPane.showMessageDialog(frame, "Item successfully loaned. Enjoy!");
        
            }
        }else if(e.getSource().equals(backButton)){
            UserMenu.main(null);
        }
    }
}