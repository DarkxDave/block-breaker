package manager;

import entity.Block;
import entity.PingBall;
import entity.Paddle;

import java.util.ArrayList;

public class CollisionManager {
    private PingBall ball;
    private Paddle paddle;
    private ArrayList<Block> blocks;

    public CollisionManager(PingBall ball, Paddle paddle, ArrayList<Block> blocks) {
        this.ball = ball;
        this.paddle = paddle;
        this.blocks = blocks;
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
                ball.checkCollision(block); // Ajusta la velocidad de la bola
                block.destroy(); // Destruye el bloque
                ScoreManager.getInstance().incrementarPuntaje(); // Incrementar el puntaje usando ScoreManager
            }
        }

        // Verificar si la bola ha tocado el fondo de la pantalla
        if (ball.getY() < 0) {
            handleBallOutOfBounds();
        }
    }

    private void handleBallOutOfBounds() {
        // Decrementar vidas y verificar si el juego ha terminado
        ScoreManager scoreManager = ScoreManager.getInstance();
        scoreManager.decrementarVidas();

        if (scoreManager.isGameOver()) {
            // Lógica de reinicio del juego o fin del juego
            System.out.println("Game Over! Reiniciando el juego...");
            scoreManager.reset(); // Reiniciar el puntaje y las vidas
            resetBlocks(); // Reiniciar los bloques
        }

        // Resetea la posición de la bola
        ball.resetPosition(paddle);
    }

    private void resetBlocks() {
        for (Block block : blocks) {
            block.reset(); // Implementa un método reset() en Block para restaurar el estado del bloque
        }
    }
}
