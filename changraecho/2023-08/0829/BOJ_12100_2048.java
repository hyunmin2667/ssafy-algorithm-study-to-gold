import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100_2048 {
    static int N;
    static int[][] grid; // N * N 보드.
    static int[][] gridCopy;
    static boolean[][] combined; // combined[i][j] = true <=> (i, j)좌표의 블록은 합쳐진 블록이다.
    static int[] dx = { -1, 0, 1, 0 }; // 위 오 아 왼
    static int[] dy = { 0, 1, 0, -1 };
    static char[] dirs = { 'U', 'R', 'D', 'L' };
    static char[] target = new char[5]; // 'R', 'D', 'L', 'U'로 구성된 길이가 5인 배열
    static long ans = 0;

    static void generateTarget(int idx) {
        if (idx == 5) {

            grid = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = gridCopy[i][j];
                }
            }

            // target을 이용해 grid를 움직인다
            for (int i = 0; i < 5; i++) {
                moveGrid(target[i]);
            }

            // 필요하다면 정답을 수정한다
            ans = Math.max(ans, getLargestNum());

            return;
        }

        for (int i = 0; i < 4; i++) {
            target[idx] = dirs[i];
            generateTarget(idx + 1);
        }
    }

    static void moveGrid(char dir) {
        if (dir == 'U') {
            moveU();
        } else if (dir == 'R') {
            moveR();
        } else if (dir == 'D') {
            moveD();
        } else {
            moveL();
        }
    }

    static long getLargestNum() {
        long res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }

        return res;
    }

    static void moveR() { // grid를 오른쪽으로 기울이기
        combined = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int j = N - 1; j > -1; j--) {
                // 각 블록을 오른쪽으로 이동
                moveBlock(row, j, 1);
            }
        }
    }

    static void moveL() { // grid를 왼쪽으로 기울이기
        combined = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int j = 0; j < N; j++) {
                moveBlock(row, j, 3);
            }
        }

    }

    static void moveU() { // grid를 위쪽으로 기울이기
        combined = new boolean[N][N];

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                moveBlock(i, j, 0);
            }
        }

    }

    static void moveD() { // grid를 아래쪽으로 기울이기
        combined = new boolean[N][N];

        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > -1; i--) {
                moveBlock(i, j, 2);
            }
        }

    }

    static void moveBlock(int x, int y, int d) {
        int cx = x;
        int cy = y;
        int nx;
        int ny;

        while (true) {
            nx = cx + dx[d];
            ny = cy + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 다음 점이 사각형을 벗어나는 경우
                break;
            }

            else if (grid[nx][ny] == 0) { // 다음 칸이 비어있는 경우
                grid[nx][ny] = grid[cx][cy]; // 블록 이동
                grid[cx][cy] = 0;
            } else if (grid[nx][ny] != grid[cx][cy]) { // 다음 칸의 블록의 숫자와 (cx, cy)좌표의 블록의 숫자가 다른 경우
                break;
            } else if (grid[nx][ny] == grid[cx][cy] && combined[nx][ny]) { // 다음 칸의 블록의 숫자와 (cx, cy) 좌표의 블록의 숫자가 같고, 다음
                                                                           // 칸의 블록이 이미 합쳐졌을 때
                break;
            } else { // 다음 칸의 블록의 숫자와 (cx, cy) 좌표의 블록의 숫자가 같고, 다음 칸이 합쳐지지 않은 경우
                grid[nx][ny] = 2 * grid[nx][ny];
                grid[cx][cy] = 0;
                combined[nx][ny] = true;
                break;
            }

            // cx, cy 변경
            cx = nx;
            cy = ny;
        }

    }

    static void generateGrid(int[] numbers) {
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = numbers[idx++];
            }
        }
    }

    static void draw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        gridCopy = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                gridCopy[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        generateTarget(0);

        System.out.println(ans);

    }

}
