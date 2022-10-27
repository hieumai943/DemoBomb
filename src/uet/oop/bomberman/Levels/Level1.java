package bomberman.Levels;

import bomberman.entities.enemy.Ballom;
import bomberman.entities.Entity;
import bomberman.entities.enemy.Oneal;
import bomberman.graphics.MapCreation;
import bomberman.graphics.Sprite;


import static bomberman.BombermanGame.*;
import static bomberman.BombermanGame.entities;
import static bomberman.entities.Bomber.*;
import static bomberman.entities.Bomb.*;

public class Level1 {

         public Level1() {
            stillObjects.clear();
            entities.clear();

            swap_kill = 1;
            try {
                new MapCreation("res/levels/TestMap.txt");
            }catch (Exception e){}
            bomberman.setX(32);
            bomberman.setY(32);
            bomberman.setLife(true);
            is_bomb = 0;

            Entity enemy1 = new Ballom(4, 4, Sprite.balloom_left1.getFxImage());
            Entity enemy2 = new Ballom(9, 9, Sprite.balloom_left1.getFxImage());
            Entity enemy3 = new Ballom(22, 6, Sprite.balloom_left1.getFxImage());
            entities.add(enemy1);
            entities.add(enemy2);
            entities.add(enemy3);

            Entity enemy4 = new Oneal(7, 6, Sprite.oneal_right1.getFxImage());
            Entity enemy5 = new Oneal(13, 8, Sprite.oneal_right1.getFxImage());
            entities.add(enemy4);
            entities.add(enemy5);

            // set default for enemy
            for (Entity Entity : entities) {
                Entity.setLife(true);
            }
        }
    }

