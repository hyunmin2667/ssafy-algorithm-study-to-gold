package algo.a0821.add;
import java.util.*;
import java.io.*;
/*
 * 테스트케이스
 * 정점 N 간선 M
 * 6 5
1 2
2 5
5 1
3 4
4 6*/

public class BOJ_11724_연결그래프 {
	static int N,M;
	static ArrayList<Integer>[]adjList;
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N];
		visited = new boolean[N];
		for(int i = 1; i<N; i++) {
			adjList[i] = new ArrayList<Integer>();	//초기화		
		}
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			adjList[to].add(from);
			adjList[from].add(to);
		}
		int count = 0;
		for(int i = 1; i<N; i++) {
			if(!visited[i]) {
				count++;
				dfs(i);
			}			
		}
		System.out.println(count);
	}
	
	static void dfs(int idx) {
		if(visited[idx])return;
		visited[idx]=true;
		
		for(int i : adjList[idx]) {
			if(!visited[i]) {//방문x탐색
				dfs(i);
			}
		}
	}

}
