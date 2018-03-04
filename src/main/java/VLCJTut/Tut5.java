package VLCJTut;

/**
 * Created by lokesh y r on 2/28/2018.
 */
import uk.co.caprica.vlcj.binding.internal.libvlc_logo_position_e;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.Equalizer;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Tut5 {
    private final JFrame fr;private final JPanel appearanceCol;private final JPanel controlCol;
    private final JButton pauseBtn,rewindBtn,forwardBtn;private static JFileChooser fchoose;
    private final EmbeddedMediaPlayerComponent emc;private static File select;
    public Tut5()
    {
         fr=new JFrame("VPdemo5");
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
        float[] ampVals={-9.6f,-9.f,5.6f,4.0f,2.4f,11.2f,-7.8f,-7.8f,16.0f,16.0f};
        fchoose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int retVal=fchoose.showOpenDialog(null);
        if(retVal==fchoose.APPROVE_OPTION){
            select=fchoose.getSelectedFile();
        }
        MediaPlayerFactory mpf=emc.getMediaPlayerFactory();
        Equalizer equalizer=mpf.newEqualizer();
        equalizer.setPreamp(8.0f);
        equalizer.setAmps(ampVals);
        emc.getMediaPlayer().setEqualizer(equalizer);

        emc.getMediaPlayer().playMedia(select.getAbsolutePath());

    }
    public static void main(String[] args) {

        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tut5();
            }
        });
    }
}
