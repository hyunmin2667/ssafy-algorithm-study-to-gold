import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1516_게임개발 {
    static int N;
    static int[] buildTime;
    static int[] inDegs;
    static List<List<Integer>> adjList;
    static int[] ansArr;

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        // 초기화
        for (int i = 1; i <= N; i++) {
            if (inDegs[i] == 0) {
                queue.offer(i);
            }
        }

        for (int vertex : queue) { // inDeg가 0인 건물들에 대해 건물들의 짓는데 걸리는 시간들을 기록
            ansArr[vertex] = buildTime[vertex];
        }

        // 알고리즘
        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : adjList.get(vertex)) {
                if (inDegs[neighbor] == 0)
                    continue;

                inDegs[neighbor]--;
                ansArr[neighbor] = Math.max(ansArr[neighbor], ansArr[vertex] + buildTime[neighbor]);

                if (inDegs[neighbor] == 0) {
                    queue.offer(neighbor);
                }

            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        buildTime = new int[N + 1];
        inDegs = new int[N + 1];
        adjList = new ArrayList<>();
        ansArr = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int n = Integer.parseInt(st.nextToken());

                if (n == -1)
                    break;

                adjList.get(n).add(i);
                inDegs[i]++;

            }
        }

        // System.out.println(adjList.toString());

        topologicalSort();

        for (int i = 1; i <= N; i++) {
            sb.append(ansArr[i]).append("\n");
        }

        System.out.println(sb);

    }

}
