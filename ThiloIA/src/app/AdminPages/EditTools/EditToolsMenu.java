package app.AdminPages.EditTools;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import app.Menus.AdminMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditToolsMenu implements ActionListener {
    private static JFrame frame;
    private static JPanel  panel;
    private static JButton addItem, removeItem, editItem, backButton;
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
        backButton.addActionListener(new EditToolsMenu());
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Edit tools");
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(subPanel, gbc);

        addItem = new JButton("Add an item");
        addItem.addActionListener(new EditToolsMenu());
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 30, 10, 30);
        subPanel.add(addItem, gbc);

        removeItem = new JButton("Remove an item");
        removeItem.addActionListener(new EditToolsMenu());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(removeItem, gbc);

        editItem = new JButton("Edit an item");
        editItem.addActionListener(new EditToolsMenu());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        subPanel.add(editItem, gbc);

        


        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addItem)){
            AddTool.main(null);

        } else if(e.getSource().equals(removeItem)){

        } else if(e.getSource().equals(editItem)){
        
        }else if(e.getSource().equals(backButton)){
            AdminMenu.main(null);
        }
    
    }
}