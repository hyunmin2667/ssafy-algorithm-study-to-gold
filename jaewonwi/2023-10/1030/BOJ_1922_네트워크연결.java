import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cnt, ans;
	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static List<Node>[] adjList;
	static boolean[] visit;
	
	static int[] minEdge;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visit = new boolean[N+1];
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			adjList[a].add(new Node(b, c));
			adjList[b].add(new Node(a, c));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
		pq.add(new Node(1, 0));
		
		ans = 0;
		cnt = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visit[cur.to]) continue;
			visit[cur.to] = true;
			ans += cur.weight;
			cnt++;
			
			if (cnt == N) break;
			
			for (Node next : adjList[cur.to]) {
				if (!visit[next.to]) pq.add(next);
			}
		}
		
		System.out.println(ans);
	}	
}
