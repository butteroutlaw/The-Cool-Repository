import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.*;

public class homePageui {
	public homePage() {
        JFrame frame = new JFrame("Home Page");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260,210);

        ImageIcon donut = new ImageIcon(getClass().getResource("space donut.jpg"));
        
        JLabel donutLabel = new JLabel("Thanks for Participating", donut, JLabel.CENTER);
        frame.add(donutLabel);
        frame.setVisible(true);
    
    public static void main (String[] args} {
     
    	SwingUtilities.invokeLater (
            new Runnable () {
                public void run() {
                     public void run() {
                        new homePage();
                    }
             }
        );
            
            }   
    
}