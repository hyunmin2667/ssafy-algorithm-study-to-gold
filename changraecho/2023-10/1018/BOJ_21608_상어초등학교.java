import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21608_상어초등학교 {
    static int N;
    static int[][] grid; // N * N 배열
    static int[][] likeStudents; // 각 학생마다 좋아하는 학생들의 배열: (N * N + 1) * 4 배열.
    static int[] decisionOrder; // 자리를 정하는 순서: decisionOrder[i]는 i번째로 자리를 정하는 학생의 번호
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static PriorityQueue<Coord> pq;

    // (i, j) 좌표에 놓여진 학생의 만족도
    static int satisfaction(int i, int j) {
        int stud = grid[i][j];

        // (i, j)의 좌표의 인접한 칸중에서, 좋아하는 학생의 수를 구한다
        int likeStudNum = getLikeStudNum(i, j, grid[i][j]);

        if (likeStudNum == 0) {
            return 0;
        } else if (likeStudNum == 1) {
            return 1;
        } else if (likeStudNum == 2) {
            return 10;
        } else if (likeStudNum == 3) {
            return 100;
        } else if (likeStudNum == 4) {
            return 1000;
        }

        return -1; // 무의미한 값

    }

    // 학생의 만족도의 총합
    static int allSatisfaction() {
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res += satisfaction(i, j);
            }
        }

        return res;
    }

    // Class Coord 정의 및 comparator 구현하기
    static class Coord implements Comparable<Coord> {
        int x, y;
        int likeNum; // 인접한 칸 중 좋아하는 학생의 수
        int emptyNeighborNum; // 인접한 칸 중 비어있는 칸의 개수

        @Override
        public int compareTo(Coord o) {
            if (this.likeNum != o.likeNum) {
                return o.likeNum - this.likeNum;
            } else if (this.emptyNeighborNum != o.emptyNeighborNum) {
                return o.emptyNeighborNum - this.emptyNeighborNum;
            } else if (this.x != o.x) {
                return this.x - o.x;
            } else if (this.y != o.y) {
                return this.y - o.y;
            }
            return 0;
        }

        public Coord(int x, int y, int likeNum, int emptyNeighborNum) {
            super();
            this.x = x;
            this.y = y;
            this.likeNum = likeNum;
            this.emptyNeighborNum = emptyNeighborNum;
        }

    }

    // (i, j) 좌표에서, 그 좌표의 인접한 칸 중 student가 좋아하는 학생의 수를 구하기
    static int getLikeStudNum(int i, int j, int student) {

        // (i, j)의 좌표의 인접한 칸중에서, 좋아하는 학생의 수를 구한다
        int likeStudNum = 0;
        int ni, nj;
        for (int dir = 0; dir < 4; dir++) {
            ni = i + dx[dir];
            nj = j + dy[dir];

            if (0 <= ni && ni < N && 0 <= nj && nj < N) {
                for (int s : likeStudents[student]) {
                    if (s == grid[ni][nj])
                        likeStudNum++;
                }
            }
        }

        return likeStudNum;

    }

    // (i, j) 좌표에서, 그 좌표의 인접한 칸 중 비어있는 칸의 개수 구하기
    static int getEmptyNeighborNum(int i, int j) {
        int emptyNeighborNum = 0;

        int ni, nj;
        for (int dir = 0; dir < 4; dir++) {
            ni = i + dx[dir];
            nj = j + dy[dir];

            if (0 <= ni && ni < N && 0 <= nj && nj < N && grid[ni][nj] == 0) {
                emptyNeighborNum++;
            }
        }

        return emptyNeighborNum;

    }

    // k번째 단계에서 비어있는 칸들을 priority queue에 offer하기
    static void fillPq(int k) {
        // 비어있는 (i,j)에 대해, Coord를 만들어 pq에 넣는다

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    pq.offer(new Coord(i, j, getLikeStudNum(i, j, decisionOrder[k]), getEmptyNeighborNum(i, j)));
                }
            }
        }
    }

    // 사람들 정렬하기
    static void fillGrid() {
        for (int i = 1; i <= N * N; i++) {
            pq = new PriorityQueue<>();
            fillPq(i); // pq 채우기
            Coord crd = pq.peek(); // decisionOrder[i]가 위치해야 할 좌표
            grid[crd.x][crd.y] = decisionOrder[i];
        }
    }

    // 테스트
    static void drawGrid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        decisionOrder = new int[N * N + 1];
        likeStudents = new int[N * N + 1][4];
        for (int i = 1; i <= N * N; i++) {
            st = new StringTokenizer(br.readLine());
            decisionOrder[i] = Integer.parseInt(st.nextToken());

            for (int idx = 0; idx < 4; idx++) {
                likeStudents[decisionOrder[i]][idx] = Integer.parseInt(st.nextToken());
            }

        }

        grid = new int[N][N];
        fillGrid();
        System.out.println(allSatisfaction());

    }

}
