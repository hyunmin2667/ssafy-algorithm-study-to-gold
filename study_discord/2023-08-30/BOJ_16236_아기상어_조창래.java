

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어_조창래 {
	static int N;
	static int[][] grid; // bfs를 실행하기 위해 필요한 grid의 복제
	static int[][] gridCopy; // 변하는 grid
	static int killedFishNum = 0;
	static int bsSize = 2;
	static int bsx; // 아기상어의 현재 x좌표
	static int bsy; // 아기상어의 현재 y좌표
	static int[] dx = {-1, 0, 1, 0}; // 위, 왼, 아, 오
	static int[] dy = {0, -1, 0, 1};
	static PriorityQueue<Coord> parentpq;
	static PriorityQueue<Coord> childrenpq;
	static int ans = 0;
	
	static void getChildren() { // parentpq로부터 childrenpq 정의하기
		childrenpq = new PriorityQueue<>();
		
		// parentpq의 각 원소로부터, 방문되지 않은 주변의 이웃들을 방문 체크하고
		for (Coord crd: parentpq) {
			int x = crd.x;
			int y = crd.y;
			
			int nx, ny;
			
			for (int dir = 0; dir < 4; dir++) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < N && grid[nx][ny] <= bsSize) {
					Coord nCrd = new Coord(nx, ny);
					if (isVisited(nCrd)) continue;
					
					visit(nCrd);
					
					childrenpq.offer(nCrd);	
				}
				
				
			}
			
		}
		
	}
	
	static boolean isVisited(Coord crd) { // 좌표의 점이 방문되어 있는 지 확인
		int x = crd.x;
		int y = crd.y;
		
		if (grid[x][y] < 0) return true;
		
		return false;
	}
	
	static void visit(Coord crd) { // 해당 좌표 방문
		int x = crd.x;
		int y = crd.y;
		
		if (grid[x][y] == 0) { // 빈칸
			grid[x][y] = -7;
		} else if (grid[x][y] > 0) { // 물고기
			grid[x][y] = - grid[x][y];
		}
	}
	
	static int bfs() { // 현재 아기상어의 좌표로부터 가장 가까운 거리에 있는 물고기로 이동.
//		PriorityQueue<Coord> pq = new PriorityQueue<>();  
		parentpq = new PriorityQueue<>();
		int depth = 0;
		
		// gridCopy로부터 grid 정의하기
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = gridCopy[i][j];
			}
		}
		
		Coord start = new Coord(bsx, bsy);
		
		parentpq.add(start);
		visit(start);
		getChildren();
		
		while(!childrenpq.isEmpty()) {			
			depth++;

			parentpq = new PriorityQueue<>();
			
			while (!childrenpq.isEmpty()) {
				Coord crd= childrenpq.poll();
				int x = crd.x;
				int y = crd.y;
				// crd가 물고기라면
				if (grid[x][y] < 0 && grid[x][y] != -7 && -grid[x][y] < bsSize) {
					// 물고기 섭취
					killedFishNum++;
					
					// 필요하다면 사이즈 증가
					if (killedFishNum == bsSize) {
						killedFishNum = 0;
						bsSize++;
					}
					
					// gridCopy 수정
					gridCopy[x][y] = 0;
					
					// bsx, bsy 수정
					bsx = x;
					bsy = y;
					
					// depth return
					return depth;
				}
				parentpq.offer(crd);
			}
			
			getChildren();
		}
		
		return N * N + 1;
	}
	
	static class Coord implements Comparable<Coord> {
		int x, y;

		@Override
		public int compareTo(Coord o) {			
			 if (this.x != o.x) {
				return this.x - o.x;
			} else {
				return this.y - o.y;
			}
			
		}
		
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
		@Override
		public String toString() {
			return "Coord [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static void draw() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(gridCopy[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		gridCopy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				gridCopy[i][j] = Integer.parseInt(st.nextToken());
				if (gridCopy[i][j] == 9) {
					bsx = i;
					bsy = j;
					gridCopy[i][j] = 0; // 9는 지운다. 물고기의 크기와 혼동될 수 있기 때문.
				}
			}
		}
		
		while (true) {
			int dist = bfs();
			
			if (dist != N * N + 1) {
				ans += dist;
			} else {
				System.out.println(ans);
				
				break;
			}
			
		}

	}

}
