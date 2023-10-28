import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	static int N, M, r, c, dir, ans;	// dir: 0~3 (^,>,v,<)
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) * (-1);
			}
		}
		
		boolean work = true;
		ans = 0;
		while (work) {
			// 1. 현재 칸 청소 X -> 현재 칸 청소
			if (map[r][c] == 0) {
				map[r][c] = 1;
				ans++;
			}
			// 2. 현재 칸 주변 4칸이 다 청소 되어있는 경우 -> 방향 유지한채로 한 칸 후진 후 1
			//	=> 바라보는 방향의 뒤쪽이 벽이라 후진할 수 없는 경우 작동중지
			// 3. '' 청소되지 않은 칸이 있는 경우 -> 반시계로 90도 회전, 바라보는 방향이 청소되지 않은 경우 한 칸 전진하여 1
			boolean isClean = true;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (map[nr][nc] == 0) {
					isClean = false; 
				}
			}

			if (!isClean) {	// 청소되지 않은 칸이 있는 경우
				dir = (dir+3) % 4;	// 반시계 방향 회전 
				if (map[r+dr[dir]][c+dc[dir]] == 0) {	// 바라보는 방향의 앞쪽 칸이 청소되지 않은 경우
					r += dr[dir];
					c += dc[dir];
				}
			} else {	// 주위가 전부 청소되어있는 경우
				int back = (dir+2) % 4;
				int nr = r + dr[back];
				int nc = c + dc[back];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != -1) {
					r = nr;
					c = nc;
				} else {
					work = false;
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
