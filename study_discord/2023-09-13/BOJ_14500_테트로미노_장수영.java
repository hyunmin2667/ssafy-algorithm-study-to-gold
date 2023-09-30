
import java.util.*;
import java.io.*;

/* N M
 * 5 5
1 2 3 4 5
5 4 3 2 1
2 3 4 5 6
6 5 4 3 2
1 2 1 2 1

->19

4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
->20


첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.

1. 테트리스라고 해서 테트리스 모양을 배열로 만들어서 겹치는 모양의 수를 더하려고 했으나 회전, 대칭이 된다는 말에 포기
2. 내가 이해한거랑 다른거같아서 블로그 참고.
3. 무조건 4개가 붙어있으면 하나의 모양으로 치기 때문에 깊이 우선 탐색으로 깊이4의 모양을 체크하도록 함
 */
public class BOJ_14500_테트로미노_장수영 {
	static int N, M, max, maxnum;
	static int[][] map;
	static boolean[][] visit;
	static Coordinates[] c;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		max = 0;
		maxnum= 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visit = new boolean[M][N];
		c = new Coordinates[4];
		
		for(int i = 0; i<4; i++) {
			c[i] = new Coordinates(0, 0);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());// 맵 받기
				
				if(map[j][i]>maxnum)
					maxnum = map[j][i];
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = true;
				c[0].x =i;//시작점
				c[0].y =j;
				
				dfs(0);
				visit[i][j] = false;
				if(max == maxnum*4)	{
					System.out.println(max);
					return;				
				}
			}
		}
		System.out.println(max);
	}

	static void dfs(int cIdx) {
		if (cIdx == 3) {// 테트로미노다
			int sum = 0; 
			for(int i = 0; i<4; i++) {
				sum += map[c[i].x][c[i].y];
			}
			
			max = Math.max(max, sum);
			return;
		}
		if (max == maxnum * 4)
			return;

		for (int d = 0; d < 2; d++) {
			for (int i = 0; i <= cIdx; i++) {
				int x = c[i].x + dx[d];
				int y = c[i].y + dy[d];

				if (x >= M || x < 0 || y >= N || y < 0 || visit[x][y])
					continue;
				c[cIdx+1].x =x; 
				c[cIdx+1].y =y; 
				visit[x][y] = true;
				dfs(cIdx + 1);
				visit[x][y] = false;
			}
		}
	}
	
	static class Coordinates{
		int x, y;

		public Coordinates(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
