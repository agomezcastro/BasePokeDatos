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
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/LabMusic.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void bulbasaur(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Bulbasaur.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void charmander(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Charmander.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void squirlte(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Squirtle.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void pikachu(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Pikachu.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void pidgey(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Pidgey.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void rattata(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Rattata.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void caterpie(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Caterpie.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
    
    public void rhydon(){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/Rhydon.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        System.out.println("Error with playing sound.");
    }
    }
}
