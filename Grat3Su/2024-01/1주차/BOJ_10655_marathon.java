/*
* 체크포인트의 수 N
* Manhattan Distance
* |x1-x2| + |y1-y2|
* 체크포인트 하나 건너뜀
* */

import java.io.*;
import java.util.*;
public class Main {

    static int N, min, ans;
    static Pos[] distance;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //차례대로

        distance = new Pos[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        distance[0] = new Pos(x,y,0);

        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int dis = Math.abs(distance[i-1].x - x) +Math.abs(distance[i-1].y - y);
            min += dis;
            distance[i] = new Pos(x,y,dis);
        }

        ans = min;
        for(int i = 1; i<N-1; i++){
            //모든 거리 - 해당 거리2 + 새로운 거리
            int dis = min - distance[i].dis - distance[i+1].dis;
            dis += Math.abs(distance[i-1].x - distance[i+1].x) +Math.abs(distance[i-1].y - distance[i+1].y);
            ans = Math.min(dis, ans);
        }

        System.out.println(ans);
    }

    static class Pos{
        int x;
        int y;
        int dis;

        public Pos(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

    }
}
