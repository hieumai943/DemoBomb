package bomberman.Levels;

import bomberman.entities.Entity;
import bomberman.entities.enemy.Ballom;
import bomberman.entities.enemy.Doll;
import bomberman.entities.enemy.Kondoria;
import bomberman.entities.enemy.Oneal;
import bomberman.graphics.MapCreation;
import bomberman.graphics.Sprite;

import java.io.FileNotFoundException;

import static bomberman.BombermanGame.*;
import static bomberman.BombermanGame.entities;
import static bomberman.entities.Bomb.is_bomb;
import static bomberman.entities.Bomber.swap_kill;

public class Level3 {
    public Level3() {
        entities.clear();
        stillObjects.clear();
        swap_kill = 1;
        try {
            new MapCreation("Level3.txt");
        } catch (FileNotFoundException e){}
        bomberman.setLife(true);
        bomberman.setX(32);
        bomberman.setY(32);

        is_bomb = 0;


        Entity enemy1 = new Ballom(5, 5, Sprite.balloom_left1.getFxImage());
        Entity enemy2 = new Ballom(11, 9, Sprite.balloom_left1.getFxImage());
        entities.add(enemy1);
        entities.add(enemy2);

        Entity enemy3 = new Doll(7, 5, Sprite.doll_left1.getFxImage());
        entities.add(enemy3);

        for (Entity animal : entities) {
            animal.setLife(true);
        }
    }
}
