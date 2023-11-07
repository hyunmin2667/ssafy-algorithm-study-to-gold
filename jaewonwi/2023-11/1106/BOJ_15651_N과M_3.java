import java.util.Scanner;

public class BOJ_15651_N과M_3 {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		N = Integer.parseInt(sc.next());
		M = Integer.parseInt(sc.next());
		
		tgt = new int[M];
		select = new boolean[N+1];
		
		perm(0);
		
		System.out.println(sb);
	}

	static int N, M;
	static int[] tgt;
	static boolean[] select;
	
	static void perm(int idx) {
		if (idx == M) {
			for (int t : tgt) sb.append(t).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			tgt[idx] = i;	
			perm(idx + 1);
		}
	}
}
