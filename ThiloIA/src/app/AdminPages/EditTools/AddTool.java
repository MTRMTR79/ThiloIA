package app.AdminPages.EditTools;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import app.Classes.SQLRequest;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTool implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JLabel emptyError;
    private static JTextField itemNameTextField;
    private static JButton confirm, backButton;
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
        backButton.addActionListener(new AddTool());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Add Tools");
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

        JLabel itemName = new JLabel("Item Name:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        subPanel.add(itemName, gbc);

        itemNameTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemNameTextField, gbc);

        emptyError = new JLabel("Please add a name");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 1;
        gbc.gridy = 2;
        emptyError.setVisible(false);
        subPanel.add(emptyError, gbc);


        confirm = new JButton("Add");
        confirm.addActionListener(new AddTool());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        subPanel.add(confirm, gbc);

        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirm)){
            if(itemNameTextField.getText().isEmpty()){
                emptyError.setVisible(true);
            }
            else{
                String name = itemNameTextField.getText();
                String SQL = "INSERT INTO Items (ItemName) VALUES (\"" + name + "\")";
                SQLRequest.SQLUpdate(SQL);
                itemNameTextField.setText("");
                JOptionPane.showMessageDialog(frame, "Item successfully added");
        
            }
        }else if(e.getSource().equals(backButton)){
            EditToolsMenu.main(null);
        }
    }
}