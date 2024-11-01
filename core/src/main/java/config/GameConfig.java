package config;

public class GameConfig {
    // Configuraciones de pantalla
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;

    // Configuraciones de la bola
    private static final int BALL_SIZE = 10;
    private static final int BALL_INITIAL_X_SPEED = 5;
    private static final int BALL_INITIAL_Y_SPEED = 7;

    // Configuraciones de la paleta
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_SPEED = 10;

    // Configuraciones de bloques
    private static final int BLOCK_WIDTH = 70;
    private static final int BLOCK_HEIGHT = 26;
    private static final int BLOCK_ROWS = 3; // Número de filas de bloques base
    private static final int BLOCK_SPACING_X = 10;
    private static final int BLOCK_SPACING_Y = 10;

    // Configuración del juego
    private static final int INITIAL_LIVES = 3;

    // Getters para cada configuración
    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getBallSize() {
        return BALL_SIZE;
    }

    public static int getBallInitialXSpeed() {
        return BALL_INITIAL_X_SPEED;
    }

    public static int getBallInitialYSpeed() {
        return BALL_INITIAL_Y_SPEED;
    }

    public static int getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    public static int getPaddleHeight() {
        return PADDLE_HEIGHT;
    }

    public static int getPaddleSpeed() {
        return PADDLE_SPEED;
    }

    public static int getBlockWidth() {
        return BLOCK_WIDTH;
    }

    public static int getBlockHeight() {
        return BLOCK_HEIGHT;
    }

    public static int getBlockRows() {
        return BLOCK_ROWS;
    }

    public static int getBlockSpacingX() {
        return BLOCK_SPACING_X;
    }

    public static int getBlockSpacingY() {
        return BLOCK_SPACING_Y;
    }

    public static int getInitialLives() {
        return INITIAL_LIVES;
    }
}
