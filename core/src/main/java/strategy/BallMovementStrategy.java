package strategy;

import entity.PingBall;

public interface BallMovementStrategy {
    void updateBallSpeed(PingBall ball);
}
