package BOJ.algostudy;

import java.io.*;
import java.util.*;

/* 데이터셋
 * 7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */
public class BOJ_2667_num {
	static int N, cnt, idx;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static StringBuilder sb = new StringBuilder();
	static Queue<int[]> queue = new ArrayDeque<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		idx = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();

			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					dfs(i, j);
					queue.offer(cnt);
					
					cnt = 0;
					idx++;
				}
			}
		}
		System.out.println(idx);
		while(!queue.isEmpty()) {
			sb.append(queue.poll()).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0)
			return;// 범위 벗어났으니 return
		if (map[x][y] == 0)
			return;// 빈 공간이면 return

		if (!visit[x][y]) {
			cnt++;
			visit[x][y] = true;
			for (int i = 0; i < 4; i++) {
				dfs(x + dx[i], y + dy[i]);
			}
		}
	}
}
