import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18352_특정거리의도시찾기_조창래 {
	
	static int N; // 도시의 개수
	static int M; // 도로의 개수
	static int K; // 최단 거리
	static int X; // 출발 도시의 번호
	static PriorityQueue<City> pq = new PriorityQueue<>(); 
	static List<List<Integer>> adjList = new ArrayList<>();
	static List<Integer> ans = new ArrayList<>();
	static boolean visited[]; // 1부터 N까지의 도시들의 방문여부
	
	static void bfs() {
		City startC = new City(X, 0);
		
		pq.add(startC);
		visited[startC.num] = true;
		
		while (!pq.isEmpty()) {
			City c = pq.poll();
			
			if (c.dist < K) {
				
				for (int neighbor: adjList.get(c.num)) {
					if (visited[neighbor]) continue;
					visited[neighbor] = true;
					pq.add(new City(neighbor, c.dist + 1));	
				}
				
			} else if (c.dist == K) {
				ans.add(c.num);
			} else {
				break;
			}
		}
		
	}
	
	
	static class City implements Comparable<City> {
		int num, dist;

		@Override
		public String toString() {
			return "City [num=" + num + ", dist=" + dist + "]";
		}
		
		public City(int num, int dist) {
			super();
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(City o) {
			// TODO Auto-generated method stub
			if (this.dist != o.dist) {
				return this.dist - o.dist;
			} else {
				return this.num - o.num;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adjList.add(new ArrayList<Integer>());
		
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		visited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			adjList.get(start).add(end);
			
		}
		
		bfs();
		
		if (ans.isEmpty()) {
			System.out.println(-1);
			return;
		} 
		
		for (int elem: ans) {
			sb.append(elem).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
