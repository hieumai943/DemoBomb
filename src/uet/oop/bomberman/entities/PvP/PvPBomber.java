package bomberman.entities.PvP;
import javafx.scene.image.Image;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

import static bomberman.PvPGame.*;

public class PvPBomber extends Entity {
    public static int swap_kill = 1;
    private static int count_kill = 0;
    public static int bomb_number = 20;
    public static int power_bomb = 0;
    public static int speed = 1;

    public PvPBomber() {

    }

    public PvPBomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public PvPBomber(int is_move, int swap, String direction, int count, int count_to_run) {
        super(8, 1, "down", 0, 0);
    }

    private void killBomber(Entity entity) {
        if (count_kill % 16 == 0) {
            if (swap_kill == 1) {
                entity.setImg(Sprite.player_dead1.getFxImage());
                swap_kill = 2;
            }
            else if (swap_kill == 2) {
                entity.setImg(Sprite.player_dead2.getFxImage());
                swap_kill = 3;
            }
            else if (swap_kill == 3) {
                entity.setImg(Sprite.player_dead3.getFxImage());
                swap_kill = 4;
            }
            else {
                entity.setImg(Sprite.transparent.getFxImage());
                running = false;
                System.exit(0);
            }
        }
    }

    private void checkBombs() {
        if (list_kill[player1.getX() / 32][player1.getY() / 32] == 4)
            player1.setLife(false);
        if (list_kill[player2.getX() / 32][player2.getY() / 32] == 4)
            player2.setLife(false);
    }

    @Override
    public void update() {
        // testing commit
        checkBombs();
        count_kill++;
        if (!player1.life) {
            killBomber(player1);
            System.out.println("player2 win!");
        }
        if (!player2.life) {
            killBomber(player2);
            System.out.println("player1 win!");
        }
    }

}
