package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11559_PuyoPuyo_조창래 {
	
	static char[][] grid = new char[12][6];
	static boolean[][] visited = new boolean[12][6];
	static int puyoNum;
	static List<Coord> puyoReps; 
	static int[] dx = {0, -1, 0, 1}; // 오 위 왼 아
	static int[] dy = {1, 0, -1, 0};
	static int explosionNum = 0;
	
	static void dfs(int x, int y) {
		puyoNum++;
		
		int nx, ny;
		
		for (int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d];
			
			if (0 <= nx && nx < 12 && 0 <= ny && ny < 6 && !visited[nx][ny] && grid[nx][ny] == grid[x][y]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
			
		}
		
	}
	
	static void erasePuyos(int x, int y, char puyoType) { // 뿌요들 지우기
		int nx, ny;
		
		for (int d = 0; d < 4; d++) {
			
			nx = x + dx[d];
			ny = y + dy[d];
			
			if (0 <= nx && nx < 12 && 0 <= ny && ny < 6 && grid[nx][ny] == puyoType) {
				grid[nx][ny] = '.';
				erasePuyos(nx, ny, puyoType);
			}
			
		}
	}
	
	static void movePuyos() {
		for (int col = 0; col < 6; col++) {
			
			for (int i = 11; i > -1; i--) {
				if (grid[i][col] != '.') { // 밑으로 내리기
					
					int ni = i + 1;
					
					while (ni < 12 && grid[ni][col] == '.') {
						ni++;
					}
					
					if (ni - i >= 2) {
						grid[ni - 1][col] = grid[i][col];
						grid[i][col] = '.';
					}
				}
			}
			
		}
		
	}
	
	
	static class Coord {
		int x, y;

		@Override
		public String toString() {
			return "Coord [x=" + x + ", y=" + y + "]";
		}

		public Coord(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		while (true) {
			
			visited = new boolean[12][6];
			puyoReps = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (grid[i][j] != '.' && !visited[i][j] ) {
						
						visited[i][j] = true;
						puyoNum = 0;
						dfs(i,j);
						
						if (puyoNum >= 4) {
							puyoReps.add(new Coord(i, j));
						}
						
					}
				}
			}
			
			if (puyoReps.isEmpty()) break;

			
			for (Coord c: puyoReps) {
				erasePuyos(c.x, c.y, grid[c.x][c.y]);
			}
			
			explosionNum++;
			
			movePuyos();
			
		}
		
		System.out.println(explosionNum);
		

	}

}
