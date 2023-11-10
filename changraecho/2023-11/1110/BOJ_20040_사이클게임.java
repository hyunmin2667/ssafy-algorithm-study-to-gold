import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040_사이클게임 {
    static int n;
    static int m;

    static int[] parent;

    static void initParent() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    static int rep(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = rep(parent[a]);
    }

    static void union(int a, int b) {
        int repa = rep(a);
        int repb = rep(b);

        parent[repa] = repb;
    }

    static boolean sameSet(int a, int b) {
        return rep(a) == rep(b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        initParent();

        int ans = 1;

        for (int game = 0; game < m; game++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (sameSet(a, b)) {
                System.out.println(ans);
                return;
            }

            union(a, b);
            ans++;

        }

        System.out.println(0);

    }

}
