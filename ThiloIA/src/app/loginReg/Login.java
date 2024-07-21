package app.loginReg;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JButton registerButton, loginButton;
    public static void main(String[] args) {
        

        // Declare and initalise login frame
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

        JTextField usernameTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(usernameTextField, gbc);

        JLabel password = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(password, gbc);

        JPasswordField passwordField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passwordField, gbc);

        registerButton = new JButton("Don't have an account? Regester here!");
        registerButton.addActionListener(new Login());
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(registerButton, gbc);

        loginButton = new JButton("Login!");
        loginButton.addActionListener(new Login());
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(loginButton, gbc);







        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)){


        }else if(e.getSource().equals(registerButton)){
        Register.main(null);

        }
    
    }
}

