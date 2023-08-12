
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1744_수묶기 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N
        int N = Integer.parseInt(br.readLine());

        // 0의 존재 확인
        boolean zeroExists = false;

        // 양수만 담은 priority queue. 우선순위는 내림정렬 순으로
        PriorityQueue<Integer> posPQ = new PriorityQueue<>((n1, n2) -> n2 - n1);

        // 음수만 담은 priority queue. 우선순위는 오름차순 순으로
        PriorityQueue<Integer> negPQ = new PriorityQueue<>();

        // 주어진 N개의 수를 이용하여 posPQ와 negPQ를 채운다.
        int num;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            if (num == 0) {
                zeroExists = true;
            } else if (num > 0) {
                posPQ.add(num);
            } else {
                negPQ.add(num);
            }
        }

        // 정답
        int ans = 0;
        int first, second;
        // posPQ의 계산

        while (posPQ.size() >= 2) {
            first = posPQ.poll();
            second = posPQ.poll();

            if (first > 1 && second > 1) {
                ans += first * second;
            } else {
                ans += first + second;
            }

        }

        if (posPQ.size() == 1) {
            ans += posPQ.poll();
        }

        // negPQ의 계산. negPQ의 size가 홀수이고 0이 존재하지 않을 경우를 생각해야 함.
        while (negPQ.size() >= 2) {
            ans += negPQ.poll() * negPQ.poll();
        }

        if (negPQ.size() == 1) {
            if (!zeroExists) {
                ans += negPQ.poll();
            }
        }

        System.out.println(ans);

    }

}
