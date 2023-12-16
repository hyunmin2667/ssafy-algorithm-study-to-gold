import java.io.*;
import java.util.*;

public class BOJ_1205_등수구하기 {
    static int N, newScore, P;
    static Integer[] scoreBoard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        newScore = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        //등록된 랭킹 리스트가 없을 때
        if (N == 0) {
            System.out.println(1);
            return;
        }
        scoreBoard = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scoreBoard[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scoreBoard, Collections.reverseOrder());


        //랭킹 리스트에 올라갈 수 없을 정도로 낮다면 -1을 출력
        if (N == P && newScore <= scoreBoard[N - 1]) {
            System.out.println(-1);
        }else{
            int rank = 1;
            for(int i = 0; i<N; i++){
                if(newScore< scoreBoard[i]) rank++;
                else
                    break;

            }
            System.out.println(rank);
        }
    }
}
