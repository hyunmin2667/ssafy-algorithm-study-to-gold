import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	// 수의 등장횟수가 커지는 순으로 정렬(같을 경우,수가 큰 순) -> 다시 A에 넣자(수&등장횟수)
	// 가장 큰 행이나 열 기준으로 배열의 크기가 변함. 정렬 시 0은 무시. 크기가 100 이상이면 100개까지만
	static int r, c, k, time;
	static int[][] A = new int[100][100];	// 3x3 ~ 100x100

	static int rCnt = 3, cCnt = 3;
	static int[] numCntArr = new int[101], numCntFin = new int[101];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		time = 0;
		while (A[r][c] != k && time < 100) {
			// 행의 개수와 열의 개수 체크 및 비교 -> 행>=열 경우 행 정렬 / 나머지는 열 정렬
			if (rCnt >= cCnt) {
				cArrange();
			} else {
				rArrange();
			}
			
//			for (int i = 0; i < rCnt; i++) {
//				for (int j = 0; j < cCnt; j++) {
//					System.out.print(A[i][j] + " ");
//				}
//				System.out.println();
//			}
//			
//			System.out.println("행의 수: " + rCnt + " | 열의 수: " + cCnt);
//			System.out.println("==============================");
			
			time++;
		}
		
		if (A[r][c] != k) System.out.println(-1);
		else System.out.println(time);
	}

	static void rArrange() {
		// 행에 대해 정렬 수행
		for (int j = 0; j < cCnt; j++) {	// 배열을 열 기준으로 돌면서
			int i = 0;
			Arrays.fill(numCntArr, 0);
			
			while (i < 100) {			// 현재까지 배열에 들어있는 숫자개수 카운트
				if (A[i][j] != 0)
					numCntArr[A[i][j]]++;
				i++;
				
			}
			
			// 숫자와 등장횟수에 따라 다시 넣어주기
			inputArr(0);
			
			if (rCnt > 100) rCnt = 100;
			// 행 정렬이므로 행 기준으로 다 넣어줌
			int idx = 0;
			while (idx < rCnt) {
				A[idx][j] = numCntFin[idx];
				idx++;
			}
		}
	}
	
	static void cArrange() {
		// 열에 대해 정렬 수행
		for (int i = 0; i < rCnt; i++) {	// 배열을 행 기준으로 돌면서
			int j = 0;
			Arrays.fill(numCntArr, 0);
			
			while (j < 100) {			// 현재까지 배열에 들어있는 숫자개수 카운트
				if (A[i][j] != 0)
					numCntArr[A[i][j]]++;
				j++;
			}
			// 숫자와 등장횟수에 따라 다시 넣어주기
			inputArr(1);
			
			if (cCnt > 100) cCnt = 100;
			// 행 정렬이므로 행 기준으로 다 넣어줌
			int idx = 0;
			while (idx < cCnt) {
				A[i][idx] = numCntFin[idx];
				idx++;
			}
		}
		
	}
	
	static void inputArr(int who) {	// 숫자 등장 횟수 배열을 가져와서 정렬해서 재입력 & 최대 행이나 열 갱신
		PriorityQueue<Num> pq = new PriorityQueue<>((n1, n2) -> n1.howmany == n2.howmany ? n1.num - n2.num : n1.howmany - n2.howmany);
		
		// 정렬
		for (int i = 1; i <= 100; i++) {
			if (numCntArr[i] > 0) {
				pq.offer(new Num(i, numCntArr[i]));
			}
		}
		
		
		// 우선순위 큐에서 다 뽑아서 넣기
		int idx = 0;
		Arrays.fill(numCntFin, 0);
		while (!pq.isEmpty()) {
			Num n = pq.poll();
			numCntFin[idx++] = n.num;
			numCntFin[idx++] = n.howmany;
		}
		
		
		if (who == 0) {	// 행 정렬
			rCnt = Math.max(rCnt, idx);
		} else {	// 열 정렬
			cCnt = Math.max(cCnt, idx);	
		}
	}
	
	static class Num {
		int num, howmany;

		public Num(int num, int howmany) {
			super();
			this.num = num;
			this.howmany = howmany;
		}	
	}
}
