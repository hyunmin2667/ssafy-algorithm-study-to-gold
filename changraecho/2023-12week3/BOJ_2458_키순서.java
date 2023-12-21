package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2458_키순서 {
	static int N;
	static int M;
	static int[][] dp;
	
	static int getTallerNum(int v) {
		int res = 0;
		
		for (int i = 1; i <= N; i++) {
			if (i == v) continue;
			if (dp[i][v] == 1) res++;
		}
		
		return res;
	}
	
	static int getSmallerNum(int v) {
		int res = 0;
		
		for (int i = 1; i <= N; i++) {
			if (i == v) continue;
			if (dp[v][i] == 1) res++;
		}
		return res;
	}
	
	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dp[i][j] == 0) {
						dp[i][j] = dp[i][k] * dp[k][j];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			dp[v1][v2] = 1;
		}
		
		floydWarshall();
		
		int ans = 0;
		
		for (int i = 1; i <= N; i++) {
			if (getTallerNum(i) + getSmallerNum(i) == N - 1) ans++;
		}
		
//		System.out.println(getSmallerNum(4));
//		System.out.println(getTallerNum(4));
		
		//System.out.println(Arrays.deepToString(dp));
		
		System.out.println(ans);
		

	}

}
