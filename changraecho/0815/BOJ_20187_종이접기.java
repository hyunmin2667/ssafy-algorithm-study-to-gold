import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20187_종이접기 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
        StringBuilder sb = new StringBuilder();

        // k 정의
        int k = Integer.parseInt(br.readLine());

        // 명령어들 정의
        str = new StringTokenizer(br.readLine());
        String[] orders = new String[2 * k];

        for (int i = 0; i < 2 * k; i++) {
            orders[i] = str.nextToken();
        }

        // 정답 초기화
        int[][] ans = new int[(int) Math.pow(2, k)][(int) Math.pow(2, k)];

        // 구멍 숫자 초기화
        int h = Integer.parseInt(br.readLine());

        // 1 * 1 정사각형의 좌표 구하기
        int xL = 0;
        int xR = (int) Math.pow(2, k) - 1;
        int xCoord = 0;

        int yL = 0;
        int yR = (int) Math.pow(2, k) - 1;
        int yCoord = 0;

        for (String s : orders) {
            if (s.equals("R")) {
                yL = (yL + yR + 1) / 2;
                yCoord = yL;
            } else if (s.equals("D")) {
                xL = (xL + xR + 1) / 2;
                xCoord = xL;
            } else if (s.equals("L")) {
                yR = (yL + yR) / 2;
                yCoord = yR;
            } else if (s.equals("U")) {
                xR = (xL + xR) / 2;
                xCoord = xR;
            }
        }

        // 좌표에 h 입력
        System.out.println(xCoord + " " + yCoord);
        ans[xCoord][yCoord] = h;

        // 좌표의 위-아래 방향으로
        int[] dx = { -1, 1 };
        int px, nx;

        for (int i = 0; i < 2; i++) {
            px = xCoord;
            nx = xCoord + dx[i];
            while (true) {
                if (0 <= nx && nx < (int) Math.pow(2, k)) {

                    // ans[nx][yCoord] 정의
                    if (ans[px][yCoord] == 0 || ans[px][yCoord] == 1) {
                        ans[nx][yCoord] = ans[px][yCoord] + 2;
                    } else {
                        ans[nx][yCoord] = ans[px][yCoord] - 2;
                    }

                    // px 재정의
                    px = nx;
                    // nx 재정의
                    nx = nx + dx[i];

                } else {
                    break;
                }
            }
        }

        // 좌표의 왼-오 방향으로
        int[] dy = { -1, 1 };
        int py, ny;

        for (int i = 0; i < (int) Math.pow(2, k); i++) {
            for (int j = 0; j < 2; j++) {
                py = yCoord;
                ny = yCoord + dy[j];

                while (true) {
                    if (0 <= ny && ny < (int) Math.pow(2, k)) {
                        if (ans[i][py] == 0 || ans[i][py] == 2) {
                            ans[i][ny] = ans[i][py] + 1;
                        } else {
                            ans[i][ny] = ans[i][py] - 1;
                        }

                        // py 재정의
                        py = ny;

                        // ny 재정의
                        ny = ny + dy[j];

                    } else {
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < (int) Math.pow(2, k); i++) {

            for (int j = 0; j < (int) Math.pow(2, k); j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

}
