package bomberman.view;

import bomberman.model.MenuButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static bomberman.BombermanGame.*;
import static bomberman.view.ViewManager.mainStage;
import static bomberman.view.ViewManager.mediaPlayer;
import static bomberman.view.ViewManager.count;

public class PauseMenu {

    private static final int MENU_START_X = 150;
    private static final int MENU_START_Y = 150;

    private static final int WIDTH =300;
    private static final int HEIGHT =400;
    private Image PauseImg;
    private ImageView Pauseimg;
    private List<MenuButton> menuButtons = new ArrayList<>();

    private MenuButton resumeButton = new MenuButton("RESUME");
    private MenuButton SoundButton = new MenuButton("SOUND");
    private MenuButton ExitButton = new MenuButton("EXIT");

    public PauseMenu(){

    }
    private void addMenuButton(MenuButton button) {
        button.setLayoutX(MENU_START_X);
        button.setLayoutY(MENU_START_Y + menuButtons.size() * 80);
        menuButtons.add(button);
        root.getChildren().add(button);

    }
    protected void createbuttons() {
        createdResumeButton();
        createdsoundButton();
        createdExitButton();

    }
    public void createdResumeButton(){

        addMenuButton(resumeButton);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pauseMenu = false;
                root.getChildren().remove(resumeButton);
                root.getChildren().remove(SoundButton);
                root.getChildren().remove(Pauseimg);
                root.getChildren().remove(ExitButton);
            }
        });
    }
    public void  createdsoundButton(){

        addMenuButton(SoundButton);

        SoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.pause();

                count++;
                if(count %2 ==0) mediaPlayer.play();
            }
        });
    }

    public void createdExitButton(){

        addMenuButton(ExitButton);
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }
    public void showPause(){
        try {

            PauseImg = new Image(new FileInputStream("res\\textures\\Pause.jpg"), 550, 330, false, true);
            Pauseimg = new ImageView(PauseImg);
            Pauseimg.setLayoutX(110);
            Pauseimg.setLayoutY(80);
            root.getChildren().add(Pauseimg);
            createbuttons();
        } catch (Exception e){}

    }
}
