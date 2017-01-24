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

    private static MidiPlayer player;
    private int currentNote1;
    private int currentNote2;
    private MidiChannel[] mChannels;
    private final int WAIT_TIME=1000;

    /*
    * Constructor
    * This create Midi Synthesizer object which can be used to play notes we need.
    */
    private MidiPlayer() {
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

    /*
    * Class has to be singleton to prevent creating more than one MIDI Synthesizer objects.
    */
    public static MidiPlayer getPlayer(){
        if(player==null){
            return new MidiPlayer();
        }
        return player;
    }

    /*
    * Start new question by playing two random notes within the user's range.
    */
    public void startNewQuestion(int range) {
        int minNote = 55;
        int rand1 = 0, rand2 = 0;
        while (rand1 == rand2) {
            rand1 = (int) (Math.random() * (range + 1) + minNote);
            rand2 = (int) (Math.random() * (range + 1) + minNote);
        }
        playTwoNotes(rand1, rand2);
    }

    /*
    * Play the two notes in current question again
    */
    public void playItAgain(){
        playTwoNotes(currentNote1,currentNote2);
    }

    /*
    * Play note 1 and note two for time of 'WAIT_TIME'
    */
    private void playTwoNotes(int note1, int note2){
        currentNote1=note1;
        currentNote2=note2;
        mChannels[0].noteOn(note1, 100);
        hold();
        mChannels[0].noteOff(note1);
        hold();
        mChannels[0].noteOn(note2, 100);
        hold();
        mChannels[0].noteOff(note2);
    }

    /*
    * Stop doing anything for 'WAIT_TIME'
    */
    private void hold(){
        try{
            Thread.sleep(WAIT_TIME);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
    * get the first note in current question
    */
    public int getCurrentNote1(){
        return currentNote1;
    }

    /*
    * get the second note in current question
    */
    public int getCurrentNote2(){
        return currentNote2;
    }
}
