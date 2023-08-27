import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {

    static int N; // 남은 N일
    static boolean[] calendars; // 0 ~ N -1일 중 상담이 가능한 날짜들은 true, 그렇지 않은 날짜들은 false로
    static boolean[] target; // target[i] = true <=> i번째 사람을 상담한다.
    static int[] costs; // 상담 시 받을 수 있는 금액.
    static int[] durations; // 상담 시 걸리는 시간.
    static int ans = 0;

    static boolean canConsult(int i) { // i번째 사람과 상담 가능하다

        // i + T_i - 1 <= N - 1이어야 한다
        if (i + durations[i] - 1 >= N)
            return false;

        // calendars[i], calendars[i+1], ..., calendars[i + T_i - 1]이 모두 false이어야 한다
        for (int idx = i; idx < i + durations[i]; idx++) {
            if (calendars[i])
                return false;
        }

        return true;
    }

    static void backtrack(int tIdx) {
        if (tIdx == N) {
            // 금액 계산 및 정답 수정
            ans = Math.max(ans, computeCost());

            return;
        }

        // tIdx번째 사람과 상담이 가능하다면 상담한다
        if (canConsult(tIdx)) {
            target[tIdx] = true;
            // calendars 칠하기
            for (int idx = tIdx; idx < tIdx + durations[tIdx]; idx++) {
                calendars[idx] = true;
            }

            backtrack(tIdx + 1);

            // calendars 원상복기
            for (int idx = tIdx; idx < tIdx + durations[tIdx]; idx++) {
                calendars[idx] = false;
            }
        }

        // tIdx번째 사람과 상담하지 않는다
        target[tIdx] = false;
        backtrack(tIdx + 1);
    }

    static int computeCost() {
        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (target[i]) {
                ans += costs[i];
            }
        }

        return ans;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        costs = new int[N];
        durations = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            durations[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }

        calendars = new boolean[N];
        target = new boolean[N];
        backtrack(0);

        System.out.println(ans);

    }

}
