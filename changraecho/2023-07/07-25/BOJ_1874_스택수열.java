
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;




//
public class BOJ_1874_스택수열 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		ArrayList<String> operations = new ArrayList<>();


		boolean possible = true;
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];

		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(br.readLine());
		}

		int seqIdx = 0;

		for (int i = 1; i <= N; i++) {


			while (!stack.empty() && seq[seqIdx] == stack.peek() ) {
					stack.pop();
					operations.add("-");
					seqIdx++;
				}

			if (stack.empty()) {
					stack.push(i);
					operations.add("+");
					continue;
				}

			if (stack.peek() < seq[seqIdx]) {
					stack.push(i);
					operations.add("+");
					continue;
				} else {
					possible = false;
					break;
				}
			}


		while (!stack.empty() && seq[seqIdx] == stack.peek() ) {
			stack.pop();
			operations.add("-");
			seqIdx++;
		}

		if (stack.empty()) {
			for (String op: operations) {
				System.out.println(op);
			}
		} else {
			System.out.println("NO");
		}

}

}