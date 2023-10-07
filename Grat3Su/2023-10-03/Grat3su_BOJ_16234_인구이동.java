package algo_study;
/*
N L R
map
2 20 50
50 30
20 40
(연합의 인구수) / (연합을 이루고 있는 칸의 개수)
*/

import java.io.*;
import java.util.*;

public class BOJ_16234_인구이동 {
    static int N, R, L;// R명 이상 L명 이하
    static int ans;
    static int[][] map;
    static boolean[][] visit;
    static boolean isMove;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        
        while(true){//인구 이동이 모두 끝날 때 까지 멈추지않는다
            visit =  new boolean[N][N];//초기화
            isMove = false;

            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(!visit[i][j])//방문하지 않았으면 돌려
                        bfs(new Coord(i, j));
                }
            }

            
            if(!isMove){//방문 안했으면 탈출
                break;
            }
            ans++;//방문했으면 횟수 추가
            
        }

        System.out.println(ans);

    }

    static void bfs(Coord c){        
        Queue<Coord> q = new ArrayDeque<>();
        q.offer(c);
        ArrayList <Coord> union = new ArrayList<>();

        while(!q.isEmpty()){
            Coord cur = q.poll();

            for(int i = 0; i<4; i++){
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if(x < 0 || x>=N||y<0||y>=N||visit[x][y])continue;//범위 확인 방문 확인

                if(L<=Math.abs(map[cur.x][cur.y] - map[x][y])&&Math.abs(map[cur.x][cur.y] - map[x][y])<=R){
                    visit[x][y] = true;
                    q.offer(new Coord(x, y));
                    union.add(new Coord(x,y));
                    isMove = true;
                }
            }
            
        }
        
        int sum = 0;
        for(int i = 0; i<union.size(); i++){
            Coord pos = union.get(i);
            sum += map[pos.x][pos.y];
        }
        int size = union.size();
        for(int i = 0; i<union.size(); i++){
            Coord pos = union.get(i);
            map[pos.x][pos.y] = sum/size;
        }
    }

    static class Coord{
        int x,y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        
    }
}