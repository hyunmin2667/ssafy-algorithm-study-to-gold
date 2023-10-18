import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2251_물통 {

    static int[] volumes = new int[3];
    static boolean[][][] visited;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> ansList = new ArrayList<>();

    static int[] pour(int i, int j, int[] buckets) { // i번째 물통에서 j번째 물통으로 물을 옮기기
        if (buckets[i] == 0 || buckets[j] == volumes[j])
            return null;

        int[] ans = new int[3];
        ans[0] = buckets[0];
        ans[1] = buckets[1];
        ans[2] = buckets[2];

        if (ans[i] >= volumes[j] - ans[j]) {
            ans[i] -= (volumes[j] - ans[j]);
            ans[j] = volumes[j];
        } else {
            ans[j] += ans[i];
            ans[i] = 0;

        }

        return ans;
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] { 0, 0, volumes[2] });
        visited[0][0][volumes[2]] = true;
        ansList.add(volumes[2]);

        while (!queue.isEmpty()) {
            int[] waterBuckets = queue.poll();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j)
                        continue;
                    int[] pouredBuckets = pour(i, j, waterBuckets);

                    if (pouredBuckets == null)
                        continue;
                    if (visited[pouredBuckets[0]][pouredBuckets[1]][pouredBuckets[2]])
                        continue;

                    // System.out.println(i + " " + j + " " + Arrays.toString(pouredBuckets));
                    visited[pouredBuckets[0]][pouredBuckets[1]][pouredBuckets[2]] = true;
                    if (pouredBuckets[0] == 0) {
                        ansList.add(pouredBuckets[2]);
                    }
                    queue.offer(new int[] { pouredBuckets[0], pouredBuckets[1], pouredBuckets[2] });

                }
            }
            // System.out.println("cycle");

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        volumes[0] = Integer.parseInt(st.nextToken());
        volumes[1] = Integer.parseInt(st.nextToken());
        volumes[2] = Integer.parseInt(st.nextToken());

        visited = new boolean[volumes[0] + 1][volumes[1] + 1][volumes[2] + 1];

        bfs();

        Collections.sort(ansList);

        for (int elem : ansList) {
            sb.append(elem).append(" ");
        }

        System.out.println(sb);
    }

}
