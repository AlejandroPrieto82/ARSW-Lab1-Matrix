package eci.edu.arsw;

import java.util.List;

public class Agente implements Runnable {
    private int x, y;
    private Mapa mapa;
    private Neo neo; // referencia para perseguirlo

    public Agente(int x, int y, Mapa mapa, Neo neo) {
        this.x = x;
        this.y = y;
        this.mapa = mapa;
        this.neo = neo;
    }

    @Override
public void run() {
    try {
        while (Juego.enJuego) {
            // calcular camino hasta Neo
            List<int[]> camino = CaminoMinimo.bfs(mapa.getGrid(), x, y, neo.getX(), neo.getY());

            if (camino != null && !camino.isEmpty()) {
                int[] siguiente = camino.get(0);
                mover(siguiente[0], siguiente[1]);

                // verificar si atrapó a Neo
                if (x == neo.getX() && y == neo.getY()) {
                    System.out.println("Agente atrapa a Neo, pierdes");
                    Juego.enJuego = false;
                    return;
                }
            }

            Thread.sleep(1000); // agentes se mueven un poco más lento
        }
    } catch (InterruptedException e) {
        System.out.println("Agente interrumpido");
    }
}


    private synchronized void mover(int nx, int ny) {
        char[][] grid = mapa.getGrid();
        grid[x][y] = ' '; // borrar posición anterior
        x = nx;
        y = ny;
        grid[x][y] = 'A'; // colocar agente en nueva posición
        mapa.imprimir();
    }
}
