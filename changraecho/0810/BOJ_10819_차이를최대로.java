
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

// do it 교재의 문제는 아님.
public class BOJ_10819_차이를최대로 {
    static int N;
    static int[] arr, permutation;
    static boolean[] visited;
    static int ans;
    static int sum;

    static void generatePerm(int idx) {
        if (idx == N) {
            // 식의 값을 계산, 필요하다면 ans를 재정의
            sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(permutation[i] - permutation[i + 1]);
            }

            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                permutation[idx] = arr[i];
                visited[i] = true;
                generatePerm(idx + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;

        // N 정의
        N = Integer.parseInt(br.readLine());

        // arr, visited 정의
        arr = new int[N];
        str = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        visited = new boolean[N];

        // permutation 초기화
        permutation = new int[N];

        generatePerm(0);

        System.out.println(ans);

    }
}
