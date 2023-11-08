import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	static int T, N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			make();
			
			rank = new int[N+1];
			Arrays.fill(rank, 1);
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int flag = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (flag == 1) {
					if (find(a) == find(b)) sb.append(1);
					else sb.append(0);
				} else {
					union(a,b);
				}
			}
			sb.append("\n");
		}
			System.out.println(sb);
	}

	static int[] parent;
	static void make() {
		parent = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);	// path compression
	}
	
	static int[] rank;
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) return false;	// 이미 같은 집합일 경우
		
		if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
			rank[a] += rank[pb];
		} else {
			parent[pa] = pb;
			rank[pb] += rank[pa];
		}
		
		return true;
	}
	
	

}
