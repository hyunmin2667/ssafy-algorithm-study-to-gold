/*
틱택토
-> 끝나는 조건 출력

*끝나는 조건*
0-2 / 3-5 / 6-8
0 4 8 / 2 4 6
0 3 6 / 1 4 7 / 2 5 8
- 모두 차있는 경우

1. X가 이겼을 때 : 무조건 X>O
2. O가 이겼을 때 : 무조건 X=O
3. X < O || X >O+1 불가능
4. 같은 줄 두줄 이상 불가능
 */

import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static int winner;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String game = br.readLine();
        StringBuilder sb = new StringBuilder();

        while (true) {//게임이 끝난 판정까지 계속 진행됨
            String game = br.readLine();
            if (game.equals("end")) break;
            winner = 0;
            map = new int[9];

            int X = 0;
            int O = 0;
            boolean blank = false;
            boolean endGame = true;//게임이 정상적으로 끝나는 경우

            for (int i = 0; i < 9; i++) {
                // X : 1 | O : 2
                if (game.charAt(i) == 'X') {
                    map[i] = 1;
                    X++;
                } else if (game.charAt(i) == 'O') {
                    map[i] = 2;
                    O++;
                }

                if (game.charAt(i) == '.') blank = true; // 빈 칸이 없으면 게임이 끝나있는 상태
            }
            if (X < O || X > O + 1) endGame = false;// 선공이 후공보다 더 작을 수 없음. 최대 한개 차이여야함
            else{
                if (horizontal() > 1) endGame = false;
                if (vertical() > 1) endGame = false;
                if (diagonal() > 2) endGame = false;

                if (winner == 1) {//승자 체크
                    if (X <= O) endGame = false;
                } else if (winner == 2) {
                    if (X != O) endGame = false;
                    if(!blank) endGame = false;//빈칸이 없을 때 후공이 이길 수 없음
                } else if(winner == 0){//승자가 없을 때
                    if(blank)//빈칸이 있음
                        endGame = false;

                }
            }

            if (endGame) sb.append("valid").append("\n");
            else sb.append("invalid").append("\n");
        }

        System.out.println(sb);
    }

    static int horizontal() {//가로줄 체크
        int flag = 0;
        for (int i = 0; i < 3; i++) {
            if (map[i * 3] != 0 && map[i * 3] == map[(i * 3) + 1] && map[i * 3] == map[(i * 3) + 2]) {
                flag++;
                if (winner == 0)
                    winner = map[i * 3];
            }
        }
        return flag;
    }

    static int vertical() {//세로줄 체크
        int flag = 0;
        for (int i = 0; i < 3; i++) {
            if (map[i] != 0 && map[i] == map[i + 3] && map[i] == map[i + 6]) {
                flag++;
                if (winner == 0)
                    winner = map[i];
                else if (winner != map[i])//승자가 이미 이긴 사람이 아님
                    flag = 100;
            }
        }
        return flag;
    }

    static int diagonal() {//대각선 줄
        int flag = 0;
        if (map[0] != 0 && map[0] == map[4] && map[0] == map[8]) {
            if (winner == 0)
                winner = map[0];
            else if (winner != map[0])//승자가 이미 이긴 사람이 아님
                flag = 100;
            flag++;
        }

        if (map[2] != 0 && map[2] == map[4] && map[2] == map[6]) {
            if (winner == 0)
                winner = map[2];
            else if (winner != map[2])//승자가 이미 이긴 사람이 아님
                flag = 100;
            flag++;
        }
        return flag;
    }
}
