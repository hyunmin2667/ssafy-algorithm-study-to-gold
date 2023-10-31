import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1750_서로소의개수 {
    static int N;
    static int[] seq; // (N + 1) �迭. ���̰� N�� ����
    static int[][] dp; // (N + 1) * (100001) �迭.
                       // dp[i][j]: ù��° ������ i��° �� ������ �κм����� ����. �� �κм����� �������� �ƴ�
                       // �κ����� �� �ִ������� j�� �͵��� ����

    static final int DIVIDER = 10000003;

    static int gcd(int a, int b) { // a�� b�� �ִ�����
        // �Ϲݼ��� ���� �ʰ� a <= b
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // ���� a�� 0�̸� b�� return�Ѵ�
        if (a == 0)
            return b;

        // �׷��� �ʴٸ� gcd(a, b) = gcd(r, a): r�� b�� a�� ���� ������
        int r = b % a;
        return gcd(r, a);
    }

    static void fillDp() {
        dp = new int[N + 1][100001];
        dp[1][seq[1]] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][seq[i]] = 1;

            for (int k = 1; k <= 100000; k++) {
                dp[i][k] += dp[i - 1][k];
                dp[i][gcd(seq[i], k)] += dp[i - 1][k];
                dp[i][gcd(seq[i], k)] %= DIVIDER;
            }
        }

        // i = 1 ~ N�� ����
        // ���� {seq[i]}

        // seq[i]�� �����ϴ�, ������ ������ 2�̻��� �κ�����
        // k = 1 ~ k = 100000�� ����
        // dp[i][gcd(seq[i], k)] += dp[i - 1][k]
        // dp�� DIVIDER�� ������
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seq = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }

        fillDp();

        System.out.println(dp[N][1]);

    }

}