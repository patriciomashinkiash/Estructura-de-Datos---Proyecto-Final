package model;

import java.util.*;

public class AlgoritmosBusqueda {
    // BFS - Busqueda en anchura
    public static List<Cell> bfsSearch(Maze maze, Cell inicio, Cell fin) {
        Queue<List<Cell>> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();

        cola.offer(Collections.singletonList(inicio));
        visitados.add(inicio.row + "," + inicio.col);

        while (!cola.isEmpty()) {
            List<Cell> path = cola.poll();
            Cell actual = path.get(path.size() - 1);

            if (actual.row == fin.row && actual.col == fin.col) {
                return path;
            }

            int[][] direcciones = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            for (int[] dir : direcciones) {
                int newRow = actual.row + dir[0];
                int newCol = actual.col + dir[1];
                String pos = newRow + "," + newCol;

                if (maze.getGrid().length > newRow && newRow >= 0 &&
                    maze.getGrid()[0].length > newCol && newCol >= 0 &&
                    !maze.isBlocked(newRow, newCol) && !visitados.contains(pos)) {
                    List<Cell> newPath = new ArrayList<>(path);
                    newPath.add(new Cell(newRow, newCol, false));
                    cola.offer(newPath);
                    visitados.add(pos);
                }
            }
        }
        return Collections.emptyList();
    }

    // DFS - Busqueda en profundidad
    public static List<Cell> dfsSearch(Maze maze, Cell inicio, Cell fin) {
        Stack<List<Cell>> stack = new Stack<>();
        Set<String> visitados = new HashSet<>();

        stack.push(Collections.singletonList(inicio));
        visitados.add(inicio.row + "," + inicio.col);

        while (!stack.isEmpty()) {
            List<Cell> path = stack.pop();
            Cell actual = path.get(path.size() - 1);

            if (actual.row == fin.row && actual.col == fin.col) {
                return path;
            }

            int[][] direcciones = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            for (int[] dir : direcciones) {
                int newRow = actual.row + dir[0];
                int newCol = actual.col + dir[1];
                String pos = newRow + "," + newCol;

                if (maze.getGrid().length > newRow && newRow >= 0 &&
                    maze.getGrid()[0].length > newCol && newCol >= 0 &&
                    !maze.isBlocked(newRow, newCol) && !visitados.contains(pos)) {
                    List<Cell> newPath = new ArrayList<>(path);
                    newPath.add(new Cell(newRow, newCol, false));
                    stack.push(newPath);
                    visitados.add(pos);
                }
            }
        }
        return Collections.emptyList();
    }

    // Busqueda recursiva
    public static List<Cell> recursiveSearch(Maze maze, Cell inicio, Cell fin, Set<String> visitados) {
        if (inicio.row == fin.row && inicio.col == fin.col) {
            return List.of(inicio);
        }

        visitados.add(inicio.row + "," + inicio.col);

        int[][] direcciones = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] direccion : direcciones) {
            int newRow = inicio.row + direccion[0];
            int newCol = inicio.col + direccion[1];
            String pos = newRow + "," + newCol;

            if (maze.getGrid().length > newRow && newRow >= 0 &&
                maze.getGrid()[0].length > newCol && newCol >= 0 &&
                !maze.isBlocked(newRow, newCol) && !visitados.contains(pos)) {
                List<Cell> path = recursiveSearch(maze, new Cell(newRow, newCol, false), fin, visitados);
                if (!path.isEmpty()) {
                    List<Cell> fullPath = new ArrayList<>();
                    fullPath.add(inicio);
                    fullPath.addAll(path);
                    return fullPath;
                }
            }
        }
        return Collections.emptyList();
    }

    // Busqueda con cache (programación dinamica)
    public static List<Cell> cacheSearch(Maze maze, Cell inicio, Cell fin, Map<String, List<Cell>> cache) {
        String key = inicio.row + "," + inicio.col;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (inicio.row == fin.row && inicio.col == fin.col) {
            return List.of(inicio);
        }

        cache.put(key, Collections.emptyList());

        int[][] direcciones = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : direcciones) {
            int newRow = inicio.row + dir[0];
            int newCol = inicio.col + dir[1];

            if (maze.getGrid().length > newRow && newRow >= 0 &&
                maze.getGrid()[0].length > newCol && newCol >= 0 &&
                !maze.isBlocked(newRow, newCol)) {
                List<Cell> path = cacheSearch(maze, new Cell(newRow, newCol, false), fin, cache);
                if (!path.isEmpty()) {
                    List<Cell> fullPath = new ArrayList<>();
                    fullPath.add(inicio);
                    fullPath.addAll(path);
                    cache.put(key, fullPath);
                    return fullPath;
                }
            }
        }
        return Collections.emptyList();
    }
}
