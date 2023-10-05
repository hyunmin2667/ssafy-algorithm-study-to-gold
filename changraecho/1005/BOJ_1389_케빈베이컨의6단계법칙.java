import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1389_케빈베이컨의6단계법칙 {
	static int N;
	static int M;
	static int[][] dp;
	
	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
	}
	
	static int getAnswer() {
		int minBaconNum = 101;
		int studNum = -1;
		
		for (int i = 1; i <= N; i++) {
			int baconNum = 0;
			
			for (int j = 1; j <= N; j++) {
				baconNum += dp[i][j];
			}
			
			if (baconNum < minBaconNum) {
				minBaconNum = baconNum;
				studNum = i;
			}
		}
		
		return studNum;
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				
				dp[i][j] = 101;
				
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			dp[v1][v2] = 1;
			dp[v2][v1] = 1;
		}
		
		floydWarshall();
		System.out.println(getAnswer());

	}

}
