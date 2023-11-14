import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, size, max, ans;
	static int[][] map;
	static class Pos{
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static boolean[] core;
	static List<Pos> list;
	static int[] dy = { -1,1,0,0 };
	static int[] dx = { 0,0,-1,1 };
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i == 0 || i == N-1 || j == 0 || j == N-1) continue;
						list.add(new Pos(i,j));
					}
				}
			}
			
			size = list.size();
			core = new boolean[size];
			ans = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = size; i > 0; i--) {
				comb(0, 0, i);
				
				if (ans < Integer.MAX_VALUE) break;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	static void comb(int idx, int cnt, int total) {
		if (cnt == total) {
			dfs(0, 0);
			return;
		}
		
		if (idx == size) return;
		
		for (int i = idx; i < size; i++) {
			core[i] = true;
			comb(i+1, cnt+1, total);
			core[i] = false;
		}
	}
	
	static void dfs(int idx, int cnt) {
		if (idx == size) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if (!core[idx]) {
			dfs(idx+1, cnt);
			return;
		}
		
		Pos cur = list.get(idx);
		for (int d = 0; d < 4; d++) {
			boolean flag = false;
			int temp = 0;
			
			int ny = cur.y;
			int nx = cur.x;
			
			while (true) {
				ny += dy[d];
				nx += dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) {	// 연결 성공
					flag = true;
					break;
				}
				
				if (map[ny][nx] != 0) break;	// 중간에 무언가 만난다
				
				map[ny][nx] = 2;
				temp++;
			}
			
			if (flag) dfs(idx+1, cnt+temp);
			
			while (true) {
				ny -= dy[d];
				nx -= dx[d];
			
				if (ny == cur.y && nx == cur.x) {
					break;
				}
				
				map[ny][nx] = 0;
			}
		}
		
	}

}
