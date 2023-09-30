import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1033_칵테일 {
    static int N;
    static Fraction[][] graph; // 두 재료 사이의 비율을 나타내는 그래프
    static Fraction[] ratio; // 0번 재료를 1이라고 할 때, 나머지 재료들의 비율을 분수로 나타낸 배열
    static boolean[] visited; // 그래프에서 방문된 노드들을 기록하는 배열
    static StringBuilder sb = new StringBuilder();

    // 분수
    static class Fraction {
        long denom, numer;

        @Override
        public String toString() {
            return "Fraction [denom=" + denom + ", numer=" + numer + "]";
        }

        public Fraction(long numer, long denom) {
            super();
            this.numer = numer;
            this.denom = denom;

        }
    }

    // 분수들의 곱 정의
    static Fraction multiply(Fraction f1, Fraction f2) {
        long denom = f1.denom * f2.denom;
        long numer = f1.numer * f2.numer;

        long commonDiv = gcd(denom, numer);

        // 기약분수로 변경
        denom = denom / commonDiv;
        numer = numer / commonDiv;

        return new Fraction(numer, denom);
    }

    // gcd
    static long gcd(long a, long b) {

        // 일반성을 잃지 않고 a >= b
        if (a < b) {
            long temp = b;
            b = a;
            a = temp;
        }

        long r;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return b;

    }

    // lcm
    static long lcm(long a, long b) {
        // 일반성을 잃지 않고 a >= b
        if (a < b) {
            long temp = b;
            b = a;
            a = temp;
        }

        return (a * b) / gcd(a, b);
    }

    static void bfs() { // 0번 재료가 1만큼 필요하다고 생각하자. 이때 나머지 재료들의 0번 재료에 대한 비율을 구해보자
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(0);
        visited[0] = true;
        ratio[0] = new Fraction(1, 1);

        while (!queue.isEmpty()) {
            int ingredient = queue.poll();

            for (int i = 0; i < N; i++) {

                if (visited[i])
                    continue;
                if (graph[ingredient][i] == null)
                    continue;

                visited[i] = true;
                ratio[i] = multiply(ratio[ingredient], graph[ingredient][i]);
                queue.offer(i);

            }
        }
    }

    static void getAnswer() {
        // ratio 배열의 모든 원소들의 분모들의 최소공배수를 구해서, ratio 배열의 각 원소에 곱해준다.
        long largestCommonMultiple = 1;

        for (Fraction fraction : ratio) {
            largestCommonMultiple = lcm(largestCommonMultiple, fraction.denom);
        }

        for (int i = 0; i < N; i++) {
            ratio[i] = multiply(ratio[i], new Fraction(largestCommonMultiple, 1));
        }

        // ratio 배열의 모든 원소들의 최대공약수를 구한다. ratio 배열의 각 원소를 최대공약수로 나눠준다.
        long greatestCommonDivisor = ratio[0].numer;

        for (Fraction fraction : ratio) {
            greatestCommonDivisor = gcd(greatestCommonDivisor, fraction.numer);
        }

        for (int i = 0; i < N; i++) {
            ratio[i] = multiply(ratio[i], new Fraction(1, greatestCommonDivisor));
        }

        // 정답을 출력한다
        for (int i = 0; i < N; i++) {
            sb.append(ratio[i].numer).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new Fraction[N][N];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int greatestCommonDivisor = (int) gcd(p, q);

            p = p / greatestCommonDivisor;
            q = q / greatestCommonDivisor;

            graph[a][b] = new Fraction(q, p);
            graph[b][a] = new Fraction(p, q);

        }

        ratio = new Fraction[N];
        visited = new boolean[N];

        bfs();
        getAnswer();

    }
}
