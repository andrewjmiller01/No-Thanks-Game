package boardgameproject;

import java.lang.reflect.Field;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import static javafx.geometry.Side.LEFT;
import static javafx.geometry.Side.TOP;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class UserInterface extends Application{
    private int turn = 0;
    NoThanks game = new NoThanks(5);
    NoThanksPlayer[] players = game.getPlayers();
    NoThanksCard activeCard = (NoThanksCard) game.getDeck().drawCard();
    Image img = activeCard.getImage();
    ImageView displayImage = new ImageView();
    Tooltip hoverOverCard = new Tooltip();
    int totalNumOfCards = 0;
    final Image bgImage = new Image("file:C:\\Users\\Andrew\\Documents\\NetBeansProjects\\BoardGameProject\\src\\boardgameproject\\NoThanks_v2\\table_background.jpg", 100, 100, true, true);
    final ImageView background = new ImageView(bgImage);
    Background bg = new Background(new BackgroundImage(bgImage, NO_REPEAT, NO_REPEAT, new BackgroundPosition(LEFT, 0, false, TOP, 0, false), new BackgroundSize(100, 100, true, true, true, true)));
    
    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        root.setId("pane");
        Parent pane = root.getParent();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("NoThanksStyling.css").toExternalForm());
        primaryStage.setTitle("Game Box");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        primaryStage.setMaxHeight(screen.getMaxY());
        primaryStage.setMaxWidth(screen.getMaxX());
        
        displayImage.setImage(img);
        hackTooltipStartTiming(hoverOverCard);
        
        Button addChip = new Button();
        addChip.setText("Add Chip");
        addChip.setOnAction((ActionEvent event) -> {
            if(players[turn % game.getNumOfPlayers()].getNumberOfChips() != 0 && totalNumOfCards != 24)
                players[turn++ % game.getNumOfPlayers()].putChip(activeCard);
        });
        
        Button grabCard = new Button();
        grabCard.setText("Grab Card");
        grabCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(totalNumOfCards != 24){
                    totalNumOfCards++;
                    players[turn % game.getNumOfPlayers()].grabCard(activeCard);
                    ImageView iv = players[turn % game.getNumOfPlayers()].getImages()[players[turn % game.getNumOfPlayers()].getNumberOfCards()-1];
                    iv.setFitWidth(80);
                    iv.setFitHeight(130);
                    root.getChildren().add(iv);
                    turn++;
                    if(game.continuing()){
                        activeCard = (NoThanksCard) game.getDeck().drawCard();
                        img = activeCard.getImage();
                        displayImage.setImage(img);
                    }
                    else{
                        displayImage.setImage(null);
                        game.displayScores();
                    }
                }
            }
        });
        
        primaryStage.setWidth(512);
        primaryStage.setHeight(512);
        
        Canvas canvas = new Canvas(primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        displayImage.setFitWidth(90);
        displayImage.setFitHeight(150);
        root.getChildren().add(background);
        root.getChildren().add(addChip);
        root.getChildren().add(grabCard);
        root.getChildren().add(displayImage);
        background.setFitWidth(primaryStage.getMaxWidth());
        background.setFitHeight(primaryStage.getMaxHeight());
        
        
        final long startNanoTime = System.nanoTime();
        
        if(game.continuing()){
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                displayImage.setLayoutX(scene.getWidth() / 2 - 35);
                displayImage.setLayoutY(5);
                addChip.setLayoutX(displayImage.getLayoutX() - 25);
                addChip.setLayoutY(displayImage.getLayoutY() + 160);
                grabCard.setLayoutX(addChip.getLayoutX() + addChip.getWidth() + 2);
                grabCard.setLayoutY(addChip.getLayoutY());
                
                hoverOverCard.setText("Card Value: " + activeCard.getCardValue());
                Tooltip.install(displayImage, hoverOverCard);
                
                gc.setStroke(Color.BLACK);
                gc.clearRect(0, 0, primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
                
                for(int i = 0; i < game.getNumOfPlayers(); i++){
                    if(turn % game.getNumOfPlayers() == i)
                        gc.setStroke(Color.RED);
                    gc.strokeText("Player " + (i + 1), scene.getWidth() * i/game.getNumOfPlayers(), 250 + (200 * (i % 2)));
                    gc.strokeText(Integer.toString(players[i].getNumberOfChips()), scene.getWidth() * i/game.getNumOfPlayers(), 275 + (200 * (i % 2)));
                    gc.setStroke(Color.BLACK);
                    for(int j = 0; j < players[i].getNumberOfCards(); j++){
                        players[i].getImages()[j].setLayoutX(scene.getWidth() * i/game.getNumOfPlayers() + (j*20));
                        players[i].getImages()[j].setLayoutY(300 + (200 * (i % 2)));
                    }
                }
            }
        }.start();
        
        primaryStage.show();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}