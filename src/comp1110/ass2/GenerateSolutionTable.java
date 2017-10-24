package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Created by Serlina on 2016/10/11.
 * @author Honggu Lin
 */
public class GenerateSolutionTable {
    /**
     * generate a table file that contain all the correct solutions

     */
    static public void generateSolutionTable(){
        ArrayList<Character> pos = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X'));
        ArrayList<Character> pie = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L'));
        ArrayList<Character> ori = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L'));

        ArrayList<ArrayList<String>> aa = new ArrayList<>();


        for(char pi: pie){
            ArrayList<String> a = new ArrayList<>();
            for(char po: pos){
                for(char o: ori){
                    String temp = "" + po + pi + o;
                    if(!LinkGame.isPiecePlacementWellFormed(temp)) continue;
                    a.add(temp);
                }
            }
            aa.add(a);
        }

        ArrayList<String> sol = new ArrayList<>();
        for(String s0: aa.get(0)){
            for(String s1: aa.get(1)){
                String t1 = s0 + s1;
                if(!LinkGame.isPlacementValid(t1)) continue;
                for(String s2: aa.get(2)){
                    String t2 = s0 + s1 + s2;
                    if(!LinkGame.isPlacementValid(t2)) continue;
                    for(String s3: aa.get(3)){
                        String t3 = s0 + s1 + s2 + s3;
                        if(!LinkGame.isPlacementValid(t3)) continue;
                        for(String s4: aa.get(4)){
                            String t4 = s0 + s1 + s2 + s3 + s4;
                            if(!LinkGame.isPlacementValid(t4)) continue;
                            for(String s5: aa.get(5)){
                                String t5 = s0 + s1 + s2 + s3 + s4 + s5;
                                if(!LinkGame.isPlacementValid(t5)) continue;
                                for(String s6: aa.get(6)){
                                    String t6 = s0 + s1 + s2 + s3 + s4 + s5 + s6;
                                    if(!LinkGame.isPlacementValid(t6)) continue;
                                    for(String s7: aa.get(7)){
                                        String t7 = s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7;
                                        if(!LinkGame.isPlacementValid(t7)) continue;
                                        for(String s8: aa.get(8)){
                                            String t8 = s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
                                            if(!LinkGame.isPlacementValid(t8)) continue;
                                            for(String s9: aa.get(9)){
                                                String t9 = s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9;
                                                if(!LinkGame.isPlacementValid(t9)) continue;
                                                for(String s10: aa.get(10)){
                                                    String t10 = s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + s10;
                                                    if(!LinkGame.isPlacementValid(t10)) continue;
                                                    for(String s11: aa.get(11)){
                                                        String t = s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + s10 + s11;
                                                        if(LinkGame.isPlacementValid(t)){
                                                            sol.add(t);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
