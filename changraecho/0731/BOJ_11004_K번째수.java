
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_11004_K번째수 {

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer nAndK = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nAndK.nextToken());
        int K = Integer.parseInt(nAndK.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        StringTokenizer numbers = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = -Integer.parseInt(numbers.nextToken());
            if (pq.size() < K) {
                pq.add(num);
            } else if (pq.peek() < num) {
                pq.poll();
                pq.add(num);
            }
        }

        System.out.println(-pq.poll());

    }

}
