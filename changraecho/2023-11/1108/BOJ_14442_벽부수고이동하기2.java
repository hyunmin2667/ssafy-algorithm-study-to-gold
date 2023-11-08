
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442_벽부수고이동하기2 {

    static int N, M, K;
    static char[][] grid;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static boolean[][][] visited;
    static Queue<Data> q;

    static class Data {
        int x;
        int y;
        int w; // 부술 수 있는 벽의 개수
        int dist; // 현재까지 이동 거리

        public Data(int x, int y, int w, int dist) {
            super();
            this.x = x;
            this.y = y;
            this.w = w;
            this.dist = dist;
        }

    }

    static void bfs() {
        q = new ArrayDeque<>();

        q.offer(new Data(0, 0, K, 1));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Data data = q.poll();

            int x = data.x;
            int y = data.y;
            int w = data.w;
            int dist = data.dist;

            if (x == N - 1 && y == M - 1) {
                System.out.println(dist);
                return;
            }

            int nx, ny;
            for (int d = 0; d < 4; d++) {
                nx = x + dx[d];
                ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;

                if (grid[nx][ny] == '0') {
                    if (!visited[nx][ny][w]) {
                        visited[nx][ny][w] = true;
                        q.offer(new Data(nx, ny, w, dist + 1));
                    }
                } else {
                    if (w >= 1 && !visited[nx][ny][w - 1]) {
                        visited[nx][ny][w - 1] = true;
                        q.offer(new Data(nx, ny, w - 1, dist + 1));
                    }
                }

            }

        }

        System.out.println(-1);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = row[j];
            }
        }

        visited = new boolean[N][M][K + 1];

        bfs();

    }

}
