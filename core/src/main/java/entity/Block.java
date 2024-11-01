package entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block extends GameObject implements Drawable {
    private boolean destroyed;
    private Color color;

    public Block(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.destroyed = false;
        this.color = Color.RED;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        if (!destroyed) {
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}