/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Lasitha
 */
public class MidiPlayer {

    MidiChannel[] mChannels;
    
    public MidiPlayer() {
        try {
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();
            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            
            mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument
        } catch (MidiUnavailableException ex) {
            System.err.println(ex);
        }
    }

    public void start(int range) {
        int minNote = 55;
        int rand1 = 0, rand2 = 0;
        while (rand1 == rand2) {
            rand1 = (int) (Math.random() * (range + 1) + minNote);
            rand2 = (int) (Math.random() * (range + 1) + minNote);
        }
        playTwoNotes(rand1, rand2);
    }
    
    private void playTwoNotes(int note1, int note2){
        mChannels[0].noteOn(note1, 100);
        wait(1000);
        mChannels[0].noteOff(note1);
        wait(1000);
        mChannels[0].noteOn(note2, 100);
        wait(1000);
        mChannels[0].noteOff(note2);
    }
    
    private void wait(int t){
        try{
            Thread.sleep(t);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
