import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11689_오일러파이함수 {

    static class PrimePower {
        int p, e;

        public PrimePower(int p, int e) {
            super();
            this.p = p;
            this.e = e;
        }

        @Override
        public String toString() {
            return "PrimePower [p=" + p + ", e=" + e + "]";
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1부터 10^6 + 1까지의 모든 소수 찾기
        int upperBound = 1000000;

        long[] sieve = new long[upperBound + 1];
        for (int i = 1; i <= upperBound; i++) {
            sieve[i] = i;
        }

        int sqrt = (int) Math.sqrt(upperBound) + 1;

        for (int i = 2; i <= sqrt; i++) {
            if (sieve[i] == 0)
                continue;

            for (int j = i + i; j <= upperBound; j = j + i) {
                sieve[j] = 0;
            }
        }

        // 소인수분해하기
        long N = Long.parseLong(br.readLine());
        List<PrimePower> factorization = new ArrayList<>();

        int exp; // 지수
        for (int i = 2; i <= (int) (Math.sqrt(N) + 1); i++) {
            if (sieve[i] == 0)
                continue;

            if (N % i == 0) {
                exp = 1;
                N = N / i;
                while (N % i == 0) {
                    N = N / i;
                    exp++;
                }

                factorization.add(new PrimePower(i, exp));
            }

        }

        // Euler phi function 계산하기

        long ans = 1;
        for (PrimePower pp : factorization) {
            ans *= (Math.pow(pp.p, pp.e) - Math.pow(pp.p, pp.e - 1));
        }

        if (N > 1) {
            ans *= (N - 1);
        }

        System.out.println(ans);
    }

}
