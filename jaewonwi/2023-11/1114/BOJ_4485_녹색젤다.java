import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색젤다 {
    static int N, t, ans;
    static int[][] map;
    
    static class Edge {
    	int y, x;
    	int c;
		public Edge(int y, int x, int c) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
		}
    }
    static int[][] cost;
    static int[] dy = { -1,1,0,0 };
    static int[] dx = { 0,0,-1,1 };
    	
    static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        t = 1;
        while (true) {
            N = Integer.parseInt(br.readLine().trim());
            if (N == 0) break;
            
            map = new int[N][N];
            cost = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    cost[i][j] = INF;
                }
            }
            
            dijkstra();
            
            
            sb.append("Problem ").append(t).append(": ").append(cost[N-1][N-1]).append("\n");
            t++;
        }
        
        System.out.print(sb);
    }
    
    static void dijkstra() {
    	PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
    	pq.add(new Edge(0, 0, map[0][0]));
    	
    	while (!pq.isEmpty()) {
    		Edge e = pq.poll();
    		for (int d = 0; d < 4; d++) {
    			int ny = e.y + dy[d];
    			int nx = e.x + dx[d];
    			
    			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
    			
    			if (e.c + map[ny][nx] < cost[ny][nx]) {
    				cost[ny][nx] = Math.min(e.c + map[ny][nx], cost[ny][nx]);
    				pq.add(new Edge(ny, nx, cost[ny][nx]));
    			}
    		}
    	}
    }
}