package app.loginReg;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;





public class Register implements ActionListener{
    private static JFrame frame;
    private static JPanel  panel;
    private static JButton registerButton, loginButton;
    private static JTextField usernameTextField, emailTextField;
    private static JPasswordField passwordField, passwordVerField;
    private static JLabel usernameError, passwordError2, passwordError3, emailError;


    public static int regVerification(String username, char[] password, char[] passwordVer, String email){
        
        //TODO - Insert search for username - return 1

        if (password.length < 8 || password.length > 64){
            return 2;
        }
        
        if(password.length != passwordVer.length){
            return 3;
        } else{
            for(int i=0; i<(password.length); i++){
                if (!password[i].equals(passwordVer[i])){
                    return 3;
                }
            }
        }
        
        
        //TODO - Email regex
    
        return -1;
    }


    public static void main(String[] args) {
        

        frame = new JFrame(); 
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IH Inventory Management");
        frame.setLocationRelativeTo(null);

        //Initialise GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setSize(900,500);
        frame.add(panel);

        JLabel title = new JLabel("Welcome to IHIM");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, gbc);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        gbc.gridy = 1;
        panel.add(inputPanel,gbc);

        JLabel username = new JLabel("Username:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(username, gbc);

        usernameTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(usernameTextField, gbc);

        usernameError = new JLabel("Username is already in use, please use another one.");
        gbc.gridx = 1;
        gbc.gridy = 1;
        usernameError.setVisible(false);
        inputPanel.add(usernameError, gbc);

        JLabel password = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(password, gbc);

        passwordField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(passwordField, gbc);

        passwordError2 = new JLabel("Password is too short, password must be 8-64 characters.");
        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordError2.setVisible(false);
        inputPanel.add(passwordError2, gbc);

        JLabel passwordVer = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(passwordVer, gbc);

        passwordVerField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(passwordVerField, gbc);

        passwordError3 = new JLabel("Passwords don't match");
        gbc.gridx = 1;
        gbc.gridy = 5;
        passwordError3.setVisible(false);
        inputPanel.add(passwordError3, gbc);

        JLabel email = new JLabel("Email:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(email, gbc);

        emailTextField = new JTextField("@gapps.uwcsea.edu.sg");
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(emailTextField, gbc);

        emailError = new JLabel("Email not Valid.");
        gbc.gridx = 1;
        gbc.gridy = 7;
        emailError.setVisible(false);
        inputPanel.add(emailError, gbc);

        loginButton = new JButton("Already have an account? Login here!");
        loginButton.addActionListener(new Register());
        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(loginButton, gbc);

        registerButton = new JButton("Register!");
        registerButton.addActionListener(new Register());
        gbc.gridx = 1;
        gbc.gridy = 8;
        inputPanel.add(registerButton, gbc);







        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)){
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
                JOptionPane.showMessageDialog(frame, "Account successfully created");
            }
            
        
    
        }
    }
}