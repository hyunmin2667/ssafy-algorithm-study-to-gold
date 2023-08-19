package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 데이터셋
* N M(엣지 갯수)
 * 7
6
1 2
2 3
1 5
5 2
5 6
4 7
 */
public class BOJ_2606_virus {
    static int N,M,count;
    static boolean[][] com;
    static boolean[] visit;
    public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
        
        N = Integer.parseInt(br.readLine())+1;//노드갯수
        M = Integer.parseInt(br.readLine());//엣지 갯수

        com = new boolean[N][N];
        visit = new boolean[N];

        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int to =Integer.parseInt(st.nextToken());
            int from =Integer.parseInt(st.nextToken());
            com[to][from] = com[from][to] = true;//adjMatrix
        }
        
        visit[1] = true;
        dfs(1);//1만 체크하면 된다.

        System.out.println(count);
    }
    static void dfs(int num){//정점 번호
        visit[num] = true;//현재 노드
        for(int i = 1; i<N; i++){
            if(!com[num][i]||visit[i])  continue;// 방문했거나 연결되지 않은 노드
            count++;

            dfs(i);
        }
    }
}
