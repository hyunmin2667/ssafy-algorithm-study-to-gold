import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2410_2의멱수의합 {
	static int N;
	static int[] dp;
	static int FIXED = 1000000000;

	static void induction(int n) { // dp[n] 구하기
		int res = dp[n - 1];
		res %= FIXED;

		int temp = n;
		while (temp % 2 == 0) {
			temp = temp / 2;
			res += dp[temp - 1];
			res %= FIXED;
		}

		dp[n] = res;

	}

	static void getDp() {
		dp[0] = 1;

		for (int i = 1; i <= N; i++) {
			induction(i);
		}

		System.out.println(dp[N]);
	}

	static void draw() {
		System.out.println(Arrays.toString(dp));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		getDp();



	}

}
