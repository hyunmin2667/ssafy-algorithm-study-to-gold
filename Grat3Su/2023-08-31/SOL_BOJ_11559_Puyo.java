package BOJ.algostudy;
import java.io.*;
import java.util.*;

public class SOL_BOJ_11559_Puyo {
	    static char[][] map;
	    static int[] dx = {0, 1, 0, -1};
	    static int[] dy = {1, 0, -1, 0};
	    static boolean[][] visited;
	    static ArrayList<Node> list;
	    static int N = 12, M = 6;
	    
	    public static void main(String[] args) throws IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        map = new char[N][M];
	        for(int i = 0; i < N; i++) {
	            String field = br.readLine();
	            for(int j = 0; j < M; j++) {
	                map[i][j] = field.charAt(j); 
	            }
	        }
	        
	        int count = 0;
	        //board를 탐색하며 4개 이상 뭉쳐있는 노드를 확인한다.
	        while(true) {
	            boolean isFinished = true;//끝났는지 확인
	            visited = new boolean[N][M];//방문 노드 초기화
	            for(int i = 0; i < N; i++) {
	                for(int j = 0; j < M; j++) {
	                    if(map[i][j] != '.') {//맵이 빈칸이 아닌 경우
	                        list = new ArrayList<>();//리스트 초기화
	                        bfs(map[i][j], i, j);//탐색
	                        
	                        if(list.size() >= 4) {//터뜨릴 뿌요가 4개 이상
	                            isFinished = false; //연쇄가 일어났으므로 더 탐색해보아야 한다.
	                            for(int k = 0; k < list.size(); k++) {
	                                map[list.get(k).x][list.get(k).y] = '.'; // 터트려서 없앰
	                            }
	                        }
	                    }
	                }
	            }
	            if(isFinished) break;//끝났다
	            fallPuyos(); // 뿌요들을 떨어뜨린다.
	            count++;
	        }
	        System.out.println(count);
	    }

	    public static void fallPuyos() {        
	        for (int i = 0; i < M; i++) {
	            for (int j = N - 1; j > 0; j--) {//아래에서부터 탐색
	                if (map[j][i] == '.') {//해당 칸이 빈칸인 경우
	                    for (int k = j - 1; k >= 0; k--) {//해당 칸에서 위로 올라간다
	                        if (map[k][i] != '.') {//빈칸이 아닌 경우(떨어뜨려야할 뿌요)
	                            map[j][i] = map[k][i];//바로 옮긴다
	                            map[k][i] = '.';//없앤다
	                            break;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    
	    public static void bfs(char c, int x, int y) {//찾아야할 뿌요, 위치
	        Queue<Node> q = new LinkedList<>();//큐 생성
	        list.add(new Node(x, y));//리스트에 노드를 추가 - 터뜨릴게 있는지 확인해야 하기 때문에
	        q.offer(new Node(x, y));//탐색을 위해 추가한다
	        visited[x][y] = true;//방문체크
	        
	        while(!q.isEmpty()) {//비어있으면 종료
	            Node current = q.poll();
	            
	            for(int i = 0; i < 4; i++) {//델타 탐색
	                int nx = current.x + dx[i];
	                int ny = current.y + dy[i];
	                
	                if(nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] == false && map[nx][ny] == c) {
	                    visited[nx][ny] = true;//방문체크
	                    list.add(new Node(nx, ny));//리스트에 추가
	                    q.offer(new Node(nx, ny));//큐에 추가
	                }
	            }
	        }
	    }
	 
	    public static class Node {
	        int x;
	        int y;
	        
	        public Node(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }
	    }
	}    
