package core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import config.GameConfig;
import entity.PingBall;
import entity.Paddle;
import manager.RenderManager;
import manager.ScoreManager;
import manager.CollisionManager;
import manager.InputHandler;
import manager.UIManager;
import level.GameLevel;
import entity.Drawable;


import java.util.ArrayList;
import java.util.List;

public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private RenderManager renderManager;
    private PingBall ball;
    private Paddle pad;
    private CollisionManager collisionManager;
    private InputHandler inputHandler;
    private UIManager uiManager;
    private GameLevel gameLevel;
    private List<Drawable> drawables;

    @Override
    public void create() {
        GameConfig config = GameConfig.getInstance();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, config.getScreenWidth(), config.getScreenHeight());
        batch = new SpriteBatch();

        renderManager = new RenderManager();
        ball = new PingBall(config.getScreenWidth() / 2 - config.getBallSize() / 2, 41, config.getBallSize(), config.getBallInitialXSpeed(), config.getBallInitialYSpeed(), true);
        pad = new Paddle(config.getScreenWidth() / 2 - config.getPaddleWidth() / 2, 40, config.getPaddleWidth(), config.getPaddleHeight());
     // Crear el nivel utilizando el Builder
        gameLevel = new GameLevel.Builder()
            .setLevelNumber(1)
            .addRowOfBlocks(700, 70, 26, GameConfig.getInstance().getScreenWidth(), 10) // Fila 1
            .addRowOfBlocks(660, 70, 26, GameConfig.getInstance().getScreenWidth(), 10) // Fila 2
            .addRowOfBlocks(620, 70, 26, GameConfig.getInstance().getScreenWidth(), 10) // Fila 3
            .build();

        // Verificar inicialización de gameLevel
        if (gameLevel == null || gameLevel.getBlocks() == null) {
            throw new NullPointerException("GameLevel or its blocks are not initialized!");
        }

        collisionManager = new CollisionManager(ball, pad, gameLevel.getBlocks());
        inputHandler = new InputHandler(pad, ball);
        uiManager = new UIManager(batch, ScoreManager.getInstance(), ball);

        drawables = new ArrayList<>();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks());
    }


    @Override
    public void render() {
        inputHandler.handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (ball == null || pad == null) {
            throw new NullPointerException("Ball or Paddle is not initialized!");
        }

        if (ball.estaQuieto()) {
            GameConfig config = GameConfig.getInstance();
            ball.setXY(pad.getX() + pad.getWidth() / 2 - config.getBallSize() / 2, pad.getY() + pad.getHeight() + config.getBallSize());
        } else {
            ball.update();
        }

        if (collisionManager == null) {
            throw new NullPointerException("CollisionManager is not initialized!");
        }
        collisionManager.checkCollisions();

        if (ScoreManager.getInstance().isGameOver()) {
            resetGame();
        } else if (gameLevel.isLevelCompleted()) {
            advanceToNextLevel();
        }

        renderManager.render(drawables);
        uiManager.drawHUD();
        uiManager.drawInstructions();
    }


    private void resetGame() {
        ScoreManager.getInstance().reset();
        gameLevel.resetLevel();
        ball.resetPosition(pad);
        drawables.clear();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks());
    }

    private void advanceToNextLevel() {
        gameLevel.advanceToNextLevel();
        ball.resetPosition(pad);
        drawables.clear();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks());
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderManager.dispose();
        uiManager.dispose();
    }
}
