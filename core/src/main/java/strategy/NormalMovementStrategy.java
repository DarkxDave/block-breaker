package strategy;

import entity.PingBall;

public class NormalMovementStrategy implements BallMovementStrategy {
    public void updateBallSpeed(PingBall ball) {
        // El movimiento normal no cambia la velocidad
        ball.setXSpeed(ball.getYSpeed());
        ball.setYSpeed(ball.getYSpeed());
    }
}
