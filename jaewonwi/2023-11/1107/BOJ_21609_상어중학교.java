import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_상어중학교 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N, M, ans;
    static int[][] map;

    static int[] dy = { -1, 0, 0, 1 };    // 상좌우하
    static int[] dx = { 0, -1, 1, 0 };
    static final int NO = -2;
    
    
    static PriorityQueue<Group> list = new PriorityQueue<>((e1, e2) ->     // 개에바
            (e1.size == e2.size) ? 
                    (e1.rainbowSize == e2.rainbowSize) ? 
                            (e1.y == e2.y) ? e2.x - e1.x : e2.y - e1.y 
                            : e2.rainbowSize - e1.rainbowSize
                    : e2.size - e1.size);

    static class Group{
        int y, x;
        int standard, size, rainbowSize;
        String points;
        
        public Group(int y, int x, int standard, int size, int rainbowSize) {
            super();
            this.y = y;
            this.x = x;
            this.standard = standard;
            this.size = size;
            this.rainbowSize = rainbowSize;
        }
        
        public Group(int y, int x, int standard, int size, int rainbowSize, String str) {
            super();
            this.y = y;
            this.x = x;
            this.standard = standard;
            this.size = size;
            this.rainbowSize = rainbowSize;
            this.points = str;
        }
    }
    
    public static void main(String[] args) throws Exception{
        // 검(-1), 무(0), 일반(M가지)    // 그룹 내 블록의 크기는 2 이상이어야 함
        // 블록 내 일반끼리 색이 같아야함. 검은색 X. 연결되어있어야
        // 기준 블록 => 무지개가 X & 행의 번호가 가장 작은 것.(맨 위 -> 맨 왼쪽)
        
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());    // 맵 크기
        M = Integer.parseInt(st.nextToken());    // 일반 블록의 색상 수

        map = new int[N][N];
    
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        ans = 0;
        
        while (true) {
            // 1. 크기가 가장 큰 블록 그룹 ( 무지개가 가장 많은 -> 기준블록이 가장 아래 -> 오른쪽 )
            grouping(map);
            
            if (list.isEmpty()) break;	// 더이상 제거할 블록그룹이 없다면 게임종료
            
            // 2. 1의 모든 블록 제거 -> 블록의 수^2 만큼 점수 획득
            if (!list.isEmpty()) {							// list에서 한번만 뽑으면 된다!!
                Group cur = list.poll();
                st = new StringTokenizer(cur.points);
                while (st.hasMoreTokens()) {
                    int y = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    map[y][x] = NO;
//                    System.out.println(y+" "+x);
                }
                ans += cur.size * cur.size;
            }
                        
            // 3. 중력 작용
            down(map);

            // 4. 90도 반시계 회전
            turnMap(map);

            // 5. 다시 중력 작용    -> 중력 작용은 검은 블록은 제외
            down(map);

            list.clear();
        }
        
        System.out.println(ans);
        
    }
    
    static void turnMap(int[][] map) {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                 copyMap[N-j-1][i] = map[i][j];
            }
        }
        copyMap(copyMap, map);
    }
    
    static boolean[][] visit;
    static void grouping(int[][] map) {
        visit = new boolean[N][N];
        
        // 왼쪽 위부터 살펴보면서 블록에 값이 있다면 걔부터 블록을 탐색하자
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 얘부터 bfs 돌아서 한 묶음이다
                if (map[i][j] > 0)
                    bfs(i, j, map[i][j]);
            }
        }
    }
    
    static void bfs(int y, int x, int num) {
        if (visit[y][x]) return;
        
        int size = 1;
        int rainbow = 0;
        
        Queue<Group> q = new ArrayDeque<>();
        Queue<int[]> r = new ArrayDeque<>();

        sb = new StringBuilder();
        sb.append(y+" "+x+" ");
        
        q.add(new Group(y, x, num, size, rainbow));
        visit[y][x] = true;
        
        while (!q.isEmpty()) {
            Group cur = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                
                if (!isIn(ny, nx) || map[ny][nx] == -1 || visit[ny][nx]) continue;
            
                if (map[ny][nx] == 0) {        // 무지개 블록일 경우
                    q.add(new Group(ny, nx, num, size++, rainbow++));
                    r.add(new int[] {ny,nx});
                } else if (map[ny][nx] == num) {	// 일반 블록일 경우
                	
                    q.add(new Group(ny, nx, num, size++, rainbow));
                } else continue;
                
                visit[ny][nx] = true;
                sb.append(ny+" "+nx+" ");    // 블록들을 저장
            }
        }
        
        if (size >= 2)
            list.add(new Group(y, x, num, size, rainbow, sb.toString()));
        
        
        while (!r.isEmpty()) {    // rainbow 방문체크 해제!!!
            int[] rb = r.poll();
            visit[rb[0]][rb[1]] = false;
        }
        
    }
    
    static void down(int[][] map) {
    	
        for (int i = 0; i < N; i++) {
            for (int j = N-1; j >= 0; --j) {
                if (map[j][i] == NO || map[j][i] == -1) continue;
                int dest = j+1;
                while(true) {
                    if (dest == N) break;
                    if (map[dest][i] == NO) dest++;
                    else break;
                }
                if (dest == j+1) continue;
                map[dest-1][i] = map[j][i];
                map[j][i] = NO;
            }
        }
    }
    
    static boolean isIn(int y, int x) {
        if (y >= 0 && x >= 0 && y < N && x < N) return true;
        else return false;
    }
    
    static void copyMap(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            to[i] = from[i].clone();
        }
    }
    
    static void print(int[][] map) {
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			System.out.print(map[i][j] +" ");
    		}
    		System.out.println();
    	}
        System.out.println("----------------");
    }

}