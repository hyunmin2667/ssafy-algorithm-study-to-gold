import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2579_계단오르기 {
    static int N;
    static int[] points;
    static long[] dp = new long[301];

    static long getDp() {
        long ans = 0;

        if (N == 1) {
            return points[1];
        } else if (N == 2) {
            return points[1] + points[2];
        } else if (N == 3) {
            return points[3] + Math.max(points[1], points[2]);
        }

        dp[1] = points[1];
        dp[2] = points[1] + points[2];
        dp[3] = points[3] + Math.max(points[1], points[2]);

        for (int i = 4; i <= N; i++) {
            dp[i] = points[i] + (Math.max(points[i - 1] + dp[i - 3], dp[i - 2]));
        }

        return dp[N];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        points = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getDp());

    }

}
