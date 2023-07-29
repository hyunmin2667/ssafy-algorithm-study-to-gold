import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BOJ_1427_소트인사이드 {
    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = (br.readLine());
        Integer len = N.length();
        int[] arr = new int[len];
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < len; i++) {
            arr[i] = N.charAt(i) - '0';
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            res.append(arr[i]);
        }

        System.out.println(res);

    }
}
