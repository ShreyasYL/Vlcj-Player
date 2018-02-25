package VLCJTut;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_marquee_position_e;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.Marquee;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by lokesh y r on 2/18/2018.
 */
public class Tut4 {
    private final JFrame fr;private final JPanel appearanceCol;private final JPanel controlCol;
    private final JButton pauseBtn,rewindBtn,forwardBtn;
    private final EmbeddedMediaPlayerComponent emc;
    public Tut4()
    {
        fr=new JFrame("Video Player 3");
        appearanceCol=new JPanel(); //creates a panel within a frame appearance rendering
        appearanceCol.setLayout(new BorderLayout());
        emc=new EmbeddedMediaPlayerComponent();
        appearanceCol.add(emc);
        controlCol=new JPanel(); //control panel
        pauseBtn=new JButton("Pause/Play"); //pause button
        rewindBtn=new JButton("Rewind");//rewind button
        forwardBtn=new JButton("Forward");//forward button

        controlCol.add(pauseBtn); //Add
        controlCol.add(rewindBtn);  // Buttons to
        controlCol.add(forwardBtn);  //the Panel
        pauseBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                emc.getMediaPlayer().pause();
            }
        });
        rewindBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emc.getMediaPlayer().skip(-100000);
            }
        });
        forwardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emc.getMediaPlayer().skip(100000);
            }
        });
        appearanceCol.add(controlCol,BorderLayout.SOUTH); //add first panel to second
        fr.setContentPane(appearanceCol); //add control panel to frame */

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fr.setBounds(0,0,1080,700);
        fr.setVisible(true);
        emc.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter(){
            private void closeWindow(){
                fr.dispatchEvent(new WindowEvent(fr, WindowEvent.WINDOW_CLOSING));
            }
            public void playing(MediaPlayer mediaPlayer)
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        fr.setTitle(String.format("Media Player -%s",emc.getMediaPlayer().getMediaMeta().getTitle()));
                    }
                });
            }
            public void error(MediaPlayer mediaPlayer)
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JOptionPane.showMessageDialog(fr,"Something went wrong!");
                        closeWindow();
                    }
                });
            }
            public void finished(MediaPlayer mediaPlayer)
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        closeWindow();
                    }
                });
            }
        });
        Marquee marquee=Marquee.marquee().text("VLCJ").position(libvlc_marquee_position_e.top).colour(Color.white).size(50).enable().opacity(2000);
        marquee.apply(emc.getMediaPlayer());
        //emc.getMediaPlayer().setMarquee(marquee);
        emc.getMediaPlayer().playMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");

    }
    public static void main(String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tut4();
            }
        });
    }
}
