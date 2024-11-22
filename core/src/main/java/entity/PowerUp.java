package entity;

public class PowerUp {
    private final float speedBoost;

    public PowerUp(float speedBoost) {
        this.speedBoost = speedBoost;
    }

    // Aplica el efecto del PowerUp a la bola
    public void activate(PingBall ball) {
        ball.incrementSpeed(speedBoost); // Aumentar la velocidad de la bola
    }
}
