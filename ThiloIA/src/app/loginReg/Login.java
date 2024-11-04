package app.loginReg;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.*;

import app.App;
import app.Classes.SQLRequest;
import app.Classes.User;
import app.Menus.AdminMenu;
import app.Menus.UserMenu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login implements ActionListener {
    private static JPanel  panel;
    private static JButton registerButton, loginButton;
    private static JTextField usernameTextField;
    private static JPasswordField passwordField;
    private static JLabel loginError;

    public static int credentialCheck(String username, char[] password){
        String output;
        ResultSet result;
        String SQL = "SELECT Username FROM Users";
        boolean found = false;
        String plaintextPassword = new String(password);

        try{
            
            result = SQLRequest.SQLQuery(SQL);
            if (result == null){
                return -1;
            }
            while(result.next()){
                output = result.getString("Username");
                if(username.equals(output)){
                    found = true;
                    break;
                }
            }
            if (found == false){
                return -1;
            }
            SQL = "SELECT * FROM Users WHERE username = \"" + username + "\"";
            result = SQLRequest.SQLQuery(SQL);
            while(result.next()){
                output = result.getString("Password");
                if(!plaintextPassword.equals(output)){
                    return -1;
                }
            }


        }
        catch (SQLException ex){
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }
        

        if (username.equals("Admin")){
            return 2;
        }
        return 1;
    }

    public static void main(String[] args) {

        //Initialise GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setSize(900,500);
        panel.setBackground(App.PanelBackground);
        App.frame.add(panel);

        JLabel header = new JLabel("Welcome to IHIM");
        header.setFont(App.TITLE);
        header.setForeground(App.TitleColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        header.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(header, gbc);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(inputPanel,gbc);

        JLabel username = new JLabel("Username:");
        username.setFont(App.DEFAULT);
        username.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 1.0;
        inputPanel.add(username, gbc);

        usernameTextField = new JTextField();
        usernameTextField.setFont(App.DEFAULT);
        usernameTextField.setForeground(App.DefaultTextColor);
        usernameTextField.setBorder(App.JTextFieldBorder);
        usernameTextField.setPreferredSize(new Dimension(250,25));
        gbc.insets = new Insets(5,20,5,50);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        inputPanel.add(usernameTextField, gbc);

        JLabel password = new JLabel("Password:");
        gbc.insets = new Insets(5,50,5,20);
        password.setFont(App.DEFAULT);
        password.setForeground(App.DefaultTextColor);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(password, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(App.DEFAULT);
        passwordField.setForeground(App.DefaultTextColor);
        passwordField.setPreferredSize(new Dimension(250,25));
        passwordField.setBorder(App.JTextFieldBorder);
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5,20,5,50);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passwordField, gbc);

        loginError = new JLabel("Username or password is incorrect");
        loginError.setFont(App.ERROR);
        loginError.setForeground(App.ErrorColor);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginError.setVisible(false);
        inputPanel.add(loginError, gbc);

        JPanel paddingPanel2 = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 3;
        paddingPanel2.setOpaque(false);
        inputPanel.add(paddingPanel2,gbc);

        registerButton = new JButton("Don't have an account? Regester here!");
        registerButton.addActionListener(new Login());
        registerButton.setFont(App.BUTTON);
        registerButton.setForeground(App.ButtonText);
        registerButton.setBackground(App.ButtonColor);
        registerButton.setOpaque(true);
        registerButton.setBorder(App.buttonBorder);
        gbc.insets = new Insets(30,50,15,50);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 2;
        inputPanel.add(registerButton, gbc);

        loginButton = new JButton("Login!");
        loginButton.addActionListener(new Login());
        loginButton.setFont(App.BUTTON);
        loginButton.setForeground(App.ButtonText);
        loginButton.setBackground(App.ButtonColor);
        loginButton.setOpaque(true);
        loginButton.setBorder(App.buttonBorder);
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(loginButton, gbc);







        App.frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)){
            String username = usernameTextField.getText();
            char[] password = passwordField.getPassword();
            int status = credentialCheck(username, password);
            if (status == 2){
                User.storeUser(username);
                App.frame.getContentPane().removeAll();
                AdminMenu.main(null);
            }else if(status == 1){
                User.storeUser(username);
                App.frame.getContentPane().removeAll();
                UserMenu.main(null);
            }else if(status == -1){
                loginError.setVisible(true);
            }

        }else if(e.getSource().equals(registerButton)){
            App.frame.getContentPane().removeAll();
            Register.main(null);
            
            
        }
    
    }
}

