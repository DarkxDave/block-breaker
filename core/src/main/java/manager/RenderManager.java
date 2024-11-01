package manager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entity.Drawable;

import java.util.List;

public class RenderManager {
    private ShapeRenderer shapeRenderer;

    public RenderManager() {
        shapeRenderer = new ShapeRenderer();
    }

    public void render(List<Drawable> drawables) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Drawable drawable : drawables) {
            drawable.draw(shapeRenderer); // Llama al método draw() de cada objeto Drawable
        }
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
