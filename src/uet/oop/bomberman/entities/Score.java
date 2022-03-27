package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Score extends Entity {
    private int i = 0;
    private long time = 0;

    public Score(double x, double y, Image img) {
        super(x, y, img);
    }

    public void update(long l) {
        if (l - time < 1_000_000_000) return;
        time = l;
        i++;
        if(i==2) img = Sprite.null_24.getFxImage();
    }

    public int getI(){
        return i;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
