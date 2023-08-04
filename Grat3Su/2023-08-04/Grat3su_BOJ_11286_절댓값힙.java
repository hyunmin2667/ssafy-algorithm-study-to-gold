package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_11286_절댓값힙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num != 0) {
				stack.offer(num);

			} else {
				if (stack.isEmpty()) {// 비었으면 0
					sb.append(0).append("\n");
					continue;
				}
				int min = Integer.MAX_VALUE;
				int realMin = Integer.MAX_VALUE;
				for (int j = 0; j < stack.size(); j++) {
					int poll = stack.poll();
					stack.offer(poll);
					if (min >= Math.abs(poll)) {
						if(poll < realMin){
							realMin = poll;
						}
						min = Math.abs(realMin);
					}
				}
				sb.append(realMin).append("\n");
				stack.remove(Integer.valueOf(realMin));

			}
		}
		
		System.out.println(sb);
	}
}
