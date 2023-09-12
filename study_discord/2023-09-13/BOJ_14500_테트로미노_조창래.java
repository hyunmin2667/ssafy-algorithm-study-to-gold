import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노_조창래 {
	static int N, M; // 세로, 가로
	static int[][] nums; // 종이에 적혀있는 수
	static Coord[] target = new Coord[4];
	static boolean[][] visited;
	static int ans = 0;
	static int nSum; // 테트로미노의 수들의 합
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// backtrack해서 구하기
	static void backtrack(int tIdx) {
		if (tIdx == 4) {
			// 좌표의 합 계산
			nSum = 0;
			for (Coord crd: target) {
				nSum += nums[crd.x][crd.y];
			}
			
			// 정답 수정
			ans = Math.max(ans, nSum);
			
			return;
		}
		
		// 선택된 좌표들의 방문되지 않은 이웃에 대해 backtrack
		for (int i = 0; i < tIdx; i++) {
			Coord crd = target[i];
			for (int d = 0; d < 4; d++) {
				int nx = crd.x + dx[d];
				int ny = crd.y + dy[d];
				if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
					visited[nx][ny] = true;
					target[tIdx] = new Coord(nx, ny);
					backtrack(tIdx + 1);
					visited[nx][ny] = false;
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
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// nums 정의
		nums = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M]; // visited 정의
		
		// 각 좌표에 대해 backtrack 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				target = new Coord[4];
				target[0] = new Coord(i, j);
				visited[i][j] = true;
				backtrack(1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(ans);

	}

}
