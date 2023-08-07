
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {
    // N, M
    static int N, M;

    // maze
    static int[][] maze;

    // checked
    static boolean[][] checked;

    // dist
    static int dist = 1;

    // queues
    // static Queue<int[]> parentQ = new ArrayDeque<>();

    // direction
    static int[] xDir = { 0, -1, 0, 1 };
    static int[] yDir = { 1, 0, -1, 0 };

    // bfs
    static void bfs(Queue<int[]> q) {
        if (q.isEmpty()) {
            return;
        }

        Queue<int[]> childrenQ = new ArrayDeque<>();

        while (!q.isEmpty()) {
            int[] coord = q.poll();
            int xCoord = coord[0];
            int yCoord = coord[1];

            if (xCoord == N && yCoord == M) {
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nxCoord = xCoord + xDir[dir];
                int nyCoord = yCoord + yDir[dir];

                if (nxCoord >= 1 && nxCoord <= N && nyCoord >= 1 && nyCoord <= M && !checked[nxCoord][nyCoord]
                        && maze[nxCoord][nyCoord] == 1) {
                    checked[nxCoord][nyCoord] = true;
                    childrenQ.offer(new int[] { nxCoord, nyCoord });

                }

            }

        }

        if (!childrenQ.isEmpty()) {
            dist++;
        }

        bfs(childrenQ);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        // N,M 정의
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        // maze 초기화 및 정의
        maze = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            for (int j = 1; j <= M; j++) {
                maze[i][j] = row.charAt(j - 1) - '0';
            }
        }

        // checked 정의
        checked = new boolean[N + 1][M + 1];
        checked[1][1] = true;

        // queue에 시작점 (1,1) 추가
        Queue<int[]> startQ = new ArrayDeque<>();
        startQ.offer(new int[] { 1, 1 });

        // bfs 실행
        bfs(startQ);

        System.out.println(dist);

    }

}
