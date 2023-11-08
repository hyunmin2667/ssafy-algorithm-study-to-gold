import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS {
	static int N, M, V;
	static int[][] adjMatrix;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];	// 0 dummy
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b]	= adjMatrix[b][a] = 1;
		}
		
		visit = new boolean[N+1];	// 0 dummy
		dfs(V);
		sb.append("\n");
		
		visit = new boolean[N+1];	// 0 dummy
		bfs(V);
		sb.append("\n");
	
		System.out.println(sb);
	}
	
	static boolean[] visit;
	static void dfs(int start) {
		visit[start] = true;
		sb.append(start).append(" ");
		
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[start][i] != 0 && !visit[i]) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(start);
		visit[start] = true;
		sb.append(start).append(" ");
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[cur][i] != 0 && !visit[i]) {
					q.add(i);
					visit[i] = true;
					sb.append(i).append(" ");
				}
			}
		}
		
	}
}
