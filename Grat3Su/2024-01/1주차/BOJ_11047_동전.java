import java.io.*;
import java.util.*;

public class BOJ_11047_coin {
    static int N, K;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (A[i] <= K) {//현재 가치가 K보다 작거나 같다
                cnt += (K / A[i]);
                K = K % A[i];
            }
        }
        System.out.println(cnt);
    }
}
