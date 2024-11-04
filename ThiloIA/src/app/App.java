
//Template//

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;


// public class _______ {
//     private static JFrame frame;
//     private static JPanel  panel;
//     public static void main(String[] args) {
        

//         // Declare and initalise login frame
//         frame = new JFrame(); 
//         frame.setSize(1000, 600);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setTitle("IH Inventory Management");
//         frame.setLocationRelativeTo(null);

//         panel = new JPanel();
//         frame.add(panel);



//         frame.setVisible(true);

//     }
// }


package app;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import app.loginReg.Login;


public class App {
    public static JFrame frame;
    public static Font  TITLE  = new Font(Font.SANS_SERIF, Font.PLAIN,  30);
    public static Font  DEFAULT  = new Font(Font.SANS_SERIF, Font.PLAIN,  18);
    public static Font  ERROR  = new Font(Font.SANS_SERIF, Font.PLAIN,  15);
    public static Font BUTTON = new Font(Font.SANS_SERIF, Font.PLAIN,  18);
    public static Font TABLE = new Font(Font.SANS_SERIF, Font.PLAIN,  15);
    public static Color MAIN = new Color(00,00,96);
    public static Color PanelBackground = Color.WHITE;
    public static Color TitleColor = MAIN;
    public static Color DefaultTextColor = MAIN;
    public static Color ErrorColor = Color.RED;
    public static Color ButtonColor = MAIN;
    public static Color ButtonBorderColor = ButtonColor;
    public static Color ButtonText = Color.WHITE;
    public static Color TableText = Color.BLACK;
    public static Dimension LogoutButtonSize = new Dimension(90,30);
    public static Dimension BackButtonSize = new Dimension(40,30);
    public static Border buttonBorder = BorderFactory.createLineBorder(ButtonBorderColor);
    public static Border JTextFieldBorder = BorderFactory.createLineBorder(MAIN);
    public static Border JComboBoxBorder = null;

    
    public static void main(String[] args) throws Exception {
        frame = new JFrame(); 
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IH Inventory Management");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setMinimumSize(new Dimension());

        Login.main(null); 
    }
}
