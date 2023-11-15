import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, ans;
	static Node[][] before, after;
	static class Node {
		int n, d, maxN;

		private Node(int n, int d, int maxN) {
			super();
			this.n = n;
			this.d = d;
			this.maxN = maxN;
		}
	}
	
	static int[] dy = { 0, -1, 1, 0, 0 };	// 0 dummy
	static int[] dx = { 0, 0, 0, -1, 1 };
	
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    
    	for (int t = 1; t <= T; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine().trim());
    		N = Integer.parseInt(st.nextToken());	// 맵 크기
    		M = Integer.parseInt(st.nextToken());	// 격리 시간
    		K = Integer.parseInt(st.nextToken());	// 미생물 군집의 수
    		
    		ans = 0;
    		before = new Node[N][N];
    		after = new Node[N][N];
    		
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				before[i][j] = new Node(0,0,0);
    				after[i][j] = new Node(0,0,0);
    			}
    		}
    		for (int k = 0; k < K; k++) {
    			st = new StringTokenizer(br.readLine().trim());
        		int y = Integer.parseInt(st.nextToken());
        		int x = Integer.parseInt(st.nextToken());
        		int n = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		
        		before[y][x].n = n;
        		before[y][x].d = d;
    		}
    		
    		for (int m = 0; m < M; m++) {
    			solve();
    		}
    		
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (before[i][j].n != 0) ans += before[i][j].n;
    			}
    		}
    		
    		sb.append("#").append(t).append(" ").append(ans).append("\n");
    	}
    	System.out.println(sb);
    }
    
    static void solve() {    	
    	for (int i = 0; i < N; i++) {	
    		for (int j = 0; j < N; j++) {
    			int num = before[i][j].n;
				int dir = before[i][j].d;
				
    			if (num > 0) {	// 미생물 군집이 있다면 이동시키자
    				int ny = i + dy[dir];
    				int nx = j + dx[dir];
    				
    				if (isItSide(ny, nx)) {	// 약품처리된 곳이라면
    					after[ny][nx].n = num / 2;
    					after[ny][nx].d = changeDir(dir);
    					continue;
    				}
    				
    				if (after[ny][nx].n != 0) {	// 이미 미생물 군집이 있다면
    					after[ny][nx].n += num;
    					if (after[ny][nx].maxN < num) {	// 제일 큰 애보다 새로운 애가 더 크다면
    						after[ny][nx].maxN = num;
    						after[ny][nx].d = dir;
    					}
    				} else {
    					after[ny][nx].n = num;
    					after[ny][nx].d = dir;
    					after[ny][nx].maxN = num;
    				}
    			}
    		}
    	}
    	
//    	System.out.println("BEFORE");
//		printMap(before);
//		System.out.println("AFTER");
//    	printMap(after);
		
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			before[i][j].n = after[i][j].n;
    			before[i][j].d = after[i][j].d;
    		}
    	}

    	mapClear(after);
    	
    }
    
    static int changeDir(int dir) {
    	if (dir % 2 != 0)	{ // 방향이 홀수면 
			dir += 1;
		} else {	// 방향이 짝수라면
			dir -= 1;
		}
    	return dir;
    }
    
    static void printMap(Node[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].n+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------------");
    }
    
    static boolean isItSide(int y, int x) {
    	if (y == 0 || y == N-1 || x == 0 || x == N-1) return true;
    	else return false;
    }

    static void mapClear(Node[][] map) {
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			map[i][j].n = 0;
    			map[i][j].d = 0;
    			map[i][j].maxN = 0;
    		}
    	}
    }
    
}
