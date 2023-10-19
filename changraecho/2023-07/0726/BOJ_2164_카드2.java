import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import java.io.BufferedReader;

public class BOJ_2164_카드2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> q = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while (N >= 2) {
            q.poll();
            if (N == 2) {
                N--;
                break;
            }
            int last = q.poll();
            q.add(last);
            N--;
        }

        System.out.println(q.peek());

    }

}