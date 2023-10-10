import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766_문제집 {
    static int N;
    static int M;
    static List<List<Integer>> adjList;
    static int[] inDegs; // 길이가 N + 1(index 0은 dummy)
    static StringBuilder sb = new StringBuilder();

    static void topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // inDegs가 0인 vertex들을 모두 pq에 offer
        for (int i = 1; i <= N; i++) {
            if (inDegs[i] == 0) {
                pq.offer(i);
            }
        }

        // pq가 nonempty일때
        while (!pq.isEmpty()) {
            // pq에서 poll한 vertex를 sb에 append
            int v = pq.poll();
            sb.append(v).append(" ");

            // vertex를 시작점으로 지니는 edge들을 모두 제거
            for (int neighbor : adjList.get(v)) {
                if (inDegs[neighbor] == 0)
                    continue;
                inDegs[neighbor]--;

                // inDegs가 0인 vertex가 생기면 pq에 offer
                if (inDegs[neighbor] == 0)
                    pq.offer(neighbor);
            }

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        inDegs = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList.get(start).add(end);
            inDegs[end]++;
        }

        topologicalSort();
        System.out.println(sb);
    }

}