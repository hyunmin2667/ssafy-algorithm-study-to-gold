import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕_조창래 {
	static int R, C; // 행과 열
	static int[][] grid; // 2차원 배열
	static int[][] temp; // 확산 계산을 위한 임시 배열: 추가되는 미세먼지들의 좌표
	static int purifierTop = -1; // 공기청정기의 윗 부분의 x좌표
	static int purifierBot = -1; // 공기청정기의 아랫 부분의 x좌표
	static int[] dx = {0, -1, 0, 1}; // 오, 위, 왼, 아
	static int[] dy = {1, 0, -1, 0};
	static int T;
	static int ans = 0;
	
	static void disperse() { // 확산
		temp = new int[R + 1][C + 1]; // 0행과 0열은 dummy
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {		
				if (grid[i][j] > 0) {
					int nx, ny;
					int dust = grid[i][j];
					
					for (int dir = 0; dir < 4; dir++) {
						nx = i + dx[dir];
						ny = j + dy[dir];
						
						if (nx == purifierTop && ny == 1) continue; // 공기청정기의 경우 확산이 되지 않음
						if (nx == purifierBot && ny == 1) continue;
						
						if (1 <= nx && nx <= R && 1 <= ny && ny <= C) {
							temp[nx][ny] += dust / 5; // 추가되는 미세먼지
							grid[i][j] -= dust / 5; // 제거되는 미세먼지
						}	
					}
				}
			}
		}
		
		// temp를 이용해 grid 재정의
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				grid[i][j] += temp[i][j];
			}
		}
	}
	
	static void purifyTop() { // 윗 부분 청소
		int[] dirArr = {1, 0, 3,2};
		int curX = purifierTop - 1;
		int curY = 1;
		 
		int dir = 0;
		
		int nx, ny;
		while (true) {
			nx = curX + dx[dirArr[dir]];
			ny = curY + dy[dirArr[dir]];
//			System.out.println(nx + " " + ny);
			if (1 <= nx && nx <= purifierTop && 1 <= ny && ny <= C) {
				
				if (nx == (purifierTop) && ny == 1) { // 경로의 마지막 좌표일 때
					grid[curX][curY] = 0;
					break;
				} else {
					grid[curX][curY] = grid[nx][ny];
					curX = nx;
					curY = ny;
				}
			} 
			
			else if (dir < 3) { // 방향조절
				dir++;
			} 
		}	
	}
	
	static void purifyBot() {
		int[] dirArr = {3, 0, 1, 2};
		int curX = purifierBot + 1;
		int curY = 1;
		 
		int dir = 0;
		
		int nx, ny;
		while (true) {
			nx = curX + dx[dirArr[dir]];
			ny = curY + dy[dirArr[dir]];
//			System.out.println(nx + " " + ny);
			if (purifierBot <= nx && nx <= R && 1 <= ny && ny <= C) {
				
				if (nx == (purifierBot) && ny == 1) { // 경로의 마지막 좌표일 때
					grid[curX][curY] = 0;
					break;
				} else {
					grid[curX][curY] = grid[nx][ny];
					curX = nx;
					curY = ny;
				}
			} 
			
			else if (dir < 3) { // 방향조절
				dir++;
			} 
		}	
	}
	
	
	
	static void cycle() {
		for (int i = 0; i < T; i++) {
			disperse();
			purifyTop();
			purifyBot();	
		}
//		draw();
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				ans += grid[i][j];
			}
		}
	}
	
	static void draw() { // 테스트를 위해
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		grid = new int[R + 1][C + 1];
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] == -1) { // 공기청정기 부분
					if (purifierTop == -1) {
						purifierTop = i;
					} else {
						purifierBot = i;
					}
					grid[i][j] = 0;
				}
			}
		}
		
		cycle();
		
		System.out.println(ans);
	}
	
}
