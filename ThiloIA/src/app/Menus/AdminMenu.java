package app.Menus;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.App;
import app.AdminPages.EditTools.AddTool;
import app.AdminPages.Loans.AddLoan;
import app.AdminPages.Search.SearchQuery;
import app.Classes.User;
import app.loginReg.Login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMenu implements ActionListener { //TODO - Fix button layout
    private static JPanel  panel;
    private static JButton search, addItem, confirmReturn, editLoan, loanItemA, logoutButton;
    public static void main(String[] args) {
        
        GridBagConstraints gbc = new GridBagConstraints();


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        App.frame.add(panel);

        JPanel buttonPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(buttonPanel, gbc);

        logoutButton = new JButton("Logout");
        logoutButton.setSize(20,20);
        logoutButton.addActionListener(new AdminMenu());
        buttonPanel.add(logoutButton);

        JLabel header = new JLabel("Menu");
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

        search = new JButton("Search");
        search.addActionListener(new AdminMenu());
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        subPanel.add(addItem, gbc);

        confirmReturn = new JButton("Confirm returns");
        confirmReturn.addActionListener(new AdminMenu());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        subPanel.add(confirmReturn, gbc);

        loanItemA = new JButton("Loan an item");
        loanItemA.addActionListener(new AdminMenu());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(loanItemA, gbc);

        editLoan = new JButton("Edit a loan");
        editLoan.addActionListener(new AdminMenu());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(editLoan, gbc);

        


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
    }
    
    }
}
