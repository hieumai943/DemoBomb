package bomberman.Levels;
import bomberman.entities.Entity;
import bomberman.entities.enemy.Ballom;
import bomberman.entities.enemy.Kondoria;
import bomberman.entities.enemy.Oneal;
import bomberman.graphics.MapCreation;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static bomberman.BombermanGame.*;
import static bomberman.entities.Bomber.*;
import static bomberman.entities.Bomb.*;
public class Level2 {
    public Level2() {
        entities.clear();
        stillObjects.clear();
        swap_kill = 1;
        try {
            new MapCreation("Level2.txt");
        } catch (Exception e){}
        bomberman.setLife(true);
        bomberman.setX(32);
        bomberman.setY(32);

        is_bomb = 0;
        speed =1;

        Entity enemy1 = new Ballom(5, 5, Sprite.balloom_left1.getFxImage());
        Entity enemy2 = new Ballom(11, 9, Sprite.balloom_left1.getFxImage());
        entities.add(enemy1);
        entities.add(enemy2);

        Entity enemy3 = new Kondoria(1, 3, Sprite.kondoria_right1.getFxImage());
        Entity enemy4 = new Kondoria(1, 7, Sprite.kondoria_right1.getFxImage());
        Entity enemy5 = new Kondoria(1, 11, Sprite.kondoria_right1.getFxImage());
        entities.add(enemy3);
        entities.add(enemy4);
        entities.add(enemy5);

        Entity enemy6 = new Oneal(7, 5, Sprite.oneal_right1.getFxImage());
        Entity enemy7 = new Oneal(19, 7, Sprite.oneal_right1.getFxImage());
        entities.add(enemy6);
        entities.add(enemy7);

        for (Entity animal : entities) {
            animal.setLife(true);
        }
    }
}
