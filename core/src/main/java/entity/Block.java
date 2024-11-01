package entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Block {
    private int x, y, width, height;
    private boolean destroyed;
    private Color color;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;

        // Generar un color aleatorio para cada bloque
        Random random = new Random();
        this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
    }

    public void draw(ShapeRenderer shape) {
        if (!destroyed) {
            shape.setColor(color);
            shape.rect(x, y, width, height);
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    // Getters para las coordenadas y tamaño del bloque
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}