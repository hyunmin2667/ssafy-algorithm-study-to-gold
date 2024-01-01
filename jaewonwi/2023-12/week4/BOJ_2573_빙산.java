import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	// 빙산의 높이가 양의 정수로 저장. 바다는 0
	// 매년 인접한 바다 칸 수만큼 줄어듬
	// 두 덩어리 이상으로 분리되는 최초의 시간(bfs)
	static int N, M, ans;	// 300이하
	static int[][] map;
	
	static Queue<Pos> q;
	static boolean flag;
	
	static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n > 0) q.add(new Pos(i,j,n));
			}
		}
		
		ans = 0;
		while (true) {
			if (!check()) break;
			melt();
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static class Pos {
		int y, x;
		int h;
		public Pos(int y, int x, int h) {
			super();
			this.y = y;
			this.x = x;
			this.h = h;
		}
	}
	
	static boolean check() {
		int size = q.size();
		if (size == 0) {
			ans = 0;
			return false;
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
		
		Queue<Pos> tq = new ArrayDeque<>();
		Pos cur = q.peek();
		visit[cur.y][cur.x] = true;
		tq.add(cur);
		cnt++;
		
		while (!tq.isEmpty()) {
			cur = tq.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
			
				if (outOfRange(ny, nx)) continue;
				
				if (visit[ny][nx]) continue;
				
				visit[ny][nx] = true;
				if (map[ny][nx] > 0) {
					cnt++;
					tq.add(new Pos(ny, nx, map[ny][nx]));
				}
			}
		}
		
//		System.out.println("cnt: " + cnt + " size: "+ size);
		if (cnt == size) return true;
		else return false;
	}

	static void melt() {
		Queue<Pos> tq = new ArrayDeque<>();
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			int minus = 0;
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
			
				if (outOfRange(ny, nx)) continue;
				
				if (map[ny][nx] == 0) minus++;
			}
		
			cur.h -= minus;
			if (cur.h > 0) tq.add(new Pos(cur.y, cur.x, cur.h));
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 0);
		}
		
		while (!tq.isEmpty()) {
			Pos cur = tq.poll();
			map[cur.y][cur.x] = cur.h;
			q.add(cur);
		}
	}
	
	static boolean outOfRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M) return true;	// 범위 밖이라면 true
		return false;
	}
}
