import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N, M, ans;
	static int[][] input, map;	// 자꾸 index를 N+1 이렇게 잡으니까 헷갈려서 틀림... 주의..!
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static List<Pos> virus = new ArrayList<>();
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception {
		// 벽(1) 세개를 세워야 함. 2는 바이러스
		// 바이러스는 벽으로 막히지 않은 빈칸 모두로 퍼짐. -> 바이러스가 퍼지지 않은 곳을 안전영역이라 함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		input = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				 int n = Integer.parseInt(st.nextToken());
				 if (n == 2) {
					 virus.add(new Pos(i, j));
				 }
				 input[i][j] = n;
			}
		}
		
		// 벽 어디 세울 지 선택
		ans = 0;
		comb(0, 0, 0);
		
		System.out.println(ans);
		
	}

	static void check() {
		// 임시 사용할 맵
		for (int i = 0; i < N; i++) {
			map[i] = input[i].clone();
		}
		
		// 바이러스 확산
		Queue<Pos> q = new ArrayDeque<Pos>();
		for (int i = 0; i < virus.size(); i++) {
			q.add(virus.get(i));
		}
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				while (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 0) {
					map[ny][nx] = 2;
					q.add(new Pos(ny, nx));
					ny += dy[d];
					nx += dx[d];
				}
			}
		}
		
		// 안전영역 계산
		ans = Math.max(ans, count(map));		
	}
	
	static int count(int[][] map) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					sum++;
			}
		}	
		return sum;
	}
	
	// 2차원 배열 조합 !!!
	static void comb(int sy, int sx, int cnt) {
		if (cnt == 3) {	// 벽을 3개 다 세웠으면..!
			check();
			return;
		}
		
		for (int i = sy; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == sy && j < sx) continue;
				
				if (input[i][j] == 0) {
					input[i][j] = 1;
					comb(i, j+1, cnt+1);
					input[i][j] = 0;
				}
			}
		}
		
//		for (int i = sy; i <= N; i++) {
//			for (int j = sx; j <= M; j++) {
//				if (input[i][j] == 0 && !visit[i][j]) {
//					tgt[cnt] = new Pos(i, j);
//					visit[i][j] = true;
//					comb(i, j, cnt+1);
//					visit[i][j] = false;
//				}
//			}
//		}
	}
	
}
