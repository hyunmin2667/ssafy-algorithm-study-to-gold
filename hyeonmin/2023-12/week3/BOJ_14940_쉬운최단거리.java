package Algorithm_2023.src.month12.day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940_쉬운최단거리 {

    static int n, m;
    static int[][] map;
    static boolean[][] visit;

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 0은 갈 수 없는 땅
        // 1은 갈 수 있는 땅
        // 2는 목표지점 (1개)

        // 0이면 0을 출력
        // 0에 의해 가로막힌 땅이 있으면 -1로 표기
        // -> visit 배열로 관리

        // input
        n = Integer.parseInt(st.nextToken()); //세로
        m = Integer.parseInt(st.nextToken()); //가로
        map = new int[n][m];
        visit = new boolean[n][m];

        int startY = 0;
        int startX = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }
        // bfs
        bfs(startY, startX);

        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < m; j++) {
                if (!visit[i][j] && map[i][j] != 0) map[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int startY, int startX) {
        Queue<int[]> queue = new ArrayDeque<>();
        int dist = 0;
        visit[startY][startX] = true;
        map[startY][startX] = dist++;
        queue.offer(new int[] {startY, startX, dist});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if( isOutRange(ny, nx) || visit[ny][nx] || map[ny][nx] == 0) continue;
                map[ny][nx] = cur[2];
                visit[ny][nx] = true;
                queue.offer(new int[] {ny, nx, cur[2] + 1});
            }
        }

    }

    static boolean isOutRange(int y, int x) {
        return y >= n || y < 0 || x >= m || x < 0;
    }
}
