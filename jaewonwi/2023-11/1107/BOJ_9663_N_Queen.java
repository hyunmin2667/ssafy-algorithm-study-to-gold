import java.util.Scanner;

public class BOJ_9663_N_Queen {
	static int N, ans = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());	// 1 <= N <= 15
		
		tgt = new int[N];
		select = new boolean[N];
		
		backtracking(0);
		
		System.out.println(ans);
	}

	static int[] tgt, src;
	static boolean[] select;
	
	static void backtracking(int tgtIdx) {
		if (tgtIdx == N) {
			// complete code
			return;
		}
		
		for (int i = 0; i < N; i++) {
			tgt[tgtIdx] = i;
			
			if (check(tgtIdx)) {			// 이때까지 체크한 게 가능하다면
				backtracking(tgtIdx+1);		// 다음 거 보러가자
			}
		}
		
	}
	
	static boolean check(int tgtIdx) {
		for (int i = 0; i < tgtIdx; i++) {
			// 같은 열에 있거나, 대각선 상에 존재한다면 -> 공격가능 => 배제하자
			if (tgt[i] == tgt[tgtIdx] || (tgtIdx-i) == Math.abs(tgt[i] - tgt[tgtIdx]))
				return false;
		}
		return true;
	}

}
