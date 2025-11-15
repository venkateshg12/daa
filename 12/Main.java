public class Main {

    static int N = 4;
    static int[][] graph = {
        { 0, 10, 15, 20 },
        { 10, 0, 35, 25 },
        { 15, 35, 0, 30 },
        { 20, 25, 30, 0 }
    };

    static boolean[] visited = new boolean[N];
    static int bestCost = Integer.MAX_VALUE;

    static void tsp(int city, int count, int cost) {

        if (count == N && graph[city][0] > 0) {
            bestCost = Math.min(bestCost, cost + graph[city][0]);
            return;
        }

        for (int next = 0; next < N; next++) {

            if (!visited[next] && graph[city][next] > 0) {

                int newCost = cost + graph[city][next];

                if (newCost >= bestCost) continue;

                visited[next] = true;
                tsp(next, count + 1, newCost);
                visited[next] = false;
            }
        }
    }

    public static void main(String[] args) {

        visited[0] = true;     
        tsp(0, 1, 0);

        System.out.println("Minimum TSP Cost = " + bestCost);
    }
}