import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 1s, 256MB
public class BOJ_2573_빙산 {

	static int N, M, year, cntIce, size;
	static int[][] map, mapCopy;
	static boolean isDivide;
	
	static boolean[][] visit;
	static Queue<Ice> queue = new ArrayDeque<>();
	
	static int[] dy = { -1, 1, 0 , 0 };
	static int[] dx = { 0, 0, -1 , 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		year = 0;
		isDivide = false;
		map = new int[N][M];
		mapCopy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				// 맵과 카피맵에 동시에 값 저장
				map[i][j] = mapCopy[i][j] = Integer.parseInt(st.nextToken());
				// 빙산 찾아서 큐에 넣기
				if(map[i][j] > 0) queue.offer(new Ice(i,j));
			}
		}
		
		// 맵에 빙산이 없어질때까지 돌린다.
		while(!queue.isEmpty()) {
			// 현재 큐에 남아있는 빙산의 개수 만큼 한바퀴 함수 돌린다.
			size = queue.size();
			Ice ice = queue.peek(); // dfs용 변수
			
			// 큐에 있는 모든 빙산 다 확인하러 감
			for (int i = 0; i < size; i++) {
				// 현재 빙산목록에서 빼서 확인하러 간다. 함수 안에서 빙산이 큐에 다시 들어갈수도 있음
				check( queue.poll() );
			}
			
			visit = new boolean[N][M];
			
			// 현재 선택된 빙산 처리
			cntIce = 1;
			int y = ice.y;
			int x = ice.x;
			visit[y][x] = true;
			// dfs 탐색 시작
			dfs(y, x);
			
			// 연결된 빙산이 전체 빙산보다 적으면 분리된 것
			if(cntIce < size) {
				isDivide = true;
				break;
			}
			
			// 한바퀴 돌고 왔으니까 업데이트된 복사 맵을 원본 맵에 덮어쓰기
			for (int i = 0; i < N; i++) {
				// 'mapCopy의 0부터 M까지' 'map의 0부터 M'에 복사한다.
				System.arraycopy(mapCopy[i], 0, map[i], 0, M);
			}
			
			// 맵을 다시 돌기 전에 시간 증가
			year++;

		}
		
		// 빙산을 다 돌았는데 빙산이 분리되지 않았다면, year을 0으로 초기화
		if(!isDivide) year = 0;
		
		// 정답 출력
		System.out.println(year);
		
	}
	
	static void check(Ice ice) {
		int y = ice.y;
		int x = ice.x;
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 주변에 빙산이 아닌 칸이 몇개인지 count
			if(map[ny][nx] <= 0) cnt++;
		}
		
		// 계산 후, 빙산이 여전히 남아 있으면 다시 빙산 큐에 넣기
		mapCopy[y][x]-=cnt;
		if(mapCopy[y][x] > 0) queue.offer(ice);
		
	}
	
	static void dfs(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(visit[ny][nx]) continue;
			// 빙산이 아닌 곳은 continue;
			if(map[ny][nx] <= 0) continue;
			
			visit[ny][nx] = true;
			cntIce++;
			dfs(ny, nx);
		}
	}
	
	static class Ice {
		int y, x;
		public Ice(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}