import java.util.*;

public class Main {
    private int V; 
    private List<Integer>[] adj; 

    private int time = 0;
    private int[] disc;   
    private int[] low;    
    private int[] parent;
    private Stack<String> edgeStack; 
    public Main(int V) {.  // BiconnnectedComponents
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    public void findBCC() {
        disc = new int[V];
        low = new int[V];
        parent = new int[V];
        edgeStack = new Stack<>();

        Arrays.fill(disc, -1);
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                dfs(i);
                
                // Print leftover BCC
                if (!edgeStack.isEmpty()) {
                    System.out.print("BCC: ");
                    while (!edgeStack.isEmpty()) {
                        System.out.print(edgeStack.pop() + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                parent[v] = u;
                edgeStack.push(u + "-" + v); // push edge

                dfs(v);

                low[u] = Math.min(low[u], low[v]);

                // Condition for articulation point → BCC found
                if (low[v] >= disc[u]) {
                    System.out.print("BCC: ");
                    String edge;
                    do {
                        edge = edgeStack.pop();
                        System.out.print(edge + " ");
                    } while (!edge.equals(u + "-" + v));
                    System.out.println();
                }

            } else if (v != parent[u] && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                edgeStack.push(u + "-" + v);
            }
        }
    }

    public static void main(String[] args) {
         // BiconnectedComponents g = new BiconnectedComponents(5);

Main g  = new Main(5);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        System.out.println("Biconnected Components:");
        g.findBCC();
    }
}