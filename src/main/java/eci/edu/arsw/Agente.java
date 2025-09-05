package eci.edu.arsw;

import java.util.List;

/**
 * Representa a un agente que persigue a Neo.
 * Se ejecuta en un hilo independiente.
 */
public class Agente implements Runnable {
    private int x, y;
    private Mapa mapa;
    private Neo neo;

    /**
     * Constructor de Agente.
     *
     * @param x posición inicial en X
     * @param y posición inicial en Y
     * @param mapa referencia al mapa
     * @param neo referencia a Neo
     */
    public Agente(int x, int y, Mapa mapa, Neo neo) {
        this.x = x;
        this.y = y;
        this.mapa = mapa;
        this.neo = neo;
    }

    /**
     * Lógica principal del agente.
     * Busca a Neo y avanza hacia su posición.
     */
    @Override
    public void run() {
        try {
            while (Juego.enJuego) {
                List<int[]> camino = CaminoMinimo.bfs(mapa.getGrid(), x, y, neo.getX(), neo.getY());

                if (camino != null && !camino.isEmpty()) {
                    int[] siguiente = camino.get(0);
                    mover(siguiente[0], siguiente[1]);

                    if (x == neo.getX() && y == neo.getY()) {
                        System.out.println("Agente atrapa a Neo, pierdes");
                        Juego.enJuego = false;
                        return;
                    }
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Agente interrumpido");
        }
    }

    /**
     * Mueve al agente en el mapa a una nueva posición.
     *
     * @param nx nueva posición en X
     * @param ny nueva posición en Y
     */
    private synchronized void mover(int nx, int ny) {
        char[][] grid = mapa.getGrid();
        grid[x][y] = ' ';
        x = nx;
        y = ny;
        grid[x][y] = 'A';
        mapa.imprimir();
    }
}
