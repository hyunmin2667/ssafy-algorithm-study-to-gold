import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {
    static int N;
    static int M; // 총 예산
    static int budgets[]; // 지방 예산의 배열. 길이가 N
    static int maxBudget; // 지방 예산의 최댓값
    static int budgetSum; // 지방 예산의 총합

    static int getBudget(int upperBound) { // 상한액이 주어졌을 때 예산 계산
        int res = 0;

        for (int b : budgets) {
            if (b <= upperBound) {
                res += b;
            } else {
                res += upperBound;
            }
        }

        return res;

    }

    static int binarySearch() { // 이분 탐색을 통해 getBudget(upperBound) <= M인 (upperBound)의 최댓값을 구한다
        int l = 1;
        int r = maxBudget;

        while (r - l >= 2) {
            int m = (l + r) / 2;

            if (getBudget(m) > M) {
                r = m;
            } else {
                l = m;
            }

        }

        if (getBudget(r) <= M) {
            return r;
        } else {
            return l;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        budgets = new int[N];
        maxBudget = 0;

        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
            maxBudget = Math.max(maxBudget, budgets[i]);
        }

        M = Integer.parseInt(br.readLine());

        System.out.println(binarySearch());

    }

}
