import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, sSize = 2;
    static int[][] map;
    static int[] dy = {-1,0,0,1};    // 상 좌 우 하
    static int[] dx = {0,-1,1,0};
    static int ans, eatCnt;
    static Fish shark;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        
        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {    // 아기상어일 경우
                    map[i][j] = 0;
                    shark = new Fish(i, j);
                }
            }
        }


        // 0: 빈칸. 1~6: 물고기의 크기. 9: 아기상어의 위치
        ans = 0;
        eatCnt = 0;
        
        while (true) {    // 물고기가 있다면 찾아서 먹으러가자
        	int y = Integer.MAX_VALUE;	// 다음 먹을 물고기 찾기 전 물고기 위치 초기화
        	int x = Integer.MAX_VALUE;
        	int d = Integer.MAX_VALUE;
        	
        	Queue<Fish> queue = new LinkedList<>();
        	int[][] dist = new int[N][N];	// 이동 거리 저장 및 사이클 방지
        	
        	queue.add(new Fish(shark.y, shark.x));	// 현재 아기상어 위치
        	
        	while ( !queue.isEmpty() ) {	// bfs
        		Fish cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];
                 
                    if (nx < 0 || ny < 0 || ny >= N || nx >= N) continue;	// 맵 내부에 있는지
                    if (map[ny][nx] > sSize) continue;	// 물고기가 아기상어보다 크면 안됨
                    if (dist[ny][nx] != 0) continue;	// 이미 방문한 위치는 다시 방문 X (사이클 방지)
                    
                    dist[ny][nx] = dist[cur.y][cur.x] + 1;	// 이동횟수 저장
                    
                    if (map[ny][nx] != 0 && map[ny][nx] < sSize) {
                    	if (d > dist[ny][nx]) {
                    		d = dist[ny][nx];
                    		y = ny;
                    		x = nx;
                    	} else if (d == dist[ny][nx]) {
                    		if (ny == y) {	// 가장 왼쪽의 물고기
                    			if (x > nx) {
                    				y = ny;
                    				x = nx;
                    			}
                    		} else if (ny < y) {	// 가장 위의 물고기
                    			y = ny;
                    			x = nx;
                    		}
                    	}
                    }
                    
                    queue.add(new Fish(ny, nx));
                    
                }
        	}
        	
        	if (y == Integer.MAX_VALUE && x == Integer.MAX_VALUE) {	// 먹을 물고기를 못 찾은 경우
        		break;	
        	}
        	
        	ans += dist[y][x];
        	map[y][x] = 0;
            
        	shark.y = y;
        	shark.x = x;
        	
            eatCnt++;
            
            if (eatCnt == sSize) {	// 상어 크기만큼 물고기를 먹었으면 
            	sSize++;
            	eatCnt = 0;
            }
        }
        
        System.out.println(ans);

    }
    
    static class Fish {
        int y, x;

        public Fish(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
