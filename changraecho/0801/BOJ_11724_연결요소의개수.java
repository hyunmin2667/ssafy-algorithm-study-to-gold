import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_11724_연결요소의개수 {
    static HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
    static boolean[] checked;

    static void bfs(int v) {
        if (checked[v])
            return;

        checked[v] = true;
        for (Integer neighbor : graph.get(v)) {
            bfs(neighbor);
        }
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer nAndM = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(nAndM.nextToken());
        int M = Integer.parseInt(nAndM.nextToken());

        checked = new boolean[N + 1];

        // graph 초기화
        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        // graph 정의
        for (int i = 0; i < M; i++) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int firstV = Integer.parseInt(edge.nextToken());
            int secondV = Integer.parseInt(edge.nextToken());

            graph.get(firstV).add(secondV);
            graph.get(secondV).add(firstV);
        }

        int ans = 0;

        // 각 꼭짓점마다, 그것이 체크되지 않았다면 그것에 bfs 적용.
        for (int v : graph.keySet()) {
            if (!checked[v]) {
                ans++;
                bfs(v);
            }
        }

        System.out.println(ans);

    }

}
