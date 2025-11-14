import java.util.*;

public class DijkstraCompare {

    static int[] dijkstraMatrix(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            int u = -1;

            // Pick smallest unvisited distance
            for (int v = 0; v < V; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }

            visited[u] = true;

            // Relax edges
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist;
    }

    static int[] dijkstraList(List<List<int[]>> graph, int src) {
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dist[src] = 0;
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        int V = 5;

        // Adjacency Matrix
        int[][] matrix = {
                {0, 10, 0, 30, 100},
                {10, 0, 50, 0, 0},
                {0, 50, 0, 20, 10},
                {30, 0, 20, 0, 60},
                {100, 0, 10, 60, 0}
        };

        // Adjacency List
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i < V; i++) list.add(new ArrayList<>());
        list.get(0).add(new int[]{1,10}); list.get(0).add(new int[]{3,30}); list.get(0).add(new int[]{4,100});
        list.get(1).add(new int[]{0,10}); list.get(1).add(new int[]{2,50});
        list.get(2).add(new int[]{1,50}); list.get(2).add(new int[]{3,20}); list.get(2).add(new int[]{4,10});
        list.get(3).add(new int[]{0,30}); list.get(3).add(new int[]{2,20}); list.get(3).add(new int[]{4,60});
        list.get(4).add(new int[]{0,100}); list.get(4).add(new int[]{2,10}); list.get(4).add(new int[]{3,60});

        long start1 = System.nanoTime();
        int[] distM = dijkstraMatrix(matrix, 0);
        long timeMatrix = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        int[] distL = dijkstraList(list, 0);
        long timeList = System.nanoTime() - start2;

        System.out.println("Dijkstra using Matrix: " + Arrays.toString(distM));
        System.out.println("Time (Matrix): " + timeMatrix + " ns");

        System.out.println("Dijkstra using List: " + Arrays.toString(distL));
        System.out.println("Time (List): " + timeList + " ns");
    }
}
