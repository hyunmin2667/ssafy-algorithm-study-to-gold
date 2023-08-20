import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
    static int N, M;
    static int[][] grid;
    static HashMap<String, Integer> islandCoords = new HashMap<>(); // 각 좌표가 어느 섬에 속하는지 알아야 한다.
    static int islandNum;
    static int[][] graph;
    static boolean[][] visited; // grid에서 dfs시
    static boolean[] checked; // 섬들의 graph가 연결되어있는지 확인할 때
    static int[] dx = { 0, -1, 0, 1 }; // 오 위 왼 아
    static int[] dy = { 1, 0, -1, 0 }; // 오 위 왼 아
    static HashMap<Integer, Integer> disjointSet = new HashMap<>();
    static int islandCount = 1; // 연결된 섬들의 갯수
    static int ans = 0;

    // dfs: 섬에 섬의 번호를 부여
    static void dfs(int x, int y, int islandName) {
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && grid[nx][ny] == 1) {
                visited[nx][ny] = true;
                islandCoords.put(Arrays.toString(new int[] { nx, ny }), islandName);
                dfs(nx, ny, islandName);
            }
        }
    }

    // disjointSet
    static int find(int x) {
        if (disjointSet.get(x) == x)
            return x;

        while (disjointSet.get(x) != x) {
            return find(disjointSet.get(x));
        }

        return 0;
    }

    // disjointSet
    static void union(int x, int y) {
        disjointSet.put(find(x), find(y));
    }

    static void dfsIsland(int v) {

        for (int neighbor = 0; neighbor < islandNum; neighbor++) {
            if (!checked[neighbor] && graph[v][neighbor] != Integer.MAX_VALUE) {
                checked[neighbor] = true;
                islandCount++;
                dfsIsland(neighbor);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N,M
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // grid 초기화 및 정의
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // visited 초기화
        visited = new boolean[N][M];

        // islandNum과 islandCoords 정의하기
        islandNum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    islandCoords.put(Arrays.toString(new int[] { i, j }), islandNum);
                    dfs(i, j, islandNum);
                    islandNum++;
                }
            }
        }

        // graph의 꼭짓점은 0 ~ islandNum - 1. 두 꼭짓점 사이의 거리가 존재하면 가장 짧은 것을 선택
        graph = new int[islandNum][islandNum];

        for (int i = 0; i < islandNum; i++) {
            for (int j = 0; j < islandNum; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        // 가로 다리들의 길이
        int start, end;
        int firstV, secondV;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= M - 2; j++) {
                if (grid[i][j - 1] == 1 && grid[i][j] == 0) {
                    start = j;
                    end = j;

                    while (end < M && grid[i][end] == 0) {
                        end++;
                    }

                    if (end != M && end - start != 1) {
                        firstV = islandCoords.get(Arrays.toString(new int[] { i, start - 1 }));
                        secondV = islandCoords.get(Arrays.toString(new int[] { i, end }));
                        graph[firstV][secondV] = Math.min(graph[firstV][secondV], end - start);
                        graph[secondV][firstV] = Math.min(graph[firstV][secondV], end - start);
                    }
                }
            }
        }

        // 세로 다리들의 길이
        for (int j = 0; j < M; j++) {
            for (int i = 1; i <= N - 2; i++) {
                if (grid[i - 1][j] == 1 && grid[i][j] == 0) {
                    start = i;
                    end = i;

                    while (end < N && grid[end][j] == 0) {
                        end++;
                    }

                    if (end != N && end - start != 1) {
                        firstV = islandCoords.get(Arrays.toString(new int[] { start - 1, j }));
                        secondV = islandCoords.get(Arrays.toString(new int[] { end, j }));
                        graph[firstV][secondV] = Math.min(graph[firstV][secondV], end - start);
                        graph[secondV][firstV] = Math.min(graph[secondV][firstV], end - start);

                    }
                }
            }
        }

        // graph가 연결되어 있는 지 확인한다. 연결되어 있지 않다면 return -1
        checked = new boolean[islandNum];

        checked[0] = true;
        dfsIsland(0);
        if (islandCount < islandNum) {
            System.out.println(-1);
            return;
        }

        // graph가 연결되어 있는 경우, Minimal spanning tree를 이용하여 다리들의 거리의 합의 최솟값을 계산.

        for (int i = 0; i < islandNum; i++) { // disjointSet 정의
            disjointSet.put(i, i);
        }

        // 다리들을 길이가 짧은 순서대로 정렬한다
        List<int[]> bridges = new ArrayList<>(); // firstV, secondV, dist

        for (int i = 0; i < islandNum; i++) {
            for (int j = i + 1; j < islandNum; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    bridges.add(new int[] { i, j, graph[i][j] });
                }
            }
        }

        Collections.sort(bridges, (e1, e2) -> e1[2] - e2[2]);

        // cycle이 안 된다면 edge를 추가
        int dist;
        for (int[] edge : bridges) {
            firstV = edge[0];
            secondV = edge[1];
            dist = edge[2];

            if (find(firstV) == find(secondV))
                continue;

            union(firstV, secondV);
            ans += dist;

        }

        System.out.println(ans);

    }

}
