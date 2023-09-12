import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자_위재원 {
	static int N, M;
	static List<Integer>[] list;
	static int[] plan;
	static int[] parents;
	static boolean[] check;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		// 여행 일정 -> 이 여행 경로가 가능한지 알아보자. (경유 가능)
		// 도시 개수. 연결 여부. 여행계획에 속한 도시들이 순서대로. 같은 도시 여러번 방문가능
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		plan = new int[M+1];
		check = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					list[i].add(j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		// 풀이
		boolean flag = true;
		
		// bfs
		Queue<Integer> q = new ArrayDeque<>();
		q.add(plan[1]);
		check[plan[1]] = true;
		while (!q.isEmpty()) {
			int i = q.poll();
			for (int j = 0; j < list[i].size(); j++) {
				if (check[list[i].get(j)])
					continue;
				else {
					q.add(list[i].get(j));
					check[list[i].get(j)] = true;
				}
			}
		}

//		System.out.println(Arrays.toString(check));
		
		for (int i = 1; i <= M; i++) {
			if (!check[plan[i]]) flag = false;
		}
		
		if (flag) System.out.println("YES");
		else System.out.println("NO");
		
	}

}
