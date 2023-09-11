import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, X;
	static int[] ans;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		ans = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
			ans[i] = -1;
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}

		bfs(X);

		// 출력
		boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (ans[i] == K) {
                System.out.println(i);
                check = true;
            }
        }

        // 없으면 -1 출력
        if (!check) System.out.println(-1);
	}

	private static void bfs(int s) {
		// 출발 도시까지의 거리는 0으로 설정
		ans[s] = 0;

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 현재 도시에서 이동할 수 있는 모든 도시를 확인
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);

				// 아직 방문하지 않은 도시라면
				if (ans[next] == -1) {
					// 최단 거리 갱신
					ans[next] = ans[cur] + 1;
					q.offer(next);
				}
			}
		}
	}
}
