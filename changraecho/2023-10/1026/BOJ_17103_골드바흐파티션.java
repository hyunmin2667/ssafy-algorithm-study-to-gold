import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17103_골드바흐파티션 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int upperBound = 1000000;
        int[] eratosthenes = new int[upperBound]; // 에라토스테네스

        for (int i = 2; i < upperBound; i++) {
            eratosthenes[i] = i;
        }

        for (int i = 2; i < upperBound; i++) {
            if (eratosthenes[i] == 0)
                continue;

            for (int j = i + i; j < upperBound; j = j + i) {
                eratosthenes[j] = 0;
            }
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int ans = 0;
            if (N - 2 >= 0 && eratosthenes[N - 2] > 0) {
                ans++;
            }
            for (int n = 3; n <= N / 2; n += 2) {
                if (eratosthenes[n] > 0 && eratosthenes[N - n] > 0) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");

        }

        System.out.println(sb);

    }

}
