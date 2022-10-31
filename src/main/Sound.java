package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURLS[] = new URL[30];

    public Sound(){

        soundURLS[0] = getClass().getResource("/assets/sound/BlueBoyAdventure.wav");
        soundURLS[1] = getClass().getResource("/assets/sound/coin.wav");
        soundURLS[2] = getClass().getResource("/assets/sound/powerup.wav");
        soundURLS[3] = getClass().getResource("/assets/sound/unlock.wav");
        soundURLS[4] = getClass().getResource("/assets/sound/fanfare.wav");
    }

    public void setFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURLS[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

        clip.stop();
    }
}
