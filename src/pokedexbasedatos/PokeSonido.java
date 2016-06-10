/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexbasedatos;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author usuario
 */
public class PokeSonido {
    public void music(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Sonidos/LabMusicMod.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
}
