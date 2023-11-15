import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static int T, N, M, R, C, L, ans;
	static int[][] map;
	
	static boolean[][] visit;
	static Queue<Pos> q = new ArrayDeque<>();
	
	static class Pos{
		int y, x;
		int time, dir;
		public Pos(int y, int x, int time, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
			this.dir = dir;
		}
	}
	
	static int[] dy = { -1, 0, 1, 0 };	// 상 우 하 좌
	static int[] dx = { 0, 1, 0, -1 };	// 0 1 2 3
	static int[][] direction = {
			{},			// 0 dummy
			{ 0, 1, 2, 3 },
			{ 0, 2 },
			{ 1, 3 },
			{ 0, 1 },	// 상우
			{ 1, 2 },	// 하우
			{ 2, 3 },	// 하좌
			{ 0, 3 }	// 상좌
	};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			bfs(R, C);
			
//			printMap(visit);
//			System.out.println("---------------");
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void bfs(int r, int c) {
		q.add(new Pos(r,c,1,map[r][c]));
		visit[r][c] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			go(cur);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j]) ans++;
			}
		}
	}
	
	static void go(Pos cur) {
		if (cur.time >= L) return;
		
		int[] dArr = direction[cur.dir];
		
		for (int d : dArr) {
			
			int ny = cur.y + dy[d];
			int nx = cur.x + dx[d];
			
			if (isOutOfArray(ny, nx) || visit[ny][nx]) continue;

			// 위치상으로는 파이프가 있지만 연결되지 못하는 파이프들인 경우 !!!
			int watchDir = (d + 2) % 4;
			int[] destDir = direction[map[ny][nx]];
			boolean flag = false;
			for (int dd : destDir) {
				if (dd == watchDir) flag = true;
			}
			if (!flag) continue;
			
			visit[ny][nx] = true;	
			q.add(new Pos(ny, nx, cur.time + 1, map[ny][nx]));
//			System.out.println(ny+" "+nx);
		}
	}
	
	static boolean isOutOfArray(int y, int x) {
		if (y < 0 || y >= N || x < 0 || x >= M || map[y][x] == 0)
			return true;
		return false;
	}
	
	static void printMap(boolean[][] map) {
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (visit[i][j])
					System.out.print("T ");
				else System.out.print("F ");
			}
			System.out.println();
		}
	}
}
