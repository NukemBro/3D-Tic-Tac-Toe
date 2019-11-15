package project4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.*;
import java.io.*;

public class Project4 extends Application {
    public static Scene splashScene, instructionScene, gameScene;
    public static ImageView background1, background2, background3;
    public static BackgroundFill background;
    public static StackPane splashScreen, instructionScreen, gameScreen;
    public static GridPane layer1, layer2, layer3, layer4, highScoreList;
    public static Button[] boardButtons;
    public static Button yesButton, noButton, goButton, exitButton;
    public static Timeline flashButton1, flashButton2, flashButton3, flashButton4;
    public static Label continueLabel, gameInstructions, gameInstructionsTitle;
    public static Label firstScore, secondScore, thirdScore, fourthScore, fifthScore;
    public static DropShadow dropShadow, buttonShadow;
    public static Rectangle scoreTable, gameTable, instructionTable;
    public static TextField initialsField;
    public static backend be;
    
        // global hovered button details
    private static final String HOVERED_BUTTON_STYLE = 
        "-fx-background-color: linear-gradient(rgba(0,255,0,0.50), rgba(100,100,100,0.50)); \n" +
        "-fx-background-insets: -2, -2, -2, -2, -2, -2, -2; \n" +
        "-fx-background-radius: 3px, 3px, 2px, 1px;";
    
    // global idle button details
    private static final String IDLE_BUTTON_STYLE = 
        "-fx-background-color: linear-gradient(rgba(150,150,150,.50), rgba(100,100,100,.50)); \n" +
        "-fx-background-insets: 0, 0, -2, 0, 0, 1, 2;\n" +
        "-fx-background-radius: 3px, 3px, 2px, 1px;";
    
    // global win button details
    private static final String WIN_BUTTON_STYLE = 
        "-fx-background-color: linear-gradient(rgba(255,0,0,.50), rgba(100,100,100,.50)); \n" +
        "-fx-background-insets: 0, 0, -2, 0, 0, 1, 2;\n" +
        "-fx-background-radius: 3px, 3px, 2px, 1px;";
	
    @Override
    // main function to create javaFX GUI (<24 lines)
    public void start(Stage primaryStage) {
        
        // Sets up stage, then creates our 3 scene for our GUI 
        init_stage(primaryStage);
        init_splash_screen(primaryStage);
        init_instruction_screen(primaryStage);
        init_game_screen(primaryStage);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("game.png")));
        primaryStage.setTitle("3D Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.setScene(splashScene);
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    
    // sets up stage (<24 lines w/o comments)
    public void init_stage(Stage primaryStage) {
        
        // background image
        Image image1 = new Image(getClass().getResourceAsStream("background.gif"));
        background1 = new ImageView(); background1.setImage(image1); background1.setOpacity(0.5);
        background2 = new ImageView(); background2.setImage(image1); background2.setOpacity(0.5);
        background3 = new ImageView(); background3.setImage(image1); background3.setOpacity(0.5);
        
        // exit button for application
        exitButton = new Button();
        button_format(exitButton, 375, -275, Color.rgb(0,255,0), 12, IDLE_BUTTON_STYLE, "X");
        exitButton.setMinSize(20, 20); exitButton.setPrefSize(25, 25);
        
        // exit button handler
        exitButton.setOnAction(e -> { System.exit(0); });
        
        // button animation for when mouse ontop
        exitButton.setOnMouseEntered(e -> { exitButton.setStyle(HOVERED_BUTTON_STYLE); });
        
        // button animation for when mouse exited
        exitButton.setOnMouseExited(e -> { exitButton.setStyle(IDLE_BUTTON_STYLE); });
        
        // fills the background with a grey-color
        background = new BackgroundFill(Color.rgb(35, 35, 35, 0.9), new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));

        // Dropshadow effect for SplashScreen Menu
        dropShadow = new DropShadow();
        dropshadow_format(dropShadow, 5.0, 3.0, 3.0, Color.BLACK);
        
        // Dropshadow effect for gameBoard buttons
        buttonShadow = new DropShadow();
        dropshadow_format(buttonShadow, 5.0, 3.0, 3.0, Color.rgb(0, 255, 0, 0.25));
    }
    
    // sets up splash screen GUI (<24 lines w/o comments)
    public void init_splash_screen(Stage primaryStage) {

        // Label for SplashScreen Menu (tic-tac-toe)
        Label splashLabel1 = new Label("TIC-TAC-TOE");
        label_format(splashLabel1, 0, -150, 40, Color.rgb(0, 255, 0));

        // Label for SplashScreen Menu (3D)
        Label splashLabel2 = new Label("3D");
        label_format(splashLabel2, 0, -50, 200, Color.rgb(0, 255, 0));

        // Label for SplashScreen Menu (group name/members)
        Label splashLabel3 = new Label("Team 3: \n -> Grant\n -> Taylor\n -> Ethan\n -> Chris");
        label_format(splashLabel3, 0, 128, 16, Color.rgb(0, 255, 0));

        // Button for SplashScreen
        Button splashButton = new Button();
        button_format(splashButton, 0, 225, Color.rgb(0, 255, 0, 1), 20, IDLE_BUTTON_STYLE, "Click to Continue");
        
        // button animations
        splashButton.setOnMouseEntered(e -> { splashButton.setStyle(HOVERED_BUTTON_STYLE); splashButton.setEffect(buttonShadow); });
        splashButton.setOnMouseExited(e -> { splashButton.setStyle(IDLE_BUTTON_STYLE); splashButton.setEffect(dropShadow); });

        // when button clicked, transition scene
        splashButton.setOnAction(e -> { primaryStage.setScene(instructionScene); });

        // StackPane set-up for SplashScreen
        splashScreen = new StackPane();
        splashScreen.getChildren().addAll(background1, splashLabel1, splashLabel2, splashLabel3, splashButton);
        splashScreen.setBackground(new Background(background));
        splashScene = new Scene(splashScreen, 800, 600);
    }
    
    // sets up instruction screen GUI (<24 lines w/o comments)
    public void init_instruction_screen(Stage primaryStage) {
        
        // Label for instructionScreen Menu (How to play)
        Label instructionLabel1 = new Label("How to Play:");
        label_format(instructionLabel1, 0, -50, 40, Color.rgb(0, 255, 0));

        // Label for instructionScreen Menu (Instructions)
        Label instructionLabel2 =
            new Label("1. Enter your initials for the high-score table\n"
                    + "2. Click a button to place your move\n"
                    + "3. Try to get four in a row, column, or diagonal to win\n");
        label_format(instructionLabel2, 0, 50, 20, Color.rgb(0, 255, 0));

        // Button for instructionScreen
        Button instructionButton = new Button();
        button_format(instructionButton, 0, 225, Color.rgb(0,255,0), 20, IDLE_BUTTON_STYLE, "Click to Continue");
        
        // button animations
        instructionButton.setOnMouseEntered(e -> { instructionButton.setStyle(HOVERED_BUTTON_STYLE); instructionButton.setEffect(buttonShadow); });
        instructionButton.setOnMouseExited(e -> { instructionButton.setStyle(IDLE_BUTTON_STYLE); instructionButton.setEffect(dropShadow); });
        
        // instruction button handler
        instructionButton.setOnAction(e -> { primaryStage.setScene(gameScene); });

        // StackPane set-up for instructionScreen
        StackPane instructionScreen = new StackPane();
        instructionScreen.getChildren().addAll(background2, instructionLabel1, instructionLabel2, instructionButton);
        instructionScreen.setBackground(new Background(background));
        instructionScene = new Scene(instructionScreen, 800, 600);
    }
    
    // sets up game screen GUI (<24 lines)
    public static void init_game_screen(Stage primaryStage) {
        
        // draw board elements
        draw_score_table();
        draw_game_board();
        draw_backdrops();
        draw_endgame_message();

        // set-up game scene
        StackPane gameScreen = new StackPane();
        gameScreen.getChildren().addAll(
            background3, scoreTable, highScoreList, gameTable,
            instructionTable, gameInstructions, gameInstructionsTitle,
            layer1, layer2, layer3, layer4,
            continueLabel, yesButton, noButton, goButton, exitButton
        );
        gameScreen.setBackground(new Background(background));
        gameScene = new Scene(gameScreen, 800, 600);
    }
    
    /* left this function larger than 24 lines because creating further functions
       to save space would depreciate the readibility of this function */
    // draws the high-score table
    public static void draw_score_table() {
        
        // retrieve Scores
        try {
            be.retrieveScores();
        } 
        catch (FileNotFoundException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        
        // init labels for highscore table
        Label tableTitle = new Label("High Scores:");
        Label firstLabel = new Label("1) ");    firstScore = new Label("lol 10");
        Label secondLabel = new Label("2) ");   secondScore = new Label("glh 8");
        Label thirdLabel = new Label("3) ");    thirdScore = new Label("hii 5");
        Label fourthLabel = new Label("4) ");   fourthScore = new Label("tes 2");
        Label fifthLabel = new Label("5) ");    fifthScore = new Label("jfk 1");
        Label initialsLabel = new Label("Initials: ");
        Label playerBestScoreLabel = new Label("Player Best Score: ");
        Label scoreLabel = new Label("10");
        
        // format labels
        label_format(tableTitle, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(firstLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(secondLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(thirdLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(fourthLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(fifthLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(firstScore, -110, 0, 16, Color.rgb(0, 255, 0));
        label_format(secondScore, -110, 0, 16, Color.rgb(0, 255, 0));
        label_format(thirdScore, -110, 0, 16, Color.rgb(0, 255, 0));
        label_format(fourthScore, -110, 0, 16, Color.rgb(0, 255, 0));
        label_format(fifthScore, -110, 0, 16, Color.rgb(0, 255, 0));
        label_format(initialsLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(playerBestScoreLabel, 0, 0, 16, Color.rgb(0, 255, 0));
        label_format(scoreLabel, 0, 0, 16, Color.rgb(0, 255, 0));

        // Initials text-field
        initialsField = new TextField ();
        initialsField.setPromptText("initials");
        initialsField.setFont(Font.font("Mank Sans", 10));
        initialsField.setTranslateX(-75);
        initialsField.setMinSize(10, 10);
        initialsField.setPrefSize(50, 20);
        
        // initials submission button 
        goButton = new Button();
        button_format(goButton, -220, -120, Color.rgb(0,255,0,0.75), 10, IDLE_BUTTON_STYLE, "Go!");
        goButton.setMinSize(30, 20); 
        goButton.setPrefSize(30, 20);
        
        // go button logic 
        goButton.setOnAction(e -> {
           final String initials = initialsField.getText();
           initials.replaceAll("\\s+","");
           System.out.println(initials);
           if (!initials.equals("")) {
               be.addPlayerInitials(initials);
           }
           else {
               be.addPlayerInitials("Guest");
           }
           goButton.setDisable(true);
           initialsField.setDisable(true);
           disable_all_buttons(false);
           scoreLabel.setText(be.getPlayerScore(initials));
        });
        
        // button animation for mouse entered
        goButton.setOnMouseEntered(e -> {
            goButton.setStyle(HOVERED_BUTTON_STYLE);
            goButton.setEffect(buttonShadow);
        });
        
        // button animation for mouse exited
        goButton.setOnMouseExited(e -> {
            goButton.setStyle(IDLE_BUTTON_STYLE);
            goButton.setEffect(dropShadow);
        });

        // gridpane for gameScreen Menu (high-score list)
        highScoreList = new GridPane();
        highScoreList.add(tableTitle, 0, 0, 1, 7);
        highScoreList.add(firstLabel, 0, 1, 1, 7);  highScoreList.add(firstScore, 1, 1, 1, 7);
        highScoreList.add(secondLabel, 0, 2, 1, 7); highScoreList.add(secondScore, 1, 2, 1, 7);
        highScoreList.add(thirdLabel, 0, 3, 1, 7);  highScoreList.add(thirdScore, 1, 3, 1, 7);
        highScoreList.add(fourthLabel, 0, 4, 1, 7); highScoreList.add(fourthScore, 1, 4, 1, 7);
        highScoreList.add(fifthLabel, 0, 5, 1, 7); highScoreList.add(fifthScore, 1, 5, 1, 7);
        highScoreList.add(initialsLabel, 0, 6, 1, 7); highScoreList.add(initialsField, 1, 6, 1, 7);
        highScoreList.add(playerBestScoreLabel, 0, 7, 1, 7);
        highScoreList.add(scoreLabel, 1, 7, 1, 7);

        // high-score grid formatting
        highScoreList.setHgap(5);
        highScoreList.setVgap(20);
        highScoreList.setTranslateX(50);
        highScoreList.setTranslateY(0);
        highScoreList.gridLinesVisibleProperty();
        highScoreList.setAlignment(Pos.TOP_LEFT);
    }
    
    /* left this function larger than 24 lines because creating further functions
       to save space would depreciate the readibility of this function */
    // draws the game board
    public static void draw_game_board() {

        // set up array of buttons for gameBoard
        boardButtons = new Button[64];
        for (int buttonIndex = 0; buttonIndex < boardButtons.length; buttonIndex++) {
            Button btn = new Button();
            btn.setMinSize(35, 35);
            btn.setTextFill(Color.rgb(0,255,0,0.75));
            btn.setStyle(IDLE_BUTTON_STYLE);
            btn.setEffect(dropShadow);
            btn.setText("");
            boardButtons[buttonIndex] = btn;
        }
        disable_all_buttons(true);
        
        layer1 = new GridPane(); 
        layer2 = new GridPane();
        layer3 = new GridPane();
        layer4 = new GridPane();
        init_layer(layer1, 50, 325, 0);
        init_layer(layer2, 225, 325, 16);
        init_layer(layer3, 400, 325, 32);
        init_layer(layer4, 575, 325, 48);
        
        // button handler
        for (int index = 0; index < boardButtons.length; index++) {
            final int buttonNumber = index;
            Button boardCell = boardButtons[index];
            boardButtons[index].setOnAction(e -> {
                
                // player move logic
                int player_move = buttonNumber;
                System.out.println("Player Move: " + buttonNumber);
                if (be.makeMove(buttonNumber, 1) && boardCell.getText().equals("")) {
                    boardCell.setText("X");
                    boardCell.setTextFill(Color.GREENYELLOW);
                }
                winCheck();
                
                // AI move logic
                int ai_move = be.computerMove();
                System.out.println("AI Move: " + ai_move);
                be.makeMove(ai_move, -1);
                boardButtons[ai_move].setText("O");
                boardButtons[ai_move].setTextFill(Color.RED);
                disable_occupied_buttons(true);
                winCheck();
            });
            
            // button animation for when mouse ontop
            boardButtons[index].setOnMouseEntered(e -> {
                boardCell.setStyle(HOVERED_BUTTON_STYLE);
                boardCell.setEffect(buttonShadow);
            });
            
            boardButtons[index].setOnMouseExited(e -> {
                boardCell.setStyle(IDLE_BUTTON_STYLE);
                boardCell.setEffect(dropShadow);
            });
        }
    }
    
    /* left this function larger than 24 lines because creating further functions
       to save space would depreciate the readibility of this function */
    // draws end-game yes/no dialogue
    public static void draw_endgame_message() {
        
        // Continue game label
        continueLabel = new Label("Play again?");
        label_format(continueLabel, 0, 15, 32, Color.rgb(0,255,0,0.75));
        continueLabel.setVisible(false);
        
        // yes button
        yesButton = new Button();
        button_format(yesButton, 125, 15, Color.rgb(0,255,0,0.75), 20, IDLE_BUTTON_STYLE, "Yes");
        yesButton.setMinSize(35, 35); yesButton.setVisible(false);
        
        // yes button handler, clears both boards for a new game
        yesButton.setOnAction(e -> {
            be.clearGameBoard();
            clearBoard();
            
            // if player plays another game, hide these buttons
            disable_all_buttons(true);
            continueLabel.setVisible(false);
            yesButton.setVisible(false);
            noButton.setVisible(false);
            goButton.setDisable(false);
            initialsField.setDisable(false);
        });
        
        // button animations
        yesButton.setOnMouseEntered(e -> { yesButton.setStyle(HOVERED_BUTTON_STYLE); yesButton.setEffect(buttonShadow); });
        yesButton.setOnMouseExited(e -> { yesButton.setStyle(IDLE_BUTTON_STYLE); yesButton.setEffect(null); });
        
        // no button handler
        noButton = new Button();
        button_format(noButton, 200, 15, Color.RED, 20, IDLE_BUTTON_STYLE, "No");
        noButton.setMinSize(35, 35);
        noButton.setVisible(false);
        noButton.setOnAction(e -> { System.exit(0); });
        
        // button animation for when mouse ontop
        noButton.setOnMouseEntered(e -> { noButton.setStyle(HOVERED_BUTTON_STYLE); noButton.setEffect(buttonShadow); });
        noButton.setOnMouseExited(e -> { noButton.setStyle(IDLE_BUTTON_STYLE); noButton.setEffect(null); });
    }
    
    // draws backdrops (<24 lines)
    public static void draw_backdrops() {
        // rectangle for gameScreen Menu (high-score table)
        scoreTable = new Rectangle(0,0,200,200);
        rectangle_format(scoreTable, -275, -175, 25, 25);

        // rectangle for gameScreen Menu (game table)
        gameTable = new Rectangle(0,0,750,300);
        rectangle_format(gameTable, 0, 125, 25, 25);
        
        // rectangle for gameScreen Menu (instruction table)
        instructionTable = new Rectangle(0,0,450,200);
        rectangle_format(instructionTable, 100, -175, 25, 25);
        
        // rectangle for gameScreen Menu (instruction label)
        gameInstructions =
                new Label("1. Enter your initials for the high-score table\n"
                        + "2. Click a button to place your move\n"
                        + "3. Get four in a row, column, or diagonal to win\n");
        label_format(gameInstructions, 100, -150, 20, Color.rgb(0,255,0));
        
        // label for gameScreen Menu (instruction label)
        gameInstructionsTitle = new Label("How to Play:");
        label_format(gameInstructionsTitle, 100, -225, 40, Color.rgb(0,255,0));
    }
    
    /* left this function larger than 24 lines because creating further functions
       to save space would depreciate the readibility of this function */
    // checks for victory
    public static boolean winCheck() {
        int isWin = be.victoryCheck();
        if (isWin == 1 || isWin == -1) {
            
            // player win logic
            if (isWin == 1) {
                be.incrementScore(be.currentPlayer);
            }
            
            try {
                be.writeScores();
            } 
            catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            firstScore.setText(be.scores[0].toString());
            secondScore.setText(be.scores[1].toString());
            thirdScore.setText(be.scores[2].toString());
            fourthScore.setText(be.scores[3].toString());
            fifthScore.setText(be.scores[4].toString());
                
            // flash victory moves
            ArrayList<Integer> winMoves = be.getVictoryMoves();
            flashButton1 = blink_cell(flashButton1, winMoves.get(0));
            flashButton2 = blink_cell(flashButton2, winMoves.get(1));
            flashButton3 = blink_cell(flashButton3, winMoves.get(2));
            flashButton4 = blink_cell(flashButton4, winMoves.get(3));
            
            // Fade logic for continue to play buttons/label
            FadeTransition yesFade = new FadeTransition(Duration.millis(1000), yesButton);
            FadeTransition noFade = new FadeTransition(Duration.millis(1000), noButton);
            FadeTransition continueFade = new FadeTransition(Duration.millis(1000), continueLabel);
            yesFade.setFromValue(0.0); yesFade.setToValue(1.0);
            noFade.setFromValue(0.0); noFade.setToValue(1.0);
            continueFade.setFromValue(0.0); continueFade.setToValue(1.0);
            
            // disable/hide buttons
            disable_all_buttons(true);
            yesFade.play(); noFade.play(); continueFade.play();
            continueLabel.setVisible(true);
            yesButton.setVisible(true);
            noButton.setVisible(true);
            return true;
        } 
        else {
            return false;
        }
    }
    
    // clears GUI board (<24 lines)
    public static void clearBoard() {
        flashButton1.stop(); flashButton2.stop(); flashButton3.stop(); flashButton4.stop();
        for (int boardIndex = 0; boardIndex < boardButtons.length; boardIndex++) {
            boardButtons[boardIndex].setText("");
            boardButtons[boardIndex].setStyle(IDLE_BUTTON_STYLE);
            boardButtons[boardIndex].setVisible(true);
        }
    }
    
    // toggles all gameBoard buttons or buttons that already have peices (<24 lines)
    public static void disable_all_buttons(boolean isDisabled) {
        for (int buttonIndex = 0; buttonIndex < boardButtons.length; buttonIndex++) {
            boardButtons[buttonIndex].setDisable(isDisabled);
        }
    }
    
    // toggles all gameBoard buttons or buttons that already have peices (<24 lines)
    public static void disable_occupied_buttons(boolean isDisabled) {
        for (int buttonIndex = 0; buttonIndex < boardButtons.length; buttonIndex++) {
            if (!boardButtons[buttonIndex].getText().isEmpty()) {
                boardButtons[buttonIndex].setDisable(isDisabled);
                boardButtons[buttonIndex].setVisible(isDisabled);
            }
        }
    }
    
    // construct and format a gridpane (<24 lines)
    public static void init_layer(GridPane pane, int x, int y, int index) {
        layer_format(pane, 40, 45, x, y, 35);
        int buttonIndex = index;
        for (int boardRowIndex = 0; boardRowIndex < 4; ++boardRowIndex) {
            for (int boardColIndex = 0; boardColIndex < 4; ++boardColIndex) {
                pane.add(boardButtons[buttonIndex], boardColIndex, boardRowIndex, 3, 3);
                buttonIndex += 1;
            }
        }
    }
    
    // set-up dropshadow formatting (<24 lines)
    public static void dropshadow_format(DropShadow ds, double radius, double x, double y, Color color) {
        ds.setRadius(radius);
        ds.setOffsetX(x);
        ds.setOffsetY(y);
        ds.setColor(color);
    }
    
    // set-up button formatting (<24 lines)
    public static void button_format(Button btn, int x, int y, Color c, int font, String style, String text) {
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setTextFill(c);
        btn.setFont(Font.font("Mank Sans", font));
        btn.setStyle(style);
        btn.setText(text);
        btn.setEffect(dropShadow);
    }
    
    // set-up layer formatting (<24 lines)
    public static void layer_format(GridPane layer, int Hgap, int Vgap, int x, int y, int size) {
        layer.setHgap(Hgap);
        layer.setVgap(Vgap);
        layer.setTranslateX(x);
        layer.setTranslateY(y);
        layer.setMinSize(size, size);
        layer.gridLinesVisibleProperty();
        layer.setAlignment(Pos.TOP_LEFT);
    }
    
    // set-up label formatting (<24 lines)
    public static void label_format(Label label, int x, int y, int font, Color color) {
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(Font.font("Mank Sans", font));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextFill(color);
        label.setEffect(dropShadow);
    }
    
    // set-up rectangle formatting (<24 lines)
    public static void rectangle_format(Rectangle rect, int x, int y, int h, int w) {
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        rect.setArcHeight(h);
        rect.setArcWidth(w);
        rect.setStroke(Color.rgb(0,255,0,1));
        rect.setFill(Color.rgb(35, 35, 35, 0.90));
        rect.setEffect(buttonShadow);
    }
    
    // blinks a specified cell in the game board indefinitely (<24 lines)
    public static Timeline blink_cell(Timeline line, int index) {
        line = new Timeline(
            new KeyFrame(Duration.seconds(0.5), evt -> boardButtons[index].setStyle(WIN_BUTTON_STYLE)),
            new KeyFrame(Duration.seconds(1.0), evt -> boardButtons[index].setStyle(IDLE_BUTTON_STYLE)));
        line.setCycleCount(Animation.INDEFINITE);
        line.play();
        return line;
    }

    // main function (<24 lines)
    public static void main(String[] args) {
        be = new backend();
        launch(args);
    }
    
}
