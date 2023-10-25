import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_13909_창문닫기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println((int) Math.sqrt(N));

    }

}
