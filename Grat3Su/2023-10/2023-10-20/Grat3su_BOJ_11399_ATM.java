package algo_A;
import java.io.*;
import java.util.*;
public class BOJ_11399_ATM {
    static int N;
    static int[] atm;
    static int[] sumArr;

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        atm = new int[N];
        sumArr= new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            atm[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N; i++){
            int idx = i;
            int t = atm[i];

            for(int j = i-1; j>-1; j--){
                if(atm[j]<atm[i]){
                    idx = j+1;
                    break;
                }
                if(j==0){
                    idx = 0;
                }
            }

            for(int j = i; j>idx; j--){
                atm[j] = atm[j-1];
            }
            atm[idx] = t;
        }
        sumArr[0] = atm[0];
        for(int i = 1; i<N; i++){
            sumArr[i] = sumArr[i-1]+atm[i];
        }

        int sum = 0;
        for(int i = 0; i<N; i++){
            sum = sum+sumArr[i];
        }
        System.out.println(sum);
    }
}
