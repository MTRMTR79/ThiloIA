package app.AdminPages.EditTools;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.App;
import app.Classes.SQLRequest;
import app.Menus.AdminMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddTool implements ActionListener {
    private static JPanel  panel;
    private static JLabel emptyError, itemGroup;
    private static JTextField itemNameTextField;
    private static JComboBox<String> itemTypeComboBox, itemGroupComboBox;
    private static JButton confirm, backButton;
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

        JLabel itemType = new JLabel("Item Type:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        subPanel.add(itemType, gbc);

        String SQL = "SELECT DISTINCT ItemType FROM ItemGroups";
        Queue<String> itemTypeQueue = new LinkedList<>();
        itemTypeQueue.add("-");
        ResultSet result = SQLRequest.SQLQuery(SQL);

        try {
            while (result.next()){
                itemTypeQueue.add(result.getString("ItemType"));
            }
        } catch (SQLException ex) {
            System.out.println("SQL BROKEN " + ex.getMessage());
            JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
        }

        int Length = itemTypeQueue.size();
        String[] itemTypes = new String[Length];

        for (int i=0; i<(Length);i++){
            itemTypes[i] = itemTypeQueue.remove();
        }


        itemTypeComboBox = new JComboBox<String>(itemTypes);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        itemTypeComboBox.addActionListener(new AddTool());
        subPanel.add(itemTypeComboBox, gbc);

        itemGroup = new JLabel("Item Group:");
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        itemGroup.setVisible(false);
        subPanel.add(itemGroup, gbc);

        String[] ItemGroup = new String[] {"-"};
        itemGroupComboBox = new JComboBox<String>(ItemGroup);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        itemGroupComboBox.setVisible(false);
        subPanel.add(itemGroupComboBox, gbc);

        confirm = new JButton("Add");
        confirm.addActionListener(new AddTool());
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        subPanel.add(confirm, gbc);

        App.frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirm)){
            if(itemNameTextField.getText().isEmpty()){
                emptyError.setVisible(true);
            }
            else{
                String name = itemNameTextField.getText();
                String type = itemTypeComboBox.getSelectedItem().toString();
                String group = itemGroupComboBox.getSelectedItem().toString();
                String SQL;
                if (type.equals("-")){
                    SQL = "INSERT INTO Items (`ItemName`) VALUES (\"" + name + "\")";
                }else if (group.equals("-")){
                    SQL = "INSERT INTO Items (`ItemName`, `Type`) VALUES (\"" + name + "\", \""+ type + "\")";
                } else {
                    SQL = "INSERT INTO Items (`ItemName`, `Type`, `Group`) VALUES (\"" + name + "\", \""+ type + "\", \"" + group + "\")";
                }
                System.out.println(SQL);
                SQLRequest.SQLUpdate(SQL);
                itemNameTextField.setText("");
                JOptionPane.showMessageDialog(App.frame, "Item successfully added");
        
            }
        }else if(e.getSource().equals(backButton)){
            App.frame.getContentPane().removeAll();
            AdminMenu.main(null);
        }else if(e.getSource().equals(itemTypeComboBox)){
            itemGroupComboBox.removeAllItems();
            String SQL = "SELECT ItemGroup FROM ItemGroups WHERE ItemType = \"" + itemTypeComboBox.getSelectedItem() + "\"";
            Queue<String> itemGroupQueue = new LinkedList<>();
            itemGroupQueue.add("-");
            ResultSet result = SQLRequest.SQLQuery(SQL);

            try {
                while (result.next()){
                    itemGroupQueue.add(result.getString("ItemGroup"));
                }
            } catch (SQLException ex) {
                System.out.println("SQL BROKEN " + ex.getMessage());
                JOptionPane.showMessageDialog(App.frame, "Error, server down. Please try again later.");
            }

            int Length = itemGroupQueue.size();
            String[] itemGroups = new String[Length];

            for (int i=0; i<(Length);i++){
                itemGroups[i] = itemGroupQueue.remove();
                System.out.println(itemGroups[i]);
            }
            for (int i=0; i<(Length);i++){
            itemGroupComboBox.addItem(itemGroups[i]);
            }
            itemGroupComboBox.setVisible(true);
            itemGroup.setVisible(true);
        }
    }
}