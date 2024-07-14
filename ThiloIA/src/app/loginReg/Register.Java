import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Regester {
    private static JFrame frame;
    private static JPanel  panel;
    public static void main(String[] args) {
        

        // Declare and initalise login frame
        frame = new JFrame(); 
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IH Inventory Management");
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        frame.add(panel);



        frame.setVisible(true);

    }
}