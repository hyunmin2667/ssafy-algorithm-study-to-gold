import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_BFS와DFS {
	static int N, M, V;
	static int[][] adj;
	static boolean[] bfsV, dfsV;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adj = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from][to] = adj[to][from] = 1;
		}
		
		bfsV = new boolean[N+1];
		dfsV = new boolean[N+1];
		
		dfs(V);
		
		sb.append("\n");
		
		bfs(V);
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int start) {
		dfsV[start] = true;
		sb.append(start).append(" ");
		
		for (int i = 1; i <= N; i++) {
			if (!dfsV[i] && adj[start][i] == 1) {
				dfs(i);
			}
		}
	}

	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		q.add(start);
		bfsV[start] = true;
		sb.append(start).append(" ");
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (!bfsV[i] && adj[cur][i] == 1) {
					q.add(i);
					bfsV[i] = true;
					sb.append(i).append(" ");
				}
			}
		}
	}
}
