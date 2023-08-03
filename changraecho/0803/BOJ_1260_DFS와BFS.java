
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

//import java.io.FileInputStream;

public class BOJ_1260_DFS와BFS {
    static ArrayList<Integer>[] graph;
    static int v;
    static boolean[] checked;
    static Queue<Integer> q = new LinkedList<Integer>();
    static StringBuilder dfsSb = new StringBuilder();
    static StringBuilder bfsSb = new StringBuilder();

    static void dfs(int v) {
        checked[v] = true;
        dfsSb.append(v).append(" ");
        for (int neighbor : graph[v]) {
            if (!checked[neighbor]) {
                dfs(neighbor);
            }
        }
    }

    static void bfs() {

        while (!q.isEmpty()) {
            // System.out.println(q);
            int vertex = q.poll();

            bfsSb.append(vertex).append(" ");

            for (int neighbor : graph[vertex]) {
                if (!checked[neighbor]) {
                    checked[neighbor] = true;
                    q.add(neighbor);
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N, M, v
        StringTokenizer nmv = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nmv.nextToken());
        int M = Integer.parseInt(nmv.nextToken());
        v = Integer.parseInt(nmv.nextToken());

        // q 정의
        q.add(v);

        // checked arr 초기화
        checked = new boolean[N + 1];

        // graph 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        // graph 정의
        for (int i = 1; i <= M; i++) {
            StringTokenizer vertexes = new StringTokenizer(br.readLine());
            int firstV = Integer.parseInt(vertexes.nextToken());
            int secondV = Integer.parseInt(vertexes.nextToken());

            graph[firstV].add(secondV);
            graph[secondV].add(firstV);
        }

        // vertex의 neighbor 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        // System.out.println(Arrays.deepToString(graph));

        // dfs 실행
        dfs(v);

        // checked 초기화
        checked = new boolean[N + 1];
        checked[v] = true;
        // bfs 실행
        bfs();

        System.out.println(dfsSb.toString());
        System.out.println(bfsSb.toString());

    }
}
