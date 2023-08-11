import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// do it 교재의 문제는 아님.
public class BOJ_16637_괄호추가하기 {
    static int N; //
    static List<String> toCompute;
    static boolean[] parenthese;
    static int ans = Integer.MIN_VALUE, temp;
    static int num1, num2;
    static String operation;
    static Deque<Integer> numDeque;
    static Deque<String> opQ;

    static int compute(int num1, String operation, int num2) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
        }

        return 0;
    }

    static void generateParenthese(int idx) {
        if (idx >= (N - 1) / 2) {
            // toCompute copy
            List<String> toComputeCopy = new ArrayList<>(toCompute);

            // toComputeCopy에서 추가된 괄호를 반영하기
            for (int i = (N - 1) / 2 - 1; i > -1; i--) {
                if (parenthese[i]) {
                    num2 = Integer.parseInt(toComputeCopy.remove(2 * i + 2));
                    operation = toComputeCopy.remove(2 * i + 1);
                    num1 = Integer.parseInt(toComputeCopy.remove(2 * i));

                    toComputeCopy.add(2 * i, String.valueOf(compute(num1, operation, num2)));
                }

            }

            // toComputeCopy로부터 deque numDeque와 queue opQ를 생성.

            numDeque = new ArrayDeque<>();
            opQ = new ArrayDeque<>();

            for (String elem : toComputeCopy) {
                if (!elem.equals("+") && !elem.equals("*") && !elem.equals("-")) {
                    numDeque.offer(Integer.parseInt(elem));
                } else {
                    opQ.offer(elem);
                }
            }

            while (numDeque.size() >= 2) {
                num1 = numDeque.poll();
                num2 = numDeque.poll();
                operation = opQ.poll();
                temp = compute(num1, operation, num2);
                numDeque.addFirst(temp);
            }

            // 필요하다면 ans를 재정의
            ans = Math.max(ans, temp);

            return;
        }

        if (parenthese[idx - 1]) {
            parenthese[idx] = false;
            generateParenthese(idx + 1);
        } else {
            parenthese[idx] = false;
            generateParenthese(idx + 1);
            parenthese[idx] = true;
            generateParenthese(idx + 1);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N
        N = Integer.parseInt(br.readLine());

        // toCompute 정의
        toCompute = Arrays.asList(br.readLine().split(""));

        if (N >= 3) {
            parenthese = new boolean[(N - 1) / 2];
            generateParenthese(1);
            parenthese = new boolean[(N - 1) / 2];
            parenthese[0] = true;
            generateParenthese(1);

        } else if (N == 1) {
            ans = Integer.parseInt(toCompute.get(0));
        }

        // 정답 출력
        System.out.println(ans);
    }
}
