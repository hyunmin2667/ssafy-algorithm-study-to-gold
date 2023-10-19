
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_1300_K번째수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;

		// N, K 정의.
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		// 임의의 자연수 m에 대해, (B[K] > m)의 참거짓은 O(N) 시간내에 확인할 수 있다. 그러므로 이진탐색을 활용하자.
		long l = 1;
		long r = K;
		long m;

		long count; // m보다 작거나 같은 A의 원소들의 갯수
		int row; // A의 행
		long ans = 0; // 문제의 정답

		while (r - l >= 0) {
			m = (l + r) / 2;

			// B[K] > m <=> m보다 작거나 같은 A의 원소들의 갯수는 K보다 작다.

			count = 0; // m보다 작거나 같은 A의 원소들의 갯수
			row = 1;
			while (true) {
				if (row <= m && row <= N) {
					count += Math.min((m / row), N);
					row++;
				} else {
					break;
				}
			}

			if (count < K) { // B[K] > m이라면 l = m + 1
				l = m + 1;
			} else { // B[K] <= m이라면 r = m - 1
				ans = m;
				r = m - 1;
			}

		}

		System.out.println(ans);

	}
}
