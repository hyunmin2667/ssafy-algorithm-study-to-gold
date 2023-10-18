import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305_주유소 {
    static int N;
    static long[] gasPrice; // 길이가 N + 1인 배열(0은 dummy)
    static long[] dist; // dist[i]: i번째 station과 i + 1번째 station 사이의 거리. 길이가 N + 1인 배열(0은 dummy)

    static long getAns() {
        long res = 0;
        long price = Long.MAX_VALUE;

        for (int i = 1; i <= N - 1; i++) {
            price = Math.min(price, gasPrice[i]);
            res += price * dist[i];
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dist = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        gasPrice = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            gasPrice[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getAns());

    }

}
