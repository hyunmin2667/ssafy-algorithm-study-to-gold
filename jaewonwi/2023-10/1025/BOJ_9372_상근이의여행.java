import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9372_상근이의여행 {
	static int T, N, M, ans;
	static int[][] adj;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adj = new int[N+1][N+1]; 
			visit = new boolean[N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adj[a][b] = adj[b][a] = 1;
			}
			
			System.out.println(bfs(1) - 1);
		}
	}
	
	static int bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = true;
        ans = 0;
        
        while(!q.isEmpty()){
            ans++;
            int cur = q.poll();
            
            for(int i = 1 ; i <= N ;i++){
                if(adj[cur][i] == 1 && !visit[i]){
                    visit[i] = true;
                    q.add(i);
                }
            }
        }
        return ans;
    }
}
