import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1948_임계경로 {
    static int N;
    static int M;
    static int start; // 출발 도시
    static int end; // 도착 도시
    static HashMap<Integer, HashMap<Integer, Integer>> weights;
    static int[] inDegs;
    static int[] dists; // dists[i]는 start로부터 i까지의 최장 길이.
    static Set<Integer>[] prevSets;
    static boolean[] visited;
    static int ans = 0;

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        dists[start] = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            if (!weights.containsKey(vertex))
                continue;

            for (int neighbor : weights.get(vertex).keySet()) {

                if (inDegs[neighbor] == 0)
                    continue;

                inDegs[neighbor] -= 1;

                // if (vertex == 5) {
                // System.out.println(inDegs[neighbor]);
                // }

                int time = dists[vertex] + weights.get(vertex).get(neighbor);

                if (time == dists[neighbor]) {
                    prevSets[neighbor].add(vertex);
                }

                else if (time > dists[neighbor]) {
                    dists[neighbor] = time;
                    prevSets[neighbor] = new HashSet<>();
                    prevSets[neighbor].add(vertex);
                }

                // if (vertex == 5) {
                // System.out.println(neighbor);
                // System.out.println(inDegs[neighbor]);
                // }

                if (inDegs[neighbor] == 0) {
                    queue.offer(neighbor);
                }

            }
        }

    }

    static void getEdgesNum(int vertex) {

        for (int neighbor : prevSets[vertex]) {
            ans++;
            if (visited[neighbor])
                continue;

            visited[neighbor] = true;
            getEdgesNum(neighbor);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        weights = new HashMap<>();

        inDegs = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 출발점
            int e = Integer.parseInt(st.nextToken()); // 도착점
            int w = Integer.parseInt(st.nextToken()); // weight

            inDegs[e]++;

            if (!weights.containsKey(s)) {
                weights.put(s, new HashMap<>());
            }

            weights.get(s).put(e, w);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dists = new int[N + 1];
        prevSets = new Set[N + 1];

        for (int i = 0; i <= N; i++) {
            prevSets[i] = new HashSet<>();
        }

        topologicalSort();

        // System.out.println(Arrays.toString(dists));
        // System.out.println(Arrays.toString(prevSets));
        // System.out.println(weights.toString());

        // sb에 최장 길이 append.
        sb.append(dists[end]).append("\n");

        // sb에 도로의 개수 append.
        // sb.append(getEdgesNum(end));

        visited = new boolean[N + 1];
        visited[end] = true;
        getEdgesNum(end);
        sb.append(ans);

        System.out.println(sb);
    }

}