import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// A형 기출
// N의 모든 부분집합에 대해, 두 부분집합이 모두 공집합이 아닌 경우들을 생각하자. 이때 각 부분집합이 연결되어 있는 지를 dfs를 통해 확인한다. 두 부분집합 모두 연결되어 있다면, 값을 계산한다.
public class BOJ_17471_게리맨더링 {
    static int N;
    static Set<Integer>[] graph;
    static boolean[] subset, complement; // 길이가 N + 1 (0은 dummy)
    static boolean[] subsetDfs, complementDfs;
    static boolean subsetConnected, complementConnected;
    static int[] population;
    static int subsetSum, complementSum;
    static int ans = Integer.MAX_VALUE;

    // 바이너리 카운팅을 이용해 모든 부분집합 생성
    static void generateSubset(int idx) {
        for (int i = 1; i < (1 << N) - 1; i++) {
            subset = new boolean[N + 1];

            // 생성된 subset에 대해, 다른 subset 또한 생성
            complement = new boolean[N + 1];

            for (int j = 0; j < N; j++) {

                if ((i & 1 << j) != 0) {
                    subset[j + 1] = true;
                } else {
                    complement[j + 1] = true;
                }
            }

            // 두 subset이 모두 연결되어 있는 지 확인
            // subset의 한 점으로부터 dfs를 할 때, subsetDfs와 subset이 같으면 연결되어 있음.
            // subsetSum, subsetConnected, subsetDfs
            subsetSum = 0;
            subsetConnected = true;
            subsetDfs = new boolean[N + 1];

            // subset의 한 점 찾기
            int firstV = 0;

            for (int k = 1; k <= N; k++) {
                if (subset[k]) {
                    firstV = k;
                    break;
                }
            }

            if (firstV == 0) {
                System.out.println(Arrays.toString(subset));
                System.out.println(Arrays.toString(complement));
                System.out.println(i);
            }

            // sDfs 실행
            subsetDfs[firstV] = true;
            subsetSum += population[firstV];
            sDfs(firstV);

            // subsetDfs와 subset 비교
            for (int k = 1; k <= N; k++) {
                if (subsetDfs[k] != subset[k]) {
                    subsetConnected = false;
                    break;
                }
            }

            // complement의 한 점으로부터 dfs를 할 때, complementDfs와 complement이 같으면 연결되어 있음.
            complementSum = 0;
            complementConnected = true;
            complementDfs = new boolean[N + 1];

            // subset의 한 점 찾기
            firstV = 0;

            for (int k = 1; k <= N; k++) {
                if (complement[k]) {
                    firstV = k;
                    break;
                }
            }

            // System.out.println(firstV);

            // sDfs 실행
            complementDfs[firstV] = true;
            complementSum += population[firstV];
            cDfs(firstV);

            // subsetDfs와 subset 비교
            for (int k = 1; k <= N; k++) {
                if (complementDfs[k] != complement[k]) {
                    complementConnected = false;
                    break;
                }
            }

            // 만약 두 subset이 모두 연결되어 있다면, 정답 계산 및 수정
            if (subsetConnected && complementConnected) {
                ans = Math.min(ans, Math.abs(subsetSum - complementSum));
            }
        }

    }

    // static 변수 subset과 complement가 정의되어 있을 때, subset의 dfs 결과를 subsetDfs에 저장
    static void sDfs(int vertex) {

        for (int neighbor : graph[vertex]) {
            if (!subsetDfs[neighbor] && subset[neighbor]) {
                subsetDfs[neighbor] = true;
                subsetSum += population[neighbor];
                sDfs(neighbor);
            }
        }
    }

    // static 변수 subset과 complement가 정의되어 있을 때, complement의 dfs 결과를 complementDfs에
    // 저장
    static void cDfs(int vertex) {

        for (int neighbor : graph[vertex]) {
            if (!complementDfs[neighbor] && complement[neighbor]) {
                complementDfs[neighbor] = true;
                complementSum += population[neighbor];
                cDfs(neighbor);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // population 정의
        st = new StringTokenizer(br.readLine());
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        // graph 정의
        graph = new HashSet[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }

        int edgeNum, vertex;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            edgeNum = Integer.parseInt(st.nextToken());

            for (int j = 0; j < edgeNum; j++) {
                vertex = Integer.parseInt(st.nextToken());
                graph[i].add(vertex);
                graph[vertex].add(i);
            }

        }

        generateSubset(0);

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        System.out.println(ans);

    }

}
