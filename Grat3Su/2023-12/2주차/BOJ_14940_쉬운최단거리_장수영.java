import java.io.*;
import java.util.*;

//각 지점에서 목표지점까지의 거리를 출력한다.
// 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {-1,1,0,0}, dy={0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];
        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int road = Integer.parseInt(st.nextToken());

                //2 = 0
                //0 = 0
                //1 = -1
                if (road == 1) map[j][i] = -1;
                else {
                    if (road == 2) {
                        x = j;
                        y = i;
                    }
                    map[j][i] = 0;
                }
            }
        }

        bfs(x, y);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                sb.append(map[j][i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }



    static void bfs(int sx, int sy){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(sx, sy));

        while (!q.isEmpty()){
            Pos pos = q.poll();

            for(int d = 0; d<4; d++){
                int nx = pos.x + dx[d];
                int ny = pos.y + dy[d];
                if(nx>=M||nx<0||ny>=N||ny<0||visit[nx][ny]||map[nx][ny] == 0) continue;//이동 못하는 경우

                visit[nx][ny] = true;
                map[nx][ny] = (map[pos.x][pos.y] +1);

                q.offer(new Pos(nx, ny));
            }
        }
    }
static class Pos{
        int x, y, cost;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
}
