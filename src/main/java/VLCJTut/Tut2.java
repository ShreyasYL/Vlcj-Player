package VLCJTut;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Created by lokesh y r on 2/13/2018.
 */
public class Tut2 {
    //output window has a black background unlike the previously used example

    public Tut2(String str)
    {
        JFrame frame=new JFrame("Video Player 2");
        frame.setBounds(100,100,600,400);
        EmbeddedMediaPlayerComponent empc=new EmbeddedMediaPlayerComponent();// create instance of Embedded Media Player Component
        frame.setContentPane(empc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        empc.getMediaPlayer().playMedia(str);
    }

    public static void main(String[] args) {
        new NativeDiscovery().discover();
        final String str="C:\\Users\\lokesh y r\\Videos\\TVS.mp4";
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Tut2(str);
            }
        });

    }

}
