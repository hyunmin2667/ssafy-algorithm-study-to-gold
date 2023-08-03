import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;

public class BOJ_2023_신기한소수 {
    static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        for (int i = 2; i <= (int) (Math.round(Math.sqrt(n))) + 1; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> hash = new HashMap<Integer, ArrayList<Integer>>();

        // N = 1인 경우
        hash.put(1, new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7)));

        // 1,3,5,7,9
        int[] odds = { 1, 3, 5, 7, 9 };

        // N에 대한 귀납법 사용.
        for (int i = 2; i <= N; i++) {
            ArrayList<Integer> mysteriousPrimes = new ArrayList<Integer>();
            for (int num : hash.get(i - 1)) {
                for (int odd : odds) {
                    if (isPrime(num * 10 + odd)) {
                        mysteriousPrimes.add(num * 10 + odd);
                    }
                }

            }
            hash.put(i, mysteriousPrimes);
        }

        for (int mysteriousPrime : hash.get(N)) {
            sb.append(mysteriousPrime).append("\n");
        }

        System.out.println(sb.toString());

    }
}
