import java.io.*;
import java.util.*;

public class Main {
    //최단 시간 구하기
    static int N, W, L, time;//트럭 수, 다리 길이, 최대 하중
    static Queue<Integer> car;//지나가는 차

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        car = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            car.offer(Integer.parseInt(st.nextToken()));
        }

        int curWeight = 0;// 현재 다리 위의 무게

        Queue<Integer> bridge = new ArrayDeque<>();//현재 위치

        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }

        while (!bridge.isEmpty()) {//다리 위에 아무것도 없음
            time++;
            curWeight -= bridge.poll();
            if (!car.isEmpty()) {
                if (car.peek() + curWeight <= L) {
                    curWeight += car.peek();
                    bridge.offer(car.poll());
                } else {
                    bridge.offer(0);
                }
            }
        }
        System.out.println(time);
    }
}
