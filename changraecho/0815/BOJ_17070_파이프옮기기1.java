
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// A형 기출
public class BOJ_17070_파이프옮기기1 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;

        // N 정의
        int N = Integer.parseInt(br.readLine());

        // grid 정의
        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        // dp 초기화

        // dp (p, q, v) 세로 방향:
        int[][] vertical = new int[N][N];

        // dp (p, q, h) 가로 방향
        int[][] horizontal = new int[N][N];

        // dp (p, q, d) 대각선 방향
        int[][] diagonal = new int[N][N];

        // dp 계산

        // 첫 행에 대해 계산
        horizontal[0][1] = 1; // 초기화
        for (int j = 2; j < N; j++) {
            if (grid[0][j] == 0) {
                horizontal[0][j] = horizontal[0][j - 1];
            }
        }

        // 두 번째 행에 대해 계산
        for (int j = 2; j < N; j++) {
            if (grid[1][j] == 0) {
                // dp horizontal
                horizontal[1][j] += horizontal[1][j - 1];

                if (grid[1][j - 2] == 0 && grid[0][j - 1] == 0 && grid[1][j - 1] == 0) {
                    horizontal[1][j] += diagonal[1][j - 1];
                }

                // dp diagonal
                if (grid[1][j - 1] == 0 && grid[0][j] == 0) {
                    diagonal[1][j] += horizontal[0][j - 1];
                }

            }
        }

        // 처음 두 개의 열은 계산할 필요가 없음.

        // 나머지에 대해 계산
        for (int i = 2; i < N; i++) {
            for (int j = 2; j < N; j++) {

                if (grid[i][j] == 0) {
                    // dp vertical 계산
                    vertical[i][j] += vertical[i - 1][j];

                    if (grid[i - 1][j - 1] == 0 && grid[i - 2][j] == 0 && grid[i - 1][j] == 0) {
                        vertical[i][j] += diagonal[i - 1][j];
                    }

                    // dp horizontal 계산
                    horizontal[i][j] += horizontal[i][j - 1];

                    if (grid[i][j - 2] == 0 && grid[i - 1][j - 1] == 0 && grid[i][j - 1] == 0) {
                        horizontal[i][j] += diagonal[i][j - 1];
                    }

                    // dp diagnoal 계산
                    if (grid[i][j - 1] == 0 && grid[i][j - 1] == 0) {
                        diagonal[i][j] += horizontal[i - 1][j - 1] + vertical[i - 1][j - 1];

                    }

                    if (grid[i - 1][j - 2] == 0 && grid[i - 2][j - 1] == 0 && grid[i - 1][j - 1] == 0) {
                        diagonal[i][j] += diagonal[i - 1][j - 1];
                    }

                }

            }
        }

        // 정답 출력
        int ans = 0;
        if (grid[N - 1][N - 1] == 0) {
            ans += horizontal[N - 1][N - 2];

            if (grid[N - 1][N - 3] == 0 && grid[N - 2][N - 2] == 0) {
                ans += diagonal[N - 1][N - 2];
            }

            if (grid[N - 1][N - 2] == 0 && grid[N - 2][N - 1] == 0) {
                ans += horizontal[N - 2][N - 2];

                if (grid[N - 2][N - 3] == 0 && grid[N - 3][N - 2] == 0) {
                    ans += diagonal[N - 2][N - 2];
                }

                ans += vertical[N - 2][N - 2];

            }

            if (grid[N - 2][N - 2] == 0 && grid[N - 3][N - 1] == 0) {
                ans += diagonal[N - 2][N - 1];
            }

            ans += vertical[N - 2][N - 1];
        }

        System.out.println(ans);

    }

}
