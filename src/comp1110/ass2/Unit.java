package comp1110.ass2;

/**
 * Created by Serlina on 2016/9/7.
 * @author Honggu Lin
 */
public class Unit {
    static Unit Ball_A = new Unit(Orientation.A, 1);
    static Unit Ball_B = new Unit(Orientation.B, 1);
    static Unit Ball_C = new Unit(Orientation.C, 1);
    static Unit Ball_D = new Unit(Orientation.D, 1);
    static Unit Ball_E = new Unit(Orientation.E, 1);
    static Unit Ball_F = new Unit(Orientation.F, 1);
    static Unit Ball_DB = new Unit(Orientation.D,Orientation.E,1);

    static Unit Ring_A = new Unit(Orientation.A, 2);
    static Unit Ring_B = new Unit(Orientation.B, 2);
    static Unit Ring_C = new Unit(Orientation.C, 2);
    static Unit Ring_D = new Unit(Orientation.D, 2);
    static Unit Ring_E = new Unit(Orientation.E, 2);
    static Unit Ring_F = new Unit(Orientation.F, 2);
    static Unit Ring_DB = new Unit(Orientation.B,Orientation.C, 2);
    static Unit Ring_NO = new Unit(2);


    public Orientation o1;
    public Orientation o2;
    public int i;

    public Unit(Orientation o1, Orientation o2, int i){
        this.o1 = o1;
        this.o2 = o2;
        this.i = i;
    }

    public Unit(int i){
        this.i = i;
    }

    public Unit(Orientation o1, int i) {
        this.o1 = o1;
        this.i = i;

    }

    public String toString(){

        String s;

        if(this.o1 != null && this.o2 != null){
            s = this.o1.toString() + this.o2.toString() + this.i;
        }else if(this.o1 == null && this.o2 == null){
            s = "" + this.i;
        }else {
            s = this.o1.toString() + this.i;
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(Unit.Ball_A.toString());
        System.out.println(Unit.Ball_A.o1.toString());
    }
}
