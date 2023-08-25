package BOJ.algostudy;

import java.util.*;
import java.io.*;

/*며칠이 지나야 토마토가 모두 익나?
 * M N
 * 6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
 */
public class BOJ_7576_Tomato {
	static int M, N, ans, day;
	static int[][] box;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());//상자 크기
		N = Integer.parseInt(st.nextToken());//토마토 수
		
		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					queue.offer(new int[] { i,j });// 첫번쨰
				}
			}
		}
		bfs();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				day = Math.max(day, box[i][j]);
				if (box[i][j] == 0) {
					System.out.println(-1);
					 return;
				}
			}
		}
		System.out.println(day-1);
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];

				if (cx < 0 || cx >= N || cy < 0 || cy >= M)
					continue;

				if (box[cx][cy] == 0) {
					box[cx][cy] = box[x][y] + 1;
					queue.offer(new int[] { cx, cy });
				}
				else if (box[cx][cy] == 1) {
					box[cx][cy] = Math.min(box[cx][cy], box[x][y] + 1);
				}
			}
		}

	}

}
