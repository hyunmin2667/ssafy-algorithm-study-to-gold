import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2294_동전2 {
    static int N; // N가지 종류의 동전
    static int K; // 합이 K가 되어야 한다
    static int[] price; // price[i]: i번째 동전의 가격
    static int[] dp; // dp[i]: i원을 만들기 위해 필요한 동전들의 최소 개수

    static void getDp() {
        dp[0] = 0;
        // dp[i] 정의하기
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (i < price[j])
                    continue;
                dp[i] = Math.min(dp[i], dp[i - price[j]] + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        price = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(br.readLine());
        }

        // dp의 값들을 10001로 초기화
        dp = new int[K + 1];
        for (int i = 0; i <= K; i++) {
            dp[i] = 10001;
        }

        getDp();

        // 만약 dp[N][K]이 무한대라면 -1, 그렇지 않다면 dp[N][K]를 출력한다
        if (dp[K] == 10001) {
            System.out.println(-1);
            return;
        } else {
            System.out.println(dp[K]);
        }

    }

}
