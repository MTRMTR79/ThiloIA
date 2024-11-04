package app.loginReg;
import java.sql.*;
import java.util.regex.Pattern;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.App;
import app.Classes.SQLRequest;





public class Register implements ActionListener{
    private static JPanel  panel;
    private static JButton registerButton, loginButton;
    private static JTextField usernameTextField, emailTextField;
    private static JPasswordField passwordField, passwordVerField;
    private static JLabel usernameError, passwordError2, passwordError3, emailError;


    public static int regVerification(String username, char[] password, char[] passwordVer, String email){
        String output;
        ResultSet result;
        String SQL = "SELECT Username FROM Users";
        result = SQLRequest.SQLQuery(SQL);

        try {
            while(result.next()){
                output = result.getString("Username");
                if(username.equals(output)){
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }      

    
        if (password.length < 8 || password.length > 64){
            return 2;
        }
        
        if(password.length != passwordVer.length){
            return 3;
        } else{
            for(int i=0; i<(password.length); i++){
                if (password[i] != passwordVer[i]){
                    return 3;
                }
            }
        }
        
        if (!Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()){
            return 4;
        }
    
        return -1;
    }


    public static void main(String[] args) {

        //Initialise GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setSize(900,500);
        panel.setBackground(App.PanelBackground);
        App.frame.add(panel);

        JLabel title = new JLabel("Welcome to IHIM");
        title.setFont(App.TITLE);
        title.setForeground(App.TitleColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, gbc);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(inputPanel,gbc);

        JLabel username = new JLabel("Username:");
        username.setFont(App.DEFAULT);
        username.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        inputPanel.add(username, gbc);

        usernameTextField = new JTextField();
        usernameTextField.setFont(App.DEFAULT);
        usernameTextField.setForeground(App.DefaultTextColor);
        usernameTextField.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(usernameTextField, gbc);

        usernameError = new JLabel("Username is already in use, please use another one.");
        usernameError.setFont(App.ERROR);
        usernameError.setForeground(App.ErrorColor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        usernameError.setVisible(false);
        inputPanel.add(usernameError, gbc);

        JLabel password = new JLabel("Password:");
        password.setFont(App.DEFAULT);
        password.setForeground(App.DefaultTextColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(password, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(App.DEFAULT);
        passwordField.setForeground(App.DefaultTextColor);
        passwordField.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(passwordField, gbc);

        passwordError2 = new JLabel("Password is too short, password must be 8-64 characters.");
        passwordError2.setFont(App.ERROR);
        passwordError2.setForeground(App.ErrorColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordError2.setVisible(false);
        inputPanel.add(passwordError2, gbc);

        JLabel passwordVer = new JLabel("Confirm Password:");
        passwordVer.setFont(App.DEFAULT);
        passwordVer.setForeground(App.DefaultTextColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(passwordVer, gbc);

        passwordVerField = new JPasswordField();
        passwordVerField.setFont(App.DEFAULT);
        passwordVerField.setForeground(App.DefaultTextColor);
        passwordVerField.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(passwordVerField, gbc);

        passwordError3 = new JLabel("Passwords don't match");
        passwordError3.setFont(App.ERROR);
        passwordError3.setForeground(App.ErrorColor);
        gbc.gridx = 1;
        gbc.gridy = 5;
        passwordError3.setVisible(false);
        inputPanel.add(passwordError3, gbc);

        JLabel email = new JLabel("Email:");
        email.setFont(App.DEFAULT);
        email.setForeground(App.DefaultTextColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(email, gbc);

        emailTextField = new JTextField();
        emailTextField.setFont(App.DEFAULT);
        emailTextField.setForeground(App.DefaultTextColor);
        emailTextField.setBorder(App.JTextFieldBorder);
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(emailTextField, gbc);

        emailError = new JLabel("Email not Valid.");
        emailError.setFont(App.ERROR);
        emailError.setForeground(App.ErrorColor);
        gbc.gridx = 1;
        gbc.gridy = 7;
        emailError.setVisible(false);
        inputPanel.add(emailError, gbc);

        JPanel paddingPanel2 = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.BOTH;
        paddingPanel2.setOpaque(false);
        inputPanel.add(paddingPanel2,gbc);

        loginButton = new JButton("Already have an account? Login here!");
        loginButton.addActionListener(new Register());
        loginButton.setFont(App.BUTTON);
        loginButton.setForeground(App.ButtonText);
        loginButton.setBackground(App.ButtonColor);
        loginButton.setBorder(App.buttonBorder);
        loginButton.setOpaque(true);
        gbc.insets = new Insets(30,50,15,50);
        gbc.gridx = 0;
        gbc.gridy = 9;
        inputPanel.add(loginButton, gbc);

        registerButton = new JButton("Register!");
        registerButton.addActionListener(new Register());
        registerButton.setFont(App.BUTTON);
        registerButton.setForeground(App.ButtonText);
        registerButton.setBackground(App.ButtonColor);
        registerButton.setBorder(App.buttonBorder);
        registerButton.setOpaque(true);
        registerButton.setMaximumSize(null);
        gbc.gridx = 1;
        gbc.gridy = 9;
        inputPanel.add(registerButton, gbc);







        App.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)){
            App.frame.getContentPane().removeAll();;
            Login.main(null);

        }else if(e.getSource().equals(registerButton)){
            String tempUsername = usernameTextField.getText();
            char[] tempPassword = passwordField.getPassword();
            char[] tempPasswordVer = passwordVerField.getPassword();
            String tempEmail = emailTextField.getText();
            usernameError.setVisible(false);
            passwordError2.setVisible(false);
            passwordError3.setVisible(false);
            emailError.setVisible(false);

            int status = regVerification(tempUsername, tempPassword, tempPasswordVer, tempEmail);
            // 1 - Username alredy exists
            // 2 - Password is wrong size
            // 3 - Passwords don't match
            // 4 - Email is not a vaild email
            // -1 - Works!

            if (status == 1){
                passwordVerField.setText("");
                usernameError.setVisible(true);
            }

            else if (status == 2){
                passwordVerField.setText("");
                passwordError2.setVisible(true);
            }

            if (status == 3){
                passwordVerField.setText("");
                passwordError3.setVisible(true);
            }

            if (status == 4){
                passwordVerField.setText("");
                emailError.setVisible(true);
            }

            else if(status == -1){
                
                

                String plaintextPassword = new String(tempPassword);
                String SQL = "INSERT INTO Users VALUES ( \"" + tempUsername + "\", \"" + plaintextPassword + "\", \"" + tempEmail + "\")";
                SQLRequest.SQLUpdate(SQL);




                JOptionPane.showMessageDialog(App.frame, "Account successfully created");
                App.frame.getContentPane().removeAll();
                Login.main(null);
            }
            
        
    
        }
    }
}