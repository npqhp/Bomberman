package uet.oop.bomberman.JavaSound;

import uet.oop.bomberman.graphics.SpriteSheet;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
    private AudioInputStream audioStream;
    private Clip clip;
    private boolean repeat = false;

    public Sound(String s) {
        URL a = SpriteSheet.class.getResource("/Sound/" + s);
        //File file = new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-2\\res\\Sound\\" + s);
        try {
            audioStream = AudioSystem.getAudioInputStream(a);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (clip.getMicrosecondLength() == clip.getMicrosecondPosition()) {
            clip.setMicrosecondPosition(0);
        }
        clip.start();
    }

    public void start1() {
        clip.start();
    }

    public void repeat(int t) {
        clip.loop(t);
    }

    public void startnew() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void pause() {
        repeat = false;
    }

    public void stop() {
        clip.stop();
    }
}
