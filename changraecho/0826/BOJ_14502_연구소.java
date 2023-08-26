import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

    static int N, M;
    static char[][] grid;
    static char[][] gridCopy;
    static Coord[] comb = new Coord[3];
    static List<Coord> emptyCoords = new ArrayList<>();
    static List<Coord> virusCoords = new ArrayList<>();
    static int[] dx = { 0, -1, 0, 1 }; // 오, 위, 왼, 아
    static int[] dy = { 1, 0, -1, 0 };
    static int ans = 0;

    static void generateComb(int cIdx, int eIdx) {
        if (cIdx == 3) {
            // gridCopy로부터 grid 정의 - 벽을 세워야 함
            grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    grid[i][j] = gridCopy[i][j];
                }
            }

            for (int i = 0; i < 3; i++) {
                Coord wallCrd = comb[i];
                grid[wallCrd.x][wallCrd.y] = '1';
            }

            // grid에서 바이러스를 이용해 채움
            for (Coord vCrd : virusCoords) {
                spreadVirus(vCrd);
            }

            // 안전영역의 갯수를 셈, 필요하다면 정답 수정
            ans = Math.max(ans, countSafe());
            return;
        }

        for (int i = eIdx; i < emptyCoords.size(); i++) {
            comb[cIdx] = emptyCoords.get(i);
            generateComb(cIdx + 1, i + 1);
        }
    }

    static void spreadVirus(Coord v) {
        int x = v.x;
        int y = v.y;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] == '0') {
                grid[nx][ny] = '2';
                spreadVirus(new Coord(nx, ny));
            }

        }
    }

    static int countSafe() {
        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '0')
                    ans++;
            }
        }

        return ans;
    }

    static class Coord {
        int x, y;

        @Override
        public String toString() {
            return "Coord [x=" + x + ", y=" + y + "]";
        }

        public Coord(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gridCopy = new char[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                gridCopy[i][j] = st.nextToken().charAt(0);

                if (gridCopy[i][j] == '0') {
                    emptyCoords.add(new Coord(i, j));
                } else if (gridCopy[i][j] == '2') {
                    virusCoords.add(new Coord(i, j));
                }
            }
        }

        generateComb(0, 0);

        System.out.println(ans);

    }

}
