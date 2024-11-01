package manager;

import entity.Block;
import entity.PingBall;
import entity.Paddle;



import java.util.ArrayList;

public class CollisionManager {
    private PingBall ball;
    private Paddle paddle;
    private ArrayList<Block> blocks;
    private ScoreManager scoreManager;

    
    public CollisionManager(PingBall ball, Paddle paddle, ArrayList<Block> blocks, ScoreManager scoreManager) {
        this.ball = ball;
        this.paddle = paddle;
        this.blocks = blocks;
        this.scoreManager = scoreManager;
    }

    public void checkCollisions() {
        // Colisión de la bola con la paleta
        if (ball.collidesWith(paddle)) {
            ball.setEstaQuieto(false);
            ball.checkCollision(paddle); // Ajusta el movimiento de la bola al rebotar en la paleta
        }

        // Colisión de la bola con los bloques
        for (Block block : blocks) {
            if (!block.isDestroyed() && ball.collidesWith(block)) {
                ball.checkCollision(block);
                scoreManager.incrementarPuntaje(); // Incrementar el puntaje usando ScoreManager
            }
        }

        // Verificar si la bola ha tocado el fondo de la pantalla
        if (ball.getY() < 0) {
            handleBallOutOfBounds();
        }
    }

    private void handleBallOutOfBounds() {
        scoreManager.decrementarVidas();
        ball.resetPosition(paddle);
    }
}