package manager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import config.GameConfig;
import entity.PingBall;

public class UIManager {
    private BitmapFont font;
    private SpriteBatch batch;
    private ScoreManager scoreManager;
    private PingBall ball;

    public UIManager(SpriteBatch batch, ScoreManager scoreManager, PingBall ball) {
        this.batch = batch;
        this.scoreManager = scoreManager;
        this.ball = ball;

        font = new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.WHITE);
    }


    // Método para dibujar el HUD en pantalla
    public void drawHUD() {
        batch.begin();
        font.draw(batch, "Puntos: " + scoreManager.getPuntaje(), 20, GameConfig.getScreenHeight() - 20);
        font.draw(batch, "Vidas: " + scoreManager.getVidas(), GameConfig.getScreenWidth() - 150, GameConfig.getScreenHeight() - 20);
        batch.end();
    }

    public void drawInstructions() {
        if (ball.estaQuieto()) {
            batch.begin();
            String instruccion1 = "Usa las flechas izquierda y derecha para mover la paleta";
            String instruccion2 = "Presiona ESPACIO para iniciar";
            GlyphLayout layout1 = new GlyphLayout(font, instruccion1);
            GlyphLayout layout2 = new GlyphLayout(font, instruccion2);

            float xPos1 = (GameConfig.getScreenWidth() - layout1.width) / 2;
            float xPos2 = (GameConfig.getScreenWidth() - layout2.width) / 2;

            font.draw(batch, instruccion1, xPos1, GameConfig.getScreenHeight() / 2 + 20);
            font.draw(batch, instruccion2, xPos2, GameConfig.getScreenHeight() / 2 - 20);
            batch.end();
        }
    }

    public void dispose() {
        font.dispose();
    }

}