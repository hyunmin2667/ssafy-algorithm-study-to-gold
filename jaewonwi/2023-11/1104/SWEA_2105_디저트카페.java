import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페 {
	static int T, N, ans, count;
	static int[][] map;
	
	static int[] dy = { 1, 1, -1, -1 };	// 시계방향으로 돌자
	static int[] dx = { 1, -1, -1, 1 };
	static StringBuilder sb = new StringBuilder();
	
	static int sy, sx;
	static boolean[][] visit;
	static boolean[] dessert;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = -1;
			visit = new boolean[N][N];
			dessert = new boolean[101];			
			// 풀이
			// 각 출발점마다 가능한 사각형을 다 돌자
			for (int i = 0; i < N-2; i++) {
				for (int j = 1; j < N-1; j++) {
					Arrays.fill(dessert, false);
					
					sy = i;
					sx = j;
					
					count = 1;
					visit[i][j] = true;
					dessert[map[i][j]] = true;
					
					dfs(i, j, 0);
					
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int y, int x, int dir) {
		for (int d = dir; d < 4; d++) {	// 지금 가고있던 방향부터 시계로 돌리자
			int ny = y + dy[d];
			int nx = x + dx[d];
		
			if (ny >= 0 && nx >= 0 && ny < N && nx < N) {	// 영역 내에 있는지 체크
				if (ny == sy && nx == sx && count > 2) {	// 출발 지점으로 다시 돌아왔을 경우 & 이동 거리가 2 이상인 경우(제자리에서 체크한 것이 아닐 때) 
					ans = Math.max(ans, count);		// 이동거리 = 먹은 종류 개수
					return;
				}
				
				if (!visit[ny][nx] && !dessert[map[ny][nx]]) {	// 아직 먹은 적 없는 디저트이고, 방문한 적 없는 칸이면 진행
					visit[ny][nx] = true;			// 방문체크
					dessert[map[ny][nx]] = true;	// 디저트 냠냠
					count++;						//한 칸 더 진행 했다고 체크해주자
					
					dfs(ny, nx, d);	// 다음 칸에서 dfs 진행
					
					count--;
					visit[ny][nx] = false;
					dessert[map[ny][nx]] = false;
				}
			}
		}	
	}
}
