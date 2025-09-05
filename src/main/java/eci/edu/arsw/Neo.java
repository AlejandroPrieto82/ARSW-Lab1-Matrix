package eci.edu.arsw;

import java.util.List;

public class Neo implements Runnable {
    private int x, y;
    private int targetX, targetY; // coordenadas del teléfono
    private Mapa mapa;

    public Neo(int x, int y, int targetX, int targetY, Mapa mapa) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.mapa = mapa;
    }

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

            Thread.sleep(1000); // pausa entre movimientos
        }
    } catch (InterruptedException e) {
        System.out.println("Neo interrumpido");
    }
}


    private synchronized void mover(int nx, int ny) {
        char[][] grid = mapa.getGrid();
        grid[x][y] = ' '; // borrar posición anterior
        x = nx; 
        y = ny;
        grid[x][y] = 'N'; // colocar Neo en nueva posición
        mapa.imprimir();
    } 

    // Para que agentes sepan dónde está Neo
    public int getX() { return x; }
    public int getY() { return y; }
}

