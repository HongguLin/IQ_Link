package comp1110.ass2;
/**
 * This test is designed for testing translatePosition method in Piece Class;
 *
 * Created by PARTY on 2016/9/20.
 */
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertTrue;

public class TranslatePositionTest {
    @Test
    public void testIndexZeroRotation() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int origin = r.nextInt(23);
            Orientation orientation = Orientation.values()[r.nextInt(Orientation.values().length)];
            Piece piece = Piece.values()[r.nextInt(Piece.values().length)];
            int t = piece.translatePosition(0, origin, piece, orientation);
            assertTrue("Piece " + ('A' + origin) + piece + orientation + ", index 0 should be " + origin + " but was " + t, t == origin);
        }
    }

    @Test
    public void testIndexOneRotation() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int origin = r.nextInt(23);
            Piece piece = Piece.values()[r.nextInt(Piece.values().length)];
            Orientation orientation = Orientation.values()[r.nextInt(Orientation.values().length)];
            int t = piece.translatePosition(1, origin, piece, orientation);
            int o = piece.translatePosition(0, origin, piece, orientation);
            int[] p = Piece.number2Point(o);
            switch (orientation) {
                case A:
                case G: {
                    p[0] = p[0] - 1;
                }
                break;
                case B:
                case H: {
                    p[1] = p[1] + 1;
                }
                break;
                case C:
                case I: {
                    p[0] = p[0] + 1;
                    p[1] = p[1] + 1;
                }
                break;
                case D:
                case J: {
                    p[0] = p[0] + 1;
                }
                break;
                case E:
                case K: {
                    p[1] = p[1] - 1;
                }
                break;
                case F:
                case L: {
                    p[0] = p[0] - 1;
                    p[1] = p[1] - 1;
                }
                break;

            }
            int tp = Piece.point2Number(p);
            assertTrue("Piece " + (char) ('A' + origin) + piece + orientation + ", index 1 should be " + tp + " but was " + t, tp == t);
        }
    }

    @Test
    public void testIndexTwoRotation() {
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int origin = r.nextInt(23);
            Piece piece = Piece.values()[r.nextInt(Piece.values().length)];
            Orientation orientation = Orientation.values()[r.nextInt(Orientation.values().length)];
            int t = piece.translatePosition(2, origin, piece, orientation);
            int o = piece.translatePosition(0, origin, piece, orientation);
            int[] p = Piece.number2Point(o);
            switch (piece) {
                case A:
                case B:
                case C: {
                    switch (orientation) {
                        case A:
                        case G: {
                            p[0] = p[0] + 1;
                        }
                        break;
                        case B:
                        case H: {
                            p[1] = p[1] - 1;
                        }
                        break;
                        case C:
                        case I: {
                            p[0] = p[0] - 1;
                            p[1] = p[1] - 1;
                        }
                        break;
                        case D:
                        case J: {
                            p[0] = p[0] - 1;
                        }
                        break;
                        case E:
                        case K: {
                            p[1] = p[1] + 1;
                        }
                        break;
                        case F:
                        case L: {
                            p[0] = p[0] + 1;
                            p[1] = p[1] + 1;
                        }
                        break;

                    }
                    int tp = Piece.point2Number(p);
                    assertTrue("Piece " + (char) ('A' + origin) + piece + orientation + ", index 2 should be " + tp + " but was " + t, tp == t);
                }break;
                case D:
                case E:
                case F:
                case G:
                case H: {
                    switch (orientation) {
                        case A:
                        case K: {
                            p[0] = p[0] + 1;
                            p[1] = p[1] + 1;
                        }
                        break;
                        case B:
                        case L: {
                            p[0] = p[0] + 1;
                        }
                        break;
                        case C:
                        case G: {

                            p[1] = p[1] - 1;
                        }
                        break;
                        case D:
                        case H: {
                            p[0] = p[0] - 1;
                            p[1] = p[1] - 1;
                        }
                        break;
                        case E:
                        case I: {
                            p[0] = p[0] - 1;
                        }
                        break;
                        case F:
                        case J: {
                            p[1] = p[1] + 1;
                        }
                        break;

                    }
                    int tp = Piece.point2Number(p);
                    assertTrue("Piece " + (char) ('A' + origin) + piece + orientation + ", index 2 should be " + tp + " but was " + t, tp == t);
                }
                break;
                case I:
                case J:
                case K:
                case L:
                {switch (orientation) {
                    case A:
                    case I: {
                        p[1] = p[1] + 1;
                    }
                    break;
                    case B:
                    case J: {
                        p[1] = p[1] + 1;
                        p[0] = p[0] + 1;
                    }
                    break;
                    case C:
                    case K: {
                        p[0] = p[0] + 1;
                    }
                    break;
                    case D:
                    case L: {
                        p[1] = p[1] - 1;
                    }
                    break;
                    case E:
                    case G: {
                        p[0] = p[0] - 1;
                        p[1] = p[1] - 1;
                    }
                    break;
                    case F:
                    case H: {
                        p[0] = p[0] - 1;
                    }
                    break;

                }
                    int tp = Piece.point2Number(p);
                    assertTrue("Piece " + (char) ('A' + origin) + piece + orientation + ", index 2 should be " + tp + " but was " + t, tp == t);
                }
                break;
            }

        }
    }
}





