import java.util.Scanner;

public class BOJ_15650_N과M_2 {
	static int N, M;
	static int tgtIdx, srcIdx;
	static int[] tgt;

	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		N = Integer.parseInt(sc.next());
		M = Integer.parseInt(sc.next());
		
		tgt = new int[M];
		
		comb(1, 0);
		
		System.out.println(sb);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(tgt[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if (srcIdx == N+1) return;
		
		tgt[tgtIdx] = srcIdx;
		comb(srcIdx+1, tgtIdx+1);
		comb(srcIdx+1, tgtIdx);
	
	}
}
