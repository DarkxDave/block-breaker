package manager;

public class ScoreManager {
    private int puntaje;
    private int vidas = 3;

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