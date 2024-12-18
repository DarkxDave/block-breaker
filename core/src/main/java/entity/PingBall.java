package entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConfig;
import strategy.AcceleratedMovementStrategy;
import strategy.BallMovementStrategy;

public class PingBall extends GameObject implements Drawable {
    private int xSpeed;
    private int ySpeed;
    private Color color;
    private boolean estaQuieto;
	private BallMovementStrategy movementStrategy;

    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        super(x, y, size, size);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = Color.WHITE;
        this.estaQuieto = iniciaQuieto;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, width / 2);
    }

    public void update() {
    	movementStrategy.updateBallSpeed(this);
        if (estaQuieto) return;
        x += xSpeed;
        y += ySpeed;

        if (x - width / 2 < 0 || x + width / 2 > GameConfig.getInstance().getScreenWidth()) {
            xSpeed = -xSpeed;
        }
        if (y + height / 2 > GameConfig.getInstance().getScreenWidth()) {
            ySpeed = -ySpeed;
        }
    }

    public void checkCollision(Block block) {
        if (collidesWith(block)) {
            ySpeed = -ySpeed;
            block.destroy();
        }
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            ySpeed = -ySpeed;
        }
    }

    public boolean collidesWith(GameObject obj) {
        return x + width / 2 > obj.getX() && x - width / 2 < obj.getX() + obj.getWidth()
                && y + height / 2 > obj.getY() && y - height / 2 < obj.getY() + obj.getHeight();
    }

    public void resetPosition(Paddle paddle) {
        x = paddle.getX() + paddle.getWidth() / 2 - width / 2;
        y = paddle.getY() + paddle.getHeight() + height;
        estaQuieto = true;
    }
    
    public void setMovementStrategy(BallMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean estaQuieto() {
        return estaQuieto;
    }

    public void setEstaQuieto(boolean estaQuieto) {
        this.estaQuieto = estaQuieto;
    }

    public int getY() {
        return y;
    }
    
    public void incrementSpeed(float increment) {
        if (xSpeed > 0) {
            xSpeed += increment;
        } else {
            xSpeed -= increment;
        }

        if (ySpeed > 0) {
            ySpeed += increment;
        } else {
            ySpeed -= increment;
        }
    }

	public float getYSpeed() {
		// TODO Auto- method stub
		return 0;
	}

	public void setYSpeed(float ySpeed2) {
		// TODO Auto-generated method stub
		
	}

	public void setXSpeed(float ySpeed2) {
		// TODO Auto-generated method stub
		
	}

	public void setMovementStrategy(AcceleratedMovementStrategy acceleratedMovementStrategy) {
		// TODO Auto-generated method stub
		
	}

}
