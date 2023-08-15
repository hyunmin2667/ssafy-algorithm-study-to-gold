import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

// A형 기출
public class BOJ_17135_캐슬디펜스 {
    static int N, M, D, numEnemy;
    static int[] comb = new int[3];
    static int[][] grid, gridCopy;
    static int ans, killedNum, eliminatedNum;
    static HashSet<List<Integer>> killedSet;

    // closestPoint(i)는 궁수의 좌표는 [N][comb[idx]]으로부터 거리가 D이하인 점 중 가장 가깝고 왼쪽에 위치한 점이다.
    static List<Integer> closestPoint(int idx) {
        int dist = D;
        List<Integer> ans = null;

        for (int j = M - 1; j > -1; j--) {
            for (int i = N - 1; i > -1; i--) {
                int distToDefender = Math.abs(i - N) + Math.abs(j - comb[idx]);
                if (gridCopy[i][j] == 1 && distToDefender <= dist) {
                    dist = distToDefender;
                    ans = new ArrayList<>();
                    ans.add(i);
                    ans.add(j);
                    break;
                }
            }
        }

        return ans;
    }

    static void generateComb(int cIdx, int numIdx) {

        if (cIdx == 3) {
            // 선택된 궁수의 자리들에 대해 제거되는 적의 수 계산

            // gridCopy 초기화 및 정의
            gridCopy = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    gridCopy[i][j] = grid[i][j];
                }
            }

            // killedNum(제거된 적의 수) , eliminatedNum(제외된 적의 수) 초기화
            killedNum = 0;
            eliminatedNum = 0;

            while (eliminatedNum < numEnemy) {
                // 궁수들이 적을 제거해야 한다.

                // killedSet - 각 단계에서 제거되는 적들의 좌표
                killedSet = new HashSet<>();

                for (int i = 0; i < 3; i++) { // 각 궁수마다 거리가 D이하인 적들 중 가장 가깝고 왼쪽에 있는 것을 선택한다.
                    if (closestPoint(i) != null) {
                        killedSet.add(closestPoint(i));
                    }

                }

                // killedNum와 eliminatedNum을 수정한다
                killedNum += killedSet.size();
                eliminatedNum += killedSet.size();

                // killedSet의 좌표들은 0으로 변경한다.
                for (List<Integer> coord : killedSet) {
                    gridCopy[coord.get(0)][coord.get(1)] = 0;
                }

                // 적들이 x축 방향으로 1만큼 움직인다. 마지막 행에 놓여져 있는 1들의 수들만큼 eliminatedNum에 더해준다.
                for (int j = 0; j < M; j++) {
                    if (gridCopy[N - 1][j] == 1) {
                        gridCopy[N - 1][j] = 0;
                        eliminatedNum++;
                    }
                }

                for (int j = M - 1; j > -1; j--) {
                    for (int i = N - 2; i > -1; i--) {
                        if (gridCopy[i][j] == 1) {
                            gridCopy[i + 1][j] = 1;
                            gridCopy[i][j] = 0;
                        }
                    }
                }

            }

            // 정답 수정
            ans = Math.max(ans, killedNum);

            return;
        }

        for (int i = numIdx; i < M; i++) {
            comb[cIdx] = i;
            generateComb(cIdx + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;

        // N, M, D 정의
        str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        D = Integer.parseInt(str.nextToken());

        // grid 정의, numEnemy 정의
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(str.nextToken());
                if (grid[i][j] == 1) {
                    numEnemy++;
                }
            }
        }

        generateComb(0, 0);

        System.out.println(ans);

    }

}
