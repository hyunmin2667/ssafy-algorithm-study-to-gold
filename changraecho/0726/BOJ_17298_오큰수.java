
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;

// 아이디어: 주어진 수열을 배열로 생각한 것을 seq라고 하자. 어떤 index i에 대해 seq[i] < seq[i + 1]이 성립한다고 가정하자. 그러면 j= i-1, i-2, i-3 ...에 대해 seq[j]와 seq[i+1]의 대소관계를 생각한다. 만약 seq[j] > seq[i+1]이라면 loop를 벗어난다.
public class BOJ_17298_오큰수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        int[] seq = new int[N + 1];
        int[] ans = new int[N + 1];

        StringTokenizer seqString = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(seqString.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int num = seq[i];

            while (!stack.empty() && seq[stack.peek()] < num) {
                ans[stack.peek()] = num;
                stack.pop();
            }

            stack.push(i);

        }

        while (!stack.empty()) {
            int topIdx = stack.pop();
            ans[topIdx] = -1;
        }

        // System.out.print를 이용하면 시간 초과됨.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i <= N; i++) {
            bw.write(ans[i] + " ");
        }

        br.close();
        bw.close();
    }

}