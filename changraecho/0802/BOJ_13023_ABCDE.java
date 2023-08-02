
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_13023_ABCDE {
    static int[] tgt = { -1, -1, -1, -1, -1 };
    static boolean[] checked;
    static HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
    static int ans = 0;

    static void backtrack(int v, int tgtIdx) {
        checked[v] = true;
        tgt[tgtIdx] = v;

        if (tgtIdx == 4) {
            ans = 1;
            return;
        }

        for (int neighbor : graph.get(v)) {

            if (checked[neighbor]) {
                continue;
            }

            backtrack(neighbor, tgtIdx + 1);
        }

        checked[v] = false;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M
        StringTokenizer str = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        // checked 초기화
        checked = new boolean[N];

        // graph 초기화
        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        // graph 정의
        for (int i = 0; i < M; i++) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int firstV = Integer.parseInt(edge.nextToken());
            int secondV = Integer.parseInt(edge.nextToken());

            graph.get(firstV).add(secondV);
            graph.get(secondV).add(firstV);
        }

        // backtrack
        for (int i = 0; i < N; i++) {
            backtrack(i, 0);

            // tgt 초기화
            tgt[0] = -1;
            tgt[1] = -1;
            tgt[2] = -1;
            tgt[3] = -1;
            tgt[4] = -1;
            if (ans == 1) {
                break;
            }
        }

        System.out.println(ans);

    }
}
