package algo_study;

import java.io.*;
import java.util.*;

/*N M
 * 5 7
3994995
9999999
4H99399
9999999
2993994

6
 */

public class BOJ_1103_game {

	static int N, M, max;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][] visit;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
	static boolean cycle;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 1;
		visit = new boolean[M][N];
		map = new int[M][N];
		dp = new int[M][N];

		for (int j = 0; j < N; j++) {
			String s = br.readLine();
			for (int i = 0; i < M; i++) {

				if (s.charAt(i) != 'H')
					map[i][j] = s.charAt(i) - '0';
				else
					map[i][j] = s.charAt(i);
			}
		}

		// 시작점 세팅
		visit[0][0] = true;
		dfs(0, 0, 1);
		if (!cycle)
			System.out.println(max);
		else
			System.out.println(-1);

	}

	static void dfs(int x, int y, int cnt) {
		// -1이면 무조건 나가기
		if (cycle)
			return;

		int scale = map[x][y];
		dp[x][y] = cnt;
		max = Math.max(max, cnt);// 더 큰값이 max가 된다

		for (int i = 0; i < 4; i++) {
			int newX = (dx[i] * scale) + x;
			int newY = (dy[i] * scale) + y;

			if (newX >= M || newX < 0 || newY >= N || newY < 0)
				continue;// 범위 체크
			if (map[newX][newY] == 'H')
				continue;// 구멍에 빠졌다

			if (visit[newX][newY]) {// true : 사이클이 있다
				max = -1;
				cycle = true;
				return;
			} // 아니면

			// 다음 이동값이 현재 값보다 크면 볼일없음
			if (cnt < dp[newX][newY])
				continue;

			visit[newX][newY] = true;
			dfs(newX, newY, cnt + 1);// dfs
			visit[newX][newY] = false;
		}

	}

	// static void dfs(int[] pos) {
	// //-1이면 무조건 나가기
	// if(max ==-1)return;

	// int scale = map[pos[0]][pos[1]];

	// for(int i = 0; i<4; i++) {
	// int newX = (dx[i] * scale) + pos[0];
	// int newY = (dy[i] * scale) + pos[1];

	// if (newX >= M || newX < 0 || newY >= N || newY < 0) continue;// 체크

	// if (visit[newX][newY]) {// true : 사이클이 있다
	// max = -1;
	// cycle = true;
	// return;
	// } // 아니면

	// if (map[newX][newY] == 24) continue;//구멍에 빠졌다
	// //다음 이동값이 현재 값+1보다 크면 볼일없음
	// if(dp[pos[0]][pos[1]]+1 < dp[newX][newY]) continue;

	// dp[newX][newY] = dp[pos[0]][pos[1]] + 1;//기존 위치 +1
	// max = Math.max(max, dp[newX][newY]);//더 큰값이 max가 된다

	// visit[newX][newY] = true;
	// dfs(new int[] { newX, newY });//dfs
	// visit[newX][newY] = false;
	// }

	// }

}
