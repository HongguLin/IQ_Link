package comp1110.ass2;

import java.util.*;

import static comp1110.ass2.Unit.Ball_DB;
import static comp1110.ass2.Unit.Ring_DB;
import static comp1110.ass2.Unit.Ring_NO;


/**
 * This class provides the text interface for the Link Game
 *
 * The game is based directly on Smart Games' IQ-Link game
 * (http://www.smartgames.eu/en/smartgames/iq-link)
 * @author Honggu Lin,Yuxi Chen
 */
public class LinkGame {
    static int []habit = new int[24];
    static HashMap<Integer, Unit> habitOwner = new HashMap<>();

    /**
     * this function is to detect whether two piece placement is clash with each other
     * the input parameter is two unit a, unit b to detect
     * the return value is a boolean value,is these two unit is clashed then return true, else return false.
     * @author Honggu Lin
     * @param a
     * @param b
     * @return boolean
     */
    static boolean isClash(Unit a, Unit b){
        if(a.i == b.i){return true;}
        else if(a == Ring_NO || b == Ring_NO){return true;}
        else if(a.o2 == null && b.o2 == null){
            if(Math.abs((int)(a.o1.toString().charAt(0)) - (int)(b.o1.toString().charAt(0))) == 3){return false;}
            else return true;
        }else if(a.o2 != null && a.i == 1){
            if(b.o2 != null && b.i ==2){
                if((orient2Int(a.o1)+orient2Int(a.o2))%6 == (orient2Int(b.o1)+orient2Int(b.o2))%6){return false;}
                else return true;
            }else return true;

        }else if(b.o2 != null && b.i == 1){
            if(a.o2 != null && a.i ==2){
                if((orient2Int(a.o1)+orient2Int(a.o2))%6 == (orient2Int(b.o1)+orient2Int(b.o2))%6){return false;}
                else return true;
            }else return true;

        }else if(a.o2 != null && a.i == 2){
            if(Math.abs((int)(a.o1.toString().charAt(0)) - (int)(b.o1.toString().charAt(0))) == 3 || Math.abs((int)(a.o2.toString().charAt(0)) - (int)(b.o1.toString().charAt(0))) == 3){return false;}
            else return true;
        }else if(b.o2 != null && b.i == 2){
            if(Math.abs((int)(b.o1.toString().charAt(0)) - (int)(a.o1.toString().charAt(0))) == 3 || Math.abs((int)(b.o2.toString().charAt(0)) - (int)(a.o1.toString().charAt(0))) == 3){return false;}
            else return true;
        }
        return true;

    }

    /**
     * this function is to transfer each unit to the correct orientation
     * @author Honggu Lin
     * @param a
     * @param o
     * @return unite
     */
    static Unit unitTransform(Unit a, Orientation o){
        Unit u = a;
        if(u != Ring_NO && u != Ring_DB && u != Ball_DB){
            if(o == Orientation.A || o == Orientation.B || o == Orientation.C || o == Orientation.D || o == Orientation.E || o == Orientation.F){
                u = new Unit(int2Orient((orient2Int(u.o1) + orient2Int(o))%6), a.i);
            }else {
                u = new Unit(int2Orient((orient2Int(o) - orient2Int(u.o1))%6), a.i);
            }
            //u.o1 = int2Orient((orient2Int(u.o1) + orient2Int(o))%6);
        }else if(u == Ring_DB){
            if(o == Orientation.A || o == Orientation.B || o == Orientation.C || o == Orientation.D || o == Orientation.E || o == Orientation.F) {
                u = new Unit(int2Orient((orient2Int(u.o1) + orient2Int(o)) % 6), int2Orient((orient2Int(u.o2) + orient2Int(o)) % 6), a.i);
            }
        }else if(u == Ball_DB){
            if(o == Orientation.A || o == Orientation.B || o == Orientation.C || o == Orientation.D || o == Orientation.E || o == Orientation.F){
                u = new Unit(int2Orient((orient2Int(u.o1) + orient2Int(o)) % 6), int2Orient((orient2Int(u.o2) + orient2Int(o)) % 6), a.i);
            }else {
                u = new Unit(int2Orient((orient2Int(o) - orient2Int(u.o1))%6), int2Orient((orient2Int(o) - orient2Int(u.o2))%6),a.i);

                if(orient2Int(u.o1) > orient2Int(u.o2)){
                    Orientation t = u.o1;
                    u.o1 = u.o2;
                    u.o2 = t;
                }

            }
        }
        return u;
    }

    /**
     * this function is to match orientation to integer
     * Orientation.A, Orientation.B, ...,Orientation.L  to 0,1,...,11
     * @author Honggu Lin,adapted from the Linkgame class code of assignment 1
     * @param o
     * @return orientation
     */
    private static int orient2Int(Orientation o){
        int i = (int)(o.toString().charAt(0)) - 'A';
        return i;
    }

    /**
     * this function is to match integer to orientation
     * 0,1,...,11 to Orientation.A,Orientation.B,...,Orientation.L
     * @param i
     * @return orientation
     */
    static Orientation int2Orient(int i) {
        Orientation o;
        switch (i) {
            case 0: {
                o = Orientation.A;
                break;
            }
            case 1: {
                o = Orientation.B;
                break;
            }
            case 2: {
                o = Orientation.C;
                break;
            }
            case 3: {
                o = Orientation.D;
                break;
            }
            case 4: {
                o = Orientation.E;
                break;
            }
            default: {
                o = Orientation.F;
                break;
            }
        }
        return o;
    }


    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. X
     * - the second character is in the range A .. L
     * - the third character is in the range A .. F if the second character is A, otherwise
     *   in the range A .. L
     * @author Yuxi Chen
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    public static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 3: determine whether a piece placement is well-formed
        if(piecePlacement.charAt(0) >= 'A' && piecePlacement.charAt(0) <= 'X' && piecePlacement.charAt(1) =='A' && piecePlacement.charAt(1) <= 'L' && piecePlacement.charAt(2) >= 'A' && piecePlacement.charAt(2) <= 'F' )
            return true;
        else if(piecePlacement.charAt(0) >= 'A' && piecePlacement.charAt(0) <= 'X' && piecePlacement.charAt(1) > 'A' && piecePlacement.charAt(1) <= 'L' && piecePlacement.charAt(2) >= 'A' && piecePlacement.charAt(2) <= 'L' )
            return true;
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 12);
     *  - each piece placement is well-formed
     *  - no piece appears more than once in the placement
     * @author Yuxi Chen
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 4: determine whether a placement is well-formed
        if(placement == "" || placement == null) return false;
        if(placement.length() % 3 == 0 )
//            return false;
        {
            if (placement.charAt(0) >= 'A' && placement.charAt(0) <= 'X' && placement.charAt(1) >= 'A' && placement.charAt(1) <= 'L' && placement.charAt(2) >= 'A' && placement.charAt(2) <= 'L')
            {
                char[] placeArray = placement.toCharArray();
                int i = 0;
                while (i < placeArray.length) {
                    int j = i + 3;
                    while (j < placeArray.length)

                    {
                        if (placeArray[i + 1] == placeArray[j + 1])
                            return false;
                        j = j + 3;
                    }
                    i = i + 3;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Return a array of peg locations according to which pegs the given piece placement touches.
     * The values in the array should be ordered according to the units that constitute the
     * piece.
     * The code needs to account for the origin of the piece, the piece shape, and the piece
     * orientation.
     * @author Honggu Lin
     * @param piecePlacement A valid string describing a piece placement
     * @return An array of integers corresponding to the pegs which the piece placement touches,
     * listed in the normal order of units for that piece.
     */
    public static int[] getPegsForPiecePlacement(String piecePlacement) {
        // FIXME Task 6: determine the pegs touched by a piece placement
        if(!isPiecePlacementWellFormed(piecePlacement)) return null;
        int rtn[];
        int origin = piecePlacement.charAt(0)- 'A';
        Piece piece;
        Orientation orientation;
        switch (piecePlacement.charAt(1)){
            case 'A': piece = Piece.A;break;
            case 'B': piece = Piece.B;break;
            case 'C': piece = Piece.C;break;
            case 'D': piece = Piece.D;break;
            case 'E': piece = Piece.E;break;
            case 'F': piece = Piece.F;break;
            case 'G': piece = Piece.G;break;
            case 'H': piece = Piece.H;break;
            case 'I': piece = Piece.I;break;
            case 'J': piece = Piece.J;break;
            case 'K': piece = Piece.K;break;
            default:piece = Piece.L;
        }
        switch (piecePlacement.charAt(2)){
            case 'A': orientation = Orientation.A;break;
            case 'B': orientation = Orientation.B;break;
            case 'C': orientation = Orientation.C;break;
            case 'D': orientation = Orientation.D;break;
            case 'E': orientation = Orientation.E;break;
            case 'F': orientation = Orientation.F;break;
            case 'G': orientation = Orientation.G;break;
            case 'H': orientation = Orientation.H;break;
            case 'I': orientation = Orientation.I;break;
            case 'J': orientation = Orientation.J;break;
            case 'K': orientation = Orientation.K;break;
            default: orientation = Orientation.L;break;
        }
        rtn = Piece.getTranslatedTitlePositions(origin,piece,orientation);

        return rtn;
    }

    /**
     * this function is to match character to piece
     * A,B,...L to Piece.A, Piece.B,... Piece.L
     * @author Honggu Lin
     * @param c
     * @return piece
     */
    public static Piece char2piece(char c){
        switch (c){
            case 'A':{return Piece.A;}
            case 'B':{return Piece.B;}
            case 'C':{return Piece.C;}
            case 'D':{return Piece.D;}
            case 'E':{return Piece.E;}
            case 'F':{return Piece.F;}
            case 'G':{return Piece.G;}
            case 'H':{return Piece.H;}
            case 'I':{return Piece.I;}
            case 'J':{return Piece.J;}
            case 'K':{return Piece.K;}
            default:{return Piece.L;}
        }
    }

    /**
     * match character to orientation
     * A,B,...L to Orientation.A, Orientation.B,...,Orientation.L
     * @author Honggu Lin
     * @param c
     * @return orientation
     */
    public static Orientation char2orient(char c){
        switch (c){
            case 'A':{return Orientation.A;}
            case 'B':{return Orientation.B;}
            case 'C':{return Orientation.C;}
            case 'D':{return Orientation.D;}
            case 'E':{return Orientation.E;}
            case 'F':{return Orientation.F;}
            case 'G':{return Orientation.G;}
            case 'H':{return Orientation.H;}
            case 'I':{return Orientation.I;}
            case 'J':{return Orientation.J;}
            case 'K':{return Orientation.K;}
            default:{return Orientation.L;}
        }
    }


    /**
     * Determine whether a placement is valid.  To be valid, the placement must be well-formed
     * and each piece must correctly connect with each other.
     * @author Honggu Lin
     * @param placement A placement string
     * @return True if the placement is valid
     */
     public static boolean isPlacementValid(String placement) {
        // FIXME Task 7: determine whether a placement is valid
        //LinkGame linkGame  = new LinkGame();[

        Arrays.fill(habit, 0);
        if(isPlacementWellFormed(placement) == false){return false;}
        else {
            for (int i = 0;i < placement.length()/3; i++){
                String sub = placement.substring(i*3, (i+1) *3);
                int[] pegs = getPegsForPiecePlacement(sub);
                for(int j = 0; j<3;j++){
                    if(pegs==null ||pegs[j] == -1){return  false;}
                    else {
                        switch (habit[pegs[j]]){
                            case 2:{return false;}
                            case 0:{
                                habit[pegs[j]] ++;
                                Unit unit = unitTransform(char2piece(sub.charAt(1)).u[j], char2orient(sub.charAt(2)));
                                habitOwner.put(pegs[j], unit);
                                break;
                            }
                            case 1:{
                                habit[pegs[j]] ++;
                                Unit unit1 = unitTransform(char2piece(sub.charAt(1)).u[j], char2orient(sub.charAt(2)));
                                if(isClash(habitOwner.get(pegs[j]), unit1)){
                                    return false;
                                }else {
                                    habitOwner.remove(pegs[j]);
                                }
                                break;
                            }

                        }

                        /*
                        if(habit[pegs[j]] == 2){return false;}
                        else if(habit[pegs[j]] == 0){
                            habit[pegs[j]] ++;
                            Unit unit = unitTransform(char2piece(sub.charAt(1)).u[j], char2orient(sub.charAt(2)));
                            habitOwner.put(pegs[j], unit);
                        }else if(habit[pegs[j]] == 1){
                            habit[pegs[j]] ++;
                            if(isClash(habitOwner.get(pegs[j]), char2piece(sub.charAt(1)).u[j])){
                                return false;
                            }else {
                                habitOwner.remove(pegs[j]);
                            }
                        }
                        */
                    }
                }
            }
        }
        //for(int i = 0;i < habit.length;i++){
         //   if(habit[i]< 1 || habit[i] >2)return false;
        //}
        return true;
    }

    /**
     * Return an array of all solutions given a starting placement.
     * @author Honggu Lin
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static public String[] getSolutions(String placement) {
        // FIXME Task 10: determine all solutions to the game, given a particular starting placement

        ArrayList<Character> pi = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L'));
        ArrayList<Character> po = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X'));
        ArrayList<Character> pd = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L'));
        Set<Character> si = new HashSet<>(pi);
        Set<Character> so = new HashSet<>(po);
        Set<Character> sd = new HashSet<>(pd);

        ArrayList<ArrayList<String>> aa = new ArrayList<>();

        //first remove the piece that has already given in the placement string
        for(int i = 0;i < placement.length()/3;i++){
            String temp = placement.substring(i*3, (i+1)*3);
            char ti = temp.charAt(1);
            if(si.contains(ti)){
                si.remove(ti);
            }
        }
        //then find all the valid placement for the piece that not in the given placement string
        for(char ci: si){
            ArrayList<String> ai = new ArrayList<>();
            for(char co: so){
                for(char cd: sd){
                    String tp = placement + co + ci + cd;
                    String tp1 = ""+ co + ci + cd;
                    if(isPlacementValid(tp)){
                        ai.add(tp1);
                    }
                }
            }
            aa.add(ai);
        }
        int al = aa.size();
        ArrayList<String> solution = new ArrayList<>();

        //use depth first search to find the final valid placement
        switch (al){
            case 0:{
                if(isPlacementValid(placement)){
                    solution.add(placement);
                }
            }break;
            case 1: {
                for (String s0 : aa.get(0)) {
                    String temp = placement + s0;
                    if (isPlacementValid(temp)) {
                        solution.add(temp);
                    }
                }
            }break;
            case 2:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        String temp = placement + s0 + s1;
                        if(isPlacementValid(temp)){
                            solution.add(temp);
                        }
                    }
                }
            }break;
            case 3:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        for(String s2: aa.get(2)){
                            String temp = placement + s0 + s1 + s2;
                            if(isPlacementValid(temp)){
                                solution.add(temp);
                            }
                        }
                    }
                }
            }break;
            case 4:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        for(String s2: aa.get(2)){
                            for(String s3: aa.get(3)){
                                String temp = placement + s0 + s1 + s2 + s3;
                                if(isPlacementValid(temp)){
                                    solution.add(temp);
                                }
                            }
                        }
                    }
                }
            }break;
            case 5:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        for(String s2: aa.get(2)){
                            for(String s3: aa.get(3)){
                                for(String s4: aa.get(4)){
                                    String temp = placement + s0 + s1 + s2 + s3 + s4;
                                    if(isPlacementValid(temp)){
                                        solution.add(temp);
                                    }
                                }
                            }
                        }
                    }
                }
            }break;
            case 6:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        String a1 = placement + s0 + s1;
                        if(!isPlacementValid(a1))
                            continue;
                        for(String s2: aa.get(2)){
                            String a2 = placement + s0 + s1 + s2;
                            if(!isPlacementValid(a2))
                                continue;
                            for(String s3: aa.get(3)){
                                String a3 = placement + s0 + s1 + s2 + s3;
                                if(!isPlacementValid(a3))
                                    continue;
                                for(String s4: aa.get(4)){
                                    for(String s5: aa.get(5)){
                                        String temp = placement + s0 + s1 + s2 + s3 + s4 + s5;
                                        if(isPlacementValid(temp)){
                                            solution.add(temp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }break;
            case 7:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        String a1 = placement + s0 + s1;
                        if(!isPlacementValid(a1))
                            continue;
                        for(String s2: aa.get(2)){
                            String a2 = placement + s0 + s1 + s2;
                            if(!isPlacementValid(a2))
                                continue;
                            for(String s3: aa.get(3)){
                                String a3 = placement + s0 + s1 + s2 + s3;
                                if(!isPlacementValid(a3))
                                    continue;
                                for(String s4: aa.get(4)){
                                    String a4 = placement + s0 + s1 + s2 + s3 + s4;
                                    if(!isPlacementValid(a4))
                                        continue;
                                    for(String s5: aa.get(5)){
                                        String a5 = placement + s0 + s1 + s2 + s3 + s4 + s5;
                                        if(!isPlacementValid(a5))
                                            continue;
                                        for(String s6: aa.get(6)){
                                            String temp = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6;
                                            if(isPlacementValid(temp)){
                                                solution.add(temp);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }break;
            case 8:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        String a1 = placement + s0 + s1;
                        if(!isPlacementValid(a1))
                            continue;
                        for(String s2: aa.get(2)){
                            String a2 = placement + s0 + s1 + s2;
                            if(!isPlacementValid(a2))
                                continue;
                            for(String s3: aa.get(3)){
                                String a3 = placement + s0 + s1 + s2 + s3;
                                if(!isPlacementValid(a3))
                                    continue;
                                for(String s4: aa.get(4)){
                                    String a4 = placement + s0 + s1 + s2 + s3 + s4;
                                    if(!isPlacementValid(a4))
                                        continue;
                                    for(String s5: aa.get(5)){
                                        String a5 = placement + s0 + s1 + s2 + s3 + s4 + s5;
                                        if(!isPlacementValid(a5))
                                            continue;
                                        for(String s6: aa.get(6)){
                                            String a6 = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6;
                                            if(!isPlacementValid(a6))
                                                continue;
                                            for(String s7: aa.get(7)){
                                                String temp = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7;
                                                if(isPlacementValid(temp)){
                                                    solution.add(temp);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }break;
            case 9:{
                for(String s0: aa.get(0)){
                    for(String s1: aa.get(1)){
                        String a1 = placement + s0 + s1;
                        if(!isPlacementValid(a1))
                            continue;
                        for(String s2: aa.get(2)){
                            String a2 = placement + s0 + s1 + s2;
                            if(!isPlacementValid(a2))
                                continue;
                            for(String s3: aa.get(3)){
                                String a3 = placement + s0 + s1 + s2 + s3;
                                if(!isPlacementValid(a3))
                                    continue;
                                for(String s4: aa.get(4)){
                                    String a4 = placement + s0 + s1 + s2 + s3 + s4;
                                    if(!isPlacementValid(a4))
                                        continue;
                                    for(String s5: aa.get(5)){
                                        String a5 = placement + s0 + s1 + s2 + s3 + s4 + s5;
                                        if(!isPlacementValid(a5))
                                            continue;
                                        for(String s6: aa.get(6)){
                                            String a6 = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6;
                                            if(!isPlacementValid(a6))
                                                continue;
                                            for(String s7: aa.get(7)){
                                                String a7 = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7;
                                                if(!isPlacementValid(a7))
                                                    continue;
                                                for(String s8: aa.get(8)) {
                                                    String temp = placement + s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
                                                    if (isPlacementValid(temp)) {
                                                        solution.add(temp);
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
            }break;
        }

        int len = solution.size();
        String sol[] = new String[len];
        for(int i = 0;i<len;i++){
            sol[i] = solution.get(i);
        }
        System.out.println("placement: " + placement + "has solution: \n" + solution);
        return sol;
    }


    static final String[] PLACEMENTS = {
            "BAARBEUCAGDFWEBFFEIGDSHBDIFPJKJKCHLL",
            "BADPBAWCARDKTEBJFIKGDMHKBILFJFCKKOLH",
            "CAAJBLICFGDFQEKFFGBGESHBUIJXJHRKIHLL",
            "CADTBAWCAQDHKEEFFGRGCMHKCILPJHBKFHLE",
            "DAAGBKWCAQDHKEEPFFRGCFHEBIKSJBHKDOLL",
            "DADTBANCJGDFJEBPFFRGAFHEBIKVJJWKCHLD",
            "EAAIBLVCJRDCKEAPFLWGBTHAAIKCJGGKDMLK",
            "EADTBAQCIGDHBEEIFKHGCLHHWICEJLPKLNLG",
            "GABCBDVCJRDCIEATFIWGBEHLGICPJFQKIMLK",
            "GACIBLVCJKDEQEGFFGRGCSHBNIBCJFEKFPLF",
            "GAETBAJCFCDDBEEMFCDGLOHAXIAFJLPKEKLE",
            "GAFJBBECDNDCUEJSFJVGBCHGXIHLJAPKARLA",
            "IAABBGWCDODDFEEMFKPGLRHIKIHIJBHKFNLE",
            "IABGBBWCJQDLFEGSFJEGEUHIOIABJKKKDJLA",
            "IACUBACCGGDFQEKFFGBGESHBIIKXJHRKIHLL",
            "IADEBJRCKVDAWEBOFIUGISHBAIKLJAGKLCLG",
            "IAEVBADCDJDKBEEOFCGGFSHBFILNJBRKLKLE",
            "IAFUBACCGKDLPEIFFEHGLSHBAIKXJHQKEGLL",
            "JAACBDQCFPDFWEISFBRGKFHEGIIHJEDKKOLL",
            "JABHBCUCAPDEIEFEFLQGCMHKBIFXJHTKHRLI",
            "JACUBACCGKDLIECFFEQGHSHBAIKWJCHKLGLL",
            "JADDBDVCDRDCMECOFBWGBFHEJILNJLHKIGLB",
            "JAEBBGOCDGDFLEFSFJTGBVHIKIIJJGRKEILH",
            "JAFUBAICIKDLCELFFEQGDSHBAIKXJAGKLHLE",
            "KAADBDUCARDKWEBHFCPGGSHBIIKAJKGKLELK",
            "KABQBBUCAPDECELIFLHGHSHBXIAAJKFKEGLL",
            "KACUBAOCJHDFDEGGFFCGESHBKIDXJHQKEILD",
            "KADPBBBCDNDBFEGSFJGGFDHLXIAKJEWKHTLC",
            "KAEHBFTCAODEFEEVFBPGJMHKBIFDJERKLJLD",
            "KAFUBACCGGDFLEFIFCBGESHBOIAEJEQKGRLE"
    };

    /**
     * generate a random start string according to the solution table
     * @author Honggu Lin
     * @return a random start string
     */
    public static String generateRandomStart(){
        Random random = new Random();
        int i = random.nextInt(30);
        String temp = PLACEMENTS[i];
        int length = random.nextInt(7)+4;
        String rt = temp.substring(0,3*length);
        return rt;
    }

    /**
     * generate a random start string according to the difficulty
     * @author Yuxi Chen
     * @param diff is an integer.diff is the difficulty level of this game, level one is the lowest level while level 7 is the highest level.
     * @return a random start string. The length of the start string is according to the difficulty,
     * the more difficulty the game is , the shorter the start string is.
     */
     public static String genRanStartAccToDiff(int diff){
        if(diff<1 || diff>7) return null;
         Random random = new Random();
         int i = random.nextInt(30);
         String temp = PLACEMENTS[i];
         String rt = temp.substring(0,3*(11-diff));
         return rt;
    }

    public static void main(String[] args) {
        String []s = getSolutions("GGFSFJTLC");
    }



}

