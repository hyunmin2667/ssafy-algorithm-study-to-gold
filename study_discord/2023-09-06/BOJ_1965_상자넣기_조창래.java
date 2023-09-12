import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1965_상자넣기_조창래 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] boxes = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			boxes[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (boxes[i] > boxes[j]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
					ans = Math.max(ans, dp[i]);
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
