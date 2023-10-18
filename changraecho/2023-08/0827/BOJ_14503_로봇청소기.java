import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
    static int N, M;
    static char[][] grid;
    static boolean[][] cleaned;
    static int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
    static int[] dy = { 0, 1, 0, -1 };
    static int startX, startY, startDir; // 로봇 청소기의 처음 위치와 처음 방향

    // 한 좌표의 주변 네 칸 중 빈칸이 모두 청소되어 있다
    static boolean fourCleaned(int x, int y) {

        int nx, ny;

        for (int d = 0; d < 4; d++) {
            nx = x + dx[d];
            ny = y + dy[d];

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (grid[nx][ny] == '0' && !cleaned[nx][ny])
                    return false;
            }

        }

        return true;
    }

    // 로봇 청소기가 움직인다
    static void clean() {
        int x = startX;
        int y = startY;
        int dir = startDir;

        int nx, ny;
        while (true) {
            cleaned[x][y] = true;

            if (fourCleaned(x, y)) { // 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
                // 후진할 수 있다면 후진
                nx = x + dx[(dir + 2) % 4];
                ny = y + dy[(dir + 2) % 4];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] == '0') {
                    x = nx;
                    y = ny;
                } else { // 그렇지 않다면 break
                    break;
                }
            }

            else {
                dir = (dir + 3) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] == '0' && !cleaned[nx][ny]) {
                    x = nx;
                    y = ny;
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startDir = Integer.parseInt(st.nextToken());

        grid = new char[N][M];
        cleaned = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = st.nextToken().charAt(0);
            }
        }

        clean();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cleaned[i][j])
                    ans++;
            }
        }

        System.out.println(ans);

    }

}
