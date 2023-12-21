package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2104_부분배열고르기 {
	static int N;
	static int[] arr;
	static long[] pSumArr;
	static int[] t;
	
	static void buildTree(int v, int tl, int tr) {
		if (tl == tr) {
			t[v] = tl;
			return;
		}
		
		int lv = 2 * v;
		int rv = 2 * v + 1;
		int tm = (tl + tr) / 2;
		
		buildTree(lv, tl, tm);
		buildTree(rv, tm + 1, tr);
		
		if (arr[t[lv]] <= arr[t[rv]]) {
			t[v] = t[lv];
		} else {
			t[v] = t[rv];
		}	
	}
	
	static int query(int l, int r, int v, int tl, int tr) {
		if (l > r) return 0;
		
		if (l == tl && r == tr) {
			return t[v];
		}
		
		int lv = 2 * v;
		int rv = 2 * v + 1;
		int tm = (tl + tr) / 2;
		
		int lIdx = query(l, Math.min(r, tm), lv, tl, tm);
		int rIdx = query(Math.max(l, tm + 1), r, rv, tm + 1, tr);
		
		if (arr[lIdx] <= arr[rIdx]) {
			return lIdx;
		} else {
			return rIdx;
		}
		
	}
	
	static void buildpSumArr() {
		
		pSumArr = new long[N + 1];
		
		for (int i = 1; i <= N; i++) {
			pSumArr[i] = pSumArr[i - 1] + arr[i];
		}
	}
	
	static long point(int l, int r) {
		return (pSumArr[r] - pSumArr[l - 1]) * arr[query(l, r, 1, 1, N)];
	}
	
	static long max(long x, long y, long z) {
		return Math.max(x, Math.max(y,  z));
	}
	
	static long maxPoint(int l, int r) {
		
		if (l > r) return 0;
		if (l == r) return point(l, r);
		
		int minIdx = query(l, r, 1, 1, N);
		return max(point(l, r), maxPoint(l, minIdx - 1), maxPoint(minIdx + 1, r));
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		arr[0] = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		t = new int[4 * N + 1];
		
		buildpSumArr();
		buildTree(1, 1, N);
		

		
		long res = maxPoint(1, N);
//		
		System.out.println(res);
		

	}

}
