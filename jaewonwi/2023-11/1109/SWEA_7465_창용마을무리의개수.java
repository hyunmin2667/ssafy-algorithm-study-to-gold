import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465_창용마을무리의개수 {
	static int T, N, M, ans;
	static int[][] adj;
	static int[] parent;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		adj = new int[N+1][N+1];
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			makeSet();
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
//				adj[a][b] = adj[b][a] = 1;
				union(a, b);
			}
			
			int[] check = new int[N+1]; 
			for (int i = 1; i <= N; i++) {
				check[find(i)]++;
			}
			
			ans = 0;
			for (int i = 1; i <= N; i++) {
				if (check[i] > 0)
					ans++;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet() {
		parent = new int[N+1];
		for (int i = 1; i <= N;	i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		if (parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return false;
		
		if (a > b) parent[b] = a;
		else parent[a] = b;
		return true;
	}
}
