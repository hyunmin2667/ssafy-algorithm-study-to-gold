/*
check 출력
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static int[] num = new int[21];//x값 체크

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = 0;
            if (!"all".equals(op) && !"empty".equals(op)) {
                x = Integer.parseInt(st.nextToken());
            }

            switch (op) {
                case "add"://x가 없으면 추가
                    if (num[x] == 0) {
                        num[x] = 1;
                    }
                    break;
                case "remove"://x가 있으면 삭제 -> 근데 전부 삭제인가?
                    if (num[x] == 1)
                        num[x] = 0;
                    break;
                case "check":
                    sb.append(num[x] != 0 ? 1 : 0).append("\n");
                    break;
                case "toggle"://x가 있으면 삭제, 없으면 추가
                    if (num[x] == 1) num[x] = 0;
                    else num[x] = 1;
                    break;
                case "empty":
                    Arrays.fill(num, 0);
                    break;
                case "all":
                    Arrays.fill(num, 1);
                    break;
            }
        }
        System.out.println(sb);
    }
}
