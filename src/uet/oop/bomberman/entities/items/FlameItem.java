package bomberman.entities.items;

import bomberman.entities.Bomb;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import static bomberman.view.BombermanGame.stillObjects;
import static bomberman.view.BombermanGame.*;


public class FlameItem  extends Items{
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public FlameItem(boolean received) {
        super(received);
    }

    public FlameItem() {
    }

    // Extends the range of flame if a player gets the item
    @Override
    public void update() {

        for (Entity entity : stillObjects)
            if (entity instanceof FlameItem && !this.received)
                if (list_kill[entity.getX() / 32][entity.getY() / 32] == 4)
                    entity.setImg(Sprite.powerup_flames.getFxImage());

        if (!this.received)
            if (bomberman.getX() == this.x && bomberman.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.received = true;
                Bomb.power_bomb += 2;
            }
    }
}
