package eci.edu.arsw;

import java.util.List;

/**
 * Representa a Neo, quien debe llegar al teléfono evitando a los agentes.
 * Se ejecuta en un hilo independiente.
 */
public class Neo implements Runnable {
    private int x, y;
    private int targetX, targetY;
    private Mapa mapa;

    /**
     * Constructor de Neo.
     *
     * @param x posición inicial en X
     * @param y posición inicial en Y
     * @param targetX posición X del teléfono
     * @param targetY posición Y del teléfono
     * @param mapa referencia al mapa
     */
    public Neo(int x, int y, int targetX, int targetY, Mapa mapa) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.mapa = mapa;
    }

    /**
     * Lógica principal de movimiento de Neo.
     * Busca el camino mínimo al teléfono y avanza paso a paso.
     */
    @Override
    public void run() {
        try {
            while (Juego.enJuego) {
                List<int[]> camino = CaminoMinimo.bfs(mapa.getGrid(), x, y, targetX, targetY);

                if (camino == null || camino.isEmpty()) {
                    System.out.println("Pierde Neo");
                    Juego.enJuego = false;
                    return;
                }

                int[] siguiente = camino.get(0);
                mover(siguiente[0], siguiente[1]);

                if (x == targetX && y == targetY) {
                    System.out.println("Gana Neo");
                    Juego.enJuego = false;
                    return;
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Neo interrumpido");
        }
    }

    /**
     * Mueve a Neo en el mapa a una nueva posición.
     *
     * @param nx nueva posición en X
     * @param ny nueva posición en Y
     */
    private synchronized void mover(int nx, int ny) {
        char[][] grid = mapa.getGrid();
        grid[x][y] = ' ';
        x = nx;
        y = ny;
        grid[x][y] = 'N';
        mapa.imprimir();
    }

    /** @return posición X actual de Neo */
    public int getX() { return x; }

    /** @return posición Y actual de Neo */
    public int getY() { return y; }
}
