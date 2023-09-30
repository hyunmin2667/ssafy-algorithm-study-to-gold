import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_2668_숫자고르기_조창래 {
	static int N;
	static int[] arr; // arr[i]는 둘째칸의 i번째 수
	static boolean[] goodSubset;
	
	static void goodSubsetHas(int i) { // i가 goodSubset에 포함된다면, goodSubset[i] = true로 설정
		if (i == arr[i]) {
			goodSubset[i] = true;
			return;
		}
		
		int firstNum = i;
		int num = arr[firstNum];
		Set<Integer> nums = new HashSet<>();
		nums.add(firstNum);
		
		while (true) {
			nums.add(num);
			if (!nums.contains(arr[num])) {
				num = arr[num];
			}
			
			else if (arr[num] == firstNum) {
				for (int n: nums) {
					goodSubset[n] = true;
				}
				return;
			}
			
			else {
				return;
			}
		}
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		goodSubset = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			goodSubsetHas(i);
		}
		
		int ansCount = 0;
		for (int i = 1; i <= N; i++) {
			if (goodSubset[i]) {
				sb.append(i);
				sb.append("\n");
				ansCount++;
			}
		}
		
		sb.insert(0, ansCount + "\n");
		
		System.out.println(sb.toString());
		
		
		
	}

}
