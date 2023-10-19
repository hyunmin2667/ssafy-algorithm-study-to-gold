import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465_스티커 {
    static int T;
    static int N;
    static int[][] stickers;
    static int[][] dp;

    static int getMaxPoint() {
        dp[0][1] = stickers[0][1];
        dp[1][1] = stickers[1][1];

        for (int j = 2; j <= N; j++) {
            dp[0][j] = Math.max(stickers[0][j] + Math.max(dp[0][j - 2], dp[1][j - 2]), stickers[0][j] + dp[1][j - 1]);
            dp[1][j] = Math.max(stickers[1][j] + Math.max(dp[0][j - 2], dp[1][j - 2]), stickers[1][j] + dp[0][j - 1]);
        }

        // draw();

        return Math.max(dp[0][N], dp[1][N]);
    }

    static void draw() {
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            stickers = new int[2][N + 1];
            dp = new int[2][N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                stickers[0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                stickers[1][i] = Integer.parseInt(st.nextToken());
            }

            sb.append(getMaxPoint()).append("\n");
        }

        System.out.println(sb);

    }

}
