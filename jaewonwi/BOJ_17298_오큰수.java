import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr, NGE;
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		NGE = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		stack.push(0);
		for (int i = 1; i <= N; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				NGE[stack.pop()] = arr[i];
			}
			stack.push(i);
		}
	
		while (!stack.isEmpty()) {
			NGE[stack.pop()] = -1;
		}

		for (int i = 1; i <= N; i++) {
			sb.append(NGE[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}
