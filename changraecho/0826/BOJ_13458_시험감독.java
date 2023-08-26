import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] testTakers = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            testTakers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans++;
            if (testTakers[i] - B <= 0)
                continue;

            ans += (testTakers[i] - B) / C;
            if ((testTakers[i] - B) % C > 0) {
                ans++;
            }
        }

        System.out.println(ans);

    }

}
