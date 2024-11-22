package manager;

import entity.PingBall;
import entity.Block;

public class BlockCollisionHandler extends AbstractCollisionHandler {
    public BlockCollisionHandler(PingBall ball, Block block) {
        super(ball, null, block);
    }

    @Override
    protected boolean detectCollision() {
        return !block.isDestroyed() && ball.collidesWith(block);
    }

    @Override
    protected void processCollision() {
        ball.checkCollision(block);
        block.destroy();
    }
}
