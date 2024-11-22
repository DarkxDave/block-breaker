package manager;

import entity.PingBall;
import entity.Paddle;

public class PaddleCollisionHandler extends AbstractCollisionHandler {
    public PaddleCollisionHandler(PingBall ball, Paddle paddle) {
        super(ball, paddle, null);
    }

    @Override
    protected boolean detectCollision() {
        return ball.collidesWith(paddle);
    }

    @Override
    protected void processCollision() {
        ball.checkCollision(paddle);
    }
}
