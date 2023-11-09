import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234_준환이의양팔저울 {
	static int T, N, ans;
	static int[] weight, tgt;
	static boolean[] select;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			weight = new int[N];
			tgt = new int[N];
			select = new boolean[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken()); 	
			}
			
			ans = 0;
			perm(0);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void perm(int idx) {
		// 기저조건
		if (idx == N) {
			check(0, 0, 0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (select[i]) continue;
			
			select[i] = true;
			tgt[idx] = weight[i];
			perm(idx + 1);
			select[i] = false;
		}
	}

	static void check(int idx, int left, int right) {
		if (left < right) return;
		if (idx == N) {
			ans++;
			return;
		}
		
		check(idx + 1, left + tgt[idx], right);
		check(idx + 1, left, right + tgt[idx]);
	}
}
