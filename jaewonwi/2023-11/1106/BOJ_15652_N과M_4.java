import java.util.Scanner;

public class BOJ_15652_N과M_4 {

	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		N = Integer.parseInt(sc.next());
		M = Integer.parseInt(sc.next());
		
		tgt = new int[M];
		select = new boolean[N+1];
		
		comb(1, 0);
		
		System.out.println(sb);
	}

	static int N, M;
	static int tgtIdx, srcIdx;
	static int[] tgt;
	static boolean[] select;
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(tgt[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = srcIdx; i <= N; i++) {
			tgt[tgtIdx] = i;
			comb(i, tgtIdx+1);
		}
	}
}
