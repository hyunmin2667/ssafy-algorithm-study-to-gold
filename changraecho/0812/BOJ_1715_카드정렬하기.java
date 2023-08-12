import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715_카드정렬하기 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N
        int N = Integer.parseInt(br.readLine());

        // N개의 수들을 priority queue에 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add((Integer.parseInt(br.readLine())));
        }

        // N == 1인 경우 고려해야
        if (N == 1) {
            System.out.println(0);
            return;
        }

        // N >= 2이상인 경우. 각 단계에서 가장 작은 두 원소를 합친다.
        // 첫 조건
        int first;
        int second;

        long ans = 0;

        while (!pq.isEmpty()) {
            first = pq.poll();
            second = pq.poll();

            ans += first + second;
            if (!pq.isEmpty()) {
                pq.add(first + second);
            }

        }

        System.out.println(ans);

    }

}
