import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// A형 기출
public class BOJ_17406_배열돌리기4 {
    static int N, M; // 행과 열의 갯수
    static int[][] grid;
    static int[][] gridCopy; // grid의 원본
    static int[] dx = { 0, 1, 0, -1 }; // 오 아 왼 위
    static int[] dy = { 1, 0, -1, 0 };
    static int K; // 회전 연산의 개수
    static int[] perm; // 길이는 K
    static boolean[] visited; // 길이는 K
    static int[][] orders; // 명령들의 배열
    static int ans = Integer.MAX_VALUE; // 정답

    static void rotate(int x1, int y1, int x2, int y2) { // 좌상단의 좌표 (x1, y1), 우상단의 좌표 (x2, y2)인 직사각형의 둘레를 시계방향으로 회전

        Deque<Integer> dq = new ArrayDeque<>(); // 직사각형의 둘레의 원소들을 보관

        int curX = x1;
        int curY = y1;

        int w = y2 - y1;
        int h = x2 - x1;

        int[] counts = { w, h };

        int d = 0; // 방향
        int c = 0; // counts의 idx;
        int count = counts[c];

        // dq에 원소들 넣기
        while (true) {
            if (d == 4)
                break;
            if (count > 0) {
                curX = curX + dx[d];
                curY = curY + dy[d];
                dq.add(grid[curX][curY]);
                count--;
            } else {
                d++;
                c = (c + 1) % 2;
                count = counts[c];
            }
        }

        // dq의 맨 마지막 원소를 제일 처음으로
        if (!dq.isEmpty()) {
            int last = dq.pollLast();
            dq.addFirst(last);

        }

        d = 0; // 방향
        c = 0; // counts의 idx;
        count = counts[c];

        while (true) {
            if (d == 4)
                break;
            if (count > 0) {
                curX = curX + dx[d];
                curY = curY + dy[d];
                grid[curX][curY] = dq.poll();
                count--;
            } else {
                d++;
                c = (c + 1) % 2;
                count = counts[c];
            }
        }

    }

    static void rotateGrid(int x1, int y1, int x2, int y2) {
        int curX1 = x1;
        int curY1 = y1;
        int curX2 = x2;
        int curY2 = y2;

        int w = curY2 - curY1;
        int h = curX2 - curX1;

        while (Math.min(w, h) >= 1) {
            rotate(curX1, curY1, curX2, curY2);
            curX1++;
            curY1++;
            curX2--;
            curY2--;
            w -= 2;
            h -= 2;
        }

    }

    static void rotateByOrder(int r, int c, int s) {
        rotateGrid(r - s, c - s, r + s, c + s);
    }

    static void generatePerm(int pIdx) { // 0 ~ K -1까지의 수들을 permutate하기
        if (pIdx == K) {

            // gridCopy를 이용해 grid 복구
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    grid[i][j] = gridCopy[i][j];
                }
            }

            // orders의 순서를 고려하여 A의 값 계산
            for (int i = 0; i < K; i++) {
                int r = orders[perm[i]][0];
                int c = orders[perm[i]][1];
                int s = orders[perm[i]][2];
                rotateByOrder(r, c, s);
            }

            ans = Math.min(ans, computeValue());

            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i])
                continue;

            perm[pIdx] = i;
            visited[i] = true;
            generatePerm(pIdx + 1);
            visited[i] = false;
        }

    }

    static int computeValue() { // grid의 값: 행들의 수의 합의 최솟값
        int ans = Integer.MAX_VALUE;
        int rowSum;

        for (int i = 1; i <= N; i++) {
            rowSum = 0;
            for (int j = 1; j <= M; j++) {
                rowSum += grid[i][j];
            }
            ans = Math.min(ans, rowSum);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        // N, M, K
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // visited, perm 초기화
        visited = new boolean[K];
        perm = new int[K];

        // gridCopy 초기화 및 정의
        gridCopy = new int[N + 1][M + 1]; // 첫째 행 및 열은 dummy

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                gridCopy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // grid 초기화
        grid = new int[N + 1][M + 1];

        // 명령
        orders = new int[K][];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            orders[i] = new int[] { r, c, s };
        }

        generatePerm(0);

        System.out.println(ans);

    }

}
