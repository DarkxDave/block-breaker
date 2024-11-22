package level;

import entity.Block;
import config.GameConfig;

import java.util.ArrayList;

public class GameLevel {
    private int levelNumber;
    private ArrayList<Block> blocks;

    public GameLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        this.blocks = new ArrayList<>();
        createBlocks();
    }

    // Método para crear los bloques en función del número de nivel
    private void createBlocks() {
        blocks.clear();

        int blockWidth = GameConfig.getInstance().getBlockWidth();
        int blockHeight = GameConfig.getInstance().getBlockHeight();
        int rows = GameConfig.getInstance().getBlockRows() + levelNumber - 1; // Aumenta las filas con el nivel
        int y = GameConfig.getInstance().getScreenHeight();

        for (int i = 0; i < rows; i++) {
            y -= blockHeight + GameConfig.getInstance().getBlockSpacingY();
            for (int x = GameConfig.getInstance().getBlockSpacingX(); x < GameConfig.getInstance().getScreenWidth(); x += blockWidth + GameConfig.getInstance().getBlockSpacingX()) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    // Método para verificar si todos los bloques han sido destruidos
    public boolean isLevelCompleted() {
        for (Block block : blocks) {
            if (!block.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    // Método para reiniciar los bloques
    public void resetLevel() {
        createBlocks();
    }

    // Getter para los bloques
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // Getter para el número de nivel
    public int getLevelNumber() {
        return levelNumber;
    }

    // Avanza al siguiente nivel
    public void advanceToNextLevel() {
        levelNumber++;
        createBlocks();
    }
}
