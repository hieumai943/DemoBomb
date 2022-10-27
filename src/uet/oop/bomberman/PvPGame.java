package bomberman;


import bomberman.components.PvPComponentMovement;
import bomberman.entities.*;
import bomberman.entities.PvP.Player1Bomb;
import bomberman.entities.PvP.Player2Bomb;
import bomberman.entities.PvP.PvPBomber;
import bomberman.graphics.PvPMapCreation;
import bomberman.graphics.Sprite;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import static bomberman.entities.PvP.Player1Bomb.*;
import static bomberman.entities.PvP.Player2Bomb.*;
import static bomberman.entities.Bomber.swap_kill;
import java.util.ArrayList;
import java.util.List;

public class PvPGame {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    //
    public static int width = 0;
    //
    public static int height = 0;
    //
    public static int[][] id_objects;
    //
    public static int[][] list_kill;
    //
    public static String[][] string_list_kill;
    //
    public static String[][] string_id_objects;
    //
    public static int level;
    public static boolean running;

    private Scene playScene;
    public static PvPBomber player1;
    public static PvPBomber player2;
    private GraphicsContext gc;
    private Canvas pvpCanvas;

    public static final List<Entity> stillObjectsPvP = new ArrayList<>();

    public Scene getMainScene() {
        return playScene;
    }

    public void createGame(Stage stage) {
        // Tao Canvas
        pvpCanvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = pvpCanvas.getGraphicsContext2D();


        // Tao root container
        Group root = new Group();
        root.getChildren().add(pvpCanvas);

        // Tao playScene
        playScene = new Scene(root);

        stage.setScene(playScene);
        stage.setTitle("Bomberman");
        stage.show();

        // Tao bomber
        player1 = new PvPBomber(1, 1, Sprite.player_right.getFxImage());
        player2 = new PvPBomber(WIDTH - 2, HEIGHT - 2, Sprite.player_left.getFxImage());

        // setLife
        player1.setLife(false);
        player2.setLife(false);

        // Tao map
        createMap();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }

    public void createMap() {
        stillObjectsPvP.clear();

        swap_kill = 1;

        new PvPMapCreation("res/levels/PvPMap.txt");
        is_bomb_player1 = 0;
        is_bomb_player2 = 0;

//        player1.speed = 1;
//        player2.speed = 2;
//        player1.power_bomb = 0;
//        player2.power_bomb = 0;

        player1.setLife(true);
        player2.setLife(true);
    }

    // moves the player1.
    private void updatePlayerInput() {
        // KeyPressed
        playScene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            // player1
            if (key == KeyCode.A) {
                // player1.setVelX(-5);
                PvPComponentMovement.left(player1);
            } else if (key == KeyCode.D) {
                // player1.setVelX(5);
                PvPComponentMovement.right(player1);
            } else if (key == KeyCode.W) {
                // player1.setVelY(-5);
                PvPComponentMovement.up(player1);
            } else if (key == KeyCode.S) {
                // player1.setVelY(5);
                PvPComponentMovement.down(player1);
            } else if (key == KeyCode.SPACE) {
                Player1Bomb.putBomb();

                // player2
            } else if (key == KeyCode.LEFT) {
                PvPComponentMovement.left(player2);
            } else if (key == KeyCode.RIGHT) {
                // player1.setVelX(5);
                PvPComponentMovement.right(player2);
            } else if (key == KeyCode.UP) {
                // player1.setVelY(-5);
                PvPComponentMovement.up(player2);
            } else if (key == KeyCode.DOWN) {
                // player1.setVelY(5);
                PvPComponentMovement.down(player2);
            } else if (key == KeyCode.ENTER) {
                Player2Bomb.putBomb();
            }
        });
    }

    public void update() {
        // keybindings update
        updatePlayerInput();

        // player1 update
        player1.update();
        player1.setCountToRun(player1.getCountToRun() + 1);
        if (player1.getCountToRun() == 4) {
            PvPComponentMovement.checkRun(player1);
            player1.setCountToRun(0);
        }

        // player2 update
        player2.update();
        player2.setCountToRun(player2.getCountToRun() + 1);
        if (player2.getCountToRun() == 4) {
            PvPComponentMovement.checkRun(player2);
            player2.setCountToRun(0);
        }

        // objects update
        for (int i = 0; i < stillObjectsPvP.size(); ++i) {
            stillObjectsPvP.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, pvpCanvas.getWidth(), pvpCanvas.getHeight());
        stillObjectsPvP.forEach(g -> g.render(gc));
        player1.render(gc);
        player2.render(gc);
    }
}