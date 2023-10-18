package algo_study;

import java.io.*;
import java.util.*;

/*N
7
3
1
1
5
5
4
6
 */

public class BOJ_2668_addNum {
    static int N;
    static int[] map;
    static boolean[] visit;//방문배열
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1];
        visit = new boolean[N+1];

        for(int i = 1; i<=N; i++){
            map[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i<=N; i++){
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;            
        }

        Collections.sort(list);

        System.out.println(list.size());
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    static void dfs(int root, int idx){        
        if(!visit[map[idx]]){
            visit[map[idx]] = true;
            dfs(root, map[idx]);
            visit[map[idx]] = false;
        }

        if(root == map[idx]){//사이클
            list.add(root);
        }
    }
}