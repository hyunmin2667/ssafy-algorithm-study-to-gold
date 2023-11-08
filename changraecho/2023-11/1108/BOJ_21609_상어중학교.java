import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_상어중학교 {
    static int N;
    static int M;
    static int[][] grid;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] gridCopy;
    static PriorityQueue<BlockGroup> pq;
    static int points = 0;

    static class BlockGroup implements Comparable {
        List<int[]> blocks;
        int rainbowBlockNum; // 무지개 블록의 수
        int[] baseBlock; // 기준 블록

        public BlockGroup(List<int[]> blocks, int rainbowBlockNum, int[] baseBlock) {
            super();
            this.blocks = blocks;
            this.rainbowBlockNum = rainbowBlockNum;
            this.baseBlock = baseBlock;
        }

        public BlockGroup() {
            super();
        }

        @Override
        public int compareTo(Object o) {
            BlockGroup otherBg = (BlockGroup) o;

            if (this.blocks.size() != otherBg.blocks.size()) {
                return otherBg.blocks.size() - this.blocks.size();
            }

            else if (this.rainbowBlockNum != otherBg.rainbowBlockNum) {
                return otherBg.rainbowBlockNum - this.rainbowBlockNum;
            }

            else if (this.baseBlock[0] != otherBg.baseBlock[0]) {
                return otherBg.baseBlock[0] - this.baseBlock[0];
            }

            return otherBg.baseBlock[1] - this.baseBlock[1];

        }

    }

    // NOTATION: -3: empty, -2: visited, -1: 검은 블록, 0: 무지개 블록, 1 ~ M: 일반블록

    // TODO 모든 블록 그룹을 찾는다
    static void findBlockGroups() {
        // pq 초기화
        pq = new PriorityQueue<BlockGroup>();

        // grid의 각 좌표에 대해
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 그것이 일반 블록이 아니면 continue
                if (grid[i][j] <= 0)
                    continue;

                // 그 일반 블록의 인접한 칸에 무지개 블록이 있거나, 그것과 색깔이 같은 일반 블록이 있는 경우만 살펴본다
                if (!isBlockGroupPart(i, j))
                    continue;
                // 그 일반 블록을 기준 블록으로 잡는다
                BlockGroup blockGroup = new BlockGroup();
                blockGroup.baseBlock = new int[] { i, j };
                blockGroup.blocks = new ArrayList<>();
                blockGroup.blocks.add(new int[] { i, j });
                blockGroup.rainbowBlockNum = 0;
                // bfs를 이용해서 블록의 개수와 무지개 블록을 센다
                Queue<int[]> q = new ArrayDeque<>();
                List<int[]> rBlocks = new ArrayList<>();

                q.add(new int[] { i, j });

                int m = grid[i][j];
                grid[i][j] = -2;
                while (!q.isEmpty()) {
                    int[] crd = q.poll();

                    int x = crd[0];
                    int y = crd[1];

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                            continue;
                        if (grid[nx][ny] == m) {
                            blockGroup.blocks.add(new int[] { nx, ny });
                            grid[nx][ny] = -2;
                            q.add(new int[] { nx, ny });
                        } else if (grid[nx][ny] == 0) {
                            blockGroup.blocks.add(new int[] { nx, ny });
                            blockGroup.rainbowBlockNum++;
                            grid[nx][ny] = -2;
                            q.add(new int[] { nx, ny });
                            rBlocks.add(new int[] { nx, ny });
                        }
                    }
                }

                pq.offer(blockGroup);

                for (int[] crd : rBlocks) {
                    grid[crd[0]][crd[1]] = 0;
                }

            }
        }

        // gridCopy를 이용하여 grid를 복구한다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = gridCopy[i][j];
            }
        }

    }

    // TODO 일반 블록이 블록 그룹의 일부인지 판단
    static boolean isBlockGroupPart(int i, int j) {
        int ni, nj;

        for (int d = 0; d < 4; d++) {
            ni = i + dx[d];
            nj = j + dy[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N)
                continue;

            if (grid[ni][nj] == grid[i][j] || grid[ni][nj] == 0)
                return true;
        }

        return false;

    }

    // stack 이용
    // -1의 좌표들은 기억
    //
    // 아래서부터 위로: 좌표가 -1이라면

    // 격자에 중력이 작용한다
    static void gravity() {
        // 각 행에서 검은색 블록을 제외한 블록을 아래로 이동한다
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > -1; i--) {
                if (grid[i][j] == -1 || grid[i][j] == -3)
                    continue;
                moveDown(i, j);

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }

    }

    // grid[i][j]를 가능한 아래쪽으로 이동한다
    static void moveDown(int i, int j) {
        int ni = i;

        while (ni + 1 < N && grid[ni + 1][j] == -3) {
            ni++;
        }

        // if (i == 1 && j == 2 && grid[i][j] == 1) {
        // System.out.println(grid[ni + 1][j]);
        // }

        if (ni >= i + 1) {

            grid[ni][j] = grid[i][j];
            grid[i][j] = -3;

        }

    }

    // 격자를 90도 반시계 방향으로 회전한다
    static void rotate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = gridCopy[j][N - 1 - i];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridCopy[i][j] = grid[i][j];
            }
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        gridCopy = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                gridCopy[i][j] = grid[i][j];
            }
        }

        while (true) {
            // 모든 블록 그룹을 찾는다
            findBlockGroups();

            if (pq.isEmpty()) {
                System.out.println(points);
                return;
            }
            // 블록 그룹이 존재한다면, 크기가 가장 큰 블록 그룹을 찾는다
            BlockGroup bg = pq.poll();
            // System.out.println(bg.blocks.size());
            // 그 블록 그룹의 모든 블록을 제거하고, B^2점수를 획득
            points += Math.pow(bg.blocks.size(), 2);

            for (int[] crd : bg.blocks) {
                grid[crd[0]][crd[1]] = -3;
            }

            // 격자에 중력 작용
            gravity();

            // 격자를 90도 반시계 방향으로 회전
            rotate();

            // 격자에 중력 작용
            gravity();

        }

    }

}