package algo_study;

import java.io.*;
import java.util.*;
public class SW_공통조상 {
    static int V, E, x,y;
    static int[]parent;
    static int[] size;
    static List<Integer>[]child;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=T; t++){
            ArrayList<Integer> v1 = new ArrayList<>();
            ArrayList<Integer> v2 = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            parent = new int[V+1];
            size = new int[V+1];
            child = new ArrayList[V+1];

            for(int i = 0; i <= V; i++){
                child[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i<E; i++){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
                child[a].add(b);
            }

            int cur = x;
            while(cur!=0){
                v1.add(cur);
                cur = parent[cur];
            }

            cur = y;
            while (cur!=0) {
                v2.add(cur);
                cur = parent[cur];
            }

            int idx1 = v1.size()-1;
            int idx2 = v2.size()-1;
            int ans = 1;
            while (idx1>=0&&idx2>=0) {
                if(v1.get(idx1).equals(v2.get(idx2)))ans = v1.get(idx1);
                idx1--;
                idx2--;
            }

            dfs(1);

            sb.append("#").append(t).append(" ").append(ans).append(" ").append(size[ans]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cur){
        size[cur] = 1;
        for(int i = 0; i<child[cur].size(); i++){
            dfs(child[cur].get(i));
            size[cur]+=size[child[cur].get(i)];
        }
    }
}
