
import java.io.*;
import java.util.*;

/*N
 * M
 * adjmatrix
 * travel List
 * 4
2
0 1 0 1
1 0 1 0
0 1 0 0
1 0 0 0
1 2
-> yes
 */

public class BOJ_1976_여행가자_장수영 {
	static int N, M;// 도시의 수/여행 도시 수
	static int[] parents;//부모

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		makeSet();
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {//to : i from: j
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken())==1)//이어져있으면
					union(i,j);//결합
			}
		}
		//탐색
		st = new StringTokenizer(br.readLine());
		int start = find(Integer.parseInt(st.nextToken()));
		
		// 방문한 도시 수
		for (int i = 1; i < M; i++) {
			int city = Integer.parseInt(st.nextToken());
			if (start!=find(city)) {//부모가 다르다
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static int find(int a) {
		if(a==parents[a])	return a;
		
		return parents[a] = find(parents[a]);  
	}
	
	static void makeSet() {
		for(int i = 1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static void union(int a, int b){
		int pa = find(a);//부모 찾기
		int pb = find(b);
		
		if(pa==pb) return;//부모가 이미 같다 (사이클 방지)
		
		if(pa>pb) 
			parents[b] = parents[a];
		else if(pa<pb) 
			parents[a] = parents[b];
	}

}
