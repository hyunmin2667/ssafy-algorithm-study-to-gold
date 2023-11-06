import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179_불 {
	static int R, C, ans;
	static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static Queue<Pos> fire = new ArrayDeque<>();
	static Queue<Pos> jh = new ArrayDeque<>();
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++){
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				 if (map[i][j]=='J') jh.add(new Pos(i,j, 0));
				 if (map[i][j]=='F') fire.add(new Pos(i,j,0));
			}
		}
		
		ans = Integer.MAX_VALUE;
		bfs();
		
		if (ans == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
		else System.out.println(ans);
	}
	
	static void bfs() {
		// 지훈이 먼저 대피! -> 불 번짐;; (X)
		// 불이 번지고 -> 대피
		
		while (!jh.isEmpty()) {		// while (true) 아무데나 하지말고 조건 제시해주자... 시간초과 남
			int fsize = fire.size();
			for (int f = 0; f < fsize; f++) {
				Pos cur = fire.poll();
				
				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					
					if (ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] != '#' && map[ny][nx] != 'F') {
						map[ny][nx] = 'F';
						fire.add(new Pos(ny, nx, 0));
					}	
				}
			}
			
			int jsize = jh.size();
			for (int j = 0; j < jsize; j++) {
				Pos jihun = jh.poll();
//				visit[jihun.y][jihun.x] = true;
				
				for (int d = 0; d < 4; d++) {
					int jy = jihun.y + dy[d];
					int jx = jihun.x + dx[d];
					
					if (jy < 0 || jy >= R || jx < 0 || jx >= C) {	// 여기서 범위체크 해주니까 밑에서 따로 할 필요 없음
//						if (map[jy][jx] != '#' && map[jy][jx] != 'F') {	// 이미 맵 밖이라서 체크 X & 
							ans = jihun.t+1;
							return;
//						}
					}
					
					if (map[jy][jx] != '#' && map[jy][jx] != 'F' && !visit[jy][jx]) {
						jh.add(new Pos(jy, jx, jihun.t+1));
						visit[jy][jx] = true;
						
					} 
				}
			}
		}
	}

	static class Pos {
		int y, x;
		int t;
		private Pos(int y, int x, int t) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}
}
