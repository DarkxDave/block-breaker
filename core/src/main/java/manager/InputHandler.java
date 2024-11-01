package manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import entity.Paddle;
import entity.PingBall;

public class InputHandler {
    private Paddle paddle;
    private PingBall ball;
    private boolean isGameStarted;

    public InputHandler(Paddle paddle, PingBall ball) {
        this.paddle = paddle;
        this.ball = ball;
        this.isGameStarted = false;
    }

    public void handleInput() {
        // Movimiento de la paleta hacia la izquierda
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            paddle.moveLeft();
        }

        // Movimiento de la paleta hacia la derecha
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            paddle.moveRight();
        }

        // Iniciar el juego cuando se presiona ESPACIO
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && ball.estaQuieto()) {
            ball.setEstaQuieto(false);
            isGameStarted = true;
        }
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }
}
