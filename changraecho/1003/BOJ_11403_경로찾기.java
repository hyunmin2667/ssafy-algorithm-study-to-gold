import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403_경로찾기 {
    static int N;
    static int[][] adjList;

    static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    adjList[i][j] = Math.max(adjList[i][j], adjList[i][k] * adjList[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        adjList = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjList[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(adjList[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
