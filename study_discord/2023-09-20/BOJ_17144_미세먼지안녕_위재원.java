import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 미세먼지 안녕!
public class BOJ_17144_미세먼지안녕_위재원 {
	static int R, C, T;
	static int air1, air2;
	static int[][] A, S;
	static int[] dr = { -1, 1, 0, 0 };	// 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		A = new int[R+1][C+1];
		S = new int[R+1][C+1];
		
		boolean flag = false;
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				// 공기청정기
				if (A[r][c] == -1 && !flag) {
					air1 = r;
					flag = true;
				}
			}
		}
		air2 = air1+1;
//		System.out.println(air1+" "+air2);
		
		// 풀이
		for (int t = 0; t < T; t++) {
			// 1. 미세먼지 확산: 모든 칸에서 사방으로 확산, 공기청정기로는 확산 X
			dust();
			// 2. 공기청정기 작동: 바람이 부는 방향으로 1칸씩 밀림
			move();
//			A[air1][1] = -1;
//			A[air2][1] = -1;
		}
		
		// 미세먼지 양(총합) 구하기
		int sum = 2;	// 공기청정기 제외(-2 상쇄)
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				sum += A[r][c];
			}
		}
		
		// 출력
		System.out.println(sum);
	}
	
	// 1. 미세먼지 확산
	// 확산되는 양 = A[r][c]/5
	// 남는 양 = A[r][c] - 확산되는 양(A[r][c]/5) * 확산된 방향의 개수
	static void dust() {
		// 합 배열 초기화
		for (int r = 1; r <= R; r++)
			Arrays.fill(S[r], 0);
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				// 미세먼지가 확산될 수 있다면
				if (A[r][c] > 0) {
					int diffuse = A[r][c] / 5;	// 확산되는 양
					for (int i = 0; i < 4; i++) {	// 인접한 방향에 대해
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						// 범위 벗어남 || 공기청정기 => continue
						if (nr < 1 || nc < 1 || nr > R || nc > C || A[nr][nc] == -1)
							continue;
						
						S[nr][nc] += diffuse;
						A[r][c] -= diffuse;
					}
				}
			}
		}

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				A[r][c] += S[r][c];
			}
		}
	}
	
	// 2. 공기청정기 작동
	// 공기청정기는 무조건 1열에 배치되어있음 -> (r, 1)에 위치
	// 공기청정기로 바람이 들어가면 정화됨
	// 공기청정기 위쪽으로는 반시계, 아랫쪽으로는 시계 방향으로 바람이 순환함.
	
	// 이동 시킬때 순서를 바람의 방향대로 생각하지말고 역순으로 생각해주면 훨씬 쉽다!!
	static void move() {
		// 위쪽 - 반시계
		for (int r = air1 - 1; r > 1; r--) {	// (1, 1) v (air1, 1)
			A[r][1] = A[r-1][1];
		}
		for (int c = 1; c < C; c++) {	// (1, 1) < (1, C) 
			A[1][c] = A[1][c+1];
		}
		for (int r = 1; r < air1 ; r++) {	// (air1, C) ^ (1, C)
			A[r][C] = A[r+1][C];
		}
		for (int c = C; c > 2; c--) {	// (air1, 1) > (air1, C)
			A[air1][c] = A[air1][c-1];
		}
		A[air1][2] = 0;
		
		// 아래쪽 - 시계
		for (int r = air2+1 ; r < R; r++) {	// (R, 1) ^ (air2, 1) 
			A[r][1] = A[r+1][1];
		}
		for (int c = 1; c < C; c++) {	// (R, C) < (R, 1)
			A[R][c] = A[R][c+1];
		}
		for (int r = R; r > air2; r--) {	// (air2, C) v (R, C)
			A[r][C] = A[r-1][C];
		}
		for (int c = C; c > 2; c--) {	// (air2, 1) > (air2, C)
			A[air2][c] = A[air2][c-1];
		}
		A[air2][2] = 0;
	}
}
