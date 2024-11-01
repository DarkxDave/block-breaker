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
    private CollisionManager collisionManager;
    private OrthographicCamera  camera;
    private SpriteBatch batch;
    private RenderManager renderManager;
    private PingBall ball;
    private Paddle pad;
    private ScoreManager scoreManager;
    private InputHandler inputHandler;
    private UIManager uiManager;
    private GameLevel gameLevel;
    private List<Drawable> drawables;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConfig.getScreenWidth(), GameConfig.getScreenHeight());
        batch = new SpriteBatch();
        renderManager = new RenderManager();

        ball = new PingBall(GameConfig.getScreenWidth() / 2 - GameConfig.getBallSize() / 2, 41, GameConfig.getBallSize(), GameConfig.getBallInitialXSpeed(), GameConfig.getBallInitialYSpeed(), true);
        pad = new Paddle(GameConfig.getScreenWidth() / 2 - GameConfig.getPaddleWidth() / 2, 40, GameConfig.getPaddleWidth(), GameConfig.getPaddleHeight());

        scoreManager = new ScoreManager();
        gameLevel = new GameLevel(1);

        // Inicialización de CollisionManager con los objetos necesarios
        collisionManager = new CollisionManager(ball, pad, gameLevel.getBlocks(), scoreManager);
        
        inputHandler = new InputHandler(pad, ball);
        uiManager = new UIManager(batch, scoreManager, ball);

        drawables = new ArrayList<>();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks());
    }

    @Override
    public void render() {
        inputHandler.handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar la posición de la bola y manejar su movimiento si no está quieto
        if (ball.estaQuieto()) {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - GameConfig.getBallSize() / 2, pad.getY() + pad.getHeight() + GameConfig.getBallSize());
        } else {
            ball.update();
        }

        // Procesar todas las colisiones
        collisionManager.checkCollisions();

        // Verificar si el juego ha terminado o si se completó el nivel
        if (scoreManager.isGameOver()) {
            resetGame();
        } else if (gameLevel.isLevelCompleted()) {
            advanceToNextLevel();
        }

        // Renderizar todos los objetos y la interfaz
        renderManager.render(drawables);
        uiManager.drawHUD();
        uiManager.drawInstructions();
    }

    private void resetGame() {
        scoreManager.reset();
        gameLevel.resetLevel();
        ball.resetPosition(pad);
        drawables.clear();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks()); // Añadir bloques después de reiniciar el nivel
    }

    private void advanceToNextLevel() {
        gameLevel.advanceToNextLevel();
        ball.resetPosition(pad);
        drawables.clear();
        drawables.add(pad);
        drawables.add(ball);
        drawables.addAll(gameLevel.getBlocks()); // Añadir nuevos bloques al avanzar de nivel
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        renderManager.dispose();
        uiManager.dispose();
    }
}
