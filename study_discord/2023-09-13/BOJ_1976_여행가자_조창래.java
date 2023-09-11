import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자_조창래 {
	static int N; // 전체 도시의 수
	static int M; // 여행 계획에 속한 도시들의 수
	static int[][] adjMatrix; // 연결 정보. dummy, (N + 1 ) * (N + 1) grid 
	static int[] travel; // 여행 계획; 길이가 M인 array
	static int[] parent; // 0은 dummy, 1 ~ N
	
	static int findParent(int x) {
		if (parent[x] == x) return x;
		
		return parent[x] = findParent(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = findParent(x);
		int py = findParent(y);
		
		parent[px] = py;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		
		// 선분 연결하기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adjMatrix[i][j] == 1) {
					union(i, j);
				}
			}
		}
		
		travel = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			travel[i] = Integer.parseInt(st.nextToken());
		}
		
	
		for (int i = 0; i < M - 1; i++) {
			if (findParent(travel[i]) != findParent(travel[i + 1])) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}

}
