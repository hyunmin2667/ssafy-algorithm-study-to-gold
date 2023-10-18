import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 상어 초등학교
// 1. 비어있는 칸 중에 좋아하는 학생이 가장 많이 인접한 칸으로 자리를 정함
// 2. 1을 만족하는 자리가 여러개라면 인접한 칸 중에서 빈칸이 가장 많은칸으로 자리를 지정
// 3. 2를 만족하는 '' 행의 번호가 가장 작은 칸, 그 중 열의 번호가 가장 작은 칸
public class boj_21608_상어초등학교 {
	static int N, who, minC, minR, ans;
	static int[][] map;
	static int[][] likes;
	static int[] score = { 0, 1, 10, 100, 1000 };
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		likes = new int[N*N+1][4];
		for (int i = 1; i <= N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			who = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				likes[who][j] = Integer.parseInt(st.nextToken());
			}
			
			// 자리 지정
			seat(who);
		}
		
		ans = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int satisfy = howManyLike(map[r][c], r, c);
				ans += score[satisfy];
//				System.out.println("add "+map[r][c]+" "+satisfy+ " " + score[satisfy]);
			}
		}
		
		System.out.println(ans);
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int howManyLike(int who, int r, int c) {
		int sum = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc] == 0) continue;
			
			for (int i = 0; i < 4; i++) {
				if (map[nr][nc] == likes[who][i]) sum++;
			}
		}
		return sum;
	}
	
	static int howManyEmpty(int r, int c) {
		int sum = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d];
			
			if (nr >= 1 && nc >= 1 && nr <= N && nc <= N && map[nr][nc] == 0)
				sum++;
		}
		return sum;
	}
	
	static void seat(int who) {
		ArrayList<Pos>[] list = new ArrayList[5];
		ArrayList<Pos>[] elist = new ArrayList[5];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Pos>();
			elist[i] = new ArrayList<Pos>();
		}
		
		PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> o1.r - o2.r);
		
		int max = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				// 1. 비어있는 칸 중에 좋아하는 학생이 가장 많이 인접한 칸으로 자리를 정함
				if (map[r][c] == 0) {	// 비어있는 칸 중에
					// 인접한 좋아하는 친구 수를 세고
					int like = howManyLike(who, r, c);
					if ( max <= like ) {	// 현재까지 최댓값 이상이라면
						max = like;
						list[max].add(new Pos(r,c));
					}
				}
			}
		}
				
		// 2. 인접한 칸 중에서 빈칸이 가장 많은 칸
		int emax = 0;
		for (int i = 0; i < list[max].size(); i++) {	// 주위에 인접한 좋아하는 친구가 최대인 칸들에 대해
			Pos cur = list[max].get(i);		// 하나씩 가져와
			int empty = howManyEmpty(cur.r, cur.c);
			if ( emax <= empty ) {	// 현재까지 최댓값 이상이라면
				emax = empty;
				elist[emax].add(new Pos(cur.r, cur.c));
			}
		}
				
		// 3. 행의 번호가 가장 작은 칸, 그 중 열의 번호가 가장 작은 칸
		for (int i = 0; i < elist[emax].size(); i++) {	// 인접한 빈칸이 최대인 칸들에 대해
			Pos cur = elist[emax].get(i);
			pq.add(cur);
		}
		
		Pos prev = pq.poll();
		minR = prev.r;
		minC = prev.c;
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			if (prev.r == cur.r) {
				if (prev.c > cur.c) {
					minC = cur.c;
					prev = cur;
				}
			} else {
				break;
			}
		}
	
		map[minR][minC] = who;
//		System.out.println(minR+" "+minC+" "+who);
		
	}	
	
}