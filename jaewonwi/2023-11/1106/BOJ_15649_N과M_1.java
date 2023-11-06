import java.util.Scanner;

public class BOJ_15649_N과M_1 {
	static int N, M;
	static int[] tgt;
	static boolean[] visit;

	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		N = Integer.parseInt(sc.next());
		M = Integer.parseInt(sc.next());
		
		tgt = new int[M];
		visit = new boolean[N+1];
		
		perm(0);
		
		System.out.println(sb);
	}

	static void perm(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(tgt[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (visit[i]) continue;
			
			tgt[idx] = i;
			visit[i] = true;
			
			perm(idx + 1);
			
			visit[i] = false;
		}
	}
}
