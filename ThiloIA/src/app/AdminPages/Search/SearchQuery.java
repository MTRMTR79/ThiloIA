package app.AdminPages.Search;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.App;
import app.Menus.AdminMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchQuery implements ActionListener {
    private static JPanel  panel;
    private static JLabel emptyError;
    private static JTextField searchQuery;
    private static JButton confirm, backButton;
    private static JComboBox<String> searchType;
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

        backButton = new JButton("←");
        backButton.setPreferredSize(App.BackButtonSize);
        backButton.addActionListener(new SearchQuery());
        backButton.setFont(App.BUTTON);
        backButton.setForeground(App.ButtonText);
        backButton.setBackground(App.ButtonColor);
        backButton.setBorder(App.buttonBorder);
        backButton.setOpaque(true);
        buttonPanel.add(backButton);

        JLabel header = new JLabel("Search");
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

        searchQuery = new JTextField("Search");
        searchQuery.setFont(App.DEFAULT);
        searchQuery.setForeground(App.DefaultTextColor);
        searchQuery.setBorder(App.JTextFieldBorder);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2;
        subPanel.add(searchQuery, gbc);

        String[] searchtypes = new String[]{"Item Name", "Username", "Loans"}; //TODO - add type, group, and ItemID
        searchType = new JComboBox<String>(searchtypes);
        searchType.setFont(App.DEFAULT);
        searchType.setForeground(App.DefaultTextColor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        subPanel.add(searchType, gbc);


        confirm = new JButton("Search");
        confirm.addActionListener(new SearchQuery());
        confirm.setFont(App.BUTTON);
        confirm.setForeground(App.ButtonText);
        confirm.setBackground(App.ButtonColor);
        confirm.setBorder(App.buttonBorder);
        confirm.setOpaque(true);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        subPanel.add(confirm, gbc);

        emptyError = new JLabel("Please add a search term.");
        emptyError.setFont(App.ERROR);
        emptyError.setForeground(App.ErrorColor);
        gbc.insets = new Insets(5,50,5,50);
        gbc.gridx = 1;
        gbc.gridy = 2;
        emptyError.setVisible(false);
        subPanel.add(emptyError, gbc);
        

        App.frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirm)){
            if(searchQuery.getText().isEmpty()){
                emptyError.setVisible(true);
            }
            else{
                String query = searchQuery.getText();
                String queryType = searchType.getSelectedItem().toString();
                App.frame.getContentPane().removeAll();
                SearchResults.main(null, query, queryType);
                
            }
        }else if(e.getSource().equals(backButton)){
            App.frame.getContentPane().removeAll();
            AdminMenu.main(null);
           
        }
    }
}