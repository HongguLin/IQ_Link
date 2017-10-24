package comp1110.ass2.gui;

import comp1110.ass2.LinkGame;
import comp1110.ass2.Piece;
import comp1110.ass2.gui.Board;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the link game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 * @author Boya Na,adapted from the board class code of assignment 1
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 100;
    private static final int PIECE_IMAGE_SIZE = 3*SQUARE_SIZE;
    private static final double ROW_HEIGHT = SQUARE_SIZE * 0.8660254; // 60 degrees
    private static final int VIEWER_WIDTH = 933;//750;
    private static final int VIEWER_HEIGHT = 700;//500;

    private static final int X_DISTANCE = 100;
    private static final int X_SIN_BAN_GAP = 50;
    private static final int X_DOU_BAN_GAP = 100;
    private static final int Y_DISTANCE = 87;
    private static final int Y_BAN_GAP = 50;

    private static final String URI_BASE = "../assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group imagegroup = new Group();
    private final Group background = new Group();
    TextField textField;

    /*private static final String BAA = "game-BAA.png";
    private static final String BAAHBA = "game-BAAHBA.png";
    private static final String BAAHBATCJ = "game-BAAHBATCJ.png";
    private static final String BAAHBATCJRDK = "game-BAAHBATCJRDK.png";
    private static final String BAAHBATCJRDKWEB = "game-BAAHBATCJRDKWEB.png";
    private static final String BAAHBATCJRDKWEBEFD = "game-BAAHBATCJRDKWEBEFD.png";
    private static final String BAAHBATCJRDKWEBEFDNGL = "game-BAAHBATCJRDKWEBEFDNGL.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHE = "game-BAAHBATCJRDKWEBEFDNGLPHE.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIF = "game-BAAHBATCJRDKWEBEFDNGLPHEDIF.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIFMJJ = "game-BAAHBATCJRDKWEBEFDNGLPHEDIFMJJ.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKI = "game-BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKI.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKIKLJ = "game-BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKILJ.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKIKLJ_350 = "game-BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKILJ-350.png";
    private static final String BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKIKLJ_700 = "game-BAAHBATCJRDKWEBEFDNGLPHEDIFMJJQKILJ-700.png";*/


    /**
     * Draw a placement in the window, removing any previously drawn one
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 5: implement the simple placement viewer
        //Image im = new Image();
        imagegroup.getChildren().clear();
        for (int i=0;i<placement.length()/3;i++){

            String piecestring = placement.substring(i*3,(i+1)*3);
            String imagepath = null;
            //LinkGame linkGame = new LinkGame();
            int[] ps = LinkGame.getPegsForPiecePlacement(piecestring);
            int[] p = new int[2];
            p[0] = ps[1]%6;
            p[1] = ps[1]/6;
            System.out.println(piecestring+" ("+p[0]+","+p[1]+")");

            switch (piecestring.charAt(1)){
                case 'A':imagepath = "comp1110/ass2/gui/assets/A.png";break;
                case 'B':imagepath = "comp1110/ass2/gui/assets/B.png";break;
                case 'C':imagepath = "comp1110/ass2/gui/assets/C.png";break;
                case 'D':imagepath = "comp1110/ass2/gui/assets/D.png";break;
                case 'E':imagepath = "comp1110/ass2/gui/assets/E.png";break;
                case 'F':imagepath = "comp1110/ass2/gui/assets/F.png";break;
                case 'G':imagepath = "comp1110/ass2/gui/assets/G.png";break;
                case 'H':imagepath = "comp1110/ass2/gui/assets/H.png";break;
                case 'I':imagepath = "comp1110/ass2/gui/assets/I.png";break;
                case 'J':imagepath = "comp1110/ass2/gui/assets/J.png";break;
                case 'K':imagepath = "comp1110/ass2/gui/assets/K.png";break;
                case 'L':imagepath = "comp1110/ass2/gui/assets/L.png";break;
                default:break;
            }
            //switch ()
            if (imagepath!=null){
                Pane pane = new HBox();
                Image img = new Image(imagepath);
                ImageView imgview = new ImageView(img);
                imgview.setFitHeight(PIECE_IMAGE_SIZE);
                imgview.setFitWidth(PIECE_IMAGE_SIZE);
                //pane.setLayoutX(+p[0]*100);
                if (p[1]%2!=0){
                    pane.setLayoutX(X_DOU_BAN_GAP+p[0]*X_DISTANCE);
                }else {
                    pane.setLayoutX(X_SIN_BAN_GAP+p[0]*X_DISTANCE);
                }
                pane.setLayoutY(Y_BAN_GAP+p[1]*Y_DISTANCE);


                Board.rotateControl(imgview,piecestring.charAt(2));

                pane.getChildren().add(imgview);

                imagegroup.getChildren().add(pane);
            }


        }

    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

        background.setLayoutX(200);
        background.setLayoutY(200);
        for(int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                /*
                Pane pane = new HBox();

                if (i%2!=0){
                    pane.setLayoutX(X_DOU_BAN_GAP+j*X_DISTANCE);\l
                }else {
                    pane.setLayoutX(X_SIN_BAN_GAP+j*X_DISTANCE);
                }
                pane.setLayoutY(-Y_BAN_GAP-i*Y_DISTANCE);
                */
                Circle c = new Circle();
                if (i%2!=0){
                    c.setCenterX(50+X_DISTANCE*j);
                }else {
                    c.setCenterX(X_DISTANCE*j);
                }
                c.setCenterY(Y_DISTANCE*i);
                c.setRadius(30);
                c.setFill(Color.GRAY);
                background.getChildren().add(c);

            }
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LinkGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(background);
        root.getChildren().add(imagegroup);


        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
