package comp1110.ass2;

/**
 * Created by Serlina on 2016/9/20.
 */

import org.junit.Test;


import static comp1110.ass2.TestUtility.SOLUTIONS_MULTI;
import static comp1110.ass2.TestUtility.SOLUTIONS_ONE;
import static org.junit.Assert.assertTrue;

public class unitTransformTest {
    @Test
    public void testBallValid(){
        assertTrue("Unit BALL_A after transfer B degree clockwise is BALL_B", Unit.Ball_B.toString().equals(LinkGame.unitTransform(Unit.Ball_A, Orientation.B).toString()));
        assertTrue("Unit BALL_C after transfer F degree clockwise is BALL_B", Unit.Ball_B.toString().equals(LinkGame.unitTransform(Unit.Ball_C, Orientation.F).toString()));
        assertTrue("Unit BALL_A after transfer H degree clockwise is BALL_B", Unit.Ball_B.toString().equals(LinkGame.unitTransform(Unit.Ball_A, Orientation.H).toString()));
        assertTrue("Unit BALL_C after transfer H degree clockwise is BALL_F", Unit.Ball_F.toString().equals(LinkGame.unitTransform(Unit.Ball_C, Orientation.H).toString()));
    }

    @Test
    public void testRingValid(){
        assertTrue("Unit Ring_A after transfer B degree clockwise is Ring_B", Unit.Ring_B.toString().equals(LinkGame.unitTransform(Unit.Ring_A, Orientation.B).toString()));
        assertTrue("Unit Ring_B after transfer F degree clockwise is Ring_A", Unit.Ring_A.toString().equals(LinkGame.unitTransform(Unit.Ring_B, Orientation.F).toString()));
        assertTrue("Unit Ring_A after transfer H degree clockwise is Ring_B", Unit.Ring_B.toString().equals(LinkGame.unitTransform(Unit.Ring_A, Orientation.H).toString()));
        assertTrue("Unit Ring_C after transfer H degree clockwise is Ring_B", Unit.Ring_F.toString().equals(LinkGame.unitTransform(Unit.Ring_C, Orientation.H).toString()));
    }

    @Test
    public void testRing_NOValid(){
        assertTrue("Unit Ring_NO after transfer K degree clockwise is Ring_NO", Unit.Ring_NO.toString().equals(LinkGame.unitTransform(Unit.Ring_NO, Orientation.K).toString()));
    }

    @Test
    public void testRing_DBValid(){
        Unit u = new Unit(Orientation.C,Orientation.D,2);
        Unit a = new Unit(Orientation.A,Orientation.B,2);
        assertTrue("Unit Ring_DB after transfer B degree clockwise is Ring_DB", u.toString().equals(LinkGame.unitTransform(Unit.Ring_DB, Orientation.B).toString()));
        assertTrue("Unit Ring_DB after transfer F degree clockwise is Ring_DB", a.toString().equals(LinkGame.unitTransform(Unit.Ring_DB, Orientation.F).toString()));
    }

    @Test
    public void testBall_DBValid(){
        Unit u = new Unit(Orientation.E,Orientation.F,1);
        Unit a = new Unit(Orientation.C,Orientation.D,1);
        Unit b = new Unit(Orientation.A,Orientation.B,1);
        Unit c = new Unit(Orientation.A,Orientation.F,1);

        System.out.println(c.toString());
        System.out.println(LinkGame.unitTransform(Unit.Ball_DB, Orientation.J).toString());
        assertTrue("Unit Ball_DB after transfer B degree clockwise is Ball_DB", u.toString().equals(LinkGame.unitTransform(Unit.Ball_DB, Orientation.B).toString()));
        assertTrue("Unit Ball_DB after transfer F degree clockwise is Ball_DB", a.toString().equals(LinkGame.unitTransform(Unit.Ball_DB, Orientation.F).toString()));
        assertTrue("Unit Ball_DB after transfer K degree clockwise is Ball_DB", b.toString().equals(LinkGame.unitTransform(Unit.Ball_DB, Orientation.K).toString()));
        assertTrue("Unit Ball_DB after transfer J degree clockwise is Ball_DB", c.toString().equals(LinkGame.unitTransform(Unit.Ball_DB, Orientation.J).toString()));

    }

}
