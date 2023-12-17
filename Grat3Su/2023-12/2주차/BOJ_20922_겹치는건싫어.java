import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        int start = 0;
        int end = 0;
        int[] cnt = new int[100001];

        while (end<N){
            while (end<N&&cnt[arr[end]]+1<=K){
                cnt[arr[end]]++;
                end++;
            }
            int len = end-start;
            ans = Math.max(ans, len);
            cnt[arr[start]]--;
            start++;
        }
        System.out.println(ans);
    }
}
