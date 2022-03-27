package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected double x;
    protected double y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit;
        this.y = yUnit;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, y * Sprite.SCALED_SIZE, x * Sprite.SCALED_SIZE);
    }
}
