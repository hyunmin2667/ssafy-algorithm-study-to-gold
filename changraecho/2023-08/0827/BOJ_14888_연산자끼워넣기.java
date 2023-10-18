import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {
    static int N;
    static Deque<Integer> numbers; // 주어진 N개의 수들
    static Deque<Integer> numbersCopy; // 주어진 N개의 수들의 복사
    static char[] operations = { '+', '-', '×', '÷' };
    static int[] opsNum; // 길이가 4인 배열. operations[0]은 +의 갯수, operations[1]은 -의 갯수, operations[2]는 *의 갯수,
                         // operations[3]은 /의 갯수이다.
    static char[] target; // 길이가 N - 1인 배열. idx에 연산이 와야한다.
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    static void backtrack(int tIdx) { // 가능한 모든 target을 backtrack으로 생성하기
        if (tIdx == N - 1) {

            // target으로 수식 계산해서 최대와 최소 갱신하기
            int computed = compute();
            min = Math.min(min, computed);
            max = Math.max(max, computed);

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opsNum[i] > 0) {
                target[tIdx] = operations[i];
                opsNum[i]--;
                backtrack(tIdx + 1);
                opsNum[i]++;
            }
        }
    }

    static int compute() { // target으로 주어진 수식 계산하기
        // numbers 초기화
        numbers = new ArrayDeque<>(numbersCopy);

        int n;

        for (int i = 0; i < N - 1; i++) {
            int first = numbers.poll();
            int second = numbers.poll();

            if (target[i] == '+') {
                n = first + second;
            } else if (target[i] == '-') {
                n = first - second;
            } else if (target[i] == '×') {
                n = first * second;
            } else {
                if (first < 0) {
                    first = -first;
                    n = -(first / second);
                } else {
                    n = first / second;
                }

            }

            numbers.offerFirst(n);
        }
        return numbers.poll();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        numbersCopy = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbersCopy.offer(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        opsNum = new int[4];

        for (int i = 0; i < 4; i++) {
            opsNum[i] = Integer.parseInt(st.nextToken());
        }

        target = new char[N - 1];
        backtrack(0);

        sb.append(max).append("\n").append(min);

        System.out.println(sb);
    }

}
