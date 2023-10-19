package BOJ.algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 데이터셋
 * T
 * M N K-배추 갯수
 * 2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5
 */

public class BOJ_1012_cabbage {
	static int N, M, K;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[M][N];
			check = new boolean[M][N];

			for (int i = 0; i < K; i++) {// 배추 위치
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && !check[i][j]) {// 배추가 심어져있고 방문하지 않았던 노드면 탐색
						cnt++;
						dfs(i, j);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y) {
		check[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if (cx == M || cy == N || cx == -1 || cy == -1)
				return;

			if (!check[cx][cy] && map[cx][cy] == 1) {
				dfs(cx, cy);
			}
		}
	}
}
