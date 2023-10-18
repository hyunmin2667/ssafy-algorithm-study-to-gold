import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.BufferedReader;

// 아이디어: 양수들을 priority queue posPQ에 저장한다. 음수들은 그것들의 절댓값을 priority queue negPQ에 저장한다.
public class BOJ_11286_절댓값힙 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> posPQ = new PriorityQueue<>();
        PriorityQueue<Integer> negPQ = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {

            if (nums[i] > 0) {
                posPQ.add(nums[i]);
            } else if (nums[i] < 0) {
                negPQ.add(-nums[i]);
            } else {
                if (posPQ.isEmpty() && negPQ.isEmpty()) {
                    System.out.println(0);
                } else if (posPQ.isEmpty()) {
                    System.out.println(-negPQ.poll());
                } else if (negPQ.isEmpty()) {
                    System.out.println(posPQ.poll());
                } else {
                    if (posPQ.peek() < negPQ.peek()) {
                        System.out.println(posPQ.poll());
                    } else {

                        System.out.println(-negPQ.poll());
                    }
                }
            }

        }

    }

}
