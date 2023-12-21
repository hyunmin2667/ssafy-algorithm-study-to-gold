package Algorithm_2023.src.month12.day21;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_7682_틱택토 {

    static char[] board;
    static int cntX, cntO;
    static boolean bingoX, bingoO;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.charAt(0) == 'e') break; // "end"
            board = input.toCharArray();
            cntX = cntO = 0;
            // X, O 돌의 개수 세기
            for (int i = 0; i < 9; i++) {
                if (board[i] == 'X') cntX++;
                else if (board[i] == 'O') cntO++;
            }

            if (cntX - cntO != 0 && cntX - cntO != 1) {
                sb.append("invalid").append("\n");
                continue;
            }

            bingoX = isBingo('X');
            bingoO = isBingo('O');
            if (bingoX && bingoO) {
                sb.append("invalid").append("\n");
                continue;
            }

            if (cntX + cntO != 9 && !bingoX && !bingoO) {
                sb.append("invalid").append("\n");
                continue;
            }

            if (bingoX && cntO == cntX) {
                sb.append("invalid").append("\n");
                continue;
            }

            if (bingoO && cntX > cntO) {
                sb.append("invalid").append("\n");
                continue;
            }

            sb.append("valid").append("\n");
        }
        // 1. X 먼저
        // 2. 가로, 세로, 대각선 3칸이면 게임 끝
        // 3. 게임판 꽉차도 게임 끝

        // 불가능한 경우
        // 1. 둘다 빙고인 경우
        // 3. O가 빙고인데 X의 돌 개수가 더 많은 경우
        // 4. X가 빙고인데 O의 개수가 X와 같은 경우
        // 5. (X의 개수) - (O의 개수) 가 0 또는 1이 아닌 경우
        // 6. 빈칸이 존재하는데 빙고가 없이 게임이 끝난 경우
        System.out.println(sb);
    }

    static boolean isBingo(char c) {
        return  c == board[0] && c == board[1] && c == board[2]
            ||  c == board[3] && c == board[4] && c == board[5]
            ||  c == board[6] && c == board[7] && c == board[8]
            ||  c == board[0] && c == board[3] && c == board[6]
            ||  c == board[1] && c == board[4] && c == board[7]
            ||  c == board[2] && c == board[5] && c == board[8]
            ||  c == board[0] && c == board[4] && c == board[8]
            ||  c == board[2] && c == board[4] && c == board[6];
    }
}
