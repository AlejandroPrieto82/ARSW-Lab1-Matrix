package eci.edu.arsw;

import java.util.*;

public class CaminoMinimo {
    public static List<int[]> bfs(char[][] grid, int startX, int startY, int goalX, int goalY) {
        int filas = grid.length;
        int cols = grid[0].length;

        boolean[][] visitado = new boolean[filas][cols];
        int[][] padreX = new int[filas][cols];
        int[][] padreY = new int[filas][cols];

        Queue<int[]> cola = new LinkedList<>();
        cola.add(new int[] { startX, startY });
        visitado[startX][startY] = true;

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int x = actual[0], y = actual[1];

            if (x == goalX && y == goalY) {
                return reconstruirCamino(padreX, padreY, startX, startY, goalX, goalY);
            }

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && ny >= 0 && nx < filas && ny < cols) {
                    if (!visitado[nx][ny] && grid[nx][ny] != '#') {
                        visitado[nx][ny] = true;
                        padreX[nx][ny] = x;
                        padreY[nx][ny] = y;
                        cola.add(new int[] { nx, ny });
                    }
                }
            }
        }
        return null;
    }

    private static List<int[]> reconstruirCamino(int[][] padreX, int[][] padreY, int sx, int sy, int gx, int gy) {
        List<int[]> camino = new ArrayList<>();
        int x = gx, y = gy;
        while (!(x == sx && y == sy)) {
            camino.add(new int[] { x, y });
            int px = padreX[x][y];
            int py = padreY[x][y];
            x = px;
            y = py;
        }
        Collections.reverse(camino);
        return camino;
    }
}
