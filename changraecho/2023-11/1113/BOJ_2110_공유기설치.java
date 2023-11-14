import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_공유기설치 {
    static int N;
    static int C;
    static int[] coords; // 길이가 N인 배열

    static boolean greedy(int k) { // 공유기 C개를 설치했을 때, 가장 인접한 두 공유기 사이의 거리가 k보다 크거나 같다면 true. 그렇지 않다면 false
        int num = 1;
        int curIdx = 0;

        while (num < C) {

            boolean flag = false;
            for (int i = curIdx + 1; i < N; i++) {
                if (coords[i] - coords[curIdx] >= k) {
                    num++;
                    curIdx = i;

                    flag = true;
                    break;
                }
            }

            if (!flag)
                return false;

        }

        return true;

    }

    static int binarySearch() {
        int l = 1;
        int r = coords[N - 1] - coords[0];

        while (r - l >= 2) {
            int m = (l + r) / 2;

            if (greedy(m)) {
                l = m;
            } else {
                r = m;
            }

        }

        if (r - l == 1) {
            if (greedy(r))
                return r;
        }

        return l;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        coords = new int[N];

        for (int i = 0; i < N; i++) {
            coords[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coords);

        System.out.println(binarySearch());
    }

}