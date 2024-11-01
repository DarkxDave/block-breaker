package entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Método abstracto para dibujar el objeto en pantalla
    public abstract void draw(ShapeRenderer shapeRenderer);

    // Métodos comunes para obtener las propiedades
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

    // Método para verificar si este objeto colisiona con otro
    public boolean collidesWith(GameObject other) {
        boolean intersectaX = (other.getX() + other.getWidth() >= x) && (other.getX() <= x + width);
        boolean intersectaY = (other.getY() + other.getHeight() >= y) && (other.getY() <= y + height);
        return intersectaX && intersectaY;
    }
}
