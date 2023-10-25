
import java.util.*;
import java.io.*;

public class Grat3suBOJ_1167_Tree {
	static ArrayList<Node>[] adjList;
	static int N, max;
	static boolean[] visited;
	static int node;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			while (true) {
				int e = Integer.parseInt(st.nextToken());
				if (e == -1)
					break;
				int cost = Integer.parseInt(st.nextToken());
				adjList[s].add(new Node(e, cost));
			}
		}

		max = 0;
		visited = new boolean[N + 1];
		dfs(1, 0);

		visited = new boolean[N + 1];
		dfs(node, 0);

		System.out.println(max);
	}

	public static void dfs(int x, int len) {
		if (len > max) {
			max = len;
			node = x;
		}
		visited[x] = true;

		for (int i = 0; i < adjList[x].size(); i++) {
			Node n = adjList[x].get(i);
			if (visited[n.e] == false) {
				dfs(n.e, n.cost + len);
				visited[n.e] = true;
			}
		}

	}

	public static class Node {
		int e;
		int cost;

		public Node(int e, int cost) {
			this.e = e;
			this.cost = cost;
		}
	}
}
