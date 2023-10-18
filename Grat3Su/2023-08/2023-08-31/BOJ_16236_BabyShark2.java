package BOJ.algostudy;

import java.util.*;
import java.io.*;
/*첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.
더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.


 * 
 * 테스트 케이스
 * N
 * Map
 * 3
0 0 0
0 0 0
0 9 0

->0
 */

public class BOJ_16236_BabyShark2 {
	static int N, count;
	static boolean getTarget;
	static int[][] map;
	static boolean[][] visit;
	static Shark shark;
	static int[] dx = { 0, -1, 0, 1 };// 위 왼쪽 순으로 우선순위가 높음
	static int[] dy = { -1, 0, 1, 0 };
	static int[] target;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		target = new int[2];
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
				}
			}
		}
		while (true) {
			boolean call = true;
				find_dfs(shark.x, shark.y);
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j]!=0&&map[i][j] <shark.size) {
						call = false;
					}
				}
			}
			
			if(call) break;
		}
		System.out.println(count);
	}

	static void update() {
		if (shark.size <= shark.feed) {
			//shark.feed -= shark.size;
			shark.size++;
		}
		getTarget = false;
	}

	static void find_dfs(int x, int y) {
		if (getTarget) {
			return;
		}
		int fish = map[x][y];
		if (fish != 0 && fish < shark.size) {
			shark.feed++;
			getTarget = true;
			map[x][y] = 0;
			count += (Math.abs(x - shark.x) + Math.abs(y - shark.y));

			update();
		}

		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			if (x+ dx[i] >= N || y + dy[i] >= N || x+ dx[i] < 0 || y + dy[i] < 0||visit[x + dx[i]][y + dy[i]])
				continue;
			find_dfs(x + dx[i], y + dy[i]);
		}
		visit[x][y] = false;
	}

	static class Shark {// 위치, 크기, 먹은양, 움직인 횟수
		int x, y, size, feed;

		public Shark(int x, int y, int size, int feed) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.feed = feed;
		}
	}
}
