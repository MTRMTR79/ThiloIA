package app.Menus;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.App;
import app.AdminPages.ConfirmLoans.ConfirmLoans;
import app.AdminPages.EditTools.AddTool;
import app.AdminPages.Loans.AddLoan;
import app.AdminPages.Search.SearchQuery;
import app.Classes.SQLRequest;
import app.Classes.User;
import app.loginReg.Login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminMenu implements ActionListener {
    private static JPanel  panel;
    private static JButton search, addItem, confirmReturn, loanItemA, logoutButton;
    public static void main(String[] args) {
        
        GridBagConstraints gbc = new GridBagConstraints();


        panel = new JPanel();
        panel.setBackground(App.PanelBackground);
        panel.setLayout(new GridBagLayout());
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

        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(App.LogoutButtonSize);
        logoutButton.addActionListener(new AdminMenu());
        logoutButton.setFont(App.BUTTON);
        logoutButton.setForeground(App.ButtonText);
        logoutButton.setBackground(App.ButtonColor);
        logoutButton.setBorder(App.buttonBorder);
        logoutButton.setOpaque(true);
        buttonPanel.add(logoutButton);

        JLabel header = new JLabel("Menu");
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

        search = new JButton("Search");
        search.addActionListener(new AdminMenu());
        search.setFont(App.BUTTON);
        search.setForeground(App.ButtonText);
        search.setBackground(App.ButtonColor);
        search.setBorder(App.buttonBorder);
        search.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        subPanel.add(search, gbc);

        addItem = new JButton("Add Item");
        addItem.addActionListener(new AdminMenu());
        addItem.setFont(App.BUTTON);
        addItem.setForeground(App.ButtonText);
        addItem.setBackground(App.ButtonColor);
        addItem.setBorder(App.buttonBorder);
        addItem.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        subPanel.add(addItem, gbc);

        loanItemA = new JButton("Loan an item");
        loanItemA.addActionListener(new AdminMenu());
        loanItemA.setFont(App.BUTTON);
        loanItemA.setForeground(App.ButtonText);
        loanItemA.setBackground(App.ButtonColor);
        loanItemA.setBorder(App.buttonBorder);
        loanItemA.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(loanItemA, gbc);

        confirmReturn = new JButton("Confirm returns");
        confirmReturn.addActionListener(new AdminMenu());
        confirmReturn.setFont(App.BUTTON);
        confirmReturn.setForeground(App.ButtonText);
        confirmReturn.setBackground(App.ButtonColor);
        confirmReturn.setBorder(App.buttonBorder);
        confirmReturn.setOpaque(true);
        String SQL = "SELECT COUNT(*) FROM Loans WHERE Status = \"Returned\"";
        ResultSet result = SQLRequest.SQLQuery(SQL);
        try {
            result.next();
            if (result.getInt(1) == 0){
                confirmReturn.setVisible(false);
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        subPanel.add(confirmReturn, gbc);


        App.frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(logoutButton)){
    User.setEmail(null);
    User.setUsername(null);
    App.frame.getContentPane().removeAll();
    Login.main(null);

    }else if(e.getSource().equals(loanItemA)){
        App.frame.getContentPane().removeAll();
        AddLoan.main(null);
        
    }else if(e.getSource().equals(search)){
        App.frame.getContentPane().removeAll();
        SearchQuery.main(null);

    }else if(e.getSource().equals(addItem)){
        App.frame.getContentPane().removeAll();
        AddTool.main(null);
    }else if(e.getSource().equals(confirmReturn)){
        App.frame.getContentPane().removeAll();
        ConfirmLoans.main(null);
    }
    
    }
}
