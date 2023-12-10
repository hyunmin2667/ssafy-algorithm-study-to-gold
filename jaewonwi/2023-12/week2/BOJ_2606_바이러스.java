import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {
	// 웜바이러스 연결된 모든 컴퓨터를 감염시킴
	// 어떠한 컴퓨터로 인해 바이러스에 걸리는 컴퓨터의 수 구하기
	
	static int N, K, A, B, ans;
//	static List<Integer>[] adjList;
	static boolean[][] adjMatrix;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());	//컴퓨터의 수	// 1 ~ N
		K = Integer.parseInt(br.readLine().trim());	//연결된 컴퓨터 정보
		
//		adjList = new ArrayList[N+1];	// 0 dummy
//		for (int i = 0; i <= N; i++) {
//			adjList[i] = new ArrayList<>();
//		}
		
		adjMatrix = new boolean[N+1][N+1];
		visit = new boolean[N+1];
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			adjMatrix[A][B] = adjMatrix[B][A] = true;
		}
		
		ans = 0;
		bfs(1);

		System.out.println(ans);
	}

	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visit[start] = true;
		q.add(start);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (visit[i]) continue;
				
				if (adjMatrix[cur][i]) {
					q.add(i);
					visit[i] = true;
					ans++;
				}
			}
		}
	}
}
