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
    private TreeMap<Integer,String> map;
    
    private NoteMap(){
        map=new TreeMap();
    }
    
    public static NoteMap getMap(){
        if(instance==null){
            return instance=new NoteMap();
        }
        return instance;
    }
    
}
