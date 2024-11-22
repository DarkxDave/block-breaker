package level;

import java.util.ArrayList;

import config.GameConfig;
import entity.Block;

public class GameLevel {
    private int levelNumber;
    private ArrayList<Block> blocks;

    private GameLevel(Builder builder) {
        this.levelNumber = builder.levelNumber;
        this.blocks = builder.blocks;
    }

    public void resetLevel() {
        blocks.clear();
        new Builder()
            .setLevelNumber(levelNumber)
            .addRowOfBlocks(700, 70, 26, GameConfig.getInstance().getScreenWidth(), 10)
            .addRowOfBlocks(660, 70, 26, GameConfig.getInstance().getScreenWidth(), 10)
            .addRowOfBlocks(620, 70, 26, GameConfig.getInstance().getScreenWidth(), 10)
            .buildTo(this);
    }

    public void advanceToNextLevel() {
        levelNumber++;
        resetLevel();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public boolean isLevelCompleted() {
        for (Block block : blocks) {
            if (!block.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {
        private int levelNumber;
        private ArrayList<Block> blocks = new ArrayList<>();

        public Builder setLevelNumber(int levelNumber) {
            this.levelNumber = levelNumber;
            return this;
        }

        public Builder addRowOfBlocks(int startY, int blockWidth, int blockHeight, int screenWidth, int spacing) {
            int y = startY;
            for (int x = spacing; x < screenWidth; x += blockWidth + spacing) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
            return this;
        }

        public GameLevel build() {
            return new GameLevel(this);
        }

        // Método para reconstruir un GameLevel existente
        public void buildTo(GameLevel existingLevel) {
            existingLevel.levelNumber = this.levelNumber;
            existingLevel.blocks.clear();
            existingLevel.blocks.addAll(this.blocks);
        }
    }
}
