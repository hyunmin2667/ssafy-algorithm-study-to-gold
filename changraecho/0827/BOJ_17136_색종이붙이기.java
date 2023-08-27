import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {

    static int minNum = Integer.MAX_VALUE; // 색종이를 붙이기 위해 필요한 최소한의 종이 수
    static char[][] grid = new char[10][10];
    static int[] squares = { 0, 5, 5, 5, 5, 5 }; // squares[i]는 i * i square의 사용할 수 있는 갯수

    // 점 (x, y)로부터 i * i square을 칠할 수 있는 지 판별
    static boolean canCover(int x, int y, int i) {
        if (grid[x][y] == '0')
            return false;

        if (x + i - 1 >= 10 || y + i - 1 >= 10)
            return false;

        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                if (grid[x + j][y + k] == '0')
                    return false;
            }
        }

        return true;
    }

    // 점 (x, y)로부터 i * i square을 칠한다
    static void cover(int x, int y, int i) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                grid[x + j][y + k] = '0';
            }
        }

    }

    // 점 (x, y)로부터 칠해진 i * i square을 걷어낸다
    static void uncover(int x, int y, int i) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                grid[x + j][y + k] = '1';
            }
        }
    }

    // backtrack
    static void backtrack(int x, int y, int squareNum) {
        if (squareNum >= minNum)
            return;

        if (x == 10) {
            minNum = squareNum;
            return;
        }

        if (grid[x][y] == '0') {
            if (y == 9) {
                backtrack(x + 1, 0, squareNum);
            } else {
                backtrack(x, y + 1, squareNum);
            }
        }

        else { // grid[x][y] = 1인 경우
            for (int i = 1; i <= 5; i++) {
                if (squares[i] > 0 && canCover(x, y, i)) {

                    cover(x, y, i);
                    squares[i]--;

                    if (y == 9) {
                        backtrack(x + 1, 0, squareNum + 1);
                    } else {
                        backtrack(x, y + 1, squareNum + 1);
                    }
                    squares[i]++;
                    uncover(x, y, i);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                grid[i][j] = st.nextToken().charAt(0);
            }
        }

        backtrack(0, 0, 0);

        System.out.println(minNum == Integer.MAX_VALUE ? -1 : minNum);

    }

}
