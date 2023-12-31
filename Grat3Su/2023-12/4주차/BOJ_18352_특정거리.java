import java.awt.*;
import java.awt.image.DataBufferUShort;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int N, M, K, X;
    static List<Integer>[] adjList;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjList[to].add(from);
        }
        bfs();
    }

    static void bfs() {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(X);
        dist[X] = 0;

        List<Integer>ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();
            if(dist[cur]>K)break;
            if(dist[cur]==K)ans.add(cur);

            for(int next:adjList[cur]){
                if(dist[next] !=Integer.MAX_VALUE)continue;
                dist[next] = dist[cur] +1;
                q.add(next);
            }
        }

        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        for(int cur:ans){
            sb.append(cur).append("\n");
        }
        System.out.println(ans.isEmpty()?-1:sb);
    }
}
