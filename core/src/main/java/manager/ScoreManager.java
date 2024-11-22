package manager;

public class ScoreManager {
    private static ScoreManager instance; // Única instancia de la clase
    private int puntaje;
    private int vidas = 3;

    private ScoreManager() {
        // Constructor privado para evitar instancias externas
    }

    // Método estático para obtener la única instancia de ScoreManager
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void incrementarPuntaje() {
        puntaje++;
    }

    public void decrementarVidas() {
        vidas--;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean isGameOver() {
        return vidas <= 0;
    }

    public void reset() {
        puntaje = 0;
        vidas = 3;
    }
}
