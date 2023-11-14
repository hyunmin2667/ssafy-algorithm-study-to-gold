import java.io.*;
import java.util.*;


public class Solution {
    static int N, K;
    static int[] weight, value;
    static int[] memoi;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            memoi = new int[K+1];
            weight = new int[N+1];
            value = new int[N+1];

            for(int i = 1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                weight[i] = Integer.parseInt(st.nextToken());
                value[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i<=N; i++){
                int w = weight[i];
                int v = value[i];
                for(int k = K; k>=w; k--){
                    if(w<=k){
                        memoi[k] = Math.max(memoi[k], memoi[k-w]+v);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(memoi[K]).append("\n");
        }

        System.out.println(sb);
    }
}
