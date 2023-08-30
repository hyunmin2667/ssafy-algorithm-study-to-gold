import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
    static int N;
    static int[][] grid; // N * N. 0은 빈 칸, 1은 사과, 2는 뱀의 위치
    static int sDir; // 뱀의 방향. 0 1 2 3 (오 아 왼 위)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static Deque<Coord> snake; // 뱀의 구성 좌표들을 Deque에 보관. front는 꼬리, back은 머리
    static PriorityQueue<DirChange> pq; // 방향변환 class를 저장
    static int time; // 게임이 진행되고 있는 시간

    // 뱀의 방향을 변환하기
    static void changeDir(char dir) {
        if (dir == 'L') {
            sDir = (sDir + 3) % 4;
        } else {
            sDir = (sDir + 1) % 4;
        }
    }

    // 게임 시작
    static void startGame() {
        // 초기 시간, 꼬리, 머리, 방향
        time = 1;
        snake = new ArrayDeque<>();
        snake.offer(new Coord(0, 0));
        grid[0][0] = 2;
        sDir = 0;

        // time이 t일 때
        while (true) {
            // draw();
            // System.out.println();
            Coord head = snake.peekLast();

            int hx = head.x;
            int hy = head.y;

            int nhx = hx + dx[sDir];
            int nhy = hy + dy[sDir];

            // System.out.println(nhx + " " + nhy);
            //
            // 다음 칸이 벽 또는 뱀의 일부라면 게임 종료
            if (nhx < 0 || nhx >= N || nhy < 0 || nhy >= N)
                break;
            else if (grid[nhx][nhy] == 2)
                break;

            // 그렇지 않다면
            // 다음 칸에 사과가 있다면
            else if (grid[nhx][nhy] == 1) {
                // 사과를 없애고, 뱀의 좌표를 추가한다
                grid[nhx][nhy] = 2;
                // 머리를 이동시킨다
                snake.offer(new Coord(nhx, nhy));

            }
            // 다음 칸에 사과가 없다면 머리를 이동시키고 꼬리를 변경
            else {
                // 머리를 이동시킨다
                snake.offer(new Coord(nhx, nhy));
                // 머리의 뱀의 좌표를 추가한다
                grid[nhx][nhy] = 2;
                // 꼬리를 지운다
                Coord tail = snake.pollFirst();

                // 꼬리의 뱀의 좌표를 지운다
                grid[tail.x][tail.y] = 0;
            }
            // pq.peek()의 time이 t와 같다면 pq.poll(), 방향 변경
            if (!pq.isEmpty() && pq.peek().time == time) {
                changeDir(pq.poll().dir);
            }

            time++;

        }
    }

    static void draw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
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

    static class DirChange implements Comparable<DirChange> {
        int time;
        char dir;

        @Override
        public String toString() {
            return "DirChange [time=" + time + ", dir=" + dir + "]";
        }

        public DirChange(int time, char dir) {
            super();
            this.time = time;
            this.dir = dir;
        }

        @Override
        public int compareTo(DirChange o) {
            // TODO Auto-generated method stub
            return this.time - o.time;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // grid 초기화
        grid = new int[N][N];

        // 사과의 개수 K
        int K = Integer.parseInt(br.readLine());
        // 사과의 위치(행과 열)이 주어질 때, -1을 해야 한다
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            // 사과를 grid에서 1로 기록
            grid[x][y] = 1;
        }

        // 뱀의 방향 변환의 개수 L
        int L = Integer.parseInt(br.readLine());

        // pq 초기화
        pq = new PriorityQueue<>();

        // pq에 방향 변환 클래스들을 넣기
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            pq.offer(new DirChange(t, dir));
        }

        // 게임시작
        startGame();

        // 정답 출력
        System.out.println(time);

    }

}
