package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall {
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;
    
    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        estaQuieto = iniciaQuieto;
    }
    
    public boolean estaQuieto() {
        return estaQuieto;
    }
    
    public void setEstaQuieto(boolean bb) {
        estaQuieto = bb;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
    
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }
    
    public void update() {
        if (estaQuieto) return;
        x += xSpeed;
        y += ySpeed;
        if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y + size > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }
    
    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            color = Color.GREEN;
            ySpeed = -ySpeed;
        } else {
            color = Color.WHITE;
        }
    }
    
    private boolean collidesWith(Paddle pp) {
        boolean intersectaX = (pp.getX() + pp.getWidth() >= x - size) && (pp.getX() <= x + size);
        boolean intersectaY = (pp.getY() + pp.getHeight() >= y - size) && (pp.getY() <= y + size);        
        return intersectaX && intersectaY;
    }
    
    public void checkCollision(Block block) {
        if (collidesWith(block)) {
            ySpeed = -ySpeed;
            block.destroy();
        }
    }
    
    private boolean collidesWith(Block bb) {
        boolean intersectaX = (bb.getX() + bb.getWidth() >= x - size) && (bb.getX() <= x + size);
        boolean intersectaY = (bb.getY() + bb.getHeight() >= y - size) && (bb.getY() <= y + size);        
        return intersectaX && intersectaY;
    }
    
    // Método para reiniciar la posición de la bola encima de la paleta y detenerla
    public void resetPosition(Paddle paddle) {
        this.x = paddle.getX() + paddle.getWidth() / 2 - size / 2;
        this.y = paddle.getY() + paddle.getHeight() + size;
        this.estaQuieto = true;
    }
}
