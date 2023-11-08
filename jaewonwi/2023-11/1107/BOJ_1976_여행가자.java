import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자 {
	static int N, M;
	static int[] parent;
	static int[] rank;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// ~200
		M = Integer.parseInt(br.readLine());	// ~1000
		
		make_Set();
		rank = new int[N];
		Arrays.fill(rank, 1);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1) union_Set(i, j);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 1; i < M; i++) {
			int next = Integer.parseInt(st.nextToken()) - 1;
			if (find_Set(prev) != find_Set(next)) {
				System.out.println("NO");
				return;
			} else {
				prev = next;
			}
		}
		System.out.println("YES");
		
	}
	
	static void make_Set() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}
	
	static int find_Set(int a) {
		if (parent[a]==a) return a;
		
		return parent[a] = find_Set(parent[a]);
	}
	
	static boolean union_Set(int a, int b) {
		int pa = find_Set(a);
		int pb = find_Set(b);
		
		if (pa == pb) return false;
		
		if (pa > pb) {
			parent[pb] = pa;
		} else {
			parent[pa] = pb;
		}
		return true;
	}
}
