import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_N번째큰수 {
    static int N;
    static int[][] grid;

    static class Coord implements Comparable<Coord> {
        int x, y;
        int value;

        public Coord(int x, int y, int value) {
            super();
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Coord o) {
            // TODO Auto-generated method stub
            return o.value - this.value;
        }

    }

    static void sort() {
        PriorityQueue<Coord> pq = new PriorityQueue<>();

        for (int j = 0; j < N; j++) {
            pq.offer(new Coord(N - 1, j, grid[N - 1][j]));
        }

        for (int c = 0; c < N - 1; c++) {
            Coord coord = pq.poll();
            if (coord.x > 0) {
                pq.offer(new Coord(coord.x - 1, coord.y, grid[coord.x - 1][coord.y]));
            }
        }

        System.out.println(grid[pq.peek().x][pq.peek().y]);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sort();
    }

}