import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
    static int N;
    static int[][] grid;
    static int[][] gridCopy;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static boolean[][] filled; // 잠긴 위치들
    static int maxHeight;
    static int ans;

    static int getSafeAreaNum(int h) { // 잠긴 위치들이 주어졌을 때, 안전 영역의 개수 구하기
        // grid 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = gridCopy[i][j];
            }
        }

        int res = 0;
        // 각 좌표에 대해
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] > h && grid[i][j] != -1) {
                    grid[i][j] = -1;
                    dfs(i, j, h);
                    res++;
                }
            }
        }

        return res;
    }

    // TODO 하나의 안전영역에 포함된 위치들 모두 체크하기
    static void dfs(int i, int j, int h) {

        int ni, nj;

        for (int dir = 0; dir < 4; dir++) {
            ni = i + dx[dir];
            nj = j + dy[dir];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N)
                continue;

            if (grid[ni][nj] == -1)
                continue;
            if (grid[ni][nj] <= h)
                continue;

            grid[ni][nj] = -1;
            dfs(ni, nj, h);

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        grid = new int[N][N];
        gridCopy = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                gridCopy[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, gridCopy[i][j]);
            }
        }

        // maxHeight 기록

        ans = 0;
        // 높이가 1부터 maxHeight -1까지 안전영역의 개수를 계산한다
        for (int h = 0; h < maxHeight; h++) {
            ans = Math.max(ans, getSafeAreaNum(h));
        }

        // 안전영역의 개수 중 최대값을 return한다
        System.out.println(ans);

    }
}