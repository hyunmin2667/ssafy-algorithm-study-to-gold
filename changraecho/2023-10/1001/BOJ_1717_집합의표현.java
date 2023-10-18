import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {
    static int n, m;
    static int[] parent;

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        parent[pa] = pb;
    }

    static int findParent(int a) {
        if (a == parent[a])
            return a;

        return parent[a] = findParent(parent[a]);
    }

    static boolean hasSameParent(int a, int b) {
        int pa = findParent(a);
        int py = findParent(b);

        return pa == py;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int symbol = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (symbol == 0) {
                union(a, b);
            } else {
                if (hasSameParent(a, b)) {
                    sb.append("YES");
                } else {
                    sb.append("NO");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());

    }

}
