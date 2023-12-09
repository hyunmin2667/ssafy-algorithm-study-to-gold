import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {
	// N x M
	// 1 이동 가능 / 0 이동 불가
	// (1,1) -> (N,M)까지 최소 이동
	
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static class Pos {
		int y, x, cnt;

		public Pos(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
//				System.out.print(map[i][j]+" ");
			}
		}
		
		ans = Integer.MAX_VALUE;
		
//		dfs(0,0,1);
		
		bfs(0,0,1);
		
		System.out.println(ans);
	}
	
	static void bfs(int sy, int sx, int cnt) {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(sy,sx,cnt));
		visit[sy][sx] = true;	// bfs에서 큐에 넣을 때, 방문 체크를 해야 메모리 초과가 나지 않음. 뺄 때 방문체크를 하면 중복방문이 일어날 수 있다.
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.y == N-1 && cur.x == M-1) {
				ans = Math.min(ans, cur.cnt);
				continue;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				if (!inArray(ny, nx)) continue;
				
				if (!visit[ny][nx] && map[ny][nx] == 1) {
					q.add(new Pos(ny, nx, cur.cnt + 1));
					visit[ny][nx] = true;
				}
			}	
		}
		
		
	}
	
	
	static void dfs(int sy, int sx, int cnt) {
		if (sy == N-1 && sx == M-1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if (cnt >= ans) return;
		
		visit[sy][sx] = true;
		for (int d = 0; d < 4; d++) {
			int ny = sy + dy[d];
			int nx = sx + dx[d];
			
			if (!inArray(ny, nx)) continue;
			
			if (!visit[ny][nx] && map[ny][nx] == 1) {
				visit[ny][nx] = true;
				dfs(ny, nx, cnt + 1);
				visit[ny][nx] = false;
			}
		}
		
	}
	
	static boolean inArray(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
			return false;
		}
		return true;
	}
}
