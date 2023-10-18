
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1138_한줄로서기 {
    static int N;
    static int[] target; // 1에서 N까지의 배열들을 모두 나열하기
    static int[] tallerPplLeft; // i번째 index: 키가 i인 사람보다 키가 크고, 그 사람 보다 왼쪽에 위치한 사람의 수
    static int oneIdx; // 키가 1인 사람이 놓여진 index
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void perm(int targetIdx) {
        // System.out.println(Arrays.toString(target));

        // 가지치기. 현재 target[targetIdx - 1]까지 정의된 상태이다
        if (targetIdx - 1 == 0) { // target[0]만 정의된 경우
            if (tallerPplLeft[target[0]] != 0)
                return;
        }

        else if (targetIdx - 1 > 0) {

            // target[targetIdx - 1]보다 크면서 index가 targetIdx -1보다 작은 것들의 개수를 직접 센다
            int tallerPplNum = 0;

            for (int i = 0; i < targetIdx - 1; i++) {
                if (target[i] > target[targetIdx - 1])
                    tallerPplNum++;
            }

            if (tallerPplNum != tallerPplLeft[target[targetIdx - 1]])
                return;

        }

        // targetIdx가 oneIdx인 경우, tagetIdx + 1로 넘어간다
        if (targetIdx == oneIdx) {
            perm(targetIdx + 1);
            return;
        }

        if (targetIdx == N) {
            for (int i = 0; i < N; i++) {
                sb.append(target[i]).append(" ");
            }

            System.out.println(sb);
            return;
        }

        // target[targetIdx]를 정의한다
        // perm(targetIdx + 1)을 정의한다
        for (int i = 1; i <= N; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            target[targetIdx] = i;
            perm(targetIdx + 1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        target = new int[N];
        tallerPplLeft = new int[N + 1];
        visited = new boolean[N + 1];
        visited[0] = true;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tallerPplLeft[i] = Integer.parseInt(st.nextToken());
        }

        oneIdx = tallerPplLeft[1];
        target[oneIdx] = 1;
        visited[1] = true;

        perm(0);

    }

}
