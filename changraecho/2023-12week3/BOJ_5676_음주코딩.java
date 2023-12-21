package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5676_음주코딩 {
	static int N;
	static int K;
	static int[] arr;
	static int[] t;
	static StringBuilder sb = new StringBuilder();
	
	static void build(int v, int tl, int tr) {
		if (tl == tr) {
			if (arr[tl] > 0) {
				t[v] = 1;
			} else if (arr[tl] == 0) {
				t[v] = 0;
			}
			else {
				t[v] = -1;
			}
			
			return;
		}
		
		int lv = 2 * v;
		int rv = 2 * v + 1;
		int tm = (tl + tr) / 2;
		
		build(lv, tl, tm);
		build(rv, tm + 1, tr);
		t[v] = t[lv] * t[rv];
	}
	
	static void update(int pos, int nval, int v, int tl, int tr) {
		if (tl == tr) {
			if (nval > 0) {
				t[v] = 1;
			} else if (nval == 0) {
				t[v] = 0; 
			} else {
				t[v] = -1;
			}
			
			return;
		}
		
		int lv = 2 * v;
		int rv = 2 * v + 1;
		int tm = (tl + tr) / 2;
		
		if (pos <= tm) {
			update(pos, nval, lv, tl, tm);
		} else {
			update(pos, nval, rv, tm + 1, tr);
		}
		
		t[v] = t[lv] * t[rv];
	}
	
	static int query(int l, int r, int v, int tl, int tr) {
		if (l > r) return 1;
		if (l == tl && r == tr) {
			return t[v];
		}
		
		int lv = 2 * v;
		int rv = 2 * v + 1;
		int tm = (tl + tr) / 2;
		
		return query(l, Math.min(r,tm), lv, tl, tm) * query(Math.max(l, tm + 1), r, rv, tm + 1, tr);
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str = "";

		while( (str = br.readLine()) != null ) {
			//System.out.println(str);
			st = new StringTokenizer(str);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			
			arr = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int n = Integer.parseInt(st.nextToken());
				if (n > 0) {
					arr[i] = 1;
				} else if (n == 0) {
					arr[i] = 0;
				} else {
					arr[i] = -1;
				}
			}
			
			t = new int[4 * N + 1];
			build(1, 1, N);
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				char order = st.nextToken().charAt(0);
				
				
				if (order == 'C') {
					int pos = Integer.parseInt(st.nextToken());
					int nval = Integer.parseInt(st.nextToken());
					
					update(pos, nval, 1, 1, N);
					
				} else {
					int l = Integer.parseInt(st.nextToken());
					int r = Integer.parseInt(st.nextToken());
					
					int q = query(l, r, 1, 1, N);
					if (q == 1) {
						sb.append("+");
					} else if (q== 0) {
						sb.append("0");
					} else {
						sb.append("-");
					}
				}
				
			}
			
			sb.append("\n");
			
			//System.out.println(sb);
			
		}
		
		//System.out.println("hi");
		
		System.out.println(sb);
		

	}

}
