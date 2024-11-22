package entity;

import config.GameConfig;

public class BasicBlockFactory implements BlockFactory {
    private final GameConfig config = GameConfig.getInstance();

    @Override
    public Block createBlock(int x, int y) {
        return new Block(x, y, config.getBlockWidth(), config.getBlockHeight());
    }
}
