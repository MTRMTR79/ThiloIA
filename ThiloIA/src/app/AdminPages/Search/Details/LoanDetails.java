package app.AdminPages.Search.Details;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import app.AdminPages.Search.SearchResults;
import app.Classes.Loan;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class LoanDetails implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JTextField ItemID, ItemName, Username, DateBorrowed, DueDate, DateReturned;
    private static JComboBox<String> Status;
    private static JButton backButton, editButton, deleteButton, confirmButton, cancelButton;
    private static String query, queryType;
    private static Loan loan;
    public static void main(String[] args, Loan newLoan, String nQuery, String nQueryType) {
        query = nQuery;
        queryType = nQueryType;
        loan = newLoan;

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
        backButton.addActionListener(new LoanDetails());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Item: " + loan.getItemID() + ", " + loan.getItem().getItemName());
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

        JLabel ItemIDLabel = new JLabel("Item ID:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(ItemIDLabel, gbc);

        ItemID = new JTextField(loan.getItemID());
        //TODO - ItemID not showing
        ItemID.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        subPanel.add(ItemID,gbc);

        JLabel ItemNameLabel = new JLabel("Item Name:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(ItemNameLabel, gbc);

        ItemName = new JTextField(loan.getItem().getItemName());
        ItemName.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        subPanel.add(ItemName,gbc);

        JLabel UsernameLabel = new JLabel("Username:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(UsernameLabel, gbc);

        Username = new JTextField(loan.getUsername());
        Username.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        subPanel.add(Username,gbc);

        JLabel DateBorrowedLabel = new JLabel("Date Borrowed:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(DateBorrowedLabel, gbc);

        DateBorrowed = new JTextField(loan.getDateBorrowed().toString());
        DateBorrowed.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        subPanel.add(DateBorrowed,gbc);
        
        JLabel DueDateLabel = new JLabel("Due Date:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(DueDateLabel, gbc);

        DueDate = new JTextField(loan.getDueDate().toString());
        DueDate.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        subPanel.add(DueDate,gbc);

        JLabel DateReturnedLabel = new JLabel("Date Returned:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(DateReturnedLabel, gbc);

        try {
            DateBorrowed = new JTextField(loan.getDateReturned().toString());
        
        } catch (Exception e) {
            DateReturned = new JTextField();
        }
        
        DateReturned.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        subPanel.add(DateReturned,gbc);

        JLabel StatusLabel = new JLabel("Status:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(StatusLabel, gbc);

        String[] statusOptions = { loan.getStatus() };
        Status = new JComboBox<String>(statusOptions);
        Status.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        subPanel.add(Status,gbc);

        editButton = new JButton("Edit Loan");
        editButton.addActionListener(new LoanDetails());
        gbc.gridx = 0;
        gbc.gridy = 7;
        subPanel.add(editButton, gbc);

        confirmButton = new JButton("Confirm Changes");
        confirmButton.addActionListener(new LoanDetails());
        confirmButton.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        subPanel.add(confirmButton, gbc);

        deleteButton = new JButton("Delete Loan");
        deleteButton.addActionListener(new LoanDetails());
        gbc.gridx = 1;
        gbc.gridy = 7;
        subPanel.add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new LoanDetails());
        cancelButton.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        subPanel.add(cancelButton, gbc);




        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(backButton)){
        SearchResults.main(null, query, queryType);
        frame.setVisible(false);
        frame.dispose();
    }else if (e.getSource().equals(editButton)){
        Username.setEditable(true);
        DueDate.setEditable(true);
        Status.removeAllItems();
        String[] statusOptions = {"Reserved", "On Loan" , "Overdue", "Returned"};
        for (int i=0; i< statusOptions.length; i++){
            Status.addItem(statusOptions[i]);
        }
        //Status.setEditable(true);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        cancelButton.setVisible(true);
        confirmButton.setVisible(true);

    }else if (e.getSource().equals(cancelButton)){
        Username.setEditable(false);
        DueDate.setEditable(false);
        Status.removeAllItems();
        Status.addItem(loan.getStatus());
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        cancelButton.setVisible(false);
        confirmButton.setVisible(false);
        Username.setText(loan.getUsername());
        DueDate.setText(loan.getDateBorrowed().toString());

    }else if (e.getSource().equals(confirmButton)){
        loan.setDateReturned(LocalDate.parse(DateReturned.getText()));
    }
}
}//TODO - Validate things