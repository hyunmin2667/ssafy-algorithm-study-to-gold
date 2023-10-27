import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	static int r, c, k;
	static int[][] matrix; // 100 * 100 행렬
	static int rowLen = 3; // 행의 길이
	static int colLen = 3; // 열의 길이
	static int arrLen; // 배열의 실제 길이

	// 주어진 배열 (길이가 100)을 정렬한 결과를 return한다
	static int[] sortArr(int[] arr) {
		HashMap<Integer, Integer> hash = new HashMap<>();

		for (int elem: arr) {
			if (elem == 0) continue;

			if (hash.containsKey(elem)) {
				hash.put(elem, hash.get(elem) + 1);
			} else {
				hash.put(elem, 1);
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> {
			if (hash.get(n1) < hash.get(n2)) {
				return -1;
			} else if (hash.get(n1) > hash.get(n2)) {
				return 1;
			} else if (n1 < n2) {
				return -1;
			} else if (n1 > n2) {
				return 1;
			}

			return 0;

		});

		Set<Integer> hashSet = hash.keySet();
		arrLen = Math.min(100, hashSet.size() * 2);

		for (int elem: hashSet) {
			pq.offer(elem);
		}

		int[] res = new int[100];

		for (int i = 0; i < 99; i = i + 2) {
			if (!pq.isEmpty()) {
				int polled = pq.poll();
				res[i] = polled;
				res[i + 1] = hash.get(polled);
			} else {
				break;
			}
		}



		return res;

	}


	// R연산을 시행한다
	static void rOperation() {
		colLen = 0;
		// 각 행을 정렬한다
		for (int i = 0; i < 100; i++) {
			matrix[i] = sortArr(matrix[i]);
			// colLen를 갱신한다
			colLen = Math.max(colLen, arrLen);
		}
	}

	// 100 * 100 행렬을 transpose한다
//	static void transpose() {
//		int[][] res = new int[100][100];
//
//		for (int i = 0; i < 100; i++) {
//			for (int j = 0; j < 100; j++) {
//				res[i][j] = matrix[j][i];
//			}
//		}
//
//		for (int i = 0; i < 100; i++) {
//			for (int j = 0; j  < 100; j++) {
//				matrix[i][j] = res[i][j];
//			}
//		}
//
//
//	}



	// C연산을 시행한다
	static void cOperation() {
		rowLen = 0;

		// 각 열을 정렬한다


		for (int j = 0; j < 100; j++) {
			int[] col = new int[100]; // j번째 열

			for (int i = 0; i < 100; i++) {
				col[i] = matrix[i][j];
			}

			col = sortArr(col); // j번재 열을 정렬한다
			rowLen = Math.max(rowLen, arrLen);

			// j번째 열을 바꾼다
			for (int i = 0; i < 100; i++) {
				matrix[i][j] = col[i];
			}
		}


	}


	// 알고리즘
	static void runAlgo() {
		for (int i = 0; i <= 100; i++) {
			// matrix[r][c] = k인지 확인: 만약 그렇다면 syso(i), break
			if (matrix[r][c] == k) {
				System.out.println(i);
				return;
			}

			// 행과 열의 크기를 비교해서 어떤 연산을 택할 지 결정한다

			// 행의 개수 >= 열의 개수: R 연산을 시행
			if (rowLen >= colLen) {
				rOperation();
			}

			// else: C 연산을 시행
			else {
				cOperation();
			}

		}

		System.out.println(-1);
	}

	static void draw() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}



	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		matrix = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		runAlgo();



		// 주어진 행과 열의 index 주의

	}

}
