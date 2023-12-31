/*
7569 tomato
 */

import javax.imageio.IIOImage;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][][] tomato;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static Queue<pos> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomato[h][i][j] = Integer.parseInt(st.nextToken());
                    if (tomato[h][i][j] == 1) q.offer(new pos(j, i, h));
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        while (!q.isEmpty()) {
            pos cur = q.poll();

            for (int d = 0; d < 6; d++) {
                int nh = cur.h + dh[d];
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                //범위 벗어나거나 멀쩡한 토마토가 아닌 경우
                if (nh >= H || nh < 0 || nx >= M || nx < 0 || ny >= N || ny < 0 || tomato[nh][ny][nx] != 0) continue;
                if (tomato[nh][ny][nx] == 0) {
                    q.offer(new pos(nx, ny, nh));

                    //이전 토마토 +1
                    tomato[nh][ny][nx] = tomato[cur.h][cur.y][cur.x] + 1;
                }

            }
        }

        int result = 0;

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomato[h][i][j] == 0)
                        return -1;
                    result = Math.max(result, tomato[h][i][j]);
                }
            }
        }
        if (result == 1) return 0;
        return (result - 1);
    }

    static class pos {
        int x, y, h;

        public pos(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
