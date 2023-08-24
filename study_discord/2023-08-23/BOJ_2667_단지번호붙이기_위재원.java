
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2667_단지번호붙이기_위재원 {
    static int N, cnt, area;
    static int[][] map;
    static int[] dy = {-1,1,0,0};	// 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        
        area = 0;
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if (map[i][j] == 1) {
        			cnt = 1;
        			map[i][j] = 0;
        			check(i, j);
        			area++;
        			pq.offer(cnt);
        		}
        	}
        }
        
        System.out.println(area);
        while (!pq.isEmpty()) {
        	System.out.println(pq.poll());	
        }
        
    }
    
    static void check(int y, int x) {
    	for (int i = 0; i < 4; i++) {
    		int ny = y + dy[i];
    		int nx = x + dx[i];
    		
    		if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 1) {	// 맵 내에 있고 1이라면
				map[ny][nx] = 0;	// 0으로 바꾸고
				cnt++;
				check(ny, nx);
    		}
    	}
    }

}