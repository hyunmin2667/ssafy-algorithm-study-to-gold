import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012_유기농배추 {
	// 배추 재배 -> 배추흰지렁이가 해충을 잡아먹음
	// 인접한 배추 무리 하나에 지렁이가 한마리만 있으면 그 무리 보호가능
	// 1이면 배추가 심어져있고, 0이면 빈 땅
	static int T, N, M, K, X, Y, cnt, ans;
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	static Pos[] input;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0,-1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());	// 가로
			N = Integer.parseInt(st.nextToken());	// 세로
			K = Integer.parseInt(st.nextToken());	// 배추 개수

			input = new Pos[K];
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine().trim());
				
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				map[Y][X] = -1;
//				input[k] = new Pos(Y, X);
				
			}
			
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1)
						bfs(i, j, cnt++);
				}
			}
			
			// 풀이
			ans = Integer.MAX_VALUE;
			
			// 출력
			System.out.println(cnt);
		}

	}
	
	static void bfs(int sy, int sx, int idx) {
		Queue<Pos> q = new ArrayDeque<>();
//		visit[sy][sx] = true;
		map[sy][sx] = idx;
		q.add(new Pos(sy, sx));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				if (!inArray(ny, nx)) continue;
				
				if (map[ny][nx] == -1) {
					q.add(new Pos(ny, nx));
					map[ny][nx] = idx;
				}
			}
		}
	}
	
	static boolean inArray(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M) return false;
		return true;
	}

}
