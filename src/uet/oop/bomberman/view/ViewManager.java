package bomberman.view;


import bomberman.model.Instruction;
import bomberman.model.MenuButton;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.util.Duration;


import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class ViewManager {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 650;
    private Image bgrImg;
    private  ImageView bgrimg;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private static final int MENU_START_X = 150;
    private static final int MENU_START_Y = 150;
    private Instruction hdscreen;
    private Instruction highscreen;

    private List<MenuButton> menuButtons = new ArrayList<>();
    private GridPane gridPane1;
    private GridPane gridPane2;
    private AnimationTimer bgrTimer;

    private Instruction sceneToHide;
    private MediaPlayer mediaPlayer;
    private int count =0;

    public void music() throws Exception {
        String path = getClass().getResource("bgr.mp3").getPath();
        Media h = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        if(count%2==0) mediaPlayer.play();
        else mediaPlayer.isMute();


    }

    public ViewManager() {


        mainPane = new AnchorPane();

        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        try {
            createBackGround();

        } catch (Exception e) {
        }
        try {
            music();

        } catch (Exception e) {
            System.out.println("Loi");
        }
        createbuttons();

        try {
            creatImgBgr();
        } catch (Exception e) {
        }
        try {
            creatWordBgr();
        } catch (Exception e) {
        }
        createScreen();
    }
    public void tryFade()   {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setFromValue(1);
        fade.setToValue(0);


        fade.setNode(mainPane);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)   {

                BombermanGame bombermanGame = new BombermanGame();
                bombermanGame.createGame(mainStage);
            }
        });
        fade.play();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void showScene(Instruction subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveScene();
        }
        subScene.moveScene();
        sceneToHide = subScene;

    }

    private void createScreen() {
        try {
            hdscreen = new Instruction("src\\uet\\resources\\Instruction.png");
            highscreen = new Instruction("src\\uet\\resources\\instruction.jpg");


        } catch (Exception e) {
        }
        mainPane.getChildren().add(hdscreen);
        mainPane.getChildren().add(highscreen);

    }

    protected void createbuttons() {
        createdStartButton();
        createdScoresButton();
        createdInsButton();
        createdSoundButton();
        createdExitButton();

    }

    private void addMenuButton(MenuButton button) {
        button.setLayoutX(MENU_START_X);
        button.setLayoutY(MENU_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    protected void createdStartButton() {
        MenuButton startButton = new MenuButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tryFade();
            }
        });
    }

    protected void createdScoresButton() {
        MenuButton scoreButton = new MenuButton("HIGH SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(highscreen);
            }
        });

    }

    protected void createdInsButton() {
        MenuButton InsButton = new MenuButton("INSTRUCTION");
        addMenuButton(InsButton);
        InsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(hdscreen);
            }
        });

    }
    protected void createdSoundButton() {
        MenuButton soundButton = new MenuButton("SOUND");
        addMenuButton(soundButton);
        soundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.pause();
                count++;
                if(count %2 ==0) mediaPlayer.play();
            }
        });

    }
    protected void createdExitButton() {
        MenuButton ExitButton = new MenuButton("EXIT");
        addMenuButton(ExitButton);
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackGround() throws Exception {
         bgrImg = new Image(new FileInputStream("src\\uet\\resources\\bgr.gif"), 1000, 650, false, true);
         bgrimg = new ImageView(bgrImg);
        BackgroundImage bg = new BackgroundImage(bgrImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1000, 650, false, false, false, false));
        mainPane.setBackground(new Background(bg));
    }


    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 2);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 2);
        if (gridPane1.getLayoutY() >= 650) {
            gridPane1.setLayoutY(-650);
        }
        if (gridPane2.getLayoutY() >= 650) {
            gridPane2.setLayoutY(-600);
        }
    }


    private void creatImgBgr() throws Exception {
        Image logo = new Image(new FileInputStream("src\\uet\\resources\\menuImg.png"));
        ImageView img = new ImageView();
        img.setImage(logo);

        img.setLayoutX(600);
        img.setLayoutY(200);


        mainPane.getChildren().add(img);
    }

    private void creatWordBgr() throws Exception {
        Image logo = new Image(new FileInputStream("src\\uet\\resources\\bomber (2).png"));
        ImageView img = new ImageView();
        img.setImage(logo);

        img.setLayoutX(300);
        img.setLayoutY(10);
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(new DropShadow());
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(null);
            }
        });

        mainPane.getChildren().add(img);
    }

}