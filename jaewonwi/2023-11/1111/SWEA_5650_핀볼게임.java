package swea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_5650_핀볼게임 {
	static int T, N, score, ans;
	static int[][] map;
	
	static class Pos{
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}	
	}
	
	static int[] dy = { -1, 0, 1, 0 };	//상우하좌
	static int[] dx = { 0, 1, 0, -1 };	//0 1 2 3
	static int[][] diaDir = {	// 대각면의 방향(둘중하나로 들어오면 다른 하나로 나가야한다)
			{},		// 0 dummy
			{0, 1},	
			{1, 2},
			{2, 3},
			{3, 0},
			{}		// 5는 사각형이라 반대로 튕김
	};

	static ArrayList<Pos>[] wormwhole = new ArrayList[5];	// 웜홀 6~10, 쌍으로 주어지니까 2개씩
//	static Pos[] blackwhole = new Pos[5];		// 블랙홀 최대 5개
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			for (int i = 0; i < 5; i++) {
				wormwhole[i] = new ArrayList<>();
			}
			
			ans = 0;
			N = Integer.parseInt(br.readLine().trim());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					
					if (n >= 6 && n <= 10) {	// 웜홀
						wormwhole[n-6].add(new Pos(i, j));
//						System.out.println(wormwhole[n-6].get(0).y+" "+wormwhole[n-6].get(0).x);
					}
				}
			}
						
			// 모든 경우의 수에 대해서 고려해보자
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {	// 블랙홀이나 웜홀 위에서는 시작 X
						for (int d = 0; d < 4; d++) {
							 game(i, j, d);
//							 System.out.println("-------");

							 ans = Math.max(ans, score);
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
	}

	static void game(int r, int c, int d) {
		int ny = r;
		int nx = c;
		
		score = 0;
		while (true) {
			ny += dy[d];
			nx += dx[d];

//			System.out.println(ny +" "+nx);
			
			// 범위 벗어날 경우 다시 벽으로 옮겨줘야할까?
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {	// 벽에 부딫힐 경우 -> 반대로 튕김
				score++;
				d = back(d);
				continue;
			}
			
			if (map[ny][nx] == -1) break;	// 블랙홀일 경우 종료
			if (ny == r && nx == c) break;	// 출발지로 돌아올 경우 종료
			
			if (map[ny][nx] == 0) continue;		// 빈칸이면 직진
			else if (map[ny][nx] == 5) {		// 정사각형 블록일 경우 -> 반대로 튕김
				score++;
				d = back(d);
			} else if (map[ny][nx] >= 6 && map[ny][nx] <= 10) {	// 웜홀일 경우
				int w = map[ny][nx] - 6;
				
				Pos cur = wormwhole[w].get(0);
				if (cur.y == ny && cur.x == nx) { // 현재 위치해 있는 웜홀이면
					ny = wormwhole[w].get(1).y;
					nx = wormwhole[w].get(1).x;
				} else {
					ny = cur.y;
					nx = cur.x;
				}
				
			} else {	// 일반 블록일 경우 (1~4)
				score++;
				d = change(ny, nx, d);
			}
			
		}
	}
	
	static int change(int ny, int nx, int d) {
		int block = map[ny][nx];	// 다음칸 블록이 어떤 것인지
		int comeDir = back(d);	// 그 칸으로 공이 어떤 방향에서 들어오는지
		
		int outDir = -1;		// 대각면을 만났는지 체크하면서
		for (int dir = 0; dir < 2; dir++) {
			if (diaDir[block][dir] == comeDir)	{	// 대각면으로 들어오고 있다면 
				outDir = diaDir[block][(dir+1)%2];
			}
		}
		
		if (outDir == -1) {	// 대각면이랑 만나지 않았다면
			outDir = comeDir;
		}
		
		return outDir;
	}
	
	static int back(int dir) {
		return (dir + 2) % 4;
	}
}

