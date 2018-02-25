package VLCJTut;

import uk.co.caprica.vlcj.binding.LibVlc;
import  uk.co.caprica.vlcj.discovery.NativeDiscovery;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Created by lokesh y r on 2/12/2018.
 */
public class Tut1 {
    //private final JFrame frame;

    public static void main(String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                new Tut1();
            }
        });
    }
    public Tut1()
    {
        /*standard code , produces media player window with grey background */
        JFrame frame=new JFrame("New Player ");
        frame.setBounds(100,100,900,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
