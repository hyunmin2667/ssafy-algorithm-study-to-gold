import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
4 4
2 1 1 0
1 0 0 1
1 1 0 0
1 1 1 0 

// 정답		// 0으로 막힌 0에 대해서 -1 처리를 해주어야 함
0 1 2 0 
1 0 0 -1 
2 3 0 0 
3 4 5 0

// 처리하지 않았을 경우
0 1 2 0 
1 0 0 -1 
2 3 0 -1 
3 4 5 0



 */

public class BOJ_14940_쉬운최단거리 {
	// 모든 지점에 대해서 목표지점까지의 거리 구하기 - 인접한 곳으로만 움직일 수 있음
	
	static int N, M;
	static int[][] map, resultMap;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static Pos dest;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		resultMap = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 2) {
					dest = new Pos(i,j);
				}
				map[i][j] = n;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				resultMap[i][j] = -1;
			}
		}
		
		bfs(dest.y, dest.x);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) resultMap[i][j] = 0;	// 0으로 막힌 0에 대해서 -1 처리를 해주어야 함
				sb.append(resultMap[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		
		System.out.println(sb);
	}
	
	static void bfs(int y, int x) {
		Queue<Pos> q = new ArrayDeque<>();
		visit[y][x] = true;
		resultMap[y][x] = 0;
		q.add(dest);
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				if (!inArray(ny,nx)) continue;	// 범위를 벗어나면
				
				if (visit[ny][nx]) continue;	// 이미 방문했다면 
				
				if (map[ny][nx] == 0) {	// 원래 갈 수 없는 땅이면 0
					resultMap[ny][nx] = 0;
					visit[ny][nx] = true;
					continue;
				}
				
				// 갈 수 있는 땅인데 도달할 수 없다면 -1
				
				
				resultMap[ny][nx] = resultMap[cur.y][cur.x] + 1;
				visit[ny][nx] = true;
				q.add(new Pos(ny, nx));
				
			}
			
		}
	}
	
	static boolean inArray(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M) return false;
		return true;
	}

}