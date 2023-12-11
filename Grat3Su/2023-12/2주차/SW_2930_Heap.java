import java.util.*;
import java.io.*;
public class heap {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq =new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t<=T; t++){
            sb.append("#").append(t).append(" ");
            pq.clear();
            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());//1은 insert, 2는 pop

                if(op == 1){
                    int input = Integer.parseInt(st.nextToken());
                    pq.offer(input);
                }
                else{
                    int output = -1;
                    if(pq.size() > 0){
                        output = pq.poll();
                    }
                    sb.append(output).append(" ");
                }

            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
