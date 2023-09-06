package BOJ.algostudy;
import java.io.*;
import java.util.*;
/*
 * 테스트케이스
 * 첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가
 * 4 4 2 1
1 2
1 3
2 3
2 4

->4
 */
public class BOJ_18352_Kdistance {
	static int N, M, K, X;
	static List<Integer>[]  adjList;	
	static boolean[] visited;
	static int[] minDis;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1;//vertex
		M = Integer.parseInt(st.nextToken());//엣지
		
		K = Integer.parseInt(st.nextToken());//제한		
		X = Integer.parseInt(st.nextToken());//출발 도시
		visited = new boolean[N];
		minDis = new int[N];
		Arrays.fill(minDis, Integer.MAX_VALUE);
		adjList = new ArrayList[N];
		for(int i = 1; i<N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i<M; i++) {			
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			adjList[to].add(from);
		}
		minDis[X] = 0;//시작
		bfs(X);
		
		for(int i:pq) {
			sb.append(i).append("\n");
			}
		if(sb.length()==0)
			sb.append(-1);
		System.out.println(sb);
	}
	static PriorityQueue<Integer>pq = new PriorityQueue<>();
	static void bfs(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(v);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			if(visited[x])continue;
			visited[x]=true;
			if(minDis[x]>K)	break;
			if(minDis[x]==K) {
				//sb.append(x).append("\n");
				pq.add(x);
			}
			
			for(int i = 0; i<adjList[x].size(); i++) {//현재 노드에 대한 연결 탐색
				int dis=adjList[x].get(i);//다음	노드
				//if(visited[dis])continue;
				if(minDis[dis]!=Integer.MAX_VALUE) continue;//방문했다
				
				minDis[dis] = Math.min(minDis[x]+1,minDis[dis]);//다음 v = 현재 거리+1 or 다음거리
	            q.add(dis);
			}
		}
	}
}
