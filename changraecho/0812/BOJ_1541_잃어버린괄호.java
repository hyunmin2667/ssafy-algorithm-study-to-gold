import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		String[] nums;
		List<Character> ops = new ArrayList<>();
		int[] parentheses; // 0이면 +, 양수면 -

		// nums 배열 정의, ops 배열 정의
		String input = br.readLine();

		nums = input.split("[+-]");

		// input의 시작은 양수.
		ops.add('+');
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' || input.charAt(i) == '-') {
				ops.add(input.charAt(i));
			}
		}

		// parentheses 초기화
		int pLen = ops.size();
		parentheses = new int[pLen];

		// parentheses 정의: +는 0, -는 그 다음 -까지의 거리를 기록
		List<Integer> memory = new ArrayList<>();

		for (int i = 0; i < pLen; i++) {
			if (ops.get(i) == '+') {
				parentheses[i] = 0;
			} else {
				memory.add(i);
			}
		}

		int memSize = memory.size();
		for (int i = 0; i < memSize - 1; i++) {
			parentheses[memory.get(i)] = memory.get(i + 1) - memory.get(i);
		}
		if (memSize > 0) {
			parentheses[memory.get(memSize - 1)] = pLen - memory.get(memSize - 1);
		}

		// nums와 parentheses를 이용하여 ans 계산
		int numsLen = nums.length;

		int idx = 0;
		while (idx < numsLen) {
			if (parentheses[idx] == 0) {
				ans += Integer.parseInt(nums[idx]);
				idx++;
			} else {
				for (int i = 0; i < parentheses[idx]; i++) {
					ans -= Integer.parseInt(nums[idx + i]);
				}
				idx += parentheses[idx];
			}
		}

		System.out.println(ans);

	}

}
