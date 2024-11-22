package manager;

import entity.PingBall;
import entity.Paddle;
import entity.Block;

public abstract class AbstractCollisionHandler {
    protected PingBall ball;
    protected Paddle paddle;
    protected Block block;

    public AbstractCollisionHandler(PingBall ball, Paddle paddle, Block block) {
        this.ball = ball;
        this.paddle = paddle;
        this.block = block;
    }

    // Template Method: Define la estructura del proceso
    public final void handleCollision() {
        if (detectCollision()) {
            processCollision();
        }
    }

    // Pasos que las subclases pueden personalizar
    protected abstract boolean detectCollision();

    protected abstract void processCollision();

}
