package config;

public class GameConfig {
    // Instancia única del Singleton
    private static GameConfig instance;

    // Configuraciones de pantalla
    private final int SCREEN_WIDTH = 1200;
    private final int SCREEN_HEIGHT = 800;

    // Configuraciones de la bola
    private final int BALL_SIZE = 10;
    private final int BALL_INITIAL_X_SPEED = 5;
    private final int BALL_INITIAL_Y_SPEED = 7;

    // Configuraciones de la paleta
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_SPEED = 10;

    // Configuraciones de bloques
    private final int BLOCK_WIDTH = 70;
    private final int BLOCK_HEIGHT = 26;
    private final int BLOCK_ROWS = 3; // Número de filas de bloques base
    private final int BLOCK_SPACING_X = 10;
    private final int BLOCK_SPACING_Y = 10;

    // Configuración del juego
    private final int INITIAL_LIVES = 3;

    // Constructor privado para evitar instanciación
    private GameConfig() {}

    // Método para obtener la instancia única
    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    // Métodos getters para configuraciones
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public int getBallSize() {
        return BALL_SIZE;
    }

    public int getBallInitialXSpeed() {
        return BALL_INITIAL_X_SPEED;
    }

    public int getBallInitialYSpeed() {
        return BALL_INITIAL_Y_SPEED;
    }

    public int getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    public int getPaddleHeight() {
        return PADDLE_HEIGHT;
    }

    public int getPaddleSpeed() {
        return PADDLE_SPEED;
    }

    public int getBlockWidth() {
        return BLOCK_WIDTH;
    }

    public int getBlockHeight() {
        return BLOCK_HEIGHT;
    }

    public int getBlockRows() {
        return BLOCK_ROWS;
    }

    public int getBlockSpacingX() {
        return BLOCK_SPACING_X;
    }

    public int getBlockSpacingY() {
        return BLOCK_SPACING_Y;
    }

    public int getInitialLives() {
        return INITIAL_LIVES;
    }
}
