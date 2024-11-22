package entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block extends GameObject implements Drawable {
    private boolean destroyed;
    private Color color;
    private String type; // Tipo del bloque, puede ser "normal", "indestructible", "especial", etc.

    public Block(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.destroyed = false;
        this.color = Color.RED;
        this.type = "normal"; // Tipo por defecto
    }

    // Sobrecarga del constructor para bloques con tipo específico
    public Block(int x, int y, int width, int height, String type, Color color) {
        super(x, y, width, height);
        this.destroyed = false;
        this.color = color != null ? color : Color.RED; // Color por defecto
        this.type = type;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        if (!destroyed) {
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
        }
    }

    public void destroy() {
        // Solo permite destruir bloques normales o con características específicas
        if (!"indestructible".equals(type)) {
            destroyed = true;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void reset() {
        this.destroyed = false;
    }

    // Getter y Setter para tipo
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
