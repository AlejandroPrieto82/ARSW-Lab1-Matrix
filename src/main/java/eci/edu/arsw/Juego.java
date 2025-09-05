package eci.edu.arsw;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    public static volatile boolean enJuego = true; // bandera global

    public static void main(String[] args) {
        // cargar mapa desde resources
        Mapa mapa = new Mapa("mapa.txt",1,0,10,10);
        char[][] grid = mapa.getGrid();

        int neoX = -1, neoY = -1;
        int telX = -1, telY = -1;
        List<int[]> agentesPos = new ArrayList<>();

        // buscar posiciones en el mapa
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'N') {
                    neoX = i;
                    neoY = j;
                } else if (grid[i][j] == 'T') {
                    telX = i;
                    telY = j;
                } else if (grid[i][j] == 'A') {
                    agentesPos.add(new int[]{i, j});
                }
            }
        }

        if (neoX == -1 || telX == -1) {
            System.out.println("Debe haber un neo y un telefono como minimo");
            return;
        }

        Neo neo = new Neo(neoX, neoY, telX, telY, mapa);

        Thread tNeo = new Thread(neo);
        tNeo.start();

        for (int[] pos : agentesPos) {
            Agente ag = new Agente(pos[0], pos[1], mapa, neo);
            Thread tAg = new Thread(ag);
            tAg.start();
        }
        
        mapa.imprimir();
    }
}
