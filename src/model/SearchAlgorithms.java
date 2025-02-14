package model;

import java.util.*;

public class SearchAlgorithms {
    // BFS - Búsqueda en anchura
    public static List<Cell> bfs(Maze maze, Cell start, Cell end) {
        Queue<List<Cell>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(Collections.singletonList(start));
        visited.add(start.row + "," + start.col);

        while (!queue.isEmpty()) {
            List<Cell> path = queue.poll();
            Cell current = path.get(path.size() - 1);

            if (current.row == end.row && current.col == end.col) {
                return path;
            }

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                String pos = newRow + "," + newCol;

                if (maze.getGrid().length > newRow && newRow >= 0 &&
                    maze.getGrid()[0].length > newCol && newCol >= 0 &&
                    !maze.isBlocked(newRow, newCol) && !visited.contains(pos)) {
                    List<Cell> newPath = new ArrayList<>(path);
                    newPath.add(new Cell(newRow, newCol, false));
                    queue.offer(newPath);
                    visited.add(pos);
                }
            }
        }
        return Collections.emptyList();
    }

    // DFS - Búsqueda en profundidad
    public static List<Cell> dfs(Maze maze, Cell start, Cell end) {
        Stack<List<Cell>> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(Collections.singletonList(start));
        visited.add(start.row + "," + start.col);

        while (!stack.isEmpty()) {
            List<Cell> path = stack.pop();
            Cell current = path.get(path.size() - 1);

            if (current.row == end.row && current.col == end.col) {
                return path;
            }

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                String pos = newRow + "," + newCol;

                if (maze.getGrid().length > newRow && newRow >= 0 &&
                    maze.getGrid()[0].length > newCol && newCol >= 0 &&
                    !maze.isBlocked(newRow, newCol) && !visited.contains(pos)) {
                    List<Cell> newPath = new ArrayList<>(path);
                    newPath.add(new Cell(newRow, newCol, false));
                    stack.push(newPath);
                    visited.add(pos);
                }
            }
        }
        return Collections.emptyList();
    }

    // Búsqueda recursiva
    public static List<Cell> recursiveSearch(Maze maze, Cell start, Cell end, Set<String> visited) {
        if (start.row == end.row && start.col == end.col) {
            return List.of(start);
        }

        visited.add(start.row + "," + start.col);

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : directions) {
            int newRow = start.row + dir[0];
            int newCol = start.col + dir[1];
            String pos = newRow + "," + newCol;

            if (maze.getGrid().length > newRow && newRow >= 0 &&
                maze.getGrid()[0].length > newCol && newCol >= 0 &&
                !maze.isBlocked(newRow, newCol) && !visited.contains(pos)) {
                List<Cell> path = recursiveSearch(maze, new Cell(newRow, newCol, false), end, visited);
                if (!path.isEmpty()) {
                    List<Cell> fullPath = new ArrayList<>();
                    fullPath.add(start);
                    fullPath.addAll(path);
                    return fullPath;
                }
            }
        }
        return Collections.emptyList();
    }

    // Búsqueda con cache (programación dinámica)
    public static List<Cell> cachedSearch(Maze maze, Cell start, Cell end, Map<String, List<Cell>> cache) {
        String key = start.row + "," + start.col;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (start.row == end.row && start.col == end.col) {
            return List.of(start);
        }

        cache.put(key, Collections.emptyList());

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : directions) {
            int newRow = start.row + dir[0];
            int newCol = start.col + dir[1];

            if (maze.getGrid().length > newRow && newRow >= 0 &&
                maze.getGrid()[0].length > newCol && newCol >= 0 &&
                !maze.isBlocked(newRow, newCol)) {
                List<Cell> path = cachedSearch(maze, new Cell(newRow, newCol, false), end, cache);
                if (!path.isEmpty()) {
                    List<Cell> fullPath = new ArrayList<>();
                    fullPath.add(start);
                    fullPath.addAll(path);
                    cache.put(key, fullPath);
                    return fullPath;
                }
            }
        }
        return Collections.emptyList();
    }
}
