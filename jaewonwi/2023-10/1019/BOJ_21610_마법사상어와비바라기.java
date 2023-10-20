package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 마법사 상어
public class BOJ_21610_마법사상어와비바라기 {
	// 1번 행(열)과 N번 행(열) 연결
	// 비바라기 (N,1) (N,2) (N-1,1) (N-1,2) 부터 구름이 생김
	// 구름이 M번 이동. i번째 이동 명령은 방향 di(8-좌부터 시계방향)와 거리 si로 이루어져있다.	
	
	static int N, M, d, s, ans;
	static int[][] A;
	
	static boolean[][] cloud;
	
	static int[] dir = { 1, 2, 3, 4, 5, 6, 7, 8 }; 
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { -1, 1, -1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 비바라기 시전 (구름 생성)
		cloud = new boolean[N+1][N+1];
		cloud[N][1] = cloud[N][2] = cloud[N-1][1] = cloud[N-1][2] = true;   
		
	 	for (int t = 1; t <= M; t++) {
	 		st = new StringTokenizer(br.readLine());
	 		d = Integer.parseInt(st.nextToken());
	 		s = Integer.parseInt(st.nextToken());
	 		
	 		// 1. 구름 이동
	 		move(cloud, d, s);

//	 		printb(cloud);
//	 		System.out.println("------------------------------------------------");
	 		// 2. 비 내림 -> 물 양 1 증가
	 		raining(A, cloud);

	 		// 3. 구름 사라짐
//	 		for (int c = 1; c <= N; c++) {
//	 			Arrays.fill(cloud[c], false);
//	 		}
	 		
	 		// 4. 물복사버그 <- 대각선 거리 1인 칸에 물이 있는 바구니의 수만큼 중심 바구니의 물의 양 증가
	 		waterbug(A, cloud);
	 		
	 		// 5. 2이상인 곳에 구름이 생기고 해당 칸 숫자 2 감소, 방금 구름이 있었던 칸은 안됨.
	 		makeCloud(A, cloud);
	 	}
	 	
	 	// 물의 양의 합
	 	ans = 0;
	 	for (int r = 1; r <= N; r++) {
	 		for (int c = 1; c <= N; c++) {
	 			ans += A[r][c];
	 		}
	 	}
	 	
	 	System.out.println(ans);
	}
	
	static void move(boolean[][] map, int d, int s) {
		int dy = 0;
		int dx = 0;
		switch (d) {
		case 1:	dx = -1; break;
		case 2: dy = -1; dx = -1; break;
		case 3: dy = -1; break;
		case 4: dy = -1; dx = 1; break;
		case 5: dx = 1; break;
		case 6: dy = 1; dx = 1; break;
		case 7: dy = 1; break;
		case 8: dy = 1; dx = -1; break;
		}

		s = s % N;
		
		if (dy != 0)
			vert(map, dy, s);
		if (dx != 0)
			hor(map, dx, s);
	
	}
	
	static void hor(boolean[][] map, int dir, int s) {
		// dir = -1, 1
		boolean[][] temp = new boolean[N+1][N+1];
		if (dir == -1) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int idx = (j - s) < 1 ? (j - s + N) : (j - s);
					temp[i][idx] = cloud[i][j];
				}
			}	
		} else if (dir == 1) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int idx = (j + s) > N ? (j + s - N) : (j + s);
					temp[i][idx] = cloud[i][j];
				}
			}
		}
		
		copyMapBoolean(temp, cloud);
	}
	
	static void vert(boolean[][] map, int dir, int s) {
		// dir = -1, 1
		boolean[][] temp = new boolean[N+1][N+1];
		if (dir == -1) {
			for (int i = 1; i <= N; i++) {
				int idx = (i - s) < 1 ? (i - s + N) : (i - s);
				temp[idx] = map[i].clone();
			}	
		} else if (dir == 1) {
			for (int i = 1; i <= N; i++) {
				int idx = (i + s) > N ? (i + s - N) : (i + s);
				temp[idx] = map[i].clone();
			}
		}
		
		copyMapBoolean(temp, cloud);
	}
	
	static void raining(int[][] map, boolean[][] cloud) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j]) {
					map[i][j]++;
				}
			}
		}
	}
	
	static void waterbug(int[][] map, boolean[][] cloud) {
		// dir = 2, 4, 6, 8
		int[][] temp = new int[N+1][N+1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (cloud[r][c]) {
					for (int k = 0; k < 4; k++) {
						int nr = r + dr[k]; 
						int nc = c + dc[k];
						if (nr > 0 && nc > 0 && nr <= N && nc <= N && map[nr][nc] > 0)
							temp[r][c]++;
					}
				}
			}
		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] += temp[r][c];
			}
		}
	}
	
	static void makeCloud(int[][] map, boolean[][] cloud) {
		boolean[][] temp = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] >= 2 && !cloud[i][j]) {
					map[i][j] -= 2;
					temp[i][j] = true;
				}
			}
		}
		copyMapBoolean(temp, cloud);
	}

	static void copyMapBoolean(boolean[][] from, boolean[][] to) {
		for (int r = 1; r <= N; r++) {
			to[r] = from[r].clone();
		}
	}
	
	static void printb(boolean[][] arr) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void print(int[][] arr) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
