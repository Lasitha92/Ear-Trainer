/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.util.TreeMap;

/**
 *
 * @author Lasitha
 */
public class NoteMap {
    private static NoteMap instance;
    private TreeMap<Integer,String> map1;
    private TreeMap<String,Integer> map2;
    private final int MIDDLE_C = 60;

    private String[] octave={"C","C#/Db","D","D#/Eb","E","F","F#/Gb","G","G#/Ab","A","A#/Bb","B"};

    private NoteMap(){
        map1=new TreeMap();
        map2=new TreeMap();

        /*
         * Logic for creating the note map goes here.
         * One way of implementing it is going from 0 key to 120 key.
         * But this way is more understandable, starting with middle C(note-60) and going down and going up from there.
         */

        int j=11;
        int k=5;
        for(int i=MIDDLE_C;i>0;i--){
            String noteName=octave[j]+String.valueOf(k);
            map1.put(i,noteName);
            map2.put(noteName,i);
            if(j==0){
                j=11;
                k--;
            }else{
                j--;
            }
        }

        int x=0;
        int y=5;
        for(int i=MIDDLE_C;i<120;i++){
            String noteName=octave[j]+String.valueOf(y);
            map1.put(i,noteName);
            map2.put(noteName,i);
            if(x==11){
                x=0;
                y++;
            }else{
                x++;
            }
        }
    }

    /*
    * This class is a Singleton class
    * It prevents creating the map more than once.
    */
    public static NoteMap getMap(){
        if(instance==null){
            return instance=new NoteMap();
        }
        return instance;
    }
}
