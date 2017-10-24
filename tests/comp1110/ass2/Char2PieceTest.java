package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;

import static comp1110.ass2.TestUtility.SOLUTIONS_ONE;
import static org.junit.Assert.assertTrue;

/**
 * Created by na on 16/9/22.
 */
public class Char2PieceTest {
    @Test
    public void test() {
        char[] tmp ={'A','B','C','D','E','F','G','H','I','J','K','L'};
        Piece[] tmp2 = {Piece.A,Piece.B,Piece.C,Piece.D,Piece.E,Piece.F,Piece.G,Piece.H,Piece.I,Piece.J,Piece.K,Piece.L};

        int x = (int)Math.random()*11;
        char test = tmp[x];
        Piece result = LinkGame.char2piece(test);

        assertTrue("The char '"+test+"' has piece '"+tmp2[x].toString()+"', but you returned a null value", result != null);
        assertTrue("The char '"+test+"' has piece '"+tmp2[x].toString()+"', but you provided '"+result.toString(),result == tmp2[x]);

    }
}
