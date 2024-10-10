
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

import javax.swing.JFrame;

import app.loginReg.Login;

//TODO - Fonts  --> Font  f1  = new Font(Font.SANS_SERIF, Font.PLAIN,  20);
public class App {
    public static JFrame frame;
    
    public static void main(String[] args) throws Exception {
        frame = new JFrame(); 
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IH Inventory Management");
        frame.setLocationRelativeTo(null);
        Login.main(null); 
    }
}
