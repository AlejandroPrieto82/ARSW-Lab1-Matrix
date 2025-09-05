package eci.edu.arsw;

import java.io.*;
import java.util.*;

public class Mapa {
    private char[][] grid;
    private int filas;
    private int columnas;

    public Mapa(String ruta, int nTelefonos, int nBarreras, int tamX, int tamY) {
        try {
            cargarMapa(ruta);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se genera mapa aleatorio");
            generarMapaValido(nTelefonos, nBarreras, tamX, tamY);
        }
    }

    private void cargarMapa(String ruta) throws IOException {
        List<char[]> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea.toCharArray());
            }
        }
        filas = lineas.size();
        columnas = lineas.get(0).length;
        grid = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            grid[i] = lineas.get(i);
        }
    }


    private void generarMapaValido(int nTelefonos, int nBarreras, int tamX, int tamY) {
        boolean valido = false;
        int intentos = 0;

        while (!valido && intentos < 10) {
            generarMapaAleatorio(nTelefonos, nBarreras, tamX, tamY);
            valido = hayCaminoDisponible();
            intentos++;
        }

        if (!valido) {
            System.out.println("No se pudo generar un mapa valido");
            System.exit(1);
        }
    }

    private void generarMapaAleatorio(int nTelefonos, int nBarreras, int tamX, int tamY) {
        filas = tamX;
        columnas = tamY;
        grid = new char[filas][columnas];

        // inicializar vacío
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grid[i][j] = ' ';
            }
        }

        Random rnd = new Random();

        for (int i = 0; i < filas; i++) {
            grid[i][0] = '#';
            grid[i][columnas - 1] = '#';
        }
        for (int j = 0; j < columnas; j++) {
            grid[0][j] = '#';
            grid[filas - 1][j] = '#';
        }

        int neoX = rnd.nextInt(filas - 2) + 1;
        int neoY = rnd.nextInt(columnas - 2) + 1;
        grid[neoX][neoY] = 'N';

        int agX, agY;
        do {
            agX = rnd.nextInt(filas - 2) + 1;
            agY = rnd.nextInt(columnas - 2) + 1;
        } while (grid[agX][agY] != ' ');
        grid[agX][agY] = 'A';

        for (int i = 0; i < nTelefonos; i++) {
            int tx, ty;
            do {
                tx = rnd.nextInt(filas - 2) + 1;
                ty = rnd.nextInt(columnas - 2) + 1;
            } while (grid[tx][ty] != ' ');
            grid[tx][ty] = 'T';
        }

        for (int i = 0; i < nBarreras; i++) {
            int bx, by;
            do {
                bx = rnd.nextInt(filas - 2) + 1;
                by = rnd.nextInt(columnas - 2) + 1;
            } while (grid[bx][by] != ' ');
            grid[bx][by] = '#';
        }
    }

    private boolean hayCaminoDisponible() {
        int neoX = -1, neoY = -1;
        List<int[]> telefonos = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (grid[i][j] == 'N') {
                    neoX = i;
                    neoY = j;
                } else if (grid[i][j] == 'T') {
                    telefonos.add(new int[]{i, j});
                }
            }
        }

        if (neoX == -1 || telefonos.isEmpty()) return false;

        for (int[] tel : telefonos) {
            List<int[]> camino = CaminoMinimo.bfs(grid, neoX, neoY, tel[0], tel[1]);
            if (camino != null && !camino.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public synchronized char[][] getGrid() {
        return grid;
    }

    public synchronized void imprimir() {
        for (char[] fila : grid) {
            System.out.println(fila);
        }
        System.out.println();
    }
}
