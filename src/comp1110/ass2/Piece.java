package comp1110.ass2;



import static comp1110.ass2.Unit.*;

/**
 * Created by Serlina on 2016/8/15.
 * @author Honggu Lin
 */
public enum Piece {

    A(Ball_A, Ring_DB, Ball_D),
    B(Ball_A, Ring_NO, Ring_C),
    C(Ball_A, Ring_NO, Ring_B),
    D(Ball_A, Ring_NO, Ring_E),
    E(Ball_A, Ring_NO, Ring_A),
    F(Ball_A, Ring_NO, Ring_B),
    G(Ball_A, Ring_F, Ball_C),
    H(Ring_B, Ring_NO, Ring_C),
    I(Ball_A, Ball_DB, Ring_B),
    J(Ball_A, Ball_DB, Ring_A),
    K(Ball_A, Ring_E, Ball_B),
    L(Ball_A, Ring_E, Ring_D),;


    Unit[] u = new Unit[3];
    Piece(Unit u1, Unit u2, Unit u3) {
        u[0] = u1;
        u[1] = u2;
        u[2] = u3;
    }

    /**
     * The placement of a particular unit within a piece is described
     * in terms of:
     *  - the piece it is on,
     *  - where it i on the piece (its index),(1,0,2)
     *  - the origin of the piece (the place were index 0 is),
     *  - and the orientation of the piece.
     *
     *
     * The location of a piece is always in terms of where the origin (index 0)
     * is located on the 6x4 board.
     *
     * This method must return the location of the unit referred to by index.
     * In the case where the index is zero, it simply returns origin (since the
     * origin is unaffected by rotation).  Otherwise, it must calculate the
     * location and return that.
     *
     * For example, if the piece were A, index were 2, the origin were (1,1),
     * and the rotation were A, the method would return (2,1).
     *
     * （1,3）（2,3）（3,3）（4,3） （5,3） （6,3）
     *   （1,2）（2,2）（3，2）（4,2） （5,2） （6,2）
     * （0,1）（1,1）（2,1）（3,1） （4,1） （5,1）
     *   （0，0）（1,0）（2,0）（3,0） （4,0） （5,0）
     *
     *
     * If the indexed square is off the grid, -1 should be returned as the location.
     *
     * @param index  The square within the piece in question.  0 refers to the
     *               origin (top left) 1 to the top right, and 2 to the bottom left.
     * @param origin The location of the origin of the piece within the board.  0
     *               refers to the top left square of the 6x4 grid, 5 to the top right,
     *               18 to the bottom left and 23 to the bottom right.
     * @param orientation The orientation of the unit.  Orientation is encoded as
     *                    'A' : 0 degree
     *                    'B' : 60 degrees clockwise
     *                    'C' : 120
     *                    'D' : 180
     *                    'E' : 240
     *                    'F' : 360
     *                    'G' : 0 degree flap
     *                    'H' : 60 degrees clockwise
     *                    'I' : 120
     *                    'J' : 180
     *                    'K' : 240
     *                    'L' : 360
     * @return The location on the grid (a number between 0 and 23) of the indexed
     * unit, given the origin of the piece and its orientation, or -1 if the indexed
     * square is off the grid.
     *
     */
    public int translatePosition(int index, int origin, Piece piece, Orientation orientation){
        int[] po = new int[2];
        int[] originP = number2Point(origin);
        switch (orientation){
            case A:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
            }break;

            case B:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
            }break;

            case C:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
            }break;

            case D:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
            }break;

            case E:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
            }break;

            case F:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
            }break;

            case G:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
            }break;

            case H:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
            }break;

            case I:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
            }break;

            case J:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]-1;po[1] = originP[1];}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
            }break;

            case K:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
            }break;

            case L:{
                if(piece == A || piece == B || piece == C){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1]+1;}break;
                    }
                }
                if(piece == D || piece == E || piece == F || piece == G || piece == H){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0]+1;po[1] = originP[1];}break;
                    }
                }
                if(piece == I || piece == J || piece == K || piece == L){
                    switch (index){
                        case 0:{po[0] = originP[0];po[1] = originP[1];}break;
                        case 1:{po[0] = originP[0]-1;po[1] = originP[1]-1;}break;
                        case 2:{po[0] = originP[0];po[1] = originP[1]-1;}break;
                    }
                }
            }break;


        }

        int rtn = point2Number(po);
        return rtn;

    }

    /**
     * Transforming the position of a single unit from axis form to number form;
     * @param po An integer array describing the position of single unit in axis form (x,y);
     * @return the position in number form 0..23;
     * */
    public static int point2Number(int []po){

        if(po[0]<=5&&po[0]>=0&&(po[1]==1||po[1]==0))
            return 6*(3-po[1])+po[0];
        else if(po[0]<=6&&po[0]>=1&&(po[1]==2||po[1]==3))
            return 6*(3-po[1])+po[0]-1;
        else
            return -1;

    }

    /**
     * Transforming the position of a single unit from number form to axis form;
     * @param n  An integer describing the position in number form 0..23;
     * @return po An array describing the position of single unit in axis form (x,y);
     *
     * */
    public static int[] number2Point(int n){
        int po[] = new int[2];
        if(n>=0&&n<=11) {
            po[0]=n%6+1;
            po[1]=3-n/6;
        }
        else if(n>=12&&n<=23){
            po[0]=n%6;
            po[1]=3-n/6;
        }
        return po;
    }


    /**
     * Transform from our definition of index to required one
     * */
    public static int[] getTranslatedTitlePositions(int origin, Piece piece, Orientation orientation){
        int[] rtn = new int[3];
        rtn[0] = piece.translatePosition(1,origin,piece,orientation);
        rtn[1] = piece.translatePosition(0,origin,piece,orientation);
        rtn[2] = piece.translatePosition(2,origin,piece,orientation);

        return rtn;
    }

}
