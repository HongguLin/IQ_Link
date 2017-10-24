package comp1110.ass2.gui;

import comp1110.ass2.LinkGame;
import comp1110.ass2.Piece;
//import comp1110.ass2.gui.Viewer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class provides the UI for Game
 *
 * Task 8-12 has been finished
 * We can drag the piece into the chessboard, and the current selection will also be shown in the right
 * 2 rotate buttons and filp button can achieve rotating and flip function for piece
 * Unsuitable position or rotating action will make the piece back to initial position
 * Restart button can restart the game
 * random start button and couple of slider + difficult start button can set different starting
 * You can use help button to get the help of game
 * Timer in right conner can be used to record palying time
 * @author Boya Na,Yuxi Chen,adapted from the board class code of assignment 1
 */


public class Board extends Application {

    // FIXME Task 8: Implement a basic playable Link Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement starting placements

    // FIXME Task 11: Implement hints

    // FIXME Task 12: Generate interesting starting placements


    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int PIECE_IMAGE_SIZE = 120;
    private static final int PIECE_IMAGE_SIZE_BIG = 300;
    private static final int X_DISTANCE = 100;
    private static final int X_GAP = 60;
    private static final int X_EXTRA_GAP = 50;
    private static final int Y_DISTANCE = 87;
    private static final int Y_GAP = 60;
    private static final int X_RESET = 80;
    private static final int Y_RESET = 365;
    private static final int Y_PIECE_GAP = 115;
    private static final int X_BTN_GAP = 40;
    private static final int Y_BTN_DISTANCE = 35;

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group background = new Group();
    private final Group currentselectcontrol = new Group();


    private char strCurrentSelect = ' ';
    private char rotateangle = ' ';

    private int num_hint = 3;
    private int num_win = 0;

    private Label hintLabel;

    private ImageView currentimgview;

    ArrayList<PieceSprite> store = new ArrayList<>();
    HashMap<Character,Point> map = new HashMap<>();



    /**
     * build the chessborad and restart, random and difficult button
     * @author Yuxi Chen
     * @param "v" used to set slider
     */
    private void setBackground(){
        background.setLayoutX(X_GAP);
        background.setLayoutY(Y_GAP);

        char k = 'A';

        for(int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                Circle c = new Circle();
                Point p = new Point();
                if (i%2!=0){
                    c.setCenterX(X_EXTRA_GAP+X_DISTANCE*j);
                    p.x = X_GAP+X_EXTRA_GAP+X_DISTANCE*j;
                }else {
                    c.setCenterX(X_DISTANCE*j);
                    p.x = X_GAP+X_DISTANCE*j;
                }
                c.setCenterY(Y_DISTANCE*i);
                p.y = Y_GAP+Y_DISTANCE*i;
                c.setRadius(30);
                c.setFill(Color.LIGHTGRAY);
                background.getChildren().add(c);

                map.put(k,p);
                //System.out.println("Key "+k+" Point: X "+p.x+" Y "+p.y);
                k++;
            }
        }


    }



    /**
     * build the view to show current selection including a label and a image and some buttons
     * @author Boya Na
     * @param v is used to set slider
     */
    private void setCurrentselectcontrol(double v){
        currentselectcontrol.setLayoutX(650);
        currentselectcontrol.setLayoutY(50);

        Label currentselecet = new Label("Current Select");
        currentselecet.setFont(new Font("Cambria",20));
        currentselecet.setLayoutX(60);
        currentselecet.setLayoutY(0);

        currentimgview = new ImageView();

        currentimgview.setFitHeight(200);
        currentimgview.setFitWidth(200);
        currentimgview.setLayoutX(20);
        currentimgview.setLayoutY(40);




        Button plusrotatebutton = new Button("Rotate+");
        plusrotatebutton.setLayoutX(X_BTN_GAP);
        plusrotatebutton.setLayoutY(270);
        plusrotatebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rotateangle += 1;
                if (rotateangle == 'G'){
                    rotateangle = 'A';
                }else if (rotateangle>'L'){
                    rotateangle = 'G';
                }

                for (PieceSprite p : store){
                    if (p.piece[1] == strCurrentSelect){
                        p.rotate();
                    }
                }

                updataCurrentSelect();
                System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);
            }
        });






        Button minusrotatebutton = new Button("Rotate-");
        minusrotatebutton.setLayoutX(X_BTN_GAP+70);
        minusrotatebutton.setLayoutY(270);
        minusrotatebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rotateangle -= 1;
                if (rotateangle == 'F'){
                    rotateangle = 'L';
                }else if (rotateangle<'A'){
                    rotateangle = 'F';
                }

                for (PieceSprite p : store){
                    if (p.piece[1] == strCurrentSelect){
                        p.rotate();
                    }
                }

                updataCurrentSelect();
                System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);
            }
        });

        Button flipbutton = new Button("Flip");
        flipbutton.setLayoutX(X_BTN_GAP+140);
        flipbutton.setLayoutY(270);
        flipbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rotateangle <= 'F'){
                    rotateangle += 6;
                }else if (rotateangle > 'F'){
                    rotateangle -= 6;
                }

                for (PieceSprite p : store){
                    if (p.piece[1] == strCurrentSelect){
                        p.rotate();
                    }
                }

                updataCurrentSelect();
                System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);
            }
        });

        Button restartbtn = new Button("Restart");
        restartbtn.setLayoutX(X_BTN_GAP);
        restartbtn.setLayoutY(270+Y_BTN_DISTANCE);
        restartbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                restartGame();
            }
        });

        Button initBtn = new Button("Random Start");
        initBtn.setLayoutX(X_BTN_GAP+80);
        initBtn.setLayoutY(270+Y_BTN_DISTANCE);
        initBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                restartPartGame(0);
                String sRandom = LinkGame.generateRandomStart();
                showString(sRandom);
                startGame();
            }
        });

        Slider difficultSLD = new Slider();
        difficultSLD.setLayoutX(X_BTN_GAP);
        difficultSLD.setLayoutY(270+Y_BTN_DISTANCE*2);
        difficultSLD.setValue(v);
        difficultSLD.setMin(1);
        difficultSLD.setMax(7);
        difficultSLD.setShowTickLabels(true);
        difficultSLD.setShowTickMarks(true);
        difficultSLD.setMajorTickUnit(2);
        difficultSLD.setMinorTickCount(1);
        difficultSLD.setSnapToTicks(true);


        Button diffBtn = new Button("Difficult & Random Start");
        diffBtn.setLayoutX(X_BTN_GAP);
        diffBtn.setLayoutY(275+Y_BTN_DISTANCE*3);
        diffBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double w = difficultSLD.getValue();
                System.out.println(w);
                //w = 8 - w;
                restartPartGame(w);
                String sDiff = LinkGame.genRanStartAccToDiff((int)w);
                showString(sDiff);
                startGame();
            }
        });

        Stage helpstage = new Stage();
        helpstage.setAlwaysOnTop(true);
        helpstage.setMaxWidth(350);
        helpstage.setMaxHeight(350);
        helpstage.setMinWidth(350);
        helpstage.setMinHeight(350);
        helpstage.setFullScreen(false);
        helpstage.setIconified(false);
        String s =
                "       Help:\n" +
                        "       1.Place put correct chess into chessboard;\n" +
                        "       2.Rotate button can make current selected\n" +
                        "       chess rotate;\n" +
                        "       3.Flip button can make current selected\n" +
                        "       chess flip;\n" +
                        "       4.Difficult & Random Start button can\n" +
                        "       start a difficult game;\n" +
                        "       5.Difficulty can be set by slider, 1 is easiest\n" +
                        "       and 7 is most difficult;\n" +
                        "       6.Incorrect or invailed chesee will return the\n" +
                        "       init loaction with init size;\n" +
                        "       7.Using '/' can get the hint;\n" +
                        "       8.'a' and 'd' button can be used to rotate,\n" +
                        "       'w' and 's' can be used to flip;\n" +
                        "       9. timer in right conner can be used to get the\n" +
                        "       playing time.";
        Label l = new Label(s);
        Scene helpscene = new Scene(l,350,350);
        helpstage.setScene(helpscene);

        Button helpBtn = new Button(" Help ");
        helpBtn.setLayoutX(X_BTN_GAP);
        helpBtn.setLayoutY(275+Y_BTN_DISTANCE*4);
        helpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("help");
                helpstage.show();
            }
        });

        hintLabel = new Label("The number of hint: "+num_hint);
        hintLabel.setLayoutX(X_BTN_GAP);
        hintLabel.setLayoutY(275+Y_BTN_DISTANCE*5);
        hintLabel.setFont(new Font("Cambria",20));

        Timer timer = new Timer();
        Group timenode = timer.timeroot;
        timenode.setLayoutX(X_BTN_GAP);
        timenode.setLayoutY(275+Y_BTN_DISTANCE*6);


        currentselectcontrol.getChildren().addAll(currentselecet,currentimgview, plusrotatebutton,minusrotatebutton,
                flipbutton,diffBtn,difficultSLD, initBtn,restartbtn,helpBtn,hintLabel,timenode);

    }

    /**
     * rotate function
     * @author Yuxi Chen,adapted from the board class code of assignment 1
     * @param view
     * @param angle
     */
    static void rotateControl(ImageView view,char angle) {
        switch (angle) {
            case 'A': {
                view.setScaleY(1);
                view.setRotate(0);
                break;
            }
            case 'B': {
                view.setScaleY(1);
                view.setRotate(60);
                break;
            }
            case 'C': {
                view.setScaleY(1);
                view.setRotate(120);
                break;
            }
            case 'D': {
                view.setScaleY(1);
                view.setRotate(180);
                break;
            }
            case 'E': {
                view.setScaleY(1);
                view.setRotate(240);
                break;
            }
            case 'F': {
                view.setScaleY(1);
                view.setRotate(300);
                break;
            }
            case 'G': {
                view.setScaleY(-1);
                view.setRotate(0);
                break;
            }
            case 'H': {
                view.setScaleY(-1);
                view.setRotate(60);
                break;
            }
            case 'I': {
                view.setScaleY(-1);
                view.setRotate(120);
                break;
            }
            case 'J': {
                view.setScaleY(-1);
                view.setRotate(180);
                break;
            }
            case 'K': {
                view.setScaleY(-1);
                view.setRotate(240);
                break;
            }
            case 'L': {
                view.setScaleY(-1);
                view.setRotate(300);
                break;
            }
            default:
                break;

        }
    }

    /**
     * update the situation of current selection (rotate and image)
     * @author Boya Na
     */
    private void updataCurrentSelect(){
        String imagepath = null;
        switch (strCurrentSelect){
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
        if (imagepath!=null){
            Image img = new Image(imagepath);
            currentimgview.setImage(img);
            rotateControl(currentimgview,rotateangle);
        }

    }

    /**
     * build the piece
     * @author Boya Na
     */
    private void makeControls() {

        //build piece
        String A = " AA";
        String B = " BA";
        String C = " CA";
        String D = " DA";
        String E = " EA";
        String F = " FA";
        String G = " GA";
        String H = " HA";
        String I = " IA";
        String J = " JA";
        String K = " KA";
        String L = " LA";

        String imagepath1 = null;
        String imagepath2 = null;
        String imagepath3 = null;
        String tmp1 = null;
        String tmp2 = null;
        String tmp3 = null;

        for (int i=0;i<4;i++){
            switch (i){
                case 0:{
                    imagepath1 = "comp1110/ass2/gui/assets/A.png";
                    imagepath2 = "comp1110/ass2/gui/assets/E.png";
                    imagepath3 = "comp1110/ass2/gui/assets/I.png";
                    tmp1 = A;
                    tmp2 = E;
                    tmp3 = I;

                }break;
                case 1:{
                    imagepath1 = "comp1110/ass2/gui/assets/B.png";
                    imagepath2 = "comp1110/ass2/gui/assets/F.png";
                    imagepath3 = "comp1110/ass2/gui/assets/J.png";
                    tmp1 = B;
                    tmp2 = F;
                    tmp3 = J;
                }break;
                case 2:{
                    imagepath1 = "comp1110/ass2/gui/assets/C.png";
                    imagepath2 = "comp1110/ass2/gui/assets/G.png";
                    imagepath3 = "comp1110/ass2/gui/assets/K.png";
                    tmp1 = C;
                    tmp2 = G;
                    tmp3 = K;
                }break;
                case 3:{
                    imagepath1 = "comp1110/ass2/gui/assets/D.png";
                    imagepath2 = "comp1110/ass2/gui/assets/H.png";
                    imagepath3 = "comp1110/ass2/gui/assets/L.png";
                    tmp1 = D;
                    tmp2 = H;
                    tmp3 = L;
                }break;
                default:break;
            }


            PieceSprite ps1 = new PieceSprite(new Image(imagepath1),tmp1,X_RESET+(PIECE_IMAGE_SIZE+10)*i,Y_RESET,true);
            PieceSprite ps2 = new PieceSprite(new Image(imagepath2),tmp2,X_RESET+(PIECE_IMAGE_SIZE+10)*i,Y_RESET+Y_PIECE_GAP,true);
            PieceSprite ps3 = new PieceSprite(new Image(imagepath3),tmp3,X_RESET+(PIECE_IMAGE_SIZE+10)*i,Y_RESET+Y_PIECE_GAP*2,true);

            store.add(ps1);
            store.add(ps2);
            store.add(ps3);

            controls.getChildren().addAll(ps1.imgview,ps2.imgview,ps3.imgview);
        }

    }

    /**
     *get the string of placement without parament piece
     * @param type the piece which has position and is being judged should be included in the string
     * @return return the string of placement
     * @author Boya Na, Yuxi Chen
     */
    private String getCurrentPlacement(char type){
        String placement = "";
        for (PieceSprite p : store){
            if (type == p.piece[1]){
                continue;
            }
            if (p.piece[0]!= ' '){
                String stmp = new String(p.piece);
                placement += stmp;
            }
        }
        return placement;
    }

    /**
     * transfor the screen coordinate to the char coordinate
     * @param x x of screen coordinate
     * @param y y of screen coordinate
     * @return char value in char coordiante
     * @author Boya Na
     */
    private char getPostion(double x, double y){
        for (char i: map.keySet()){
            Point p = map.get(i);
            if (((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y))<=45*45){
                return i;
            }
        }
        return ' ';
    }

    /**
     * Start the game.....
     * @author Boya Na
     */
    private void startGame(){
        for (PieceSprite p : store){
            if (p.bSelected){
                p.setOnMouseEvent();

            }
        }
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SLASH){
                    num_hint --;
                    if (num_hint>=0){
                        System.out.println("/ Released");
                        String s = getCurrentPlacement(' ');
                        String sRes[] = LinkGame.getSolutions(s);
                        if (sRes.length==0){
                            Stage stage = new Stage();
                            stage.setMaxWidth(200);
                            stage.setMaxHeight(100);
                            stage.setMinWidth(200);
                            stage.setMinHeight(100);
                            stage.setFullScreen(false);
                            stage.setIconified(false);
                            Label l = new Label("   No solutions!");
                            l.setFont(new Font("Cambria",20));
                            Scene scene = new Scene(l,200,100);
                            stage.setScene(scene);
                            stage.show();
                        }else {
                            String splace = sRes[0];
                            for (int i=0;i< splace.length()/3;i++){
                                String stmp = splace.substring(i*3,(i+1)*3);
                                if (s.contains(stmp)==false){
                                    showString(stmp);
                                    break;
                                }
                            }
                        }
                    }else {
                        num_hint = 0;
                        Stage hintstage = new Stage();
                        hintstage.setMaxWidth(200);
                        hintstage.setMaxHeight(100);
                        hintstage.setMinWidth(200);
                        hintstage.setMinHeight(100);
                        hintstage.setFullScreen(false);
                        hintstage.setIconified(false);
                        Label hintl = new Label("   No available hints!");
                        hintl.setFont(new Font("Cambria",20));
                        Scene hintscene = new Scene(hintl,200,100);
                        hintstage.setScene(hintscene);
                        hintstage.show();
                    }
                    hintLabel.setText("The number of hint: "+num_hint);

                }else if (event.getCode() == KeyCode.A){
                    rotateangle -= 1;
                    if (rotateangle == 'F'){
                        rotateangle = 'L';
                    }else if (rotateangle<'A'){
                        rotateangle = 'F';
                    }
                    for (PieceSprite p : store){
                        if (p.piece[1] == strCurrentSelect){
                            p.rotate();
                        }
                    }
                    updataCurrentSelect();
                    System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);

                }else if (event.getCode() == KeyCode.D){

                    rotateangle += 1;
                    if (rotateangle == 'G'){
                        rotateangle = 'A';
                    }else if (rotateangle>'L'){
                        rotateangle = 'G';
                    }
                    for (PieceSprite p : store){
                        if (p.piece[1] == strCurrentSelect){
                            p.rotate();
                        }
                    }
                    updataCurrentSelect();
                    System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);

                }else if (event.getCode() == KeyCode.W){
                    if (rotateangle <= 'F'){
                        rotateangle += 6;
                    }else if (rotateangle > 'F'){
                        rotateangle -= 6;
                    }

                    for (PieceSprite p : store){
                        if (p.piece[1] == strCurrentSelect){
                            p.rotate();
                        }
                    }
                    updataCurrentSelect();
                    System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);
                }else if (event.getCode() == KeyCode.S){
                    if (rotateangle <= 'F'){
                        rotateangle += 6;
                    }else if (rotateangle > 'F'){
                        rotateangle -= 6;
                    }

                    for (PieceSprite p : store){
                        if (p.piece[1] == strCurrentSelect){
                            p.rotate();
                        }
                    }
                    updataCurrentSelect();
                    System.out.println("Current piece "+strCurrentSelect+" rotate to "+rotateangle);
                }
            }
        });

        Stage winstage = new Stage();
        winstage.setMaxWidth(200);
        winstage.setMaxHeight(150);
        winstage.setMinWidth(200);
        winstage.setMinHeight(150);
        winstage.setFullScreen(false);
        winstage.setIconified(false);
        winstage.setAlwaysOnTop(true);
        String s =
                "\n       WIN! Good job!\n" ;
        Label l = new Label(s);
        Button restart = new Button("restart");
        restart.setLayoutX(70);
        restart.setLayoutY(80);
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                winstage.close();
                restartGame();
            }
        });
        l.setFont(new Font("Cambria",20));
        Group win = new Group();
        win.getChildren().addAll(l,restart);
        Scene winscene = new Scene(win,200,150);
        winstage.setScene(winscene);


        Timeline wincheck = new Timeline();
        wincheck.setCycleCount(Timeline.INDEFINITE);
        Duration duration = Duration.millis(200);
        KeyFrame keyFrame = new KeyFrame(duration, new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (num_win == 12){
                    winstage.show();
                }else {
                    winstage.close();
                }
            }
        });
        wincheck.getKeyFrames().add(keyFrame);

        wincheck.play();

    }

    /**
     * restart game
     * @author Boya Na
     */
    private void restartGame(){
        root.getChildren().clear();

        controls.getChildren().clear();
        background.getChildren().clear();
        currentselectcontrol.getChildren().clear();

        strCurrentSelect = ' ';
        rotateangle = ' ';

        num_hint = 3;
        num_win = 0;

        store = new ArrayList<>();
        map = new HashMap<>();

        root.getChildren().add(background);
        root.getChildren().add(currentselectcontrol);
        root.getChildren().add(controls);

        setBackground();
        setCurrentselectcontrol(0);
        makeControls();

        startGame();
    }

    /**
     * restart game without event
     * @param v is used to set slider
     * @author Boya Na, Yuxi Chen
     */
    private void restartPartGame(double v){
        root.getChildren().clear();

        controls.getChildren().clear();
        background.getChildren().clear();
        currentselectcontrol.getChildren().clear();

        strCurrentSelect = ' ';
        rotateangle = ' ';

        num_hint = 3;
        num_win = 0;

        store = new ArrayList<>();
        map = new HashMap<>();

        root.getChildren().add(background);
        root.getChildren().add(currentselectcontrol);
        root.getChildren().add(controls);

        setBackground();
        setCurrentselectcontrol(v);
        makeControls();

    }

    /**
     * show the piece placement by string
     * @author Boya Na, Yuxi Chen
     * @param s used to show
     */
    private void showString(String s){
        System.out.println(s);
        for (int i=0;i<s.length()/3;i++){
            num_win++;
            String stmp = s.substring(i*3,(i+1)*3);
            for (int j=0;j<store.size();j++){
                if (stmp.charAt(1)==store.get(j).piece[1]){
                    store.get(j).setRotate(stmp.charAt(2));
                    store.get(j).setPosition(stmp.charAt(0));
                    store.get(j).bSelected = false;
                    store.get(j).binchessboard = true;
                    continue;
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ-link");
        primaryStage.getIcons().add(new Image("comp1110/ass2/gui/assets/icon.png"));
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(background);
        root.getChildren().add(currentselectcontrol);
        root.getChildren().add(controls);

        setBackground();
        setCurrentselectcontrol(0);
        makeControls();

        startGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * the class to show, drag and rotate the piece
     * @author Boya Na
     */
    class PieceSprite {
        public ImageView imgview;
        public char[] piece = new char[3];
        public Piece p;
        boolean b = false;
        boolean bSelected = true;
        double initX;
        double initY;
        boolean binchessboard = false;
        Timeline timeline;
        int big_num = 0;
        int small_num = 0;

        PieceSprite(Image img, String s, double x, double y, boolean bb){
            imgview = new ImageView(img);
            imgview.setLayoutX(x);
            imgview.setLayoutY(y);
            setSmallImgview();

            initX = x;
            initY = y;

            piece[0] = s.charAt(0);
            piece[1] = s.charAt(1);
            piece[2] = s.charAt(2);

            bSelected = bb;

            String stest = new String(piece);

            //System.out.println("Piece "+stest+" build!");


        }

        void setOnMouseEvent() {


            imgview.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //System.out.println(piece[0]+piece[1]+piece[2]+"Clicked");
                    double X = event.getSceneX();
                    double Y = event.getSceneY();
                    setSmallImgview();
                    imgview.setLayoutX(X-PIECE_IMAGE_SIZE/2);
                    imgview.setLayoutY(Y-PIECE_IMAGE_SIZE/2);
                    strCurrentSelect = piece[1];
                    rotateangle = piece[2];
                    updataCurrentSelect();
                    b=true;
                }
            });

            imgview.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //System.out.println("Moved");
                    if (b==true){
                        double X = event.getSceneX();
                        double Y = event.getSceneY();
                        setSmallImgview();
                        imgview.setLayoutX(X-PIECE_IMAGE_SIZE/2);
                        imgview.setLayoutY(Y-PIECE_IMAGE_SIZE/2);
                    }
                }
            });

            imgview.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //System.out.println("Released");
                    if (b==true){
                        double X = event.getSceneX();
                        double Y = event.getSceneY();
                        char pos = getPostion(X,Y);
                        //System.out.println("The point is "+pos);
                        if (pos == ' '){
                            backbox();
                        }else {
                            piece[0] = pos;
                        }
                        String s = getCurrentPlacement(piece[1]);
                        s = s+ new String(piece);
                        boolean bresult = LinkGame.isPlacementValid(s);
                        System.out.println(s+" is "+bresult);
                        if (bresult == true){
                            Point p = map.get(pos);
                            imgview.setLayoutX(p.x - PIECE_IMAGE_SIZE_BIG/2);
                            imgview.setLayoutY(p.y - PIECE_IMAGE_SIZE_BIG/2);
                            setBigImgview(p.x,p.y,true);
                            if (binchessboard == false){
                                num_win++;
                                binchessboard = true;
                            }
                        }else {
                            backbox();
                        }
                        b=false;
                    }
                }
            });

        }

        void setBigImgview(double x, double y,boolean isAnimation){
            big_num=1;
            if (isAnimation==true){
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                Duration duration = Duration.millis(10);
                KeyFrame keyFrame = new KeyFrame(duration, new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        int size = PIECE_IMAGE_SIZE + (PIECE_IMAGE_SIZE_BIG-PIECE_IMAGE_SIZE)/8*big_num;
                        big_num++;
                        imgview.setLayoutX(x-size/2);
                        imgview.setLayoutY(y-size/2);
                        imgview.setFitHeight(size);
                        imgview.setFitWidth(size);
                        if (big_num > 8){
                            timeline.stop();
                        }
                    }
                });
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }



            imgview.setFitHeight(PIECE_IMAGE_SIZE_BIG);
            imgview.setFitWidth(PIECE_IMAGE_SIZE_BIG);
        }

        void backbox(){
            if (binchessboard==true){
                num_win--;
                binchessboard = false;
            }
            piece[0] = ' ';
            imgview.setLayoutX(initX);
            imgview.setLayoutY(initY);
            setSmallImgview();
        }

        void setSmallImgview(){
            imgview.setFitHeight(PIECE_IMAGE_SIZE);
            imgview.setFitWidth(PIECE_IMAGE_SIZE);
        }

        void setPosition(char chPosition){
            Point p = map.get(chPosition);
            if (p!=null){
                imgview.setLayoutX(p.x - PIECE_IMAGE_SIZE_BIG/2);
                imgview.setLayoutY(p.y - PIECE_IMAGE_SIZE_BIG/2);
                setBigImgview(p.x,p.y,false);
                piece[0] = chPosition;
            }
        }

        void setRotate(char chRotate){
            rotateControl(imgview,chRotate);
            piece[2] = chRotate;
        }

        void rotate(){
            rotateControl(imgview,rotateangle);
            piece[2] = rotateangle;
            String s = getCurrentPlacement(piece[1]);
            s = s+ new String(piece);
            boolean bresult = LinkGame.isPlacementValid(s);
            System.out.println(s+" is "+bresult);
            if (!bresult){
                backbox();
            }
         }
    }

    /**
     * A little timer to show the time.
     * @author Boya Na
     *
     */

    class Timer{
        private Timeline timeline;
        public Group timeroot = new Group();

        private static final int X_DISTANCE = 50;
        private static final int Y_DISTANCE = 40;

        private int time;

        Timer(){
            time = 0;

            Label display = new Label();
            display.setText(TimeFormat(time));
            display.setFont(new Font("Cambria",30));
            display.setLayoutX(0);
            display.setLayoutY(0);

            Button startBtn = new Button("Start");
            startBtn.setLayoutX(0);
            startBtn.setLayoutY(Y_DISTANCE);
            startBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeline.play();
                }
            });

            Button pauseBtn = new Button("Pasue");
            pauseBtn.setLayoutX(X_DISTANCE);
            pauseBtn.setLayoutY(Y_DISTANCE);
            pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeline.stop();
                }
            });

            Button restartBtn = new Button("Restart");
            restartBtn.setLayoutX(X_DISTANCE*2+10);
            restartBtn.setLayoutY(Y_DISTANCE);
            restartBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeline.stop();
                    time = 0;
                    display.setText(TimeFormat(time));
                }
            });


            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            Duration duration = Duration.millis(1000);
            KeyFrame keyFrame = new KeyFrame(duration, new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    time++;
                    display.setText(TimeFormat(time));
                }
            });
            timeline.getKeyFrames().add(keyFrame);

            timeroot.getChildren().addAll(display,startBtn,pauseBtn,restartBtn);
        }

        private String TimeFormat(int count) {
            int hours = count / 3600;
            int minutes = (count-hours*3600)/60;
            int seconds = count-minutes*60;
            return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
        }
    }

}


