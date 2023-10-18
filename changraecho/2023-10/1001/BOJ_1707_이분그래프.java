import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프 {
    static int K;
    static int V, E;
    static List<List<Integer>> adjList;
    static boolean[] visited;
    static int[] colors;
    static String isBipartite;

    static void bfs() {
        isBipartite = "YES";
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= V; i++) {
            deque.offer(i);
        }

        int vertex;
        while (!deque.isEmpty()) {
            vertex = deque.poll();

            if (visited[vertex])
                continue;

            if (colors[vertex] == 0) {
                colors[vertex] = 1;

                for (int neighbor : adjList.get(vertex)) {
                    colors[neighbor] = 2;
                    deque.offerFirst(neighbor);
                }
            } else if (colors[vertex] == 1) {
                for (int neighbor : adjList.get(vertex)) {
                    if (visited[neighbor])
                        continue;

                    if (colors[neighbor] == 0) {
                        colors[neighbor] = 2;
                        deque.offerFirst(neighbor);
                    }

                    else if (colors[neighbor] == 2) {
                        deque.offerFirst(neighbor);
                    }

                    else {
                        isBipartite = "NO";
                        return;
                    }
                }
            }

            else {
                for (int neighbor : adjList.get(vertex)) {
                    if (visited[neighbor])
                        continue;

                    if (colors[neighbor] == 0) {
                        colors[neighbor] = 1;
                        deque.offerFirst(neighbor);
                    }

                    else if (colors[neighbor] == 1) {
                        deque.offerFirst(neighbor);
                    }

                    else {
                        isBipartite = "NO";
                        return;
                    }

                }
            }

            visited[vertex] = true;

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= K; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList<>();
            for (int i = 0; i <= V + 1; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                adjList.get(v1).add(v2);
                adjList.get(v2).add(v1);
            }

            visited = new boolean[V + 1];
            colors = new int[V + 1];

            bfs();

            sb.append(isBipartite).append("\n");
        }

        System.out.println(sb);

    }
}
