import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {
    static int N; // 세로선의 개수. 가로의 길이
    static int H; // 세로의 길이
    static int M; // 가로선의 개수
    static boolean[][] edge; // edge[i][j] = true: j번째 세로선과 j + 1번째 세로선을 i번 점선 위치에서 연결. H * (N - 1) 행렬
    static int res = -1;

    static boolean gameResult() { // i = 1, ..., N일때, 모든 i에 대해 i번 세로선의 결과가 i라면 true
        for (int i = 1; i <= N; i++) {
            if (start(i) != i)
                return false;
        }

        return true;

    }

    static int start(int i) { // i번 세로선에서 출발했을 때의 결과
        int curX = 0;
        int curY = i;

        while (curX <= H) {
            // 현재 좌표에서 오른쪽 방향으로의 가로선이 있다면, 오른쪽으로 이동 후 아래로 이동
            if (curY <= N - 1 && edge[curX][curY]) {
                curY++;
                curX++;
            }

            // 현재 좌표에서 왼쪽 방향으로의 가로선이 있다면, 왼쪽으로 이동 후 아래로 이동
            else if (curY - 1 >= 1 && edge[curX][curY - 1]) {
                curY--;
                curX++;
            }

            // 위의 두 경우가 아니라면 아래로 내려감
            else {
                curX++;
            }

        }

        return curY;

    }

    static void backtrack(int cnt, int addedEdgesNum) { // edge를 addedEdgesNum만큼 추가하는 경우를 모두 고려
        if (cnt == addedEdgesNum) {
            // 사다리 게임 실행
            // 만약 잘 조작되었다면 결과를 조정한다
            if (gameResult()) {
                res = addedEdgesNum;
            }

            // return
            return;
        }

        // edge를 놓는다
        // backtrack
        // edge를 없앤다

        // edge를 놓을 수 있는 모든 위치에 대해,
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N - 1; j++) {
                if (edge[i][j])
                    continue; // edge가 이미 놓여져 있다면 continue
                else if (j - 1 >= 1 && edge[i][j - 1])
                    continue; // 이 위치의 왼쪽에 edge가 놓여져 있다면 continue
                else if (j + 1 <= N - 1 && edge[i][j + 1])
                    continue; // 이 위치의 오른쪽에 edge가 놓여져 있다면 continue

                edge[i][j] = true;
                backtrack(cnt + 1, addedEdgesNum);
                edge[i][j] = false;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        edge = new boolean[H + 1][N];
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            edge[i][j] = true;
        }

        for (int i = 0; i <= 3; i++) {
            backtrack(0, i);
            if (res == i) {
                System.out.println(res);
                return;
            }
        }

        // res 출력
        System.out.println(res);
    }

}