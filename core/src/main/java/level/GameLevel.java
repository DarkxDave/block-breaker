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

    private void createBlocks() {
        blocks.clear();

        int blockWidth = GameConfig.getBlockWidth();
        int blockHeight = GameConfig.getBlockHeight();
        int rows = GameConfig.getBlockRows() + levelNumber - 1;
        int y = GameConfig.getScreenHeight();

        for (int i = 0; i < rows; i++) {
            y -= blockHeight + GameConfig.getBlockSpacingY();
            for (int x = GameConfig.getBlockSpacingX(); x < GameConfig.getScreenWidth(); x += blockWidth + GameConfig.getBlockSpacingX()) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    public boolean isLevelCompleted() {
        for (Block block : blocks) {
            if (!block.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void resetLevel() {
        createBlocks();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void advanceToNextLevel() {
        levelNumber++;
        createBlocks();
    }
}
