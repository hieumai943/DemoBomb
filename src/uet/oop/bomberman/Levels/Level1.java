package bomberman.Levels;

import bomberman.graphics.MapCreation;


import java.io.FileNotFoundException;

import static bomberman.view.BombermanGame.*;
import static bomberman.view.BombermanGame.entities;
import static bomberman.entities.Bomber.*;
import static bomberman.entities.Bomb.*;
import static bomberman.entities.items.SpeedItem.*;
public class Level1 {
    public Level1() {
        stillObjects.clear();
        entities.clear();
        swap_kill = 1;
        power_bomb = 0;
        try {
            new MapCreation("res/levels/Level1.txt");
        }catch (Exception e ){}
        is_bomb = 0;
        speed = 1;
    }
}
