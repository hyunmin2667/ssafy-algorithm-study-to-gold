import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] adjmatrix;
    
    static int ans;
    static boolean[] select, visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        adjmatrix = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        for (int i = 1; i <= N; i++) {
            adjmatrix[i][0] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= n; j++) {
                int v = Integer.parseInt(st.nextToken());
                adjmatrix[i][j] = v;	// 인접 행렬 구조 잘 생각하기!!!
            }
        }
        
        select = new boolean[N+1];
        visit = new boolean[N+1];
        
        ans = Integer.MAX_VALUE;
        subset(1);
        
        if (ans == Integer.MAX_VALUE) System.out.println(-1);	// ans 값의 변경이 없으면
        else System.out.println(ans);
    }
    
    static void subset(int srcIdx) {
    	// 부분집합 완성
    	if (srcIdx == N+1) {
    		// complete code
    		check();
    		return;
    	}
    	
    	select[srcIdx] = true;	// 선택
    	subset(srcIdx+1);
    	select[srcIdx] = false;	// 비선택
    	subset(srcIdx+1);
    }
    
    static void check() {
    	Arrays.fill(visit, false);	// 매번 체크 시마다 visit 배열 초기화!!!
    	
    	// a부터 bfs 돌려보기
    	int a = 0;
    	for (int i = 1; i <= N; i++) {
    		if (select[i]) {
    			a = i;
    			break;
    		}
    	}
    	if (a == 0) return;
    	bfs(a, true);

    	// b도 bfs 돌려보기
    	int b = 0;
    	for (int i = 1; i <= N; i++) {
    		if (!select[i]) {
    			b = i;
    			break;
    		}
    	}
    	if (b == 0) return;
    	bfs(b, false);
    
    	// a와 b 모두 bfs를 돌았으면 다 연결되어있을 경우, visit가 다 true로 바뀌어있어야 함
    	for (int i = 1; i <= N; i++) {
    		if (!visit[i]) {
    			return;
    		}
    	}
    	
    	// 다 연결되어있으면 합을 구해서 차를 출력
    	int sumA = 0;
    	int sumB = 0;
    	for (int i = 1; i <= N; i++) {
    		if (select[i]) sumA += adjmatrix[i][0];
    		else sumB += adjmatrix[i][0];
    	}
    		
    	ans = Math.min(ans, Math.abs(sumA-sumB));
    	return;
    }
    
    static void bfs(int idx, boolean flag) {
    	Queue<Integer> q = new ArrayDeque<>();
    	visit[idx] = true;
    	q.offer(idx);
    	
    	while (!q.isEmpty()) {
    		int v = q.poll();
    		for (int i = 1; i <= N; i++) {
    			int adj = adjmatrix[v][i];
    			if (adj != 0 && !visit[adj] && select[adj] == flag) {
    				visit[adj] = true;
    				q.offer(adj);
    			}
    		}
    	}
    	
    	
    }
}
