import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2573_빙산_조창래 {
	
	static int N, M;
	static int[][] grid;
	static int[][] temp; // 줄어드는 높이를 기록하는 배열
	static boolean[][] visited; // dfs 실행 시 방문된 좌표들을 기록
	
	static int[] dx = {0, -1, 0, 1}; // 오 위 왼 아
	static int[] dy = {1, 0, -1, 0};
	
	static void melt() { // 빙산이 녹는다
		temp = new int[N][M];
		
		int nx, ny;
		
		// 각 빙산에 대해 줄어들어야 하는 높이들을 기록
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] > 0) {
					
					for (int d = 0; d < 4; d++) {
						nx = i + dx[d];
						ny = j + dy[d];
						if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] == 0) {
							temp[i][j] += 1;
						}
					}
					
				}
			}
		}
		
		// 각 빙산을 녹임
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] > 0) {
					grid[i][j] = Math.max(0, grid[i][j] - temp[i][j]);
				}
			}
		}
		
	}
	
	static int getIslandNum() {
		int res = 0;
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && grid[i][j] > 0) {
					visited[i][j] = true;
					res++;
					dfs(i, j);
				}
			}
		}
		
		return res;
		
	}
	
	static void dfs(int i, int j) {
		
		int nx, ny;
		
		for (int d = 0; d < 4; d++) {
			nx = i + dx[d];
			ny = j + dy[d];
			
			if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] > 0 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
			
		}
	}
	
	static boolean allMelted() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] > 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void draw() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(grid[i][j] + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 빙산 녹이기
		int time = 0;
		while (true) {
			// 덩어리의 개수가 2라면 time 출력
			
			if (getIslandNum() >= 2) {
				System.out.println(time);
				return;
			}
			
			// 모든 빙산이 다 녹았다면 0을 출력
			if (allMelted()) {
				System.out.println(0);
				return;
			}
			
			
			
			// 빙산 녹이기, 시간++
			melt();
			time++;
			
			
		}

	}

}
