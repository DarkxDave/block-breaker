package manager;

import entity.Block;
import entity.PingBall;
import entity.Paddle;

import java.util.ArrayList;

public class CollisionManager {
    private PingBall ball;
    private ArrayList<Block> blocks;

    public CollisionManager(PingBall ball, Paddle paddle, ArrayList<Block> blocks) {
        this.ball = ball;
        this.blocks = blocks;
    }

    public void checkCollision(Block block) {
        ball.checkCollision(block);
        if (block.isDestroyed()) {
            blocks.remove(block);
        }
    }
}