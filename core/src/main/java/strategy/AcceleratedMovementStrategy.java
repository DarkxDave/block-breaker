package strategy;

import entity.PingBall;

public class AcceleratedMovementStrategy implements BallMovementStrategy {
    private static final float SPEED_INCREMENT = 0.1f;

    public void updateBallSpeed(PingBall ball) {
        // Incrementar la velocidad de la bola en cada rebote
        ball.setYSpeed(ball.getYSpeed() + SPEED_INCREMENT);
        ball.setYSpeed(ball.getYSpeed() + SPEED_INCREMENT);
    }
}
