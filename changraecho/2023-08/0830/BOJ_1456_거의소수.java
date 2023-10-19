import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1456_거의소수 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int upperBound = (int) Math.floor(Math.sqrt(B)) + 1;

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

        long ans = 0;

        for (int i = 2; i < upperBound; i++) {
            if (eratosthenes[i] == 0)
                continue;

            ans += Math.floor(Math.log(B) / Math.log(eratosthenes[i]))
                    - Math.max(Math.ceil(Math.log(A) / Math.log(eratosthenes[i])), 2) + 1;
        }

        System.out.println(ans);
    }

}
