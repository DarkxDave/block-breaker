package core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConfig;
import entity.PingBall;
import entity.Paddle;
import manager.ScoreManager;
import manager.CollisionManager;
import manager.InputHandler;
import manager.UIManager;
import level.GameLevel;
import entity.Block;

import java.util.ArrayList;
import java.util.Iterator;

public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private PingBall ball;
    private Paddle pad;
    private ScoreManager scoreManager;
    private InputHandler inputHandler;
    private UIManager uiManager;
    private GameLevel gameLevel;

    @Override
    public void create() {
        // Configuración de la cámara y del batch para gráficos
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConfig.getScreenWidth(), GameConfig.getScreenHeight());
        batch = new SpriteBatch();

        // Configuración de la bola, paleta y otros elementos
        shape = new ShapeRenderer();
        ball = new PingBall(GameConfig.getScreenWidth() / 2 - GameConfig.getBallSize() / 2, 41, GameConfig.getBallSize(), GameConfig.getBallInitialXSpeed(), GameConfig.getBallInitialYSpeed(), true);
        pad = new Paddle(GameConfig.getScreenWidth() / 2 - GameConfig.getPaddleWidth() / 2, 40, GameConfig.getPaddleWidth(), GameConfig.getPaddleHeight());

        // Inicialización de los gestores y componentes
        scoreManager = new ScoreManager();
        new CollisionManager(ball, pad, new ArrayList<>());
        inputHandler = new InputHandler(pad, ball);
        uiManager = new UIManager(batch, scoreManager, ball);
        gameLevel = new GameLevel(1); // Iniciar en el nivel 1
    }

    @Override
    public void render() {
        // Procesar la entrada del usuario
        inputHandler.handleInput();

        // Limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Iniciar el dibujo de los elementos en pantalla
        shape.begin(ShapeRenderer.ShapeType.Filled);

        // Dibujar la paleta
        pad.draw(shape);

        // Colocar la bola sobre la paleta al inicio
        if (ball.estaQuieto()) {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - GameConfig.getBallSize() / 2, pad.getY() + pad.getHeight() + GameConfig.getBallSize());
        } else {
            ball.update();
        }

        // Verificar si la bola ha caído por debajo de la pantalla
        if (ball.getY() < 0) {
            scoreManager.decrementarVidas();
            ball.resetPosition(pad); // Reiniciar la posición de la bola
        }

        // Verificar si el juego ha terminado
        if (scoreManager.isGameOver()) {
            scoreManager.reset();
            gameLevel.resetLevel(); // Reiniciar el nivel actual
            ball.resetPosition(pad);
        }

        // Verificar si el nivel actual se ha completado
        if (gameLevel.isLevelCompleted()) {
            gameLevel.advanceToNextLevel(); // Avanzar al siguiente nivel
            ball.resetPosition(pad);
        }

        // Dibujar los bloques del nivel actual y verificar colisiones con la bola
        Iterator<Block> iterator = gameLevel.getBlocks().iterator();
        while (iterator.hasNext()) {
            Block b = iterator.next();
            b.draw(shape);
            ball.checkCollision(b);
            if (b.isDestroyed()) {
                iterator.remove();
                scoreManager.incrementarPuntaje();
            }
        }

        // Verificar colisión entre la bola y la paleta
        ball.checkCollision(pad);

        // Dibujar la bola
        ball.draw(shape);

        // Finalizar el dibujo de los elementos en pantalla
        shape.end();

        // Dibujar el HUD e instrucciones usando UIManager
        uiManager.drawHUD();
        uiManager.drawInstructions();
    }

    @Override
    public void dispose() {
        // Liberar recursos
        batch.dispose();
        shape.dispose();
        uiManager.dispose();
    }
}
