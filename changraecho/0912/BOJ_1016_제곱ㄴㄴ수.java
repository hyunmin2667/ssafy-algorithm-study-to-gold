import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1016_제곱ㄴㄴ수 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1부터 10^6 + 1까지의 모든 소수 찾기
        int upperBound = 1000000 + 1;

        long[] sieve = new long[upperBound + 1];
        for (int i = 1; i <= upperBound; i++) {
            sieve[i] = i;
        }

        int sqrt = (int) Math.sqrt(upperBound) + 1;

        // 소수 찾기
        for (int i = 2; i <= sqrt; i++) {
            if (sieve[i] == 0)
                continue;

            for (int j = i + i; j <= upperBound; j = j + i) {
                sieve[j] = 0;
            }
        }

        // min과 max 정의
        st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        long[] nums = new long[(int) (max - min + 1)];

        for (long i = 0; i <= max - min; i++) {
            nums[(int) i] = 1;
        }

        // 소수의 제곱수의 배수들을 처리
        for (long i = 2; i <= upperBound; i++) {
            if (sieve[(int) i] == 0)
                continue;

            else if (i * i > max)
                break;

            for (long j = i * i * (min % (i * i) == 0 ? min / (i * i) : min / (i * i) + 1); j <= max; j += i * i) {

                nums[(int) (j - min)] = 0;
            }
        }

        int ans = 0;

        for (int i = 0; i <= max - min; i++) {
            if (nums[i] == 1)
                ans++;
        }

        System.out.println(ans);
    }

}
