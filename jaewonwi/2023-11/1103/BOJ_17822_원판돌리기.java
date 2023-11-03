import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T, x, d, k, ans;
	static int[][] input;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		input = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			// 번호가 x의 배수인 원판을 d 방향으로 k칸 회전 (d=0 시계/d=1 반시계)
			for (int j = x; j <= N; j += x) {
				 spin(j, d, k);
			}
			// 원판에 수가 남아있으면 인접하면서 수가 같은 것을 모두 찾는다.
			check();
		}

		ans = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (input[r][c] != -1) 
					ans += input[r][c];
			}
		}
		
		System.out.println(ans);
	}
	
	static void printArr(int[][] arr) {
		for (int r = 0; r <= N; r++) {
			for (int c = 0; c <= M; c++) {
				System.out.print(arr[r][c]+" ");
			}
			System.out.println();
		}		
		System.out.println("============================");
	}
	
	
	static void check() {
		boolean flag = false;
		int[][] map = new int[N+1][M+1];
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (input[r][c] == -1) continue;
				
				int cur = input[r][c];
				
				for (int dir = 0; dir < 4; dir++) {	// 사방탐색해서 같은 것이 발견되면 
					int nr = r + dr[dir];
					if (nr < 1 || nr > N) continue;
					
					int nc = c + dc[dir];
					if (nc < 1) nc += M;
					else if (nc > M) nc -= M;
					
					if (cur == input[nr][nc]) {	// 삭제해야할 배열에 추가 해주자
						map[nr][nc] = cur;
						map[r][c] = cur;
						flag = true;
					}
				}
			}
		}
        
		// 인접하면서 같은 수(존재하는 경우)를 다 지움
		if (flag) {
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					if (map[r][c] > 0) {
						input[r][c] = -1;	// 원본배열에서도 지워주자!
					}
				}
			}
		} else {
			// 원판에 적힌 수의 평균을 구하고 평균보다 큰 수는 1을 빼고 작은수는 1을 더함
			cal(input);
		}
	}
	
	static void cal(int[][] arr) {
		float sum = 0;
		float cnt = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (arr[r][c] == -1) continue;
				
				sum += arr[r][c];
				cnt++;
			}
		}
		
		float avg = sum / cnt;
		//System.out.println(avg);
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (arr[r][c] == -1) continue;
				
				
				if (arr[r][c] < avg) arr[r][c] = arr[r][c] + 1;
				else if (arr[r][c] > avg) arr[r][c] = arr[r][c] - 1;  // 제발!!!!!!!!! 조건 확인
			}
		}
		
	}
	
	static void spin(int who, int how, int many) {
		if (how == 0) {	// 시계 방향
			for (int i = 0; i < many; i++) {
				int temp = input[who][M];
				for (int j = M; j > 1; j--) {
					input[who][j] = input[who][j-1];
				}
				input[who][1] = temp;
			}
		} else {	// 반시계 방향
			for (int i = 0; i < many; i++) {
				int temp = input[who][1];
				for (int j = 1; j < M; j++) {
					input[who][j] = input[who][j+1];
				}
				input[who][M] = temp;
			}
		}
	}
}
