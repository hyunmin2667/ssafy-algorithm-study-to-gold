package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_ACMCraft {
	static int T;
	static int N;
	static int K;
	static int[] buildTime;
	static List<List<Integer>> adjList;
	static int[] deg;
	static int W;
	static int[] ansArr;
	static StringBuilder sb = new StringBuilder();
	
	static void ts() {
		Queue<Integer> q = new ArrayDeque<>();
		ansArr = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			if (deg[i] == 0) {
				q.offer(i);
				ansArr[i] = buildTime[i];
			}
		}
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			for (int neighbor: adjList.get(v)) {
				ansArr[neighbor] = Math.max(ansArr[neighbor], buildTime[neighbor] + ansArr[v]);
				deg[neighbor]--;
				if (deg[neighbor] == 0) {
					if (neighbor == W) {
						sb.append(ansArr[neighbor]);
						sb.append("\n");
						return;
					}
					q.offer(neighbor);
				}
			}
		}
		
		sb.append(ansArr[W]);
		sb.append("\n");
		
		
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			buildTime = new int[N + 1];
			for (int i = 1; i <= N; i++ ) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			
			adjList = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adjList.add(new ArrayList<>());
			}
			
			deg = new int[N + 1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList.get(s).add(e);
				deg[e]++;
			}
			
			W = Integer.parseInt(br.readLine());
			
			ts();
		}
		
		System.out.println(sb);
		

	}

}
