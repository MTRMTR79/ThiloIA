package app.Menus;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.App;
import app.Classes.User;
import app.UserPages.CheckLoans;
import app.UserPages.LoanItem;
import app.loginReg.Login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserMenu implements ActionListener {
    private static JPanel  panel;
    private static JButton loanCheck, loanItem, logoutButton;
    public static void main(String[] args) {
        

        GridBagConstraints gbc = new GridBagConstraints();


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(App.PanelBackground);
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
        logoutButton.setSize(20,20);
        logoutButton.addActionListener(new UserMenu());
        logoutButton.setFont(App.BUTTON);
        logoutButton.setForeground(App.ButtonText);
        logoutButton.setBackground(App.ButtonColor);
        logoutButton.setBorder(App.buttonBorder);
        logoutButton.setOpaque(true);
        logoutButton.setPreferredSize(App.LogoutButtonSize);
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

        loanItem = new JButton("Loan an item");
        loanItem.addActionListener(new UserMenu());
        loanItem.setFont(App.BUTTON);
        loanItem.setForeground(App.ButtonText);
        loanItem.setBackground(App.ButtonColor);
        loanItem.setBorder(App.buttonBorder);
        loanItem.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(30, 30, 30, 30);
        subPanel.add(loanItem, gbc);

        loanCheck = new JButton("Check loans");
        loanCheck.addActionListener(new UserMenu());
        loanCheck.setFont(App.BUTTON);
        loanCheck.setForeground(App.ButtonText);
        loanCheck.setBackground(App.ButtonColor);
        loanCheck.setBorder(App.buttonBorder);
        loanCheck.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(loanCheck, gbc);

        


        App.frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(loanCheck)){
        App.frame.getContentPane().removeAll();
        CheckLoans.main(null);

    } else if (e.getSource().equals(logoutButton)){
        User.setEmail(null);
        User.setUsername(null);
        App.frame.getContentPane().removeAll();
        Login.main(null);
    } else if(e.getSource().equals(loanItem)){
        App.frame.getContentPane().removeAll();
        LoanItem.main(null);
    }
    
    }
}