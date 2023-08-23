import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2667_단지번호붙이기_조창래 {
	static int[] dx = {0, -1, 0, 1}; // 오 위 왼 아
	static int[] dy = {1, 0, -1, 0};
	static int N;
	static char[][] grid; // N * N 정사각형
	static boolean[][] visited;
	static List<Integer> houseNums = new ArrayList<>();
	static int houseNum;
	
	
	static void countHouse(int i, int j) { // grid의 좌표 (i,j)가 1이라면, (i,j)를 포함하는 단지의 아파트 수를 세기
		
		int nx, ny;
		for (int d = 0; d < 4; d++) {
			nx = i + dx[d];
			ny = j + dy[d];
			
			if (0 <= nx && nx < N && 0 <= ny && ny < N && grid[nx][ny] == '1' ) {
					grid[nx][ny] = '#';
					houseNum++;
					countHouse(nx, ny);
			} 
			
		}
		

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == '1') {
					
					grid[i][j] = '#';
					houseNum = 1;
					countHouse(i, j);
					houseNums.add(houseNum);
				}
			}
		}
		
		sb.append(houseNums.size()).append("\n");
		
		Collections.sort(houseNums);
		for (int num: houseNums) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
