package entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import config.GameConfig;

public class Paddle extends GameObject implements Drawable 
{
    private int speed;
    private Color color;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = 10;
        this.color = Color.BLUE;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0;
    }

    public void moveRight() {
        x += speed;
        if (x + width > GameConfig.getScreenWidth()) {
            x = GameConfig.getScreenWidth() - width; // Ajustar la posición para que no sobrepase el límite
        }
    }

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