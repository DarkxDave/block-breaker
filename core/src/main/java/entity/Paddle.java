package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    private int x, y, width, height;
    private int speed = 10; // Velocidad de movimiento de la paleta

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(Color.BLUE);
        shape.rect(x, y, width, height);
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0; // Evita que se salga de la pantalla por la izquierda
    }

    public void moveRight() {
        x += speed;
        if (x + width > Gdx.graphics.getWidth()) x = Gdx.graphics.getWidth() - width; // Evita que se salga de la pantalla por la derecha
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
