
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1920_수찾기 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N
        int N = Integer.parseInt(br.readLine());

        // hashSet
        Set<Integer> numbers = new HashSet<>();

        // N개의 수들을 hashSet에 add
        StringTokenizer str = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers.add(Integer.parseInt(str.nextToken()));
        }

        // M
        int M = Integer.parseInt(br.readLine());

        // 각 숫자들이 numbers에 포함되어 있는 지 확인
        StringTokenizer str2 = new StringTokenizer(br.readLine());

        for (int i = 1; i <= M; i++) {
            int res = 0;
            if (numbers.contains(Integer.parseInt(str2.nextToken()))) {
                res = 1;
            }
            sb.append(res).append("\n");
        }

        System.out.println(sb.toString());

    }

}
