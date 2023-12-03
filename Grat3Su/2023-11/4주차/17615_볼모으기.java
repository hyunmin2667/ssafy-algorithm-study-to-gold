import java.io.*;

/**
 * 1. 오른쪽에 R이 오냐 B가 오냐만?
 */
public class Main {
    static int N, maxR, maxB, ans;
    static char[] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N];
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            map[i] = s.charAt(i);
        }
        //구현
        ans = Integer.MAX_VALUE;        
        RRight();
        RLeft();
        BRight();
        BLeft();
        System.out.println(ans);
    }
    
    //R이 오른쪽으로 간다.
    static void RRight() {
        int cnt = 0;
        for (int i = N - 1; i > 0; i--) {
            if (map[i] == 'R')//R인경우 무시
                continue;
            for (int j = i - 1; j > -1; j--) {
                if (map[j] == 'R') {
                    cnt++;
                    i = j;
                    if (cnt > ans)
                        return;
                }
            }
        }

        ans = Math.min(ans, cnt);
    }
    static void RLeft() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (map[i] == 'R')//R인경우 무시
                continue;
            for (int j = i+1; j <N; j++) {
                if (map[j] == 'R') {
                    cnt++;
                    i = j;
                    if (cnt > ans)
                        return;
                }
            }
        }

        ans = Math.min(ans, cnt);
    }
    static void BLeft() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (map[i] == 'B')//B인경우 무시
                continue;
            for (int j = i+1; j <N; j++) {
                if (map[j] == 'B') {
                    cnt++;
                    i = j;
                    if (cnt > ans)
                        return;
                }
            }
        }

        ans = Math.min(ans, cnt);
    }
    static void BRight() {
        int cnt = 0;
        for (int i = N - 1; i > 0; i--) {
            if (map[i] == 'B')//B인경우 무시
                continue;
            for (int j = i - 1; j > -1; j--) {
                if (map[j] == 'B') {
                    cnt++;
                    i = j;
                    if (cnt > ans)
                        return;
                }
            }
        }

        ans = Math.min(ans, cnt);
    }
}
