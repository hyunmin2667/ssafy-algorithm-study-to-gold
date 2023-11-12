import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4615_재밌는오셀로게임 {
	static int T, t, N, M, white, black;
	static int[][] map;
	
	static boolean[][] visit;
	static int cnt;
	static boolean flag;
	static int[] dy = { -1,-1,0,1,1,1,0,-1 };	// 상~ 시계 방향으로 8방향
	static int[] dx = { 0,1,1,1,0,-1,-1,-1 };
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for (t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			mapInit(map);
			for (int m = 0; m < M; m++) {	// 돌을 놓자! (흑:1, 백:2)
				st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken()) - 1;	// 열
				int y = Integer.parseInt(st.nextToken()) - 1;	// 행
				int stone = Integer.parseInt(st.nextToken());
				
				visit = new boolean[N][N];
				game(x, y, stone);
			}
			
			mapCnt(map);
		}
		System.out.print(sb);
	}

	static void game(int x, int y, int stone) {
		map[y][x] = stone;
		
		for (int d = 0; d < 8; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (outOfRange(ny, nx)) continue;
			
			// 체크할 배열 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}
			
			cnt = 0;
			flag = false;

			dfs(ny, nx, d, stone);
			
			if (flag) {
				// d 방향으로 cnt만큼 해당 돌로 바꿔주자
				change(ny, nx, d, stone);	// (y,x)부터 d방향으로 cnt만큼 stone으로 바꿔주자
			}
		}
	}
	
	static void dfs(int y, int x, int d, int stone) {
		visit[y][x] = true;
		cnt++;
		
		if (map[y][x] == stone) {
			flag = true;
			return;
		}
		
		if (map[y][x] == 0) {	// 중간에 돌이 없는 경우도 고려해주어야 함!!
			return;
		}
		
		int ny = y + dy[d]; 
		int nx = x + dx[d];
		
		if (outOfRange(ny, nx)) return;
			
		dfs(ny, nx, d, stone);
	}

	static void change(int y, int x, int d, int stone) {
		int ny = y;
		int nx = x;
		for (int i = 0; i < cnt; i++) {	// 마지막 점은 어차피 바꿔줄 필요가 없음
			map[ny][nx] = stone;
			ny += dy[d];
			nx += dx[d];
		}
	}
	
	static boolean outOfRange(int ny, int nx) {		// 범위 밖이면 true
		if (ny < 0 || ny >= N || nx < 0 || nx >= N)
			return true;
		else return false;
	}
	
	static void mapInit(int[][] map) {	// 중앙에 4개 놓고 시작 
		int mid = map.length / 2 - 1;
		map[mid][mid] = map[mid+1][mid+1] = 2; // 백돌 세팅
		map[mid][mid+1] = map[mid+1][mid] = 1; // 흑돌 세팅
	}
	
	static void mapCnt(int[][] map) {
		white = 0;
		black = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) black++;
				else if (map[i][j] == 2) white++;
			}
		}
		sb.append("#").append(t).append(" ").append(black).append(" ").append(white).append("\n");
	}
}
