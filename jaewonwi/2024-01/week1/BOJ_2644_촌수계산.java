import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산 {
	static int n, m, a, b, x, y, ans;
	static int[][] adj;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		adj = new int[n+1][n+1];	// 0 dummy
		visit = new boolean[n+1];	// 0 dummy
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			x = Integer.parseInt(st.nextToken());	// 부모
			y = Integer.parseInt(st.nextToken());	// 자식
			
			adj[x][y] = adj[y][x] = 1;
		}
		
		ans = -1;
		visit[a] = true;
		dfs(a, 0);
		System.out.println(ans);
	}
	
	static void dfs(int idx, int cnt) {
		if (idx == b) {
			ans = cnt;
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (!visit[i] && adj[idx][i] == 1) {
				visit[i] = true;
				dfs(i, cnt+1);
			}
		}
	}

}
